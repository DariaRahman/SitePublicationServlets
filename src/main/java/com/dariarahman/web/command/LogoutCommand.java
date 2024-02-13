package com.dariarahman.web.command;

import com.dariarahman.exceptions.DBException;
import com.dariarahman.web.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// Класс реализует процесс выхода пользователя из системы (логаут),
// путем завершения текущей сессии и перенаправления на страницу логина
public class LogoutCommand implements Command {

    private static final Logger log = LogManager.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        log.debug("LogoutCommand starts");

        // Получение текущей сессии из запроса
        HttpSession session = req.getSession(false);

        if (session != null) {
            session.invalidate(); // Завершение текущей сессии, удаление всех связанных с ней атрибутов и освобождение ресурсов
        }

        log.debug("LogoutCommand finished");
        return Path.PAGE_LOGIN; // Возвращение адреса страницы логина
    }
}


