package com.example.salaris.models;

public class Role {
    public static String CO = "CO";
    public static String MANAGER = "Manager";
    public static String WORKER = "Worker";

    String title;
    Double minHourlyRate;

    Role(String title, Double minHourlyRate) {
        this.title = title;
        this.minHourlyRate = minHourlyRate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getMinHourlyRate() {
        return minHourlyRate;
    }

    public void setMinHourlyRate(Double minHourlyRate) {
        this.minHourlyRate = minHourlyRate;
    }
}
