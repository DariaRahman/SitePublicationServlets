package com.dariarahman.web.command.client;

import com.dariarahman.exceptions.DBException;
import com.dariarahman.entity.User;
import com.dariarahman.web.Path;
import com.dariarahman.web.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Класс ClientProfileCommand реализует интерфейс Command
// и используется для обработки запроса на отображение профиля клиента
public class ClientProfileCommand implements Command {

    private static final Logger log = LogManager.getLogger(ClientProfileCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        log.debug("ClientProfileCommand starts");

        User user = (User) req.getSession().getAttribute("user");
        log.trace("user ==> " + user);

        log.debug("ClientProfileCommand finished");
        return Path.PAGE_CLIENT_PROFILE;
    }
}
