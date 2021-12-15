package com.example.salaris.models;

import com.example.salaris.helper.Period;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Workhour {
    private User user;
    private Company company;
    private Date from, until;
    double hourlyRateAtTheTime;
    SimpleDateFormat formatter;

    public Workhour(User user, Company company, Date from, Date until, double hourlyRateAtTheTime) {
        this.user = user;
        this.company = company;
        this.from = from;
        this.until = until;
        this.hourlyRateAtTheTime = hourlyRateAtTheTime;
    }

    public double getTotal() {
        Period p = new Period(this.from, this.until);

        double hours = p.getHours();

        return hours * hourlyRateAtTheTime;
    }

    public double getHours() {
        Period p = new Period(this.from, this.until);

        return p.getHours();
    }

    public String getDayOfWeekString() {
        formatter = new SimpleDateFormat("E");
        return formatter.format(from);
    }

    public String getDateString() {
        formatter = new SimpleDateFormat("d MMMM yyyy");
        return formatter.format(from);
    }

    public String getTimeFromString() {
        formatter = new SimpleDateFormat("HH:mm");
        return formatter.format(from);
    }

    public String getTimeUntilString() {
        formatter = new SimpleDateFormat("HH:mm");
        return formatter.format(until);
    }

    public double getHourlyRateAtTheTime() {
        return hourlyRateAtTheTime;
    }
}
