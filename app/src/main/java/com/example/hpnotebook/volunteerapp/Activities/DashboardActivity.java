package com.example.hpnotebook.volunteerapp.Activities;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

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
        list.add(new NewEventsFragment());
        list.add(new MyEventsFragment());
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id)
        {
            case R.id.profile:
                startActivity(new Intent(this, UserProfileActivity.class));
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                finish();
                return true;

            case R.id.search:
                startActivity(new Intent(this, SearchEventsActivity.class));
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
