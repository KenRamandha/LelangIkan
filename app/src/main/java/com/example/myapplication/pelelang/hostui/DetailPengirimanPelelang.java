package com.example.myapplication.pelelang.hostui;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityDetailPengirimanPelelangBinding;
import com.example.myapplication.model.pelelang.DataPengiriman;
import com.google.gson.Gson;

public class DetailPengirimanPelelang extends AppCompatActivity {

    private ActivityDetailPengirimanPelelangBinding binding;
    String jsonString;
    private static final Gson gson = new Gson();
    DataPengiriman model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailPengirimanPelelangBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        jsonString = getIntent().getStringExtra("data");
        model = gson.fromJson(jsonString, DataPengiriman.class);
        if (model != null){
            showData(model);
        }

        binding.btnBack.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    private void showData(DataPengiriman model) {
        binding.tvPelelangId.setText(model.getPelelangId());
        binding.tvLelangId.setText(model.getLelangId());
        binding.tvPengirimanId.setText(model.getPengirimanId());
        binding.tvNamaPengirim.setText(model.getNamaPengirim());
        binding.tvNomorPolisi.setText(model.getNoPolisi());
        binding.tvNamaKendaraan.setText(model.getNamaKendaraan());
        binding.tvNomorHp.setText(model.getNoHp());
        checkStatus(model.getStatusTransaksi(),binding.tvStatusTransaksi);
        binding.tvTanggalPengiriman.setText(model.getTglPengiriman());
    }

    private void checkStatus(String status, TextView textView) {
        switch (status) {
            case "0":
                textView.setText("Sedang di proses");
                break;
            case "1":
                textView.setText("Terkirim");
                break;
            case "2":
                textView.setText("Selesai");
                break;
        }
    }

}