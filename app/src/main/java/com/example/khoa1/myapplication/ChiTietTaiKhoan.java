package com.example.khoa1.myapplication;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.khoa1.myapplication.Adapter.ChiTieuAdapter;
import com.example.khoa1.myapplication.Model.Category;
import com.example.khoa1.myapplication.Model.ChiTieu;

import java.util.ArrayList;

public class ChiTietTaiKhoan extends AppCompatActivity {
    ListView lvThuChi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_tai_khoan);
        //Set back toolbar button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle("Chi tiết tài khoản");
        lvThuChi = (ListView) findViewById(R.id.lvThuChi);
//        lvChiTieu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//            }
//        });

        Category anUong = new Category(1, "Ăn Uống", ContextCompat.getDrawable(this, R.drawable.anuong));
        Category muaSam = new Category(2, "Mua Sắm", ContextCompat.getDrawable(this, R.drawable.muasam));
        ArrayList<ChiTieu> arrChiTieu = new ArrayList<ChiTieu>();

        ChiTieu ct1 = new ChiTieu(1,
                25000, anUong, "Ăn Phở",
                "Ăn phở Hùng Lê Văn Việt", null, null);

        ChiTieu ct2 = new ChiTieu(2,
                26000, anUong, "Ăn Gà Rán",
                "Ăn gà rán tại Texas Chiken Võ Văn Ngân", null,null);

        ChiTieu ct3 = new ChiTieu(3,
                26000, anUong, "Uống Trà Sữa",
                "Uống trà sữa Bobapop ở Lê Văn Việt", null,null);

        ChiTieu ct4 = new ChiTieu(4,
                27000, anUong, "Uống Cafe",
                "Uống cafe Napoli", null,null);

        ChiTieu ct5 = new ChiTieu(5,
                270000, muaSam, "Mua Áo Sơ Mi",
                "Mua áo sơ mi mới ở Yame", null,null);
        ChiTieu ct6 = new ChiTieu(6,
                7000, muaSam, "Mua bút bi",
                "Mua bút bi trong siêu thị", null,null);

        arrChiTieu.add(ct1);
        arrChiTieu.add(ct5);
        arrChiTieu.add(ct2);
        arrChiTieu.add(ct6);
        arrChiTieu.add(ct3);
        arrChiTieu.add(ct4);

        ChiTieuAdapter chiTieuAdapter = new ChiTieuAdapter(this, R.layout.chitieu_listview, arrChiTieu);
        lvThuChi.setAdapter(chiTieuAdapter);
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
