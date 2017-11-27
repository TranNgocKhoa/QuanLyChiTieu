package com.example.khoa1.myapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.example.khoa1.myapplication.Model.Account;
import com.example.khoa1.myapplication.Model.Category;
import com.example.khoa1.myapplication.Model.ChiTieu;
import com.example.khoa1.myapplication.Model.HoatDong;
import com.example.khoa1.myapplication.Model.ThuNhap;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by Tri Nha on 11/18/2017.
 */

public class SQLiteThuChi extends SQLiteDataController{
    SQLiteAccount sqLiteAccount;
    SQLiteCategory sqLiteCategory;
    SQLiteDanhGia sqLiteDanhGia;
    Context context;
    public SQLiteThuChi(Context con)
    {
        super(con);
        sqLiteCategory = new SQLiteCategory(con);
        sqLiteAccount = new SQLiteAccount(con);
        sqLiteDanhGia = new SQLiteDanhGia(con);
        this.context = con;
    }

    public ArrayList<ThuNhap> getListThuNhap(){
        ArrayList<ThuNhap> listThuNhap = new ArrayList<>();
        try{
            openDataBase();
            Cursor cs = database.rawQuery("select * from ThuTien",null);
            ThuNhap thuNhap;
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            Date date;
            while (cs.moveToNext()) {
                thuNhap = new ThuNhap(cs.getInt(0),
                        cs.getDouble(2),
                        df.parse(cs.getString(3)), sqLiteCategory.getCategoryThuNhapByID(cs.getInt(1)),
                        cs.getString(5),cs.getString(4),
                        sqLiteAccount.getAccountByID(cs.getInt(6)));
                Log.d("aaa",cs.getString(0));
                listThuNhap.add(thuNhap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return listThuNhap;
    }


    public ArrayList<ChiTieu> getListChiTieu(){
        ArrayList<ChiTieu> listChiTieu = new ArrayList<>();
        try{
            openDataBase();
            Cursor cs = database.rawQuery("select * from ChiTien",null);
            ChiTieu chiTieu;
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            Date date;
            while (cs.moveToNext()) {
                chiTieu = new ChiTieu(cs.getInt(0),
                        cs.getDouble(2),
                        df.parse(cs.getString(3)),
                        sqLiteCategory.getCategoryThuNhapByID(cs.getInt(1)),
                        cs.getString(5),cs.getString(4),
                        sqLiteAccount.getAccountByID(cs.getInt(6)), null);
                Log.d("aaa",cs.getString(0));
                listChiTieu.add(chiTieu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return listChiTieu;
    }

    public ArrayList<HoatDong> getListHoatDongByAccountID(int ID) {
        ArrayList<ChiTieu> arrChiTieu = new ArrayList<>();
        ArrayList<ThuNhap> arrThuNhap = new ArrayList<>();
        try {
            openDataBase();
            Cursor cs = database.rawQuery("SELECT *\n" +
                            "FROM ChiTien\n" +
                    "WHERE MaTaiKhoan = " + String.valueOf(ID)
                    , null);

            while (cs.moveToNext()) {
                int MaChiTien = cs.getInt(0);
                int MaLoaiChiTien = cs.getInt(1);
                double SoTienChi = cs.getDouble(2);
                Date Ngay = new Date(cs.getLong(3));
                String ChiTiet = cs.getString(4);
                String TieuDe = cs.getString(5);
                int MaDanhGia = cs.getInt(6);
                int MaTaiKhoan = cs.getInt(7);
                ChiTieu hd = new ChiTieu(MaChiTien, SoTienChi,Ngay,  sqLiteCategory.getCategoryChiTieuByID(MaLoaiChiTien),
                        TieuDe, ChiTiet, sqLiteAccount.getAccountByID(MaTaiKhoan), sqLiteDanhGia.getDanhGiaByID(MaDanhGia));

                arrChiTieu.add(hd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        try {
            openDataBase();
            Cursor cs = database.rawQuery("SELECT *\n" +
                            "FROM ThuTien\n" +
                            "WHERE MaTaiKhoan = " + String.valueOf(ID)
                    , null);

            while (cs.moveToNext()) {
                int MaThuTien = cs.getInt(0);
                int MaLoaiThuTien = cs.getInt(1);
                double SoTienThu= cs.getDouble(2);
                Date Ngay = new Date(cs.getLong(3));
                String ChiTiet = cs.getString(4);
                String TieuDe = cs.getString(5);
                int MaTaiKhoan = cs.getInt(7);
                ThuNhap hd = new ThuNhap(MaLoaiThuTien, SoTienThu,Ngay, sqLiteCategory.getCategoryThuNhapByID(MaLoaiThuTien),
                        TieuDe, ChiTiet, sqLiteAccount.getAccountByID(MaTaiKhoan));

                arrThuNhap.add(hd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }



        ArrayList<HoatDong> arrHoatDong = new ArrayList<HoatDong>();
        arrHoatDong.addAll(arrChiTieu);
        arrHoatDong.addAll(arrThuNhap);
        Collections.sort(arrHoatDong, new Comparator<HoatDong>() {
            @Override
            public int compare(HoatDong hoatDong, HoatDong t1) {
                return hoatDong.getNgay().compareTo(t1.getNgay());
            }
        });
        return  arrHoatDong;
    }



    public ArrayList<Category> getListLoaiThuChi(){

        ArrayList<Category> listLoaiThuChi= new ArrayList<>();
        try{
            openDataBase();
            Cursor cs = database.rawQuery("SELECT *\n" +
                            "FROM LoaiChiTien\n"
                    ,null);

            Category category;
            while (cs.moveToNext()) {
                category = new Category(cs.getInt(0), cs.getString(1),cs.getInt(2));
                Log.d("aaa",cs.getString(1));
                listLoaiThuChi.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return listLoaiThuChi;
    }
    public ArrayList<Account> getListAccount(){
        ArrayList<Account> ListAccount= new ArrayList<Account>();
        try{
            openDataBase();
            Cursor cs = database.rawQuery("SELECT *\n" +
                            "FROM TaiKhoan\n"
                    ,null);
            Account account;
            while (cs.moveToNext()) {
                account = new Account(cs.getInt(0), cs.getString(1), cs.getDouble(2), cs.getDouble(3),cs.getInt(4));
                Log.d("aaa",cs.getString(0));
                ListAccount.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return ListAccount;
    }
    public ArrayList<HoatDong> getListThuChibyAccount(int IDAccount){
        ArrayList<HoatDong> listchiTieu= new ArrayList<>();
        ArrayList<Category> listLoaiThuChi =  getListLoaiThuChi();
        ArrayList<Account> listAccount = getListAccount();
        try{
            openDataBase();
            Cursor cs = database.rawQuery("SELECT *\n" +
                            "FROM ChiTien\n"
                    ,null);
            ChiTieu chiTieu;
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            Date date;
            while (cs.moveToNext()) {

                chiTieu = new ChiTieu(cs.getInt(0),
                        cs.getInt(1),
                        null, listLoaiThuChi.get(cs.getInt(3)),
                        cs.getString(4),cs.getString(5),
                        sqLiteAccount.getAccountByID(IDAccount),null);
                Log.d("aaa",cs.getString(0));
                listchiTieu.add(chiTieu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return listchiTieu;
    }


    public boolean addChiTieu(ChiTieu chiTieu)
    {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        boolean result = false;
        try {

            openDataBase();
            sqLiteDanhGia.addDanhGia(chiTieu.getDanhGia());
            int maDanhGia = sqLiteDanhGia.getLastDanhGia();
            ContentValues values = new ContentValues();
            values.put("MaLoaiChiTien", chiTieu.getCategory().getMaLoai());
            values.put("SoTienChi", chiTieu.getSoTien());
            values.put("Ngay", df.format(chiTieu.getNgay()));
            values.put("ChiTiet", chiTieu.getNoiDung());
            values.put("TieuDe", chiTieu.getTieuDe());
            values.put("MaDanhGia", maDanhGia);
            values.put("MaTaiKhoan", chiTieu.getTaiKhoan().getMaTaiKhoan());
            //  values.put("SoTienNo", account.getAccountType().getId());
            long rs = database.insert("ChiTien", null, values);
            if (rs > 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        Log.d("aaa","finish");
        return result;
    }

    public boolean addThuNhap(ThuNhap thuNhap)
    {
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        boolean result = false;
        try {

            openDataBase();
            ContentValues values = new ContentValues();
            values.put("MaLoaiThuTien", thuNhap.getCategory().getMaLoai());
            values.put("SoTienThu", thuNhap.getSoTien());
            values.put("Ngay", df.format(thuNhap.getNgay()));
            values.put("ChiTiet", thuNhap.getNoiDung());
            values.put("TieuDe", thuNhap.getTieuDe());
            values.put("MaTaiKhoan", thuNhap.getTaiKhoan().getMaTaiKhoan());
            //  values.put("SoTienNo", account.getAccountType().getId());
            long rs = database.insert("ThuTien", null, values);
            if (rs > 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        Log.d("aaa","finish");
        return result;
    }

}
