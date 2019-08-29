package com.example.hpnotebook.volunteerapp.ModelClasses;

public class User {

    private String name, uid, email, pass, imageURL, status;

    public User(String name, String uid, String email, String pass) {
        this.name = name;
        this.uid = uid;
        this.email = email;
        this.pass = pass;
    }

    public User() {
    }

    public User(String name, String uid, String email, String pass, String imageURL, String status) {
        this.name = name;
        this.uid = uid;
        this.email = email;
        this.pass = pass;
        this.imageURL = imageURL;
        this.status = status;
    }

    public User(String name, String uid, String email, String pass, String imageURL) {
        this.name = name;
        this.uid = uid;
        this.email = email;
        this.pass = pass;
        this.imageURL = imageURL;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
