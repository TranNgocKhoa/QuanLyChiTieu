package com.example.khoa1.myapplication.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;

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
                byte[] HinhAnh = cs.getBlob(1);
                float KinhDo = cs.getFloat(2);
                float ViDo = cs.getFloat(3);
                int DanhGia = cs.getInt(4);
                String ChiTiet = cs.getString(5);

                DanhGia danhGia = new DanhGia(MaDanhGia, HinhAnh,  KinhDo, ViDo, DanhGia, ChiTiet);
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
                byte[] HinhAnh = cs.getBlob(1);
                float KinhDo = cs.getFloat(2);
                float ViDo = cs.getFloat(3);
                int DanhGia = cs.getInt(4);
                String ChiTiet = cs.getString(5);
                danhGia = new DanhGia(MaDanhGia, HinhAnh,  KinhDo, ViDo, DanhGia, ChiTiet);
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
}
