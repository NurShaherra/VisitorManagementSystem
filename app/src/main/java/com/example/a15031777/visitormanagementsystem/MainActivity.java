package com.example.a15031777.visitormanagementsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.ObjectInputValidation;

public class MainActivity extends AppCompatActivity {
    EditText etUName, etPw;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUName = (EditText) findViewById(R.id.editTextUsername);
        etPw = (EditText) findViewById(R.id.editTextPassword);
        btnLogin = (Button) findViewById(R.id.buttonLogin);

        String username = etUName.getText().toString();
        String pw = etPw.getText().toString();


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




    }
}
