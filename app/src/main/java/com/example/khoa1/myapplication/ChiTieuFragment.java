package com.example.khoa1.myapplication;

import android.content.Intent;
import android.icu.text.IDNA;
import android.os.Bundle;
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

import com.example.khoa1.myapplication.Adapter.AccountAdapter;
import com.example.khoa1.myapplication.Adapter.ChiTieuAdapter;
import com.example.khoa1.myapplication.Model.Account;
import com.example.khoa1.myapplication.Model.AccountType;
import com.example.khoa1.myapplication.Model.Category;
import com.example.khoa1.myapplication.Model.ChiTieu;

import java.util.ArrayList;

/**
 * Created by anupamchugh on 10/12/15.
 */
public class ChiTieuFragment extends Fragment {
    private ListView lvChiTieu;
    private FloatingActionButton fabChiTieu;
    public ChiTieuFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_chitieu, container, false);
        lvChiTieu = (ListView) rootView.findViewById(R.id.lvChiTieu);
        lvChiTieu.setOnItemClickListener(new ListViewOnItemClickListener());
        registerForContextMenu(lvChiTieu);


        fabChiTieu = (FloatingActionButton) rootView.findViewById(R.id.fabChiTieu);
        fabChiTieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(),ThemHoatDong.class);
                startActivity(intent);
            }
        });
        Category anUong = new Category(1, "Ăn Uống", R.drawable.anuong);
        Category muaSam = new Category(2, "Mua Sắm", R.drawable.muasam);
        ArrayList<ChiTieu> arrChiTieu = new ArrayList<ChiTieu>();

        ChiTieu ct1 = new ChiTieu(1,
                25000, anUong, "Ăn Phở",
                "Ăn phở Hùng Lê Văn Việt", null, null);

        ChiTieu ct2 = new ChiTieu(2,
                26000, anUong, "Ăn Gà Rán",
                "Ăn gà rán tại Texas Chiken Võ Văn Ngân", null,null);

        ChiTieu ct3 = new ChiTieu(3,
                26000, anUong, "Uống Trà Sữa",
                "Uống trà sữa Bobapop ở Lê Văn Việt", null,null);

        ChiTieu ct4 = new ChiTieu(4,
                27000, anUong, "Uống Cafe",
                "Uống cafe Napoli", null,null);

        ChiTieu ct5 = new ChiTieu(5,
                270000, muaSam, "Mua Áo Sơ Mi",
                "Mua áo sơ mi mới ở Yame", null,null);
        ChiTieu ct6 = new ChiTieu(6,
                7000, muaSam, "Mua bút bi",
                "Mua bút bi trong siêu thị", null,null);

        arrChiTieu.add(ct1);
        arrChiTieu.add(ct5);
        arrChiTieu.add(ct2);
        arrChiTieu.add(ct6);
        arrChiTieu.add(ct3);
        arrChiTieu.add(ct4);

        ChiTieuAdapter chiTieuAdapter = new ChiTieuAdapter(getActivity(), R.layout.chitieu_listview, arrChiTieu);
        lvChiTieu.setAdapter(chiTieuAdapter);
//lvAccount.setAdapter(arAdp);
        return rootView;
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
    public boolean onContextItemSelected(MenuItem item){
        //Lay vi tri click
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;
        if(item.getTitle()=="Xem"){
            Intent intent = new Intent(getActivity().getApplicationContext(),ChiTietHoatDong.class);
            //Truyen vao ma chi tieu cho Activity Chi Tiet Hoat Dong
            intent.putExtra("Ma Chi Tieu",((ChiTieu)lvChiTieu.getItemAtPosition(index)).getMaHoatDong());
           startActivity(intent);
        }
        else if(item.getTitle()=="Sửa"){
            Toast.makeText(getContext(),"Sửa",Toast.LENGTH_LONG).show();
        }
        else if(item.getTitle()=="Xoá"){
            Toast.makeText(getContext(),"Sửa",Toast.LENGTH_LONG).show();
        }
        else{
            return false;
        }
        return true;
    }

    private class ListViewOnItemClickListener implements AdapterView.OnItemClickListener
    {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent intent = new Intent(getActivity().getApplicationContext(),ChiTietHoatDong.class);
            //Truyen vao ma chi tieu cho Activity Chi Tiet Hoat Dong
            intent.putExtra("Ma Chi Tieu",((ChiTieu)adapterView.getItemAtPosition(i)).getMaHoatDong());
            startActivity(intent);
        }
    }

}
