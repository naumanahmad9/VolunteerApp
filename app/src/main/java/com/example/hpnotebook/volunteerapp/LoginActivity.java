package com.example.hpnotebook.volunteerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    EditText login_contact, login_pass;
    Button login_btn;
    TextView tv_signup;
    private boolean fieldCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                }
            }
        });

        tv_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });


    }

    private void init() {
        login_contact = findViewById(R.id.login_contact);
        login_pass = findViewById(R.id.login_password);
        login_btn = findViewById(R.id.login_btn);
        tv_signup = findViewById(R.id.tv_signup);
    }
}