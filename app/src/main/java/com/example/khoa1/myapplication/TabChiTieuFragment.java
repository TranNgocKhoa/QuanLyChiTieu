package com.example.khoa1.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.khoa1.myapplication.Adapter.CategoryAdapter;
import com.example.khoa1.myapplication.Database.SQLiteCategory;
import com.example.khoa1.myapplication.Model.Category;

import java.util.ArrayList;

/**
 * Created by khoa1 on 11/16/2017.
 */

public class TabChiTieuFragment extends Fragment {
    ListView lvChiTieu;
    SQLiteCategory sqLiteCategory;
    ArrayList<Category> listCat;
    public TabChiTieuFragment()
    {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_tab_chitieu, container, false);
        lvChiTieu = (ListView) rootView.findViewById(R.id.lvChiTieu);
        sqLiteCategory = new SQLiteCategory(getContext());
        listCat = sqLiteCategory.getListCategoryChiTieu();

        CategoryAdapter accountAdaper = new CategoryAdapter(getActivity(), R.layout.category_listview, listCat);
        lvChiTieu.setAdapter(accountAdaper);
        return rootView;
    }




}
