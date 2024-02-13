package com.dariarahman.entity;

// Перечисление Role определяет две роли пользователя (администратор и клиент)
// и предоставляет методы для получения роли на основе объекта пользователя и получения имени роли в виде строки
public enum Role {
    ADMIN, CLIENT;

    public static Role getRole(User user) {
        int roleId = user.getRoleId();
        return Role.values()[roleId];
    }

    public String getName() {
        return name().toLowerCase();
    }
}
