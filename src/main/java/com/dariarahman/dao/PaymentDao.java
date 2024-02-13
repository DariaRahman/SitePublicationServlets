package com.dariarahman.dao;

import com.dariarahman.exceptions.DBException;
import com.dariarahman.entity.Payment;
import com.dariarahman.entity.Subscription;

// Этот интерфейс служит контрактом для классов,
// реализующих доступ к базе данных и операции с платежами
public interface PaymentDao {
    // Добавление платежа в базу данных
    boolean addPayment(Payment payment) throws DBException;

    // Добавление платежа по подписке в базу данных
    boolean addPaymentBySubscription(Payment payment, Subscription subscription) throws DBException;

    // Создание платежа с указанием подписки и добавление в базу данных
    boolean createPayment(Payment payment, Subscription subscription) throws DBException;
}
