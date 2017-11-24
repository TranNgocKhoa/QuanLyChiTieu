package com.example.khoa1.myapplication;

/**
 * Created by khoa1 on 10/30/2017.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.khoa1.myapplication.Adapter.ThuNhapAdapter;
import com.example.khoa1.myapplication.Database.SQLiteThuChi;
import com.example.khoa1.myapplication.Model.Category;
import com.example.khoa1.myapplication.Model.ThuNhap;

import java.util.ArrayList;

/**
 * Created by anupamchugh on 10/12/15.
 */
public class ThuNhapFragment extends Fragment {
    private ListView lvThuNhap;
    private SQLiteThuChi sqLiteThuChi;
    public ThuNhapFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_thunhap, container, false);
        sqLiteThuChi = new SQLiteThuChi(getContext());
        lvThuNhap = (ListView) rootView.findViewById(R.id.lvThuNhap);

        Category luong = new Category(1, "Lương", R.drawable.luong);
        Category tienvay = new Category(2, "Lấy lại tiền vay", R.drawable.tienvay);
        ArrayList<ThuNhap> arrThuNhap = sqLiteThuChi.getListThuNhap();

        Toast.makeText(getContext(), String.valueOf(arrThuNhap.size()), Toast.LENGTH_LONG).show();

        ThuNhapAdapter thuNhapAdapter = new ThuNhapAdapter(getActivity(), R.layout.chitieu_listview, arrThuNhap);
        lvThuNhap.setAdapter(thuNhapAdapter);
//lvAccount.setAdapter(arAdp);
        return rootView;
    }

}
