package com.example.myapplication.admin.fragment;


import static com.example.myapplication.admin.adapter.AdapterCardPelelangAdmin.listPelelang;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import com.example.myapplication.admin.adapter.AdapterCardPesertaAdmin;
import com.example.myapplication.model.admin.AdminPelelangModel;
import com.example.myapplication.model.admin.AdminPesertaModel;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentPelelangAdmin extends Fragment implements AdapterCardPelelangAdmin.ItemClickListener {

    private RecyclerView recyclerView;
    private AdapterCardPelelangAdmin adapter;
    private ArrayList<AdminPelelangModel> adminPelelangModels;
    private TextInputEditText tisearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pelelang_admin, container, false);

        recyclerView = view.findViewById(R.id.rv_pelelang_admin);
        tisearch = view.findViewById(R.id.et_query);

        tisearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                adapter.getFilter().filter(editable);
            }
        });

        generateItem();

        return view;
    }


    private void generateItem() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ArrayList<AdminPelelangModel>> pelelangAdminListCall = retrofitAPI.getAllPelelangAdmin();
        pelelangAdminListCall.enqueue(new Callback<ArrayList<AdminPelelangModel>>() {
            @Override
            public void onResponse(Call<ArrayList<AdminPelelangModel>> call, Response<ArrayList<AdminPelelangModel>> response) {
                if (response.isSuccessful()) {
                    adminPelelangModels = new ArrayList<>(response.body());
                    adapter = new AdapterCardPelelangAdmin(getActivity(), adminPelelangModels);
                    //
                    String tes = tisearch.getText().toString();
                    if (!tes.equals(""))  adapter.getFilter().filter(tisearch.getText().toString());
                    //
                    recyclerView.setAdapter(adapter);
                    adapter.setClickListener(FragmentPelelangAdmin.this);
                }
            }



            @Override
            public void onFailure(Call<ArrayList<AdminPelelangModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onClick(View view, int position) {
        final AdminPelelangModel pelelangModel = listPelelang.get(position);
        switch (view.getId()){
            default:
                Intent intent = new Intent(getActivity(), DetailPelelangActivity.class);
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

    @Override
    public void onResume() {
        super.onResume();
        generateItem();
    }
}