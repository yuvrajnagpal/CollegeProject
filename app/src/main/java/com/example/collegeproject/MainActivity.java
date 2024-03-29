package com.example.collegeproject;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.sql.Time;

public class MainActivity extends AppCompatActivity {

    LinearLayout syllabusLinear,timetableLinear,notificationLinear,profileLinear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        syllabusLinear=findViewById(R.id.syllabus);
        timetableLinear=findViewById(R.id.timetable);
        notificationLinear=findViewById(R.id.notifications);
        profileLinear=findViewById(R.id.profile);

        syllabusLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(Intent.ACTION_VIEW);
               intent.setData(Uri.parse("https://www.ptu.ac.in/userfiles/file/engg_syllab/CSE/25-04-19%20Scheme&Syllabus-B_tech-CSE-2018.pdf"));
               startActivity(intent);
            }
        });


        timetableLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TimeTable.class));
            }
        });

        notificationLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Notification.class));
            }
        });


        profileLinear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Profile.class));
            }
        });
    }
}
