package com.example.myapplication.peserta.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.peserta.KatalogModel;
import com.example.myapplication.peserta.adapter.AdapterCardPenawaranLelang;
import com.example.myapplication.peserta.activity.DetailLelangActivity;
import com.example.myapplication.peserta.adapter.AdapterCardPenawaranLelangBesar;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LelangFragment extends Fragment implements AdapterCardPenawaranLelangBesar.ItemClickListener{
    private RecyclerView recyclerView;
    private AdapterCardPenawaranLelangBesar adapter;
    private ArrayList<KatalogModel> katalogModelArrayList;

    private SharedPreferences detail;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lelang, container, false);

        recyclerView = view.findViewById(R.id.rv_penawaran_lelang);
        recyclerView.setHasFixedSize(true);

        katalogModelArrayList = new ArrayList<>();

        generateItem();

        return view;
    }

    private void generateItem() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ArrayList<KatalogModel>> katalogListCall = retrofitAPI.getAllProduct();
        katalogListCall.enqueue(new Callback<ArrayList<KatalogModel>>(){
            @Override
            public void onResponse(Call<ArrayList<KatalogModel>> call, Response<ArrayList<KatalogModel>> response) {
                if (response.isSuccessful()) {
                    katalogModelArrayList = response.body();
                    for (int i = 0; i < katalogModelArrayList.size(); i++) {
                        adapter = new AdapterCardPenawaranLelangBesar(getActivity(), katalogModelArrayList);
//                        GridLayoutManager manager = new GridLayoutManager(getView().getContext(), 2);
//                        recyclerView.setLayoutManager(manager);
                        recyclerView.setAdapter(adapter);
                        adapter.setClickListener(LelangFragment.this);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<KatalogModel>> call, Throwable t) {
                Toast.makeText(getView().getContext(), "Fail to get data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view, int position) {
        final KatalogModel katalogModel = katalogModelArrayList.get(position);
        switch (view.getId()) {
            default:
                Intent intent = new Intent(getActivity(), DetailLelangActivity.class);
                intent.putExtra("lelang_id", katalogModel.getLelang_id());
                intent.putExtra("image1", katalogModel.getImage1());
                intent.putExtra("image2", katalogModel.getImage2());
                intent.putExtra("image3", katalogModel.getImage3());
                intent.putExtra("image4", katalogModel.getImage4());
                intent.putExtra("produk", katalogModel.getProduk());
                intent.putExtra("waktu_awal", katalogModel.getTgl_mulai());
                intent.putExtra("waktu_selesai", katalogModel.getTgl_selesai());
                intent.putExtra("harga_awal", katalogModel.getHarga_awal());
                intent.putExtra("pelelang_id", katalogModel.getPelelang_id());
                intent.putExtra("deskripsi_produk", katalogModel.getDeskripsi_produk());
                intent.putExtra("bayar_sekarang", katalogModel.getHarga_beli_sekarang());
                intent.putExtra("jumlah", katalogModel.getJumlah());
                startActivity(intent);
                break;
        }
    }
}