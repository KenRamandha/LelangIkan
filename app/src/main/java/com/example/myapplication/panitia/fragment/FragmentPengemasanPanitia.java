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
import com.example.myapplication.panitia.adapter.AdapterCardPengemasanPanitia;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentPengemasanPanitia extends Fragment {

    private RecyclerView recyclerView;
    private AdapterCardPengemasanPanitia adapter;
    private ArrayList<PanitiaPengemasanModel> panitiaPengemasanModels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_pengemasan_panitia, container, false);

        recyclerView = view.findViewById(R.id.rv_pengemasan_panitia);
        recyclerView.setHasFixedSize(true);
        panitiaPengemasanModels = new ArrayList<>();

        generateItem();

        return view;
    }

    private void generateItem() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ArrayList<PanitiaPengemasanModel>> pengemasanPanitiaListCall = retrofitAPI.getAllPengemasanPanitia();
        pengemasanPanitiaListCall.enqueue(new Callback<ArrayList<PanitiaPengemasanModel>>(){
            @Override
            public void onResponse(Call<ArrayList<PanitiaPengemasanModel>> call, Response<ArrayList<PanitiaPengemasanModel>> response) {
                if (response.isSuccessful()) {
                    panitiaPengemasanModels = response.body();
                    for (int i = 0; i < panitiaPengemasanModels.size(); i++) {
                        adapter = new AdapterCardPengemasanPanitia(getView().getContext(),panitiaPengemasanModels);
                        LinearLayoutManager manager = new LinearLayoutManager(getView().getContext(), LinearLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(manager);
                        recyclerView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PanitiaPengemasanModel>> call, Throwable t) {
                Toast.makeText(getView().getContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}