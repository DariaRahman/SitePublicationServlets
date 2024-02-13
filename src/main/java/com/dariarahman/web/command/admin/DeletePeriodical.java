package com.dariarahman.web.command.admin;

import com.dariarahman.exceptions.DBException;
import com.dariarahman.dao.DaoFactory;
import com.dariarahman.web.Path;
import com.dariarahman.web.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Класс DeletePeriodical реализует интерфейс Command
// и используется для обработки запроса на удаление периодического издания.
public class DeletePeriodical implements Command {

    private static final Logger log = LogManager.getLogger(DeletePeriodical.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        log.debug("DeletePeriodicalCommand starts");

        long periodicalId = Long.parseLong(req.getParameter("periodicalId"));
        log.trace("periodicalId ==> " + periodicalId);

        DaoFactory.createPeriodicalDao().deletePeriodicalById(periodicalId);

        log.debug("DeletePeriodicalCommand finished");
        return Path.COMMAND_MAIN_PAGE;
    }
}
