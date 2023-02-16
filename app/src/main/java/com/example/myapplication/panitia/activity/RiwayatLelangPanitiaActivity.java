package com.example.myapplication.panitia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RiwayatLelangPanitiaActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private ImageView iv1,iv2,iv3,iv4;
    private TextView tvNamaPelelang,tvProduk,tvHarga,tvTanggal,tvNamaPeserta,tvTotal,tvProvinsi,tvKota,tvKecamatan,tvKelurahan,tvAlamat;

    String image1,image2,image3,image4,pelelang,produk,harga,tanggal,peserta,total,provinsi,kota,kecamatan,kelurahan,alamat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_riwayat_lelang_panitia);

        btnBack = findViewById(R.id.btn_back_detail_riwayat_lelang_panitia);
        iv1 = findViewById(R.id.iv_gambar1_detail_panitia);
        iv2 = findViewById(R.id.iv_gambar2_detail_panitia);
        iv3 = findViewById(R.id.iv_gambar3_detail_panitia);
        iv4 = findViewById(R.id.iv_gambar4_detail_panitia);
        tvNamaPelelang = findViewById(R.id.tv_nama_detail_riwayat_lelang_panitia);
        tvProduk = findViewById(R.id.tv_produk_detail_riwayat_lelang_panitia);
        tvHarga = findViewById(R.id.tv_awal_detail_riwayat_lelang_panitia);
        tvTanggal = findViewById(R.id.tv_tanggal_detail_riwayat_lelang_panitia);
        tvNamaPeserta = findViewById(R.id.tv_peserta_detail_riwayat_lelang_panitia);
        tvTotal = findViewById(R.id.tv_total_detail_riwayat_lelang_panitia);
        tvProvinsi = findViewById(R.id.tv_provinsi_detail_riwayat_lelang_panitia);
        tvKota = findViewById(R.id.tv_kota_detail_riwayat_lelang_panitia);
        tvKecamatan = findViewById(R.id.tv_kecamatan_detail_riwayat_lelang_panitia);
        tvKelurahan = findViewById(R.id.tv_kelurahan_detail_riwayat_lelang_panitia);
        tvAlamat = findViewById(R.id.tv_alamat_detail_riwayat_lelang_panitia);

        Intent intent = getIntent();
        this.image1 = intent.getStringExtra("image1");
        this.image2 = intent.getStringExtra("image2");
        this.image3 = intent.getStringExtra("image3");
        this.image4 = intent.getStringExtra("image4");
        this.pelelang = intent.getStringExtra("pelelang");
        this.produk = intent.getStringExtra("produk");
        this.harga = intent.getStringExtra("harga");
        this.tanggal = intent.getStringExtra("tanggal");
        this.peserta = intent.getStringExtra("peserta");
        this.total = intent.getStringExtra("total");
        this.provinsi = intent.getStringExtra("provinsi");
        this.kota = intent.getStringExtra("kota");
        this.kecamatan = intent.getStringExtra("kecamatan");
        this.kelurahan = intent.getStringExtra("kelurahan");
        this.alamat = intent.getStringExtra("alamat");

        Glide.with(this).load(image1).into(iv1);
        Glide.with(this).load(image2).into(iv2);
        Glide.with(this).load(image3).into(iv3);
        Glide.with(this).load(image4).into(iv4);

        tvNamaPelelang.setText(pelelang);
        tvProduk.setText(produk);

        DecimalFormat decimalFormat = new DecimalFormat("#,##0");

        if (harga !=null){
            tvHarga.setText(decimalFormat.format(Integer.parseInt(harga)));
        }

        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "yyyy-MM-dd";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String result = null;

        try {
            date = inputFormat.parse(tanggal);
            result = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tvTanggal.setText(result);
        tvNamaPeserta.setText(peserta);

        if (total !=null){
            tvTotal.setText(decimalFormat.format(Integer.parseInt(total)));
        }

        tvProvinsi.setText(provinsi);
        tvKecamatan.setText(kecamatan);
        tvKelurahan.setText(kelurahan);
        tvAlamat.setText(alamat);

    }
}
