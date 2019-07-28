package com.example.hpnotebook.volunteerapp.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hpnotebook.volunteerapp.FragmentListAdapter;
import com.example.hpnotebook.volunteerapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewEventsFragment extends Fragment {

    ListView lv_new_events;
    String[] events = {"Tree Plantation", "Karachi Literature Festival", "Red Crescent", "World Wide Fund", "United Nations"};
    String[] dates = {"10 August", "10 October", "20 November", "1 December", "10 December"};
    String[] times = {"9 AM", "5 PM", "6 PM", "8 AM", "2 PM"};
    String[] locations = {"Islamabad", "Karachi", "Karachi", "Lahore", "Islamabad"};
    int[] images = {R.drawable.treeplantation, R.drawable.karachi_volunteer_1, R.drawable.karachi_volunteer_2, R.drawable.lahore_volunteer_1, R.drawable.isl_volunteer_1};

    public NewEventsFragment() {
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
                Toast.makeText(getActivity(), events[pos], Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}
