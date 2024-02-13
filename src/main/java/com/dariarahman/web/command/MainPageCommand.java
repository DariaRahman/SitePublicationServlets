package com.dariarahman.web.command;

import com.dariarahman.exceptions.DBException;
import com.dariarahman.dao.DaoFactory;
import com.dariarahman.entity.Periodical;
import com.dariarahman.entity.Role;
import com.dariarahman.web.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.List;

// Класс MainPageCommand реализует интерфейс Command.
// Этот класс представляет команду для обработки главной страницы приложения.
public class MainPageCommand implements Command {

    private static final Logger log = LogManager.getLogger(MainPageCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        log.debug("MainPageCommand starts");

        // Получение текущей сессии из запроса
        HttpSession session = req.getSession();
        // Переменная для хранения адреса страницы
        String address = Path.PAGE_ERROR;
        // Список периодических изданий
        List<Periodical> periodicals;

        // Проверка параметра isSearch из запроса
        String isSearch = req.getParameter("isSearch");
        if (isSearch != null) {
            // Если isSearch не равен null, выполняется поиск периодических изданий по имени
            periodicals = DaoFactory.createPeriodicalDao().findPeriodicalsByName(req.getParameter("search"));
        } else {
            // Если isSearch равен null, выполняется сортировка периодических изданий
            // Получение параметра sortBy из запроса
            String sortName = req.getParameter("sortBy");
            // Получение всех периодических изданий из базы данных
            periodicals = DaoFactory.createPeriodicalDao().findAllPeriodicals();

            // Проверка значения параметра sortBy и выполнение соответствующей сортировки
            if (sortName == null || sortName.isEmpty() || sortName.equals("type")) {
                sortByType(periodicals);
                session.setAttribute("sortName", "type");
            } else if (sortName.equals("name")) {
                sortByName(periodicals);
                session.setAttribute("sortName", "name");
            } else if (sortName.equals("price")) {
                sortByPrice(periodicals);
                session.setAttribute("sortName", "price");
            }
        }
        // Сохранение списка периодических изданий в атрибут сессии
        session.setAttribute("periodicals", periodicals);

        // Получение роли пользователя из атрибута сессии
        Role userRole = (Role) session.getAttribute("userRole");
        log.trace("userRole ==> " + userRole);

        // Определение адреса страницы в зависимости от роли пользователя
        if (userRole == null) {
            address = Path.PAGE_MAIN;
        } else if (userRole == Role.ADMIN) {
            address = Path.COMMAND_ADMIN_PAGE;
        } else if (userRole == Role.CLIENT) {
            address = Path.COMMAND_CLIENT_PAGE;
        }

        log.trace("address ==> " + address);
        log.debug("MainPageCommand finished");
        return address;
    }

    // Вспомогательные методы для сортировки списка периодических изданий
    private List<Periodical> sortByType(List<Periodical> periodicals) {
        periodicals.sort(Comparator.comparing(Periodical::getType));
        return periodicals;
    }

    private List<Periodical> sortByName(List<Periodical> periodicals) {
        periodicals.sort(Comparator.comparing(Periodical::getName));
        return periodicals;
    }

    private List<Periodical> sortByPrice(List<Periodical> periodicals) {
        periodicals.sort(Comparator.comparing(Periodical::getPrice));
        return periodicals;
    }
}
