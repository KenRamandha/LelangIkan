package com.example.myapplication.panitia.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.intro.LoginActivity;
import com.example.myapplication.model.panitia.PanitiaCalonPemenangModel;
import com.example.myapplication.model.panitia.PanitiaPemenangCalonModel;
import com.example.myapplication.model.panitia.PanitiaPilihanPembayaranModel;
import com.example.myapplication.model.panitia.PanitiaPilihanPemenangModel;
import com.example.myapplication.model.peserta.WaktuLelangModel;
import com.example.myapplication.panitia.adapter.AdapterCardPenawaranPemenangPanitia;
import com.example.myapplication.peserta.activity.DetailLelangActivity;
import com.example.myapplication.peserta.activity.MainActivity;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.text.DecimalFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListPenawaranPanitiaActivity extends AppCompatActivity implements AdapterCardPenawaranPemenangPanitia.itemClickListener {

    private ImageButton btnBack;
    private TextView tvProduk, textView, textView1;
    private RecyclerView recyclerView;
    private Button button;

    private AdapterCardPenawaranPemenangPanitia adapter;
    private ArrayList<PanitiaCalonPemenangModel> panitiaCalonPemenangModels;
    private PanitiaPemenangCalonModel model;
    private LinearLayout linearLayout;
    private CardView cardView;

    private SharedPreferences sharedPreferences,path;
    public static final String calonPemanang = "calonPemanang";
    private Dialog dialog;
    
    String lelang_id, produk, panitia_id,peserta_id,total_bayar,tgl_diumumkan,status,waktu1;
    String lelang_id1,pesertaId1,tgl_diumumkan1,status1,panitia_id1,harga1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panitia_calon_penawaran);

        btnBack = findViewById(R.id.btn_kembali_penawaran_calon_pemenang);
        tvProduk = findViewById(R.id.tv_produk_penawaran_calon_pemenang);
        linearLayout = findViewById(R.id.ln_pemenang_lelang_panitia);
        cardView = findViewById(R.id.cv_pemenang_lelang);
        textView = findViewById(R.id.tv_peserta_calon_pemenang);
        textView1 = findViewById(R.id.tv_idpeserta_calon_pemenang);

        button = findViewById(R.id.btn_pemenang1_calon_panitia);



        recyclerView = findViewById(R.id.rv_penawaran_calon_pemenang);
        recyclerView.setHasFixedSize(true);

        btnBack.setOnClickListener(view-> onBackPressed());
        
        dialog = new Dialog(this);

        lelang_id = getIntent().getStringExtra("id");
        produk = getIntent().getStringExtra("produk");

        tvProduk.setText(produk);

        path = getSharedPreferences(LoginActivity.myLelang,Context.MODE_PRIVATE);
        panitia_id = path.getString(LoginActivity.TAG_KODE,"");

        sharedPreferences = getSharedPreferences(calonPemanang, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(calonPemanang, lelang_id);
        edit.commit();

        generatePemenang();

        generateItem();

        button.setOnClickListener(view->{
            postPemenangLangsung();
        });
    }



    private void generatePemenang() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<PanitiaPemenangCalonModel>pemenangPanitiaListCall = retrofitAPI.getPemenangCalonPanitia(lelang_id);
        pemenangPanitiaListCall.enqueue(new Callback<PanitiaPemenangCalonModel>() {
            @Override
            public void onResponse(Call<PanitiaPemenangCalonModel> call, Response<PanitiaPemenangCalonModel> response) {
                if (response.isSuccessful()){
                    linearLayout.setVisibility(View.GONE);
                    cardView.setVisibility(View.VISIBLE);
                    button.setVisibility(View.VISIBLE);
                    textView.setText(response.body().getNama());
                    textView1.setText("("+response.body().getPeserta_id()+")");
                    lelang_id1 = response.body().getLelang_id();
                    pesertaId1 = response.body().getPeserta_id() ;
                    harga1 = response.body().getNominal_dibayarkan();
                }
            }
            @Override
            public void onFailure(Call<PanitiaPemenangCalonModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void generateItem() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ArrayList<PanitiaCalonPemenangModel>> produkPanitiaListCall = retrofitAPI.getCalonPemanang(lelang_id);
        produkPanitiaListCall.enqueue(new Callback<ArrayList<PanitiaCalonPemenangModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PanitiaCalonPemenangModel>> call, Response<ArrayList<PanitiaCalonPemenangModel>> response) {
                if (response.isSuccessful()) {
                    panitiaCalonPemenangModels = response.body();
                    for (int i = 0; i < panitiaCalonPemenangModels.size(); i++) {
                        adapter = new AdapterCardPenawaranPemenangPanitia(ListPenawaranPanitiaActivity.this, panitiaCalonPemenangModels);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ListPenawaranPanitiaActivity.this, LinearLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(adapter);
                        adapter.setClickListener(ListPenawaranPanitiaActivity.this);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PanitiaCalonPemenangModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onItemClick(PanitiaCalonPemenangModel panitiaCalonPemenangModel, View view, int position) {
        switch (view.getId()){
            default:
                String nama, pesertaId, harga;
                nama = panitiaCalonPemenangModel.getNama();
                pesertaId = panitiaCalonPemenangModel.getPeserta_id();
                harga = panitiaCalonPemenangModel.getHarga_tawar();
                openCalonDialog(nama,pesertaId,harga);
                break;
        }
    }

    private void openCalonDialog(String nama, String pesertaId, String harga) {
        dialog.setContentView(R.layout.dialog_calon_pemenang_panitia);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageButton btnBack=dialog.findViewById(R.id.btn_back_dialog_calon_panitia);
        
        Button btnPemenang = dialog.findViewById(R.id.btn_pemenang_calon_panitia);
        
        TextView tvNama,tvId,tvHarga;
        tvNama = dialog.findViewById(R.id.tv_nama_calon_panitia);
        tvId = dialog.findViewById(R.id.tv_pesertaId_calon_panitia);
        tvHarga = dialog.findViewById(R.id.tv_harga_calon_panitia);

        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        
        tvNama.setText(nama);
        tvId.setText(pesertaId);
        tvHarga.setText("Rp."+decimalFormat.format(Integer.parseInt(harga)));

        btnBack.setOnClickListener(view -> {
            dialog.dismiss();
        });
        
        btnPemenang.setOnClickListener(view -> {
            postPemenang(pesertaId,harga);
            postPembayaran();
            putWaktuLelang();
            Intent intent = new Intent(ListPenawaranPanitiaActivity.this, PanitiaActivity.class);
            startActivity(intent);
        });
        
        dialog.show();
    }

    private void putWaktuLelang() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        final WaktuLelangModel waktuLelangModel = new WaktuLelangModel(lelang_id,waktu1);
        Call<WaktuLelangModel> waktuLelanglCall = retrofitAPI.postWaktuLelang(waktuLelangModel);
        waktuLelanglCall.enqueue(new Callback<WaktuLelangModel>() {
            @Override
            public void onResponse(Call<WaktuLelangModel> call, Response<WaktuLelangModel> response) {
                if (response.body().getStats()) {
                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<WaktuLelangModel> call, Throwable t) {
                Toast.makeText(ListPenawaranPanitiaActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void postPemenang(String pesertaId, String harga) {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        final PanitiaPilihanPemenangModel panitiaPilihanPemenangModel = new PanitiaPilihanPemenangModel(lelang_id,pesertaId,tgl_diumumkan,status,panitia_id,harga);
        Call<PanitiaPilihanPemenangModel> panitiaPemenanglCall = retrofitAPI.postPilihanPemenangPanitia(panitiaPilihanPemenangModel);
        panitiaPemenanglCall.enqueue(new Callback<PanitiaPilihanPemenangModel>() {
            @Override
            public void onResponse(Call<PanitiaPilihanPemenangModel> call, Response<PanitiaPilihanPemenangModel> response) {
                if (response.body().getStats()) {
                    dialog.dismiss();

                } else {
                    Toast.makeText(ListPenawaranPanitiaActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PanitiaPilihanPemenangModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void postPemenangLangsung() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        final PanitiaPilihanPemenangModel panitiaPilihanPemenangModel = new PanitiaPilihanPemenangModel(lelang_id,pesertaId1,tgl_diumumkan,status,panitia_id,harga1);
        Call<PanitiaPilihanPemenangModel> panitiaPemenanglCall = retrofitAPI.postPilihanPemenangPanitia(panitiaPilihanPemenangModel);
        panitiaPemenanglCall.enqueue(new Callback<PanitiaPilihanPemenangModel>() {
            @Override
            public void onResponse(Call<PanitiaPilihanPemenangModel> call, Response<PanitiaPilihanPemenangModel> response) {
                if (response.body().getStats()) {
                    onBackPressed();
                } else {
                    Toast.makeText(ListPenawaranPanitiaActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PanitiaPilihanPemenangModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void postPembayaran() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        final PanitiaPilihanPembayaranModel panitiaPilihanPembayaranModel = new PanitiaPilihanPembayaranModel(lelang_id,panitia_id);
        Call<PanitiaPilihanPembayaranModel> panitiaPemenanglCall = retrofitAPI.postPilihanPembayaranPanitia(panitiaPilihanPembayaranModel);
        panitiaPemenanglCall.enqueue(new Callback<PanitiaPilihanPembayaranModel>() {
            @Override
            public void onResponse(Call<PanitiaPilihanPembayaranModel> call, Response<PanitiaPilihanPembayaranModel> response) {
                if (response.body().getStats()) {
                    dialog.dismiss();

                } else {
                    Toast.makeText(ListPenawaranPanitiaActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PanitiaPilihanPembayaranModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}
