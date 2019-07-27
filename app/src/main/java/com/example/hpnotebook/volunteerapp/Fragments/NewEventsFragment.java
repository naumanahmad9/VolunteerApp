package com.example.hpnotebook.volunteerapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hpnotebook.volunteerapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewEventsFragment extends Fragment {


    public NewEventsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_events, container, false);
    }

}
