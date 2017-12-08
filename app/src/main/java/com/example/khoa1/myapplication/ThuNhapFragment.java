package com.example.khoa1.myapplication;

/**
 * Created by khoa1 on 10/30/2017.
 */
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.khoa1.myapplication.Adapter.ThuNhapAdapter;
import com.example.khoa1.myapplication.Database.SQLiteDataController;
import com.example.khoa1.myapplication.Database.SQLiteThuChi;
import com.example.khoa1.myapplication.Model.Category;
import com.example.khoa1.myapplication.Model.ChiTieu;
import com.example.khoa1.myapplication.Model.ThuNhap;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by anupamchugh on 10/12/15.
 */
public class ThuNhapFragment extends Fragment {
    private ListView lvThuNhap;
    private FloatingActionButton fabThuNhap;
    private SQLiteThuChi sqLiteThuChi;
    private Handler handlerSetDataListview;

    private static final int MSG_SET_DATA_SUCCESS = 1;
    public ThuNhapFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_thunhap, container, false);
        sqLiteThuChi = new SQLiteThuChi(getContext());
        lvThuNhap = (ListView) rootView.findViewById(R.id.lvThuNhap);
        registerForContextMenu(lvThuNhap);
        fabThuNhap = (FloatingActionButton) rootView.findViewById(R.id.fabThuNhap);
        fabThuNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(),ThemHoatDong.class);
                startActivityForResult(intent, 0);
            }
        });

        handlerSetDataListview = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == MSG_SET_DATA_SUCCESS) {
                    ThuNhapAdapter thuNhapAdapter = new ThuNhapAdapter(getActivity(), R.layout.chitieu_listview,
                            (ArrayList<ThuNhap>) msg.obj);
                    lvThuNhap.setAdapter(thuNhapAdapter);
                    lvThuNhap.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(getActivity().getApplicationContext(),ChiTietHoatDong.class);
                            //Truyen vao ma thu nhap cho Activity Chi Tiet Hoat Dong
                            intent.putExtra("Ma Thu Nhap",((ThuNhap)adapterView.getItemAtPosition(i)).getMaHoatDong());
                            startActivity(intent);
                        }
                    });
                }
            }
        };
        setDataListView();
        return rootView;
    }

    private void setDataListView() {

        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<ThuNhap> arrThuNhap = sqLiteThuChi.getListThuNhap();
                ThuNhapAdapter thuNhapAdapter = new ThuNhapAdapter(getActivity(), R.layout.chitieu_listview, arrThuNhap);
                //lấy message từ Main thread
                Message msg = handlerSetDataListview.obtainMessage();
                msg.what = MSG_SET_DATA_SUCCESS;
                msg.obj = arrThuNhap;
                //gửi lại Message này về cho Main Thread
                handlerSetDataListview.sendMessage(msg);

            }
        });

        //kích hoạt tiến trình
        th.start();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Lựa chọn:");
        //groupId, itemId, order, title
        menu.add(0, v.getId(), 0, "Xem");
        menu.add(0, v.getId(), 0, "Sửa");
        menu.add(0, v.getId(), 0, "Xoá");
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 0) {
            ArrayList<ThuNhap> arrThuNhap = sqLiteThuChi.getListThuNhap();
            Toast.makeText(getContext(), String.valueOf(arrThuNhap.size()), Toast.LENGTH_LONG).show();
            ThuNhapAdapter thuNhapAdapter = new ThuNhapAdapter(getActivity(), R.layout.chitieu_listview, arrThuNhap);
            lvThuNhap.setAdapter(thuNhapAdapter);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        //Lay vi tri click
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;
        if(item.getTitle()=="Xem"){
            Intent intent = new Intent(getActivity().getApplicationContext(),ChiTietHoatDong.class);
            //Truyen vao ma chi tieu cho Activity Chi Tiet Hoat Dong
            intent.putExtra("Ma Chi Tieu",((ThuNhap)lvThuNhap.getItemAtPosition(index)).getMaHoatDong());
            intent.putExtra("ThuNhap",true);
            startActivity(intent);
        }
        else if(item.getTitle()=="Sửa"){
            Intent intent = new Intent(getActivity().getApplicationContext(),ThemHoatDong.class);
            intent.putExtra("MaHoatDong",((ThuNhap)lvThuNhap.getItemAtPosition(index)).getMaHoatDong());
            intent.putExtra("ThuNhap",true);
            startActivity(intent);
        }
        else if(item.getTitle()=="Xoá"){
            Toast.makeText(getContext(),"Sửa",Toast.LENGTH_LONG).show();
        }
        else{
            return false;
        }
        return true;
    }

}
