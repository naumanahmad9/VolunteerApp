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
import com.example.hpnotebook.volunteerapp.MyEventsListAdapter;
import com.example.hpnotebook.volunteerapp.NewEventsListAdapter;
import com.example.hpnotebook.volunteerapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyEventsFragment extends Fragment {

    /*
    ListView lv_new_events;
    String[] events = { "Karachi Literature Festival", "World Wide Fund"};
    String[] dates = {"10 October", "1 December"};
    String[] times = {"5 PM", "8 AM"};
    String[] locations = {"Karachi", "Lahore"};
    int[] images = {R.drawable.karachi_volunteer_1, R.drawable.lahore_volunteer_1};
    */

    RecyclerView rv_my_events;
    ImageView add_new_event;
    ArrayList<Event> events;
    MyEventsListAdapter adapter;
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference eventListingRef, userRef;
    FirebaseUser user;

    public MyEventsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_my_events, container, false);

        /*
        lv_new_events = (ListView) view.findViewById(R.id.lv_new_events);

        FragmentListAdapter fragmentListAdapter = new FragmentListAdapter(getActivity(), events, dates, times, locations, images);
        lv_new_events.setAdapter(fragmentListAdapter);

        lv_new_events.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                startActivity(new Intent(getActivity(), EventDetailActivity.class));
            }
        });
        */
        add_new_event = view.findViewById(R.id.add_new_event);

        rv_my_events = view.findViewById(R.id.rv_my_events);
        events = new ArrayList<>();
        adapter = new MyEventsListAdapter(events, getContext());

        rv_my_events.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_my_events.setAdapter(adapter);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        eventListingRef = database.getReference("events");
        user = FirebaseAuth.getInstance().getCurrentUser();
        userRef = database.getReference("users").child(user.getUid());

        userRef.child("userEvents").addChildEventListener(new ChildEventListener() {
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

        return view;
    }

}
