package com.edgars.currency_calculator;

import java.io.Serializable;
import java.sql.Date;

public class RateId implements Serializable {
    private Date date;
    private String name;

    public RateId() {
    }

    public RateId(Date date, String name) {
        this.date = date;
        this.name = name;
    }
}
