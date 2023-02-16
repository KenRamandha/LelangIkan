package com.example.myapplication.panitia.activity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.R;
import com.example.myapplication.admin.activity.PemenangActivity;
import com.example.myapplication.admin.adapter.AdapterCardPemenang;
import com.example.myapplication.model.admin.PemenangModel;
import com.example.myapplication.model.panitia.PanitiaTestimoniModel;
import com.example.myapplication.model.pelelang.KatalogPelelangModel;
import com.example.myapplication.panitia.adapter.AdapterCardTestimoniPanitia;
import com.example.myapplication.panitia.section.SectionsAdapterTestimoni;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestimoniPanitiaActivity extends AppCompatActivity {
    private ImageButton btnBack;
    private RecyclerView rvTestimoni;
    private AdapterCardTestimoniPanitia adapter;
    private ArrayList<PanitiaTestimoniModel> testimoniModels;
    private ArrayList<PanitiaTestimoniModel> cobamodels = new ArrayList<>();
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testimoni_panitia);

        btnBack = findViewById(R.id.btn_kembali_testimoni);
        btnBack.setOnClickListener(view -> {
            onBackPressed();
        });
        rvTestimoni = findViewById(R.id.rv_testimoni_panitia);

        testimoniModels = new ArrayList<>();

//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                if (query.length() > 0){
//                    cobamodels.clear();
//                    for (PanitiaTestimoniModel model : testimoniModels){
//                        if (model.getNama().toLowerCase().contains(query.toLowerCase()) ||
//                                model.getTestimoni_pemenang().toLowerCase().contains(query.toLowerCase())){
//                            cobamodels.add(model);
//                        }
//                    }
//                    adapter.notifyDataSetChanged();
//                }
//                else {
//                    cobamodels.clear();
//                    cobamodels.addAll(testimoniModels);
//                    adapter.notifyDataSetChanged();
//                }
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                cobamodels.clear();
//                cobamodels.addAll(testimoniModels);
//                adapter.notifyDataSetChanged();
//                return false;
//            }
//        });

        generateItem();
    }

    private void generateItem() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ArrayList<PanitiaTestimoniModel>> testimoniListCall = retrofitAPI.getAllTestimoniPanitia();
        testimoniListCall.enqueue(new Callback<ArrayList<PanitiaTestimoniModel>>(){
            @Override
            public void onResponse(Call<ArrayList<PanitiaTestimoniModel>> call, Response<ArrayList<PanitiaTestimoniModel>> response) {
                if (response.isSuccessful()) {
                    testimoniModels = response.body();
                    for (int i = 0; i < testimoniModels.size(); i++) {
                        adapter = new AdapterCardTestimoniPanitia(TestimoniPanitiaActivity.this, testimoniModels);
                        rvTestimoni.setLayoutManager(new LinearLayoutManager(TestimoniPanitiaActivity.this));
                        rvTestimoni.setAdapter(adapter);
                    }
                }
            }
            @Override
            public void onFailure(Call<ArrayList<PanitiaTestimoniModel>> call, Throwable t) {
                Toast.makeText(TestimoniPanitiaActivity.this, "Failed to get data", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        generateItem();
    }
}