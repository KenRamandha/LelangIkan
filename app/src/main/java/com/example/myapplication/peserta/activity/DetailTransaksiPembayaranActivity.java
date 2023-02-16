package com.example.myapplication.peserta.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.model.peserta.PembayaranTransaksiPesertaModel;
import com.example.myapplication.model.peserta.WaktuLelangModel;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;
import com.google.android.material.button.MaterialButton;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailTransaksiPembayaranActivity extends AppCompatActivity {
    private TextView tvStatus, tvProduk, tvHarga, tvPelelang, tvTanggal, tvAlamat, tvNohp, tvNominal;
    private ImageButton btnBack;
    private MaterialButton btnKonfirmasi;
    private ImageView ivVerif, ivTolak, ivProses;

    String status, status1, produk, nominal, pelelang, tgl_lelang, alamat, hp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peserta_detail_pesanan_pembayaran);

        tvStatus = findViewById(R.id.tv_status_detail_riwayat_pembayaran);
        tvProduk = findViewById(R.id.tv_produk_detail_riwayat_pembayaran);
        tvHarga = findViewById(R.id.tv_harga_detail_riwayat_pembayaran);
        tvPelelang = findViewById(R.id.tv_pelelang_detail_riwayat_pembayaran);
        tvTanggal = findViewById(R.id.tv_tanggallelang_detail_riwayat_pembayaran);
        tvAlamat = findViewById(R.id.tv_alamat_detail_riwayat_pembayaran);
        tvNohp = findViewById(R.id.tv_nohp_detail_riwayat_pembayaran);
        tvNominal = findViewById(R.id.tv_nominal_ikan_detail_riwayat_pembayaran);

        btnKonfirmasi = findViewById(R.id.btn_konfirmasi_detail_riwayat_pembayaran);
        btnBack = findViewById(R.id.btn_back_detail_riwayat_pembayaran);

        ivProses = findViewById(R.id.iv_proses_detail_riwayat_pembayaran);
        ivVerif = findViewById(R.id.iv_verif_detail_riwayat_pembayaran);
        ivTolak = findViewById(R.id.iv_tolak_detail_riwayat_pembayaran);

        btnBack.setOnClickListener(view -> {
            onBackPressed();
        });



        Intent intent = getIntent();

        this.status = intent.getStringExtra("status");
        this.produk = intent.getStringExtra("produk");
        this.nominal = intent.getStringExtra("nominal");
        this.pelelang = intent.getStringExtra("pelelang");
        this.tgl_lelang = intent.getStringExtra("tgl_lelang");
        this.alamat = intent.getStringExtra("alamat");
        this.hp = intent.getStringExtra("hp");


        tvProduk.setText(produk);

        tvPelelang.setText(pelelang);
        tvPelelang.setText(pelelang);
        tvTanggal.setText(tgl_lelang);
        tvNohp.setText(hp);
        tvAlamat.setText(alamat);

        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        tvNominal.setText("Rp. " + decimalFormat.format(Integer.parseInt(nominal)));
        tvHarga.setText("Rp. " + decimalFormat.format(Integer.parseInt(nominal)));


        if (status.equals("0")) {
            status1 = "   Menunggu Verifikasi Admin   ";
            ivProses.setVisibility(View.VISIBLE);
        } else if (status.equals("1")) {
            status1 = "   Terverivikasi Admin   ";
            ivVerif.setVisibility(View.VISIBLE);
            btnKonfirmasi.setVisibility(View.VISIBLE);
        } else if (status.equals("2")) {
            status1 = "   Ditolak   ";
            ivTolak.setVisibility(View.VISIBLE);
        } else {
            status1 = "Dibatalkan Oleh Sistem";
            ivTolak.setVisibility(View.VISIBLE);
        }

        tvStatus.setText(status1);

        btnKonfirmasi.setOnClickListener(view -> {
            startActivity(new Intent(this, PemenangPengirimanPesertaActivity.class));
        });
    }

//    private void putKonfirmasiPenerimaan() {
////        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
////        final PembayaranTransaksiPesertaModel pembayaranTransaksiPesertaModel = new PembayaranTransaksiPesertaModel(konfirmasi_terima,lelangId);
////        Call<PembayaranTransaksiPesertaModel> konfirmasiTerimalCall = retrofitAPI.postKonfirmasiTerima(pembayaranTransaksiPesertaModel);
////        konfirmasiTerimalCall.enqueue(new Callback<PembayaranTransaksiPesertaModel>() {
////            @Override
////            public void onResponse(Call<PembayaranTransaksiPesertaModel> call, Response<PembayaranTransaksiPesertaModel> response) {
////                if (response.body().getStats()) {
////                    onBackPressed();
////                    DetailTransaksiPembayaranActivity.super.onRestart();
////
////                } else {
////                    Toast.makeText(DetailTransaksiPembayaranActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
////                }
////            }
////
////            @Override
////            public void onFailure(Call<PembayaranTransaksiPesertaModel> call, Throwable t) {
////                Toast.makeText(DetailTransaksiPembayaranActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
////            }
////        });
//    }

}
