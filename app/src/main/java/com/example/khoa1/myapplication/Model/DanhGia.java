package com.example.khoa1.myapplication.Model;

/**
 * Created by khoa1 on 11/8/2017.
 */

public class DanhGia {
    private int maDanhGia;
    private float longtitude;
    private float latitude;
    private int rate;
    private ChiTieu chiTieu;
    private String chiTiet;

    public String getChiTiet() {
        return chiTiet;
    }

    public void setChiTiet(String chiTiet) {
        this.chiTiet = chiTiet;
    }

    public DanhGia(int maDanhGia, float longtitude, float latitude, int rate, ChiTieu chiTieu, String chiTiet) {

        this.maDanhGia = maDanhGia;
        this.longtitude = longtitude;
        this.latitude = latitude;
        this.rate = rate;
        this.chiTieu = chiTieu;
        this.chiTiet = chiTiet;
    }

    public DanhGia() {
    }

    public ChiTieu getChiTieu() {
        return chiTieu;
    }

    public void setChiTieu(ChiTieu chiTieu) {
        this.chiTieu = chiTieu;
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
