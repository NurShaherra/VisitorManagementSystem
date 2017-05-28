package com.example.a15031777.visitormanagementsystem;

import android.app.DatePickerDialog;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ViewSummary extends AppCompatActivity {
    ImageButton IbLogo;
    Spinner spnOptions;
    TextView tvViewSum;
    ListView lv;
    Records record;

    ArrayList<String> al;
    ArrayAdapter<String> aa;

    //get calendar for user to select when the user selected "Date"
    Calendar myCalendar = Calendar.getInstance();

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };

    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.getDefault());
        al.add(sdf.format(myCalendar.getTime()));
        aa.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_summary);

        IbLogo = (ImageButton) findViewById(R.id.imageButton2);
        spnOptions = (Spinner) findViewById(R.id.spinnerOptions);
        tvViewSum = (TextView) findViewById(R.id.tvViewSum);
        lv = (ListView) findViewById(R.id.lv);

        aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, al);
        lv.setAdapter(aa);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spnOptions.setAdapter(adapter);

        spnOptions.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spnOptions.getSelectedItem().toString().equalsIgnoreCase("Date")) {
                    al.clear();
                    new DatePickerDialog(ViewSummary.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DATE)).show();
                    updateLabel();

                } else if (spnOptions.getSelectedItem().toString().equalsIgnoreCase("Month")) {
                    al.clear();
                    al.add("January");
                    al.add("February");
                    al.add("March");
                    al.add("April");
                    al.add("May");
                    aa.notifyDataSetChanged();
                } else if (spnOptions.getSelectedItem().toString().equalsIgnoreCase("Year")) {
                    al.add("2016");
                    al.add("2017");
                    aa.notifyDataSetChanged();
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                
            }
        });
    }
}
