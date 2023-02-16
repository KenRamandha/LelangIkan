package com.example.myapplication.admin.fragment;

import static com.example.myapplication.admin.adapter.AdapterCardPesertaAdmin.listPeserta;

import android.content.Intent;
import android.os.Bundle;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.admin.adapter.AdapterCardPanitiaAdmin;
import com.example.myapplication.admin.adapter.AdapterCardPesertaAdmin;
import com.example.myapplication.admin.activity.DetailPesertaActivity;
import com.example.myapplication.model.admin.AdminPanitiaModel;
import com.example.myapplication.model.admin.AdminPesertaModel;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentPesertaAdmin extends Fragment implements AdapterCardPesertaAdmin.ItemClickListener {

    private RecyclerView recyclerView;
    private AdapterCardPesertaAdmin adapter;
    private ArrayList<AdminPesertaModel> adminPesertaModelArrayList;
    private TextInputEditText tisearch;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_peserta_admin, container, false);

        recyclerView = view.findViewById(R.id.rv_peserta_admin);
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
        Call<ArrayList<AdminPesertaModel>> pesertaAdminListCall = retrofitAPI.getAllPesertaAdmin();
        pesertaAdminListCall.enqueue(new Callback<ArrayList<AdminPesertaModel>>() {
            @Override
            public void onResponse(Call<ArrayList<AdminPesertaModel>> call, Response<ArrayList<AdminPesertaModel>> response) {
                if (response.isSuccessful()) {
                    adminPesertaModelArrayList = new ArrayList<>(response.body());
                    adapter = new AdapterCardPesertaAdmin(getActivity(), adminPesertaModelArrayList);
                    //
                    String tes = tisearch.getText().toString();
                    if (!tes.equals(""))  adapter.getFilter().filter(tisearch.getText().toString());
                    //
                    recyclerView.setAdapter(adapter);
                    adapter.setClickListener(FragmentPesertaAdmin.this);
                }
            }



            @Override
            public void onFailure(Call<ArrayList<AdminPesertaModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onClick(View view, int position) {
        final AdminPesertaModel pesertaModel = listPeserta.get(position);
        switch (view.getId()){
            default:
                Intent intent = new Intent(getActivity(), DetailPesertaActivity.class);
                intent.putExtra("nama",pesertaModel.getNama());
                intent.putExtra("jeniskel",pesertaModel.getJeniskel());
                intent.putExtra("provinsi",pesertaModel.getProvinsi());
                intent.putExtra("kota",pesertaModel.getKota());
                intent.putExtra("kecamatan",pesertaModel.getKecamatan());
                intent.putExtra("alamat",pesertaModel.getAlamat());
                intent.putExtra("telp",pesertaModel.getTelp());
                intent.putExtra("email",pesertaModel.getEmail());
                intent.putExtra("npwp",pesertaModel.getNpwp());
                intent.putExtra("nik",pesertaModel.getNik());
                intent.putExtra("filenpwp",pesertaModel.getFilenpwp());
                intent.putExtra("filektp", pesertaModel.getFilektp());
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