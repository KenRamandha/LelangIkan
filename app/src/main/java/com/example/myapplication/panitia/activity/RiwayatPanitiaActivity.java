package com.example.myapplication.panitia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.panitia.PanitiaPesertaModel;
import com.example.myapplication.model.panitia.PanitiaRiwayatLelangModel;
import com.example.myapplication.panitia.adapter.AdapterCardPesertaPanitia;
import com.example.myapplication.panitia.adapter.AdapterCardRiwayatLelangPanitia;
import com.example.myapplication.panitia.fragment.FragmentStatusPesertaPanitia;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiwayatPanitiaActivity extends AppCompatActivity implements AdapterCardRiwayatLelangPanitia.ItemClickListener{
    
    private RecyclerView recyclerView;
    private AdapterCardRiwayatLelangPanitia adapter;
    private ArrayList<PanitiaRiwayatLelangModel> panitiaRiwayatLelangModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_panitia);
        
        recyclerView = findViewById(R.id.rv_riwayat_lelang_panitia);
        recyclerView.setHasFixedSize(true);
        
        panitiaRiwayatLelangModels = new ArrayList<>();
        
        generateItem();
    }

    private void generateItem() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ArrayList<PanitiaRiwayatLelangModel>> panitiaRiwayatLelangListCall = retrofitAPI.getRiwayatLelangPanitia();
        panitiaRiwayatLelangListCall.enqueue(new Callback<ArrayList<PanitiaRiwayatLelangModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PanitiaRiwayatLelangModel>> call, Response<ArrayList<PanitiaRiwayatLelangModel>> response) {
                if (response.isSuccessful()) {
                    panitiaRiwayatLelangModels = response.body();
                    for (int i = 0; i < panitiaRiwayatLelangModels.size(); i++) {
                        adapter = new AdapterCardRiwayatLelangPanitia(RiwayatPanitiaActivity.this, panitiaRiwayatLelangModels);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RiwayatPanitiaActivity.this, LinearLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(adapter);
                        adapter.setClickListener(RiwayatPanitiaActivity.this);
                    }
                }
            }
            @Override
            public void onFailure(Call<ArrayList<PanitiaRiwayatLelangModel>> call, Throwable t) {
                Toast.makeText(RiwayatPanitiaActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view, int position) {
        final PanitiaRiwayatLelangModel model = panitiaRiwayatLelangModels.get(position);
        switch (view.getId()){
            default:
                Intent intent = new Intent(RiwayatPanitiaActivity.this, RiwayatLelangPanitiaActivity.class);
                intent.putExtra("image1",model.getImage1());
                intent.putExtra("image2",model.getImage2());
                intent.putExtra("image3",model.getImage3());
                intent.putExtra("image4",model.getImage4());
                intent.putExtra("pelelang",model.getNama_pelelang());
                intent.putExtra("produk",model.getProduk());
                intent.putExtra("harga",model.getHarga_awal());
                intent.putExtra("tanggal",model.getTgl_selesai());
                intent.putExtra("peserta",model.getNama_peserta());
                intent.putExtra("total",model.getTotal_bayar());
                intent.putExtra("provinsi",model.getProvinsi_kirim());
                intent.putExtra("kota",model.getKota_kirim());
                intent.putExtra("kecamatan",model.getKecamatan_kirim());
                intent.putExtra("kelurahan",model.getKelurahan_kirim());
                intent.putExtra("alamat",model.getAlamat());
                startActivity(intent);
                break;
        }
    }
}
