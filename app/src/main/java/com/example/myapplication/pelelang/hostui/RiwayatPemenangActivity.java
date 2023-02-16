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
import com.example.myapplication.model.pelelang.RiwayatPemenangModel;
import com.example.myapplication.pelelang.adapter.AdapterCardRiwayatPemenang;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiwayatPemenangActivity extends AppCompatActivity {

    RecyclerView rv_pemenang_pelelang;
    ArrayList<RiwayatPemenangModel> riwayatPemenangModels = new ArrayList<>();
    ArrayList<RiwayatPemenangModel> riwayatPemenangModelsCopy = new ArrayList<>();
    private AdapterCardRiwayatPemenang adapter;
    SharedPreferences sharedPreferences;
    private SearchView searchView;
    private String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemenang_pelelang);
        sharedPreferences = getSharedPreferences(LoginActivity.myLelang, Context.MODE_PRIVATE);
        path = sharedPreferences.getString(LoginActivity.TAG_KODE,"");
        rv_pemenang_pelelang = findViewById(R.id.RecycleItemPesananPelelang);
        searchView = findViewById(R.id.et_query);

        searchView.onActionViewExpanded();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.length() > 0){
                    riwayatPemenangModelsCopy.clear();
                    for (RiwayatPemenangModel model : riwayatPemenangModels){
                        if (model.getProduk().toLowerCase().contains(query.toLowerCase()) ||
                                model.getNama().toLowerCase().contains(query.toLowerCase())||
                                model.getLelang_id().toLowerCase().contains(query.toLowerCase())||
                                model.getHargatawar().toLowerCase().contains(query.toLowerCase())){
                            riwayatPemenangModelsCopy.add(model);
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
                else {
                    riwayatPemenangModelsCopy.clear();
                    riwayatPemenangModelsCopy.addAll(riwayatPemenangModels);
                    adapter.notifyDataSetChanged();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                riwayatPemenangModelsCopy.clear();
                riwayatPemenangModelsCopy.addAll(riwayatPemenangModels);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

        getData();
    }

    // get data
    private void getData() {

        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ArrayList<RiwayatPemenangModel>> riwayatPemenangModelsCall = retrofitAPI.getRiwayatPemenang(path);
        riwayatPemenangModelsCall.enqueue(new Callback<ArrayList<RiwayatPemenangModel>>() {
            @Override
            public void onResponse(Call<ArrayList<RiwayatPemenangModel>> call, Response<ArrayList<RiwayatPemenangModel>> response) {
                if (response.isSuccessful()) {
                    riwayatPemenangModels.addAll(response.body());
                    riwayatPemenangModelsCopy.addAll(riwayatPemenangModels);
                    adapter = new AdapterCardRiwayatPemenang(RiwayatPemenangActivity.this,riwayatPemenangModelsCopy);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RiwayatPemenangActivity.this, LinearLayoutManager.VERTICAL, false);
                    rv_pemenang_pelelang.setLayoutManager(linearLayoutManager);
                    rv_pemenang_pelelang.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<RiwayatPemenangModel>> call, Throwable t) {
                Toast.makeText(RiwayatPemenangActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
