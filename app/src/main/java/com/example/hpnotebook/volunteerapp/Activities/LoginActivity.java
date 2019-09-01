package com.example.hpnotebook.volunteerapp.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

public class LoginActivity extends AppCompatActivity {

    EditText login_email, login_pass;
    Button login_btn;
    TextView tv_signup;
    private boolean fieldCheck;
    private int LOGIN = 1;
    ProgressDialog progressDialog;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference userRef;
    FirebaseUser user, firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Log in");

        init();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        tv_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        //check if user is null
        if (firebaseUser != null){
            startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
            finish();
        }
    }

    private void authUser(String email, String pass) {

        progressDialog.show();

        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                progressDialog.dismiss();

                if (task.isSuccessful()) {
                    user = auth.getCurrentUser();
                    startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
                    overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                }
            }
        });
    }

    public void login() {

        String email = login_email.getText().toString();
        String pass = login_pass.getText().toString();

        if (email.isEmpty()) {
            login_email.setError("This field is empty");
            fieldCheck = true;
        }
        if (pass.isEmpty()) {
            login_pass.setError("This field is empty");
            fieldCheck = true;
        }
        else if (pass.length() <= 6) {
            login_pass.setError("Password is too short");
            fieldCheck = true;
        }
        if (!fieldCheck) {
            authUser(email, pass);
            finish();
        }
    }

    private void init() {
        login_email = findViewById(R.id.login_email);
        login_pass = findViewById(R.id.login_password);
        login_btn = findViewById(R.id.login_btn);
        tv_signup = findViewById(R.id.tv_signup);
        firebaseDatabase = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        userRef = firebaseDatabase.getReference("users");
    }

}
