package com.example.khoa1.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.khoa1.myapplication.Database.SQLiteCategory;
import com.example.khoa1.myapplication.Database.SQLiteDataController;
import com.example.khoa1.myapplication.Model.Category;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    SQLiteCategory sqLiteCategory;
    Toolbar toolbar;    //Thanh toolbar trên cùng
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle; //Nút Toggle để hiện menu

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteDataController sql = new SQLiteDataController(this);
        try {
            sql.isCreatedDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqLiteCategory = new SQLiteCategory(this);
        if(sqLiteCategory.getListCategoryChiTieu().size() == 0) {
            Category catCT1 = new Category(0, "Mua Sắm", R.drawable.muasam);
            Category catCT2 = new Category(0, "Ăn Quán", R.drawable.food2);
            Category catCT3 = new Category(0, "Uống Cafe", R.drawable.cafe);
            Category catCT4 = new Category(0, "Uống Trà Sữa", R.drawable.milk);
            Category catCT5 = new Category(0, "Đổ Xăng", R.drawable.xang);
            Category catCT6 = new Category(0, "Đồ Uống Khác", R.drawable.bar1);
            Category catCT7 = new Category(0, "Chi Tiêu Khác", R.drawable.other_shopping);

            sqLiteCategory.addChiTieuCategory(catCT1);
            sqLiteCategory.addChiTieuCategory(catCT2);
            sqLiteCategory.addChiTieuCategory(catCT3);
            sqLiteCategory.addChiTieuCategory(catCT4);
            sqLiteCategory.addChiTieuCategory(catCT5);
            sqLiteCategory.addChiTieuCategory(catCT6);
            sqLiteCategory.addChiTieuCategory(catCT7);

        }
        if(sqLiteCategory.getListCategoryThuNhap().size() == 0) {
            Category catTN1 = new Category(0, "Lương", R.drawable.luong);
            Category catTN2 = new Category(0, "Tiền Lãi", R.drawable.coin2);
            Category catTN3 = new Category(0, "Bán Đồ", R.drawable.luong1);
            Category catTN4 = new Category(0, "Thưởng", R.drawable.money_bag);
            Category catTN5 = new Category(0, "Khoản Thu Khác", R.drawable.khoanthukhac);

            sqLiteCategory.addThuNhapCategory(catTN1);
            sqLiteCategory.addThuNhapCategory(catTN2);
            sqLiteCategory.addThuNhapCategory(catTN3);
            sqLiteCategory.addThuNhapCategory(catTN4);
            sqLiteCategory.addThuNhapCategory(catTN5);
        }

        mTitle = mDrawerTitle = getTitle(); //Lấy title của Activity
        //Mỗi fragment sẽ dùng title của navigation_drawer_item_array
        mNavigationDrawerItemTitles= getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        setupToolbar();

        DataModel[] drawerItem = new DataModel[5];

        drawerItem[0] = new DataModel(R.drawable.home, "Tổng quan");
        drawerItem[1] = new DataModel(R.drawable.account, "Tài khoản");
        drawerItem[2] = new DataModel(R.drawable.income, "Thu nhập");
        drawerItem[3] = new DataModel(R.drawable.outcome, "Chi tiêu");
        drawerItem[4] = new DataModel(R.drawable.chart, "Thống kê");
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        DrawerItemCustomAdapter adapter = new DrawerItemCustomAdapter(this, R.layout.list_view_item_row, drawerItem);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        //mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        setupDrawerToggle();
        Fragment fragment = new HomeFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }

    }

    private void selectItem(int position) {

        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new AccountFragment();
                break;
            case 2:
                fragment = new ThuNhapFragment();
                break;
            case 3:
                fragment = new ChiTieuFragment();
                break;
            case 4:
                fragment = new thongke();
                break;

            default:
                break;
        }

        if (fragment != null) {
            //Khi fragment được chọn
            //Dùng fragmentManager chuyển sang fragment được chọn
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(mNavigationDrawerItemTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);

        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    void setupToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    void setupDrawerToggle(){
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name, R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState();
    }
}