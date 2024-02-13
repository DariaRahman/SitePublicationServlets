package com.dariarahman.web.command;

import com.dariarahman.exceptions.DBException;
import com.dariarahman.dao.DaoFactory;
import com.dariarahman.dao.UserDao;
import com.dariarahman.entity.Gender;
import com.dariarahman.entity.User;
import com.dariarahman.web.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// SignupCommand реализует интерфейс Command
// и используется для обработки запроса на регистрацию нового пользователя
public class SignupCommand implements Command {

    private static final Logger log = LogManager.getLogger(SignupCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        log.debug("SignupCommand starts");
        String address = "signup.jsp";

        String methodthodName = req.getMethod();
        log.trace("input method ==> " + methodthodName);

        if (methodthodName.equals("GET")) {
            return Path.PAGE_SIGN_UP;
        }

        // Извлечение параметров запроса
        String email = req.getParameter("email");
        log.trace("email ==> " + email);

        String firstName = req.getParameter("first_name");
        log.trace("firstName ==> " + firstName);

        String lastName = req.getParameter("last_name");
        log.trace("lastName ==> " + lastName);

        Gender gender = Gender.valueOf(req.getParameter("gender").toUpperCase());
        log.trace("gender ==> " + gender);

        String password = req.getParameter("password");
        log.trace("password ==> " + password);

        // Создание объекта User и заполнение его свойств данными из запроса
        User user = new User();
        user.setRoleId(1);
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setGender(gender);
        user.setPassword(password);

        // Создание экземпляра UserDao с использованием DaoFactory и добавление пользователя в базу данных
        UserDao userDao = DaoFactory.createUserDao();
        boolean isAdd = userDao.addUser(user);

        // Установка адреса страницы для перенаправления пользователя в зависимости от результата регистрации
        if (isAdd) {
            address = Path.PAGE_LOGIN;
        }

        log.debug("SignupCommand finished");
        return address;
    }
}


