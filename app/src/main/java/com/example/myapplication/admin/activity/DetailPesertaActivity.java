package com.example.myapplication.admin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

public class DetailPesertaActivity extends AppCompatActivity {
    private ImageButton btnKembali;
    private TextView tvNama,tvJeniskel,tvProvinsi,tvKota,tvKecamatan,tvAlamat,tvTelp,tvEmail,tvNpwp,tvKtp;
    private ImageView ivNpwp,ivKtp;

    String nama,jeniskel,provinsi,kota,kecamatan,alamat,telp,email,npwp,nik,filenpwp,filektp,jenis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_detail_peserta);

        btnKembali = findViewById(R.id.btn_back_detail_peserta);

        tvNama = findViewById(R.id.tv_nama_detail_peserta_admin);
        tvJeniskel = findViewById(R.id.tv_jeniskel_detail_peserta_admin);
        tvProvinsi = findViewById(R.id.tv_provinsi_detail_peserta_admin);
        tvKota = findViewById(R.id.tv_kota_detail_peserta_admin);
        tvKecamatan = findViewById(R.id.tv_kecamatan_detail_peserta_admin);
        tvAlamat = findViewById(R.id.tv_alamat_detail_peserta_admin);
        tvTelp = findViewById(R.id.tv_telp_detail_peserta_admin);
        tvEmail = findViewById(R.id.tv_email_detail_peserta_admin);
        tvNpwp = findViewById(R.id.tv_npwp_detail_peserta_admin);
        tvKtp = findViewById(R.id.tv_ktp_detail_peserta_admin);
        ivNpwp = findViewById(R.id.iv_npwp_detail_peserta_admin);
        ivKtp = findViewById(R.id.iv_ktp_detail_peserta_admin);

        btnKembali.setOnClickListener(view -> {
            onBackPressed();
        });

        Intent intent = getIntent();
        this.nama = intent.getStringExtra("nama");
        this.jeniskel = intent.getStringExtra("jeniskel");
        this.provinsi = intent.getStringExtra("provinsi");
        this.kota = intent.getStringExtra("kota");
        this.kecamatan = intent.getStringExtra("kecamatan");
        this.alamat = intent.getStringExtra("alamat");
        this.telp =intent.getStringExtra("telp");
        this.email = intent.getStringExtra("email");
        this.npwp = intent.getStringExtra("npwp");
        this.nik = intent.getStringExtra("nik");
        this.filenpwp = intent.getStringExtra("filenpwp");
        this.filektp = intent.getStringExtra("filektp");



        //        Mas Ken
        //        tvNama.setText(nama);
        //        if (jeniskel.equals("L")) {
        //            jenis = "Laki - Laki";
        //        } else if (jeniskel.equals("P")){
        //            jenis = "Perempuan";
        //        } else {
        //            jenis = "Lainnya";
        //        }


        tvNama.setText(nama);
        if ((jeniskel.equals("L")) || (jeniskel.equals("l"))) {
            jenis = "Laki - Laki";
        } else if ((jeniskel.equals("P")) || (jeniskel.equals("p"))) {
            jenis = "Perempuan";
        } else {
            jenis = "Lainnya";
        }

        tvJeniskel.setText(jenis);
        //tvJeniskel.setText(jeniskel);
        tvProvinsi.setText(provinsi);
        tvKota.setText(kota);
        tvKecamatan.setText(kecamatan);
        tvAlamat.setText(alamat);
        tvTelp.setText(telp);
        tvEmail.setText(email);
        tvNpwp.setText(npwp);
        tvKtp.setText(nik);
        Glide.with(DetailPesertaActivity.this).load(filenpwp).into(ivNpwp);
        Glide.with(DetailPesertaActivity.this).load(filektp).into(ivKtp);
    }
}
