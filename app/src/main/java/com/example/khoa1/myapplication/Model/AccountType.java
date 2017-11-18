package com.example.khoa1.myapplication.Model;

import android.graphics.drawable.Drawable;

/**
 * Created by khoa1 on 10/31/2017.
 */

public class AccountType {

    public AccountType(int id, String name, String property)
    {
        this.id = id;
        this.name = name;
        this.property = property;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }


    int id;
    String name;
    String property;
}
