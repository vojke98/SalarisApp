package com.example.salaris.models;

import java.util.Date;

public class JoinRequest {

    private User user;
    private Company company;
    private Date date;

    public JoinRequest(User user, Company company, Date date) {
        this.user = user;
        this.company = company;
        this.date = date;
    }

    public String toString() {
        return this.user + " sent join request to " + this.company + " on " + this.date.toString();
    }
}
