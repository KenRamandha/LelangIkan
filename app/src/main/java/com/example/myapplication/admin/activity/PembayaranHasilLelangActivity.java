package com.example.myapplication.admin.activity;


import static com.example.myapplication.admin.adapter.AdapterCardPembayaranHasilLelangAdmin.listPembayaranPanitia;

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
import com.example.myapplication.admin.adapter.AdapterCardPembayaranHasilLelangAdmin;
import com.example.myapplication.model.admin.AdminPembayaranHasilLelangModel;


import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PembayaranHasilLelangActivity extends AppCompatActivity implements AdapterCardPembayaranHasilLelangAdmin.ItemClickListener{

    private ImageButton btnKembali;
    private RecyclerView recyclerView;
    private AdapterCardPembayaranHasilLelangAdmin adapter;
    private TextInputEditText tisearch;

    private ArrayList<AdminPembayaranHasilLelangModel> pembayaranhasillelangModelArrayList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaranhasillelangadmin);
        recyclerView = findViewById(R.id.rv_pembayaranhasillelangadmin);
        btnKembali = findViewById(R.id.btn_kembali_pembayaran);
        tisearch = findViewById(R.id.et_query);
        pembayaranhasillelangModelArrayList = new ArrayList<>();

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
        Call<ArrayList<AdminPembayaranHasilLelangModel>> pembayaranListCall = retrofitAPI.infoPembayaranPanitia();
        pembayaranListCall.enqueue(new Callback<ArrayList<AdminPembayaranHasilLelangModel>>(){
            @Override
            public void onResponse(Call<ArrayList<AdminPembayaranHasilLelangModel>> call, Response<ArrayList<AdminPembayaranHasilLelangModel>> response) {
                if (response.isSuccessful()) {
                    pembayaranhasillelangModelArrayList = response.body();
                    for (int i = 0; i < pembayaranhasillelangModelArrayList.size(); i++) {
                        adapter = new AdapterCardPembayaranHasilLelangAdmin(PembayaranHasilLelangActivity.this, pembayaranhasillelangModelArrayList);
                        recyclerView.setLayoutManager(new LinearLayoutManager(PembayaranHasilLelangActivity.this));
                        recyclerView.setAdapter(adapter);
                        adapter.setClickListener(PembayaranHasilLelangActivity.this);
                    }
                }
            }
            @Override
            public void onFailure(Call<ArrayList<AdminPembayaranHasilLelangModel>> call, Throwable t) {
                Toast.makeText(PembayaranHasilLelangActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view, int position) {
        final AdminPembayaranHasilLelangModel pembayaranhasillelangModel = listPembayaranPanitia.get(position);
        switch (view.getId()){
            default:
                Intent intent = new Intent(this, DetailPembayaranPanitia.class);
                intent.putExtra("bukti_transfer",pembayaranhasillelangModel.getBukti_transfer());
                intent.putExtra("telp",pembayaranhasillelangModel.getTelp());
                intent.putExtra("email",pembayaranhasillelangModel.getEmail());
                intent.putExtra("norekening",pembayaranhasillelangModel.getNorekening());
                intent.putExtra("atasnama",pembayaranhasillelangModel.getAtasnama());

                intent.putExtra("nama_panitia",pembayaranhasillelangModel.getNama_panitia());
                intent.putExtra("nama",pembayaranhasillelangModel.getNama());
                intent.putExtra("nominal_dibayarkan",pembayaranhasillelangModel.getNominal_dibayarkan());
                startActivity(intent);
                break;
        }
    }
}



