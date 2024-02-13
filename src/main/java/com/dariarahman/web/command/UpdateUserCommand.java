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

// Данный код представляет собой реализацию команды UpdateUserCommand,
// которая обрабатывает запрос на обновление данных пользователя
public class UpdateUserCommand implements Command {

    private static final Logger log = LogManager.getLogger(UpdateUserCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        log.debug("UpdateUserCommand starts");
        String address = Path.COMMAND_CLIENT_PROFILE;

        String methodName = req.getMethod();
        log.trace("input method ==> " + methodName);

        if (methodName.equals("GET")) {
            return Path.PAGE_UPDATE_USER;
        }

        // Извлечение параметров запроса
        String email = req.getParameter("email");
        log.trace("email ==> " + email);

        String firstName = req.getParameter("first_name");
        log.trace("firstName ==> " + firstName);

        String lastName = req.getParameter("last_name");
        log.trace("lastName ==> " + lastName);

        String password = req.getParameter("password");
        log.trace("password ==> " + password);

        String genderStr = req.getParameter("gender");

        // Проверка наличия всех необходимых параметров
        if (email == null || email.isEmpty() || firstName == null || firstName.isEmpty()
                || lastName == null || lastName.isEmpty() || password == null || password.isEmpty()
                || genderStr == null || genderStr.isEmpty()) {
            return Path.COMMAND_CLIENT_PROFILE;
        }

        // Преобразование строки с полом пользователя в объект Gender
        Gender gender = Gender.valueOf(genderStr.toUpperCase());
        log.trace("gender ==> " + gender);

        // Получение текущего пользователя из сессии и обновление его данных
        User user = (User) req.getSession().getAttribute("user");
        log.trace("user before update ==> " + user);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setGender(gender);
        user.setPassword(password);

        // Создание экземпляра UserDao с использованием DaoFactory и обновление данных пользователя в базе данных
        UserDao userDao = DaoFactory.createUserDao();
        userDao.updateUserWithoutBalance(user);

        // Получение обновленных данных пользователя из базы данных и сохранение их в сессии
        user = userDao.findUserByID(user.getId());
        log.trace("user after update ==> " + user);
        req.getSession().setAttribute("user", user);

        log.debug("UpdateUserCommand finished");
        return address;
    }
}

