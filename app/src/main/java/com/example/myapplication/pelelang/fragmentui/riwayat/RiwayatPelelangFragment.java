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
import com.example.myapplication.model.pelelang.RiwayatLelangModel;
import com.example.myapplication.pelelang.adapter.AdapterCardRiwayatLelang;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiwayatPelelangFragment extends Fragment implements AdapterCardRiwayatLelang.ItemClickListener {


    private RecyclerView recyclerView;
    private AdapterCardRiwayatLelang adapter;
    private SearchView searchView;
    private ArrayList<RiwayatLelangModel> riwayatLelangList = new ArrayList<>();
    private ArrayList<RiwayatLelangModel> riwayatLelangCopyList = new ArrayList<>();
    SharedPreferences sharedPreferences;
    String path;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_riwayat_pelelang, container, false);

        recyclerView =view.findViewById(R.id.riwayat);
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
                    riwayatLelangCopyList.clear();
                    for (RiwayatLelangModel model : riwayatLelangList){
                        if (model.getProduk().toLowerCase().contains(query.toLowerCase()) ||
                                model.getHarga_awal().toLowerCase().contains(query.toLowerCase()) ||
                                model.getNamaPeserta().toLowerCase().contains(query.toLowerCase())){
                            riwayatLelangCopyList.add(model);
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
                else {
                    riwayatLelangCopyList.clear();
                    riwayatLelangCopyList.addAll(riwayatLelangList);
                    adapter.notifyDataSetChanged();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                riwayatLelangCopyList.clear();
                riwayatLelangCopyList.addAll(riwayatLelangList);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

        return view;
    }

    private void generateItem() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ArrayList<RiwayatLelangModel>> RiwayatLelangModelListCall = retrofitAPI.getRiwayatLelang(path);
        RiwayatLelangModelListCall.enqueue(new Callback<ArrayList<RiwayatLelangModel>>() {
            @Override
            public void onResponse(Call<ArrayList<RiwayatLelangModel>> call, Response<ArrayList<RiwayatLelangModel>> response) {
                if(response.isSuccessful()){
                    riwayatLelangList.addAll(response.body());
                    riwayatLelangCopyList.addAll(riwayatLelangList);
                    adapter = new AdapterCardRiwayatLelang(getView().getContext(),riwayatLelangCopyList);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(adapter);
                    adapter.setClickListener(RiwayatPelelangFragment.this);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<RiwayatLelangModel>> call, Throwable t) {
                Toast.makeText(getView().getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onClick(View view, int position) {
        final RiwayatLelangModel RiwayatLelangModel = riwayatLelangCopyList.get(position);
        switch (view.getId()){
            default:
//                Intent intent = new Intent(getView().getContext(),RiwayatPelelangFragment.class);
//                intent.putExtra("produk",RiwayatLelangModel.getProduk());
//                intent.putExtra("harga_awal",RiwayatLelangModel.getHarga_awal());
//                intent.putExtra("nama",RiwayatLelangModel.getNamaPeserta());
//                intent.putExtra("jumlah",RiwayatLelangModel.getJumlahProduk());
//                intent.putExtra("nominal_dibayarkan",RiwayatLelangModel.getNominal());
//                intent.putExtra("lelang_id",RiwayatLelangModel.getLelang_id());

//                startActivity(intent);
                break;
        }
    }
}