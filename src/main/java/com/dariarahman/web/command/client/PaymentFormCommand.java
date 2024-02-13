package com.dariarahman.web.command.client;

import com.dariarahman.exceptions.DBException;
import com.dariarahman.dao.DaoFactory;
import com.dariarahman.entity.Payment;
import com.dariarahman.entity.Periodical;
import com.dariarahman.entity.Subscription;
import com.dariarahman.entity.SubscriptionPeriod;
import com.dariarahman.entity.User;
import com.dariarahman.web.Path;
import com.dariarahman.web.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;

// Класс PaymentFormCommand реализует интерфейс Command и используется для обработки формы оплаты
public class PaymentFormCommand implements Command {

    private static final Logger log = LogManager.getLogger(PaymentFormCommand.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        log.debug("PaymentFormCommand starts");

        // Получаем текущий сеанс
        HttpSession session = req.getSession();
        // Извлекаем объекты из сеанса
        Periodical periodical = (Periodical) session.getAttribute("periodical");
        User user = (User) session.getAttribute("user");
        SubscriptionPeriod subscriptionPeriod = (SubscriptionPeriod) session.getAttribute("subscriptionPeriod");

        // Получаем текущую дату и время
        Calendar currentDate = Calendar.getInstance();

        // Создаем объекты Subscription и Payment
        Subscription subscription = createSubscription(user.getId(), periodical.getId(), subscriptionPeriod, currentDate);
        Payment payment = createPayment(user.getId(), currentDate, periodical.getPrice(), subscriptionPeriod.getNumber());

        // Проверяем достаточность средств на балансе пользователя
        if (!checkBalance(user.getBalance(), payment.getTotalPrice())) {
            log.debug("not enough money on the balance, forward to top up balance");
            return Path.PAGE_TOP_UP_BALANCE;
        }

        // Сохраняем платеж и подписку в базе данных
        DaoFactory.createPaymentDao().createPayment(payment, subscription);

        // Обновляем баланс пользователя и сохраняем обновленный объект в сеансе
        updateBalance(user, payment.getTotalPrice());
        session.setAttribute("user", user);

        log.debug("PaymentFormCommand finished");
        return Path.PAGE_CLIENT;
    }

    // Метод для создания объекта Subscription
    private Subscription createSubscription(long userId, long periodicalId, SubscriptionPeriod subscriptionPeriod, Calendar currentDate) {
        Subscription subscription = new Subscription();

        // Устанавливаем дату начала подписки
        Timestamp startDate = new Timestamp(currentDate.getTime().getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.add(Calendar.MONTH, subscriptionPeriod.getNumber());
        Timestamp endDate = new Timestamp(calendar.getTime().getTime());

        // Заполняем поля объекта Subscription
        subscription.setStatus(true);
        subscription.setUserId(userId);
        subscription.setPeriodicalId(periodicalId);
        subscription.setPeriod(subscriptionPeriod);
        subscription.setStartDate(startDate);
        subscription.setEndDate(endDate);

        return subscription;
    }

    private Payment createPayment(long userId, Calendar currentDate, BigDecimal price, int numberOfMonths) {
        Payment payment = new Payment();

        // Создаем объект Timestamp с текущей датой и временем
        Timestamp paymentDate = new Timestamp(currentDate.getTime().getTime());

        // Заполняем поля объекта Payment
        payment.setUserId(userId);
        payment.setPaymentDateTime(paymentDate);
        payment.setTotalPrice(price.multiply(new BigDecimal(numberOfMonths)));

        return payment;
    }

    private void updateBalance(User user, BigDecimal totalPrice) throws DBException {
        BigDecimal resultBalance = user.getBalance().subtract(totalPrice);
        user.setBalance(resultBalance);
        DaoFactory.createUserDao().updateUser(user);
    }

    private boolean checkBalance(BigDecimal userBalance, BigDecimal totalPrice) {
        return userBalance.compareTo(totalPrice) >= 0;
    }
}
