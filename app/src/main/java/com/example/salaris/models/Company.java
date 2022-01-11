package com.example.salaris.models;

import com.example.salaris.models.User;

import java.io.Serializable;

public class Company implements Serializable {
    private Integer id, ceoId, addressId;
    private String name, address, city, postNo, taxNo, about;
    private User CEO;

    public Company(String name, String address, String city, String postNo, String taxNo, String about, User CEO) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.postNo = postNo;
        this.taxNo = taxNo;
        this.about = about;
        this.CEO = CEO;
    }

    public Company(Integer id, String name, String taxNo, String about, Integer ceoId, Integer addressId) {
        this.id = id;
        this.name = name;
        this.taxNo = taxNo;
        this.about = about;
        this.ceoId = ceoId;
        this.addressId = addressId;
    }

    public Integer getID() {
        return id;
    }

    public void setID(Integer id) {
        this.id = id;
    }

    public String toString() {
        return this.name + " (" + this.taxNo + ")";
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public User getCEO() {
        return CEO;
    }

    public void setCEO(User CEO) {
        this.CEO = CEO;
    }
}
