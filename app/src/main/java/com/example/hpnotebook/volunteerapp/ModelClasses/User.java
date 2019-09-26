package com.example.hpnotebook.volunteerapp.ModelClasses;

import java.util.ArrayList;

public class User {

    private String name, uid, email, contact, location, gender, type, pass, description, imageURL, userEvents, achievedEvents;

    public User(String name, String uid, String email, String pass, String contact, String location, String gender,
                String type, String description, String imageURL) {
        this.name = name;
        this.uid = uid;
        this.email = email;
        this.pass = pass;
        this.contact = contact;
        this.location = location;
        this.gender = gender;
        this.type = type;
        this.description = description;
        this.imageURL = imageURL;
    }

    public User(String name, String uid, String email, String pass, String contact, String location,
                String type, String description, String imageURL) {
        this.name = name;
        this.uid = uid;
        this.email = email;
        this.pass = pass;
        this.contact = contact;
        this.location = location;
        this.type = type;
        this.description = description;
        this.imageURL = imageURL;
    }

    public User(String name, String uid, String email, String type) {
        this.name = name;
        this.uid = uid;
        this.email = email;
        this.type = type;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserEvents() {
        return userEvents;
    }

    public void setUserEvents(String userEvents) {
        this.userEvents = userEvents;
    }

    public String getAchievedEvents() {
        return achievedEvents;
    }

    public void setAchievedEvents(String achievedEvents) {
        this.achievedEvents = achievedEvents;
    }
}
