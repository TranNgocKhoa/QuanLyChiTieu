package com.example.khoa1.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.khoa1.myapplication.Adapter.AccountAdapter;
import com.example.khoa1.myapplication.Adapter.IconAccountAdapter;
import com.example.khoa1.myapplication.Database.SQLiteAccount;
import com.example.khoa1.myapplication.Model.IconAccount;

import java.util.ArrayList;

public class ChonIconTaiKhoan extends AppCompatActivity {
    GridView gridview;
    ArrayList<IconAccount> listItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_icon_tai_khoan);

        IconAccount acc1 =new IconAccount(R.drawable.bank_account);
        IconAccount acc2 =new IconAccount(R.drawable.wallet_icon);
        IconAccount acc3 =new IconAccount(R.drawable.heo_dat);
        IconAccount acc4 =new IconAccount(R.drawable.money_bag);
        IconAccount acc5 =new IconAccount(R.drawable.coin1);
        IconAccount acc6 =new IconAccount(R.drawable.coin2);
        IconAccount acc7 =new IconAccount(R.drawable.bitcoin);
        IconAccount acc8 =new IconAccount(R.drawable.money_bag1);
        IconAccount acc9 =new IconAccount(R.drawable.wallet1);
        IconAccount acc10 =new IconAccount(R.drawable.walet2);
        IconAccount acc11 =new IconAccount(R.drawable.bank_card);

        listItem = new ArrayList<IconAccount>();
        listItem.add(acc1);
        listItem.add(acc2);
        listItem.add(acc3);
        listItem.add(acc4);
        listItem.add(acc5);
        listItem.add(acc6);
        listItem.add(acc7);
        listItem.add(acc8);
        listItem.add(acc9);
        listItem.add(acc10);
        listItem.add(acc11);

        gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new IconAccountAdapter(this, R.layout.icon_account_listview, listItem));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                int idImage = ((IconAccount)parent.getItemAtPosition(position)).getDrawableID();
                Intent intent = new Intent();
                intent.putExtra("Image", idImage);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}
