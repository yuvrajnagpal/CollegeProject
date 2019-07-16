package com.example.collegeproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class TimeTable extends AppCompatActivity {


    RecyclerView timetable;
    ArrayList<String> smesterList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);


        timetable=findViewById(R.id.timetable_list);
        smesterList=new ArrayList<String>();
        smesterList.add("Semester 1");
        smesterList.add("Semester 2");
        smesterList.add("Semester 3");
        smesterList.add("Semester 4");
        smesterList.add("Semester 5");
        smesterList.add("Semester 6");
        smesterList.add("Semester 7");
        smesterList.add("Semester 8");


        timetable.setLayoutManager(new LinearLayoutManager(this));
        timetable.setAdapter(new SmestersAdapter(smesterList));
    }
}
