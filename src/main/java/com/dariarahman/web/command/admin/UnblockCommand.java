package com.dariarahman.web.command.admin;

import com.dariarahman.exceptions.DBException;
import com.dariarahman.dao.DaoFactory;
import com.dariarahman.entity.User;
import com.dariarahman.web.Path;
import com.dariarahman.web.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Класс UnblockCommand реализует интерфейс Command
// и используется для обработки запроса на разблокировку пользователя
public class UnblockCommand implements Command {

    private static final Logger log = LogManager.getLogger(UnblockCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        log.debug("UnblockCommand starts");

        long userId = Long.parseLong(req.getParameter("userId"));
        log.trace("userId ==> " + userId);

        User user = DaoFactory.createUserDao().findUserByID(userId);
        user.setStatus(false);
        DaoFactory.createUserDao().updateUser(user);

        log.debug("UnblockCommand finished");
        return Path.COMMAND_PAGE_USERS;
    }
}
