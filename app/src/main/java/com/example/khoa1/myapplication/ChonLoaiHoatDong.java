package com.example.khoa1.myapplication;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.khoa1.myapplication.Database.SQLiteCategory;
import com.example.khoa1.myapplication.Model.Category;

import java.util.ArrayList;
import java.util.List;

public class ChonLoaiHoatDong extends AppCompatActivity {
    private SectionsPageAdapter mSectionsPageAdapter;
    private ViewPager mViewPager;
    private static final String TAG = "ChonLoaiHoatDong";
    SQLiteCategory sqLiteCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_loai_hoat_dong);
        Log.d(TAG, "onCreate: Starting.");
        sqLiteCategory = new SQLiteCategory(this);
        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.viewpager_container);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

//        Category catCT1 = new Category(0, "Mua Sắm", R.drawable.muasam);
//        Category catCT2 = new Category(0, "Ăn Quán", R.drawable.food2);
//        Category catCT3 = new Category(0, "Uống Cafe", R.drawable.cafe);
//        Category catCT4 = new Category(0, "Uống Trà Sữa", R.drawable.milk);
//        Category catCT5 = new Category(0, "Đổ Xăng", R.drawable.xang);
//        Category catCT6 = new Category(0, "Đồ Uống Khác", R.drawable.bar1);
//        Category catCT7 = new Category(0, "Chi Tiêu Khác", R.drawable.other_shopping);
//
//        sqLiteCategory.addChiTieuCategory(catCT1);
//        sqLiteCategory.addChiTieuCategory(catCT2);
//        sqLiteCategory.addChiTieuCategory(catCT3);
//        sqLiteCategory.addChiTieuCategory(catCT4);
//        sqLiteCategory.addChiTieuCategory(catCT5);
//        sqLiteCategory.addChiTieuCategory(catCT6);
//        sqLiteCategory.addChiTieuCategory(catCT7);
//
//        ArrayList<Category> arr = sqLiteCategory.getListCategoryChiTieu();
//
//        Category catTN1 = new Category(0, "Lương", R.drawable.luong);
//        Category catTN2 = new Category(0, "Tiền Lãi", R.drawable.coin2);
//        Category catTN3 = new Category(0, "Bán Đồ", R.drawable.luong1);
//        Category catTN4 = new Category(0, "Thưởng", R.drawable.money_bag);
//        Category catTN5 = new Category(0, "Khoản Thu Khác", R.drawable.khoanthukhac);
//
//        sqLiteCategory.addThuNhapCategory(catTN1);
//        sqLiteCategory.addThuNhapCategory(catTN2);
//        sqLiteCategory.addThuNhapCategory(catTN3);
//        sqLiteCategory.addThuNhapCategory(catTN4);
//        sqLiteCategory.addThuNhapCategory(catTN5);
//        ArrayList<Category> arr1 = sqLiteCategory.getListCategoryThuNhap();

    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new TabThuNhapFragment(), "Thu Nhập");
        adapter.addFragment(new TabChiTieuFragment(), "Chi Tiêu");
        viewPager.setAdapter(adapter);
    }

    public class SectionsPageAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        public SectionsPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
    }
}
