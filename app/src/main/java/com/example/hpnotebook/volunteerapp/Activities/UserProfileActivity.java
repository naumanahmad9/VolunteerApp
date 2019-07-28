package com.example.hpnotebook.volunteerapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.hpnotebook.volunteerapp.R;

import java.util.Objects;

public class UserProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Profile");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id)
        {
            case R.id.dashboard:
                startActivity(new Intent(this, DashboardActivity.class));
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                return true;

            case R.id.search:
                startActivity(new Intent(this, SearchEventsActivity.class));
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
