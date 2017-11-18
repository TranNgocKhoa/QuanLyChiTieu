package com.example.khoa1.myapplication.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.util.Log;

import com.example.khoa1.myapplication.Model.Account;
import com.example.khoa1.myapplication.Model.ChiTieu;

import java.util.ArrayList;

/**
 * Created by Tri Nha on 11/18/2017.
 */

public class SQLiteThuChi extends SQLiteDataController{
    public SQLiteThuChi(Context con) {
        super(con);
    }
    public ArrayList<ChiTieu> getListChiTieubyAccount(){
        ArrayList<ChiTieu> listchiTieu= new ArrayList<ChiTieu>();
//        try{
//            openDataBase();
//            Cursor cs = database.rawQuery("SELECT *\n" +
//                            "FROM TaiKhoan\n"
//                    ,null);
//            Account account;
//            while (cs.moveToNext()) {
//                account = new Account(cs.getInt(0), cs.getString(1), cs.getDouble(2), cs.getDouble(3),cs.getInt(4));
//                Log.d("aaa",cs.getString(0));
//                ListAccount.add(account);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            close();
//        }

        return listchiTieu;
    }
}
