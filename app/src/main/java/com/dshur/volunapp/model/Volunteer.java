package com.dshur.volunapp.model;

public class Volunteer {
    public String fullName;
    public String email;
    public String phone;
    public String password;

    boolean isAuthenticated;

    boolean isCreated, isNew;

    public Volunteer(){}

    public Volunteer(String fullName, String email, String phone, String password) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }
}
