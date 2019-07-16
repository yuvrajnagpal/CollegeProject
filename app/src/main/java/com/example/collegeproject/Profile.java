package com.example.collegeproject;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Profile extends AppCompatActivity {

    TextView firstname,lastname,dob,percentage,email;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        sp=getSharedPreferences("file1",0);

        firstname=findViewById(R.id.profile_fname);
        lastname=findViewById(R.id.profile_lname);
        dob=findViewById(R.id.profile_dob);
        percentage=findViewById(R.id.profile_percentage);
        email=findViewById(R.id.profile_email);

        firstname.setText(sp.getString("firstname",""));
        firstname.setText(sp.getString("lastname",""));
        firstname.setText(sp.getString("dob",""));
        firstname.setText(sp.getString("percentage",""));
        firstname.setText(sp.getString("email",""));

    }
}
