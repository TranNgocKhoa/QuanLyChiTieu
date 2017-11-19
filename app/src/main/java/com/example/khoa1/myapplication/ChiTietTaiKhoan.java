package com.example.khoa1.myapplication;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.khoa1.myapplication.Adapter.ChiTieuAdapter;
import com.example.khoa1.myapplication.Database.SQLiteAccount;
import com.example.khoa1.myapplication.Database.SQLiteDataController;
import com.example.khoa1.myapplication.Database.SQLiteThuChi;
import com.example.khoa1.myapplication.Model.Account;
import com.example.khoa1.myapplication.Model.Category;
import com.example.khoa1.myapplication.Model.ChiTieu;
import com.example.khoa1.myapplication.Model.HoatDong;

import java.io.IOException;
import java.util.ArrayList;

public class ChiTietTaiKhoan extends AppCompatActivity {
    ListView lvThuChi;
    ArrayList ListThuChi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_tai_khoan);
        //Set back toolbar button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent intent = getIntent();
        int id= intent.getIntExtra("Ma Tai Khoan",-1);
        Log.d("ma tai khoan",Integer.toString(id));
        setTitle("Chi tiết tài khoản");
        createDB();
        lvThuChi = (ListView) findViewById(R.id.lvThuChi);
        ArrayList<ChiTieu> arrChiTieu = new ArrayList<ChiTieu>();
        ChiTieuAdapter chiTieuAdapter = new ChiTieuAdapter(this, R.layout.chitieu_listview, ListThuChi);
        lvThuChi.setAdapter(chiTieuAdapter);
    }


    private void createDB() {
        // khởi tạo database
        SQLiteDataController sql = new SQLiteDataController(this);
        try {
            sql.isCreatedDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void getListaccount(int id) {
        SQLiteThuChi listThuChi = new SQLiteThuChi(this);
        ListThuChi = new ArrayList<HoatDong>();
        //account.DeleteAllAccount();
        ListThuChi = listThuChi.getListThuChibyAccount(id);

    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if(id == android.R.id.home)
        {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
