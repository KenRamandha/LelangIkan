package com.example.myapplication.admin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

public class DetailPelelangActivity extends AppCompatActivity {
    private ImageButton btnKembali;
    private TextView tvNama,tvJenis,tvProvinsi,tvKota,tvKecamatan,tvAlamat,tvTelp,tvEmail,tvNpwp,tvKtp,tvNorek,tvBank,tvDeskripsi;
    private ImageView ivNpwp,ivKtp,ivSiup;

    String nama,jenis,provinsi,kota,kecamatan,alamat,telp,email,npwp,nik,norek,filenpwp,filektp,filesiup,jenisP,bank,deskripsi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_detail_pelelang);

        btnKembali = findViewById(R.id.btn_back_detail_pelelang);

        tvNama = findViewById(R.id.tv_nama_detail_pelelang_admin);
        tvJenis = findViewById(R.id.tv_jenis_detail_pelelang_admin);
        tvProvinsi = findViewById(R.id.tv_provinsi_detail_pelelang_admin);
        tvKota = findViewById(R.id.tv_kota_detail_pelelang_admin);
        tvKecamatan = findViewById(R.id.tv_kecamatan_detail_pelelang_admin);
        tvAlamat = findViewById(R.id.tv_alamat_detail_pelelang_admin);
        tvTelp = findViewById(R.id.tv_telp_detail_pelelang_admin);
        tvEmail = findViewById(R.id.tv_email_detail_pelelang_admin);
        tvNpwp = findViewById(R.id.tv_npwp_detail_pelelang_admin);
        tvKtp = findViewById(R.id.tv_nik_detail_pelelang_admin);
        tvNorek = findViewById(R.id.tv_norek_detail_pelelang_admin);
        tvBank = findViewById(R.id.tv_bank_detail_pelelang_admin);
        tvDeskripsi = findViewById(R.id.tv_deskripsi_detail_pelelang_admin);

        ivNpwp = findViewById(R.id.iv_npwp_detail_pelelang_admin);
        ivKtp = findViewById(R.id.iv_ktp_detail_pelelang_admin);
        ivSiup = findViewById(R.id.iv_siup_detail_pelelang_admin);

        btnKembali.setOnClickListener(view ->{
            onBackPressed();
        });

        Intent intent = getIntent();
        this.nama = intent.getStringExtra("nama");
        this.jenis = intent.getStringExtra("jenis");
        this.provinsi = intent.getStringExtra("provinsi");
        this.kota = intent.getStringExtra("kota");
        this.kecamatan = intent.getStringExtra("kecamatan");
        this.alamat = intent.getStringExtra("alamat");
        this.telp =intent.getStringExtra("telp");
        this.email = intent.getStringExtra("email");
        this.npwp = intent.getStringExtra("npwp");
        this.nik = intent.getStringExtra("nik");
        this.norek = intent.getStringExtra("norek");
        this.bank = intent.getStringExtra("bank");
        this.deskripsi = intent.getStringExtra("deskripsi");
        this.filenpwp = intent.getStringExtra("filenpwp");
        this.filektp = intent.getStringExtra("filektp");
        this.filesiup = intent.getStringExtra("filesiup");

        tvNama.setText(nama);
        if (jenis.equals("2")) {
            jenisP = "Perusahaan";
        } else if (jenis.equals("1")){
            jenisP = "Perorangan";
        } else {
            jenisP = "Lainnya";
        }
        //tvJenis.setText(String.valueOf(jenis));
        tvJenis.setText(jenisP);
        //tvSaldo.setText(String.valueOf(deposit));
        //tvJeniskel.setText(jeniskel);
        tvProvinsi.setText(provinsi);
        tvKota.setText(kota);
        tvKecamatan.setText(kecamatan);
        tvAlamat.setText(alamat);
        tvTelp.setText(telp);
        tvEmail.setText(email);
        tvNpwp.setText(npwp);
        tvKtp.setText(nik);
        tvNorek.setText(norek);
        tvBank.setText(bank);
        tvDeskripsi.setText(deskripsi);
        Glide.with(DetailPelelangActivity.this).load(filenpwp).into(ivNpwp);
        Glide.with(DetailPelelangActivity.this).load(filektp).into(ivKtp);
        Glide.with(DetailPelelangActivity.this).load(filesiup).into(ivSiup);
    }
}
