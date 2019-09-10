package com.example.hpnotebook.volunteerapp.Activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hpnotebook.volunteerapp.ModelClasses.Event;
import com.example.hpnotebook.volunteerapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class EventDetailActivity extends AppCompatActivity {

    boolean check = false;

    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference eventRef, userRef, eventUserIdRef;

    ImageView event_detail_image;
    TextView event_detail_title, event_detail_location, event_detail_date, event_detail_time,
            event_detail_stipend, event_detail_category, event_detail_org, event_detail_language,
            event_detail_dress, event_detail_refreshments, tv_comment_count,tv_view_comments,
            tv_add_comment;

    Event event;
    String eventid, event_userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Event Detail");

        init();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            eventid = bundle.getString("eventid");
        }

        eventRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                event = dataSnapshot.getValue(Event.class);

                Glide.with(getApplicationContext()).load(event.getEvent_image()).into(event_detail_image);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void init() {
        event_detail_image = findViewById(R.id.event_detail_image);
        event_detail_title = findViewById(R.id.event_detail_title);
        event_detail_location = findViewById(R.id.event_detail_location);
        event_detail_date = findViewById(R.id.event_detail_date);
        event_detail_time = findViewById(R.id.event_detail_time);
        event_detail_stipend = findViewById(R.id.event_detail_stipend);
        event_detail_category = findViewById(R.id.event_detail_category);
        event_detail_org = findViewById(R.id.event_detail_org);
        event_detail_language = findViewById(R.id.event_detail_language);
        event_detail_dress = findViewById(R.id.event_detail_dress);
        event_detail_refreshments = findViewById(R.id.event_detail_refreshments);
        tv_comment_count = findViewById(R.id.tv_comment_count);
        tv_view_comments = findViewById(R.id.tv_view_comments);
        tv_add_comment = findViewById(R.id.tv_add_comment);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        database = FirebaseDatabase.getInstance();
        eventRef = database.getReference("events").child(eventid);
        userRef = database.getReference("users").child(auth.getCurrentUser().getUid());

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