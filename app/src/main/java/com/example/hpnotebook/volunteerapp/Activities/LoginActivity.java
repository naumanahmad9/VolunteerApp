package com.example.hpnotebook.volunteerapp.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hpnotebook.volunteerapp.R;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    EditText login_contact, login_pass;
    Button login_btn;
    TextView tv_signup;
    private boolean fieldCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Log in");

        init();

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

    private void init() {
        login_contact = findViewById(R.id.login_contact);
        login_pass = findViewById(R.id.login_password);
        login_btn = findViewById(R.id.login_btn);
        tv_signup = findViewById(R.id.tv_signup);
    }

    public void login() {

        String email = login_contact.getText().toString();
        String pass = login_pass.getText().toString();

        if (email.isEmpty()) {
            login_contact.setError("This field is empty");
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
            startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
            finish();
        }
    }
}
