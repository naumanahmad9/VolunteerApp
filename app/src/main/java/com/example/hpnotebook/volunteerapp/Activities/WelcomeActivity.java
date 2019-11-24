package com.example.hpnotebook.volunteerapp.Activities;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.hpnotebook.volunteerapp.ModelClasses.Event;
import com.example.hpnotebook.volunteerapp.ModelClasses.User;
import com.example.hpnotebook.volunteerapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class WelcomeActivity extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseDatabase database;
    DatabaseReference userRef;
    FirebaseUser user;
    Query query;
    List<String> userType, eventid;
    String type, evid;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        getSupportActionBar().hide();

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        userRef = database.getReference("users").child(user.getUid());
        query = database.getReference("events")
                .orderByChild("event_userId")
                .equalTo(auth.getCurrentUser().getUid());

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                if (user != null) {
                    type = user.getType();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


        Handler handler=new Handler();

        Runnable runnable=new Runnable() {
            @Override
            public void run() {

                if(type.equals("Volunteer")) {
                    startActivity(new Intent(WelcomeActivity.this, DashboardActivity.class));
                    finish();
                }
                else {
                    query.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            if (dataSnapshot.exists()) {
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                                    Event event = snapshot.getValue(Event.class);
                                    if (event != null) {
                                        evid = event.getEvent_id();
                                        Toast.makeText(WelcomeActivity.this, evid, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                        }
                    });
                }
            }
        };
        handler.postDelayed(runnable,2000);


        Handler handler2=new Handler();

        Runnable runnable2=new Runnable() {
            @Override
            public void run() {

                if (evid != null) {
                    bundle = new Bundle();
                    bundle.putString("eventid", evid);

                    Intent mIntent = new Intent(WelcomeActivity.this, EventDetailActivity.class);
                    mIntent.putExtras(bundle);

                    startActivity(mIntent);
                    overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                    finish();
                }
            }
        };
        handler2.postDelayed(runnable2,4000);

    }
}
