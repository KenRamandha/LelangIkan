package com.example.myapplication.peserta.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.intro.LoginActivity;
import com.example.myapplication.model.peserta.BayarSekarangBidModel;
import com.example.myapplication.model.peserta.PenawaranModel;
import com.example.myapplication.model.peserta.ProfilePesertaModel;
import com.example.myapplication.peserta.section.MinFilter;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.text.DecimalFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BidActivity extends AppCompatActivity {

    private Button btnBid;
    private ImageButton btnKembali;
    private Dialog dialog;
    private EditText etBid;
    private TextView tvTertinggi;

    SharedPreferences sharedPreferences,sharedPreferences1;

    String lelang_id,kode,waktu1,harga_tawar;
    Integer kelipatan,harga_awal,hasil,hasil1,bid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bid);

        btnBid = findViewById(R.id.btn_bid);
        btnKembali= findViewById(R.id.btn_back_bid);
        etBid = findViewById(R.id.et_bid);
        tvTertinggi = findViewById(R.id.tv_bid_tertinggi);

        sharedPreferences = getSharedPreferences(DetailLelangActivity.detailLelang, Context.MODE_PRIVATE);
        lelang_id = sharedPreferences.getString(DetailLelangActivity.TAG_ID, "");
        Log.d("id",lelang_id);

        sharedPreferences1 = getSharedPreferences(LoginActivity.myLelang,Context.MODE_PRIVATE);
        kode = sharedPreferences1.getString(LoginActivity.TAG_KODE,"");
        Log.d("idpeserta",kode);

        loadBid();

//        if (bid !=null){
//            try{
//                etBid.setFilters(new InputFilter[]{new MinFilter(bid)});
//            }catch (NumberFormatException e){
//                etBid = "0";
//            }
//        }
//        etBid.setFilters(new InputFilter[]{new MinFilter(bid)});


        btnKembali.setOnClickListener(view -> {
            onBackPressed();
        });

        dialog = new Dialog(this);
//        btnBid.setOnClickListener(view -> openBidDialog());
        btnBid.setOnClickListener(view -> {
            if (Integer.parseInt(etBid.getText().toString()) < hasil) {
                Toast.makeText(this, "Bid Kurang Tinggi", Toast.LENGTH_SHORT).show();
            } else {
                postBid();
            }
        });
    }

    private void postBid() {
        harga_tawar = etBid.getText().toString();
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        final BayarSekarangBidModel bayarSekarangBidModel = new BayarSekarangBidModel(lelang_id,kode,waktu1,harga_tawar);
        Call<BayarSekarangBidModel> bidSekaranglCall = retrofitAPI.postTambahBidBayarSekarang(bayarSekarangBidModel);
        bidSekaranglCall.enqueue(new Callback<BayarSekarangBidModel>() {
            @Override
            public void onResponse(Call<BayarSekarangBidModel> call, Response<BayarSekarangBidModel> response) {
                if (response.body().getStats()) {
                    openBidDialog();

                } else {
                    Toast.makeText(BidActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BayarSekarangBidModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void loadBid() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<PenawaranModel> peanwaranModelCall = retrofitAPI.getPenawaran(lelang_id);
        peanwaranModelCall.enqueue(new Callback<PenawaranModel>() {
            @Override
            public void onResponse(Call<PenawaranModel> call, Response<PenawaranModel> response) {
                if (response.isSuccessful()) {
                  bid = response.body().getHarga_tawar();
                  kelipatan = response.body().getIncremental_value();
                  harga_awal = response.body().getHarga_awal();
                  if (response.body().getHarga_tawar() == 0){
//                      hasil =  harga_awal + kelipatan;
                      hasil = harga_awal;
                      etBid.setText(hasil + "");
                  } else {
                      hasil = bid + kelipatan;
                      etBid.setText(hasil + "");
                  }
                  DecimalFormat decimalFormat = new DecimalFormat("#,##0");
                  if (bid !=null) {
                      tvTertinggi.setText("Rp." + decimalFormat.format(bid));
                  }
                }
            }

            @Override
            public void onFailure(Call<PenawaranModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void openBidDialog() {
        dialog.setContentView(R.layout.dialog_bid_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btnKembali = dialog.findViewById(R.id.btn_kembali_dbid);

        btnKembali.setOnClickListener(view -> {
            onBackPressed();
            super.onRestart();
            dialog.dismiss();
        });
        dialog.show();
    }
}
