package com.dariarahman.web.command.admin;

import com.dariarahman.exceptions.DBException;
import com.dariarahman.web.Path;
import com.dariarahman.web.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Класс AdminProfileCommand реализует интерфейс Command
// и используется для обработки запроса на отображение профиля администратора
public class AdminProfileCommand implements Command {

    private static final Logger log = LogManager.getLogger(AdminProfileCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        log.debug("AdminProfileCommand");
        return Path.PAGE_ADMIN_PROFILE;
    }
}
