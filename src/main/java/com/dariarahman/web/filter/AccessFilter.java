package com.dariarahman.web.filter;

import com.dariarahman.exceptions.DBException;
import com.dariarahman.dao.DaoFactory;
import com.dariarahman.entity.Role;
import com.dariarahman.entity.User;
import com.dariarahman.web.Path;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

// Этот код представляет собой реализацию фильтра доступа к ресурсам на основе ролей пользователей в веб-приложении.
// Фильтр проверяет, имеет ли текущий пользователь право на выполнение запрошенной команды.
// Если у пользователя есть доступ, фильтр пропускает запрос дальше по цепочке фильтров или в сервлет,
// если фильтр является последним в цепочке.
// Если доступ запрещен, фильтр перенаправляет запрос на страницу ошибки доступа.
public class AccessFilter implements Filter {

    private static final Logger log = LogManager.getLogger(AccessFilter.class);

    private static final Map<Role, List<String>> accessMap = new ConcurrentHashMap<>();
    private static List<String> outOfControl = new CopyOnWriteArrayList<>();

    // Метод инициализации фильтра
    @Override
    public void init(FilterConfig filterConfig) {
        log.debug("AccessFilter init...");

        // Получение конфигурационных параметров из web.xml и инициализация доступа для каждой роли
        accessMap.put(Role.ADMIN, asList(filterConfig.getInitParameter("admin")));
        accessMap.put(Role.CLIENT, asList(filterConfig.getInitParameter("client")));

        outOfControl = asList(filterConfig.getInitParameter("out-of-control"));
        log.trace("out of control commands ==> " + outOfControl);

        log.debug("AccessFilter init finished");
    }

    // Метод фильтрации запросов
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.debug("AccessFilter is starting..");

        HttpServletRequest req = (HttpServletRequest) request;

        // Проверка доступа для текущего запроса
        if (checkAccess(req, response)) {
            log.debug("access is allowed");
            chain.doFilter(request, response);
        } else {
            log.debug("access is denied");
            String errorMessasge = "You do not have permission to access the requested resource";

            request.setAttribute("errorMessage", errorMessasge);

            request.getRequestDispatcher(Path.PAGE_ERROR_ACCESS)
                    .forward(request, response);
        }
    }

    // Метод проверки доступа
    private boolean checkAccess(HttpServletRequest request, ServletResponse response) throws ServletException, IOException {
        log.debug("checkAccess...");

        String commandName = request.getParameter("command");
        log.trace("commandName ==> " + commandName);

        // Если команда находится в списке исключений, доступ разрешен
        if (outOfControl.contains(commandName)) {
            log.debug("this command does not require access");
            return true;
        }

        HttpSession session = request.getSession(false);


        // Если сессия не существует, доступ запрещен
        if (session == null) {
            log.debug("session is null");
            return false;
        }

        User user = (User) session.getAttribute("user");
        // Если пользователь не аутентифицирован, доступ запрещен
        if (user == null) {
            log.debug("user is null");
            return false;
        }

        if (commandName == null || commandName.isEmpty()) {
            log.debug("command is null");
            return false;
        }

        //Update user info
        try {
            user = DaoFactory.createUserDao().findUserByID(user.getId());
        } catch (DBException e) {
            request.setAttribute("error", e);
            request.getRequestDispatcher(Path.PAGE_ERROR)
                    .forward(request, response);
        }
        log.trace("user ==>" + user);

        Role userRole = Role.getRole(user);

        if (user.isStatus()) {
            log.debug("user is blocked");
            request.getRequestDispatcher(Path.PAGE_BLOCKED_USER)
                    .forward(request, response);
        }

        log.debug("user is not blocked");
        return accessMap.get(userRole).contains(commandName);
    }

    @Override
    public void destroy() {
        System.out.println("AccessFilter destroy...");
    }

    private List<String> asList(String str) {
        List<String> list = new CopyOnWriteArrayList<>();
        StringTokenizer st = new StringTokenizer(str);
        while (st.hasMoreTokens()) list.add(st.nextToken());
        return list;
    }
}