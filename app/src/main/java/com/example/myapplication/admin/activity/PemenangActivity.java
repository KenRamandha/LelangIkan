package com.example.myapplication.admin.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.admin.adapter.AdapterCardPemenang;
import com.example.myapplication.model.admin.PemenangModel;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PemenangActivity extends AppCompatActivity {

    private ImageButton btnKembali;
    private RecyclerView recyclerView;
    private AdapterCardPemenang adapter;
    private TextInputEditText tisearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemenang_admin);

        btnKembali = findViewById(R.id.btn_kembali_pemenang);
        tisearch = findViewById(R.id.et_query);
        recyclerView = findViewById(R.id.rv_pemenang_admin);

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
        Call<ArrayList<PemenangModel>> pemenangListCall = retrofitAPI.getAllPemenang();
        pemenangListCall.enqueue(new Callback<ArrayList<PemenangModel>>(){
            @Override
            public void onResponse(Call<ArrayList<PemenangModel>> call, Response<ArrayList<PemenangModel>> response) {
                if (response.isSuccessful()) {
                    ArrayList<PemenangModel> pemenangModels = new ArrayList<>(response.body());
                    adapter = new AdapterCardPemenang(PemenangActivity.this, pemenangModels);
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(PemenangActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ArrayList<PemenangModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
