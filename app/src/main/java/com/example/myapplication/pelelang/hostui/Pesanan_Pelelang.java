package com.example.myapplication.pelelang.hostui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.pelelang.ListDataTransaksi;
import com.example.myapplication.model.pelelang.ListTransaksiResponse;
import com.example.myapplication.pelelang.adapter.List_ItemPsnanPelelang_Adapter;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Pesanan_Pelelang extends AppCompatActivity {


    private RecyclerView RecyleItemPsnan;
    SharedPreferences sharedPreferences;
    private SearchView searchView;
    public static final String myLelang = "myLelang";
    public final static String TAG_KODE = "kode";
    private String pelelangId;
    private ArrayList<ListDataTransaksi> transaksiArrayList = new ArrayList<>();
    private ArrayList<ListDataTransaksi> transaksiArrayCopyList = new ArrayList<>();
    List_ItemPsnanPelelang_Adapter listItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan_pelelang);

        sharedPreferences = getSharedPreferences(myLelang, MODE_PRIVATE);
        pelelangId = (sharedPreferences.getString(TAG_KODE, ""));

        RecyleItemPsnan = findViewById(R.id.RecycleItemPesananPelelang);
        RecyleItemPsnan.setHasFixedSize(true);
        ImageButton back = findViewById(R.id.btnBackListPesanan);
        searchView = findViewById(R.id.et_query);

        searchView.onActionViewExpanded();

        back.setOnClickListener(view -> onBackPressed());

        getListItmPsnan();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.length() > 0){
                    transaksiArrayCopyList.clear();
                    for (ListDataTransaksi model : transaksiArrayList){
                        if (model.getLelangId().toLowerCase().contains(query.toLowerCase())||
                                model.getNama().toLowerCase().contains(query.toLowerCase())){
                            transaksiArrayCopyList.add(model);
                        }
                    }
                    listItemAdapter.notifyDataSetChanged();
                }
                else {
                    transaksiArrayCopyList.clear();
                    transaksiArrayCopyList.addAll(transaksiArrayList);
                    listItemAdapter.notifyDataSetChanged();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                transaksiArrayCopyList.clear();
                transaksiArrayCopyList.addAll(transaksiArrayList);
                listItemAdapter.notifyDataSetChanged();
                return false;
            }
        });

    }

    public void getListItmPsnan() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ListTransaksiResponse> pelelangListCall = retrofitAPI.getPelelang(pelelangId);
        pelelangListCall.enqueue(new Callback<ListTransaksiResponse>(){
            @Override
            public void onResponse(Call<ListTransaksiResponse> call, Response<ListTransaksiResponse> response) {
                if (response.isSuccessful()) {
                    transaksiArrayList.addAll(response.body().getData().get(0));
                    transaksiArrayCopyList.addAll(transaksiArrayList);
                    listItemAdapter = new List_ItemPsnanPelelang_Adapter(transaksiArrayCopyList);
                    RecyleItemPsnan.setLayoutManager(new LinearLayoutManager(Pesanan_Pelelang.this));
                    RecyleItemPsnan.setAdapter(listItemAdapter);
                }
            }
            @Override
            public void onFailure(Call<ListTransaksiResponse> call, Throwable t) {
                Log.d("TAG ", "onFailure: Error"+t);
            }
        });
    }

}