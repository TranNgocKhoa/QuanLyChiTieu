package com.example.khoa1.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.khoa1.myapplication.Adapter.AccountAdapter;
import com.example.khoa1.myapplication.Database.SQLiteAccount;
import com.example.khoa1.myapplication.Model.Account;

import java.util.ArrayList;

public class ChonTaiKhoan extends AppCompatActivity {
    private ListView lvAccount;
    private ArrayList<Account> listAccount;
    private SQLiteAccount account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_tai_khoan);
        account = new SQLiteAccount(this);
        account = new SQLiteAccount(this);
        lvAccount = (ListView) findViewById(R.id.lv_Account);
        listAccount = account.getListAccount();
        AccountAdapter accountAdaper = new AccountAdapter(this, R.layout.account_listview, listAccount);
        lvAccount.setAdapter(accountAdaper);
        lvAccount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ChonTaiKhoan.this, ThemHoatDong.class);
                //Truyen vao ma chi tieu cho Activity Chi Tiet Hoat Dong
                intent.putExtra("Tai Khoan", ((Account) adapterView.getItemAtPosition(i)));
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        registerForContextMenu(lvAccount);
    }
}
