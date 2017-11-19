package com.example.khoa1.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.khoa1.myapplication.Adapter.CategoryAdapter;
import com.example.khoa1.myapplication.Database.SQLiteCategory;
import com.example.khoa1.myapplication.Model.Category;

import java.util.ArrayList;

/**
 * Created by khoa1 on 11/16/2017.
 */

public class TabThuNhapFragment extends Fragment {
    ListView lvThuNhap;
    SQLiteCategory sqLiteCategory;
    ArrayList<Category> listCat;

    public TabThuNhapFragment()
    {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_tab_thunhap, container, false);
            lvThuNhap = (ListView) rootView.findViewById(R.id.lvThuNhap);
            sqLiteCategory = new SQLiteCategory(getContext());
            listCat = sqLiteCategory.getListCategoryThuNhap();

            CategoryAdapter accountAdaper = new CategoryAdapter(getActivity(), R.layout.category_listview, listCat);
            lvThuNhap.setAdapter(accountAdaper);
        return rootView;
    }
}
