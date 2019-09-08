package com.example.hpnotebook.volunteerapp.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hpnotebook.volunteerapp.Activities.DashboardActivity;
import com.example.hpnotebook.volunteerapp.Activities.SignupActivity;
import com.example.hpnotebook.volunteerapp.ModelClasses.Event;
import com.example.hpnotebook.volunteerapp.ModelClasses.User;
import com.example.hpnotebook.volunteerapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */

public class VolunteerSignupFragment extends Fragment {

    EditText signup_contact, signup_name, signup_email, signup_password, et_location, et_description;
    Spinner signup_gender;
    Button vol_signup_btn;
    private FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference userRef;
    FirebaseUser user;
    ProgressDialog progressDialog;
    private boolean fieldCheck;

    public VolunteerSignupFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_volunteer_signup, container, false);

        signup_contact = view.findViewById(R.id.et_contact);
        signup_password = view.findViewById(R.id.et_password);
        signup_name = view.findViewById(R.id.et_name);
        signup_gender = view.findViewById(R.id.spinner_gender);
        et_location = view.findViewById(R.id.et_location);
        vol_signup_btn = view.findViewById(R.id.vol_signup_btn);
        signup_email = view.findViewById(R.id.et_email);
        et_description = view.findViewById(R.id.et_description);

        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        userRef = firebaseDatabase.getReference("users");

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please wait...");

        vol_signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = signup_name.getText().toString();
                String email = signup_email.getText().toString();
                String password = signup_password.getText().toString();
                String contact = signup_contact.getText().toString();
                String location = et_location.getText().toString();
                String gender = signup_gender.getSelectedItem().toString();
                String type = "Volunteer";
                String description = et_description.getText().toString();
                String imageUrl = "default";
                ArrayList<Event> userEvents = new ArrayList<>();

                if (name.isEmpty()) {
                    signup_name.setError("This field is empty");
                    fieldCheck = true;
                }
                if (contact.isEmpty()) {
                    signup_contact.setError("This field is empty");
                    fieldCheck = true;
                }
                if (password.isEmpty()) {
                    signup_password.setError("This field is empty");
                    fieldCheck = true;
                } else if (password.length() <= 6) {
                    signup_password.setError("Password is too short");
                    fieldCheck = true;
                }
                if (location.isEmpty()) {
                    et_location.setError("This field is empty");
                    fieldCheck = true;
                }
                if (description.isEmpty()) {
                    et_description.setError("This field is empty");
                    fieldCheck = true;
                }
                if (!fieldCheck) {
                    authUser(name, email, password, contact, location, gender, type, description, imageUrl, userEvents);
                }
            }
        });

        return view;
    }

    private void authUser(final String name, final String email, final String pass, final String contact, final String location, final String gender, final String type, final String description, final String imageUrl, final ArrayList<Event> userEvents) {

        progressDialog.show();

        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                progressDialog.dismiss();

                if (task.isSuccessful()) {
                    user = auth.getCurrentUser();
                    signupUser(name, Objects.requireNonNull(user).getUid(), email, pass, contact, location, gender, type, description, imageUrl, userEvents);

                } else {
                    Toast.makeText(getContext(), Objects.requireNonNull(task.getException()).toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void signupUser(String name, String uid, String email, String pass, String contact, String location, String gender, String type, String description, String imageUrl, ArrayList<Event> userEvents) {

        User user = new User(name, uid, email, pass, contact, location, gender, type, description, imageUrl, userEvents);
        userRef.child(uid).setValue(user);
        startActivity(new Intent(getContext(), DashboardActivity.class));
    }

}
