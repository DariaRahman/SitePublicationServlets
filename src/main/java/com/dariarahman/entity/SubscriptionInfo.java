package com.dariarahman.entity;

import java.io.Serializable;
import java.util.Objects;

// Этот класс предоставляет модель данных информации о подписках и
// содержит необходимые методы для доступа к этим данным
public class SubscriptionInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String periodicalName;
    private String periodicalType;
    private String frequency;
    private String startDate;
    private String endDate;

    public SubscriptionInfo(String periodicalName, String periodicalType, String frequency, String startDate, String endDate) {
        this.periodicalName = periodicalName;
        this.periodicalType = periodicalType;
        this.frequency = frequency;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public SubscriptionInfo() {
    }

    public String getPeriodicalName() {
        return periodicalName;
    }

    public void setPeriodicalName(String periodicalName) {
        this.periodicalName = periodicalName;
    }

    public String getPeriodicalType() {
        return periodicalType;
    }

    public void setPeriodicalType(String periodicalType) {
        this.periodicalType = periodicalType;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionInfo that = (SubscriptionInfo) o;
        return Objects.equals(periodicalName, that.periodicalName) && Objects.equals(periodicalType, that.periodicalType) && Objects.equals(frequency, that.frequency) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(periodicalName, periodicalType, frequency, startDate, endDate);
    }

    @Override
    public String toString() {
        return "SubscriptionInfo{" +
                "periodicalName='" + periodicalName + '\'' +
                ", periodicalType='" + periodicalType + '\'' +
                ", frequency='" + frequency + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
