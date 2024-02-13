package com.dariarahman.web.command.client;

import com.dariarahman.exceptions.DBException;
import com.dariarahman.dao.DaoFactory;
import com.dariarahman.entity.User;
import com.dariarahman.web.Path;
import com.dariarahman.web.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

// Класс TopUpBalanceCommand реализует интерфейс Command.
// Этот класс отвечает за обработку запроса пополнения баланса пользователя
public class TopUpBalanceCommand implements Command {

    private static final Logger log = LogManager.getLogger(TopUpBalanceCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        log.debug("TopUpBalanceCommand starts");

        String methodName = req.getMethod();
        log.trace("input method ==> " + methodName);

        if (methodName.equals("GET")) {
            return Path.PAGE_TOP_UP_BALANCE; // Если метод запроса GET, возвращается адрес страницы пополнения баланса
        }

        HttpSession session = req.getSession();
        BigDecimal amount = new BigDecimal(req.getParameter("amount"));
        log.trace("amount ==> " + amount);
        User user = (User) session.getAttribute("user");
        log.trace("user ==> " + user);
        user.setBalance(user.getBalance().add(amount)); // Увеличение баланса пользователя на указанную сумму

        DaoFactory.createUserDao().updateUser(user); // Обновление информации о пользователе в базе данных
        session.setAttribute("user", user); // Обновление информации о пользователе в сессии

        log.debug("TopUpBalanceCommand finished");
        return Path.COMMAND_MAIN_PAGE; // Возвращение адреса главной страницы
    }
}


