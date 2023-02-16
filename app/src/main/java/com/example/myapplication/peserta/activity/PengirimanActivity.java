package com.example.myapplication.peserta.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class PengirimanActivity extends AppCompatActivity {
    private ImageButton btnKembali;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengiriman);

        btnKembali = findViewById(R.id.btn_back_pengiriman);

        btnKembali.setOnClickListener(view -> {
            onBackPressed();
        });
    }
}
