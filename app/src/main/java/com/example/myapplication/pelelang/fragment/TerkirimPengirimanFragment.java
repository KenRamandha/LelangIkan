package com.example.myapplication.pelelang.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.databinding.FragmentTerkirimPengirimanBinding;
import com.example.myapplication.model.pelelang.DataPengiriman;
import com.example.myapplication.model.pelelang.GetListDataPengirimanResponse;
import com.example.myapplication.pelelang.adapter.AdapterDataPengiriman;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TerkirimPengirimanFragment extends Fragment {

    private FragmentTerkirimPengirimanBinding binding;
    private AdapterDataPengiriman adapter;
    private ArrayList<DataPengiriman> sendList = new ArrayList<>();
    SharedPreferences sharedPreferences;
    public static final String myLelang = "myLelang";
    public final static String TAG_KODE = "kode";
    private String id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTerkirimPengirimanBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedPreferences = requireActivity().getSharedPreferences(myLelang, Context.MODE_PRIVATE);
        id = sharedPreferences.getString(TAG_KODE, "");
        getData();
    }

    private void getData() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<GetListDataPengirimanResponse> katalogListCall = retrofitAPI.getListDataPengiriman(id);
        katalogListCall.enqueue(new Callback<GetListDataPengirimanResponse>() {
            @Override
            public void onResponse(@NonNull Call<GetListDataPengirimanResponse> call, @NonNull Response<GetListDataPengirimanResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        for (DataPengiriman dataPengiriman : response.body().getData()) {
                            if (dataPengiriman.getStatusTransaksi().equals("1")){
                                sendList.add(dataPengiriman);
                            }
                        }
                        adapter = new AdapterDataPengiriman(requireContext(),sendList);
                        binding.rvData.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<GetListDataPengirimanResponse> call, Throwable t) {
                Log.i("cek", "onFailure: "+t.getMessage());
            }
        });
    }
}