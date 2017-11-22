package com.example.khoa1.myapplication.Model;

/**
 * Created by khoa1 on 11/8/2017.
 */

public class DanhGia {
    private int maDanhGia;
    private byte[] hinhAnh;
    private float longtitude;
    private float latitude;
    private int rate;

    public DanhGia(int maDanhGia, byte[] hinhAnh, float longtitude, float latitude, int rate) {

        this.maDanhGia = maDanhGia;
        this.hinhAnh = hinhAnh;
        this.longtitude = longtitude;
        this.latitude = latitude;
        this.rate = rate;
    }

    public DanhGia() {
    }

    public byte[] getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(byte[] hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

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
