package com.example.myapplication.pelelang.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.intro.LoginActivity;
import com.example.myapplication.model.pelelang.RiwayatTestimoniModel;
import com.example.myapplication.pelelang.adapter.AdapterTestimoni;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentDetailTestimoniPelelang extends Fragment {

    private RecyclerView recyclerView;
    private AdapterTestimoni adapter;
    SharedPreferences sharedPreferences;
    String path;
    private ArrayList<RiwayatTestimoniModel> TestimoniModels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_testimoni_pelelang, container, false);
        SharedPreferences preferences = this.getActivity().getSharedPreferences(LoginActivity.myLelang, Context.MODE_PRIVATE);
        path = preferences.getString(LoginActivity.TAG_KODE,"");
        recyclerView = view.findViewById(R.id.rv_data_testimoni_detail);

        generateItem();
        return view;
    }

    private void generateItem() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ArrayList<RiwayatTestimoniModel>> testimoniListCall = retrofitAPI.feedback(path);
        testimoniListCall.enqueue(new Callback<ArrayList<RiwayatTestimoniModel>>(){
            @Override
            public void onResponse(Call<ArrayList<RiwayatTestimoniModel>> call, Response<ArrayList<RiwayatTestimoniModel>> response) {
                if (response.isSuccessful()) {
                    TestimoniModels = response.body();
                    for (int i = 0; i < TestimoniModels.size(); i++) {
                        adapter = new AdapterTestimoni(getView().getContext(), TestimoniModels);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getView().getContext()));
                        recyclerView.setAdapter(adapter);
                    }
                }
            }
            @Override
            public void onFailure(Call<ArrayList<RiwayatTestimoniModel>> call, Throwable t) {
                Toast.makeText(getView().getContext(), "Failed to get data", Toast.LENGTH_SHORT).show();
            }
        });
//        List<ItemTestimoniDetail> itemList = new ArrayList<>();
//        for (int i=0;i<30;i++){
//            itemList.add(new ItemTestimoniDetail("Yere",
//                    "5","15:30"));
//        }
//        adapter = new AdapterCardTestimoniDetail(getContext(),itemList);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setAdapter(adapter);
    }
}