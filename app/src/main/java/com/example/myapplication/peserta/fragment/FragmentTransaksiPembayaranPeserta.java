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
import com.example.myapplication.peserta.activity.DetailTransaksiPembayaranActivity;
import com.example.myapplication.peserta.adapter.AdapterCardPembayaranTransaksiPeserta;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentTransaksiPembayaranPeserta extends Fragment implements AdapterCardPembayaranTransaksiPeserta.ItemClickListener {

    private RecyclerView recyclerView;
    private AdapterCardPembayaranTransaksiPeserta adapter;
    private ArrayList<PembayaranTransaksiPesertaModel> pembayaranTransaksiPesertaModelArrayList;

    String path;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transaksi_pembayaran_peserta, container, false);

        recyclerView = view.findViewById(R.id.rv_transaksi_pembayaran_peserta);
        recyclerView.setHasFixedSize(true);

        sharedPreferences = getActivity().getSharedPreferences(LoginActivity.myLelang, Context.MODE_PRIVATE);
        path = sharedPreferences.getString(LoginActivity.TAG_KODE,"");

        generateItem();

        return view;
    }

    private void generateItem() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ArrayList<PembayaranTransaksiPesertaModel>> pesertaPembayaranListCall = retrofitAPI.getPembayaranTransaksiPeserta(path);
        pesertaPembayaranListCall.enqueue(new Callback<ArrayList<PembayaranTransaksiPesertaModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PembayaranTransaksiPesertaModel>> call, Response<ArrayList<PembayaranTransaksiPesertaModel>> response) {
                if (response.isSuccessful()) {
                    pembayaranTransaksiPesertaModelArrayList = new ArrayList<>(response.body());
                    adapter = new AdapterCardPembayaranTransaksiPeserta(getActivity(), pembayaranTransaksiPesertaModelArrayList);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(adapter);
                    adapter.setClickListener(FragmentTransaksiPembayaranPeserta.this);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PembayaranTransaksiPesertaModel>> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view, int position) {
        final PembayaranTransaksiPesertaModel pembayaranModel = pembayaranTransaksiPesertaModelArrayList.get(position);
        switch (view.getId()){
            default:
                Intent intent = new Intent(getView().getContext(), DetailTransaksiPembayaranActivity.class);
                intent.putExtra("status",pembayaranModel.getStatus());
                intent.putExtra("produk",pembayaranModel.getProduk());
                intent.putExtra("nominal",pembayaranModel.getTotal_bayar());
                intent.putExtra("pelelang",pembayaranModel.getNama_pelelang());
                intent.putExtra("tgl_lelang",pembayaranModel.getTgl_selesai());
                intent.putExtra("alamat",pembayaranModel.getAlamat());
                intent.putExtra("hp",pembayaranModel.getTelp());
                startActivity(intent);
                break;
        }
    }
}