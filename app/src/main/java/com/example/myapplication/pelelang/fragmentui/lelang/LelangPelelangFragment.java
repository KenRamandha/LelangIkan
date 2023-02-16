package com.example.myapplication.pelelang.fragmentui.lelang;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.pelelang.GetProductListResponse;
import com.example.myapplication.model.pelelang.KatalogPelelangModel;
import com.example.myapplication.pelelang.InterfacePelelang;
import com.example.myapplication.pelelang.adapter.AdapterCardProdukPelelang;
import com.example.myapplication.pelelang.hostui.DetailProdukActivity;
import com.example.myapplication.pelelang.hostui.TambahLelangActivity;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LelangPelelangFragment extends Fragment {

    private String id;
    private RecyclerView recyclerView;
    private SearchView searchView;
    private Button btnAdd;
    private AdapterCardProdukPelelang adapter;
    private List<KatalogPelelangModel> arrayListProduk = new ArrayList<>();
    private List<KatalogPelelangModel> filterListProduk = new ArrayList<>();
    SharedPreferences sharedPreferences;
    public static final String myLelang = "myLelang";
    public final static String TAG_KODE = "kode";
    private InterfacePelelang interfacePelelang;
    KatalogPelelangModel model;
    private final Gson gson = new Gson();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lelang_pelelang, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnAdd = view.findViewById(R.id.btnAddLelang);
        recyclerView =view.findViewById(R.id.rv_product_terbaru);
        searchView = view.findViewById(R.id.et_query);

        searchView.onActionViewExpanded();
        sharedPreferences = requireContext().getSharedPreferences(myLelang, MODE_PRIVATE);
        id = (sharedPreferences.getString(TAG_KODE, ""));
        getData();
        setOnClick();
    }

    private void setOnClick() {
        btnAdd.setOnClickListener(view1 -> {
            Intent intent = new Intent(getActivity(), TambahLelangActivity.class);
            startActivity(intent);
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.length() > 0){
                    filterListProduk.clear();
                    for (KatalogPelelangModel model : arrayListProduk){
                        if (model.getProduk().toLowerCase().contains(query.toLowerCase()) ||
                                String.valueOf(model.getHarga_awal()).toLowerCase().contains(query.toLowerCase()) ||
                                model.getTgl_mulai().toLowerCase().contains(query.toLowerCase())){
                            filterListProduk.add(model);
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
                else {
                    filterListProduk.clear();
                    filterListProduk.addAll(arrayListProduk);
                    adapter.notifyDataSetChanged();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterListProduk.clear();
                filterListProduk.addAll(arrayListProduk);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    private void getData() {

        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<GetProductListResponse> getProductListResponseCall = retrofitAPI.getAllPelelangProduk(id);

        getProductListResponseCall.enqueue(new Callback<GetProductListResponse>() {
            @Override
            public void onResponse(Call<GetProductListResponse> call, Response<GetProductListResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        if (response.body().getStatus().equalsIgnoreCase("success")){
                            arrayListProduk = response.body().getData();
                            interfacePelelang = position -> {
                                model = arrayListProduk.get(position);
                                Intent intent = new Intent(requireContext(), DetailProdukActivity.class);
                                String jsonString = gson.toJson(model);
                                intent.putExtra("data",jsonString);
                                startActivity(intent);
                            };
                            adapter = new AdapterCardProdukPelelang(getActivity(),arrayListProduk,interfacePelelang);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<GetProductListResponse> call, Throwable t) {
                Log.d("TAG ", "onFailure: "+t);
                Toast.makeText(requireContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }
}