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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.khoa1.myapplication.Adapter.CategoryAdapter;
import com.example.khoa1.myapplication.Adapter.ChiTieuAdapter;
import com.example.khoa1.myapplication.Database.SQLiteCategory;
import com.example.khoa1.myapplication.Database.SQLiteThuChi;
import com.example.khoa1.myapplication.Model.Category;
import com.example.khoa1.myapplication.Model.CategoryCount;
import com.example.khoa1.myapplication.Model.ChiTieu;
import com.example.khoa1.myapplication.Model.HoatDong;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;


public class ThongKeFragment extends Fragment {
    SQLiteCategory sqlcategory;
    SQLiteThuChi sqlthuchi;
    private ListView listChiTieu;
    ArrayList<ChiTieu> listchi;
    Spinner spinner;
    private static String TAG = "ThongKeFragment";
    private ArrayList listarr = new ArrayList();
    PieChart pieChart;
    ArrayList<CategoryCount> list;
    ListView lvChitieu;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_thongke, container, false);
        // final float[] yData = {25.3f, 10.6f, 66.76f, 44.32f, 46.01f, 16.89f, 23.9f};
        lvChitieu=(ListView) rootView.findViewById(R.id.listViewChiTieu);
        spinner = (Spinner) rootView.findViewById(R.id.spinner);
        pieChart = (PieChart) rootView.findViewById(R.id.idPieChart);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.spinner_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
//        sqlcategory = new SQLiteCategory(getContext());
//        sqlthuchi = new SQLiteThuChi(getContext());
//        list=sqlcategory.getListCategoryCount();
//        listchi = sqlthuchi.getListChiTieu();
//        ChiTieuAdapter chiTieuAdapter = new ChiTieuAdapter(getActivity(), R.layout.chitieu_listview, listchi);
//        lvChitieu.setAdapter(chiTieuAdapter);
        LoadGraph(spinner.getSelectedItem().toString());
        Log.d(TAG, "onCreate: starting to create chart");

        pieChart.setDescription("Biểu đồ chi tiêu theo %");
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

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), spinner.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
                LoadGraph(spinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Log.d(TAG, "onValueSelected: Value select from chart.");
                Log.d(TAG, "onValueSelected: " + e.toString());
                Log.d(TAG, "onValueSelected: " + list.get((int)h.getX()).getTenLoai());

                String TenLoai= list.get((int)h.getX()).getTenLoai();
                listchi = sqlthuchi.getListChiTieubyCategory(list.get((int)h.getX()).getMaLoai());
                ChiTieuAdapter chiTieuAdapter = new ChiTieuAdapter(getActivity(), R.layout.chitieu_listview, listchi);
                lvChitieu.setAdapter(chiTieuAdapter);
                int TongTien= list.get((int)h.getX()).getTongTien();
                Toast.makeText(getActivity(), "Tên loại " +  TenLoai +"\nTổng tiền " + Integer.toString(TongTien), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });
        return rootView;
    }


    private void LoadGraph(String SearchBy){

        sqlcategory = new SQLiteCategory(getContext());
        sqlthuchi = new SQLiteThuChi(getContext());
        list=sqlcategory.getListCategoryCount();
        switch (SearchBy){
            case "Tất cả":
                listchi = sqlthuchi.getListChiTieu();
                break;
            case "Ngày":
                listchi = sqlthuchi.getListChiTieubyDay();
            case "Tháng":
            case "Năm":
        }


        ChiTieuAdapter chiTieuAdapter = new ChiTieuAdapter(getActivity(), R.layout.chitieu_listview, listchi);
        lvChitieu.setAdapter(chiTieuAdapter);

        Log.d(TAG, "onCreate: starting to create chart");

        pieChart.setDescription("Biểu đồ chi tiêu theo %");
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
        PieDataSet pieDataSet = new PieDataSet(yEntrys,"");
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
