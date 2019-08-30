package com.example.hpnotebook.volunteerapp.ModelClasses;

public class User {

    private String name, uid, email, contact, gender, type, pass, imageURL;

    public User(String name, String uid, String email, String pass, String contact, String gender, String type, String imageURL) {
        this.name = name;
        this.uid = uid;
        this.email = email;
        this.pass = pass;
        this.contact = contact;
        this.gender = gender;
        this.type = type;
        this.imageURL = imageURL;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
