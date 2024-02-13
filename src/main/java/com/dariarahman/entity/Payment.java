package com.dariarahman.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

// Этот класс предоставляет модель данных для платежей и
// содержит необходимые методы для доступа к этим данным
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private long userId;
    private long subscriptionId;
    private Timestamp paymentDateTime;
    private BigDecimal totalPrice;

    public Payment(long id, long userId, long subscriptionId, Timestamp paymentDateTime, BigDecimal totalPrice) {
        this.id = id;
        this.userId = userId;
        this.subscriptionId = subscriptionId;
        this.paymentDateTime = paymentDateTime;
        this.totalPrice = totalPrice;
    }

    public Payment() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Timestamp getPaymentDateTime() {
        return paymentDateTime;
    }

    public void setPaymentDateTime(Timestamp paymentDateTime) {
        this.paymentDateTime = paymentDateTime;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return id == payment.id && userId == payment.userId && subscriptionId == payment.subscriptionId && Objects.equals(paymentDateTime, payment.paymentDateTime) && Objects.equals(totalPrice, payment.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, subscriptionId, paymentDateTime, totalPrice);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", userId=" + userId +
                ", subscriptionId=" + subscriptionId +
                ", paymentDateTime=" + paymentDateTime +
                ", totalPrice=" + totalPrice +
                '}';
    }
}


