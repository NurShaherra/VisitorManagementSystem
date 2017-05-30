package com.example.a15031777.visitormanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
        String sign = i.getStringExtra("sign");
        tvTitle = (TextView) findViewById(R.id.textViewName);
        tvName = (TextView) findViewById(R.id.textViewFullName);
        tvEmail = (TextView) findViewById(R.id.textViewEmail);
        tvIC = (TextView) findViewById(R.id.textViewIC);
        tvArrived = (TextView) findViewById(R.id.textViewMode);
        tvLicense = (TextView) findViewById(R.id.textViewLicense);
        btnConfirm = (Button) findViewById(R.id.buttonConfirm);
        final DBHandlerVisitor db = new DBHandlerVisitor(ConfirmActivity.this);
        final Visitor current = db.getVisitor(ic);
        Log.d("name", current.getFullName());
        tvTitle.setText(current.getFullName());
        tvName.setText(current.getFullName());
        tvEmail.setText(current.getEmailAddress());
        if (sign.equalsIgnoreCase("in")) {
            tvArrived.setText(arrivedBy);
            tvLicense.setText(license);
            btnConfirm.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    boolean signIn = db.checkSignedIn(ic);
                    if (signIn == true) {
                        Toast.makeText(ConfirmActivity.this, "Visitor already signed in!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ConfirmActivity.this, "Signed in successfully!", Toast.LENGTH_SHORT).show();
                        db.updateVisitor(ic, arrivedBy, 1, license);
                        db.close();
                        Intent back = new Intent(ConfirmActivity.this, SecurityGuardActivity.class);
                        startActivity(back);

                    }
                }
            });
        } else {
            tvArrived.setText(current.getModeOfTransport());
            tvLicense.setText(current.getLicensePlate());
            btnConfirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean signIn = db.checkSignedIn(ic);
                    if (signIn == false) {
                        Toast.makeText(ConfirmActivity.this, "Visitor already signed out!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ConfirmActivity.this, "Signed out successfully!", Toast.LENGTH_SHORT).show();
                        db.updateVisitor(ic, current.getModeOfTransport(), 0, current.getLicensePlate());
                        db.close();
                        Intent back = new Intent(ConfirmActivity.this, SecurityGuardActivity.class);
                        startActivity(back);

                    }
                }
            });
        }


    }
}
