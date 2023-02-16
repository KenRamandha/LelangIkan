package com.example.myapplication.admin.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.admin.adapter.AdapterCardPengirimanAdmin;
import com.example.myapplication.model.admin.AdminPengemasanModel;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PengemasanActivity extends AppCompatActivity {

    private ImageButton btnKembali;
    private RecyclerView recyclerView;
    private AdapterCardPengirimanAdmin adapter;
    private ArrayList<AdminPengemasanModel> adminPengemasanModelArrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_pengiriman);

        btnKembali = findViewById(R.id.btn_kembali_pengemasan);
        recyclerView = findViewById(R.id.rv_pengemasan_admin);
        recyclerView.setHasFixedSize(true);

        adminPengemasanModelArrayList = new ArrayList<>();

        generateItem();


        btnKembali.setOnClickListener(view->{
            onBackPressed();
        });
    }

    private void generateItem() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ArrayList<AdminPengemasanModel>> katalogListCall = retrofitAPI.getAllPengemasanAdmin();
        katalogListCall.enqueue(new Callback<ArrayList<AdminPengemasanModel>>(){
            @Override
            public void onResponse(Call<ArrayList<AdminPengemasanModel>> call, Response<ArrayList<AdminPengemasanModel>> response) {
                if (response.isSuccessful()) {
                    adminPengemasanModelArrayList = response.body();
                    for (int i = 0; i < adminPengemasanModelArrayList.size(); i++) {
                        adapter = new AdapterCardPengirimanAdmin(PengemasanActivity.this,adminPengemasanModelArrayList);
                        LinearLayoutManager manager = new LinearLayoutManager(PengemasanActivity.this, LinearLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(manager);
                        recyclerView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<AdminPengemasanModel>> call, Throwable t) {
                Log.d("ERROR", t.getMessage());
                Toast.makeText(PengemasanActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
