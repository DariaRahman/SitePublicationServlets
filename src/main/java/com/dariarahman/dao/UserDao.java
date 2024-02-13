package com.dariarahman.dao;

import com.dariarahman.exceptions.DBException;
import com.dariarahman.entity.User;

import java.util.List;

// Интерфейс UserDao определяет методы для работы с пользователями в базе данных
public interface UserDao {
    // Поиск пользователя по идентификатору
    User findUserByID(long id) throws DBException;

    // Поиск пользователя по адресу электронной почты
    User findUserByEmail(String email) throws DBException;

    // Получение списка всех пользователей
    List<User> findAllUsers() throws DBException;

    // Добавление пользователя в базу данных
    boolean addUser(User user) throws DBException;

    // Обновление информации о пользователе
    boolean updateUser(User user) throws DBException;

    // Обновление информации о пользователе без обновления баланса
    boolean updateUserWithoutBalance(User user) throws DBException;
}
