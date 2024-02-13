package com.dariarahman.web.command;

import com.dariarahman.exceptions.DBException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Интерфейс Command, который представляет команду веб-приложения. Интерфейс Command содержит один метод execute,
// который принимает объекты HttpServletRequest и HttpServletResponse и возвращает строковое значение
public interface Command {
    String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException;
}
