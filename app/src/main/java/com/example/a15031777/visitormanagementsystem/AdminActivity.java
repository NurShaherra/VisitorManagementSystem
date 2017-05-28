package com.example.a15031777.visitormanagementsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {
    TextView tv;
    ListView lv;
    ArrayAdapter aa;
    ArrayList<String> al = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        tv = (TextView)findViewById(R.id.textViewWelcome);
        lv = (ListView) findViewById(R.id.lv);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        int id = pref.getInt("isLoggedIn",-1);
        DBHandlerUser db = new DBHandlerUser(this);
        User currentUser = db.getUserWithId(id);
        db.close();
        tv.append(" "+currentUser.getUserRole()+"!");

        String[] values = new String[] { "Manage Users",
                "Manage Visitors"
        };
        aa =  new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,values);
        lv.setAdapter(aa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0) {
                    Intent i = new Intent(AdminActivity.this, ManageUsers.class);
                    startActivity(i);
                } else if(position == 1){
                    Intent i = new Intent(AdminActivity.this, ManageVisitor.class);
                    startActivity(i);
                } else if(position == 2){
                    Intent i = new Intent(AdminActivity.this, ViewSummary.class);
                    startActivity(i);
                }


            }
        });

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
            edit.putInt("isLoggedIn",-1).commit();
            Intent i = new Intent(AdminActivity.this,MainActivity.class);
            startActivity(i);
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

}
