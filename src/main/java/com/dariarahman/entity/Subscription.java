package com.dariarahman.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

// Этот класс предоставляет модель данных для подписок и
// содержит необходимые методы для доступа к этим данным
public class Subscription implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private boolean status;
    private long userId;
    private long periodicalId;
    private SubscriptionPeriod period;
    private Timestamp startDate;
    private Timestamp endDate;

    public Subscription(long id, boolean status, long userId, long periodicalId, SubscriptionPeriod period, Timestamp startDate, Timestamp endDate) {
        this.id = id;
        this.status = status;
        this.userId = userId;
        this.periodicalId = periodicalId;
        this.period = period;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Subscription() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getPeriodicalId() {
        return periodicalId;
    }

    public void setPeriodicalId(long periodicalId) {
        this.periodicalId = periodicalId;
    }

    public SubscriptionPeriod getPeriod() {
        return period;
    }

    public void setPeriod(SubscriptionPeriod period) {
        this.period = period;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscription that = (Subscription) o;
        return id == that.id && status == that.status && userId == that.userId && periodicalId == that.periodicalId && period == that.period && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, userId, periodicalId, period, startDate, endDate);
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", status=" + status +
                ", userId=" + userId +
                ", periodicalId=" + periodicalId +
                ", period=" + period +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
