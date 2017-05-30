package com.example.a15031777.visitormanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QRManualActivity extends AppCompatActivity {
    TextView tv;
    Button btnQr, btnManual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrmanual);
        tv = (TextView) findViewById(R.id.textViewName);
        btnManual = (Button) findViewById(R.id.buttonManual);
        btnQr = (Button) findViewById(R.id.buttonQR);
        Intent i = getIntent();
        String sign = i.getStringExtra("sign");
        if(sign.equalsIgnoreCase("Sign In")){
            tv.setText(sign);
            btnManual.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent next = new Intent(QRManualActivity.this,SignInActivity.class);
                    startActivity(next);
                }
            });
        } else {
            tv.setText(sign);
            btnManual.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent next = new Intent(QRManualActivity.this,SignOutActivity.class);
                    startActivity(next);
                }
            });
        }
    }
}
