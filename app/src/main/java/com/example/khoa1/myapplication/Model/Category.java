package com.example.khoa1.myapplication.Model;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by khoa1 on 11/8/2017.
 */

public class Category implements Serializable {

    public Category(int maLoai, String tenLoai, int image) {
        MaLoai = maLoai;
        TenLoai = tenLoai;
        this.image = image;
    }

    public Category() {
    }

    private int MaLoai;
    private String TenLoai;
    int image;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
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
