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
import com.example.myapplication.model.panitia.PanitiaPelelangModel;
import com.example.myapplication.model.panitia.PanitiaPesertaModel;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPelelangPanitiaActivity extends AppCompatActivity {
    private ImageButton btnKembali;
    private TextView tvNama,tvJenis,tvProvinsi,tvKota,tvKecamatan,tvAlamat,tvTelp,tvEmail,tvNpwp,tvKtp,tvNorek,tvBank,tvDeskripsi;
    private ImageView ivNpwp,ivKtp,ivSiup;
    private Button btnVerif,btnTolak;

    String pelelang_id,status,nama,jenis,provinsi,kota,kecamatan,alamat,telp,email,npwp,nik,norek,filenpwp,filektp,filesiup,jenisP,bank,deskripsi;
    Boolean verif = false, tolak = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panitia_detail_pelelang);

        btnKembali = findViewById(R.id.btn_back_detail_pelelang_panitia);
        btnVerif = findViewById(R.id.btn_verif_detail_pelelang_panitia);
        btnTolak = findViewById(R.id.btn_tolak_detail_pelelang_panitia);

        tvNama = findViewById(R.id.tv_nama_detail_pelelang_panitia);
        tvJenis = findViewById(R.id.tv_jenis_detail_pelelang_panitia);
        tvProvinsi = findViewById(R.id.tv_provinsi_detail_pelelang_panitia);
        tvKota = findViewById(R.id.tv_kota_detail_pelelang_panitia);
        tvKecamatan = findViewById(R.id.tv_kecamatan_detail_pelelang_panitia);
        tvAlamat = findViewById(R.id.tv_alamat_detail_pelelang_panitia);
        tvTelp = findViewById(R.id.tv_telp_detail_pelelang_panitia);
        tvEmail = findViewById(R.id.tv_email_detail_pelelang_panitia);
        tvNpwp = findViewById(R.id.tv_npwp_detail_pelelang_panitia);
        tvKtp = findViewById(R.id.tv_nik_detail_pelelang_panitia);
        tvNorek = findViewById(R.id.tv_norek_detail_pelelang_panitia);
        tvBank = findViewById(R.id.tv_bank_detail_pelelang_panitia);
        tvDeskripsi = findViewById(R.id.tv_deskripsi_detail_pelelang_panitia);

        ivNpwp = findViewById(R.id.iv_npwp_detail_pelelang_panitia);
        ivKtp = findViewById(R.id.iv_ktp_detail_pelelang_panitia);
        ivSiup = findViewById(R.id.iv_siup_detail_pelelang_panitia);

        btnKembali.setOnClickListener(view ->{
            onBackPressed();
        });

        Intent intent = getIntent();
        this.pelelang_id = intent.getStringExtra("pelelang_id");
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
        Glide.with(DetailPelelangPanitiaActivity.this).load(filenpwp).into(ivNpwp);
        Glide.with(DetailPelelangPanitiaActivity.this).load(filektp).into(ivKtp);
        Glide.with(DetailPelelangPanitiaActivity.this).load(filesiup).into(ivSiup);

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
        final PanitiaPelelangModel panitiaPelelangModel = new PanitiaPelelangModel(pelelang_id,status);
        Call<PanitiaPelelangModel> panitiaPelelangModelCall = retrofitAPI.putVerifPelelangPanitia(panitiaPelelangModel);

        panitiaPelelangModelCall.enqueue(new Callback<PanitiaPelelangModel>() {
            @Override
            public void onResponse(Call<PanitiaPelelangModel> call, Response<PanitiaPelelangModel> response) {
                if (response.isSuccessful()){
                    onBackPressed();
                }
            }
            @Override
            public void onFailure(Call<PanitiaPelelangModel> call, Throwable t) {
                Log.d("error",t.getMessage());
            }
        });
    }
}
