package com.example.salaris.helper;

import java.util.Date;

public class Period {
    private Date from, until;

    public Period(Date from, Date until) {
        this.from = from;
        this.until = until;
    }

    public long getMills() {
        return this.until.getTime() - this.from.getTime();
    }

    public long getSeconds() {
        return getMills() / 1000;
    }

    public long getMinutes() {
        return this.getSeconds() / 60;
    }

    public long getHours() {
        return this.getMinutes() / 60;
    }
}
