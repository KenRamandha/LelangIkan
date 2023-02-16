package com.example.myapplication.admin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

public class DetailPanitiaActivity extends AppCompatActivity{


    private ImageButton btnKembali;
    private TextView tvNama,tvNik,tvInstansi,tvJabatan,tvJeniskel,tvFoto,tvUsername,tvPassword;
    private ImageView ivFoto,ivKtp,ivSiup;

    String nama,nik,instansi,jabatan,jeniskel,foto,username, jenisP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_detail_panitia);

        btnKembali = findViewById(R.id.btn_back_detail_pelelang);

        tvNama = findViewById(R.id.tv_nama_detail_panitia_admin);
        tvNik = findViewById(R.id.tv_nik_detail_panitia_admin);
        tvInstansi = findViewById(R.id.tv_instansi_detail_panitia_admin);
        tvJabatan = findViewById(R.id.tv_jabatan_detail_panitia_admin);
        tvJeniskel = findViewById(R.id.tv_jeniskel_detail_panitia_admin);
//        tvFoto = findViewById(R.id.tv_alamat_detail_pelelang_admin);
//        tvUsername = findViewById(R.id.tv_telp_detail_pelelang_admin);


        ivFoto = findViewById(R.id.iv_foto_detail_panitia_admin);

        btnKembali.setOnClickListener(view ->{
            onBackPressed();
        });

        Intent intent = getIntent();
        this.nama = intent.getStringExtra("nama");
        this.instansi = intent.getStringExtra("instansi");
        this.jabatan = intent.getStringExtra("jabatan");
        this.jeniskel = intent.getStringExtra("jeniskel");
        this.foto = intent.getStringExtra("foto");
        this.username = intent.getStringExtra("username");
        this.nik = intent.getStringExtra("nik");


        tvNama.setText(nama);
        if (jeniskel.equals("L")) {
            jenisP = "Laki-Laki";
        } else if (jeniskel.equals("P")){
            jenisP = "Perempuan";
        } else {
            jenisP = "Lainnya";
        }
        //tvJenis.setText(String.valueOf(jenis));

        tvJeniskel.setText(jenisP);
        tvInstansi.setText(instansi);
        tvJabatan.setText(jabatan);
//        tvUsername.setText(username);
        tvNik.setText(nik);

        Glide.with(DetailPanitiaActivity.this).load(foto).into(ivFoto);

    }
}
