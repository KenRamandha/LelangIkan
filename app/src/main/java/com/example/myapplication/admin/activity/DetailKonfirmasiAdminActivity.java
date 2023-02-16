package com.example.myapplication.admin.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class DetailKonfirmasiAdminActivity extends AppCompatActivity {
    private ImageButton btnKembali;
    private TextView tvPesertaid, tvNama, tvLelangid, tvProduk, tvJumlah, tvTestimoni, tvStatus, tvKonfirmasi_terimaproduk;
    private Spinner spStatus;



    String condition = null;
    int conditionColor;
    String peserta_id, nama_peserta, lelang_id, produk, jumlah, testimoni, status, status1, konfirmasi_terimaproduk, konfirmasi_terimaproduk1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_detail_pesanan);
        btnKembali = findViewById(R.id.btn_kembali_detail_pesanan_admin);
        tvPesertaid = findViewById(R.id.tv_pesertaid_detail_konfirmasi);
        tvNama = findViewById(R.id.tv_nama_detail_konfirmasi);
        tvLelangid = findViewById(R.id.tv_lelangid_detail_konfirmasi);
        tvProduk = findViewById(R.id.tv_produk_detail_konfirmasi);
        tvJumlah =findViewById(R.id.tv_jumlah_detail_konfirmasi);
        tvTestimoni = findViewById(R.id.tv_testimoni_detail_konfirmasi);
//        tvStatus = findViewById(R.id.tv_statuskonfirmasi_lelang);
        tvKonfirmasi_terimaproduk = findViewById(R.id.tv_konfirmasi_terimaproduk);


//        spStatus = findViewById(R.id.spinnerStatus);

        Intent intent = getIntent();
        peserta_id= intent.getStringExtra("peserta_id");
        nama_peserta= intent.getStringExtra("nama_peserta");
        lelang_id= intent.getStringExtra("lelang_id");
        produk= intent.getStringExtra("produk");
        jumlah= intent.getStringExtra("jumlah");
        testimoni= intent.getStringExtra("testimoni");
        status= intent.getStringExtra("status");
        konfirmasi_terimaproduk= intent.getStringExtra("konfirmasi_terimaproduk");

        tvPesertaid.setText(peserta_id);
        tvNama.setText(nama_peserta);
        tvLelangid.setText(lelang_id);
        tvProduk.setText(produk);
        tvJumlah.setText(jumlah);
        tvTestimoni.setText(testimoni);
//        if (status.equals("1")) {
//            status1 = "Pesanan Dalam Pengemasan";
//        } else if (status.equals("2")){
//            status1 = "Pesanan Sedang Diantar";
//        } else {
//            status1 = "Pesanan Telah Diterima";
//        }
//        tvPesertaid.setText(status);

        if (konfirmasi_terimaproduk.equals("1")) {
            konfirmasi_terimaproduk1 = "Pesanan Telah Diterima";
            conditionColor = Color.parseColor("#005a5c");
        } else {
            konfirmasi_terimaproduk1 = "Pesanan Belum Diterima";
            conditionColor = Color.parseColor("#F74000");
        }

        tvKonfirmasi_terimaproduk.setText(konfirmasi_terimaproduk1);

        tvKonfirmasi_terimaproduk.setTextColor(conditionColor);
//        txtCON.setText("" + condition);

        btnKembali.setOnClickListener(view ->{
            onBackPressed();
        });
    }
}
