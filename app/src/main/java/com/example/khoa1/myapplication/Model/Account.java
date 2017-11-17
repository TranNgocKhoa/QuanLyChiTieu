package com.example.khoa1.myapplication.Model;

/**
 * Created by khoa1 on 10/31/2017.
 */

public class Account {
    private int maTaiKhoan;
    private String tenTaiKhoan;
    private double soTienDu;
    private double soTienNo;
    private int picture;

    public int getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public void setMaTaiKhoan(int maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public double getSoTienDu() {
        return soTienDu;
    }

    public void setSoTienDu(double soTienDu) {
        this.soTienDu = soTienDu;
    }

    public double getSoTienNo() {
        return soTienNo;
    }

    public void setSoTienNo(double soTienNo) {
        this.soTienNo = soTienNo;
    }

    public Account(int maTaiKhoan, String tenTaiKhoan, double soTienDu, double soTienNo, int picture) {
        this.maTaiKhoan = maTaiKhoan;
        this.tenTaiKhoan = tenTaiKhoan;
        this.soTienDu = soTienDu;
        this.soTienNo = soTienNo;
        this.picture = picture;
    }
    public int getPicture() {
        return picture;
    }
    public void setPicture(int picture) {
        this.picture = picture;
    }


}
