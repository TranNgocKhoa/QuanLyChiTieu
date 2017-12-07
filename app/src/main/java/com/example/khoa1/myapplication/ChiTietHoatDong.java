package com.example.khoa1.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.khoa1.myapplication.Database.SQLiteThuChi;
import com.example.khoa1.myapplication.Model.ChiTieu;
import com.example.khoa1.myapplication.Model.HoatDong;

import java.io.File;

public class ChiTietHoatDong extends AppCompatActivity {

    private ImageView imgDetail;
    private TextView tvTieuDeHoatDong;
    private TextView tvCategory;
    private ImageView imgCategoryHoatDong;
    private TextView tvSoTien;
    private RatingBar ratingBar;
    private TextView tvNoiDung;
    private boolean ThuNhap = true;
    private int MaHoatDong = -1;
    private HoatDong hoatDong;
    private SQLiteThuChi sqLiteThuChi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_hoat_dong);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        sqLiteThuChi = new SQLiteThuChi(this);
        //Lay ma thu nhap va ma chi tieu
        Intent intent = getIntent();
        MaHoatDong = intent.getIntExtra("Ma Chi Tieu", -1);
        if (MaHoatDong != -1) {
            ThuNhap = false;
            hoatDong = sqLiteThuChi.getChiTieuByID(MaHoatDong);
        } else {
            MaHoatDong = intent.getIntExtra("Ma Thu Nhap", -1);
            if (MaHoatDong != -1) {
                ThuNhap = true;
                hoatDong = sqLiteThuChi.getThuNhapByID(MaHoatDong);
            }
        }
        initComponent();
        SetInfoHoatDong();
    }

    private void initComponent() {
        imgDetail = (ImageView) findViewById(R.id.imgDetail);
        tvTieuDeHoatDong = (TextView) findViewById(R.id.tvTieuDeHoatDong);
        tvCategory = (TextView) findViewById(R.id.tvCategory);
        imgCategoryHoatDong = (ImageView) findViewById(R.id.imgCategoryHoatDong);
        tvSoTien = (TextView) findViewById(R.id.tvSoTien);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        tvNoiDung = (TextView) findViewById(R.id.tvNoiDung);
    }

    private void SetInfoHoatDong() {
        tvTieuDeHoatDong.setText(hoatDong.getTieuDe());
        tvCategory.setText(hoatDong.getCategory().getTenLoai());
        imgCategoryHoatDong.setImageResource(hoatDong.getCategory().getImage());
        tvSoTien.setText(String.valueOf(hoatDong.getSoTien()));
        tvNoiDung.setText(hoatDong.getNoiDung());

        if (!ThuNhap) {
            ratingBar.setRating(((ChiTieu) hoatDong).getDanhGia().getRate());
            String pathImg = ((ChiTieu) hoatDong).getDanhGia().getHinhAnh();
            File imgFile = null;
            if(pathImg!= null && pathImg.length() !=0)
            imgFile = new File(pathImg);

            if (imgFile != null && imgFile.exists()) {

                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());


                imgDetail.setImageBitmap(myBitmap);

            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
