package com.example.myapplication.peserta.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.myapplication.R;
import com.example.myapplication.intro.LoginActivity;
import com.example.myapplication.model.peserta.AlamatKirimPemenangModel;
import com.example.myapplication.model.peserta.ProfilePesertaModel;
import com.example.myapplication.model.peserta.WaktuLelangModel;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.io.ByteArrayOutputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PemenangPengirimanPesertaActivity extends AppCompatActivity{

    private ImageButton btnKembali;
    private Button btnSimpan;
    private EditText etProvinsi,etKota,etKecamatan,etAlamat,etKelurahan;

    private ProgressBar progressBar;

    private Dialog dialog;

    SharedPreferences sharedPreferences;

    String path, provinsi, kota, kecamatan, alamat, kelurahan;

    int  method = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peserta_pengiriman_pemenang);

        etProvinsi = findViewById(R.id.et_provinsi_alamat_kirim_peserta);
        etKota = findViewById(R.id.et_kota_alamat_kirim_peserta);
        etKecamatan = findViewById(R.id.et_kecamatan_alamat_kirim_peserta);
        etKelurahan = findViewById(R.id.et_kelurahan_alamat_kirim_peserta);
        etAlamat = findViewById(R.id.et_alamat_alamat_kirim_peserta);

        btnSimpan = findViewById(R.id.btn_simpan_alamat_kirim_peserta);


        btnSimpan.setOnClickListener(view -> postPengiriman());

        sharedPreferences = getSharedPreferences(DetailLelangActivity.detailLelang, Context.MODE_PRIVATE);
        path = sharedPreferences.getString(DetailLelangActivity.TAG_ID,"");
        Log.d("kode", path);

        dialog = new Dialog(this);


    }

    private void postPengiriman() {
        provinsi = etProvinsi.getText().toString();
        kecamatan = etKecamatan.getText().toString();
        kota = etKota.getText().toString();
        kelurahan = etKelurahan.getText().toString();
        alamat = etAlamat.getText().toString();
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        final AlamatKirimPemenangModel alamatKirimPemenangModel = new AlamatKirimPemenangModel(path,provinsi,kota,kecamatan,kelurahan,alamat);
        Call<AlamatKirimPemenangModel> alamatKirimCall = retrofitAPI.postAlamatKirim(alamatKirimPemenangModel);
        alamatKirimCall.enqueue(new Callback<AlamatKirimPemenangModel>() {
            @Override
            public void onResponse(Call<AlamatKirimPemenangModel> call, Response<AlamatKirimPemenangModel> response) {
                if (response.body().getStats()) {
//                    Intent intent = new Intent(PemenangPengirimanPesertaActivity.this,DaftarMenangActivity.class);
//                    startActivity(intent);
//                    dialog.dismiss();
                    openDialog();


                } else {
                    Toast.makeText(PemenangPengirimanPesertaActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AlamatKirimPemenangModel> call, Throwable t) {
                Toast.makeText(PemenangPengirimanPesertaActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openDialog() {
        dialog.setContentView(R.layout.dialog_alamat_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btnKembali = dialog.findViewById(R.id.btn_kembali_dalamat);

        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
