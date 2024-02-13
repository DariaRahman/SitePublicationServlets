package com.dariarahman.dao;

import com.dariarahman.dao.impl.mysql.MySqlPaymentDao;
import com.dariarahman.dao.impl.mysql.MySqlPeriodicalDao;
import com.dariarahman.dao.impl.mysql.MySqlSubscriptionDao;
import com.dariarahman.dao.impl.mysql.MySqlUserDao;

// В данном коде определяется фабрика (DaoFactory) для создания экземпляров
// объектов доступа к данным (DAO) для различных сущностей в системе
public class DaoFactory {

    public static PaymentDao createPaymentDao() {
        // Возвращаем экземпляр MySqlPaymentDao
        return new MySqlPaymentDao();
    }

    public static PeriodicalDao createPeriodicalDao() {
        // Возвращаем экземпляр MySqlPeriodicalDao
        return new MySqlPeriodicalDao();
    }

    public static SubscriptionDao createSubscriptionDao() {
        // Возвращаем экземпляр MySqlSubscriptionDao
        return new MySqlSubscriptionDao();
    }

    public static UserDao createUserDao() {
        // Возвращаем экземпляр MySqlUserDao
        return new MySqlUserDao();
    }
}
