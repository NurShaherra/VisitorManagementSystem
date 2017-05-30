package com.example.a15031777.visitormanagementsystem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HostActivity extends AppCompatActivity {
    EditText etName, etEmail, etIC, etMobile;
    Button btnSave;
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_register);
        etName = (EditText) findViewById(R.id.editTextName);
        etEmail = (EditText) findViewById(R.id.editTextEmail);
        etIC = (EditText) findViewById(R.id.editTextIC);
        etMobile = (EditText) findViewById(R.id.editTextMobile);
        btnSave = (Button) findViewById(R.id.buttonSave);
        tvTitle = (TextView) findViewById(R.id.textViewTitle);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(HostActivity.this);
        final int id = pref.getInt("isLoggedIn", -1);
        DBHandlerUser db = new DBHandlerUser(HostActivity.this);
        User currentUser = db.getUserWithId(id);
        if (currentUser.getUserRole().equalsIgnoreCase("Host")) {
            tvTitle.setText("Welcome Host!");
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //incomplete as send email and generate qr code is in the works.
                    String name = etName.getText().toString();
                    String email = etEmail.getText().toString();
                    String nric = etIC.getText().toString();
                    int mobile = Integer.parseInt(etMobile.getText().toString());
                    //Idk what to put so just fuck it all and dash because what the fuck is this even.
                    Visitor visitor = new Visitor(nric, name, email, mobile,"-");
                    DBHandlerVisitor db = new DBHandlerVisitor(HostActivity.this);
                    db.addVisitor(visitor, id);
                    Toast.makeText(getBaseContext(), "Successfully saved visitor!", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            tvTitle.setText("Register Visitor");
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //incomplete as send email and generate qr code is in the works.
                    String name = etName.getText().toString();
                    String email = etEmail.getText().toString();
                    String nric = etIC.getText().toString();
                    int mobile = Integer.parseInt(etMobile.getText().toString());
                    //Idk what to put so just fuck it all and dash because what the fuck is this even.
                    Visitor visitor = new Visitor(nric, name, email, mobile,"-");
                    DBHandlerVisitor db = new DBHandlerVisitor(HostActivity.this);
                    db.addVisitor(visitor, id);
                    Toast.makeText(getBaseContext(), "Successfully saved visitor!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_host, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();
        if (id == R.id.menu_logout) {
            SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(HostActivity.this);
            SharedPreferences.Editor edit = pref.edit();
            edit.putInt("isLoggedIn", -1).commit();
            Intent i = new Intent(HostActivity.this, MainActivity.class);
            startActivity(i);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
