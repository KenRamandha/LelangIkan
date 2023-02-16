package com.example.myapplication.panitia.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.intro.LoginActivity;
import com.example.myapplication.model.panitia.PanitiaDepositPesertaModel;
import com.example.myapplication.model.panitia.PanitiaPembayaranPemenangModel;
import com.example.myapplication.model.panitia.PanitiaPembayaranPesertaModel;
import com.example.myapplication.model.panitia.PanitiaPemenangModel;
import com.example.myapplication.model.panitia.PanitiaUbahSaldoPesertaModel;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPembayaranPesertaPanitiaActivity extends AppCompatActivity {

    private ImageButton btnKembali;
    private Button btnVerif,btnTolak;
    private TextView tvNama,tvPesertaId,tvLelang_id,tvProduk,tvNominal,tvBerhasil,tvTanggal,tvBukti;
    private ImageView ivBukti;

    SharedPreferences sharedPreferences;

    String bukti,kode,stats,lelang_id,tgl_pembayaran,nominal_dibayarkan,bukti_pembayaran,peserta_id,nama,produk,status,bukti1;

    Boolean verif = false, tolak = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_panitia_detail_pembayaran_peserta);

        btnKembali = findViewById(R.id.btn_back_pembayaran_peserta);

        btnVerif = findViewById(R.id.btn_verif_pembayaran_panitia);
        btnTolak = findViewById(R.id.btn_tolak_pembayaran_panitia);

        tvNama = findViewById(R.id.tv_nama_pembayaran_panitia);
        tvPesertaId = findViewById(R.id.tv_pesertaId_pembayaran_panitia);
        tvLelang_id = findViewById(R.id.tv_lelangId_pembayaran_panitia);
        tvProduk = findViewById(R.id.tv_produk_pembayaran_panitia);
        tvNominal = findViewById(R.id.tv_nominal_pembayaran_panitia);
        ivBukti = findViewById(R.id.iv_pembayaran_panitia);
        tvBerhasil = findViewById(R.id.tv_pembayaran_berhasil);
        tvTanggal = findViewById(R.id.tv_tanggal_pembayaran_panitia);
        tvBukti = findViewById(R.id.tv_bukti_pembayaran_panitia);


        sharedPreferences = getSharedPreferences(LoginActivity.myLelang, Context.MODE_PRIVATE);
        kode = sharedPreferences.getString(LoginActivity.TAG_KODE,"");

        btnKembali.setOnClickListener(view -> {
            onBackPressed();
        });

        Intent intent = getIntent();
        this.lelang_id = intent.getStringExtra("lelang_id");
        this.tgl_pembayaran = intent.getStringExtra("tgl_pembayaran");
        this.nominal_dibayarkan = intent.getStringExtra("nominal_dibayarkan");
        this.bukti_pembayaran = intent.getStringExtra("bukti_pembayaran");
        this.peserta_id = intent.getStringExtra("peserta_id");
        this.nama = intent.getStringExtra("nama");
        this.produk = intent.getStringExtra("produk");
        this.stats = intent.getStringExtra("status");
        this.bukti = intent.getStringExtra("bukti");
        this.bukti1 = intent.getStringExtra("bukti1");

        tvProduk.setText(produk);
        tvLelang_id.setText(lelang_id);
        tvTanggal.setText(tgl_pembayaran);
        tvNama.setText(nama);
        tvPesertaId.setText(peserta_id);
        tvNominal.setText(nominal_dibayarkan);

        tvBukti.setText(bukti1);

        Glide.with(DetailPembayaranPesertaPanitiaActivity.this).load(bukti_pembayaran).into(ivBukti);

        if (bukti == null){
            tvBerhasil.setVisibility(View.GONE);
            findViewById(R.id.tv_pembayaran_tolak).setVisibility(View.GONE);
        } else {
            btnVerif.setVisibility(View.GONE);
            btnTolak.setVisibility(View.GONE);
        }
        if (stats.equals("0")){
            tvBerhasil.setVisibility(View.GONE);
            findViewById(R.id.tv_pembayaran_tolak).setVisibility(View.GONE);
        } else if (stats.equals("1")){
            findViewById(R.id.tv_pembayaran_tolak).setVisibility(View.GONE);
        }else{
            tvBerhasil.setVisibility(View.GONE);
            findViewById(R.id.tv_pembayaran_tolak).setVisibility(View.GONE);
        }

        Log.d("pesertaID", peserta_id);

        btnVerif.setOnClickListener(view ->{
            verif = true;
            putPembayaran();
            putPemenang();

        });

        btnTolak.setOnClickListener(view ->{
            tolak = true;
            putPemenang();
        });

    }

    private void putPembayaran() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        final PanitiaPembayaranPesertaModel model = new PanitiaPembayaranPesertaModel(kode,lelang_id);
        Call<PanitiaPembayaranPesertaModel> ubahDepositModelCall = retrofitAPI.putPembayaranPanitia(model);
        ubahDepositModelCall.enqueue(new Callback<PanitiaPembayaranPesertaModel>() {
            @Override
            public void onResponse(Call<PanitiaPembayaranPesertaModel> call, Response<PanitiaPembayaranPesertaModel> response) {
                if (response.isSuccessful()){
                    onRestart();
                    onBackPressed();
                }
            }
            @Override
            public void onFailure(Call<PanitiaPembayaranPesertaModel> call, Throwable t) {
                Log.d("error",t.getMessage());
            }
        });
    }

    private void putPemenang() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        if (verif){
            status = "1";
        }else if (tolak) {
            status = "2";
        }
        final PanitiaPembayaranPemenangModel panitiaPesertaModel = new PanitiaPembayaranPemenangModel(kode,lelang_id,status,bukti1,tgl_pembayaran);
        Call<PanitiaPembayaranPemenangModel> panitiaPesertaModelCall = retrofitAPI.putPemenangPanitia(panitiaPesertaModel);
        panitiaPesertaModelCall.enqueue(new Callback<PanitiaPembayaranPemenangModel>() {
            @Override
            public void onResponse(Call<PanitiaPembayaranPemenangModel> call, Response<PanitiaPembayaranPemenangModel> response) {
                if (response.isSuccessful()){
                    onRestart();
                    onBackPressed();
                }
            }
            @Override
            public void onFailure(Call<PanitiaPembayaranPemenangModel> call, Throwable t) {
                Log.d("error",t.getMessage());
            }
        });
    }
}
