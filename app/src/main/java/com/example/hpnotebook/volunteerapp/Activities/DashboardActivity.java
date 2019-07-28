package com.example.hpnotebook.volunteerapp.Activities;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hpnotebook.volunteerapp.DashboardFragmentAdapter;
import com.example.hpnotebook.volunteerapp.Fragments.AchievedEventsFragment;
import com.example.hpnotebook.volunteerapp.Fragments.MyEventsFragment;
import com.example.hpnotebook.volunteerapp.Fragments.NewEventsFragment;
import com.example.hpnotebook.volunteerapp.R;

import java.util.ArrayList;
import java.util.Objects;

public class DashboardActivity extends AppCompatActivity {

    ViewPager vp;
    TabLayout tl;
    ArrayList<Fragment> list;
    DashboardFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Dashboard");

        init();
        list.add(new MyEventsFragment());
        list.add(new NewEventsFragment());
        list.add(new AchievedEventsFragment());

        vp.setAdapter(adapter);
        tl.setupWithViewPager(vp);

    }
    private void init() {
        vp = findViewById(R.id.vp_dashboard);
        tl =  findViewById(R.id.tl_dashboard);
        list = new ArrayList<>();
        adapter = new DashboardFragmentAdapter(getSupportFragmentManager(), list);
    }
}
