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

    public long getSeconds() {
        return (this.from.getTime() - this.until.getTime()) / 1000;
    }

    public long getMinutes() {
        return (this.getSeconds() % 3600) / 60;
    }

    public long getHours() {
        return this.getSeconds() / 3600;
    }
}
