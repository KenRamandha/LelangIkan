package com.example.myapplication.pelelang.hostui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class DetailPesananPelelang extends AppCompatActivity {

    Button BtnkrmPsnn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pesanan_pelelang);

        BtnkrmPsnn = findViewById(R.id.kirimPesanan);

        BtnkrmPsnn.setOnClickListener(view -> {
            Intent krmPsnan = new Intent(this, DetailPengirimanPelelang.class);
            startActivity(krmPsnan);
        });
    }
}