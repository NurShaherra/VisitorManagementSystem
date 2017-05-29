package com.example.a15031777.visitormanagementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class UserDetails extends AppCompatActivity {
    TextView tvFullName, tvUsername, tvRole, tvEmail, tvAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        TextView headerName = (TextView) findViewById(R.id.tvName);
        tvFullName = (TextView) findViewById(R.id.tvFullName);
        tvUsername = (TextView) findViewById(R.id.tvUsername);
        tvRole = (TextView) findViewById(R.id.tvRole);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvAddress = (TextView) findViewById(R.id.tvAddress);


        Bundle extras = getIntent().getExtras();
        String fullName = extras.getString("fullName");
        String username = extras.getString("username");
        String role = extras.getString("role");
        String email = extras.getString("email");
        String address = extras.getString("address");

        tvFullName.setText(fullName);
        tvUsername.setText(username);
        tvRole.setText(role);
        tvEmail.setText(email);
        tvAddress.setText(address);







    }
}
