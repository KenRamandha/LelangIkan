package com.example.myapplication.panitia.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.admin.activity.DetailPesertaActivity;
import com.example.myapplication.admin.fragment.FragmentPesertaAdmin;
import com.example.myapplication.model.admin.AdminPesertaModel;
import com.example.myapplication.model.panitia.PanitiaPesertaModel;
import com.example.myapplication.model.panitia.ProdukPanitiaModel;
import com.example.myapplication.panitia.activity.DetailPesertaPanitiaActivity;
import com.example.myapplication.panitia.adapter.AdapterCardPesertaPanitia;
import com.example.myapplication.panitia.adapter.AdapterCardVerifikasiPanitia;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentStatusPesertaPanitia extends Fragment implements AdapterCardPesertaPanitia.ItemClickListener {

    private RecyclerView recyclerView;
    private AdapterCardPesertaPanitia adapter;
    private ArrayList<PanitiaPesertaModel> panitiaPesertaModels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_status_peserta_panitia, container, false);
        recyclerView = view.findViewById(R.id.rv_peserta_panitia);
        recyclerView.setHasFixedSize(true);

        panitiaPesertaModels = new ArrayList<>();

        generateItem();
        return view;
    }

    private void generateItem() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ArrayList<PanitiaPesertaModel>> pesertaPanitiaListCall = retrofitAPI.getAllPesertaPanitia();
        pesertaPanitiaListCall.enqueue(new Callback<ArrayList<PanitiaPesertaModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PanitiaPesertaModel>> call, Response<ArrayList<PanitiaPesertaModel>> response) {
                if (response.isSuccessful()) {
//                    Toast.makeText(getView().getContext(), "bebas", Toast.LENGTH_SHORT).show();
                    panitiaPesertaModels = response.body();
                    for (int i = 0; i < panitiaPesertaModels.size(); i++) {
                        adapter = new AdapterCardPesertaPanitia(getView().getContext(), panitiaPesertaModels);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(adapter);
                        adapter.setClickListener(FragmentStatusPesertaPanitia.this);
                    }
                }
            }
            @Override
            public void onFailure(Call<ArrayList<PanitiaPesertaModel>> call, Throwable t) {
                Toast.makeText(getView().getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view, int position) {
        final PanitiaPesertaModel pesertaModel = panitiaPesertaModels.get(position);
        switch (view.getId()){
            default:
                Intent intent = new Intent(getView().getContext(), DetailPesertaPanitiaActivity.class);
                intent.putExtra("peserta_id",pesertaModel.getPeserta_id());
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
}