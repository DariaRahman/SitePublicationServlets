package com.dariarahman.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

// Этот класс предоставляет модель данных для периодических изданий и
// содержит необходимые методы для доступа к этим данным
public class Periodical implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String name;
    private String publisher;
    private String frequency;
    private String type;
    private BigDecimal price;
    private String description;

    public Periodical(long id, String name, String publisher, String frequency, String type, BigDecimal price, String description) {
        this.id = id;
        this.name = name;
        this.publisher = publisher;
        this.frequency = frequency;
        this.type = type;
        this.price = price;
        this.description = description;
    }

    public Periodical() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Periodical that = (Periodical) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(publisher, that.publisher) && Objects.equals(frequency, that.frequency) && Objects.equals(type, that.type) && Objects.equals(price, that.price) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, publisher, frequency, type, price, description);
    }

    @Override
    public String toString() {
        return "Periodical{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", publisher='" + publisher + '\'' +
                ", frequency='" + frequency + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}