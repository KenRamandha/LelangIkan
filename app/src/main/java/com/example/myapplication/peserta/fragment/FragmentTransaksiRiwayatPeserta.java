package com.example.myapplication.peserta.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.admin.adapter.AdapterCardPesertaAdmin;
import com.example.myapplication.admin.activity.DetailPesertaActivity;
import com.example.myapplication.intro.LoginActivity;
import com.example.myapplication.model.admin.AdminPesertaModel;
import com.example.myapplication.model.peserta.PembayaranTransaksiPesertaModel;
import com.example.myapplication.model.peserta.PembayaranTransaksiRiwayatPesertaModel;
import com.example.myapplication.peserta.activity.DetailTransaksiPembayaranActivity;
import com.example.myapplication.peserta.activity.DetailTransaksiRiwayatActivity;
import com.example.myapplication.peserta.adapter.AdapterCardPembayaranTransaksiPeserta;
import com.example.myapplication.peserta.adapter.AdapterCardRiwayatTransaksiPeserta;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentTransaksiRiwayatPeserta extends Fragment implements AdapterCardRiwayatTransaksiPeserta.ItemClickListener{

    private RecyclerView recyclerView;
    private AdapterCardRiwayatTransaksiPeserta adapter;
    private ArrayList<PembayaranTransaksiRiwayatPesertaModel> pembayaranTransaksiRiwayatPesertaModels;

    String path;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transaksi_riwayat_peserta, container, false);

        recyclerView = view.findViewById(R.id.rv_transaksi_riwayat);
        recyclerView.setHasFixedSize(true);

        sharedPreferences = getActivity().getSharedPreferences(LoginActivity.myLelang, Context.MODE_PRIVATE);
        path = sharedPreferences.getString(LoginActivity.TAG_KODE,"");

        generateItem();

        return view;
    }

    private void generateItem() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ArrayList<PembayaranTransaksiRiwayatPesertaModel>> pesertaRiwayatPembayaranListCall = retrofitAPI.getPembayaranTransaksiRiwayatPeserta(path);
        pesertaRiwayatPembayaranListCall.enqueue(new Callback<ArrayList<PembayaranTransaksiRiwayatPesertaModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PembayaranTransaksiRiwayatPesertaModel>> call, Response<ArrayList<PembayaranTransaksiRiwayatPesertaModel>> response) {
                if (response.isSuccessful()) {
                    pembayaranTransaksiRiwayatPesertaModels = new ArrayList<>(response.body());
                    adapter = new AdapterCardRiwayatTransaksiPeserta(getActivity(), pembayaranTransaksiRiwayatPesertaModels);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(adapter);
                    adapter.setClickListener(FragmentTransaksiRiwayatPeserta.this);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PembayaranTransaksiRiwayatPesertaModel>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onClick(View view, int position) {
        final PembayaranTransaksiRiwayatPesertaModel pembayaranModel = pembayaranTransaksiRiwayatPesertaModels.get(position);
        switch (view.getId()){
            default:
                Intent intent = new Intent(getView().getContext(), DetailTransaksiRiwayatActivity.class);
                intent.putExtra("produk",pembayaranModel.getProduk());
                intent.putExtra("harga",pembayaranModel.getHarga_awal());
                intent.putExtra("pelelang",pembayaranModel.getNama_pelelang());
                intent.putExtra("tglLelang",pembayaranModel.getTgl_selesai());
                intent.putExtra("pengirim",pembayaranModel.getNama_pengirim());
                intent.putExtra("noHp",pembayaranModel.getNo_hp());
                intent.putExtra("Nopol",pembayaranModel.getNo_polisi());
                intent.putExtra("tglKirim",pembayaranModel.getTgl_pengiriman());
                intent.putExtra("produk1",pembayaranModel.getProduk());
                intent.putExtra("harga1",pembayaranModel.getNominal_dibayarkan());
                intent.putExtra("alamat",pembayaranModel.getAlamat());
                intent.putExtra("status",pembayaranModel.getStatus());
                startActivity(intent);
                break;
        }
    }
}