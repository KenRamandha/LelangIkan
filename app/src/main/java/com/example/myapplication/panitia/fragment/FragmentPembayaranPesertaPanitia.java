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
import com.example.myapplication.model.panitia.PanitiaDepositPesertaModel;
import com.example.myapplication.model.panitia.PanitiaPembayaranPesertaModel;
import com.example.myapplication.model.panitia.PanitiaPesertaModel;
import com.example.myapplication.panitia.activity.DetailDepositPesertaPanitiaActivity;
import com.example.myapplication.panitia.activity.DetailPembayaranPesertaPanitiaActivity;
import com.example.myapplication.panitia.activity.DetailPesertaPanitiaActivity;
import com.example.myapplication.panitia.adapter.AdapterCardDepositPesertaPanitia;
import com.example.myapplication.panitia.adapter.AdapterCardPembayaranPesertaPanitia;
import com.example.myapplication.panitia.adapter.AdapterCardPesertaPanitia;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentPembayaranPesertaPanitia extends Fragment implements AdapterCardPembayaranPesertaPanitia.ItemClickListener {

    private RecyclerView recyclerView;
    private AdapterCardPembayaranPesertaPanitia adapter;
    private ArrayList<PanitiaPembayaranPesertaModel> panitiaPesertaModels;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_panitia_pembayaran_peserta, container, false);
        recyclerView = view.findViewById(R.id.rv_pembayaran_peserta_panitia);
        recyclerView.setHasFixedSize(true);

        panitiaPesertaModels = new ArrayList<>();

        generateItem();
        return view;
    }

    private void generateItem() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ArrayList<PanitiaPembayaranPesertaModel>> pesertaPanitiaListCall = retrofitAPI.getPembayaranPanitia();
        pesertaPanitiaListCall.enqueue(new Callback<ArrayList<PanitiaPembayaranPesertaModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PanitiaPembayaranPesertaModel>> call, Response<ArrayList<PanitiaPembayaranPesertaModel>> response) {
                if (response.isSuccessful()) {
                    panitiaPesertaModels = response.body();
                    for (int i = 0; i < panitiaPesertaModels.size(); i++) {
                        adapter = new AdapterCardPembayaranPesertaPanitia(getView().getContext(), panitiaPesertaModels);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(adapter);
                        adapter.setClickListener(FragmentPembayaranPesertaPanitia.this);
                    }
                }
            }
            @Override
            public void onFailure(Call<ArrayList<PanitiaPembayaranPesertaModel>> call, Throwable t) {
                Toast.makeText(getView().getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view, int position) {
        final PanitiaPembayaranPesertaModel pesertaModel = panitiaPesertaModels.get(position);
        switch (view.getId()){
            default:
                Intent intent = new Intent(getView().getContext(), DetailPembayaranPesertaPanitiaActivity.class);
                intent.putExtra("lelang_id",pesertaModel.getLelang_id());
                intent.putExtra("tgl_pembayaran",pesertaModel.getTgl_pembayaran());
                intent.putExtra("nominal_dibayarkan",pesertaModel.getNominal_dibayarkan());
                intent.putExtra("bukti_pembayaran",pesertaModel.getBukti_pembayaran());
                intent.putExtra("peserta_id",pesertaModel.getPeserta_id());
                intent.putExtra("nama",pesertaModel.getNama());
                intent.putExtra("produk",pesertaModel.getProduk());
                intent.putExtra("status",pesertaModel.getStatus());
                intent.putExtra("bukti",pesertaModel.getBukti_bayar());
                intent.putExtra("bukti1",pesertaModel.getBukti_text());
                startActivity(intent);
                break;
        }
    }
}