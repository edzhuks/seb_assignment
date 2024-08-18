package com.edgars.currency_calculator;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;

@Entity
@IdClass(RateId.class)
public class Rate {

    @Id
    private Date date;
    @Id
    private String name;
    private float rate;

    Rate() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "ExchangeRate{" + ", name='" + this.name + '\'' + ", value='" + this.rate + '\'' + " date= " + this.date
                + '}';
    }
}
