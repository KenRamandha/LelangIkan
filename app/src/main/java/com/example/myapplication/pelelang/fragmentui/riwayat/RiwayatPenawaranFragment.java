package com.example.myapplication.pelelang.fragmentui.riwayat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.intro.LoginActivity;
import com.example.myapplication.model.pelelang.RiwayatPenawaranModel;
import com.example.myapplication.pelelang.adapter.AdapterCardRiwayatPenawaran;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiwayatPenawaranFragment extends Fragment implements AdapterCardRiwayatPenawaran.ItemClickListener {
    private RecyclerView recyclerView;
    private AdapterCardRiwayatPenawaran adapter;
    private SearchView searchView;
    private ArrayList<RiwayatPenawaranModel> riwayatPenawaranList = new ArrayList<>();
    private ArrayList<RiwayatPenawaranModel> riwayatPenawaranCopyList = new ArrayList<>();
    SharedPreferences sharedPreferences;
    String path;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_penawaran_pelelang, container, false);
        recyclerView =view.findViewById(R.id.riwayatpenawaran); 
        searchView = view.findViewById(R.id.et_query);

        searchView.onActionViewExpanded();
        recyclerView.setHasFixedSize(true);
        SharedPreferences preferences = this.getActivity().getSharedPreferences(LoginActivity.myLelang, Context.MODE_PRIVATE);
        path = preferences.getString(LoginActivity.TAG_KODE,"");
        
        generateItem();
        
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.length() > 0){
                    riwayatPenawaranCopyList.clear();
                    for (RiwayatPenawaranModel model : riwayatPenawaranList){
                        if (model.getProduk().toLowerCase().contains(query.toLowerCase()) ||
                                model.getNama().toLowerCase().contains(query.toLowerCase()) ||
                                model.getHarga_tawar().toLowerCase().contains(query.toLowerCase())){
                            riwayatPenawaranCopyList.add(model);
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
                else {
                    riwayatPenawaranCopyList.clear();
                    riwayatPenawaranCopyList.addAll(riwayatPenawaranList);
                    adapter.notifyDataSetChanged();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                riwayatPenawaranCopyList.clear();
                riwayatPenawaranCopyList.addAll(riwayatPenawaranList);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        return view;
    }

    private void generateItem() {
//        sharedPreferences = getSharedPreferences(LoginActivity.myLelang, Context.MODE_PRIVATE);
//        path = sharedPreferences.getString(LoginActivity.TAG_KODE,"");
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ArrayList<RiwayatPenawaranModel>> RiwayatLelangModelListCall = retrofitAPI.RiwayatTawar(path);
        RiwayatLelangModelListCall.enqueue(new Callback<ArrayList<RiwayatPenawaranModel>>() {
            @Override
            public void onResponse(Call<ArrayList<RiwayatPenawaranModel>> call, Response<ArrayList<RiwayatPenawaranModel>> response) {
                if(response.isSuccessful()){
                    riwayatPenawaranList.addAll(response.body());
                    riwayatPenawaranCopyList.addAll(riwayatPenawaranList);
                    adapter = new AdapterCardRiwayatPenawaran(getView().getContext(),riwayatPenawaranCopyList);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(adapter);
                    adapter.setClickListener(RiwayatPenawaranFragment.this);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<RiwayatPenawaranModel>> call, Throwable t) {
                Toast.makeText(getView().getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onClick(View view, int position) {
        final RiwayatPenawaranModel RiwayatPenawaranModel = riwayatPenawaranCopyList.get(position);
        switch (view.getId()){
            default:
//                Intent intent = new Intent(getView().getContext(),RiwayatPenawaranFragment.class);
//                intent.putExtra("produk",RiwayatPenawaranModel.getProduk());
//                intent.putExtra("harga_tawar",RiwayatPenawaranModel.getHarga_tawar());
//                intent.putExtra("nama",RiwayatPenawaranModel.getNama());
//                intent.putExtra("jumlah",RiwayatPenawaranModel.getJumlah());
//
//                startActivity(intent);
                break;
        }
    }
}
