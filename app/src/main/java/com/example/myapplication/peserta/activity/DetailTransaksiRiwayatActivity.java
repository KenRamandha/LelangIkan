package com.example.myapplication.peserta.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailTransaksiRiwayatActivity extends AppCompatActivity {
    private TextView tvStatus,tvProduk,tvHarga,tvPelelang,tvTgllelang,tvPengirim,tvNoHp,tvNopol,tvTglKirim,tvAlamat,tvProduk1,tvHarga1,tvBKirim,tvBAdmin,tvTotal;
    private ImageButton btnBack;
    private ImageView ivVerif,ivTolak,ivProses;

    String status,status1,alamat,produk,harga,pelelang,tglLelang,pengirim,noHp,Nopol,tglKirim,produk1,harga1,bKirim,bAdmin,total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peserta_detail_riwayat_pembayaran);

        tvStatus = findViewById(R.id.tv_status_detail_riwayat);
        tvProduk = findViewById(R.id.tv_produk_detail_riwayat);
        tvHarga = findViewById(R.id.tv_harga_detail_riwayat);
        tvPelelang= findViewById(R.id.tv_pelelang_detail_riwayat);
        tvTgllelang= findViewById(R.id.tv_tanggallelang_detail_riwayat);
        tvPengirim = findViewById(R.id.tv_pengirim_detail_riwayat);
        tvNoHp = findViewById(R.id.tv_nohp_detail_riwayat);
        tvNopol = findViewById(R.id.tv_plat_detail_riwayat);
        tvAlamat = findViewById(R.id.tv_alamat_detail_riwayat);
        tvTglKirim = findViewById(R.id.tv_tanggalkirim_detail_riwayat);
        tvProduk1 = findViewById(R.id.tv_ikan_detail_riwayat);
        tvHarga1 = findViewById(R.id.tv_harga_ikan_detail_riwayat);

        btnBack = findViewById(R.id.btn_back_detail_riwayat);

        ivProses = findViewById(R.id.iv_proses_detail_riwayat);
        ivVerif = findViewById(R.id.iv_verif_detail_riwayat);
        ivTolak = findViewById(R.id.iv_tolak_detail_riwayat);

        btnBack.setOnClickListener(view ->{
            onBackPressed();
        });

        Intent intent = getIntent();

        this.produk = intent.getStringExtra("produk");
        this.harga = intent.getStringExtra("harga");
        this.pelelang = intent.getStringExtra("pelelang");
        this.tglLelang = intent.getStringExtra("tglLelang");
        this.pengirim = intent.getStringExtra("pengirim");
        this.noHp = intent.getStringExtra("noHp");
        this.Nopol = intent.getStringExtra("Nopol");
        this.tglKirim = intent.getStringExtra("tglKirim");
        this.produk1 = intent.getStringExtra("produk1");
        this.harga1 = intent.getStringExtra("harga1");
        this.alamat = intent.getStringExtra("alamat");
        this.status = intent.getStringExtra("status");

        tvProduk.setText(produk);

        tvPelelang.setText(pelelang);
        tvPengirim.setText(pengirim);
        tvNoHp.setText(noHp);
        tvNopol.setText(Nopol);
        tvProduk1.setText(produk1);
        tvAlamat.setText(alamat);

        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        tvHarga1.setText("Rp. "+decimalFormat.format(Integer.parseInt(harga1)));
        tvHarga.setText("Rp. "+decimalFormat.format(Integer.parseInt(harga)));

        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "yyyy-MM-dd";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String result = null;

        try {
            date = inputFormat.parse(tglLelang);
            result = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String inputPattern1 = "yyyy-MM-dd";
        String outputPattern1 = "yyyy-MM-dd";
        SimpleDateFormat inputFormat1 = new SimpleDateFormat(inputPattern1);
        SimpleDateFormat outputFormat1 = new SimpleDateFormat(outputPattern1);

        Date date1 = null;
        String result1 = null;

        try {
            date1 = inputFormat1.parse(tglKirim);
            result1 = outputFormat1.format(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        tvTgllelang.setText(result);
        tvTglKirim.setText(result1);

        if (status.equals("0")){
            status1 = "   Menunggu Verifikasi Admin   ";
            ivProses.setVisibility(View.VISIBLE);
        } else if (status.equals("1")){
            status1 = "   Ditolak   ";
            ivTolak.setVisibility(View.VISIBLE);
        } else if (status.equals("2")){
            status1 = "   Pesanan Telah Selesai   ";
            ivVerif.setVisibility(View.VISIBLE);
        } else {
            status1 = "-";
        }

        tvStatus.setText(status1);


    }
}
