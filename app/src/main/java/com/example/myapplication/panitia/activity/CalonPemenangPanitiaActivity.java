package com.example.myapplication.panitia.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.panitia.ProdukPanitiaModel;
import com.example.myapplication.panitia.adapter.AdapterCardCalonPemenangPanitia;
import com.example.myapplication.panitia.adapter.AdapterCardVerifikasiPanitia;
import com.example.myapplication.panitia.fragment.FragmentVerifikasiPanitia;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalonPemenangPanitiaActivity extends AppCompatActivity implements AdapterCardCalonPemenangPanitia.ItemClickListener {

    private RecyclerView recyclerView;
    private ImageButton btnBack;
    private AdapterCardCalonPemenangPanitia adapter;
    private ArrayList<ProdukPanitiaModel> produkPanitiaModels;

    SharedPreferences sharedPreferences;

    public static final String idCalon = "idCalon";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panitia_calon_pemenang);

        btnBack = findViewById(R.id.btn_kembali_calon_pemenang);
        recyclerView = findViewById(R.id.rv_calon_pemenang_panitia);
        recyclerView.hasFixedSize();

        btnBack.setOnClickListener(view ->{
            onBackPressed();
            finish();
        });
        
        generateItem();
    }

    private void generateItem() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ArrayList<ProdukPanitiaModel>> produkPanitiaListCall = retrofitAPI.getAllVerifProductPanitia();
        produkPanitiaListCall.enqueue(new Callback<ArrayList<ProdukPanitiaModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ProdukPanitiaModel>> call, Response<ArrayList<ProdukPanitiaModel>> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(getView().getContext(), "bebas", Toast.LENGTH_SHORT).show();
                    produkPanitiaModels = response.body();
                    for (int i = 0; i < produkPanitiaModels.size(); i++) {
                        adapter = new AdapterCardCalonPemenangPanitia(CalonPemenangPanitiaActivity.this, produkPanitiaModels);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(CalonPemenangPanitiaActivity.this, LinearLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(adapter);
                        adapter.setClickListener(CalonPemenangPanitiaActivity.this);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ProdukPanitiaModel>> call, Throwable t) {
                Toast.makeText(CalonPemenangPanitiaActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view, int position) {
        final ProdukPanitiaModel panitiaModel = produkPanitiaModels.get(position);
        switch (view.getId()) {
            default:
                Intent intent = new Intent(getApplicationContext(), ListPenawaranPanitiaActivity.class);
                intent.putExtra("id", panitiaModel.getLelang_id());
                intent.putExtra("produk", panitiaModel.getProduk());
                startActivity(intent);
                break;
        }
    }
}
