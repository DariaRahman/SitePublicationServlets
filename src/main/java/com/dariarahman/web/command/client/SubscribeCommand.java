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
// Класс SubscribeCommand реализует интерфейс Command.
// Этот класс отвечает за обработку запроса подписки пользователя на периодическое издание.
public class SubscribeCommand implements Command {

    private static final Logger log = LogManager.getLogger(SubscribeCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        log.debug("SubscribeCommand starts");
        HttpSession session = req.getSession();

        String periodicalIdStr = req.getParameter("periodicalId");
        log.trace("periodicalIdStr ==> " + periodicalIdStr);
        long periodicalId = Long.parseLong(req.getParameter("periodicalId"));
        log.trace("periodicalId ==> " + periodicalId);

        User user = (User) session.getAttribute("user");
        long userId = user.getId();

        if (DaoFactory.createSubscriptionDao().isSubscribed(userId, periodicalId)) {
            log.debug("user is already subscribed, forward to error page");
            return Path.PAGE_SUBSCRIBED_USER;
        }

        session.setAttribute("periodical", DaoFactory.createPeriodicalDao().findPeriodicalById(periodicalId));

        log.debug("SubscribeCommand finished");
        return Path.PAGE_SUBSCRIBE;
    }
}
