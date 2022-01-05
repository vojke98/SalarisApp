package com.example.salaris.models;

import com.example.salaris.helper.Period;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Workhour {
    private User_Company user_company;
    private Date from, until;

    public Workhour(User_Company user_company, Date from, Date until) {
        this.user_company = user_company;
        this.from = from;
        this.until = until;
    }

    public String toString() {
        return this.user_company + ", from: " + this.from + " - " + this.until;
    }

    public double getTotalEarned() {
        double hourlyRate = user_company.getHourlyRate();

        return this.getTotalHours() * hourlyRate;
    }

    public double getTotalHours() {
        Period p = new Period(this.from, this.until);

        return Math.abs(p.getHours());
    }

    public void setUser_company(User_Company user_company) { this.user_company = user_company; }

    public String getDateString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("DD.MM.YYYY");
        return simpleDateFormat.format(this.from);
    }

    public String getDayOfWeekString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE");
        return simpleDateFormat.format(this.from);
    }

    public String getTimeFromString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        return simpleDateFormat.format(this.from);
    }

    public String getTimeUntilString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        return simpleDateFormat.format(this.until);
    }
}
