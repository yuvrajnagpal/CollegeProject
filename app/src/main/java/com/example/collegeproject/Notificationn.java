package com.example.collegeproject;

import io.realm.RealmObject;

public class Notificationn extends RealmObject {

    String title;
    String content;
    String time;

    public  Notificationn(){

    }


    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
