package com.example.salaris.models;

public class User_Company {
    private User user;
    private Company company;
    private Role role;
    private Double hourlyRate;

    User_Company(User user, Company company, Role role, double hourlyRate) {
        this.user = user;
        this.company = company;
        this.role = role;
        this.hourlyRate = hourlyRate;
    }

    public Role getRole() {
        return role;
    }

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
