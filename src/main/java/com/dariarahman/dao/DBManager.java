package com.dariarahman.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

//В данном коде представлен класс DBManager,
// который является синглтоном (singleton) и обеспечивает управление подключением к базе данных.
//Класс DBManager использует пул соединений (connection pool) для получения и управления соединениями с базой данных.
// Пул соединений позволяет повторно использовать уже созданные соединения,
// вместо создания нового соединения каждый раз при запросе.
public class DBManager {

    private static final Logger log = LogManager.getLogger(DBManager.class);

    private DataSource ds;

    private static DBManager instance;

    public static synchronized DBManager getInstance() { // метод синхронизированного доступа к экземпляру класса DBManager
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    private DBManager() { // приватный конструктор класса, который инициализирует объект
        try {
            // Получение начального контекста
            Context initContext = new InitialContext();
            // Получение контекста окружения
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            // Получение источника данных (DataSource) с именем "jdbc/TestDB"
            ds = (DataSource) envContext.lookup("jdbc/TestDB");
        } catch (NamingException ex) {
            log.error("Cannot init DBManager");
            throw new IllegalStateException("Cannot init DBManager");
        }
    }

    public Connection getConnection() throws SQLException { // метод для получения соединения с базой данных из пула соединений
        // Получение соединения из пула соединений, предоставляемого источником данных
        return ds.getConnection();
    }

    public void close(AutoCloseable ac) { // метод для закрытия ресурсов
        if (ac != null) {
            try {
                ac.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void rollback(Connection con) { // метод для отката транзакции в случае необходимости
        if (con != null) {
            try {
                // Откат транзакции в случае необходимости
                con.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}