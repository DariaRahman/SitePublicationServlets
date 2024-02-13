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

// Класс BlockCommand реализует интерфейс Command
// и используется для обработки запроса на блокировку пользователя
public class BlockCommand implements Command {

    private static final Logger log = LogManager.getLogger(BlockCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        log.debug("BlockCommand starts");

        long userId = Long.parseLong(req.getParameter("userId"));

        User user = DaoFactory.createUserDao().findUserByID(userId);
        log.trace("user ==> " + user);
        user.setStatus(true);
        DaoFactory.createUserDao().updateUser(user);

        log.debug("BlockCommand starts");
        return Path.COMMAND_PAGE_USERS;
    }
}
