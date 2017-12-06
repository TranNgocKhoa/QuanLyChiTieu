package com.example.khoa1.myapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

import com.example.khoa1.myapplication.Model.Category;
import com.example.khoa1.myapplication.Model.CategoryCount;
import com.example.khoa1.myapplication.Model.ChiTieu;
import com.example.khoa1.myapplication.Model.HoatDong;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by khoa1 on 11/19/2017.
 */

public class SQLiteCategory extends SQLiteDataController {

    public SQLiteCategory(Context con) {
        super(con);
    }
    public ArrayList<CategoryCount> getListCategoryCount() {
        ArrayList<CategoryCount> arrCategory = new ArrayList<>();

        try {
            openDataBase();
            Cursor cs = database.rawQuery("SELECT LoaiChiTien.MaLoai," +
                            "LoaiChiTien.TenLoai,LoaiChiTien.Image,Count(LoaiChiTien.TenLoai),Sum(ChiTien.SoTienChi)\n" +
                            "FROM ChiTien,LoaiChiTien\n" +
                            "Where ChiTien.MaLoaiChiTien=LoaiChiTien.MaLoai\n" +
                            "GROUP BY LoaiChiTien.TenLoai"
                    , null);

            while (cs.moveToNext()) {
                int MaChiTien = cs.getInt(0);
                String TenLoai = cs.getString(1);
                int Image = cs.getInt(2);
                int SoLuong= cs.getInt(3);
                int TongTien= cs.getInt(4);
                Log.d("Infor",TenLoai+' ' +Integer.toString(TongTien));
                CategoryCount cat = new CategoryCount(MaChiTien, TenLoai, Image,SoLuong,TongTien);
                arrCategory.add(cat);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return arrCategory;
    }
    public ArrayList<Category> getListCategorybyLoaiChiTieu(String TenLoaiChiTieu) {
        ArrayList<Category> arrCategory = new ArrayList<>();

        try {
            openDataBase();
            Cursor cs = database.rawQuery("SELECT LoaiChiTien.MaLoai,LoaiChiTien.TenLoai,LoaiChiTien.Image\n" +
                            "FROM ChiTien,LoaiChiTien\n" +
                            "Where ChiTien.MaLoaiChiTien=LoaiChiTien.MaLoai and TenLoai='" + TenLoaiChiTieu + "'\n" +
                            "GROUP BY LoaiChiTien.TenLoai"
                    , null);

            while (cs.moveToNext()) {
                int MaChiTien = cs.getInt(0);
                String TenLoai = cs.getString(1);
                int Image = cs.getInt(2);
                Log.d("Infor",TenLoai);
                Category cat = new Category(MaChiTien, TenLoai, Image);
                arrCategory.add(cat);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return arrCategory;
    }
    public ArrayList<Category> getListCategoryChiTieu() {
        ArrayList<Category> arrCategory = new ArrayList<>();

        try {
            openDataBase();
            Cursor cs = database.rawQuery("SELECT *\n" +
                            "FROM LoaiChiTien\n"
                    , null);

            while (cs.moveToNext()) {
                int MaChiTien = cs.getInt(0);
                String TenLoai = cs.getString(1);
                int Image = cs.getInt(2);
                Category cat = new Category(MaChiTien, TenLoai, Image);
                arrCategory.add(cat);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return arrCategory;
    }

    public ArrayList<Category> getListCategoryThuNhap() {
        ArrayList<Category> arrCategory = new ArrayList<>();

        try {
            openDataBase();
            Cursor cs = database.rawQuery("SELECT *\n" +
                            "FROM LoaiThuTien\n", null);

            while (cs.moveToNext()) {
                int MaThuTien = cs.getInt(0);
                String TenLoai = cs.getString(1);
                int Image = cs.getInt(2);
                Category cat = new Category(MaThuTien, TenLoai, Image);
                arrCategory.add(cat);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return arrCategory;
    }

    public Category getCategoryChiTieuByID(int ID) {
        Category cat = null;
           try {
            openDataBase();
            Cursor cs = database.rawQuery("SELECT *\n" +
                            "FROM LoaiChiTien\n" +
                    "WHERE MaLoai = " + String.valueOf(ID)
                    , null);

            while (cs.moveToNext()) {
                int MaChiTien = cs.getInt(0);
                String TenLoai = cs.getString(1);
                int Image = cs.getInt(2);
                cat = new Category(MaChiTien, TenLoai, Image);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        if(cat != null)
            return cat;
        else return null;
    }

    public boolean addThuNhapCategory(Category thuNhapCat)
    {
        boolean result = false;
        try {
            openDataBase();
            ContentValues values = new ContentValues();
            values.put("TenLoai", thuNhapCat.getTenLoai());
            values.put("Image", thuNhapCat.getImage());

            long rs = database.insert("LoaiThuTien", null, values);
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
    public boolean addChiTieuCategory(Category chiTieuCat)
    {
        boolean result = false;
        try {
            openDataBase();
            ContentValues values = new ContentValues();
            values.put("TenLoai", chiTieuCat.getTenLoai());
            values.put("Image", chiTieuCat.getImage());

            long rs = database.insert("LoaiChiTien", null, values);
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

    public Category getCategoryThuNhapByID(int ID) {
        Category cat = null;
        try {
            openDataBase();
            Cursor cs = database.rawQuery("SELECT *\n" +
                            "FROM LoaiThuTien\n" +
                            "WHERE MaLoaiThuTien = " + String.valueOf(ID)
                    , null);

            while (cs.moveToNext()) {
                int MaChiTien = cs.getInt(0);
                String TenLoai = cs.getString(1);
                int Image = cs.getInt(2);
                cat = new Category(MaChiTien, TenLoai, Image);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        if(cat != null)
            return cat;
        else return null;
    }
}
