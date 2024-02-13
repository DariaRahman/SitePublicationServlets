package com.dariarahman.web.command.admin;

import com.dariarahman.exceptions.DBException;
import com.dariarahman.dao.DaoFactory;
import com.dariarahman.dao.PeriodicalDao;
import com.dariarahman.entity.Periodical;
import com.dariarahman.web.Path;
import com.dariarahman.web.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

// Класс AddPeriodicalCommand обрабатывает запросы на добавление периодического издания,
// извлекает параметры из запроса, проверяет их наличие и корректность,
// создает объект Periodical и добавляет его в базу данных через PeriodicalDao.
public class AddPeriodicalCommand implements Command {

    private static final Logger log = LogManager.getLogger(AddPeriodicalCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException { //метод выполняет обработку запроса
        log.debug("AddPeriodicalCommand starts");

        String methodName = req.getMethod();
        log.trace("input method ==> " + methodName);

        if (methodName.equals("GET")) {
            return Path.PAGE_ADD_PERIODICAL;
        }

        String periodicalName = req.getParameter("periodicalName");
        log.trace("periodical name ==> " + periodicalName);

        String periodicalType = req.getParameter("periodicalType");
        log.trace("periodical type ==> " + periodicalType);

        String publisher = req.getParameter("publisher");
        log.trace("publisher ==> " + publisher);

        String frequency = req.getParameter("frequency");
        log.trace("frequency ==> " + frequency);

        String description = req.getParameter("periodicalDescription");
        log.trace("description ==> " + description);

        String priceStr = req.getParameter("periodicalPrice");
        if (periodicalName == null || periodicalName.isEmpty() || periodicalType == null || periodicalType.isEmpty()
                || publisher == null || publisher.isEmpty() || frequency == null || frequency.isEmpty()
                || priceStr == null || priceStr.isEmpty() || description == null || description.isEmpty()) {
            return Path.COMMAND_ADD_PERIODICAL;
        }

        BigDecimal price;

        try {
            price = new BigDecimal(Integer.parseInt(priceStr));
        } catch (NumberFormatException ex) {
            return Path.COMMAND_ADD_PERIODICAL;
        }
        log.trace("price ==> " + price);

        Periodical periodical = new Periodical();
        periodical.setName(periodicalName);
        periodical.setType(periodicalType);
        periodical.setPublisher(publisher);
        periodical.setFrequency(frequency);
        periodical.setPrice(price);
        periodical.setDescription(description);

        PeriodicalDao periodicalDao = DaoFactory.createPeriodicalDao();
        periodicalDao.addPeriodical(periodical);

        log.debug("AddPeriodicalCommand finished");
        return Path.COMMAND_MAIN_PAGE;
    }
}
