package com.example.khoa1.myapplication.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
        IconAccountAdapter.ViewHolder viewHolder;
        //Nếu convertView == null thì tạo một convertView mới bằng cách infalte từ row_listview
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.icon_account_listview, parent, false);
            //Tạo một viewHolder để lưu các giá trị view lấy được từ convertView
            viewHolder = new IconAccountAdapter.ViewHolder();
            viewHolder.image = ((ImageView)convertView.findViewById(R.id.imgAccount));
            viewHolder.name = (TextView) convertView.findViewById(R.id.tvAccount);
            convertView.setTag(viewHolder);
        }
        //Nếu Converview đã có
        else
        {
            viewHolder = (IconAccountAdapter.ViewHolder) convertView.getTag();
        }

        //Set thông tin cho item
        IconAccount iconaccount = arrIconAccount.get(position);
        if(position%2==0) {
            convertView.setBackgroundResource(R.color.colorSecondaryLight);
        }
        viewHolder.name.setText(iconaccount.getName());
        viewHolder.image.setImageResource(iconaccount.getDrawableID());
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
