package com.example.salaris.models;

import java.io.Serializable;

public class Role implements Serializable {
    public static String CO = "CO";
    public static String MANAGER = "Manager";
    public static String WORKER = "Worker";

    String title;
    Double minHourlyRate;

    public Role(String title, Double minHourlyRate) {
        this.title = title;
        this.minHourlyRate = minHourlyRate;
    }

    public String toString() {
        return this.title + " (min. " + this.minHourlyRate + "€/h)";
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
