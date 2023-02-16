package com.example.myapplication.peserta.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.model.admin.PemenangModel;
import com.example.myapplication.model.peserta.TestimoniDetailModel;
import com.example.myapplication.peserta.activity.DetailLelangActivity;
import com.example.myapplication.peserta.adapter.AdapterCardTestimoniDetail;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentDetailTestimoni extends Fragment {

    private RecyclerView recyclerView;
    private AdapterCardTestimoniDetail adapter;

    private ArrayList<TestimoniDetailModel> testimoniDetailModels;

    private SharedPreferences detail;
    private String lelangId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_testimoni, container, false);

        detail = getActivity().getSharedPreferences(DetailLelangActivity.detailLelang, Context.MODE_PRIVATE);
        lelangId = detail.getString( DetailLelangActivity.TAG_ID, "");

        Log.d("ID", lelangId);

        recyclerView = view.findViewById(R.id.rv_data_testimoni_detail);

        generateItem();
        return view;
    }

    private void generateItem() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ArrayList<TestimoniDetailModel>> pemenangListCall = retrofitAPI.getTestimoniDetail(lelangId);
        pemenangListCall.enqueue(new Callback<ArrayList<TestimoniDetailModel>>(){
            @Override
            public void onResponse(Call<ArrayList<TestimoniDetailModel>> call, Response<ArrayList<TestimoniDetailModel>> response) {
                if (response.isSuccessful()) {
                    testimoniDetailModels = response.body();
                    for (int i = 0; i < testimoniDetailModels.size(); i++) {
                        adapter = new AdapterCardTestimoniDetail(getView().getContext(), testimoniDetailModels);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getView().getContext()));
                        recyclerView.setAdapter(adapter);
                    }
                }
            }
            @Override
            public void onFailure(Call<ArrayList<TestimoniDetailModel>> call, Throwable t) {
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