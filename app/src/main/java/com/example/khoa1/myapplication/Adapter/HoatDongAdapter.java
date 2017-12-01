package com.example.khoa1.myapplication.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khoa1.myapplication.Model.ChiTieu;
import com.example.khoa1.myapplication.Model.HoatDong;
import com.example.khoa1.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by khoa1 on 11/19/2017.
 */

public class HoatDongAdapter extends ArrayAdapter {
    private int resource;
    private ArrayList<HoatDong> arrHoatDong;
    private Context context;

    public HoatDongAdapter(Context context, int resource, ArrayList<HoatDong> arrHoatDong) {
        super(context, resource, arrHoatDong);
        this.context = context;
        this.resource = resource;
        this.arrHoatDong = arrHoatDong;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        HoatDongAdapter.ViewHolder viewHolder;
        if (convertView == null) {  //Nếu convertView == null thì tạo một convertView mới bằng cách infalte từ row_listview
            convertView = LayoutInflater.from(context).inflate(R.layout.chitieu_listview, parent, false);
            //Tạo một viewHolder để lưu các giá view lấy được từ convertView
            viewHolder = new HoatDongAdapter.ViewHolder();
            viewHolder.imgChiTieuType = (ImageView)convertView.findViewById(R.id.imgChiTieuType);
            viewHolder.tvChiTieuName = (TextView) convertView.findViewById(R.id.tvChiTieuName);
            viewHolder.tvSoTien = (TextView) convertView.findViewById(R.id.tvSoTien);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (HoatDongAdapter.ViewHolder) convertView.getTag();
        }
        HoatDong hoatDong = arrHoatDong.get(position);
        if(hoatDong.getClass() == ChiTieu.class)
            convertView.setBackgroundResource(R.color.colorSecondaryLight);
        else {
            convertView.setBackgroundResource(android.R.color.background_light);
        }
        viewHolder.imgChiTieuType.setImageResource(hoatDong.getCategory().getImage());
        viewHolder.tvChiTieuName.setText(hoatDong.getTieuDe());
        viewHolder.tvSoTien.setText(Double.toString(hoatDong.getSoTien()));
        return convertView;
    }

    public class ViewHolder {
        TextView tvChiTieuName, tvSoTien;
        ImageView imgChiTieuType;
    }
}
