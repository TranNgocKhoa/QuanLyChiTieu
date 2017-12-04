package com.example.khoa1.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.khoa1.myapplication.Adapter.CategoryAdapter;
import com.example.khoa1.myapplication.Database.SQLiteCategory;
import com.example.khoa1.myapplication.Database.SQLiteThuChi;
import com.example.khoa1.myapplication.Model.Category;
import com.example.khoa1.myapplication.Model.CategoryCount;
import com.example.khoa1.myapplication.Model.ChiTieu;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;


public class thongke extends Fragment {
    private SQLiteCategory sqlcategory;
    private ListView listChiTieu;
    ArrayList<Category> listCat;
    private static String TAG = "ThongKeFragment";
    private ArrayList listarr = new ArrayList();
   PieChart pieChart;
    private ArrayList<CategoryCount> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_thongke2, container, false);
        // final float[] yData = {25.3f, 10.6f, 66.76f, 44.32f, 46.01f, 16.89f, 23.9f};
        sqlcategory = new SQLiteCategory(getContext());
        list=sqlcategory.getListCategoryCount();

        listCat = sqlcategory.getListCategorybyLoaiChiTieu("Ăn");
        CategoryAdapter accountAdaper = new CategoryAdapter(getActivity(), R.layout.category_listview, listCat);
        listChiTieu.setAdapter(accountAdaper);
        Log.d(TAG, "onCreate: starting to create chart");
        pieChart = (PieChart) rootView.findViewById(R.id.idPieChart);
        pieChart.setDescription("Sales by employee (In Thousands $) ");
        pieChart.setRotationEnabled(true);
        //pieChart.setUsePercentValues(true);
        //pieChart.setHoleColor(Color.BLUE);
        //pieChart.setCenterTextColor(Color.BLACK);
        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleAlpha(0);
        //pieChart.setDrawEntryLabels(true);
        //pieChart.setEntryLabelTextSize(20);
        //More options just check out the documentation!

        addDataSet();

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Log.d(TAG, "onValueSelected: Value select from chart.");
                Log.d(TAG, "onValueSelected: " + e.toString());
                Log.d(TAG, "onValueSelected: " + list.get((int)h.getX()).getTenLoai());

                String TenLoai= list.get((int)h.getX()).getTenLoai();
                int TongTien= list.get((int)h.getX()).getTongTien();
                Toast.makeText(getActivity(), "Tên loại " +  TenLoai +"\nTổng tiền " + Integer.toString(TongTien), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });
        return rootView;
    }


    private void addDataListView(){
        Log.d(TAG,"addDataListView started");

    }
    private void addDataSet() {
        Log.d(TAG, "addDataSet started");
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();
        int Sum=0;
        for (int i=0;i< list.size();i++){
            Sum+=list.get(i).getTongTien();
            xEntrys.add(list.get(i).getTenLoai());
        }

        for(int i = 0; i < list.size(); i++) {
            yEntrys.add(new PieEntry(list.get(i).getTongTien()*100/Sum, i));
        }
        //create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "Employee Sales");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(Color.GRAY);
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.CYAN);
        colors.add(Color.YELLOW);
        colors.add(Color.MAGENTA);

        pieDataSet.setColors(colors);



        //create pie data object
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();
    }
}
