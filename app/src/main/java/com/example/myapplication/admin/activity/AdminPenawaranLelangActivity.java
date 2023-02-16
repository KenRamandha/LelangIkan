package com.example.myapplication.admin.activity;

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


import com.example.myapplication.admin.adapter.AdapterCardKonfirmasiAdmin;
import com.example.myapplication.admin.adapter.AdapterPenawaranLelang;
import com.example.myapplication.model.admin.AdminPenawaranModel;
import com.example.myapplication.model.admin.AdminPesertaModel;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminPenawaranLelangActivity extends AppCompatActivity {
    private ImageButton btnKembali;
    private RecyclerView recyclerView;
    private AdapterPenawaranLelang adapter;

    private ArrayList<AdminPenawaranModel> penawaranModelArrayList;
    private TextInputEditText tisearch;

    private String lelang_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penawaran_admin);
        recyclerView = findViewById(R.id.rv_penawaran_admin);
        btnKembali = findViewById(R.id.btn_kembali_penawaran);
        tisearch = findViewById(R.id.et_query);

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

//        penawaranModelArrayList = new ArrayList<>();

        lelang_id = getIntent().getStringExtra("lelang_id");

        btnKembali.setOnClickListener(view ->onBackPressed());

        generateItem();
    }

    private void generateItem() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ArrayList<AdminPenawaranModel>> penawaranListCall = retrofitAPI.infoTawarjoin(lelang_id);
        penawaranListCall.enqueue(new Callback<ArrayList<AdminPenawaranModel>>(){
            @Override
            public void onResponse(Call<ArrayList<AdminPenawaranModel>> call, Response<ArrayList<AdminPenawaranModel>> response) {
                if (response.isSuccessful()) {
//                    penawaranModelArrayList = response.body();
//                    for (int i = 0; i < penawaranModelArrayList.size(); i++) {
//                        adapter = new AdapterPenawaranLelang(AdminPenawaranLelangActivity.this, penawaranModelArrayList);
//                        recyclerView.setLayoutManager(new LinearLayoutManager(AdminPenawaranLelangActivity.this));
//                        recyclerView.setAdapter(adapter);
//                    }

                    penawaranModelArrayList = new ArrayList<>(response.body());
                    adapter = new AdapterPenawaranLelang(AdminPenawaranLelangActivity.this, penawaranModelArrayList);
                    recyclerView.setAdapter(adapter);
                    Log.d("DDSDSDS", String.valueOf(penawaranModelArrayList));
                }
            }
            @Override
            public void onFailure(Call<ArrayList<AdminPenawaranModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
