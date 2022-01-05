package com.example.salaris.helper;

import java.util.Date;

public class Period {
    private Date from, until;

    public Period(Date from, Date until) {
        this.from = from;
        this.until = until;
    }

    public long getMills() {
        return this.from.getTime() - this.until.getTime();
    }

    public double getSeconds() { return this.getMills() / 1000.0; }

    public double getMinutes() { return this.getSeconds() / 60; }

    public double getHours() {
        return this.getMinutes() / 60;
    }
}
