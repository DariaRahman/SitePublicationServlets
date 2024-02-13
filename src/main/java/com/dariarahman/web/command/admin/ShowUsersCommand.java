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
import javax.servlet.http.HttpSession;
import java.util.List;

// Класс ShowUsersCommand реализует интерфейс Command
// и используется для обработки запроса на отображение списка пользователей
public class ShowUsersCommand implements Command {

    private static final Logger log = LogManager.getLogger(ShowUsersCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        log.debug("ShowUsersCommand starts");

        List<User> users = DaoFactory.createUserDao().findAllUsers();
        HttpSession session = req.getSession();

        session.setAttribute("users", users);

        log.debug("ShowUsersCommand finished");
        return Path.PAGE_USERS;
    }
}
