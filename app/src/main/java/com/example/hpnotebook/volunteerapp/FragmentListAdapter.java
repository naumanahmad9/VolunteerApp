package com.example.hpnotebook.volunteerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FragmentListAdapter extends ArrayAdapter<String> {

    private Context context;
    private String[] events, dates, times, locations;
    private int[] images;
    private LayoutInflater inflater;

    public FragmentListAdapter(Context context, String[] events, String[] dates, String[] times, String[] locations, int[] images) {
        super(context, R.layout.fragment_list_layout, events);

        this.context = context;
        this.events = events;
        this.dates = dates;
        this.times = times;
        this.locations = locations;
        this.images = images;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.fragment_list_layout, null);
        }

        TextView event_title = (TextView) convertView.findViewById(R.id.event_title);
        TextView event_date = (TextView) convertView.findViewById(R.id.event_date);
        TextView event_time = (TextView) convertView.findViewById(R.id.event_time);
        TextView event_location = (TextView) convertView.findViewById(R.id.event_location);
        ImageView event_image = (ImageView) convertView.findViewById(R.id.event_image);

        event_title.setText(events[position]);
        event_date.setText(dates[position]);
        event_time.setText(times[position]);
        event_location.setText(locations[position]);
        event_image.setImageResource(images[position]);

        return convertView;
    }
}
