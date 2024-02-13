package com.dariarahman.web.command;

import com.dariarahman.exceptions.DBException;
import com.dariarahman.dao.DaoFactory;
import com.dariarahman.dao.UserDao;
import com.dariarahman.entity.Role;
import com.dariarahman.entity.User;
import com.dariarahman.web.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// Класс LoginCommand, который реализует интерфейс Command.
// Этот класс представляет команду для выполнения процесса аутентификации пользователя.
public class LoginCommand implements Command {

    private static final Logger log = LogManager.getLogger(LoginCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        log.debug("LoginCommand starts");

        HttpSession session = req.getSession();

        String email = req.getParameter("email");
        log.trace("email ==> " + email);
        String password = req.getParameter("password");
        log.trace("password ==> " + password);

        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            log.warn("Login/password cannot be empty");
            return Path.PAGE_LOGIN; // Если email или пароль отсутствуют или пусты, возвращается адрес страницы логина
        }

        UserDao userDao = DaoFactory.createUserDao();
        User user = userDao.findUserByEmail(email);
        log.trace("user ==> " + user);

        if (user == null || !password.equals(user.getPassword())) {
            log.warn("user is null or wrong password");
            return Path.PAGE_LOGIN; // Если пользователь не найден или пароль неверный, возвращается адрес страницы логина
        }

        initSession(session, user); // Инициализация сессии для аутентифицированного пользователя

        log.debug("LoginCommand finished");
        return Path.COMMAND_MAIN_PAGE; // Возвращение адреса главной страницы
    }


    private void initSession(HttpSession session, User user) {
        Role userRole = Role.getRole(user);
        log.trace("userRole ==> " + userRole);

        session.setAttribute("user", user); // Установка атрибута "user" в сессию, содержащего информацию о пользователе
        log.trace("user ==> " + user);

        session.setAttribute("userRole", userRole); // Установка атрибута "userRole" в сессию, содержащего роль пользователя
    }
}



