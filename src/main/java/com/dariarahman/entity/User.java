package com.dariarahman.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

// Этот класс предоставляет модель данных для пользователей и
// содержит необходимые методы для доступа к этим данным
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private int roleId;
    private String email;
    private String firstName;
    private String lastName;
    private String password;
    private Gender gender;
    private boolean status;
    private BigDecimal balance;

    public User(long id, int roleId, String email, String firstName, String lastName, String password, Gender gender, boolean status, BigDecimal balance) {
        this.id = id;
        this.roleId = roleId;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.gender = gender;
        this.status = status;
        this.balance = balance;
    }

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender.toString();
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && roleId == user.roleId && status == user.status && Objects.equals(email, user.email) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(password, user.password) && gender == user.gender && Objects.equals(balance, user.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleId, email, firstName, lastName, password, gender, status, balance);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                ", status=" + status +
                ", balance=" + balance +
                '}';
    }
}
