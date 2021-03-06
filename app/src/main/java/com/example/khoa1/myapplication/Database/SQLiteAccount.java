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

                account = new Account(cs.getInt(0), cs.getString(1), cs.getInt(2), cs.getInt(3),cs.getBlob(5),null);
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
    public boolean addAccount(Account account){
        boolean result = false;
        try {

            openDataBase();
            ContentValues values = new ContentValues();
            values.put("TenTaiKhoan", account.getName());
            values.put("SoTienDu", account.getBalance());
            values.put("SoTienNo", account.getDebit());
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
            values.put("TenTaiKhoan", account.getName());
            values.put("SoTienDu", account.getBalance());
            values.put("SoTienNo", account.getDebit());
            values.put("HinhAnh", account.getPicture());
            //values.put("MaLoaiTaiKhoan", account.getAccountType().getId());
            int rs = database.update("TaiKhoan",values,"MaTaiKhoan="+ account.getId(),null);
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
