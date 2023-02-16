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
import com.example.myapplication.admin.activity.DetailPembayaranDeposit;
import com.example.myapplication.model.panitia.PanitiaDepositPesertaModel;
import com.example.myapplication.model.panitia.PanitiaPesertaModel;
import com.example.myapplication.panitia.activity.DetailDepositPesertaPanitiaActivity;
import com.example.myapplication.panitia.activity.DetailPesertaPanitiaActivity;
import com.example.myapplication.panitia.adapter.AdapterCardDepositPesertaPanitia;
import com.example.myapplication.panitia.adapter.AdapterCardPesertaPanitia;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentDepositPesertaPanitia extends Fragment implements AdapterCardDepositPesertaPanitia.ItemClickListener {

    private RecyclerView recyclerView;
    private AdapterCardDepositPesertaPanitia adapter;
    private ArrayList<PanitiaDepositPesertaModel> panitiaPesertaModels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_panitia_deposit_peserta, container, false);
        recyclerView = view.findViewById(R.id.rv_deposit_peserta_panitia);
        recyclerView.setHasFixedSize(true);

        panitiaPesertaModels = new ArrayList<>();

        generateItem();
        return view;
    }

    private void generateItem() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ArrayList<PanitiaDepositPesertaModel>> pesertaPanitiaListCall = retrofitAPI.getDepositPanitia();
        pesertaPanitiaListCall.enqueue(new Callback<ArrayList<PanitiaDepositPesertaModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PanitiaDepositPesertaModel>> call, Response<ArrayList<PanitiaDepositPesertaModel>> response) {
                if (response.isSuccessful()) {
                    panitiaPesertaModels = response.body();
                    for (int i = 0; i < panitiaPesertaModels.size(); i++) {
                        adapter = new AdapterCardDepositPesertaPanitia(getView().getContext(), panitiaPesertaModels);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(adapter);
                        adapter.setClickListener(FragmentDepositPesertaPanitia.this);
                    }
                }
            }
            @Override
            public void onFailure(Call<ArrayList<PanitiaDepositPesertaModel>> call, Throwable t) {
                Toast.makeText(getView().getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view, int position) {
        final PanitiaDepositPesertaModel pesertaModel = panitiaPesertaModels.get(position);
        switch (view.getId()){
            default:
                Intent intent = new Intent(getView().getContext(), DetailDepositPesertaPanitiaActivity.class);
                intent.putExtra("peserta_id",pesertaModel.getPeserta_id());
                intent.putExtra("nama",pesertaModel.getNama());
                intent.putExtra("deposit",pesertaModel.getDeposit());
                intent.putExtra("nominal",pesertaModel.getNominal_deposit());
                intent.putExtra("bukti",pesertaModel.getBukti_pembayaran());
                intent.putExtra("keterangan",pesertaModel.getKeterangan());
                intent.putExtra("status",pesertaModel.getStatus());
                startActivity(intent);
                break;
        }
    }
}