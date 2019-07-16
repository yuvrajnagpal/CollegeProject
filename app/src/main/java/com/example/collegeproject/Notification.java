package com.example.collegeproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.onesignal.OSNotification;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import io.realm.Realm;
import io.realm.RealmResults;

public class Notification extends AppCompatActivity {

    Realm realm;
    RecyclerView recyclerView;
    ArrayList<Notificationn> notificationns;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        Realm.init(this);
        notificationns=new ArrayList<Notificationn>();
        realm=Realm.getDefaultInstance();
        recyclerView=findViewById(R.id.notifications);



        RealmResults<Notificationn> results = realm.where(Notificationn.class).findAllAsync();

        for (Notificationn n:results){

            Notificationn notificationn=new Notificationn();
            notificationn.setTitle(n.getTitle());
            notificationn.setContent(n.getContent());
            notificationn.setTime(n.getTime());

            notificationns.add(notificationn);

        }
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new NotificationAdapter(notificationns));

    }



}
