package com.example.hpnotebook.volunteerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Objects;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Dashboard");

    }

}
