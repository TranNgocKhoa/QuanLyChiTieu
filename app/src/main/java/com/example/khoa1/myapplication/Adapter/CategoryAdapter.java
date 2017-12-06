package com.example.khoa1.myapplication.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khoa1.myapplication.Model.Account;
import com.example.khoa1.myapplication.Model.Category;
import com.example.khoa1.myapplication.R;

import java.util.ArrayList;

/**
 * Created by khoa1 on 11/19/2017.
 */

public class CategoryAdapter extends ArrayAdapter {
    private Context context;
    private int resource;
    private ArrayList<Category> arrCat;


    public CategoryAdapter(Context context, int resource, ArrayList<Category> arrCat) {
        super(context, resource, arrCat);
        this.context = context;
        this.resource = resource;
        this.arrCat = arrCat;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        CategoryAdapter.ViewHolder viewHolder;
        //Nếu convertView == null thì tạo một convertView mới bằng cách infalte từ row_listview
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.category_listview, parent, false);
            //Tạo một viewHolder để lưu các giá trị view lấy được từ convertView
            viewHolder = new CategoryAdapter.ViewHolder();
            viewHolder.image = (ImageView)convertView.findViewById(R.id.imgCat);
            viewHolder.name = (TextView) convertView.findViewById(R.id.tvCatName);
            convertView.setTag(viewHolder);
        }
        //Nếu Converview đã có
        else
        {
            viewHolder = (CategoryAdapter.ViewHolder) convertView.getTag();
        }

        //Set thông tin cho item
        Category category = arrCat.get(position);

        Log.d("position",String.valueOf(position));
        Log.d("position",category.getTenLoai());
        if(position%2==0) {
            convertView.setBackgroundResource(R.color.colorSecondaryLight);
        }
        else {
            convertView.setBackgroundResource(android.R.color.background_light);
        }
        viewHolder.name.setText(category.getTenLoai());
        viewHolder.image.setImageResource(category.getImage());
//        if (account.getPicture()!=null){
//            Bitmap bitmap= BitmapFactory.decodeByteArray(account.getPicture(), 0, account.getPicture().length);
//            viewHolder.imgAccountType.setImageBitmap(bitmap);}
//        else viewHolder.imgAccountType.setImageResource(R.drawable.nophoto);
        return convertView;
    }

    public class ViewHolder {
        TextView name;
        ImageView image;
    }
}
