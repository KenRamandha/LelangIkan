package com.example.myapplication.admin.activity;


import static com.example.myapplication.admin.adapter.AdapterCardLelangAdmin.listProduk;

import android.content.Intent;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myapplication.R;
import com.example.myapplication.admin.adapter.AdapterCardLelangAdmin;
import com.example.myapplication.model.admin.KatalogLelangAdminModel;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LelangActivity extends AppCompatActivity implements AdapterCardLelangAdmin.ItemClickListener {
    private RecyclerView recyclerView;
    private AdapterCardLelangAdmin adapter;
    private ArrayList<KatalogLelangAdminModel> katalogLelangAdminModelArrayList;
    private ImageButton btnKembali;
    private TextInputEditText tisearch;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lelang_admin);

        btnKembali = findViewById(R.id.btn_back_lelang_admin);
        tisearch = findViewById(R.id.et_query);
        recyclerView = findViewById(R.id.rv_lelang_admin);
        recyclerView.setHasFixedSize(true);

        katalogLelangAdminModelArrayList = new ArrayList<>();

        tisearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                adapter.getFilter().filter(editable);
            }
        });

        generateItem();

        btnKembali.setOnClickListener(view -> {
            onBackPressed();
        });
    }

    private void generateItem() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ArrayList<KatalogLelangAdminModel>> katalogListCall = retrofitAPI.produkLelangAdmin();
        katalogListCall.enqueue(new Callback<ArrayList<KatalogLelangAdminModel>>(){
            @Override
            public void onResponse(Call<ArrayList<KatalogLelangAdminModel>> call, Response<ArrayList<KatalogLelangAdminModel>> response) {
                if (response.isSuccessful()) {
                    katalogLelangAdminModelArrayList = response.body();
                    for (int i = 0; i < katalogLelangAdminModelArrayList.size(); i++) {
                        adapter = new AdapterCardLelangAdmin(LelangActivity.this,katalogLelangAdminModelArrayList);
                        LinearLayoutManager manager = new LinearLayoutManager(LelangActivity.this, LinearLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(manager);
                        recyclerView.setAdapter(adapter);
                        adapter.setClickListener(LelangActivity.this);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<KatalogLelangAdminModel>> call, Throwable t) {
                Toast.makeText(LelangActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view, int position) {
        final KatalogLelangAdminModel katalogLelangAdminModel = listProduk.get(position);
        switch (view.getId()) {
            default:
                Intent intent = new Intent(this, AdminDetailLelangActivity.class);
                // Tambahan
                intent.putExtra("harga_minimal_diterima", katalogLelangAdminModel.getHarga_minimal_diterima());
                intent.putExtra("incremental_value", katalogLelangAdminModel.getIncremental_value());
                intent.putExtra("tgl_selesai", katalogLelangAdminModel.getTgl_selesai());
                // ENd Tambahan
                intent.putExtra("image1", katalogLelangAdminModel.getImage1());
                intent.putExtra("lelang_id", katalogLelangAdminModel.getLelang_id());
                intent.putExtra("image2", katalogLelangAdminModel.getImage2());
                intent.putExtra("image3", katalogLelangAdminModel.getImage3());
                intent.putExtra("image4", katalogLelangAdminModel.getImage4());
                intent.putExtra("produk", katalogLelangAdminModel.getProduk());
                intent.putExtra("tgl_mulai", katalogLelangAdminModel.getTgl_mulai());
                intent.putExtra("waktu_selesai", katalogLelangAdminModel.getTgl_selesai());
                intent.putExtra("harga_awal", katalogLelangAdminModel.getHarga_awal());
                intent.putExtra("nama", katalogLelangAdminModel.getNamaPelelang());
                intent.putExtra("desktipsi_produk", katalogLelangAdminModel.getDeskripsi_produk());
                intent.putExtra("bayar_sekarang", katalogLelangAdminModel.getHarga_beli_sekarang());
                intent.putExtra("jumlah", katalogLelangAdminModel.getJumlah());
                intent.putExtra("status",katalogLelangAdminModel.getStatus());
                startActivity(intent);
                break;
        }
    }
}
