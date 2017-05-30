package com.example.a15031777.visitormanagementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignOutActivity extends AppCompatActivity {
    EditText etIC;
    Button btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_out);
        etIC = (EditText)findViewById(R.id.editTextIC);
        btnNext = (Button)findViewById(R.id.buttonNxt);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ic = etIC.getText().toString();
                DBHandlerVisitor db = new DBHandlerVisitor(SignOutActivity.this);
                boolean checkVisitor = db.checkVisitor(ic);
                if (checkVisitor == true) {
                Intent i = new Intent(SignOutActivity.this, ConfirmActivity.class);
                i.putExtra("nric", ic);
                i.putExtra("sign","out");
                startActivity(i);
                } else {
                    Toast.makeText(SignOutActivity.this, "Visitor does not exist!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
