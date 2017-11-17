package com.example.khoa1.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khoa1.myapplication.Model.Account;
import com.example.khoa1.myapplication.Model.ChiTieu;
import com.example.khoa1.myapplication.R;

import java.util.ArrayList;

/**
 * Created by khoa1 on 11/13/2017.
 */

public class ChiTieuAdapter extends ArrayAdapter<ChiTieu> {
    private int resource;
    private ArrayList<ChiTieu> arrChiTieu;
    private Context context;

    public ChiTieuAdapter(Context context, int resource, ArrayList<ChiTieu> arrChiTieu) {
        super(context, resource, arrChiTieu);
        this.context = context;
        this.resource = resource;
        this.arrChiTieu = arrChiTieu;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ChiTieuAdapter.ViewHolder viewHolder;
        if (convertView == null) {  //Nếu convertView == null thì tạo một convertView mới bằng cách infalte từ row_listview
            convertView = LayoutInflater.from(context).inflate(R.layout.chitieu_listview, parent, false);
            //Tạo một viewHolder để lưu các giá view lấy được từ convertView
            viewHolder = new ChiTieuAdapter.ViewHolder();
            viewHolder.imgChiTieuType = (ImageView)convertView.findViewById(R.id.imgChiTieuType);
            viewHolder.tvChiTieuName = (TextView) convertView.findViewById(R.id.tvChiTieuName);
            viewHolder.tvSoTien = (TextView) convertView.findViewById(R.id.tvSoTien);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ChiTieuAdapter.ViewHolder) convertView.getTag();
        }
        ChiTieu chitieu = arrChiTieu.get(position);
        if(position%2==0)
            convertView.setBackgroundResource(R.color.colorSecondaryLight);
        viewHolder.imgChiTieuType.setImageResource(chitieu.getCategory().getImage());
        viewHolder.tvChiTieuName.setText(chitieu.getTieuDe());
        viewHolder.tvSoTien.setText(Double.toString(chitieu.getSoTien()));
        return convertView;
    }

    public class ViewHolder {
        TextView tvChiTieuName, tvSoTien;
        ImageView imgChiTieuType;
    }
}
