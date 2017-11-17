package com.example.khoa1.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.khoa1.myapplication.Adapter.AccountAdapter;
import com.example.khoa1.myapplication.Adapter.IconAccountAdapter;
import com.example.khoa1.myapplication.Database.SQLiteAccount;
import com.example.khoa1.myapplication.Model.IconAccount;

import java.util.ArrayList;

public class ChonIconTaiKhoan extends AppCompatActivity {
    ListView lvIcon;
    ArrayList<IconAccount> listItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_icon_tai_khoan);
        lvIcon =(ListView)findViewById(R.id.lvICon);
        lvIcon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id = ((IconAccount)adapterView.getItemAtPosition(i)).getDrawableID();
                Intent intent = new Intent();
                intent.putExtra("Image", id);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        IconAccount acc1 =new IconAccount(R.drawable.bank_account, "Tài khoản Ngân hàng");
        IconAccount acc2 =new IconAccount(R.drawable.wallet_icon, "Tài khoản Ví");
        IconAccount acc3 =new IconAccount(R.drawable.heo_dat, "Tài khoản Heo đất");
        IconAccount acc4 =new IconAccount(R.drawable.money_bag, "Money Bag");

        listItem = new ArrayList<IconAccount>();
        listItem.add(acc1);
        listItem.add(acc2);
        listItem.add(acc3);
        listItem.add(acc4);


        IconAccountAdapter accountAdaper = new IconAccountAdapter(this, R.layout.icon_account_listview, listItem);
        lvIcon.setAdapter(accountAdaper);

    }
}
