package com.example.myapplication.panitia.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.intro.LoginActivity;
import com.example.myapplication.model.panitia.PanitiaPengemasanModel;
import com.example.myapplication.model.panitia.ProdukPanitiaModel;
import com.example.myapplication.peserta.activity.BidActivity;
import com.example.myapplication.peserta.activity.EditAkunActivity;
import com.example.myapplication.peserta.activity.MetodBayarActivity;
import com.example.myapplication.peserta.section.SectionsPagerAdapter;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailVerifPanitiaActivity extends AppCompatActivity {

    private Button btnVerif,btnTolak;
    private ImageButton btnKembali;

    private TextView tvProduk,tvDeskripsi,tvJumlah,tvHrgpenawaran,tvHrgminimal,tvHrgKelipatan,tvBeliskg,tvTglmulai,tvTglselesai,tvKeterangan;
    private ImageView ivImage1, ivImage2, ivImage3, ivImage4;

    SharedPreferences sharedPreferences;

    String image1, image2, image3, image4,lelang_id,status,produk,deskripsi,jumlah,penawaran,minim,kelipatan,beli,tglMulai,tglSelesai,keterangan,kode;

    Boolean verif = false, tolak = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_lelang_panitia);

        btnKembali = findViewById(R.id.btn_back_detail_panitia);

        btnVerif = findViewById(R.id.btn_verif_lelang_panitia);
        btnTolak = findViewById(R.id.btn_tolak_lelang_panitia);

        tvProduk =findViewById(R.id.tv_nama_detail_panitia);
        tvDeskripsi = findViewById(R.id.tv_desk_detail_panitia);
        tvJumlah = findViewById(R.id.tv_jumlah_detail_panitia);
        tvHrgpenawaran =findViewById(R.id.tv_hrgawal_detail_panitia);
        tvHrgminimal = findViewById(R.id.tv_hrgmin_detail_panitia);
        tvHrgKelipatan = findViewById(R.id.tv_kelipatan_detail_panitia);
        tvBeliskg = findViewById(R.id.tv_beliskg_detail_panitia);
        tvTglmulai = findViewById(R.id.tv_tglmulai_detail_panitia);
        tvTglselesai = findViewById(R.id.tv_tglselesai_detail_panitia);
        tvKeterangan = findViewById(R.id.tv_keterangan_detail_panitia);


        ivImage1 = findViewById(R.id.iv_gambar1_detail_panitia);
        ivImage2 = findViewById(R.id.iv_gambar2_detail_panitia);
        ivImage3 = findViewById(R.id.iv_gambar3_detail_panitia);
        ivImage4 = findViewById(R.id.iv_gambar4_detail_panitia);

        sharedPreferences = getSharedPreferences(LoginActivity.myLelang, Context.MODE_PRIVATE);
        kode = sharedPreferences.getString(LoginActivity.TAG_KODE,"");


        Intent intent = getIntent();
        this.lelang_id = intent.getStringExtra("id");
        this.image1 = intent.getStringExtra("img1");
        this.image2 = intent.getStringExtra("img2");
        this.image3 = intent.getStringExtra("img3");
        this.image4 = intent.getStringExtra("img4");
        this.produk = intent.getStringExtra("produk");
        this.deskripsi = intent.getStringExtra("deskripsi");
        this.jumlah = intent.getStringExtra("jumlah");
        this.penawaran = intent.getStringExtra("penawaran");
        this.minim = intent.getStringExtra("minim");
        this.kelipatan = intent.getStringExtra("incremental");
        this.beli = intent.getStringExtra("beli");
        this.tglMulai = intent.getStringExtra("tglmulai");
        this.tglSelesai = intent.getStringExtra("tglselesai");
        this.keterangan = intent.getStringExtra("keterangan");

        tvProduk.setText(produk);
        tvDeskripsi.setText(deskripsi);
        tvJumlah.setText(jumlah + " Kg");

        DecimalFormat decimalFormat = new DecimalFormat("#,##0");

        tvHrgpenawaran.setText(decimalFormat.format(Integer.parseInt(penawaran)));
        tvHrgminimal.setText(decimalFormat.format(Double.parseDouble(minim)));
        tvHrgKelipatan.setText(decimalFormat.format(Integer.parseInt(kelipatan)));
//        tvHrgKelipatan.setText(kelipatan);
        tvBeliskg.setText(decimalFormat.format(Integer.parseInt(beli)));

        tvTglmulai.setText(tglMulai);
        tvTglselesai.setText(tglSelesai);

        tvKeterangan.setText(keterangan);

        Glide.with(DetailVerifPanitiaActivity.this)
                .load(image1)
                .into(ivImage1);
        Glide.with(DetailVerifPanitiaActivity.this)
                .load(image2)
                .into(ivImage2);
        Glide.with(DetailVerifPanitiaActivity.this)
                .load(image3)
                .into(ivImage3);
        Glide.with(DetailVerifPanitiaActivity.this)
                .load(image4)
                .into(ivImage4);

        btnKembali.setOnClickListener(view -> {
            onBackPressed();
        });

        btnVerif.setOnClickListener(view -> {
            verif = true;
            putStatus();
        });

        btnTolak.setOnClickListener(view -> {
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
        final ProdukPanitiaModel produkPanitiaModel = new ProdukPanitiaModel(lelang_id,status,kode);
        Call<ProdukPanitiaModel> produkPanitiaModelCall = retrofitAPI.putverifPembukaan(produkPanitiaModel);

        produkPanitiaModelCall.enqueue(new Callback<ProdukPanitiaModel>() {
            @Override
            public void onResponse(Call<ProdukPanitiaModel> call, Response<ProdukPanitiaModel> response) {
                if (response.body().getStats()){
                    startActivity(new Intent(DetailVerifPanitiaActivity.this, PanitiaActivity.class));
                }else {
                    Toast.makeText(DetailVerifPanitiaActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ProdukPanitiaModel> call, Throwable t) {
                Log.d("error",t.getMessage());
            }
        });

    }
}