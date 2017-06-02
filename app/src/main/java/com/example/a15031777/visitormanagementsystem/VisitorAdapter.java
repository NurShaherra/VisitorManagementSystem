package com.example.a15031777.visitormanagementsystem;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;
import static com.example.a15031777.visitormanagementsystem.R.id.textView;

/**
 * Created by 15017199 on 30/5/2017.
 */

public class VisitorAdapter extends ArrayAdapter<Visitor> {
    private ArrayList<Visitor> visitors;
    private Context context;
    private ImageButton imageButton;
    private TextView textViewName;
    private TextView textViewNumber;
    private TextView textViewVName;
    private TextView textViewHost;
    private TextView textViewNum;
    ManagerReport MR;

    public VisitorAdapter(Context context, int resource, ArrayList<Visitor> objects) {
        super(context, resource, objects);

        visitors = objects;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // "Inflate" the row.xml as the layout for the View object
        View rowView = inflater.inflate(R.layout.row, parent, false);

        imageButton = (ImageButton) rowView.findViewById(R.id.imageButton);
        // Get the TextView object
        textViewName = (TextView) rowView.findViewById(R.id.textViewName);
        textViewNumber = (TextView) rowView.findViewById(R.id.textViewNumber);
        textViewVName = (TextView) rowView.findViewById(R.id.textViewVName);
        textViewHost = (TextView) rowView.findViewById(R.id.textViewHost);
        textViewNum = (TextView) rowView.findViewById(R.id.textViewNum);

        Visitor currentInfo = visitors.get(position);
        textViewVName.setText(currentInfo.getFullName());
        //textViewNumber.setText(MR.lvReport.getAdapter().getCount());
        textViewHost.setText(currentInfo.getHostName());
        textViewNum.setText(currentInfo.getMobileNum());
        return rowView;
    }

}
