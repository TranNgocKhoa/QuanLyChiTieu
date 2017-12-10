package com.example.khoa1.myapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

import com.example.khoa1.myapplication.Model.Account;
import com.example.khoa1.myapplication.Model.Category;
import com.example.khoa1.myapplication.Model.DanhGia;

import java.util.ArrayList;

/**
 * Created by khoa1 on 11/19/2017.
 */

public class SQLiteDanhGia extends SQLiteDataController {
    public SQLiteDanhGia(Context con) {
        super(con);
    }

    public ArrayList<DanhGia> getListDanhGia() {
        ArrayList<DanhGia> arrDanhGia = new ArrayList<>();

        try {
            openDataBase();
            Cursor cs = database.rawQuery("SELECT *\n" +
                            "FROM DanhGia\n"
                    , null);

            while (cs.moveToNext()) {
                int MaDanhGia = cs.getInt(0);
                String HinhAnh = cs.getString(1);
                float KinhDo = cs.getFloat(2);
                float ViDo = cs.getFloat(3);
                int DanhGia = cs.getInt(4);

                DanhGia danhGia = new DanhGia(MaDanhGia, HinhAnh,  KinhDo, ViDo, DanhGia);
                arrDanhGia.add(danhGia);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return arrDanhGia;
    }

    public DanhGia getDanhGiaByID(int ID) {
        DanhGia danhGia = null;
        try {
            openDataBase();
            Cursor cs = database.rawQuery("SELECT *\n" +
                            "FROM DanhGia\n" +
                            "WHERE MaDanhGia = " + String.valueOf(ID)
                    , null);

            while (cs.moveToNext()) {
                int MaDanhGia = cs.getInt(0);
                String HinhAnh = cs.getString(1);
                float KinhDo = cs.getFloat(2);
                float ViDo = cs.getFloat(3);
                int DanhGia = cs.getInt(4);
                danhGia = new DanhGia(MaDanhGia, HinhAnh,  KinhDo, ViDo, DanhGia);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        if(danhGia != null)
            return danhGia;
        else return null;
    }




    public boolean addDanhGia(DanhGia danhGia){
        boolean result = false;
        try {

            openDataBase();
            ContentValues values = new ContentValues();
            values.put("HinhAnh", danhGia.getHinhAnh());
            values.put("KinhDo", danhGia.getLongtitude());
            values.put("ViDo", danhGia.getLatitude());
            values.put("DanhGia", danhGia.getRate());
            //  values.put("SoTienNo", account.getAccountType().getId());
            long rs = database.insert("DanhGia", null, values);
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
    public boolean updateDanhGia(DanhGia danhGia){
        boolean result = false;
        try {

            openDataBase();
            ContentValues values = new ContentValues();
            values.put("HinhAnh", danhGia.getHinhAnh());
            values.put("KinhDo", danhGia.getLongtitude());
            values.put("ViDo", danhGia.getLatitude());
            values.put("DanhGia", danhGia.getRate());
            //  values.put("SoTienNo", account.getAccountType().getId());
            long rs = database.update("DanhGia", values, "MaDanhGia=?" , new String[]{String.valueOf(danhGia.getMaDanhGia())});
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


    public int getLastDanhGia()
    {
        int maDanhGia = -1;
        try {

            openDataBase();
             Cursor cs = database.rawQuery("SELECT * from DanhGia order by MaDanhGia DESC limit 1", null);
            while (cs.moveToNext()) {
                maDanhGia = cs.getInt(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return  maDanhGia;
    }


}
