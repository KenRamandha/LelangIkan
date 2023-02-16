package com.example.myapplication.peserta.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.intro.LoginActivity;
import com.example.myapplication.model.peserta.ProfilePesertaModel;
import com.example.myapplication.model.peserta.TestimoniModel;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KonfirmasiProdukActivity extends AppCompatActivity{
    private ImageButton btnBack;
    private Button btnKonfirmasi;
    private Dialog dialog;
    private TextView tvProduk,tvPelelang,tvTanggal;
    private EditText etKeterangan;
    private ImageView ivGambar1,ivGambar2,ivGambar3,ivGambar4;

    String lelangId,image1,image2,image3,image4,produk,pelelang,tanggal,keterangan,nama;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi_produk);

        btnBack = findViewById(R.id.btn_back_konfirmasi_testimoni);
        btnKonfirmasi = findViewById(R.id.btn_konfirmasi_testimoni);
        ivGambar1 = findViewById(R.id.iv_image1_produk_testimoni);
        ivGambar2 = findViewById(R.id.iv_image2_produk_testimoni);
        ivGambar3 = findViewById(R.id.iv_image3_produk_testimoni);
        ivGambar4 = findViewById(R.id.iv_image4_produk_testimoni);
        tvProduk = findViewById(R.id.tv_judul_produk_testimoni);
        tvPelelang = findViewById(R.id.tv_pelelang_produk_testimoni);
        tvTanggal = findViewById(R.id.tv_tanggal_produk_testimoni);
        etKeterangan = findViewById(R.id.et_konfirmasi_produk);
        dialog = new Dialog(this);

        btnBack.setOnClickListener(view ->{
            onBackPressed();
        });
        btnKonfirmasi.setOnClickListener(view-> {
            putTestimoni();
        });

        Intent intent = getIntent();
        this.lelangId = intent.getStringExtra("lelang_id");
        this.image1 = intent.getStringExtra("image1");
        this.image2 = intent.getStringExtra("image2");
        this.image3 = intent.getStringExtra("image3");
        this.image4 = intent.getStringExtra("image4");
        this.produk = intent.getStringExtra("produk");
        this.tanggal = intent.getStringExtra("waktu_selesai");
        this.pelelang = intent.getStringExtra("pelelang");
        this.keterangan = intent.getStringExtra("keterangan");

        tvProduk.setText(produk);

        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "yyyy-MM-dd";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String result = null;

        try {
            date = inputFormat.parse(tanggal);
            result = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        tvTanggal.setText(result);
        tvPelelang.setText(pelelang);
        etKeterangan.setText(keterangan);

        Glide.with(KonfirmasiProdukActivity.this)
                .load(image1)
                .into(ivGambar1);
        Glide.with(KonfirmasiProdukActivity.this)
                .load(image2)
                .into(ivGambar2);
        Glide.with(KonfirmasiProdukActivity.this)
                .load(image3)
                .into(ivGambar3);
        Glide.with(KonfirmasiProdukActivity.this)
                .load(image4)
                .into(ivGambar4);

    }

    private void putTestimoni() {
        keterangan = etKeterangan.getText().toString();

        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        final TestimoniModel testimoniModel = new TestimoniModel(lelangId,keterangan);
        Call<TestimoniModel> testimoniModelCall = retrofitAPI.postTestimoni(testimoniModel);

        testimoniModelCall.enqueue(new Callback<TestimoniModel>() {
            @Override
            public void onResponse(Call<TestimoniModel> call, Response<TestimoniModel> response) {
                if (response.body().getStats()) {
                    openBtnKonfirmasi();
                } else {
                    Toast.makeText(KonfirmasiProdukActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TestimoniModel> call, Throwable t) {
                Toast.makeText(KonfirmasiProdukActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openBtnKonfirmasi() {
        dialog.setContentView(R.layout.dialog_konfirmasi_testimoni);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btnKembali = dialog.findViewById(R.id.btn_kembali_dkonfirmasi);

        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
