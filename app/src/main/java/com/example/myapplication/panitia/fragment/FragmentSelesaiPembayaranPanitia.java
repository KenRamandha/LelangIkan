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
import com.example.myapplication.model.panitia.PanitiaRiwayatTransferModel;
import com.example.myapplication.model.panitia.PanitiaTransferModel;
import com.example.myapplication.panitia.adapter.AdapterCardRequestTransferPanitia;
import com.example.myapplication.panitia.adapter.AdapterCardRiwayatTransferPanitia;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentSelesaiPembayaranPanitia extends Fragment {

    private RecyclerView recyclerView;
    private AdapterCardRiwayatTransferPanitia adapter;
    private ArrayList<PanitiaRiwayatTransferModel> panitiaTransferModels;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selesai_pembayaran_panitia, container, false);

        recyclerView = view.findViewById(R.id.rv_riwayat_transfer_panitia);
        recyclerView.setHasFixedSize(true);

        panitiaTransferModels = new ArrayList<>();

        generateItem();



        return view;
    }

    private void generateItem() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ArrayList<PanitiaRiwayatTransferModel>> transferPanitiaListCall = retrofitAPI.getRiwayatTransferPanitia();
        transferPanitiaListCall.enqueue(new Callback<ArrayList<PanitiaRiwayatTransferModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PanitiaRiwayatTransferModel>> call, Response<ArrayList<PanitiaRiwayatTransferModel>> response) {
                if (response.isSuccessful()) {
                    panitiaTransferModels = response.body();
                    for (int i = 0; i < panitiaTransferModels.size(); i++) {
                        adapter = new AdapterCardRiwayatTransferPanitia(getView().getContext(), panitiaTransferModels);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PanitiaRiwayatTransferModel>> call, Throwable t) {
                Toast.makeText(getView().getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}