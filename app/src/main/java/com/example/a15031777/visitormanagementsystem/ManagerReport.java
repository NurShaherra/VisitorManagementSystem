package com.example.a15031777.visitormanagementsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ManagerReport extends AppCompatActivity {
    ListView lvReport;
    ArrayAdapter aa;
    ArrayList<Visitor> visitors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_report);
        lvReport = (ListView) this.findViewById(R.id.lvReport);
        visitors = new ArrayList<Visitor>();
        //for seeing the lv shown
        visitors.add(new Visitor("", "Danny Wang", "", 81234567, "Alice Tan"));
        aa = new VisitorAdapter(this, R.layout.row, visitors);
        lvReport.setAdapter(aa);

        lvReport.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Visitor info = visitors.get(position);

                Toast.makeText(ManagerReport.this, "The visitor information is shown", Toast.LENGTH_LONG).show();
            }
        });
    }
}
