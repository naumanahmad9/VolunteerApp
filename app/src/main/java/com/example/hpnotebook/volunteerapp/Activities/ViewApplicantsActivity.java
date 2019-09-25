package com.example.hpnotebook.volunteerapp.Activities;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.hpnotebook.volunteerapp.ModelClasses.Event;
import com.example.hpnotebook.volunteerapp.ModelClasses.User;
import com.example.hpnotebook.volunteerapp.R;
import com.example.hpnotebook.volunteerapp.ViewApplicantsAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ViewApplicantsActivity extends AppCompatActivity {

    RecyclerView rv_applicants;
    ArrayList<User> users;
    ViewApplicantsAdapter adapter;
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference eventRef;
    String eventid;
    boolean applicantsCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_applicants);

        getSupportActionBar().setTitle("Applicants");

        Bundle bundle = getIntent().getExtras();
        eventid = bundle.getString("eventid");

        rv_applicants = findViewById(R.id.rv_applicants);

        users = new ArrayList<>();
        adapter = new ViewApplicantsAdapter(users, this, eventid);

        rv_applicants.setLayoutManager(new LinearLayoutManager(this));
        rv_applicants.setAdapter(adapter);

        auth=FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        database.getReference("applicants").child(eventid).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    /*
                    User user = dataSnapshot.getValue(User.class);
                    users.add(user);
                    adapter.notifyDataSetChanged();
                    */
                    users.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        User  user = snapshot.getValue(User.class);
                        users.add(user);
                    }

                    adapter.notifyDataSetChanged();
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        }
    }
