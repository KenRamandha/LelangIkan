package com.example.myapplication.admin.activity;


import static com.example.myapplication.admin.adapter.AdapterCardDepositAdmin.listDeposit;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.admin.adapter.AdapterCardDepositAdmin;


import com.example.myapplication.model.admin.AdminDepositModel;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DepositLelangActivity extends AppCompatActivity implements AdapterCardDepositAdmin.ItemClickListener{

    private ImageButton btnKembali;
    private RecyclerView recyclerView;
    private AdapterCardDepositAdmin adapter;
    private TextInputEditText tisearch;

    private ArrayList<AdminDepositModel> depositModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deposit_admin);
        recyclerView = findViewById(R.id.rv_deposit_admin);
        btnKembali = findViewById(R.id.btn_kembali_deposit);
        tisearch = findViewById(R.id.et_query);

       depositModelArrayList = new ArrayList<>();


        tisearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                adapter.getFilter().filter(editable);
            }
        });

        btnKembali.setOnClickListener(view -> onBackPressed());

        generateItem();
    }



    private void generateItem() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ArrayList<AdminDepositModel>> depositListCall = retrofitAPI.infoDeposit();
        depositListCall.enqueue(new Callback<ArrayList<AdminDepositModel>>(){
            @Override
            public void onResponse(Call<ArrayList<AdminDepositModel>> call, Response<ArrayList<AdminDepositModel>> response) {
                if (response.isSuccessful()) {
                    depositModelArrayList = response.body();
                    for (int i = 0; i < depositModelArrayList.size(); i++) {
                        adapter = new AdapterCardDepositAdmin(DepositLelangActivity.this, depositModelArrayList);
                        recyclerView.setLayoutManager(new LinearLayoutManager(DepositLelangActivity.this));
                        recyclerView.setAdapter(adapter);
                        adapter.setClickListener(DepositLelangActivity.this);
                    }
                }
            }
            @Override
            public void onFailure(Call<ArrayList<AdminDepositModel>> call, Throwable t) {
                Toast.makeText(DepositLelangActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onClick(View view, int position) {
        final AdminDepositModel depositModel = listDeposit.get(position);
        switch (view.getId()){
            default:
                Intent intent = new Intent(this, DetailPembayaranDeposit.class);

                intent.putExtra("tgl_deposit",depositModel.getTgl_deposit());
                intent.putExtra("nominal_deposit",depositModel.getNominal_deposit());

                intent.putExtra("nama",depositModel.getNama());
                intent.putExtra("bukti_pembayaran",depositModel.getBukti_pembayaran());
                startActivity(intent);
                break;
        }
    }
}



