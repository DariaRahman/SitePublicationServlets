package com.dariarahman.web.command.client;

import com.dariarahman.exceptions.DBException;
import com.dariarahman.dao.DaoFactory;
import com.dariarahman.entity.User;
import com.dariarahman.web.Path;
import com.dariarahman.web.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// Класс ShowSubscriptionsCommand, реализует интерфейс Command.
// Этот класс отвечает за обработку запроса, связанного с отображением подписок пользователя.
public class ShowSubscriptionsCommand implements Command {

    private static final Logger log = LogManager.getLogger(ShowSubscriptionsCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        log.debug("ShowSubscriptionsCommand starts");

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        log.trace("user ==> " + user);
        session.setAttribute("subscriptionsInfo", DaoFactory.createSubscriptionDao().getSubscriptionsInfo(user.getId()));

        log.debug("ShowSubscriptionsCommand finished");
        return Path.PAGE_SUBSCRIPTIONS;
    }
}
