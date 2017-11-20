package com.example.khoa1.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khoa1.myapplication.Model.ThuNhap;
import com.example.khoa1.myapplication.Model.ThuNhap;
import com.example.khoa1.myapplication.R;

import java.util.ArrayList;

/**
 * Created by khoa1 on 11/13/2017.
 */

public class ThuNhapAdapter extends ArrayAdapter<ThuNhap> {
    private int resource;
    private ArrayList<ThuNhap> arrThuNhap;
    private Context context;

    public ThuNhapAdapter(Context context, int resource, ArrayList<ThuNhap> arrThuNhap) {
        super(context, resource, arrThuNhap);
        this.context = context;
        this.resource = resource;
        this.arrThuNhap = arrThuNhap;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ThuNhapAdapter.ViewHolder viewHolder;
        if (convertView == null) {  //Nếu convertView == null thì tạo một convertView mới bằng cách infalte từ row_listview
            convertView = LayoutInflater.from(context).inflate(R.layout.chitieu_listview, parent, false);
            //Tạo một viewHolder để lưu các giá view lấy được từ convertView
            viewHolder = new ThuNhapAdapter.ViewHolder();
            viewHolder.imgThuNhapType = (ImageView)convertView.findViewById(R.id.imgChiTieuType);
            viewHolder.tvThuNhapName = (TextView) convertView.findViewById(R.id.tvChiTieuName);
            viewHolder.tvSoTien = (TextView) convertView.findViewById(R.id.tvSoTien);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ThuNhapAdapter.ViewHolder) convertView.getTag();
        }
        ThuNhap ThuNhap = arrThuNhap.get(position);
        if(position%2==0)
            convertView.setBackgroundResource(R.color.colorSecondaryLight);
        viewHolder.imgThuNhapType.setImageResource(ThuNhap.getCategory().getImage());
        viewHolder.tvThuNhapName.setText(ThuNhap.getTieuDe());
        viewHolder.tvSoTien.setText(Double.toString(ThuNhap.getSoTien()));
        return convertView;
    }

    public class ViewHolder {
        TextView tvThuNhapName, tvSoTien;
        ImageView imgThuNhapType;
    }

}
