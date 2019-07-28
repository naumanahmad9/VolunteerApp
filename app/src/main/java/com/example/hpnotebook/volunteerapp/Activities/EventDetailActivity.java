package com.example.hpnotebook.volunteerapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hpnotebook.volunteerapp.R;

import java.util.Objects;

public class EventDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Event Detail");

    }
}
