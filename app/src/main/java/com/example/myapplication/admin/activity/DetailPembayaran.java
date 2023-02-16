package com.example.myapplication.admin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

public class DetailPembayaran extends AppCompatActivity{
    private ImageButton btnKembali;
    private TextView tvNama_peserta,tvNominal_dibayarkan,  tvProduk_lelang;
    private ImageView  ivBukti_pembayaran;

    String nominal_dibayarkan,nama_peserta,produk,bukti_pembayaran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pembayaran);

        btnKembali = findViewById(R.id.btn_back_detail_peserta);

        tvNama_peserta = findViewById(R.id.tv_nama_peserta);
        tvNominal_dibayarkan = findViewById(R.id.tv_nominal_dibayarkan);
        tvProduk_lelang = findViewById(R.id.tv_produk);

        ivBukti_pembayaran= findViewById(R.id.iv_bukti_pembayaran);



        btnKembali.setOnClickListener(view -> {
            onBackPressed();
        });

        Intent intent = getIntent();
        produk = intent.getStringExtra("produk");
        nama_peserta = intent.getStringExtra("nama_peserta");
        nominal_dibayarkan= intent.getStringExtra("nominal_dibayarkan");
//        this.provinsi = intent.getStringExtra("provinsi");
//        this.kota = intent.getStringExtra("kota");
//        this.kecamatan = intent.getStringExtra("kecamatan");
//        this.alamat = intent.getStringExtra("alamat");

        bukti_pembayaran = intent.getStringExtra("bukti_pembayaran");





        tvNama_peserta.setText(nama_peserta);
        tvProduk_lelang.setText(produk);
        tvNominal_dibayarkan.setText(nominal_dibayarkan);


        Glide.with(DetailPembayaran.this).load(bukti_pembayaran).into(ivBukti_pembayaran);
    }
}

