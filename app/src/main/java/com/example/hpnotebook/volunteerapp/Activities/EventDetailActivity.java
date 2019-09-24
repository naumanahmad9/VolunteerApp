package com.example.hpnotebook.volunteerapp.Activities;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hpnotebook.volunteerapp.ModelClasses.Event;
import com.example.hpnotebook.volunteerapp.ModelClasses.User;
import com.example.hpnotebook.volunteerapp.R;
import com.example.hpnotebook.volunteerapp.ViewApplicantsAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class EventDetailActivity extends AppCompatActivity {

    boolean likeCheck = false;

    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference eventRef, userRef, eventUserIdRef, eventCommentsRef;

    ImageView event_detail_image;
    TextView event_detail_title, event_detail_location, event_detail_date, event_detail_time,
            event_detail_stipend, event_detail_category, event_detail_org, event_detail_language,
            event_detail_dress, event_detail_refreshments, tv_comment_count, tv_view_comments,
            tv_add_comment;
    Button button_apply, button_view_applicants;
    Event event;
    String eventid, event_org;
    User user;
    private boolean applyCheck = true;
    User applicant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Event Detail");

        init();

        Bundle bundle = getIntent().getExtras();
        //if (bundle != null) {
        eventid = bundle.getString("eventid");
        //}

        eventRef.child(eventid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                event = dataSnapshot.getValue(Event.class);

                Glide.with(getApplicationContext()).load(event.getEvent_image()).into(event_detail_image);

                event_detail_title.setText(event.getEvent_title());
                event_detail_location.setText(event.getEvent_location());
                event_detail_date.setText(event.getEvent_date());
                event_detail_time.setText(event.getEvent_time());
                event_detail_stipend.setText(event.getEvent_stipend());
                event_detail_category.setText(event.getEvent_category());
                event_detail_language.setText(event.getEvent_language());
                event_detail_dress.setText(event.getEvent_dresscode());
                event_detail_refreshments.setText(event.getEvent_refreshments());

                eventUserIdRef = database.getReference("users").child(event.getEvent_userId());

                eventUserIdRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        user = dataSnapshot.getValue(User.class);
                        //event_org = user.getName();
                        event_detail_org.setText(user.getName());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                event_detail_org.setText(event_org);

                if (event.getEvent_userId().equals(auth.getCurrentUser().getUid())) {
                    button_apply.setVisibility(View.GONE);
                    button_view_applicants.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        eventCommentsRef.child(eventid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tv_comment_count.setText(String.valueOf(dataSnapshot.getChildrenCount()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        tv_view_comments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventDetailActivity.this, CommentsActivity.class);
                intent.putExtra("eventid", eventid);
                startActivity(intent);
            }
        });

        tv_add_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventDetailActivity.this, CommentsActivity.class);
                intent.putExtra("eventid", eventid);
                intent.putExtra("publisherid", auth.getCurrentUser().getUid());
                startActivity(intent);
            }
        });

        button_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!applyCheck) {

                    userRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            applicant = dataSnapshot.getValue(User.class);

                            database.getReference("applicants").child(eventid)
                                    .child(applicant.getUid()).removeValue()
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            button_apply.setText("Apply");
                                            Toast.makeText(EventDetailActivity.this, "Application Canceled", Toast.LENGTH_SHORT).show();
                                            applyCheck = true;
                                        }
                                    });
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });

                }
                else {
                    userRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            applicant = dataSnapshot.getValue(User.class);

                            database.getReference("applicants").child(eventid)
                                    .child(applicant.getUid()).setValue(applicant)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            button_apply.setText("Applied. Tap to cancel.");
                                            Toast.makeText(EventDetailActivity.this, "Application Submitted", Toast.LENGTH_SHORT).show();
                                            applyCheck = false;
                                        }
                                    });
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                }
            }
        });

        button_view_applicants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mIntent = new Intent(EventDetailActivity.this, ViewApplicantsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("eventid", eventid);
                mIntent.putExtras(bundle);

                startActivity(mIntent);
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
        button_apply = findViewById(R.id.button_apply);
        button_view_applicants = findViewById(R.id.button_view_applicants);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        eventRef = database.getReference("events");
        userRef = database.getReference("users").child(auth.getCurrentUser().getUid());
        eventCommentsRef = FirebaseDatabase.getInstance().getReference().child("Comments");
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

                if (likeCheck) {
                    item.setIcon(R.drawable.ic_favorite_border_black);
                    item.setTitle("add to fav");
                    likeCheck = false;
                } else {
                    item.setIcon(R.drawable.ic_favorite_black);
                    item.setTitle("fav event");
                    likeCheck = true;
                }
                return true;

        }
        return true;
    }
}