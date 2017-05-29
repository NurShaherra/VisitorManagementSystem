package com.example.a15031777.visitormanagementsystem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class SignInActivity extends AppCompatActivity {
    EditText etIc;
    Button btnNext;
    Spinner spn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        etIc = (EditText) findViewById(R.id.editTextIC);
        btnNext = (Button) findViewById(R.id.buttonNext);
        spn = (Spinner) findViewById(R.id.spinnerTrans);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.transport, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spn.setAdapter(adapter);
    }
}
