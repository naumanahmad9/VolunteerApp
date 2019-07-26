package com.example.hpnotebook.volunteerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SearchEventsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_events);

        getSupportActionBar().setTitle("Search Events");

    }
}
