package com.example.myapplication.peserta.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.model.peserta.BidModel;
import com.example.myapplication.peserta.adapter.AdapterCardRiwayatDetail;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentDetailRiwayat extends Fragment {

    private RecyclerView recyclerView;
    private AdapterCardRiwayatDetail adapter;

    private ArrayList<BidModel> bidModels;

    SharedPreferences detail;
    String lelangId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_riwayat, container, false);

        detail = getActivity().getSharedPreferences("detailLelang", Context.MODE_PRIVATE);
        lelangId = detail.getString("lelang_id","");



        recyclerView = view.findViewById(R.id.rv_data_riwayat_detail_peserta);

        generateItem();
        return view;
    }

    private void generateItem() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ArrayList<BidModel>> pemenangListCall = retrofitAPI.getBid(lelangId);
        pemenangListCall.enqueue(new Callback<ArrayList<BidModel>>(){
            @Override
            public void onResponse(Call<ArrayList<BidModel>> call, Response<ArrayList<BidModel>> response) {
                if (response.isSuccessful()) {
                    bidModels = response.body();
                    for (int i = 0; i < bidModels.size(); i++) {
                        adapter = new AdapterCardRiwayatDetail(getView().getContext(), bidModels);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getView().getContext()));
                        recyclerView.setAdapter(adapter);
                    }
                }
            }
            @Override
            public void onFailure(Call<ArrayList<BidModel>> call, Throwable t) {
                Toast.makeText(getView().getContext(), "Failed to get data", Toast.LENGTH_SHORT).show();
            }
        });
//        List<ItemRiwayatDetail> itemList = new ArrayList<>();
//        for (int i=0;i<30;i++){
//            itemList.add(new ItemRiwayatDetail("Yere",
//                    "300.000","15:30"));
//        }
//        adapter = new AdapterCardRiwayatDetail(getContext(),itemList);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        recyclerView.setAdapter(adapter);
    }
}