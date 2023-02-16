package com.example.myapplication.panitia.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.panitia.ProdukPanitiaModel;
import com.example.myapplication.panitia.adapter.AdapterCardTerverifikasiPanitia;
import com.example.myapplication.panitia.adapter.AdapterCardVerifikasiPanitia;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentTerverifikasiPanitia extends Fragment {

    private RecyclerView recyclerView;
    private AdapterCardTerverifikasiPanitia adapter;
    private ArrayList<ProdukPanitiaModel> produkPanitiaModels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_terverifikasi_panitia, container, false);

        recyclerView = view.findViewById(R.id.rv_terverif_panitia);
        recyclerView.setHasFixedSize(true);


        produkPanitiaModels = new ArrayList<>();

        generateItem();

        return view;
    }

    private void generateItem() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ArrayList<ProdukPanitiaModel>> produkPanitiaListCall = retrofitAPI.getAllVerifProductPanitia();
        produkPanitiaListCall.enqueue(new Callback<ArrayList<ProdukPanitiaModel>>() {
            @Override
            public void onResponse(Call<ArrayList<ProdukPanitiaModel>> call, Response<ArrayList<ProdukPanitiaModel>> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(getView().getContext(), "bebas", Toast.LENGTH_SHORT).show();
                    produkPanitiaModels = response.body();
                    for (int i = 0; i < produkPanitiaModels.size(); i++) {
                        adapter = new AdapterCardTerverifikasiPanitia(getView().getContext(), produkPanitiaModels);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ProdukPanitiaModel>> call, Throwable t) {
                Toast.makeText(getView().getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}