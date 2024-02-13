package com.dariarahman;

import com.dariarahman.exceptions.DBException;
import com.dariarahman.web.Path;
import com.dariarahman.web.command.Command;
import com.dariarahman.web.command.CommandContainer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// Класс Controller, который является сервлетом и обрабатывает HTTP-запросы GET и POST
@WebServlet(name = "Controller", value = "/controller")
public class Controller extends HttpServlet {

    private static final Logger log = LogManager.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("Controller#doGet starts");

        String address = Path.PAGE_ERROR; // Устанавливаем адрес по умолчанию на страницу ошибки
        String commandName = req.getParameter("command"); // Получаем значение параметра "command" из запроса
        log.trace("command ==> " + commandName);

        Command command = CommandContainer.getCommand(commandName); // Получаем команду по имени из CommandContainer

        try {
            address = command.execute(req, resp); // Выполняем команду и получаем адрес для перенаправления
            log.trace("address ==> " + address);
        } catch (DBException ex) {
            req.setAttribute("error", ex); // Если происходит исключение типа DBException, добавляем его в атрибут запроса "error"
        }

        log.debug("Controller#doGet finished");
        req.getRequestDispatcher(address).forward(req, resp); // Перенаправляем запрос и ответ на указанный адрес
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.debug("Controller#doGPost starts");

        String address = "error.jsp"; // Устанавливаем адрес по умолчанию на страницу ошибки
        String commandName = req.getParameter("command"); // Получаем значение параметра "command" из запроса
        log.trace("command ==> " + commandName);

        Command command = CommandContainer.getCommand(commandName); // Получаем команду по имени из CommandContainer

        try {
            address = command.execute(req, resp); // Выполняем команду и получаем адрес для перенаправления
            log.trace("address ==> " + address);
        } catch (DBException ex) {
            req.setAttribute("error", ex); // Если происходит исключение типа DBException, добавляем его в атрибут запроса "error"
        }

        StringBuilder q = new StringBuilder(req.getContextPath());
        q.append(address);

        log.debug("Controller#doGPost finished");
        resp.sendRedirect(q.toString()); // Перенаправляем клиента на указанный адрес
    }
}
