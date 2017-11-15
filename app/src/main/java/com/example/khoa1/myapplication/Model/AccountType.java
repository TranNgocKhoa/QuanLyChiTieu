package com.example.khoa1.myapplication.Model;

import android.graphics.drawable.Drawable;

/**
 * Created by khoa1 on 10/31/2017.
 */

public class AccountType {

    public AccountType(int id, String name, String property, Drawable image)
    {
        this.id = id;
        this.name = name;
        this.property = property;
        this.image = image;
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

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    int id;
    String name;
    String property;
    Drawable image;
}
