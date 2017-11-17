package com.example.khoa1.myapplication.Model;

/**
 * Created by khoa1 on 11/18/2017.
 */

public class IconAccount {
    private int drawableID;
    private String name;


    public IconAccount(int drawableID, String name) {
        this.drawableID = drawableID;
        this.name = name;
    }

    public int getDrawableID() {
        return drawableID;
    }

    public void setDrawableID(int drawableID) {
        this.drawableID = drawableID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
