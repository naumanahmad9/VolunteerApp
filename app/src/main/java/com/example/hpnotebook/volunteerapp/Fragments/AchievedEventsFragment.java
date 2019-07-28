package com.example.hpnotebook.volunteerapp.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.example.hpnotebook.volunteerapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AchievedEventsFragment extends Fragment {


    ListView lv_new_events;
    String[] events = { "Karachi Literature Festival"};
    String[] dates = {"10 October"};
    String[] times = {"5 PM"};
    String[] locations = {"Karachi"};
    int[] images = {R.drawable.karachi_volunteer_1};
    ImageView add_new_event;

    public AchievedEventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_events, container, false);

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

        return view;
    }

}
