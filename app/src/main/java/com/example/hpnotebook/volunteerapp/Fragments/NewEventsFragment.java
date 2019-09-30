package com.example.hpnotebook.volunteerapp.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hpnotebook.volunteerapp.Activities.AddEventActivity;
import com.example.hpnotebook.volunteerapp.Activities.EventDetailActivity;
import com.example.hpnotebook.volunteerapp.FragmentListAdapter;
import com.example.hpnotebook.volunteerapp.ModelClasses.Event;
import com.example.hpnotebook.volunteerapp.ModelClasses.User;
import com.example.hpnotebook.volunteerapp.NewEventsListAdapter;
import com.example.hpnotebook.volunteerapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.EventTarget;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewEventsFragment extends Fragment {

    ImageView add_new_event;
    RecyclerView rv_newEvents;
    ArrayList<Event> events;
    NewEventsListAdapter adapter;
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference eventListingRef, userRef;
    FirebaseUser user;

    public NewEventsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_new_events, container, false);

        add_new_event = view.findViewById(R.id.add_new_event);

        rv_newEvents = view.findViewById(R.id.rv_newEvents);
        events = new ArrayList<>();
        adapter = new NewEventsListAdapter(events, getContext());

        rv_newEvents.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_newEvents.setAdapter(adapter);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        eventListingRef = database.getReference("events");
        user = FirebaseAuth.getInstance().getCurrentUser();
        userRef = database.getReference("users").child(user.getUid());

        eventListingRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Event event = dataSnapshot.getValue(Event.class);

                if (Objects.requireNonNull(event).getEvent_id() != null) {
                    events.add(event);
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                String userType = user.getType();

                if (userType.equals("Organization")) {
                    add_new_event.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        add_new_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddEventActivity.class));
            }
        });

        return view;
    }

}


