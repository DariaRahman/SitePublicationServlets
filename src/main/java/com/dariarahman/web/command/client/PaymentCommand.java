package com.dariarahman.web.command.client;

import com.dariarahman.exceptions.DBException;
import com.dariarahman.entity.SubscriptionPeriod;
import com.dariarahman.web.Path;
import com.dariarahman.web.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// Класс PaymentCommand реализует интерфейс Command и используется для обработки запроса на оплату
public class PaymentCommand implements Command {

    private static final Logger log = LogManager.getLogger(PaymentCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        log.debug("PaymentCommand starts");

        String methodName = req.getMethod();
        log.trace("input method ==> " + methodName);

        if (methodName.equals("GET")) {
            return Path.PAGE_PAYMENT; // Если метод запроса GET, возвращается адрес страницы оплаты
        }

        SubscriptionPeriod subscriptionPeriod = SubscriptionPeriod.valueOf(req.getParameter("subscriptionType")); // Извлечение типа подписки из параметров запроса и преобразование в перечисление SubscriptionPeriod
        HttpSession session = req.getSession();
        log.trace("subscription period ==> " + subscriptionPeriod);

        session.setAttribute("subscriptionPeriod", subscriptionPeriod); // Сохранение выбранного типа подписки в сессии

        log.debug("PaymentCommand finished");
        return Path.COMMAND_PAYMENT; // Возвращение адреса команды оплаты
    }
}
