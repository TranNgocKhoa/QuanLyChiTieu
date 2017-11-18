package com.example.khoa1.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.khoa1.myapplication.Adapter.AccountAdapter;
import com.example.khoa1.myapplication.Database.SQLiteAccount;
import com.example.khoa1.myapplication.Database.SQLiteDataController;
import com.example.khoa1.myapplication.Model.Account;
import com.example.khoa1.myapplication.Model.AccountType;
import com.example.khoa1.myapplication.Model.ChiTieu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by khoa1 on 10/30/2017.
 */

public class AccountFragment extends Fragment {
    private ListView lvAccount;
    private ArrayList<Account> listAccount;
    private FloatingActionButton fabThemAccount;
    private SQLiteAccount account;
    public AccountFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_account, container, false);

        //Log.d("aaa",Integer.toString(account.size()));
//        lvAccount = (ListView) rootView.findViewById(R.id.lv_Account);
//        ArrayAdapter<String> arAdp = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, menuitem);
        account = new SQLiteAccount(getContext());
        lvAccount = (ListView) rootView.findViewById(R.id.lv_Account);
        registerForContextMenu(lvAccount);
        fabThemAccount = (FloatingActionButton) rootView.findViewById(R.id.fabAccount);
        fabThemAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),ChinhSuaTaiKhoan.class);
                intent.putExtra("Them", true);
                startActivityForResult(intent, 1);
            }
        });
        //  ArrayList<Account> arrAccount = new ArrayList<Account>();
        createDB();

        final SQLiteAccount account1 = new SQLiteAccount(getContext());

        getListaccount();

        AccountAdapter accountAdaper = new AccountAdapter(getActivity(), R.layout.account_listview, listAccount);
        lvAccount.setAdapter(accountAdaper);

        lvAccount.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity().getApplicationContext(),ChiTietTaiKhoan.class);
                startActivity(intent);
            }
        });
        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        getListaccount();
        AccountAdapter accountAdaper = new AccountAdapter(getActivity(), R.layout.account_listview, listAccount);
        lvAccount.setAdapter(accountAdaper);
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Lựa chọn:");
        //groupId, itemId, order, title
        menu.add(0, v.getId(), 0, "Xem");
        menu.add(0, v.getId(), 0, "Sửa");
        menu.add(0, v.getId(), 0, "Xoá");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        //Lay vi tri click
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;
        if(item.getTitle()=="Xem"){
            Intent intent = new Intent(getActivity().getApplicationContext(),ChiTietTaiKhoan.class);
            //Truyen vao ma chi tieu cho Activity Chi Tiet Hoat Dong
            intent.putExtra("Ma Tai Khoan",((Account)lvAccount.getItemAtPosition(index)).getMaTaiKhoan());
            startActivity(intent);
        }
        else if(item.getTitle()=="Sửa"){
            Intent intent = new Intent(getActivity().getApplicationContext(),ChinhSuaTaiKhoan.class);
            //Truyen vao ma chi tieu cho Activity Chi Tiet Hoat Dong
            intent.putExtra("Ma Tai Khoan",((Account)lvAccount.getItemAtPosition(index)).getMaTaiKhoan());
            intent.putExtra("Them", false);
            startActivityForResult(intent, 1);
            Toast.makeText(getContext(),"Sửa",Toast.LENGTH_LONG).show();
        }
        else if(item.getTitle()=="Xoá"){
            account.deleteAccount(((Account)lvAccount.getItemAtPosition(index)).getMaTaiKhoan());
            getListaccount();
            AccountAdapter accountAdaper = new AccountAdapter(getActivity(), R.layout.account_listview, listAccount);
            lvAccount.setAdapter(accountAdaper);
            Toast.makeText(getContext(),"Xoá",Toast.LENGTH_LONG).show();
        }
        else{
            return false;
        }
        return true;
    }

    public void deleteaccount(int position){
        SQLiteAccount account = new SQLiteAccount(getActivity());
        account.deleteAccount(position);
    }
    private void getListaccount() {
        SQLiteAccount account = new SQLiteAccount(getActivity());
        listAccount = new ArrayList<Account>();
        //account.DeleteAllAccount();
       listAccount = account.getListAccount();

    }
    private  int getAccountID(String name) {
        SQLiteAccount account = new SQLiteAccount(getActivity());
        return account.getIDAccountbyName(name);
    }

}

