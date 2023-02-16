package com.example.myapplication.admin.activity;



import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AdminPenawaran2 extends AppCompatActivity {
    private ImageButton btnBack;
    private TextView tvHargaTawar, tvNama, tvTgl_bid;

    private Handler handler;
    private Runnable runnable;

    String nama, tgl_bid, harga_tawar;
//    Integer status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.item_penawaran_admin);

//        btnBack = findViewById(R.id.btn_kembali_penawaran);


        tvNama = findViewById(R.id.tv_nama);
        tvTgl_bid = findViewById(R.id.tv_tgl_bid);

        tvHargaTawar = findViewById(R.id.tv_harga_tawar);


        btnBack.setOnClickListener(view -> {
            onBackPressed();
        });


        Intent intent = getIntent();
        this.harga_tawar = intent.getStringExtra("harga_tawar");
        this.tgl_bid = intent.getStringExtra("tgl_bid");
        this.nama = intent.getStringExtra("nama");


        tvHargaTawar.setText(harga_tawar);
        tvNama.setText(nama);
        tvTgl_bid.setText(tgl_bid);


        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "HH:mm:ss";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String result = null;


    }
}


