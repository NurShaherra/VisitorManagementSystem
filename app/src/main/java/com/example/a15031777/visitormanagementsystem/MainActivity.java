package com.example.a15031777.visitormanagementsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean userLoggedIn = pref.getBoolean("isLoggedIn", false);


        if (userLoggedIn == false) {
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DBHandlerUser db = new DBHandlerUser(MainActivity.this);
                    db.removeAllUser();
                    db.addUser(new User(1, "admin", "email", "123", "Administrator", "-", "-"));
                    SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                    SharedPreferences.Editor edit = pref.edit();
                    String username = etUName.getText().toString();
                    String pw = etPw.getText().toString();
                    Boolean userId = db.checkUser(username, pw);

                    if (userId == true) {
                        edit.putBoolean("isLoggedIn", userId).commit();
                        edit.putInt("id", db.getUserId(username, pw)).commit();
                        db.close();
                        Intent i = new Intent(MainActivity.this, AdminActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(MainActivity.this, "Your username/password is wrong.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Intent i = new Intent(MainActivity.this, AdminActivity.class);
            startActivity(i);
        }
    }
}
