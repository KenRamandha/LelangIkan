package com.example.myapplication.pelelang.hostui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.intro.LoginActivity;
import com.example.myapplication.model.pelelang.PembayaranPanitiaModel;
import com.example.myapplication.pelelang.adapter.AdapterPembayaranPelelang;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PembayaranPelelangActivity_old extends AppCompatActivity {
    RecyclerView rvPembayaranPelelang;
    List<PembayaranPanitiaModel> pembayaranPanitiaModelList = new ArrayList<>();
    private AdapterPembayaranPelelang adapter;
    SharedPreferences sharedPreferences;
    private String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran_pelelang);
        sharedPreferences = getSharedPreferences(LoginActivity.myLelang, Context.MODE_PRIVATE);
        path = sharedPreferences.getString(LoginActivity.TAG_KODE,"");
        rvPembayaranPelelang = findViewById(R.id.rvPembayaranPelelang);

        getData();
    }

    // get data
    private void getData() {

        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ArrayList<PembayaranPanitiaModel>> pembayaranPanitiaModelCall = retrofitAPI.hasilBayarPanitia(path);
        pembayaranPanitiaModelCall.enqueue(new Callback<ArrayList<PembayaranPanitiaModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PembayaranPanitiaModel>> call, Response<ArrayList<PembayaranPanitiaModel>> response) {
                if (response.isSuccessful()) {
                    pembayaranPanitiaModelList = response.body();
                    for(int i = 0; i<pembayaranPanitiaModelList.size(); i++){
                        adapter = new AdapterPembayaranPelelang(PembayaranPelelangActivity_old.this,pembayaranPanitiaModelList);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PembayaranPelelangActivity_old.this, LinearLayoutManager.VERTICAL, false);
                        rvPembayaranPelelang.setLayoutManager(linearLayoutManager);
                        rvPembayaranPelelang.setAdapter(adapter);
//                        adapter.setClickListener(RiwayatPelelangFragment.this);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PembayaranPanitiaModel>> call, Throwable t) {
                Toast.makeText(PembayaranPelelangActivity_old.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}