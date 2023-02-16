package com.example.myapplication.panitia.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.admin.activity.DetailPelelangActivity;
import com.example.myapplication.admin.adapter.AdapterCardPelelangAdmin;
import com.example.myapplication.admin.fragment.FragmentPelelangAdmin;
import com.example.myapplication.model.admin.AdminPelelangModel;
import com.example.myapplication.model.panitia.PanitiaPelelangModel;
import com.example.myapplication.panitia.activity.DetailPelelangPanitiaActivity;
import com.example.myapplication.panitia.adapter.AdapterCardPelelangPanitia;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentStatusPelelangPanitia extends Fragment implements AdapterCardPelelangPanitia.ItemClickListener {

    private RecyclerView recyclerView;
    private AdapterCardPelelangPanitia adapter;
    private ArrayList<PanitiaPelelangModel> panitiaPelelangModels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_status_pelelang_panitia, container, false);

        recyclerView = view.findViewById(R.id.rv_pelelang_panitia);
        recyclerView.setHasFixedSize(true);

        panitiaPelelangModels = new ArrayList<>();

        generateItem();

        return view;
    }

    private void generateItem() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ArrayList<PanitiaPelelangModel>> pelelangPanitiaListCall = retrofitAPI.getAllPelelangPanitia();
        pelelangPanitiaListCall.enqueue(new Callback<ArrayList<PanitiaPelelangModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PanitiaPelelangModel>> call, Response<ArrayList<PanitiaPelelangModel>> response) {
                if (response.isSuccessful()) {
                    panitiaPelelangModels = response.body();
                    for (int i = 0; i < panitiaPelelangModels.size(); i++) {
                        adapter = new AdapterCardPelelangPanitia(getView().getContext(), panitiaPelelangModels);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(adapter);
                        adapter.setClickListener(FragmentStatusPelelangPanitia.this);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PanitiaPelelangModel>> call, Throwable t) {
                Toast.makeText(getView().getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view, int position) {
        final PanitiaPelelangModel pelelangModel = panitiaPelelangModels.get(position);
        switch (view.getId()){
            default:
                Intent intent = new Intent(getView().getContext(), DetailPelelangPanitiaActivity.class);
                intent.putExtra("pelelang_id",pelelangModel.getPelelang_id());
                intent.putExtra("nama",pelelangModel.getNama());
                intent.putExtra("jenis",pelelangModel.getJenis());
                intent.putExtra("provinsi",pelelangModel.getProvinsi());
                intent.putExtra("kota",pelelangModel.getKota());
                intent.putExtra("kecamatan",pelelangModel.getKecamatan());
                intent.putExtra("alamat",pelelangModel.getAlamat());
                intent.putExtra("telp",pelelangModel.getTelp());
                intent.putExtra("email",pelelangModel.getEmail());
                intent.putExtra("npwp",pelelangModel.getNpwp());
                intent.putExtra("nik",pelelangModel.getNik());
                intent.putExtra("norek",pelelangModel.getNorekening());
                intent.putExtra("bank",pelelangModel.getBank());
                intent.putExtra("deskripsi",pelelangModel.getDeskripsi());
                intent.putExtra("filenpwp",pelelangModel.getFilenpwp());
                intent.putExtra("filektp", pelelangModel.getFilektp());
                intent.putExtra("filesiup", pelelangModel.getFileSIUP());
                startActivity(intent);
                break;
        }
    }
}