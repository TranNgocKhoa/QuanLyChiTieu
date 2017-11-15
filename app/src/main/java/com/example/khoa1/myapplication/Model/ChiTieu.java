package com.example.khoa1.myapplication.Model;

/**
 * Created by khoa1 on 11/8/2017.
 */

public class ChiTieu extends HoatDong{
    public ChiTieu(int maHoatDong, int soTien, Category category, String tieuDe, String noiDung,
                   DanhGia danhGia, Account taiKhoan)
    {
        super(maHoatDong, soTien, category, tieuDe, noiDung, taiKhoan);
        this.danhGia = danhGia;
    }

    private DanhGia danhGia;

    public DanhGia getDanhGia() {
        return danhGia;
    }

    public void setDanhGia(DanhGia danhGia) {
        this.danhGia = danhGia;
    }
}
