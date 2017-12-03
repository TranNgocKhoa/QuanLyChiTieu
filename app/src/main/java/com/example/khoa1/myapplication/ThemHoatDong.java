package com.example.khoa1.myapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khoa1.myapplication.Database.SQLiteAccount;
import com.example.khoa1.myapplication.Database.SQLiteCategory;
import com.example.khoa1.myapplication.Database.SQLiteThuChi;
import com.example.khoa1.myapplication.Model.Account;
import com.example.khoa1.myapplication.Model.Category;
import com.example.khoa1.myapplication.Model.ChiTieu;
import com.example.khoa1.myapplication.Model.DanhGia;
import com.example.khoa1.myapplication.Model.ThuNhap;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ThemHoatDong extends AppCompatActivity{
    private Toolbar toolbar;
    private TextView tvChonLoaiHoatDong;
    private TextView tvChonNgay;
    private TextView tvChonTaiKhoan;
    private ImageView imgChonTaiKhoan;
    private EditText edSoTien;
    private EditText edTieuDe;
    private EditText edNoiDung;
    private ImageView imgChonLoaiHoatDong;
    private ImageButton imgButtonCamera;
    private ImageView imgHinhAnh;
    private  Calendar myCalendar;
    private SQLiteThuChi sqLiteThuChi;
    private Account TaiKhoan;
    private Category category;
    private RatingBar ratingBar;
    private DanhGia danhGia;
    private String imgPath;
    private boolean thuNhap = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_hoat_dong);
        sqLiteThuChi = new SQLiteThuChi(this);
        myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            }

        };
        myCalendar.setTime(myCalendar.getTime());
        tvChonLoaiHoatDong = (TextView) findViewById(R.id.tvChonLoaiHoatDong);
        imgChonLoaiHoatDong = (ImageView) findViewById(R.id.imageIconLoai);
        tvChonNgay = (TextView) findViewById(R.id.tvChonNgay);
        tvChonTaiKhoan = (TextView) findViewById(R.id.tvChonTaiKhoan);
        imgChonTaiKhoan = (ImageView) findViewById(R.id.imageIconVi) ;
        imgButtonCamera = (ImageButton) findViewById(R.id.imgButtonCamera);
        imgHinhAnh = (ImageView) findViewById(R.id.imgHinhAnh);
        edSoTien = (EditText) findViewById(R.id.edSoTien);
        edTieuDe = (EditText) findViewById(R.id.edTieuDe);
        edNoiDung = (EditText) findViewById(R.id.edNoiDung);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        tvChonLoaiHoatDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ChonLoaiHoatDong.class);
                startActivityForResult(intent, 1);
            }
        });
        tvChonNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(ThemHoatDong.this, date , myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        tvChonTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ChonTaiKhoan.class);
                startActivityForResult(intent, 2);
            }
        });
        imgButtonCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThemHoatDong.this, CameraActivity.class);
                //startActivity(intent);
                startActivityForResult(intent, 3);
            }
        });

        ratingBar.setEnabled(false);
        imgButtonCamera.setEnabled(false);

        //Set back toolbar button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_menu_them_hoat_dong, menu);
        // Action View
        //MenuItem searchItem = menu.findItem(R.id.action_search);
        //SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        // Configure the search info and add any event listeners
        //return super.onCreateOptionsMenu(menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_favorite:
                try {
                    saveMenuClick();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                this.finish();
                return true;
            case R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveMenuClick() throws ParseException {

        double soTien = Double.parseDouble(edSoTien.getText().toString());
        String tieuDe = edTieuDe.getText().toString();
        String noiDung = edNoiDung.getText().toString();
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        if(thuNhap)
        {
            ThuNhap thuNhapRecord = new ThuNhap(0, soTien, myCalendar.getTime(), category, tieuDe, noiDung,TaiKhoan);

            sqLiteThuChi.addThuNhap(thuNhapRecord);
        }
        else
        {
            danhGia = getDanhGia();
            ChiTieu chiTieuRecord = new ChiTieu(0, soTien, myCalendar.getTime(), category, tieuDe, noiDung, TaiKhoan, danhGia);
            sqLiteThuChi.addChiTieu(chiTieuRecord);

        }
    }

    private DanhGia getDanhGia()
    {
        DanhGia danhGia = null;
        if(ratingBar.getNumStars() > 0)
        {
            danhGia = new DanhGia(0, imgPath, 0.0f, 0.0f, ratingBar.getNumStars());
        }
        return danhGia;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                Category cat = (Category) data.getSerializableExtra("LoaiChiTieu");
                if(cat != null)
                {
                    thuNhap = false;
                    ratingBar.setEnabled(true);
                    imgButtonCamera.setEnabled(true);
                    category = cat;
                }
                else
                {
                    thuNhap = true;
                    ratingBar.setEnabled(false);
                    imgButtonCamera.setEnabled(false);
                    category = (Category) data.getSerializableExtra("LoaiThuNhap");
                }

                //Cập nhật hình và text cho loại hoạt động
                tvChonLoaiHoatDong.setText(category.getTenLoai());
                imgChonLoaiHoatDong.setImageResource(category.getImage());

            }
        }
        if(requestCode == 2)
        {
            if(resultCode == RESULT_OK) {
                Account acc = (Account) data.getSerializableExtra("Tai Khoan");
                if(acc != null)
                {
                    TaiKhoan = acc;
                }
                //Cập nhật account icon và tên
                tvChonTaiKhoan.setText(TaiKhoan.getTenTaiKhoan());
                imgChonTaiKhoan.setImageResource(TaiKhoan.getPicture());
            }
        }

        if(requestCode == 3)
        {
            if(resultCode == RESULT_OK) {
                String imgString = data.getStringExtra("Image");
                if(imgString != null)
                {
                    imgPath = imgString;

                    File imgFile = new  File(imgString);

                    if(imgFile.exists()){

                        Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());


                        imgHinhAnh.setImageBitmap(myBitmap);

                    }
                }

            }
        }
    }
}
