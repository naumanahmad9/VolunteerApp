package com.example.hpnotebook.volunteerapp.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hpnotebook.volunteerapp.DashboardFragmentAdapter;
import com.example.hpnotebook.volunteerapp.Fragments.AchievedEventsFragment;
import com.example.hpnotebook.volunteerapp.Fragments.OrgSignupFragment;
import com.example.hpnotebook.volunteerapp.Fragments.VolunteerSignupFragment;
import com.example.hpnotebook.volunteerapp.ModelClasses.Event;
import com.example.hpnotebook.volunteerapp.ModelClasses.User;
import com.example.hpnotebook.volunteerapp.R;
import com.example.hpnotebook.volunteerapp.SignupFragmentAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;

public class SignupActivity extends AppCompatActivity {

    ViewPager vp;
    TabLayout tl;
    ArrayList<Fragment> list;
    SignupFragmentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Sign up");

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        init();;

        list.add(new OrgSignupFragment());
        list.add(new VolunteerSignupFragment());

        vp.setAdapter(adapter);
        tl.setupWithViewPager(vp);

    }

    private void init() {
        vp = findViewById(R.id.vp_signup);
        tl =  findViewById(R.id.tl_signup);
        list = new ArrayList<>();
        adapter = new SignupFragmentAdapter(getSupportFragmentManager(), list);
    }

}