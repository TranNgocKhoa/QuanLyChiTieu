package com.example.khoa1.myapplication.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khoa1.myapplication.Model.Account;
import com.example.khoa1.myapplication.Model.IconAccount;
import com.example.khoa1.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by khoa1 on 11/18/2017.
 */

public class IconAccountAdapter extends ArrayAdapter<IconAccount>{
    private Context context;
    private int resource;
    private ArrayList<IconAccount> arrIconAccount;
    public IconAccountAdapter(Context context, int resource, ArrayList<IconAccount> arrIconAccount) {
        super(context, resource, arrIconAccount);
        this.context = context;
        this.resource = resource;
        this.arrIconAccount = arrIconAccount;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(5, 5, 5, 5);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(arrIconAccount.get(position).getDrawableID());
        return imageView;
    }

    public class ViewHolder {
        ImageView image;
    }
}
