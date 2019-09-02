package com.example.hpnotebook.volunteerapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class EventListViewHolder extends RecyclerView.ViewHolder {

    public de.hdodenhof.circleimageview.CircleImageView event_image;
    public TextView event_title, event_date, event_time, event_location;

    public EventListViewHolder(@NonNull View itemView) {
        super(itemView);

        event_image =  itemView.findViewById(R.id.event_image);
        event_title =  itemView.findViewById(R.id.event_title);
        event_date =  itemView.findViewById(R.id.event_date);
        event_time =  itemView.findViewById(R.id.event_time);
        event_location =  itemView.findViewById(R.id.event_location);
    }
}
