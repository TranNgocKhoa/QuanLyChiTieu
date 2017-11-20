package com.example.khoa1.myapplication;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.khoa1.myapplication.Database.SQLiteAccount;
import com.example.khoa1.myapplication.Model.Account;
import com.example.khoa1.myapplication.Model.AccountType;

import java.util.ArrayList;

public class ChinhSuaTaiKhoan extends AppCompatActivity {
    Toolbar toolbar;
    private boolean Them;
    private int maTaiKhoan;
    private ImageView imageTaiKhoan;
    private TextView chonImage;
    private EditText tenTaiKhoan;
    private EditText soDu;
    private EditText soNo;
    private int imageId = R.drawable.wallet_account;
    SQLiteAccount sqlAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinh_sua_tai_khoan);
        //Enable Toolbar back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        sqlAccount = new SQLiteAccount(getApplicationContext());
        chonImage = (TextView) findViewById(R.id.tvChonHinh);
        imageTaiKhoan = (ImageView) findViewById(R.id.imageTaiKhoan);
        tenTaiKhoan = (EditText) findViewById(R.id.tenTaiKhoan);
        soDu = (EditText) findViewById(R.id.soDu);
        soNo = (EditText) findViewById(R.id.soNo);
        Them = getIntent().getBooleanExtra("Them", true);
        if(!Them) {
            maTaiKhoan = getIntent().getIntExtra("Ma Tai Khoan", 0);
            Account account = sqlAccount.getAccountByID(maTaiKhoan);
            if(account != null)
            {
                imageTaiKhoan.setImageResource(account.getPicture());
                tenTaiKhoan.setText(account.getTenTaiKhoan());
                soDu.setText(String.valueOf(account.getSoTienDu()));
                soNo.setText(String.valueOf(account.getSoTienNo()));
            }
        }
        chonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ChonIconTaiKhoan.class);
                startActivityForResult(i, 1);
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_menu_them_hoat_dong, menu);
        // Action View
        //MenuItem searchItem = menu.findItem(R.id.action_search);
        //SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        // Configure the search info and add any event listeners
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_favorite:
                saveMenuClick();
                this.finish();
                return true;
            case R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean isThem() {
        return Them;
    }

    public void setThem(boolean them) {
        Them = them;
    }

    private void saveMenuClick()
    {
        SQLiteAccount sqlAccount = new SQLiteAccount(getApplicationContext());

        double SoDu = Double.parseDouble(soDu.getText().toString());
        double SoNo = Double.parseDouble(soNo.getText().toString());
        String TenTaiKhoan = tenTaiKhoan.getText().toString();
        if(Them) {
            Account account = new Account(0, TenTaiKhoan, SoDu, SoNo, imageId);
            sqlAccount.addAccount(account);
            ArrayList<Account> listAccount = sqlAccount.getListAccount();
            int k = 0;
        }
        else
        {
            Account account = new Account(maTaiKhoan, TenTaiKhoan, SoDu, SoNo, imageId);
            sqlAccount.updateAccount(account);
        }

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                int id = data.getIntExtra("Image", 0);
                if(id!=0)
                {
                    imageTaiKhoan.setImageResource(id);
                    imageId = id;
                    Toast.makeText(this, String.valueOf(id), Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
