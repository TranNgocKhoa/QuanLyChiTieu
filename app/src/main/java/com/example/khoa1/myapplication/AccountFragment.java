package com.example.khoa1.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.khoa1.myapplication.Adapter.AccountAdapter;
import com.example.khoa1.myapplication.Model.Account;
import com.example.khoa1.myapplication.Model.AccountType;

import java.util.ArrayList;

/**
 * Created by khoa1 on 10/30/2017.
 */
public class AccountFragment extends Fragment {
    private ListView lvAccount;

    public AccountFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_account, container, false);

        lvAccount = (ListView) rootView.findViewById(R.id.lv_Account);

        lvAccount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity().getApplicationContext(),ChinhSuaTaiKhoan.class);
                startActivity(intent);
            }
        });
        AccountType tkNganHang = new AccountType(1, "TaiKhoanNganHang", "Tai khoan nam o ngan hang ACB",ContextCompat.getDrawable(getContext(), R.drawable.bank_account) );
        AccountType tkVi = new AccountType(2, "TaiKhoanVi", "So Tien Trong Vi",ContextCompat.getDrawable(getContext(), R.drawable.wallet_account) );
        ArrayList<Account> arrAccount = new ArrayList<Account>();

        Account acc1 = new Account(1, "Số tiền dư và nợ trong ví", 1200000, 0, tkVi);
        Account acc2 = new Account(2, "Ngân Hàng Vietcombank", 1200000, 0, tkNganHang);
        Account acc3 = new Account(3, "Ngân Hàng ACB", 1200000, 0, tkNganHang);
        Account acc4 = new Account(4, "Ngân Hàng Techcombank", 1200000, 0, tkNganHang);

        arrAccount.add(acc1);
        arrAccount.add(acc2);
        arrAccount.add(acc3);
        arrAccount.add(acc4);

        AccountAdapter accountAdaper = new AccountAdapter(getActivity(), R.layout.account_listview, arrAccount);
        lvAccount.setAdapter(accountAdaper);
//lvAccount.setAdapter(arAdp);
        return rootView;
    }




}