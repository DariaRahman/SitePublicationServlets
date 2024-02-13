package com.dariarahman.web.command.admin;

import com.dariarahman.exceptions.DBException;
import com.dariarahman.web.Path;
import com.dariarahman.web.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Класс AdminPageCommand используется для перенаправления
// пользователя на страницу администратора при обработке соответствующего запроса
public class AdminPageCommand implements Command {

    private static final Logger log = LogManager.getLogger(AdminPageCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        log.debug("AdminPageCommand");
        return Path.PAGE_ADMIN;
    }
}
