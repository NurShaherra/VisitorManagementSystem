package com.example.a15031777.visitormanagementsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class AdminActivity extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        tv = (TextView)findViewById(R.id.textViewWelcome);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        int id = pref.getInt("id",-1);
        DBHandlerUser db = new DBHandlerUser(this);
        User currentUser = db.getUserWithId(id);
        db.close();
        tv.append(" "+currentUser.getUserRole()+"!");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();
        int TextView = item.getItemId();


        if (id == R.id.menu_logout) {
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(AdminActivity.this);
            SharedPreferences.Editor edit = pref.edit();
            edit.putBoolean("isLoggedIn",false).commit();
            Intent i = new Intent(AdminActivity.this,MainActivity.class);
            startActivity(i);
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

}
