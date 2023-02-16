package com.example.myapplication.panitia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.model.panitia.PanitiaPesertaModel;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPesertaPanitiaActivity extends AppCompatActivity {

    private ImageButton btnKembali;
    private Button btnVerif,btnTolak;
    private TextView tvNama,tvJeniskel,tvProvinsi,tvKota,tvKecamatan,tvAlamat,tvTelp,tvEmail,tvNpwp,tvKtp;
    private ImageView ivNpwp,ivKtp;

    String nama,peserta_id,status,jeniskel,provinsi,kota,kecamatan,alamat,telp,email,npwp,nik,filenpwp,filektp,jenis;

    Boolean verif = false, tolak = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_panitia_detail_peserta);

        btnKembali = findViewById(R.id.btn_back_detail_peserta);

        btnVerif = findViewById(R.id.btn_verif_detail_peserta_panitia);
        btnTolak = findViewById(R.id.btn_tolak_detail_peserta_panitia);

        tvNama = findViewById(R.id.tv_nama_detail_peserta_panitia);
        tvJeniskel = findViewById(R.id.tv_jeniskel_detail_peserta_panitia);
        tvProvinsi = findViewById(R.id.tv_provinsi_detail_peserta_panitia);
        tvKota = findViewById(R.id.tv_kota_detail_peserta_panitia);
        tvKecamatan = findViewById(R.id.tv_kecamatan_detail_peserta_panitia);
        tvAlamat = findViewById(R.id.tv_alamat_detail_peserta_panitia);
        tvTelp = findViewById(R.id.tv_telp_detail_peserta_panitia);
        tvEmail = findViewById(R.id.tv_email_detail_peserta_panitia);
        tvNpwp = findViewById(R.id.tv_npwp_detail_peserta_panitia);
        tvKtp = findViewById(R.id.tv_ktp_detail_peserta_panitia);
        ivNpwp = findViewById(R.id.iv_npwp_detail_peserta_panitia);
        ivKtp = findViewById(R.id.iv_ktp_detail_peserta_panitia);

        btnKembali.setOnClickListener(view -> {
            onBackPressed();
        });

        Intent intent = getIntent();
        this.peserta_id = intent.getStringExtra("peserta_id");
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

        tvNama.setText(nama);

        if (jeniskel.equals("L")) {
            jenis = "Laki - Laki";
        } else if (jeniskel.equals("P")){
            jenis = "Perempuan";
        } else {
            jenis = "Lainnya";
        }
        tvJeniskel.setText(jenis);
        tvProvinsi.setText(provinsi);
        tvKota.setText(kota);
        tvKecamatan.setText(kecamatan);
        tvAlamat.setText(alamat);
        tvTelp.setText(telp);
        tvEmail.setText(email);
        tvNpwp.setText(npwp);
        tvKtp.setText(nik);
        Glide.with(DetailPesertaPanitiaActivity.this).load(filenpwp).into(ivNpwp);
        Glide.with(DetailPesertaPanitiaActivity.this).load(filektp).into(ivKtp);

        Log.d("pesertaID", peserta_id);

        btnVerif.setOnClickListener(view ->{
            verif = true;
            putStatus();

        });

        btnTolak.setOnClickListener(view ->{
            tolak = true;
            putStatus();

        });

    }

    private void putStatus() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        if (verif){
            status = "1";
        }else if (tolak) {
            status = "2";
        }
        final PanitiaPesertaModel panitiaPesertaModel = new PanitiaPesertaModel(peserta_id,status);
        Call<PanitiaPesertaModel> panitiaPesertaModelCall = retrofitAPI.putVerifPesertaPanitia(panitiaPesertaModel);

        panitiaPesertaModelCall.enqueue(new Callback<PanitiaPesertaModel>() {
            @Override
            public void onResponse(Call<PanitiaPesertaModel> call, Response<PanitiaPesertaModel> response) {
                if (response.isSuccessful()){
                    onRestart();
                    onBackPressed();
                }
            }
            @Override
            public void onFailure(Call<PanitiaPesertaModel> call, Throwable t) {
                Log.d("error",t.getMessage());
            }
        });
    }
}
