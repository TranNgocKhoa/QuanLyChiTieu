package com.example.khoa1.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.khoa1.myapplication.Adapter.AccountAdapter;
import com.example.khoa1.myapplication.Database.SQLiteAccount;
import com.example.khoa1.myapplication.Database.SQLiteDataController;
import com.example.khoa1.myapplication.Model.Account;
import com.example.khoa1.myapplication.Model.AccountType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by khoa1 on 10/30/2017.
 */

public class AccountFragment extends Fragment {
    private ListView lvAccount;
    private ArrayList<Account> listAccount;
    public AccountFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_account, container, false);

        //Log.d("aaa",Integer.toString(account.size()));
//        lvAccount = (ListView) rootView.findViewById(R.id.lv_Account);
//        ArrayAdapter<String> arAdp = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, menuitem);
        lvAccount = (ListView) rootView.findViewById(R.id.lv_Account);
        //  ArrayList<Account> arrAccount = new ArrayList<Account>();
        createDB();
        //Test database
        Random rand = new Random();
        int  n = rand.nextInt(10) + 1;
        AccountType accountType=new AccountType(1,"Agribank","blablabla",null);
        Account account=new Account(0,Integer.toString(n),10000,2000,null,accountType);
        final SQLiteAccount account1=new SQLiteAccount(getActivity());
        account1.addAccount(account);
        getListaccount();
        AccountAdapter accountAdaper = new AccountAdapter(getActivity(), R.layout.account_listview, listAccount);
        lvAccount.setAdapter(accountAdaper);

//        lvAccount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(getActivity().getApplicationContext(),ChinhSuaTaiKhoan.class);
//                startActivity(intent);
//            }
//        });
        lvAccount.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                listAccount.get(position).getName();
                Log.d("bbb",listAccount.get(position).getName());
                deleteaccount(getAccountID(listAccount.get(position).getName()));
                getListaccount();
                AccountAdapter accountAdaper = new AccountAdapter(getActivity(), R.layout.account_listview, listAccount);
                lvAccount.setAdapter(accountAdaper);
                return false;
            }
        });
        lvAccount.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity().getApplicationContext(),ChiTietTaiKhoan.class);
                startActivity(intent);
            }
        });
        return rootView;
    }
    private void createDB() {
        // khởi tạo database
        SQLiteDataController sql = new SQLiteDataController(getActivity());
        try {
            sql.isCreatedDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteaccount(int position){
        SQLiteAccount account = new SQLiteAccount(getActivity());
        account.deleteAccount(position);
    }
    private void getListaccount() {
        SQLiteAccount account = new SQLiteAccount(getActivity());
        listAccount = new ArrayList<>();
        listAccount = account.getListAccount();

    }
    private  int getAccountID(String name) {
        SQLiteAccount account = new SQLiteAccount(getActivity());
        return account.getIDAccountbyName(name);
    }

}

