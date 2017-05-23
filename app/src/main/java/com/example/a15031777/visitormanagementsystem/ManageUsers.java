package com.example.a15031777.visitormanagementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class ManageUsers extends AppCompatActivity {
    ListView lv;
    ArrayAdapter aa;
    Spinner spn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_users);

        Intent intent = getIntent();

        DBHandlerUser db = new DBHandlerUser(this);
        lv = (ListView) findViewById(R.id.lv);
        spn = (Spinner) findViewById(R.id.spinner);

//        display all users in listview

        ArrayList<User> al = db.getAllUsers();
        aa =  new ArrayAdapter<User>(this,android.R.layout.simple_list_item_1,al);
        lv.setAdapter(aa);

//        User currentUser = db.getUserWithId(0);
        db.close();

        String [] values =
                {"Admin","Manager","Security Guard"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spn.setAdapter(adapter);

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if (spn.getSelectedItem().toString().equalsIgnoreCase() == currentUser.getUserRole("admin")) {
//
//                }
        }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
