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
import com.example.myapplication.model.panitia.PanitiaPengemasanModel;
import com.example.myapplication.model.panitia.PanitiaTerkirimModel;
import com.example.myapplication.panitia.adapter.AdapterCardPengemasanPanitia;
import com.example.myapplication.panitia.adapter.AdapterCardTerkirimPanitia;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentTerkirimPanitia extends Fragment {

    private RecyclerView recyclerView;
    private AdapterCardTerkirimPanitia adapter;
    private ArrayList<PanitiaTerkirimModel> panitiaTerkirimModels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_terkirim_panitia, container, false);

        recyclerView = view.findViewById(R.id.rv_terkirim_panitia);
        recyclerView.setHasFixedSize(true);
        panitiaTerkirimModels = new ArrayList<>();

        generateItem();

        return view;
    }

    private void generateItem() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ArrayList<PanitiaTerkirimModel>> terkirimPanitiaListCall = retrofitAPI.getAllTerkirimPanitia();
        terkirimPanitiaListCall.enqueue(new Callback<ArrayList<PanitiaTerkirimModel>>(){
            @Override
            public void onResponse(Call<ArrayList<PanitiaTerkirimModel>> call, Response<ArrayList<PanitiaTerkirimModel>> response) {
                if (response.isSuccessful()) {
                    panitiaTerkirimModels = response.body();
                    for (int i = 0; i < panitiaTerkirimModels.size(); i++) {
                        adapter = new AdapterCardTerkirimPanitia(getView().getContext(),panitiaTerkirimModels);
                        LinearLayoutManager manager = new LinearLayoutManager(getView().getContext(), LinearLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(manager);
                        recyclerView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PanitiaTerkirimModel>> call, Throwable t) {
                Toast.makeText(getView().getContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}