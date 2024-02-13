package com.dariarahman.web.command;

import com.dariarahman.exceptions.DBException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// NoCommand реализует интерфейс Command и используется, когда не найдена команда для обработки запроса
public class NoCommand implements Command {

    private static final Logger log = LogManager.getLogger(NoCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        log.debug("Command starts");

        String errorMessage = "No such command";
        req.setAttribute("errorMessage", errorMessage); // Устанавливает атрибут "errorMessage" в запросе с сообщением об ошибке
        log.error("Set the request attribute: errorMessage --> " + errorMessage);

        log.debug("Command finished");

        log.error(errorMessage); // Логирует ошибку с сообщением "No such command"
        throw new DBException(errorMessage); // Генерирует исключение DBException с сообщением "No such command"
    }
}
