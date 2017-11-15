package com.example.khoa1.myapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khoa1.myapplication.Model.Account;
import com.example.khoa1.myapplication.R;

import java.util.ArrayList;

/**
 * Created by khoa1 on 10/31/2017.
 */

public class AccountAdapter extends ArrayAdapter<Account> {
    private Context context;
    private int resource;
    private ArrayList<Account> arrAccount;

    public AccountAdapter(Context context, int resource, ArrayList<Account> arrAccount) {
        super(context, resource, arrAccount);
        this.context = context;
        this.resource = resource;
        this.arrAccount = arrAccount;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder viewHolder;
        if (convertView == null) {  //Nếu convertView == null thì tạo một convertView mới bằng cách infalte từ row_listview
            convertView = LayoutInflater.from(context).inflate(R.layout.account_listview, parent, false);
            //Tạo một viewHolder để lưu các giá view lấy được từ convertView
            viewHolder = new ViewHolder();
            viewHolder.imgAccountType = (ImageView)convertView.findViewById(R.id.imgAccountType);
            viewHolder.tvAccountName = (TextView) convertView.findViewById(R.id.tvAccountName);
            viewHolder.tvBalance = (TextView) convertView.findViewById(R.id.tvBalance);
            viewHolder.tvDebit = (TextView) convertView.findViewById(R.id.tvDebit);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Account account = arrAccount.get(position);
        if(position%2==0)
            convertView.setBackgroundResource(R.color.colorSecondaryLight);
        viewHolder.imgAccountType.setImageDrawable(account.getAccountType().getImage());
        viewHolder.tvAccountName.setText(account.getName());
        StringBuilder str = new StringBuilder();
        str.append("Số dư: ");
        str.append(account.getBalance());
        viewHolder.tvBalance.setText(str.toString());
        str.setLength(0);
        str.append("Số nợ: ");
        str.append(account.getDebit());
        viewHolder.tvDebit.setText(str.toString());

        return convertView;
    }

    public class ViewHolder {
        TextView tvAccountName, tvBalance, tvDebit;
        ImageView imgAccountType;
    }

}


