package com.example.khoa1.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
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
import com.example.khoa1.myapplication.Model.ThuNhap;

import java.util.ArrayList;

public class ChiTietTaiKhoan extends AppCompatActivity {
    private  FloatingActionButton fabThemHoatDong;
    private ListView lvThuChi;
    private TextView tvBalance;
    private TextView tvDebit;
    private SQLiteThuChi sqLiteThuChi;
    private SQLiteAccount sqLiteAccount;
    private Account account;
    private int MaTaiKhoan = -1;
    private static final int MSG_SET_DATA_SUCCESS = 1;
    private Handler handlerSetDataListview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_tai_khoan);
        setTitle("Chi tiết tài khoản");
        //Set back toolbar button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        tvBalance = (TextView) findViewById(R.id.tvBalance);
        tvDebit = (TextView) findViewById(R.id.tvDebit);
        fabThemHoatDong = (FloatingActionButton) findViewById(R.id.fabThemHoatDong);
        fabThemHoatDong.setOnClickListener(new FabClickListener());
        sqLiteThuChi = new SQLiteThuChi(this);
        sqLiteAccount = new SQLiteAccount(this);
        //Set back toolbar button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        MaTaiKhoan = getIntent().getIntExtra("Ma Tai Khoan", 0);
        account = sqLiteAccount.getAccountByID(MaTaiKhoan);
        tvBalance.setText(String.valueOf(account.getSoTienDu()));
        tvDebit.setText(String.valueOf(account.getSoTienNo()));
        lvThuChi = (ListView) findViewById(R.id.lvThuChi);

        handlerSetDataListview = new Handler()
        {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == MSG_SET_DATA_SUCCESS) {
                    HoatDongAdapter hoatDongAdapter = new HoatDongAdapter(ChiTietTaiKhoan.this, R.layout.chitieu_listview,
                            (ArrayList<HoatDong>) msg.obj);
                    lvThuChi.setAdapter(hoatDongAdapter);
                    lvThuChi.setOnItemClickListener(new ListViewOnItemClickListener());
                }
            }
        };
        setDataListView();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setDataListView();
    }

    private void setDataListView() {
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<HoatDong> arrHoatDong = new ArrayList<HoatDong>();
                arrHoatDong = sqLiteThuChi.getListHoatDongByAccountID(MaTaiKhoan);
                //lấy message từ Main thread

                Message msg = handlerSetDataListview.obtainMessage();
                msg.what = MSG_SET_DATA_SUCCESS;
                msg.obj = arrHoatDong;
                //gửi lại Message này về cho Main Thread
                handlerSetDataListview.sendMessage(msg);
            }
        });
        //kích hoạt tiến trình
        th.start();
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

    //Lớp chứa phương thức handle sự kiện listview item click
    private class ListViewOnItemClickListener implements AdapterView.OnItemClickListener
    {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(ChiTietTaiKhoan.this,ChiTietHoatDong.class);
            //Truyen vao ma chi tieu cho Activity Chi Tiet Hoat Dong
            if(adapterView.getItemAtPosition(i).getClass() == ChiTieu.class)
                intent.putExtra("Ma Chi Tieu",((ChiTieu)adapterView.getItemAtPosition(i)).getMaHoatDong());
            else
                intent.putExtra("Ma Thu Nhap",((ThuNhap)adapterView.getItemAtPosition(i)).getMaHoatDong());
            startActivity(intent);
        }
    }

    private class FabClickListener implements FloatingActionButton.OnClickListener
    {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ChiTietTaiKhoan.this, ThemHoatDong.class);
            intent.putExtra("MaTaiKhoan", account);
            startActivityForResult(intent, 0);
        }
    }
}
