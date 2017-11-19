package com.example.khoa1.myapplication.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

import com.example.khoa1.myapplication.Model.Account;
import com.example.khoa1.myapplication.Model.Category;
import com.example.khoa1.myapplication.Model.ChiTieu;
import com.example.khoa1.myapplication.Model.HoatDong;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Tri Nha on 11/18/2017.
 */

public class SQLiteThuChi extends SQLiteDataController{
    public SQLiteThuChi(Context con) {
        super(con);
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
        SQLiteAccount accountsql=null;
        try{
            openDataBase();
            Cursor cs = database.rawQuery("SELECT *\n" +
                            "FROM ChiTien\n"
                    ,null);
            ChiTieu chiTieu;
            DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
            Date date;
            while (cs.moveToNext()) {

                chiTieu = new ChiTieu(cs.getInt(0), cs.getInt(1), null, listLoaiThuChi.get(cs.getInt(3)),cs.getString(4),cs.getString(5),accountsql.);
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
}
