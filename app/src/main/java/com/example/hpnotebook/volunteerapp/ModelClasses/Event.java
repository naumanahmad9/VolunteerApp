package com.example.hpnotebook.volunteerapp.ModelClasses;

import java.util.ArrayList;

public class Event {
    private String event_id, event_title, event_userId, event_description,
            event_date, event_time, event_location, event_category, event_stipend,
            event_refreshments, event_dresscode, event_language, event_image;
    private int event__viewcount;
    private ArrayList<User> event_users;

    public Event(String event_id, String event_title, String event_userId,
                 String event_description, String event_date, String event_time,
                 String event_location, String event_category, String event_stipend,
                 String event_refreshments, String event_dresscode, String event_language,
                 String event_image, ArrayList<User> event_users) {

        this.event_id = event_id;
        this.event_title = event_title;
        this.event_userId = event_userId;
        this.event_description = event_description;
        this.event_date = event_date;
        this.event_time = event_time;
        this.event_location = event_location;
        this.event_category = event_category;
        this.event_stipend = event_stipend;
        this.event_refreshments = event_refreshments;
        this.event_dresscode = event_dresscode;
        this.event_language = event_language;
        this.event_image = event_image;
        this.event_users = event_users;
    }

    public Event(String event_id, String event_title, String event_date, String event_time,
                 String event_location, String event_image) {

        this.event_id = event_id;
        this.event_title = event_title;
        this.event_date = event_date;
        this.event_time = event_time;
        this.event_location = event_location;
        this.event_image = event_image;
    }


    public Event() {}

    public String getEvent_id() {
        return event_id;
    }

    public void setEvent_id(String event_id) {
        this.event_id = event_id;
    }

    public String getEvent_title() {
        return event_title;
    }

    public void setEvent_title(String event_title) {
        this.event_title = event_title;
    }

    public String getEvent_userId() {
        return event_userId;
    }

    public void setEvent_userId(String event_userId) {
        this.event_userId = event_userId;
    }

    public String getEvent_description() {
        return event_description;
    }

    public void setEvent_description(String event_description) {
        this.event_description = event_description;
    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public String getEvent_time() {
        return event_time;
    }

    public void setEvent_time(String event_time) {
        this.event_time = event_time;
    }

    public String getEvent_location() {
        return event_location;
    }

    public void setEvent_location(String event_location) {
        this.event_location = event_location;
    }

    public String getEvent_category() {
        return event_category;
    }

    public void setEvent_category(String event_category) {
        this.event_category = event_category;
    }

    public String getEvent_stipend() {
        return event_stipend;
    }

    public void setEvent_stipend(String event_stipend) {
        this.event_stipend = event_stipend;
    }

    public String getEvent_refreshments() {
        return event_refreshments;
    }

    public void setEvent_refreshments(String event_refreshments) {
        this.event_refreshments = event_refreshments;
    }

    public String getEvent_dresscode() {
        return event_dresscode;
    }

    public void setEvent_dresscode(String event_dresscode) {
        this.event_dresscode = event_dresscode;
    }

    public String getEvent_language() {
        return event_language;
    }

    public void setEvent_language(String event_language) {
        this.event_language = event_language;
    }

    public String getEvent_image() {
        return event_image;
    }

    public void setEvent_image(String event_image) {
        this.event_image = event_image;
    }

    public int getEvent__viewcount() {
        return event__viewcount;
    }

    public void setEvent__viewcount(int event__viewcount) {
        this.event__viewcount = event__viewcount;
    }

    public ArrayList<User> getEvent_users() {
        return event_users;
    }

    public void setEvent_users(ArrayList<User> event_users) {
        this.event_users = event_users;
    }
}
