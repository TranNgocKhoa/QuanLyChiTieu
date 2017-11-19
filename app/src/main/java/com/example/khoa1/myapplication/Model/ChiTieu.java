package com.example.khoa1.myapplication.Model;

import java.util.Date;

/**
 * Created by khoa1 on 11/8/2017.
 */

public class ChiTieu extends HoatDong{

    private DanhGia danhGia;

    public ChiTieu(int maHoatDong, int soTien, Date date, Category category, String tieuDe, String noiDung, Account taiKhoan, DanhGia danhGia) {
        super(maHoatDong, soTien,date ,category, tieuDe, noiDung, taiKhoan);
        this.danhGia = danhGia;
    }

    public DanhGia getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(DanhGia danhGia) {
        this.danhGia = danhGia;
    }
}
