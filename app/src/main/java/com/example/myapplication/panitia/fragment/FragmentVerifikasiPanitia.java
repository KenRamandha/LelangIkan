package com.example.myapplication.panitia.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.admin.adapter.AdapterCardPesertaAdmin;
import com.example.myapplication.admin.fragment.FragmentPesertaAdmin;
import com.example.myapplication.model.admin.AdminPesertaModel;
import com.example.myapplication.model.panitia.ProdukPanitiaModel;
import com.example.myapplication.panitia.activity.DetailPesertaPanitiaActivity;
import com.example.myapplication.panitia.activity.DetailVerifPanitiaActivity;
import com.example.myapplication.panitia.adapter.AdapterCardPesertaPanitia;
import com.example.myapplication.panitia.adapter.AdapterCardVerifikasiPanitia;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentVerifikasiPanitia extends Fragment implements AdapterCardVerifikasiPanitia.ItemClickListener {
    private RecyclerView recyclerView;
    private AdapterCardVerifikasiPanitia adapter;
    private ArrayList<ProdukPanitiaModel> produkPanitiaModels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_verifikasi_panitia, container, false);

        recyclerView = view.findViewById(R.id.rv_verifikasi_panitia);
        recyclerView.setHasFixedSize(true);

        produkPanitiaModels = new ArrayList<>();

        generateItem();
        return view;
    }

    private void generateItem() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ArrayList<ProdukPanitiaModel>> produkPanitiaListCall = retrofitAPI.getAllProductPanitia();
        produkPanitiaListCall.enqueue(new Callback<ArrayList<ProdukPanitiaModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ProdukPanitiaModel>> call, Response<ArrayList<ProdukPanitiaModel>> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(getView().getContext(), "bebas", Toast.LENGTH_SHORT).show();
                    produkPanitiaModels = response.body();
                    for (int i = 0; i < produkPanitiaModels.size(); i++) {
                        adapter = new AdapterCardVerifikasiPanitia(getView().getContext(), produkPanitiaModels);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(adapter);
                        adapter.setClickListener(FragmentVerifikasiPanitia.this);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ProdukPanitiaModel>> call, Throwable t) {
                Toast.makeText(getView().getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view, int position) {
        final ProdukPanitiaModel panitiaModel = produkPanitiaModels.get(position);
        switch (view.getId()){
            default:
//                Log.d("coba", String.valueOf(panitiaModel.getIncremental_vaule()));
                Intent intent = new Intent(getView().getContext(), DetailVerifPanitiaActivity.class);
                intent.putExtra("id",panitiaModel.getLelang_id());
                intent.putExtra("produk",panitiaModel.getProduk());
                intent.putExtra("deskripsi",panitiaModel.getDeskripsi_produk());
                intent.putExtra("jumlah",panitiaModel.getJumlah());
                intent.putExtra("penawaran",panitiaModel.getHarga_awal());
                intent.putExtra("minim",panitiaModel.getHarga_minimal_diterima());
                intent.putExtra("incremental",panitiaModel.getIncremental_value());
                intent.putExtra("beli",panitiaModel.getHarga_beli_sekarang());
                intent.putExtra("tglmulai",panitiaModel.getTgl_mulai());
                intent.putExtra("tglselesai",panitiaModel.getTgl_selesai());
                intent.putExtra("keterangan",panitiaModel.getKeterangan());
                intent.putExtra("img1",panitiaModel.getImage1());
                intent.putExtra("img2",panitiaModel.getImage2());
                intent.putExtra("img3",panitiaModel.getImage3());
                intent.putExtra("img4",panitiaModel.getImage4());
                startActivity(intent);
                break;
        }
    }
}