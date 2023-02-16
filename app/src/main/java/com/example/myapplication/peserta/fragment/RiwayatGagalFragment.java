package com.example.myapplication.peserta.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.intro.LoginActivity;
import com.example.myapplication.model.peserta.RiwayatLelangPesertaModel;
import com.example.myapplication.peserta.adapter.AdapterCardRiwayatGagal;
import com.example.myapplication.peserta.adapter.AdapterCardRiwayatSukses;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RiwayatGagalFragment extends Fragment implements AdapterCardRiwayatGagal.ItemClickListener {
    private RecyclerView recyclerView;
    private AdapterCardRiwayatGagal adapter;

    private ArrayList<RiwayatLelangPesertaModel> riwayatLelangPesertaModelArrayList;

    String path;
    Dialog dialog;

    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_riwayat_gagal, container, false);

        recyclerView = view.findViewById(R.id.rv_riwayat_gagal);
        recyclerView.setHasFixedSize(true);

        sharedPreferences = getContext().getSharedPreferences(LoginActivity.myLelang, Context.MODE_PRIVATE);
        path = sharedPreferences.getString(LoginActivity.TAG_KODE,"");

        riwayatLelangPesertaModelArrayList = new ArrayList<>();

        dialog = new Dialog(getContext());

        generateItem();

        return view;
    }
    private void generateItem() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ArrayList<RiwayatLelangPesertaModel>> riwayatListCall = retrofitAPI.getRiwayatlelangGagal(path);
        riwayatListCall.enqueue(new Callback<ArrayList<RiwayatLelangPesertaModel>>(){
            @Override
            public void onResponse(Call<ArrayList<RiwayatLelangPesertaModel>> call, Response<ArrayList<RiwayatLelangPesertaModel>> response) {
                if (response.isSuccessful()) {
                    riwayatLelangPesertaModelArrayList = response.body();
                    for (int i = 0; i < riwayatLelangPesertaModelArrayList.size(); i++) {
                        adapter = new AdapterCardRiwayatGagal(getActivity(), riwayatLelangPesertaModelArrayList);
                        recyclerView.setAdapter(adapter);
                        adapter.setClickListener(RiwayatGagalFragment.this);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<RiwayatLelangPesertaModel>> call, Throwable t) {
                Toast.makeText(getView().getContext(), "Fail to get data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view, int position) {
        final RiwayatLelangPesertaModel riwayatLelangPesertaModel = riwayatLelangPesertaModelArrayList.get(position);
        switch (view.getId()){
            default:
                openDetailDialog();
        }
    }

    private void openDetailDialog() {
        dialog.setContentView(R.layout.dialog_alert_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button btnKembaliDetail=dialog.findViewById(R.id.btn_kembali_dalert);
        btnKembaliDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}