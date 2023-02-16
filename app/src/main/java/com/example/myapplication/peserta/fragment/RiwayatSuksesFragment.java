package com.example.myapplication.peserta.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.intro.LoginActivity;
import com.example.myapplication.model.peserta.RiwayatLelangPesertaModel;
import com.example.myapplication.peserta.adapter.AdapterCardRiwayatSukses;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.text.DecimalFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiwayatSuksesFragment extends Fragment implements AdapterCardRiwayatSukses.ItemClickListener  {
    private RecyclerView recyclerView;
    private AdapterCardRiwayatSukses adapter;
    private ArrayList<RiwayatLelangPesertaModel> riwayatLelangPesertaModelArrayList;

    String path;
    Dialog dialog;

    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_riwayat_sukses, container, false);

        recyclerView = view.findViewById(R.id.rv_riwayat_sukses);
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
        Call<ArrayList<RiwayatLelangPesertaModel>> riwayatListCall = retrofitAPI.getRiwayatlelangSukses(path);
        riwayatListCall.enqueue(new Callback<ArrayList<RiwayatLelangPesertaModel>>(){
            @Override
            public void onResponse(Call<ArrayList<RiwayatLelangPesertaModel>> call, Response<ArrayList<RiwayatLelangPesertaModel>> response) {
                if (response.isSuccessful()) {
                    riwayatLelangPesertaModelArrayList = response.body();
                    for (int i = 0; i < riwayatLelangPesertaModelArrayList.size(); i++) {
                        adapter = new AdapterCardRiwayatSukses(getActivity(), riwayatLelangPesertaModelArrayList);
                        recyclerView.setAdapter(adapter);
                        adapter.setClickListener(RiwayatSuksesFragment.this);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<RiwayatLelangPesertaModel>> call, Throwable t) {
                Toast.makeText(getView().getContext(), "Fail to get data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openDetailDialog(String nama, String alamat, String berat, String produk, String total, String telp) {
        dialog.setContentView(R.layout.dialog_detail_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button btnKembaliDetail=dialog.findViewById(R.id.btn_kembali_ddetail);

        TextView tvNama,tvAlamat,tvBerat,tvProduk,tvTotal, tvTelp;
//        String nama,alamat,berat,produk,total,telp;

        tvNama = dialog.findViewById(R.id.tv_nama_riwayat_sukses);
        tvAlamat = dialog.findViewById(R.id.tv_alamat_riwayat_sukses);
        tvBerat = dialog.findViewById(R.id.tv_berat_riwayat_sukses);
        tvProduk = dialog.findViewById(R.id.tv_produk_riwayat_sukses);
        tvTotal = dialog.findViewById(R.id.tv_harga_riwayat_sukses);
        tvTelp = dialog.findViewById(R.id.tv_telp_riwayat_sukses);

        DecimalFormat decimalFormat = new DecimalFormat("#,##0");

        tvNama.setText(nama);
        tvAlamat.setText(alamat);
        tvBerat.setText(berat+"Kg");
        tvProduk.setText(produk);
        tvTotal.setText("Rp."+decimalFormat.format(Integer.parseInt(total)));
        tvTelp.setText(telp);

//        "Rp. "+decimalFormat.format(Integer.parseInt(modal.getNominal_dibayarkan()))

        btnKembaliDetail.setOnClickListener(view -> dialog.dismiss());
        dialog.show();
    }

    @Override
    public void onItemClick(RiwayatLelangPesertaModel riwayatLelangPesertaModel, View view, int position) {
        switch (getId()) {
            default:
                String nama, alamat, berat, produk, total, telp;
                nama = riwayatLelangPesertaModel.getNama_peserta();
                alamat = riwayatLelangPesertaModel.getAlamat_kirim();
                berat = riwayatLelangPesertaModel.getJumlah();
                produk = riwayatLelangPesertaModel.getProduk();
                total = riwayatLelangPesertaModel.getTotal_bayar();
                telp = riwayatLelangPesertaModel.getTelp();
                openDetailDialog(nama, alamat, berat, produk, total, telp);
                break;
        }
    }
}