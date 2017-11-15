package com.example.khoa1.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

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
    public ChiTieuFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_chitieu, container, false);
        lvChiTieu = (ListView) rootView.findViewById(R.id.lvChiTieu);
        lvChiTieu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity().getApplicationContext(),ChiTietHoatDong.class);
                Bundle bundle = new Bundle();
                bundle.putInt("Ma Chi Tieu",((ChiTieu)adapterView.getSelectedItem()).getMaHoatDong() );
                startActivity(intent);
            }
        });

        Category anUong = new Category(1, "Ăn Uống", ContextCompat.getDrawable(getContext(), R.drawable.anuong));
        Category muaSam = new Category(2, "Mua Sắm", ContextCompat.getDrawable(getContext(), R.drawable.muasam));
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

}
