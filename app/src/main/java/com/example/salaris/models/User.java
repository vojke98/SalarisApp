package com.example.salaris.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class User implements Serializable {
    private String firstName, lastName, address, city, postNo, taxNo, email, password;
    private Role role;
    private double hourlyRate;
    private Date dateJoined;
    private ArrayList<Workhour> workhours = new ArrayList();

    public User(String firstName, String lastName, String address, String city, String postNo, String taxNo, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.postNo = postNo;
        this.taxNo = taxNo;
        this.email = email;
        this.password = password;
    }

    public String toString() {
        return this.lastName + ", " + this.firstName + " (" + this.taxNo + ")";
    }

    public Date getDateJoined() { return dateJoined; }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void addWorkhour(Workhour workhour) {
        this.workhours.add(workhour);
    }

    public void removeWorkhour(Workhour workhour) {
        this.workhours.remove(workhour);
    }

    public ArrayList<Workhour> getWorkhours() {
        return this.workhours;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostNo() {
        return postNo;
    }

    public void setPostNo(String postNo) {
        this.postNo = postNo;
    }

    public String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
