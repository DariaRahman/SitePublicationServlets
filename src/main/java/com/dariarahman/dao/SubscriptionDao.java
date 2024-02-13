package com.dariarahman.dao;

import com.dariarahman.exceptions.DBException;
import com.dariarahman.entity.Subscription;
import com.dariarahman.entity.SubscriptionInfo;

import java.util.List;

// Интерфейс SubscriptionDao определяет методы
// для работы с подписками на периодические издания в базе данных
public interface SubscriptionDao {
    // Добавление подписки в базу данных
    boolean addSubscription(Subscription subscription) throws DBException;

    // Поиск подписок по идентификатору пользователя
    List<Subscription> findSubscriptionsByUserId(long userId) throws DBException;

    // Получение информации о подписках пользователя
    List<SubscriptionInfo> getSubscriptionsInfo(long userId) throws DBException;

    // Проверка, подписан ли пользователь на определенное периодическое издание
    boolean isSubscribed(long userId, long periodicalId) throws DBException;
}
