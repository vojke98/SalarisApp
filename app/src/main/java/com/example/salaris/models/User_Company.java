package com.example.salaris.models;

import java.io.Serializable;

public class User_Company implements Serializable {
    private User user;
    private Company company;
    private Role role;
    private Double hourlyRate;

    public User_Company(User user, Company company, Role role, double hourlyRate) {
        this.user = user;
        this.company = company;
        this.role = role;
        this.hourlyRate = hourlyRate;
    }

    public String toString() {
        return this.user + " at " + this.company + " as " + this.role + " for " + this.hourlyRate + "€/h";
    }

    public Role getRole() {
        return role;
    }

    public User getUser() { return user; }

    public void setRole(Role role) {
        this.role = role;
    }

    public Double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(Double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }
}
