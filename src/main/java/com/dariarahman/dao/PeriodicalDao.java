package com.dariarahman.dao;

import com.dariarahman.exceptions.DBException;
import com.dariarahman.entity.Periodical;

import java.util.List;

// Этот интерфейс служит контрактом для классов,
// реализующих доступ к базе данных и операции с периодическими изданиями
public interface PeriodicalDao {
    // Получение списка всех периодических изданий
    List<Periodical> findAllPeriodicals() throws DBException;

    // Поиск периодических изданий по имени
    List<Periodical> findPeriodicalsByName(String name) throws DBException;

    // Поиск периодического издания по идентификатору
    Periodical findPeriodicalById(long id) throws DBException;

    // Добавление периодического издания в базу данных
    boolean addPeriodical(Periodical periodical) throws DBException;

    // Обновление информации о периодическом издании
    boolean updatePeriodical(Periodical periodical) throws DBException;

    // Удаление периодического издания по идентификатору
    boolean deletePeriodicalById(long periodicalId) throws DBException;
}
