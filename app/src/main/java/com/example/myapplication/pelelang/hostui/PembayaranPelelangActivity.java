package com.example.myapplication.pelelang.hostui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
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

public class PembayaranPelelangActivity extends AppCompatActivity {

    RecyclerView rvPembayaranPelelang;
    ImageView vbukti_bayar;
    private SearchView searchView;
    List<PembayaranPanitiaModel> pembayaranPanitiaModelList = new ArrayList<>();
    List<PembayaranPanitiaModel> pembayaranPanitiaCopyList = new ArrayList<>();
    private AdapterPembayaranPelelang adapter;
    SharedPreferences sharedPreferences;
    private String path;
//    String bukti_bayar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran_pelelang);
//        vbukti_bayar = findViewById(R.id.bukti_bayar);
        searchView = findViewById(R.id.et_query);

        searchView.onActionViewExpanded();

        rvPembayaranPelelang = findViewById(R.id.rvPembayaranPelelang);
        sharedPreferences = getSharedPreferences(LoginActivity.myLelang, Context.MODE_PRIVATE);
        path = sharedPreferences.getString(LoginActivity.TAG_KODE,"");
//        Glide.with(PembayaranPelelangActivity.this)
//                .load(bukti_bayar)
//                .into(vbukti_bayar);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.length() > 0){
                    pembayaranPanitiaCopyList.clear();
                    for (PembayaranPanitiaModel model : pembayaranPanitiaModelList){
                        if (model.getPanitia_id().toLowerCase().contains(query.toLowerCase()) ||
                                model.getNama().toLowerCase().contains(query.toLowerCase())||
                        model.getLelang_id().toLowerCase().contains(query.toLowerCase())||
                        model.getPanitia_id().toLowerCase().contains(query.toLowerCase())){
                            pembayaranPanitiaCopyList.add(model);
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
                else {
                    pembayaranPanitiaCopyList.clear();
                    pembayaranPanitiaCopyList.addAll(pembayaranPanitiaModelList);
                    adapter.notifyDataSetChanged();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                pembayaranPanitiaCopyList.clear();
                pembayaranPanitiaCopyList.addAll(pembayaranPanitiaModelList);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

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
                    pembayaranPanitiaModelList.addAll(response.body());
                    pembayaranPanitiaCopyList.addAll(pembayaranPanitiaModelList);
                    adapter = new AdapterPembayaranPelelang(PembayaranPelelangActivity.this,pembayaranPanitiaCopyList);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(PembayaranPelelangActivity.this, LinearLayoutManager.VERTICAL, false);
                    rvPembayaranPelelang.setLayoutManager(linearLayoutManager);
                    rvPembayaranPelelang.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PembayaranPanitiaModel>> call, Throwable t) {
                Toast.makeText(PembayaranPelelangActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
