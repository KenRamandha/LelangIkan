package com.example.myapplication.peserta.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import com.example.myapplication.intro.LoginActivity;
import com.example.myapplication.R;

import com.example.myapplication.model.peserta.BayarSekarangBidModel;
import com.example.myapplication.model.peserta.BayarSekarangPemenangModel;
import com.example.myapplication.model.peserta.WaktuLelangModel;
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

public class DetailLelangActivity extends AppCompatActivity {

    private Button btnTawar, btnKembali1;
    private ImageButton btnKembali;
    private LinearLayout lnBayar, lnIkut;

    private TextView tvHargaAwal, tvPelelang, tvIkan, tvKeterangan, tvBayarNow, tvJam, tvMenit, tvDetik, tvHari,tvJumlah;
    private ImageView ivImage1, ivImage2, ivImage3, ivImage4;

    private Handler handler;
    private Runnable runnable;
    private Dialog dialog;

    SharedPreferences sharedPreferences, detail, bid;

    public static final String detailLelang = "detailLelang";
    public static final String bidLelang = "bidLelang";
    public static final String TAG_ID = "lelang_id";


    String kode,lelang_id,image1, image2, image3, image4, judul, pelelang, keterangan, waktu, stats, harga, bayarsekarang,jumlah,waktu1;
    Integer status;

    @StringRes
    private final int[] TAB_TITLES = new int[]{
            R.string.tab_peraturan,
            R.string.tab_riwayat,
            R.string.tab_testimoni
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_lelang);

        btnKembali = findViewById(R.id.btn_back_lelang);
        btnTawar = findViewById(R.id.btn_tawar1);
        lnBayar = findViewById(R.id.ln_bayar_detail);
        lnIkut = findViewById(R.id.ln_ikut_lelang);

        tvHargaAwal = findViewById(R.id.tv_harga_awal_detail);
        tvIkan = findViewById(R.id.tv_judul_detail);
        tvPelelang = findViewById(R.id.tv_pelelang_detail);
        tvKeterangan = findViewById(R.id.tv_keterangan_detail);
        tvBayarNow = findViewById(R.id.tv_bayar_sekarang_detail);
        tvJam = findViewById(R.id.tv_jam_detail);
        tvMenit = findViewById(R.id.tv_menit_detail);
        tvDetik = findViewById(R.id.tv_detik_detail);
        tvHari = findViewById(R.id.tv_hari_detail);
        tvJumlah = findViewById(R.id.tv_jumlah_detail);

        ivImage1 = findViewById(R.id.iv_image1);
        ivImage2 = findViewById(R.id.iv_image2);
        ivImage3 = findViewById(R.id.iv_image3);
        ivImage4 = findViewById(R.id.iv_image4);

        dialog = new Dialog(this);

        sharedPreferences = getSharedPreferences(LoginActivity.myLelang, Context.MODE_PRIVATE);
        stats = sharedPreferences.getString(LoginActivity.TAG_STATS, "");
        kode = sharedPreferences.getString(LoginActivity.TAG_KODE,"");
        Log.d("kode", kode);

        if (stats.equals("0")) {
            btnTawar.setVisibility(View.GONE);
            lnBayar.setVisibility(View.GONE);
            lnIkut.setVisibility(View.VISIBLE);

        } else {
            btnTawar.setVisibility(View.VISIBLE);
            lnBayar.setVisibility(View.VISIBLE);
            lnIkut.setVisibility(View.GONE);
        }

        Intent intent = getIntent();
        lelang_id = intent.getStringExtra("lelang_id");
        image1 = intent.getStringExtra("image1");
        image2 = intent.getStringExtra("image2");
        image3 = intent.getStringExtra("image3");
        image4 = intent.getStringExtra("image4");
        judul = intent.getStringExtra("produk");
        waktu = intent.getStringExtra("waktu_selesai");
        harga = intent.getStringExtra("harga_awal");
        pelelang = intent.getStringExtra("pelelang_id");
        keterangan = intent.getStringExtra("deskripsi_produk");
        bayarsekarang = intent.getStringExtra("bayar_sekarang");
        jumlah = intent.getStringExtra("jumlah");

        detail = getSharedPreferences(detailLelang, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = detail.edit();
        edit.putString(TAG_ID, lelang_id);
        edit.commit();

        bid = getSharedPreferences(bidLelang, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit1 = detail.edit();
        edit.putString(TAG_ID, lelang_id);
        edit1.commit();

        tvIkan.setText(judul);
        tvPelelang.setText(pelelang);
        tvKeterangan.setText(keterangan);
        tvJumlah.setText(jumlah + " Kg");

        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "HH:mm:ss";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String result = null;

        try {
            date = inputFormat.parse(waktu);
            result = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        countDownLelang();
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");

        tvHargaAwal.setText(decimalFormat.format(Integer.parseInt(harga)));
        tvBayarNow.setText(decimalFormat.format(Integer.parseInt(bayarsekarang)));

        Glide.with(this)
                .load(image1)
                .into(ivImage1);
        Glide.with(DetailLelangActivity.this)
                .load(image2)
                .into(ivImage2);
        Glide.with(DetailLelangActivity.this)
                .load(image3)
                .into(ivImage3);
        Glide.with(DetailLelangActivity.this)
                .load(image4)
                .into(ivImage4);

        btnKembali.setOnClickListener(view -> {
            onBackPressed();
        });
        btnTawar.setOnClickListener(view -> {
            Intent tawar = new Intent(getApplicationContext(), BidActivity.class);
            startActivity(tawar);
        });
        lnBayar.setOnClickListener(view ->{
            openKonfirmasi();
        });
        lnIkut.setOnClickListener(view -> {
            Intent ikut = new Intent(getApplicationContext(), EditAkunActivity.class);
            startActivity(ikut);
        });

        SectionsPagerAdapter sectionsPagerAdapterFragment = new SectionsPagerAdapter(this);
        ViewPager2 viewPagerDetail = findViewById(R.id.view_pager_detail);
        viewPagerDetail.setAdapter(sectionsPagerAdapterFragment);
        TabLayout tabsDetail = findViewById(R.id.tabs_detail);
        new TabLayoutMediator(tabsDetail, viewPagerDetail,
                (tab, position) -> tab.setText(getResources().getString(TAB_TITLES[position]))
        ).attach();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
        }
    }

    private void openKonfirmasi() {
        dialog.setContentView(R.layout.dialog_konfirmasi_bayar_sekarang);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button btnBatal = dialog.findViewById(R.id.btn_batal_bayar_sekarang);
        Button btnSetuju = dialog.findViewById(R.id.btn_setuju_bayar_sekarang);

        btnBatal.setOnClickListener(view ->{
            dialog.dismiss();

        });

        btnSetuju.setOnClickListener(view ->{
            putWaktuLelang();
            postBidLelang();
            postPemenangLelang();
        });

        dialog.show();
    }

    private void postPemenangLelang() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        final BayarSekarangPemenangModel bayarSekarangPemenangModel = new BayarSekarangPemenangModel(lelang_id);
        Call<BayarSekarangPemenangModel> pemenangSekaranglCall = retrofitAPI.postTambahPememangBayarSekarang(bayarSekarangPemenangModel);
        pemenangSekaranglCall.enqueue(new Callback<BayarSekarangPemenangModel>() {
            @Override
            public void onResponse(Call<BayarSekarangPemenangModel> call, Response<BayarSekarangPemenangModel> response) {
                if (response.body().getStats()) {
                    dialog.dismiss();

                } else {
                    Toast.makeText(DetailLelangActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BayarSekarangPemenangModel> call, Throwable t) {
                Toast.makeText(DetailLelangActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void postBidLelang() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        final BayarSekarangBidModel bayarSekarangBidModel = new BayarSekarangBidModel(lelang_id,kode,waktu1,bayarsekarang);
        Call<BayarSekarangBidModel> bidSekaranglCall = retrofitAPI.postTambahBidBayarSekarang(bayarSekarangBidModel);
        bidSekaranglCall.enqueue(new Callback<BayarSekarangBidModel>() {
            @Override
            public void onResponse(Call<BayarSekarangBidModel> call, Response<BayarSekarangBidModel> response) {
                if (response.body().getStats()) {
                    dialog.dismiss();

                } else {
                    Toast.makeText(DetailLelangActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BayarSekarangBidModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void putWaktuLelang() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        final WaktuLelangModel waktuLelangModel = new WaktuLelangModel(lelang_id,waktu1);
        Call<WaktuLelangModel> waktuLelanglCall = retrofitAPI.postWaktuLelang(waktuLelangModel);
        waktuLelanglCall.enqueue(new Callback<WaktuLelangModel>() {
            @Override
            public void onResponse(Call<WaktuLelangModel> call, Response<WaktuLelangModel> response) {
                if (response.body().getStats()) {
                    Intent intent = new Intent(DetailLelangActivity.this,MainActivity.class);
                    startActivity(intent);
                    dialog.dismiss();

                } else {
                    Toast.makeText(DetailLelangActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<WaktuLelangModel> call, Throwable t) {
                Toast.makeText(DetailLelangActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void countDownLelang() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1);
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    Date futureDate = dateFormat.parse(waktu);

                    Date currentDate = new Date();
                    if (!currentDate.after(futureDate)) {
                        long diff = futureDate.getTime() - currentDate.getTime();
                        long days = diff / (24 * 60 * 60 * 1000);
                        diff -= days * (24 * 60 * 60 * 1000);
                        long hours = diff / (60 * 60 * 1000);
                        diff -= hours * (60 * 60 * 1000);
                        long minutes = diff / (60 * 1000);
                        diff -= minutes * (60 * 1000);
                        long seconds = diff / 1000;
                        tvHari.setText("" + String.format("%02d", days));
                        tvJam.setText("" + String.format("%02d", hours));
                        tvMenit.setText("" + String.format("%02d", minutes));
                        tvDetik.setText("" + String.format("%02d", seconds));
                    } else {
                        btnTawar.setVisibility(View.GONE);
                        lnBayar.setVisibility(View.GONE);

//                        String tHari = tvHari.getText().toString();
//                        String tJam = tvJam.getText().toString();
//                        String tMenit = tvMenit.getText().toString();
//                        String tDetik = tvDetik.getText().toString();
//
//                        if (tHari.equals("00") && tJam.equals("00") && tMenit.equals("00") && tDetik.equals("00")) {
//                            Toast.makeText(DetailLelangActivity.this, "TESSSSSS", Toast.LENGTH_SHORT).show();
//                            finish();
//                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 1 * 1);
    }
}