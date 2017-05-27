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

        DBHandlerUser db = new DBHandlerUser(UserDetails.this);
        ArrayList<User> user = db.getUser();

        
        Intent received = getIntent();
        String name = received.getStringExtra("name");
        headerName.setText(name);


    }
}
