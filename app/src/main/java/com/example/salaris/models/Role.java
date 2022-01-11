package com.example.salaris.models;

import java.io.Serializable;

public class Role implements Serializable {
    public static String CO = "CO";
    public static String MANAGER = "Manager";
    public static String WORKER = "Worker";

    private Integer id;
    private String title;
    private Double minHourlyRate;

    public Role(String title, Double minHourlyRate) {
        this.title = title;
        this.minHourlyRate = minHourlyRate;
    }

    public Integer getID() {
        return id;
    }

    public void setID(Integer id) {
        this.id = id;
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
