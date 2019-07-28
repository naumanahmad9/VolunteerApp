package com.example.hpnotebook.volunteerapp.Activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.hpnotebook.volunteerapp.R;

import java.util.Objects;

public class EventDetailActivity extends AppCompatActivity {

    boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Event Detail");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fav, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.fav:

                if (check) {
                    item.setIcon(R.drawable.ic_favorite_border_black);
                    item.setTitle("add to fav");
                    check = false;
                } else {
                    item.setIcon(R.drawable.ic_favorite_black);
                    item.setTitle("fav event");
                    check = true;
                }
                return true;

        }
        return true;
    }
}