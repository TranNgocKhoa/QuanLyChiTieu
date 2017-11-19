package com.example.khoa1.myapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

import com.example.khoa1.myapplication.Model.Account;

import java.util.ArrayList;

/**
 * Created by Tri Nha on 11/13/2017.
 */

public class SQLiteAccount extends SQLiteDataController{
    public SQLiteAccount(Context con) {
        super(con);
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

    public void DeleteAllAccount(){
        ArrayList<Account> ListAccount= new ArrayList<Account>();

            openDataBase();
            Cursor cs = database.rawQuery("Delete from TaiKhoan"
                    ,null);

    }

    public boolean addAccount(Account account){
        boolean result = false;
        try {

            openDataBase();
            ContentValues values = new ContentValues();
            values.put("TenTaiKhoan", account.getTenTaiKhoan());
            values.put("SoTienDu", account.getSoTienDu());
            values.put("SoTienNo", account.getSoTienNo());
            values.put("HinhAnh", account.getPicture());
          //  values.put("SoTienNo", account.getAccountType().getId());
            long rs = database.insert("TaiKhoan", null, values);
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
    public boolean updateAccount(Account account){
        boolean result = false;
        try {

            openDataBase();
            ContentValues values = new ContentValues();
            values.put("TenTaiKhoan", account.getTenTaiKhoan());
            values.put("SoTienDu", account.getSoTienDu());
            values.put("SoTienNo", account.getSoTienNo());
            values.put("HinhAnh", account.getPicture());
            //values.put("MaLoaiTaiKhoan", account.getAccountType().getId());
            int rs = database.update("TaiKhoan",values,"MaTaiKhoan="+ account.getMaTaiKhoan(),null);
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
    public boolean deleteAccount(int id){
        boolean result = false;
        try {

            openDataBase();
            //
            int rs = database.delete("TaiKhoan", "MaTaiKhoan=" + id, null);
            if (rs > 0) {
                result = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return result;
    }
    public Account getAccountByID(int ID)
    {
        Account account = null;
        try{
            openDataBase();
            String sql = "SELECT *\n" +
                    "FROM TaiKhoan\n"+
                    "WHERE TaiKhoan.MaTaiKhoan ="+String.valueOf(ID);
            Cursor cs = database.rawQuery(sql
                    ,null);
            // if(cs.moveToFirst())
            while (cs.moveToNext()) {
                account = new Account(cs.getInt(0), cs.getString(1), cs.getDouble(2), cs.getDouble(3),cs.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return account;
    }

    public int getIDAccountbyName(String name){
        int id=-1;
        try{
            openDataBase();
            Cursor cs = database.rawQuery("SELECT *\n" +
                            "FROM TaiKhoan\n" +
                    "Where TenTaiKhoan = '" +name + "'"
                    ,null);

            while (cs.moveToNext()) {
                id=cs.getInt(0);
                Log.d("aaa",cs.getString(0));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return id;
    }

}
