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
import com.example.myapplication.model.panitia.PanitiaPesertaModel;
import com.example.myapplication.model.panitia.PanitiaUbahSaldoPesertaModel;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailDepositPesertaPanitiaActivity extends AppCompatActivity {

    private ImageButton btnKembali;
    private Button btnVerif,btnTolak;
    private TextView tvNama,tvPesertaId,tvNominal,tvBerhasil;
    private EditText etKeterangan;
    private ImageView ivBukti;

    SharedPreferences sharedPreferences;

    String peserta_id,nama,deposit,nominal,bukti,keterangan,status,kode,stats;

    Boolean verif = false, tolak = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_panitia_detail_deposit_peserta);

        btnKembali = findViewById(R.id.btn_back_deposit_peserta);

        btnVerif = findViewById(R.id.btn_verif_deposit_panitia);
        btnTolak = findViewById(R.id.btn_tolak_deposit_panitia);

        tvNama = findViewById(R.id.tv_nama_deposit_panitia);
        tvPesertaId = findViewById(R.id.tv_pesertaId_deposit_panitia);
        tvNominal = findViewById(R.id.tv_nominal_deposit_panitia);
        etKeterangan = findViewById(R.id.et_keterangan_deposit_panitia);
        ivBukti = findViewById(R.id.iv_deposit_panitia);
        tvBerhasil = findViewById(R.id.tv_deposit_berhasil);

        sharedPreferences = getSharedPreferences(LoginActivity.myLelang, Context.MODE_PRIVATE);
        kode = sharedPreferences.getString(LoginActivity.TAG_KODE,"");

        btnKembali.setOnClickListener(view -> {
            onBackPressed();
        });

        Intent intent = getIntent();
        this.peserta_id = intent.getStringExtra("peserta_id");
        this.nama = intent.getStringExtra("nama");
        this.deposit = intent.getStringExtra("deposit");
        this.nominal = intent.getStringExtra("nominal");
        this.bukti = intent.getStringExtra("bukti");
        this.keterangan = intent.getStringExtra("keterangan");
        this.stats = intent.getStringExtra("status");

        tvNama.setText(nama);
        tvPesertaId.setText(peserta_id);
        tvNominal.setText(nominal);
        etKeterangan.setText(keterangan);
        Glide.with(DetailDepositPesertaPanitiaActivity.this).load(bukti).into(ivBukti);

        if (stats.equals("0")){
            tvBerhasil.setVisibility(View.GONE);
            findViewById(R.id.tv_deposit_tolak).setVisibility(View.GONE);
        } else if (stats.equals("1")){
            btnVerif.setVisibility(View.GONE);
            btnTolak.setVisibility(View.GONE);
            findViewById(R.id.tv_deposit_tolak).setVisibility(View.GONE);
        }else{
            tvBerhasil.setVisibility(View.GONE);
            findViewById(R.id.tv_deposit_tolak).setVisibility(View.GONE);
        }

        Log.d("pesertaID", peserta_id);

        btnVerif.setOnClickListener(view ->{
            verif = true;
            putStatus();
            putDeposit();

        });

        btnTolak.setOnClickListener(view ->{
            tolak = true;
            putStatus();

        });

    }

    private void putDeposit() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        final PanitiaUbahSaldoPesertaModel model = new PanitiaUbahSaldoPesertaModel(peserta_id,nominal,deposit);
        Call<PanitiaUbahSaldoPesertaModel> ubahDepositModelCall = retrofitAPI.putTambahDeposit(model);
        ubahDepositModelCall.enqueue(new Callback<PanitiaUbahSaldoPesertaModel>() {
            @Override
            public void onResponse(Call<PanitiaUbahSaldoPesertaModel> call, Response<PanitiaUbahSaldoPesertaModel> response) {
                if (response.isSuccessful()){
                    onRestart();
                    onBackPressed();
                }
            }
            @Override
            public void onFailure(Call<PanitiaUbahSaldoPesertaModel> call, Throwable t) {
                Log.d("error",t.getMessage());
            }
        });
    }

    private void putStatus() {
        keterangan = etKeterangan.getText().toString();
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        if (verif){
            status = "1";
        }else if (tolak) {
            status = "2";
        }
        final PanitiaDepositPesertaModel panitiaPesertaModel = new PanitiaDepositPesertaModel(peserta_id,keterangan,kode);
        Call<PanitiaDepositPesertaModel> panitiaPesertaModelCall = retrofitAPI.putStatusBayarDeposit(panitiaPesertaModel);

        panitiaPesertaModelCall.enqueue(new Callback<PanitiaDepositPesertaModel>() {
            @Override
            public void onResponse(Call<PanitiaDepositPesertaModel> call, Response<PanitiaDepositPesertaModel> response) {
                if (response.isSuccessful()){
                    onRestart();
                    onBackPressed();
                }
            }
            @Override
            public void onFailure(Call<PanitiaDepositPesertaModel> call, Throwable t) {
                Log.d("error",t.getMessage());
            }
        });
    }
}
