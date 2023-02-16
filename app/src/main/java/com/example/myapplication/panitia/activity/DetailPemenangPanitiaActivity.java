package com.example.myapplication.panitia.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.intro.LoginActivity;
import com.example.myapplication.model.panitia.PanitiaPemenangModel;
import com.example.myapplication.model.panitia.PanitiaPesertaModel;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPemenangPanitiaActivity extends AppCompatActivity {

    private ImageButton btnKembali;
    private Button btnVerif,btnTolak;
    private TextView tvNama,tvJeniskel,tvProvinsi,tvKota,tvKecamatan,tvAlamat,tvTelp,tvEmail,tvNamaLelang,tvTanggalLelang,tvTotal;
    private ImageView ivDokumen;
    String kode,nama,peserta_id,status,jeniskel,provinsi,kota,kecamatan,alamat,telp,email,nama_lelang,tanggal_lelang,total,file_dokumen,jenis,lelang_id,diumumkan;

    Boolean verif = false, tolak = false;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_panitia_detail_pemenang);
//
        btnKembali = findViewById(R.id.btn_back_detail_pemenang_panitia);

        btnVerif = findViewById(R.id.btn_verif_detail_pemenang_panitia);
        btnTolak = findViewById(R.id.btn_tolak_detail_pemenang_panitia);

        tvNama = findViewById(R.id.tv_nama_detail_pemenang_panitia);
        tvJeniskel = findViewById(R.id.tv_jeniskel_detail_pemenang_panitia);
        tvProvinsi = findViewById(R.id.tv_provinsi_detail_pemenang_panitia);
        tvKota = findViewById(R.id.tv_kota_detail_pemenang_panitia);
        tvKecamatan = findViewById(R.id.tv_kecamatan_detail_pemenang_panitia);
        tvAlamat = findViewById(R.id.tv_alamat_detail_pemenang_panitia);
        tvTelp = findViewById(R.id.tv_telp_detail_pemenang_panitia);
        tvEmail = findViewById(R.id.tv_email_detail_pemenang_panitia);
        tvNamaLelang = findViewById(R.id.tv_namalelang_detail_pemenang_panitia);
        tvTanggalLelang = findViewById(R.id.tv_tanggal_detail_pemenang_panitia);
        tvTotal = findViewById(R.id.tv_total_detail_pemenang_panitia);
        ivDokumen = findViewById(R.id.iv_bukti_detail_pemenang_panitia);

        sharedPreferences = getSharedPreferences(LoginActivity.myLelang, Context.MODE_PRIVATE);
        kode = sharedPreferences.getString(LoginActivity.TAG_KODE,"");
        Log.d("kode", kode);

        btnKembali.setOnClickListener(view -> {
            onBackPressed();
        });

        Intent intent = getIntent();
        this.peserta_id = intent.getStringExtra("peserta_id");
        this.lelang_id = intent.getStringExtra("lelang_id");
        this.nama = intent.getStringExtra("nama");
        this.jeniskel = intent.getStringExtra("jeniskel");
        this.provinsi = intent.getStringExtra("provinsi");
        this.kota = intent.getStringExtra("kota");
        this.kecamatan = intent.getStringExtra("kecamatan");
        this.alamat = intent.getStringExtra("alamat");
        this.telp =intent.getStringExtra("telp");
        this.email = intent.getStringExtra("email");
        this.nama_lelang = intent.getStringExtra("nama_lelang");
        this.tanggal_lelang = intent.getStringExtra("tanggal_lelang");
        this.total = intent.getStringExtra("total");
        this.file_dokumen = intent.getStringExtra("file_dokumen");

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
        tvNamaLelang.setText(nama_lelang);

        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "yyyy-MM-dd";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String result = null;

        try {
            date = inputFormat.parse(tanggal_lelang);
            result = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tvTanggalLelang.setText(result);

        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        if (total !=null){
            tvTotal.setText(decimalFormat.format(Integer.parseInt(total)));
        }


        Glide.with(DetailPemenangPanitiaActivity.this).load(file_dokumen).into(ivDokumen);

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
        final PanitiaPemenangModel panitiaPemenangModel = new PanitiaPemenangModel(lelang_id,status,kode,diumumkan);
        Call<PanitiaPemenangModel> panitiaPemenangModelCall = retrofitAPI.putVerifPemenangPanitia(panitiaPemenangModel);

        panitiaPemenangModelCall.enqueue(new Callback<PanitiaPemenangModel>() {
            @Override
            public void onResponse(Call<PanitiaPemenangModel> call, Response<PanitiaPemenangModel> response) {
                if (response.isSuccessful()){
                    startActivity(new Intent(DetailPemenangPanitiaActivity.this, PanitiaActivity.class));
                    DetailPemenangPanitiaActivity.super.onRestart();
                }
            }
            @Override
            public void onFailure(Call<PanitiaPemenangModel> call, Throwable t) {
                Log.d("error",t.getMessage());
            }
        });
    }
}
