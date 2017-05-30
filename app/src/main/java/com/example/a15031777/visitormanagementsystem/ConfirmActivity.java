package com.example.a15031777.visitormanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConfirmActivity extends AppCompatActivity {
    TextView tvTitle, tvName, tvEmail, tvIC, tvArrived, tvLicense;
    Button btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        Intent i = getIntent();
        final String ic = i.getStringExtra("nric");
        final String license = i.getStringExtra("license");
        final String arrivedBy = i.getStringExtra("by");
        tvTitle = (TextView) findViewById(R.id.textViewName);
        tvName = (TextView) findViewById(R.id.textViewFullName);
        tvEmail = (TextView) findViewById(R.id.textViewEmail);
        tvIC = (TextView) findViewById(R.id.textViewIC);
        tvArrived = (TextView) findViewById(R.id.textViewMode);
        tvLicense = (TextView) findViewById(R.id.textViewLicense);
        btnConfirm = (Button) findViewById(R.id.buttonConfirm);
        final DBHandlerVisitor db = new DBHandlerVisitor(ConfirmActivity.this);
        Visitor current = db.getVisitor(ic);
        Log.d("name", current.getFullName());
        tvTitle.setText(current.getFullName());
        tvName.setText(current.getFullName());
        tvEmail.setText(current.getEmailAddress());
        tvArrived.setText(arrivedBy);
        tvLicense.setText(license);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.updateVisitor(ic, arrivedBy, 1, license);
                db.close();
                Intent back = new Intent(ConfirmActivity.this, SecurityGuardActivity.class);
                startActivity(back);
            }
        });

    }
}
