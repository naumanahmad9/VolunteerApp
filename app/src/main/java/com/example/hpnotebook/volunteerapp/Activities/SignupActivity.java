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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.hpnotebook.volunteerapp.ModelClasses.User;
import com.example.hpnotebook.volunteerapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class SignupActivity extends AppCompatActivity {

    EditText signup_contact, signup_name, signup_password;
    Spinner signup_gender, signup_type;
    Button signup_btn;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference userRef;
    FirebaseUser user;
    ProgressDialog progressDialog;
    private boolean fieldCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Sign up");

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        init();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");

        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = signup_name.getText().toString();
                String contact = signup_contact.getText().toString();
                String password = signup_password.getText().toString();
                String imageUrl = "default";

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
                if (!fieldCheck) {
                    authUser(name, contact, password, imageUrl);
                }

            }
        });
    }

    private void authUser(final String name, final String email, final String pass, final String imageURL) {

        progressDialog.show();

        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                progressDialog.dismiss();

                if (task.isSuccessful()) {
                    user = auth.getCurrentUser();
                    signupUser(name, email, pass, user.getUid(), imageURL);

                } else {
                    Toast.makeText(SignupActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void signupUser(String name, String email, String pass, String uid, String imageURL) {

        User user = new User(name, uid, email, pass, imageURL);
        userRef.child(uid).setValue(user);
        startActivity(new Intent(this, DashboardActivity.class));
    }

    private void init() {
        signup_contact = findViewById(R.id.et_contact);
        signup_password = findViewById(R.id.et_password);
        signup_name = findViewById(R.id.et_name);
        signup_gender = findViewById(R.id.spinner_gender);
        signup_type = findViewById(R.id.spinner_gender);
        signup_btn = findViewById(R.id.spinner_acc_type);
        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        userRef = firebaseDatabase.getReference("users");
    }
}