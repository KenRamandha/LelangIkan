package com.example.myapplication.peserta.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.intro.LoginActivity;
import com.example.myapplication.model.peserta.TestimoniModel;
import com.example.myapplication.peserta.adapter.AdapterCardTestimoni;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestimoniActivity extends AppCompatActivity implements AdapterCardTestimoni.ItemClickListener {
    private ImageButton btnKembali;
    private RecyclerView recyclerView;
    private AdapterCardTestimoni adapter;
    private ArrayList<TestimoniModel> testimoniModels;

    String path;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testimoni);

        btnKembali = findViewById(R.id.btn_kembali_testimoni);
        recyclerView = findViewById(R.id.rv_testimoni);

        testimoniModels = new ArrayList<>();

        btnKembali.setOnClickListener(view -> {
            onBackPressed();
        });

        sharedPreferences = getSharedPreferences(LoginActivity.myLelang, Context.MODE_PRIVATE);
        path = sharedPreferences.getString(LoginActivity.TAG_KODE,"");
        Log.d("kode", path);

        generateItem();
    }

    private void generateItem() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ArrayList<TestimoniModel>> testimoniListCall = retrofitAPI.getTestimoniPeserta(path);
        testimoniListCall.enqueue(new Callback<ArrayList<TestimoniModel>>(){
            @Override
            public void onResponse(Call<ArrayList<TestimoniModel>> call, Response<ArrayList<TestimoniModel>> response) {
                if (response.isSuccessful()) {
                    testimoniModels = response.body();
                    for (int i = 0; i < testimoniModels.size(); i++) {
                        adapter = new AdapterCardTestimoni(TestimoniActivity.this, testimoniModels);
                        recyclerView.setLayoutManager(new LinearLayoutManager(TestimoniActivity.this));
                        recyclerView.setAdapter(adapter);
                        adapter.setClickListener(TestimoniActivity.this);

                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<TestimoniModel>> call, Throwable t) {
                Toast.makeText(TestimoniActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view, int position) {
        final TestimoniModel testimoniModel = testimoniModels.get(position);
        switch (view.getId()) {
            default:
                Intent intent = new Intent(this, KonfirmasiProdukActivity.class);
                intent.putExtra("lelang_id", testimoniModel.getLelang_id());
                intent.putExtra("image1", testimoniModel.getImage1());
                intent.putExtra("image2", testimoniModel.getImage2());
                intent.putExtra("image3", testimoniModel.getImage3());
                intent.putExtra("image4", testimoniModel.getImage4());
                intent.putExtra("produk", testimoniModel.getProduk());
                intent.putExtra("waktu_selesai", testimoniModel.getTgl_selesai());
                intent.putExtra("pelelang", testimoniModel.getNama());
                intent.putExtra("keterangan", testimoniModel.getTestimoni_pemenang());
                startActivity(intent);
                break;
        }
    }
}
