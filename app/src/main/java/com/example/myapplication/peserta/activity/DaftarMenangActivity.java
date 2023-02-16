package com.example.myapplication.peserta.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.intro.LoginActivity;
import com.example.myapplication.model.peserta.PemenangPesertaModel;
import com.example.myapplication.peserta.adapter.AdapterCardMenang;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarMenangActivity extends AppCompatActivity implements AdapterCardMenang.ItemClickListener {

    private ImageButton btnKembali;
    private RecyclerView recyclerView;
    private AdapterCardMenang adapter;
    private LinearLayout linearLayout;

    private ArrayList<PemenangPesertaModel> pemenangPesertaModelArrayList;

    SharedPreferences sharedPreferences;

    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_menang);

        btnKembali = findViewById(R.id.btn_back_menang);
        recyclerView = (RecyclerView)findViewById(R.id.rv_data_menang);
        linearLayout = findViewById(R.id.ln_menang_lelang);

        pemenangPesertaModelArrayList = new ArrayList<>();

        sharedPreferences = getSharedPreferences(LoginActivity.myLelang, Context.MODE_PRIVATE);
        path = sharedPreferences.getString(LoginActivity.TAG_KODE,"");


        btnKembali.setOnClickListener(view -> onBackPressed());



        generateItem();
    }

    private void generateItem() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ArrayList<PemenangPesertaModel>> pemenangListCall = retrofitAPI.getPemenangPeserta(path);
        pemenangListCall.enqueue(new Callback<ArrayList<PemenangPesertaModel>>(){
            @Override
            public void onResponse(Call<ArrayList<PemenangPesertaModel>> call, Response<ArrayList<PemenangPesertaModel>> response) {
                if (response.isSuccessful()) {
                    pemenangPesertaModelArrayList = response.body();
                    for (int i = 0; i < pemenangPesertaModelArrayList.size(); i++) {
                        adapter = new AdapterCardMenang(DaftarMenangActivity.this,pemenangPesertaModelArrayList);
                        LinearLayoutManager manager = new LinearLayoutManager(DaftarMenangActivity.this, LinearLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(manager);
                        recyclerView.setAdapter(adapter);
                        adapter.setClickListener(DaftarMenangActivity.this);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PemenangPesertaModel>> call, Throwable t) {
                Toast.makeText(DaftarMenangActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view, int position) {
        final PemenangPesertaModel pemenangPesertaModel = pemenangPesertaModelArrayList.get(position);
        switch (view.getId()){
            default:
//                Log.d("tess",pemenangPesertaModel.getLelang_id());
            Intent intent = new Intent(DaftarMenangActivity.this, BayarPemenangActivity.class);
            intent.putExtra("harga_tawar", pemenangPesertaModel.getHarga_tawar());
            intent.putExtra("lelang_id", pemenangPesertaModel.getLelang_id());
            startActivity(intent);
        }
    }
}
