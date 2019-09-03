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
import com.example.hpnotebook.volunteerapp.NewEventsListAdapter;
import com.example.hpnotebook.volunteerapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.EventTarget;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewEventsFragment extends Fragment {

    /*
    ListView lv_new_events;
    String[] events = {"Tree Plantation", "Karachi Literature Festival", "Red Crescent",
                        "World Wide Fund", "United Nations"};
    String[] dates = {"10 August", "10 October", "20 November", "1 December", "10 December"};
    String[] times = {"9 AM", "5 PM", "6 PM", "8 AM", "2 PM"};
    String[] locations = {"Islamabad", "Karachi", "Karachi", "Lahore", "Islamabad"};
    int[] images = {R.drawable.treeplantation, R.drawable.karachi_volunteer_1,
            R.drawable.karachi_volunteer_2, R.drawable.lahore_volunteer_1, R.drawable.isl_volunteer_1};
    ImageView add_new_event;
    */

    RecyclerView rv_newEvents;
    ArrayList<Event> events;
    NewEventsListAdapter adapter;
    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference eventListingRef;

    public NewEventsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_new_events, container, false);

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

        add_new_event = view.findViewById(R.id.add_new_event);
        add_new_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AddEventActivity.class));
            }
        });
        */

        rv_newEvents = view.findViewById(R.id.rv_newEvents);
        events = new ArrayList<>();
        adapter = new NewEventsListAdapter(events, getContext());

        rv_newEvents.setLayoutManager(new LinearLayoutManager(getContext()));
        rv_newEvents.setAdapter(adapter);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        eventListingRef = database.getReference("events");

        eventListingRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Event event = dataSnapshot.getValue(Event.class);

                if(Objects.requireNonNull(event).getEvent_id() != null) {
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


