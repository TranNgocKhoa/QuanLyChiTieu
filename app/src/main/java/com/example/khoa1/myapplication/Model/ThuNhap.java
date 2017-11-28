package com.example.khoa1.myapplication.Model;

import java.util.Date;

/**
 * Created by khoa1 on 11/13/2017.
 */

public class ThuNhap extends HoatDong {

    public ThuNhap(int maHoatDong, double soTien, Date ngay, Category category, String tieuDe, String noiDung, Account taiKhoan) {
        super(maHoatDong, soTien, ngay, category, tieuDe, noiDung, taiKhoan);
    }
}
