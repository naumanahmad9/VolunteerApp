package com.example.hpnotebook.volunteerapp.Activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hpnotebook.volunteerapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateProfileActivity extends AppCompatActivity {

    EditText et_location, et_description;
    Button btn_update;
    FirebaseAuth auth;
    FirebaseUser user;
    FirebaseDatabase database;
    DatabaseReference userRef;
    String loc, desc;
    Boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        getSupportActionBar().setTitle("Update Profile");

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        userRef = database.getReference("users");

        et_location = findViewById(R.id.et_location);
        et_description = findViewById(R.id.et_description);
        btn_update = findViewById(R.id.btn_update);

        loc = et_location.getText().toString();
        desc = et_description.getText().toString();

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loc = et_location.getText().toString();
                desc = et_description.getText().toString();

                userRef.child(user.getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild("location")) {
                            check = true;
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });

                if (check = true) {
                    userRef.child(user.getUid()).child("location").removeValue();
                    userRef.child(user.getUid()).child("description").removeValue();
                }
                userRef.child(user.getUid()).child("location").setValue(loc);
                userRef.child(user.getUid()).child("description").setValue(desc);

                Toast.makeText(UpdateProfileActivity.this, "Profile updated.", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(UpdateProfileActivity.this, UserProfileActivity.class));
                finish();

            }
        });
    }
}
