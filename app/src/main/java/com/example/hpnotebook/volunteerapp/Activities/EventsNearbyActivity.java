package com.example.hpnotebook.volunteerapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.hpnotebook.volunteerapp.R;

import java.util.Objects;

public class EventsNearbyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_nearby);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Events Nearby");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id)
        {
            case R.id.profile:
                startActivity(new Intent(this, UserProfileActivity.class));
                return true;

            case R.id.dashboard:
                startActivity(new Intent(this, DashboardActivity.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
