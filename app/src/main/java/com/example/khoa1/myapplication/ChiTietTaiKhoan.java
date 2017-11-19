package com.example.khoa1.myapplication;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.khoa1.myapplication.Adapter.ChiTieuAdapter;
import com.example.khoa1.myapplication.Adapter.HoatDongAdapter;
import com.example.khoa1.myapplication.Database.SQLiteAccount;
import com.example.khoa1.myapplication.Database.SQLiteThuChi;
import com.example.khoa1.myapplication.Model.Account;
import com.example.khoa1.myapplication.Model.Category;
import com.example.khoa1.myapplication.Model.ChiTieu;
import com.example.khoa1.myapplication.Model.HoatDong;

import java.util.ArrayList;

public class ChiTietTaiKhoan extends AppCompatActivity {
    ListView lvThuChi;
    TextView tvBalance;
    TextView tvDebit;
    SQLiteThuChi sqLiteThuChi;
    SQLiteAccount sqLiteAccount;
    int MaTaiKhoan = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_tai_khoan);
        tvBalance = (TextView) findViewById(R.id.tvBalance);
        tvDebit = (TextView) findViewById(R.id.tvDebit);
        sqLiteThuChi = new SQLiteThuChi(this);
        sqLiteAccount = new SQLiteAccount(this);
        //Set back toolbar button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        MaTaiKhoan = getIntent().getIntExtra("Ma Tai Khoan", 0);
        Account account = sqLiteAccount.getAccountByID(MaTaiKhoan);
        tvBalance.setText(String.valueOf(account.getSoTienDu()));
        tvDebit.setText(String.valueOf(account.getSoTienNo()));
        setTitle("Chi tiết tài khoản");
        lvThuChi = (ListView) findViewById(R.id.lvThuChi);
//        lvChiTieu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//            }
//        });


        ArrayList<HoatDong> arrHoatDong = new ArrayList<HoatDong>();
        arrHoatDong = sqLiteThuChi.getListHoatDongByAccountID(MaTaiKhoan);

        HoatDongAdapter hoatDongAdapter = new HoatDongAdapter(this, R.layout.chitieu_listview, arrHoatDong);
        lvThuChi.setAdapter(hoatDongAdapter);
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
