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
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;

// Класс EditPeriodical реализует интерфейс Command
// и используется для обработки запроса на редактирование периодического издания
public class EditPeriodical implements Command {

    private static final Logger log = LogManager.getLogger(EditPeriodical.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        log.debug("EditPeriodicalCommand starts");

        HttpSession session = req.getSession();
        Periodical periodical = null;
        String methodName = req.getMethod();
        log.trace("input method ==> " + methodName);

        if (methodName.equals("GET")) {
            long periodicalId = Long.parseLong(req.getParameter("periodicalId"));
            periodical = DaoFactory.createPeriodicalDao().findPeriodicalById(periodicalId);
            session.setAttribute("periodical", periodical);
            session.setAttribute("periodicalId", periodicalId);
            return Path.PAGE_EDIT_PERIODICAL;
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
        log.trace("priceStr==> " + priceStr);

        if (periodicalName == null || periodicalName.isEmpty() || periodicalType == null || periodicalType.isEmpty()
                || publisher == null || publisher.isEmpty() || frequency == null || frequency.isEmpty()
                || priceStr == null || priceStr.isEmpty() || description == null || description.isEmpty()) {
            return Path.COMMAND_ADD_PERIODICAL;
        }

        BigDecimal price = new BigDecimal(priceStr);
        log.trace("price ==> " + price);

        periodical = new Periodical();
        periodical.setId((Long) session.getAttribute("periodicalId"));
        periodical.setName(periodicalName);
        periodical.setType(periodicalType);
        periodical.setPublisher(publisher);
        periodical.setFrequency(frequency);
        periodical.setPrice(price);
        periodical.setDescription(description);

        PeriodicalDao periodicalDao = DaoFactory.createPeriodicalDao();
        periodicalDao.updatePeriodical(periodical);

        log.debug("EditPeriodicalCommand finished");
        return Path.COMMAND_MAIN_PAGE;
    }
}
