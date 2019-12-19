package com.example.todomvp.model;

import androidx.annotation.NonNull;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Task extends RealmObject {

    @PrimaryKey
    @NonNull
    private int id;

    private String text;
    private String shortDate;
    private Date longDate;
    private String time;
    private Boolean status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getShortDate() {
        return shortDate;
    }

    public void setShortDate(String shortDate) {
        this.shortDate = shortDate;
    }

    public Date getLongDate() {
        return longDate;
    }

    public void setLongDate(Date date) {
        this.longDate = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
