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
import com.example.myapplication.model.panitia.PanitiaPengirimanModel;
import com.example.myapplication.panitia.adapter.AdapterCardPengemasanPanitia;
import com.example.myapplication.panitia.adapter.AdapterCardPengirimanPanitia;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentPengirimanPanitia extends Fragment {

    private RecyclerView recyclerView;
    private AdapterCardPengirimanPanitia adapter;
    private ArrayList<PanitiaPengirimanModel> panitiaPengirimanModels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pengiriman_panitia, container, false);

        recyclerView = view.findViewById(R.id.rv_pengiriman_panitia);
        recyclerView.setHasFixedSize(true);
        panitiaPengirimanModels = new ArrayList<>();

        generateItem();

        return view;
    }

    private void generateItem() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ArrayList<PanitiaPengirimanModel>> pengirimanPanitiaListCall = retrofitAPI.getAllPengirimanPanitia();
        pengirimanPanitiaListCall.enqueue(new Callback<ArrayList<PanitiaPengirimanModel>>(){
            @Override
            public void onResponse(Call<ArrayList<PanitiaPengirimanModel>> call, Response<ArrayList<PanitiaPengirimanModel>> response) {
                if (response.isSuccessful()) {
                    panitiaPengirimanModels = response.body();
                    for (int i = 0; i < panitiaPengirimanModels.size(); i++) {
                        adapter = new AdapterCardPengirimanPanitia(getView().getContext(),panitiaPengirimanModels);
                        LinearLayoutManager manager = new LinearLayoutManager(getView().getContext(), LinearLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(manager);
                        recyclerView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PanitiaPengirimanModel>> call, Throwable t) {
                Toast.makeText(getView().getContext(),t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}