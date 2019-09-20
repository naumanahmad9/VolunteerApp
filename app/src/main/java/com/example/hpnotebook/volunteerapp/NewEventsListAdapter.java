package com.example.hpnotebook.volunteerapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.hpnotebook.volunteerapp.Activities.EventDetailActivity;
import com.example.hpnotebook.volunteerapp.ModelClasses.Event;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NewEventsListAdapter extends RecyclerView.Adapter<EventListViewHolder>{

    private ArrayList<Event> events;
    private Context mContext;
    private FirebaseDatabase database;

    public NewEventsListAdapter(ArrayList<Event> events, Context context) {
        this.events = events;
        this.mContext = context;
    }

    @NonNull
    @Override
    public EventListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_list_layout, viewGroup, false);
        return new EventListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventListViewHolder eventListViewHolder, int i) {

        final Event event = events.get(i);

        database = FirebaseDatabase.getInstance();

        eventListViewHolder.event_title.setText(event.getEvent_title());
        eventListViewHolder.event_date.setText(event.getEvent_date());
        eventListViewHolder.event_time.setText(event.getEvent_time());
        eventListViewHolder.event_location.setText(event.getEvent_location());

        Glide.with(mContext).load(event.getEvent_image()).into(eventListViewHolder.event_image);

        eventListViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("eventid", event.getEvent_id());

                Intent mIntent = new Intent(mContext, EventDetailActivity.class);
                mIntent.putExtras(bundle);
                mContext.startActivity(mIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}
