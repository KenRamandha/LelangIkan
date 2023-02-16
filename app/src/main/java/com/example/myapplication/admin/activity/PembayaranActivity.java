package com.example.myapplication.admin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import static com.example.myapplication.admin.adapter.AdapterCardPembayaran.listPembayaran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.admin.adapter.AdapterCardPembayaran;
import com.example.myapplication.model.admin.AdminPembayaranModel;
import com.example.myapplication.model.admin.PemenangModel;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PembayaranActivity extends AppCompatActivity implements AdapterCardPembayaran.ItemClickListener{
    private ImageButton btnKembali;
    private RecyclerView recyclerView;
    private AdapterCardPembayaran adapter;
    private TextInputEditText tipencarian;

    private ArrayList<AdminPembayaranModel> pembayaranModels;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran_admin);
        btnKembali = findViewById(R.id.btn_kembali_pembayaran);
        tipencarian = findViewById(R.id.et_bang);
        recyclerView = findViewById(R.id.rv_pembayaran_admin);

        pembayaranModels = new ArrayList<>();


//        findViewById(R.id.btn_search).setOnClickListener(view -> {
//            Log.d("KKK", tipencarian.getText().toString());
//            adapter.getFilter().filter(tipencarian.getText().toString());
//        });
//
//        btnKembali.setOnClickListener(view ->{
//            onBackPressed();
//        });
//
//        generateItem();
        tipencarian.addTextChangedListener(new TextWatcher() {
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
        Call<ArrayList<AdminPembayaranModel>> pemenangListCall = retrofitAPI.getAllPembayaranAdmin();
        pemenangListCall.enqueue(new Callback<ArrayList<AdminPembayaranModel>>(){
            @Override
            public void onResponse(Call<ArrayList<AdminPembayaranModel>> call, Response<ArrayList<AdminPembayaranModel>> response) {
                if (response.isSuccessful()) {
                    pembayaranModels = response.body();
                    for (int i = 0; i < pembayaranModels.size(); i++) {
                        adapter = new AdapterCardPembayaran(PembayaranActivity.this, pembayaranModels);
                        recyclerView.setLayoutManager(new LinearLayoutManager(PembayaranActivity.this));
                        recyclerView.setAdapter(adapter);
                        adapter.setClickListener(PembayaranActivity.this);
                    }
                }
            }
            @Override
            public void onFailure(Call<ArrayList<AdminPembayaranModel>> call, Throwable t) {
                Toast.makeText(PembayaranActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view, int position) {
        final AdminPembayaranModel pembayaranModel = listPembayaran.get(position);
        switch (view.getId()){
            default:
                Intent intent = new Intent(this, DetailPembayaran.class);

                intent.putExtra("bukti_pembayaran",pembayaranModel.getBukti_pembayaran());

                intent.putExtra("nama_peserta",pembayaranModel.getNama_peserta());
                intent.putExtra("produk",pembayaranModel.getProduk());
                intent.putExtra("nominal_dibayarkan",pembayaranModel.getNominal_dibayarkan());
                startActivity(intent);
                break;
        }
    }
}
