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
import com.example.myapplication.model.panitia.PanitiaPelelangModel;
import com.example.myapplication.model.panitia.PanitiaTransferModel;
import com.example.myapplication.panitia.activity.KirimSaldoPanitiaActivity;
import com.example.myapplication.panitia.adapter.AdapterCardPelelangPanitia;
import com.example.myapplication.panitia.adapter.AdapterCardRequestTransferPanitia;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentRequestPembayaranPanitia extends Fragment implements AdapterCardRequestTransferPanitia.ItemClickListener {

    private RecyclerView rvRequest;
    private AdapterCardRequestTransferPanitia adapter;
    private ArrayList<PanitiaTransferModel> panitiaTransferModels;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_request_pembayaran_panitia, container, false);

        rvRequest = view.findViewById(R.id.rv_requset_transfer_panitia);
        rvRequest.setHasFixedSize(true);

        panitiaTransferModels = new ArrayList<>();

        generateItem();
        return view;
    }

    private void generateItem() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ArrayList<PanitiaTransferModel>> requestPanitiaListCall = retrofitAPI.getRequestTransferPanitia();
        requestPanitiaListCall.enqueue(new Callback<ArrayList<PanitiaTransferModel>>() {
            @Override
            public void onResponse(Call<ArrayList<PanitiaTransferModel>> call, Response<ArrayList<PanitiaTransferModel>> response) {
                if (response.isSuccessful()) {
                    panitiaTransferModels = response.body();
                    for (int i = 0; i < panitiaTransferModels.size(); i++) {
                        adapter = new AdapterCardRequestTransferPanitia(getView().getContext(), panitiaTransferModels);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                        rvRequest.setLayoutManager(linearLayoutManager);
                        rvRequest.setAdapter(adapter);
                        adapter.setClickListener(FragmentRequestPembayaranPanitia.this);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<PanitiaTransferModel>> call, Throwable t) {
                Toast.makeText(getView().getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view, int position) {
        final PanitiaTransferModel model = panitiaTransferModels.get(position);
        switch (view.getId()){
            default:
                Intent intent = new Intent(getContext(), KirimSaldoPanitiaActivity.class);
                intent.putExtra("nama",model.getAtasnama());
                intent.putExtra("norek",model.getNorekening());
                intent.putExtra("lelang_id",model.getLelang_id());
                intent.putExtra("pelelang_id",model.getPelelang_id());
                intent.putExtra("nominal",model.getTotal_bayar());
                startActivity(intent);
                break;

        }
    }
}