package com.example.khoa1.myapplication.Model;

/**
 * Created by khoa1 on 11/8/2017.
 */

public class DanhGia {
    public DanhGia() {
    }

    public DanhGia(int maDanhGia, float longtitude, float latitude, int rate) {

        this.maDanhGia = maDanhGia;
        this.longtitude = longtitude;
        this.latitude = latitude;
        this.rate = rate;
    }

    private int maDanhGia;
    private float longtitude;
    private float latitude;
    private int rate;

    public int getMaDanhGia() {
        return maDanhGia;
    }

    public void setMaDanhGia(int maDanhGia) {
        this.maDanhGia = maDanhGia;
    }

    public float getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(float longtitude) {
        this.longtitude = longtitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
