package com.dariarahman.exceptions;

// класс DBException представляет пользовательское исключение,
// которое может быть брошено в случае ошибки взаимодействия с базой данных
// и позволяет передавать информацию об ошибке и причине исключения.
public class DBException extends Exception {
    public DBException(String message, Throwable cause) {
        super(message, cause);
    }

    public DBException(String message) {
        super(message);
    }
}
