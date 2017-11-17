package com.example.khoa1.myapplication.Model;

/**
 * Created by khoa1 on 11/8/2017.
 */
//Test commit khoa branch
public abstract class HoatDong {

    private int maHoatDong;
    private double soTien;
    private Category category;
    private String tieuDe;
    private String noiDung;
    private Account taiKhoan;


    public HoatDong(int maHoatDong, int soTien, Category category, String tieuDe, String noiDung, Account taiKhoan) {
        this.maHoatDong = maHoatDong;
        this.soTien = soTien;
        this.category = category;
        this.tieuDe = tieuDe;
        this.noiDung = noiDung;
        this.taiKhoan = taiKhoan;
    }

    public int getMaHoatDong() {
        return maHoatDong;
    }



    public void setMaHoatDong(int maHoatDong) {
        this.maHoatDong = maHoatDong;
    }

    public double getSoTien() {
        return soTien;
    }

    public void setSoTien(int soTien) {
        this.soTien = soTien;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Account getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(Account taiKhoan) {
        this.taiKhoan = taiKhoan;
    }
}
