package com.example.khoa1.myapplication;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
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


import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class ThemHoatDong extends AppCompatActivity {
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
    private Calendar myCalendar;
    private SQLiteThuChi sqLiteThuChi;
    private Account TaiKhoan;
    private Category category;
    private RatingBar ratingBar;
    private DanhGia danhGia;
    private String imgPath;
    private boolean thuNhap = true;
    private int MaHoatDong;
    private Location LastLocation;
    private LocationManager locationManager;
    private LocationListener listener;
    private FusedLocationProviderClient mFusedLocationClient;
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_hoat_dong);
        setTitle("Thêm mới thu chi");
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        //Set back toolbar button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        sqLiteThuChi = new SQLiteThuChi(this);
        initComponent();
        setListernerForComponent();
    }

    @Override
    public void onStart() {
        super.onStart();

        if (!checkPermissions()) {
            requestPermissions();
        } else {
            getLastLocation();
        }
    }

    private boolean checkPermissions() {
        int permissionState = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);
        return permissionState == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermissions() {
        boolean shouldProvideRationale =
                ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION);

        if (shouldProvideRationale) {
            // Request permission
                            startLocationPermissionRequest();
        } else {
            startLocationPermissionRequest();
        }
    }
    private void startLocationPermissionRequest() {
        ActivityCompat.requestPermissions(ThemHoatDong.this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                REQUEST_PERMISSIONS_REQUEST_CODE);
    }

    @SuppressWarnings("MissingPermission")
    private void getLastLocation() {
        mFusedLocationClient.getLastLocation()
                .addOnCompleteListener(this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(Task<Location> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            LastLocation = task.getResult();

                        } else {
                            Log.w("Error", "getLastLocation:exception", task.getException());
                        }
                    }
                });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                          int[] grantResults) {
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            if (grantResults.length <= 0) {
                // If user interaction was interrupted, the permission request is cancelled and you
                // receive empty arrays.
                Log.i("Error", "User interaction was cancelled.");
            } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted.
                getLastLocation();
            }
        }
    }

    //Hàm khỏi tạo các thành phần trong activity
    private void initComponent() {

        myCalendar = Calendar.getInstance();
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
        ratingBar.setEnabled(false);
        imgButtonCamera.setEnabled(false);

        MaHoatDong = getIntent().getIntExtra("MaHoatDong", -1);
        thuNhap = getIntent().getBooleanExtra("ThuNhap", true);
        TaiKhoan = (Account) getIntent().getSerializableExtra("MaTaiKhoan");
        if(TaiKhoan!= null)
        {
            tvChonTaiKhoan.setText(TaiKhoan.getTenTaiKhoan());
            imgChonTaiKhoan.setImageResource(TaiKhoan.getPicture());
        }
        if(MaHoatDong!=-1)
        {
            setTitle("Chỉnh sửa thu chi");
            getHoatDong();
        }
    }
    //Hàm set listener cho các thành phần
    private void setListernerForComponent()
    {
        tvChonLoaiHoatDong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ChonLoaiHoatDong.class);
                startActivityForResult(intent, 1);
            }
        });
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

    }
    private void getHoatDong() {

        if(!thuNhap)
        {
            ratingBar.setEnabled(true);
            imgButtonCamera.setEnabled(true);
           ChiTieu chiTieu = sqLiteThuChi.getChiTieuByID(MaHoatDong);
           if(chiTieu!= null)
           {
               tvChonTaiKhoan.setText(chiTieu.getTaiKhoan().getTenTaiKhoan());
               imgChonTaiKhoan.setImageResource(chiTieu.getTaiKhoan().getPicture());
               TaiKhoan = chiTieu.getTaiKhoan();
               tvChonLoaiHoatDong.setText(chiTieu.getCategory().getTenLoai());
               imgChonLoaiHoatDong.setImageResource(chiTieu.getCategory().getImage());
               category = chiTieu.getCategory();
               ratingBar.setRating(chiTieu.getDanhGia().getRate());
               danhGia = chiTieu.getDanhGia();
               imgPath = chiTieu.getDanhGia().getHinhAnh();
               edTieuDe.setText(chiTieu.getTieuDe());
               edNoiDung.setText(chiTieu.getNoiDung());
               edSoTien.setText(String.valueOf(chiTieu.getSoTien()));
               if(imgPath!=null)
               {File imgFile = new File(imgPath);

               if(imgFile.exists()){

                   Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                   imgHinhAnh.setImageBitmap(myBitmap);
               }}
           }
        }

        else
        {
            ratingBar.setEnabled(false);
            imgButtonCamera.setEnabled(false);
            ThuNhap thuNhap = sqLiteThuChi.getThuNhapByID(MaHoatDong);
            if(thuNhap!= null)
            {
                tvChonTaiKhoan.setText(thuNhap.getTaiKhoan().getTenTaiKhoan());
                imgChonTaiKhoan.setImageResource(thuNhap.getTaiKhoan().getPicture());
                TaiKhoan = thuNhap.getTaiKhoan();
                tvChonLoaiHoatDong.setText(thuNhap.getCategory().getTenLoai());
                imgChonLoaiHoatDong.setImageResource(thuNhap.getCategory().getImage());
                category = thuNhap.getCategory();
                edTieuDe.setText(thuNhap.getTieuDe());
                edNoiDung.setText(thuNhap.getNoiDung());
                edSoTien.setText(String.valueOf(thuNhap.getSoTien()));
            }
        }
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
            case  android.R.id.home:
                this.finish();
                return true;
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

        if(edSoTien.getText().length() == 0) edSoTien.setText("0");
        double soTien = Double.parseDouble(edSoTien.getText().toString());
        String tieuDe = edTieuDe.getText().toString();
        String noiDung = edNoiDung.getText().toString();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SS");
        if(thuNhap)
        {
            ThuNhap thuNhapRecord = new ThuNhap(MaHoatDong, soTien, myCalendar.getTime(), category, tieuDe, noiDung,TaiKhoan);
            if(MaHoatDong==-1)
                sqLiteThuChi.addThuNhap(thuNhapRecord);
            else
                sqLiteThuChi.updateThuNhap(thuNhapRecord);
        }
        else
        {
            danhGia = getDanhGia();
            ChiTieu chiTieuRecord = new ChiTieu(MaHoatDong, soTien, myCalendar.getTime(), category, tieuDe, noiDung, TaiKhoan, danhGia);
            if(MaHoatDong==-1)
                sqLiteThuChi.addChiTieu(chiTieuRecord);
            else
                sqLiteThuChi.updateChiTieu(chiTieuRecord);

            Toast.makeText(this, String.valueOf(LastLocation.getLatitude()), Toast.LENGTH_SHORT).show();
        }
    }

    private DanhGia getDanhGia()
    {
        DanhGia danhGia = null;
        if(ratingBar.getNumStars() > 0)
        {
            int maDanhgia =0;
            if(MaHoatDong!=-1)
            maDanhgia = sqLiteThuChi.getChiTieuByID(MaHoatDong).getDanhGia().getMaDanhGia();
            danhGia = new DanhGia(maDanhgia, imgPath, (float) LastLocation.getLongitude(), (float) LastLocation.getLatitude(), (int) ratingBar.getRating());
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
