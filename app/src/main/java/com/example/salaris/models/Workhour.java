package com.example.salaris.models;

import com.example.salaris.helper.Period;

import java.util.Date;

public class Workhour {
    private User_Company user_company;
    private Date from, until;

    Workhour(User_Company user_company, Date from, Date until) {
        this.user_company = user_company;
        this.from = from;
        this.until = until;
    }

    public double getTotal() {
        double hourlyRate = user_company.getHourlyRate();
        Period p = new Period(this.from, this.until);

        int hours = (int) p.getHours();

        return hours * hourlyRate;
    }
}
