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
import com.example.khoa1.myapplication.Model.AccountType;
import com.example.khoa1.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by khoa1 on 11/14/2017.
 */

public class AccountTypeAdapter extends ArrayAdapter<AccountType> {

    private Context context;
    private int resource;
    private ArrayList<AccountType> arrAccountType;
    public AccountTypeAdapter(Context context, int resource, ArrayList<AccountType> arrAccountType) {
        super(context, resource, arrAccountType);
        this.context = context;
        this.resource = resource;
        this.arrAccountType = arrAccountType;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        AccountTypeAdapter.ViewHolder viewHolder;
        if (convertView == null) {  //Nếu convertView == null thì tạo một convertView mới bằng cách infalte từ row_listview
            convertView = LayoutInflater.from(context).inflate(R.layout.account_type_spinner_item, parent, false);
            //Tạo một viewHolder để lưu các giá view lấy được từ convertView
            viewHolder = new AccountTypeAdapter.ViewHolder();
            viewHolder.tvAccountTypeName = (TextView) convertView.findViewById(R.id.tvSpinnerItem);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (AccountTypeAdapter.ViewHolder) convertView.getTag();
        }
        AccountType accountType = arrAccountType.get(position);
        if(position%2==0)
            convertView.setBackgroundResource(R.color.colorSecondaryLight);
        viewHolder.tvAccountTypeName.setText(accountType.getName());
        viewHolder.tvAccountTypeProperty.setText(accountType.getProperty());
        return convertView;
    }

    public class ViewHolder {
        TextView tvAccountTypeName, tvAccountTypeProperty;
        ImageView imgAccountType;
    }
}
