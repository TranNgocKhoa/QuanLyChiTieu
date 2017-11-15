package com.example.khoa1.myapplication;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.khoa1.myapplication.Model.AccountType;

import java.util.ArrayList;

public class ChinhSuaTaiKhoan extends AppCompatActivity {
    private ArrayList<AccountType> arrAccType;
    Spinner spinnerAccountType;
    TextView tvAccountTypeProperty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinh_sua_tai_khoan);
        //Enable Toolbar back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        spinnerAccountType = (Spinner) findViewById(R.id.spinnerLoaiTk);
        tvAccountTypeProperty = (TextView) findViewById(R.id.tvAccountTypeProperty);
        arrAccType = createAccType();
        ArrayAdapter adapter = new ArrayAdapter(this,
                R.layout.account_type_spinner_item, getAccountTypeName());

        spinnerAccountType.setAdapter(adapter);

        spinnerAccountType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                AccountType accType = arrAccType.get(i);
                tvAccountTypeProperty.setText(accType.getProperty());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if(id == android.R.id.home)
        {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public ArrayList<AccountType> createAccType()
    {
        AccountType tkNganHang = new AccountType(1, "Tài Khoản Ngân Hàng",
                "Tai khoan nam o ngan hang ACB",
                ContextCompat.getDrawable(getApplicationContext(), R.drawable.bank_account) );
        AccountType tkVi = new AccountType(2, "Tài Khoản Ví",
                "So Tien Trong Vi",
                ContextCompat.getDrawable(getApplicationContext(), R.drawable.wallet_account) );
        ArrayList<AccountType> arr = new ArrayList<AccountType>();
        arr.add(tkNganHang);
        arr.add(tkVi);
        return arr;
    }

    public ArrayList<String> getAccountTypeName()
    {
        ArrayList<String> arr = new ArrayList<String>();

        for (AccountType item: arrAccType
             ) {
            arr.add(item.getName());

        }
        return arr;
    }
}
