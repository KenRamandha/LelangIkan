package com.example.myapplication.panitia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.panitia.PanitiaPemenangModel;
import com.example.myapplication.model.panitia.PanitiaPesertaModel;
import com.example.myapplication.panitia.adapter.AdapterCardPemenangPanitia;
import com.example.myapplication.panitia.adapter.AdapterCardPesertaPanitia;
import com.example.myapplication.panitia.fragment.FragmentStatusPesertaPanitia;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatusPemenangPanitiaActivity extends AppCompatActivity implements AdapterCardPemenangPanitia.ItemClickListener {
    private ImageButton btnBack;
    private RecyclerView recyclerView;
    
    private AdapterCardPemenangPanitia adapterCardPemenangPanitia;
    private ArrayList<PanitiaPemenangModel> panitiaPemenangModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_pemenang_panitia);

        btnBack = findViewById(R.id.btn_kembali_pemenang);
        recyclerView = findViewById(R.id.rv_pemenang_lelang_panitia);
        recyclerView.setHasFixedSize(true);
        
        panitiaPemenangModels = new ArrayList<>();
        
        generateItem();

        btnBack.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    private void generateItem() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ArrayList<PanitiaPemenangModel>> pemenangPanitia = retrofitAPI.getAllPemenangPanitia();
        pemenangPanitia.enqueue(new Callback<ArrayList<PanitiaPemenangModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PanitiaPemenangModel>> call, Response<ArrayList<PanitiaPemenangModel>> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(getView().getContext(), "bebas", Toast.LENGTH_SHORT).show();
                    panitiaPemenangModels = response.body();
                    for (int i = 0; i < panitiaPemenangModels.size(); i++) {
                        adapterCardPemenangPanitia = new AdapterCardPemenangPanitia(StatusPemenangPanitiaActivity.this, panitiaPemenangModels);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(StatusPemenangPanitiaActivity.this, LinearLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(adapterCardPemenangPanitia);
                        adapterCardPemenangPanitia.setClickListener(StatusPemenangPanitiaActivity.this);
                    }
                }
            }
            @Override
            public void onFailure(Call<ArrayList<PanitiaPemenangModel>> call, Throwable t) {
                Toast.makeText(StatusPemenangPanitiaActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view, int position) {
        final PanitiaPemenangModel panitiaPemenangModel = panitiaPemenangModels.get(position);
        switch (view.getId()){
            default:
                Intent intent = new Intent(this, DetailPemenangPanitiaActivity.class);
                intent.putExtra("peserta_id",panitiaPemenangModel.getPeserta_id());
                intent.putExtra("lelang_id",panitiaPemenangModel.getLelang_id());
                intent.putExtra("nama",panitiaPemenangModel.getNama());
                intent.putExtra("jeniskel",panitiaPemenangModel.getJeniskel());
                intent.putExtra("provinsi",panitiaPemenangModel.getProvinsi());
                intent.putExtra("kota",panitiaPemenangModel.getKota());
                intent.putExtra("kecamatan",panitiaPemenangModel.getKecamatan());
                intent.putExtra("alamat",panitiaPemenangModel.getAlamat());
                intent.putExtra("telp",panitiaPemenangModel.getTelp());
                intent.putExtra("email",panitiaPemenangModel.getEmail());
                intent.putExtra("nama_lelang",panitiaPemenangModel.getProduk());
                intent.putExtra("tanggal_lelang",panitiaPemenangModel.getTgl_selesai());
                intent.putExtra("total",panitiaPemenangModel.getTotal_bayar());
                intent.putExtra("file_dokumen", panitiaPemenangModel.getBukti_bayar());
                startActivity(intent);
                break;
        }

    }
}