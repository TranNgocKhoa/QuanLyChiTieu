package com.example.khoa1.myapplication.Model;

/**
 * Created by Tri Nha on 11/29/2017.
 */

public class CategoryCount extends Category {
    private int SoLuong;
    private int TongTien;

    public CategoryCount(int maLoai, String tenLoai, int image, int SoLuong, int TongTien) {
        super(maLoai, tenLoai, image);
        this.SoLuong= SoLuong;
        this.TongTien=TongTien;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public int getTongTien() {
        return TongTien;
    }

    public void setTongTien(int tongTien) {
        TongTien = tongTien;
    }
}
