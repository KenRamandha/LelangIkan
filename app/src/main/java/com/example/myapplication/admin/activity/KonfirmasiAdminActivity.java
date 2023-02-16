package com.example.myapplication.admin.activity;

import static com.example.myapplication.admin.adapter.AdapterCardKonfirmasiAdmin.listKonfirmasi;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.admin.adapter.AdapterCardKonfirmasiAdmin;
import com.example.myapplication.model.admin.AdminKonfirmasiModel;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KonfirmasiAdminActivity extends AppCompatActivity implements AdapterCardKonfirmasiAdmin.ItemClickListener {
    private ImageButton btnKembali;
    private RecyclerView recyclerView;
    private AdapterCardKonfirmasiAdmin adapter;
    private LinearLayout   lndetailkonfirmasi;
    private ArrayList<AdminKonfirmasiModel> konfirmasiModelArrayList;
    private TextInputEditText tisearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_konfirmasi_hasil);

        recyclerView = findViewById(R.id.rv_konfirmasi_admin);
        btnKembali = findViewById(R.id.btn_kembali_konfirmasi_admin);
        tisearch = findViewById(R.id.et_query);

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

        btnKembali.setOnClickListener(view -> onBackPressed());

        generateItem();
    }

    private void generateItem() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ArrayList<AdminKonfirmasiModel>> pemenangListCall = retrofitAPI.getAllKonfirmasiAdmin();
        pemenangListCall.enqueue(new Callback<ArrayList<AdminKonfirmasiModel>>(){
            @Override
            public void onResponse(Call<ArrayList<AdminKonfirmasiModel>> call, Response<ArrayList<AdminKonfirmasiModel>> response) {
                if (response.isSuccessful()) {
                    konfirmasiModelArrayList = new ArrayList<>(response.body());
                    adapter = new AdapterCardKonfirmasiAdmin(KonfirmasiAdminActivity.this, konfirmasiModelArrayList);
                    recyclerView.setAdapter(adapter);
                    adapter.setClickListener(KonfirmasiAdminActivity.this);
                } else {
                    Toast.makeText(KonfirmasiAdminActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ArrayList<AdminKonfirmasiModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onClick(View view, int position) {
        final AdminKonfirmasiModel konfirmasiModel = listKonfirmasi.get(position);
        switch (view.getId()){
            default:
                Intent intent = new Intent(this, DetailKonfirmasiAdminActivity.class);
                intent.putExtra("nama_peserta",konfirmasiModel.getNama());
                intent.putExtra("status",konfirmasiModel.getStatus());
                intent.putExtra("konfirmasi_terimaproduk",konfirmasiModel.getKonfirmasi_terimaproduk());
                intent.putExtra("peserta_id",konfirmasiModel.getPeserta_id());
                intent.putExtra("lelang_id",konfirmasiModel.getLelang_id());
                intent.putExtra("produk",konfirmasiModel.getProduk());
                intent.putExtra("jumlah",konfirmasiModel.getJumlah());
                intent.putExtra("testimoni",konfirmasiModel.getTestimoni_pemenang());
                startActivity(intent);
                break;
        }
    }
}
