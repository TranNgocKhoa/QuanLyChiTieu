package com.example.khoa1.myapplication.Model;

import android.graphics.drawable.Drawable;

/**
 * Created by khoa1 on 11/8/2017.
 */

public class Category {

    public Category(int maLoai, String tenLoai, Drawable image) {
        MaLoai = maLoai;
        TenLoai = tenLoai;
        this.image = image;
    }

    public Category() {
    }

    private int MaLoai;
    private String TenLoai;
    Drawable image;

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public int getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(int maLoai) {
        MaLoai = maLoai;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String tenLoai) {
        TenLoai = tenLoai;
    }
}
