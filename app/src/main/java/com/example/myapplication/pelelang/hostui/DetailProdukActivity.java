package com.example.myapplication.pelelang.hostui;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityDetailProdukBinding;
import com.example.myapplication.model.pelelang.DeleteProdukResponse;
import com.example.myapplication.model.pelelang.KatalogPelelangModel;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;
import com.example.myapplication.util.ServerAPI;
import com.google.gson.Gson;

import java.io.File;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailProdukActivity extends AppCompatActivity {

    private ActivityDetailProdukBinding binding;


    KatalogPelelangModel model;
    Uri Image_Uri;
    String image1, image2, image3, image4,produk,deskripsiProduk,tglMulai,tglSelesai,keterangan;
    String jsonString;
    Double hargaAwal,hargaMinimalDiterima,incrementalValue,hargaBeliSekarang;
    Integer jumlah;
    File photo;
    MultipartBody.Part foto;
    private static final Gson gson = new Gson();
    private String pelelangId;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailProdukBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        jsonString = getIntent().getStringExtra("data");
        model = gson.fromJson(jsonString, KatalogPelelangModel.class);
        dialog = new Dialog(this);

        if (model != null){
            setUpData();
        }
    }

    private void setUpData() {
        binding.btnBackBuatLelang.setOnClickListener(view -> onBackPressed());
        Glide.with(this)
                .load(ServerAPI.URL_GET_GAMBAR+"produk/"+model.getImage1())
                .into(binding.ivFotoLelang);
        Glide.with(this)
                .load(ServerAPI.URL_GET_GAMBAR+"produk/"+model.getImage2())
                .into(binding.ivFotoLelang2);
        Glide.with(this)
                .load(ServerAPI.URL_GET_GAMBAR+"produk/"+model.getImage3())
                .into(binding.ivFotoLelang3);
        Glide.with(this)
                .load(ServerAPI.URL_GET_GAMBAR+"produk/"+model.getImage4())
                .into(binding.ivFotoLelang4);
        binding.NamaProdukPelelang.setText(model.getProduk());
        binding.DeskProduk.setText(model.getDeskripsi_produk());
        binding.JumlahBeratIkan.setText(String.valueOf(model.getJumlah()));
        binding.MulaiPenawaran.setText(String.valueOf(model.getHarga_awal()));
        binding.hrgaMinimAccept.setText(String.valueOf(model.getHarga_minimal_diterima()));
        binding.hrgaIncremental.setText(String.valueOf(model.getIncremental_value()));
        binding.BeliSkrng.setText(model.getHarga_beli_sekarang());
        binding.TglMulaiLelang.setText(model.getTgl_mulai());
        binding.TglEndLelang.setText(model.getTgl_selesai());
        binding.KetLelang.setText(model.getKeterangan());

        binding.editLelang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditProdukActivity.class);
                String jsonString = gson.toJson(model);
                intent.putExtra("data",jsonString);
                startActivity(intent);
            }
        });



        binding.deleteLelang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDeleteDialog();
            }
        });
    }

    private void openDeleteDialog() {
        dialog.setContentView(R.layout.dialog_delete_produk);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btnKembali = dialog.findViewById(R.id.btnDelete);

        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData();
            }
        });
        dialog.show();
    }

    private void deleteData() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<DeleteProdukResponse> deleteProduk = retrofitAPI.deleteProduk(model.getLelang_id());
        deleteProduk.enqueue(new Callback<DeleteProdukResponse>() {
            @Override
            public void onResponse(Call<DeleteProdukResponse> call, Response<DeleteProdukResponse> response) {
                if(response.isSuccessful()){
                    onBackPressed();
                    Toast.makeText(DetailProdukActivity.this, "Sukses Hapus", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DeleteProdukResponse> call, Throwable t) {
                Log.d("TAG ", "onFailure: "+t);
            }
        });
    }

}