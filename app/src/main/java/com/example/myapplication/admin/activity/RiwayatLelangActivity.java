package com.example.myapplication.admin.activity;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.admin.adapter.AdapterCardRiwayatAdmin;


import com.example.myapplication.model.admin.AdminRiwayatModel;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiwayatLelangActivity extends AppCompatActivity{

    private ImageButton btnKembali;
    private RecyclerView recyclerView;
    private AdapterCardRiwayatAdmin adapter;
    private TextInputEditText tisearch;

    private ArrayList<AdminRiwayatModel> riwayatModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_admin);
        recyclerView = findViewById(R.id.rv_riwayat_admin);
        btnKembali = findViewById(R.id.btn_kembali_riwayat);
        tisearch = findViewById(R.id.et_query);

        riwayatModelArrayList = new ArrayList<>();

//        findViewById(R.id.btn_search).setOnClickListener(view -> {
//            Log.d("KKK", tisearch.getText().toString());
//            adapter.getFilter().filter(tisearch.getText().toString());
//        });
//
//        btnKembali.setOnClickListener(view ->{
//            onBackPressed();
//        });
//
//        generateItem();
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
        Call<ArrayList<AdminRiwayatModel>> pemenangListCall = retrofitAPI.infoRiwayatLelang();
        pemenangListCall.enqueue(new Callback<ArrayList<AdminRiwayatModel>>(){
            @Override
            public void onResponse(Call<ArrayList<AdminRiwayatModel>> call, Response<ArrayList<AdminRiwayatModel>> response) {
                if (response.isSuccessful()) {
                    riwayatModelArrayList = response.body();
                    for (int i = 0; i < riwayatModelArrayList.size(); i++) {
                        adapter = new AdapterCardRiwayatAdmin(RiwayatLelangActivity.this, riwayatModelArrayList);
                        recyclerView.setLayoutManager(new LinearLayoutManager(RiwayatLelangActivity.this));
                        recyclerView.setAdapter(adapter);
                    }
                }
            }
            @Override
            public void onFailure(Call<ArrayList<AdminRiwayatModel>> call, Throwable t) {
                Toast.makeText(RiwayatLelangActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

