package com.example.myapplication.panitia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.panitia.PanitiaPesertaModel;
import com.example.myapplication.model.panitia.PanitiaSuratPerintahModel;
import com.example.myapplication.panitia.adapter.AdapterCardPesertaPanitia;
import com.example.myapplication.panitia.adapter.AdapterCardSuratPanitia;
import com.example.myapplication.panitia.fragment.FragmentStatusPesertaPanitia;
import com.example.myapplication.pelelang.adapter.AdapterTestimoni;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SuratPanitiaActivity extends AppCompatActivity implements AdapterCardSuratPanitia.ItemClickListener {
    private ImageButton btnBack;

    private AdapterCardSuratPanitia adapter;
    private RecyclerView recyclerView;
    private ArrayList<PanitiaSuratPerintahModel> panitiaSuratPerintahModels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_surat_panitia);

        recyclerView = findViewById(R.id.rv_surat_perintah_panitia);
        recyclerView.setHasFixedSize(true);

        btnBack = findViewById(R.id.btn_kembali_surat);
        btnBack.setOnClickListener(view -> {
            onBackPressed();
        });

        generateItem();
    }

    private void generateItem() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ArrayList<PanitiaSuratPerintahModel>> suratPanitiaListCall = retrofitAPI.getAllSuratPerintahPanitia();
        suratPanitiaListCall.enqueue(new Callback<ArrayList<PanitiaSuratPerintahModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PanitiaSuratPerintahModel>> call, Response<ArrayList<PanitiaSuratPerintahModel>> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(getView().getContext(), "bebas", Toast.LENGTH_SHORT).show();
                    panitiaSuratPerintahModels = response.body();
                    for (int i = 0; i < panitiaSuratPerintahModels.size(); i++) {
                        adapter = new AdapterCardSuratPanitia(SuratPanitiaActivity.this, panitiaSuratPerintahModels);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SuratPanitiaActivity.this, LinearLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(adapter);
                        adapter.setClickListener(SuratPanitiaActivity.this);
                    }
                }
            }
            @Override
            public void onFailure(Call<ArrayList<PanitiaSuratPerintahModel>> call, Throwable t) {
                Toast.makeText(SuratPanitiaActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view, int position) {
        final PanitiaSuratPerintahModel panitiaSuratPerintahModel = panitiaSuratPerintahModels.get(position);
        switch (view.getId()){
            default:
                Intent intent = new Intent(SuratPanitiaActivity.this,ActivityDetailSuratPengirimanPanitia.class);
                intent.putExtra("peserta_id", panitiaSuratPerintahModel.getPeserta_id());
                intent.putExtra("nama_peserta", panitiaSuratPerintahModel.getNama_peserta());
                intent.putExtra("nama_pelelang", panitiaSuratPerintahModel.getNama_pelelang());
                intent.putExtra("lelang_id", panitiaSuratPerintahModel.getLelang_id());
                intent.putExtra("pelelang_id", panitiaSuratPerintahModel.getPelelang_id());
                intent.putExtra("pengiriman_id", panitiaSuratPerintahModel.getPengiriman_id());
                intent.putExtra("no_hp", panitiaSuratPerintahModel.getNo_hp());
                intent.putExtra("kendaraan",panitiaSuratPerintahModel.getNama_kendaraan());
                intent.putExtra("nopol",panitiaSuratPerintahModel.getNo_polisi());
                intent.putExtra("provinsi", panitiaSuratPerintahModel.getProvinsi_kirim());
                intent.putExtra("kota", panitiaSuratPerintahModel.getKota_kirim());
                intent.putExtra("kecamatan", panitiaSuratPerintahModel.getKecamatan_kirim());
                intent.putExtra("kelurahan", panitiaSuratPerintahModel.getKelurahan_kirim());
                intent.putExtra("alamat", panitiaSuratPerintahModel.getAlamat_kirim());
                intent.putExtra("telp", panitiaSuratPerintahModel.getTelp());
                intent.putExtra("status", panitiaSuratPerintahModel.getStatus_pengiriman());
                intent.putExtra("diumumkan", panitiaSuratPerintahModel.getTgl_diumumkan());
                intent.putExtra("jumlah",panitiaSuratPerintahModel.getJumlah());
                intent.putExtra("produk",panitiaSuratPerintahModel.getProduk());
                intent.putExtra("status_transaksi",panitiaSuratPerintahModel.getStatus_transaksi());
                intent.putExtra("kotap", panitiaSuratPerintahModel.getKota());
                intent.putExtra("kecamatanp", panitiaSuratPerintahModel.getKecamatan());

                startActivity(intent);
                break;
        }
    }
}