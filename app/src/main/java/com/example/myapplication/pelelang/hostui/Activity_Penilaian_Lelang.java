package com.example.myapplication.pelelang.hostui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.intro.LoginActivity;
import com.example.myapplication.model.pelelang.RiwayatTestimoniModel;
import com.example.myapplication.pelelang.adapter.AdapterTestimoni;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_Penilaian_Lelang extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterTestimoni adapter;
    private SearchView searchView;
    private ArrayList<RiwayatTestimoniModel> testimoniModels = new ArrayList<>();
    private ArrayList<RiwayatTestimoniModel> testimoniModelsCopy = new ArrayList<>();
    SharedPreferences sharedPreferences;
    private String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penilaian_lelang);
        recyclerView = findViewById(R.id.riwayat);
        searchView = findViewById(R.id.et_query);

        searchView.onActionViewExpanded();
        sharedPreferences = getSharedPreferences(LoginActivity.myLelang, Context.MODE_PRIVATE);
        path = sharedPreferences.getString(LoginActivity.TAG_KODE,"");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.length() > 0){
                    testimoniModelsCopy.clear();
                    for (RiwayatTestimoniModel model : testimoniModels){
                        if (model.getProduk().toLowerCase().contains(query.toLowerCase()) ||
                                model.getNama().toLowerCase().contains(query.toLowerCase())){
                            testimoniModelsCopy.add(model);
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
                else {
                    testimoniModelsCopy.clear();
                    testimoniModelsCopy.addAll(testimoniModels);
                    adapter.notifyDataSetChanged();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                testimoniModelsCopy.clear();
                testimoniModelsCopy.addAll(testimoniModels);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

        generateItem();
    }

    private void generateItem() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ArrayList<RiwayatTestimoniModel>> testimoniListCall = retrofitAPI.feedback(path);
        testimoniListCall.enqueue(new Callback<ArrayList<RiwayatTestimoniModel>>(){
            @Override
            public void onResponse(Call<ArrayList<RiwayatTestimoniModel>> call, Response<ArrayList<RiwayatTestimoniModel>> response) {
                if (response.isSuccessful()) {
                    testimoniModels.addAll(response.body());
                    testimoniModelsCopy.addAll(testimoniModels);
                    adapter = new AdapterTestimoni(Activity_Penilaian_Lelang.this, testimoniModelsCopy);
                    recyclerView.setLayoutManager(new LinearLayoutManager(Activity_Penilaian_Lelang.this));
                    recyclerView.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<ArrayList<RiwayatTestimoniModel>> call, Throwable t) {
                Toast.makeText(Activity_Penilaian_Lelang.this, "Failed to get data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}