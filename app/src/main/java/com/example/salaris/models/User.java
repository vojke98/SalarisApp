package com.example.salaris.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class User implements Serializable {
    private Integer id;
    private String firstName, lastName, address, city, postNo, taxNo, email, password, qualifications, about;
    private Role role;
    private double hourlyRate;
    private Date dateJoined;
    private ArrayList<Workhour> workhours = new ArrayList();
    private Company company;

    private Integer companyId, roleId, addressId;

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

    public User(Integer id, String firstName, String lastName, String taxNo, String email, String password, Integer companyId, Integer roleId, Integer addressId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.taxNo = taxNo;
        this.email = email;
        this.password = password;
        this.companyId = companyId;
        this.roleId = roleId;
        this.addressId = addressId;
    }

    public Integer getID() {
        return id;
    }

    public void setID(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() { return companyId; }

    public Company getCompany() { return company; }

    public void setCompany(Company company) { this.company = company; }

    public String toString() { return this.lastName + ", " + this.firstName + " (" + this.taxNo + ")";  }

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

    public String getFullName() { return lastName + ", " + firstName; }

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

    public JSONObject toJSON() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("tax_no", this.taxNo);
            obj.put("first_name", this.firstName);
            obj.put("last_name", this.lastName);
            obj.put("email", this.email);
            obj.put("qualifications", this.qualifications);
            obj.put("user_about_info", this.about);
            obj.put("password", this.password);
            obj.put("address", this.address);
            obj.put("company", this.company.getID());
            obj.put("role", this.role.getID());
        } catch (JSONException e) { e.printStackTrace(); }

        return obj;
    }
}
