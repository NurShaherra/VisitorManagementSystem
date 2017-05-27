package com.example.a15031777.visitormanagementsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ManageUsers extends AppCompatActivity {
    ListView lv;
    ArrayAdapter aa;
    Spinner spn;
    ArrayList<String> al;
    DBHandlerUser db;

    //ArrayList is String because CustomAdapter has not been created yet.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_users);

        Intent intent = getIntent();

        db = new DBHandlerUser(this);
        lv = (ListView) findViewById(R.id.lv_users);
        spn = (Spinner) findViewById(R.id.spinner);

//        display all users in listview
        // Refer to DBHandlerUser for the explanation of this method.
        al = db.getAllUsername();
        aa = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);

//        User currentUser = db.getUserWithId(0);
        db.close();

        String[] values =
                {"All", "Admin", "Manager", "Security Guard"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, values);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spn.setAdapter(adapter);

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // clear the arraylist then call the addAll method is because if you try to instantiate the same arraylist to a new list, the reference will be lost.
                // Hence, notifyDataSetChanged() will not work.
                // All toasts are just to debug whether the spinner works.
                if (spn.getSelectedItem().toString().equalsIgnoreCase("Admin")) {
                    al.clear();
                    //Refer to DBHandlerUser for explanation of this method.
                    al.addAll(db.getAllUsernameFilter("Administrator"));
                    db.close();
                    Toast.makeText(ManageUsers.this, "Admin Selected", Toast.LENGTH_SHORT).show();
                    aa.notifyDataSetChanged();
                } else if (spn.getSelectedItem().toString().equalsIgnoreCase("Manager")) {
                    al.clear();
                    al.addAll(db.getAllUsernameFilter("Manager"));
                    db.close();
                    Toast.makeText(ManageUsers.this, "Manager Selected", Toast.LENGTH_SHORT).show();
                    aa.notifyDataSetChanged();
                } else if (spn.getSelectedItem().toString().equalsIgnoreCase("Security Guard")) {
                    al.clear();
                    al.addAll(db.getAllUsernameFilter("Security Guard"));
                    db.close();
                    Toast.makeText(ManageUsers.this, "Security Guard Selected", Toast.LENGTH_SHORT).show();
                    aa.notifyDataSetChanged();
                } else {
                    al.clear();
                    al.addAll(db.getAllUsername());
                    db.close();
                    Toast.makeText(ManageUsers.this, "All Selected", Toast.LENGTH_SHORT).show();
                    aa.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ManageUsers.this, UserDetails.class);
                i.putExtra("name", al.get(position).toString());
                startActivity(i);
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

        if (id == R.id.menu_add) {
            Intent intent = new Intent(getBaseContext(), AddUser.class);
            startActivity(intent);
            return true;

        }

        return super.onOptionsItemSelected(item);
    }


}
