package com.example.myapplication.pelelang.hostui.transaksidetail;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityDetailTransaksiBinding;
import com.example.myapplication.model.pelelang.DetailTransaksiResponse;
import com.example.myapplication.model.pelelang.EditStatusTransaksi;
import com.example.myapplication.model.pelelang.KatalogPelelangModel;
import com.example.myapplication.model.pelelang.KonfirmasiPembayaran;
import com.example.myapplication.model.pelelang.UpdateNamaKendaraan;
import com.example.myapplication.model.pelelang.UpdateNamaPengirim;
import com.example.myapplication.model.pelelang.UpdateNoHpPengirim;
import com.example.myapplication.model.pelelang.UpdateNoPolisiPengirim;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;
import com.example.myapplication.util.ServerAPI;
import com.google.gson.Gson;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailTransaksi extends AppCompatActivity implements AdapterView.OnItemSelectedListener{


    private ActivityDetailTransaksiBinding binding;
    private Context context;
    private static List<KatalogPelelangModel> itemList;
    private Spinner spinner;
    private String [] sts_transaksi = {"Sedang Diproses", "Sedang Dikirim", "Barang Telah Sampai"};
    
    DetailTransaksiResponse model;
    String noInvoice, metodeBayar, status, jmlPembelian;
    String jsonString;
    private static final Gson gson = new Gson();
    private Dialog dialog;
    String namaKendaraan = "";
    String noPolisiPengirim = "";
    String noHp = "";
    String namaPengirim = "";
    String uploadBukti;
    String stsTransaksi = "";
    File photo;

    ActivityResultLauncher<Intent> launchImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent intent = result.getData();
                    if (intent != null){
                        Uri uri = intent.getData();
                        uploadBukti = getRealPathFromUri(uri);
                        Glide.with(getApplicationContext())
                                .load(uri)
                                .placeholder(R.drawable.imgup)
                                .into(binding.imgPreview);
                        photo = new File(uploadBukti);
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailTransaksiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        spinner = binding.spinnerStsTransaksi;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(DetailTransaksi.this,
                R.array.sts_transaksi, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(DetailTransaksi.this);

        noInvoice = getIntent().getStringExtra("id");
        setUpData();

//        binding.btnUploadBukti.setOnClickListener(view -> {
//            Intent intent = new Intent(Intent.ACTION_PICK);
//            intent.setType("image/*");
//            launchImage.launch(intent);
//        });

        binding.btnubahMerkKendaraan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                namaKendaraan = binding.merkKendaraan.getText().toString();

//                RequestBody nama_Kendaraan = RequestBody.create(namaKendaraan, MediaType.parse("text/plain"));

                RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
                Call<UpdateNamaKendaraan> updateNamaKendaraanCall = retrofitAPI.getNamaKendaraan(noInvoice, namaKendaraan);
                updateNamaKendaraanCall.enqueue(new Callback<UpdateNamaKendaraan>() {
                    @Override
                    public void onResponse(Call<UpdateNamaKendaraan> call, Response<UpdateNamaKendaraan> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(DetailTransaksi.this, "Update Merk Kendaraan Berhasil", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UpdateNamaKendaraan> call, Throwable t) {
                        Log.d("asd", "onFailure: "+t);
                    }
                });
            }
        });

        binding.btnubahNoPolisi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                noPolisiPengirim = binding.noPolisi.getText().toString();
//                RequestBody no_polisi = RequestBody.create(noPolisiPengirim, MediaType.parse("text/plain"));

                RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
                Call<UpdateNoPolisiPengirim> updateNoPolisiPengirimCall = retrofitAPI.getNoPolisi(noInvoice, noPolisiPengirim);
                updateNoPolisiPengirimCall.enqueue(new Callback<UpdateNoPolisiPengirim>() {
                    @Override
                    public void onResponse(Call<UpdateNoPolisiPengirim> call, Response<UpdateNoPolisiPengirim> response) {
                        if (response.isSuccessful()) {
                            if (!response.body().getNo_polisi().isEmpty()){
                                Toast.makeText(DetailTransaksi.this,"Update No Polisi Berhasil", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<UpdateNoPolisiPengirim> call, Throwable t) {
                        Log.d("asd", "onFailure: "+t);
                    }
                });
            }
        });

        binding.btnUbahNamaPengirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                namaPengirim = binding.namaPengirim.getText().toString();

                RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
                Call<UpdateNamaPengirim> updateNamaPengirimCall = retrofitAPI.getNamaPengirim(noInvoice, namaPengirim);
                updateNamaPengirimCall.enqueue(new Callback<UpdateNamaPengirim>() {
                    @Override
                    public void onResponse(Call<UpdateNamaPengirim> call, Response<UpdateNamaPengirim> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(DetailTransaksi.this, "Update Nama Pengirim Berhasil", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UpdateNamaPengirim> call, Throwable t) {
                        Log.d("TAG ", "onFailure: "+t);
                    }
                });
            }
        });

        binding.btnubahNoPengirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                noHp = binding.noHpPengirim.getText().toString();
//                RequestBody no_hp = RequestBody.create(noHp, MediaType.parse("text/plain"));

                RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
                Call<UpdateNoHpPengirim> updateNoHpPengirimCall = retrofitAPI.getNoHp(noInvoice, noHp);
                updateNoHpPengirimCall.enqueue(new Callback<UpdateNoHpPengirim>() {
                    @Override
                    public void onResponse(Call<UpdateNoHpPengirim> call, Response<UpdateNoHpPengirim> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(DetailTransaksi.this, "Update No Hp Berhasil", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<UpdateNoHpPengirim> call, Throwable t) {
                        Log.d("asd", "onFailure: "+t);
                    }
                });
            }
        });

        binding.btnKonfirmasiBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData();
            }
        });
    }

    private void setUpData() {

        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<DetailTransaksiResponse> listDataTransaksiCall = retrofitAPI.getAllTransaksi(noInvoice);
        listDataTransaksiCall.enqueue(new Callback<DetailTransaksiResponse>() {
            @Override
            public void onResponse(Call<DetailTransaksiResponse> call, Response<DetailTransaksiResponse> response) {
                if (response.isSuccessful()) {
                    model = response.body();

                    namaKendaraan = model.getData().get(0).get(0).getNamaPengirim();
                    binding.btnBackBuatLelang.setOnClickListener(view -> onBackPressed());
                    binding.noInvoice.setText(model.getData().get(0).get(0).getLelangId());
                    if (model.getData().get(0).get(0).getMetodeBayar()==null ||
                            model.getData().get(0).get(0).getMetodeBayar()==0){
                        binding.metodePembayaran.setText("Transfer");
                    }

                    if (model.getData().get(0).get(0).getStatus().equals("1")){
                        binding.stsPembayaran.setText("Berhasil Dibayar");
                    }
                    else if (model.getData().get(0).get(0).getStatus().equals("2")){
                        binding.stsPembayaran.setText("Ditolak");
                    }
                    else {
                        binding.stsPembayaran.setText("Belum Dibayar");
                    }
                    binding.jmlPembelian.setText(model.getData().get(0).get(0).getJumlah());

                    if (model.getData().get(0).get(0).getNamaPengirim()!=null){
                        binding.namaPengirim.setText(model.getData().get(0).get(0).getNamaPengirim());
                        binding.btnUbahNamaPengirim.setVisibility(View.VISIBLE);
                    }
                    else{
                        binding.btnUbahNamaPengirim.setVisibility(View.GONE);
                        binding.namaPengirim.setText("Belum Ada Data");
                    }

                    if (model.getData().get(0).get(0).getNoPolisi()!=null){
                        binding.noPolisi.setText(model.getData().get(0).get(0).getNoPolisi());
                        binding.btnubahNoPolisi.setVisibility(View.VISIBLE);
                    }
                    else{
                        binding.btnubahNoPolisi.setVisibility(View.GONE);
                        binding.noPolisi.setText("Belum Ada Data");
                    }

                    if (model.getData().get(0).get(0).getNamaKendaraan()!=null){
                        binding.merkKendaraan.setText(model.getData().get(0).get(0).getNamaKendaraan());
                        binding.btnubahMerkKendaraan.setVisibility(View.VISIBLE);
                    }
                    else{
                        binding.btnubahMerkKendaraan.setVisibility(View.GONE);
                        binding.merkKendaraan.setText("Belum Ada Data");
                    }

                    if (model.getData().get(0).get(0).getNoHp()!=null){
                        binding.noHpPengirim.setText(model.getData().get(0).get(0).getNoHp());
                        binding.btnubahNoPengirim.setVisibility(View.VISIBLE);
                    }
                    else{
                        binding.btnubahNoPengirim.setVisibility(View.GONE);
                        binding.noHpPengirim.setText("Belum Ada Data");
                    }

                    binding.tglPengiriman.setText(model.getData().get(0).get(0).getmTglPengiriman());
                    Glide.with(getApplicationContext())
                            .load(ServerAPI.URL_GET_GAMBAR+"images/pembayaran/"+model.getData().get(0).get(0).getBuktiPembayaran())
                            .into(binding.imgPreview);

                    if (model.getData().get(0).get(0).getStatusTransaksi().equals("0")){
                        spinner.setSelection(0);

                    }
                    else if (model.getData().get(0).get(0).getStatusTransaksi().equals("1")){
                        spinner.setSelection(1);
                    }
                    else {spinner.setSelection(2);}

                    binding.biayaAdmin.setText(model.getBiayaAdm().get(0).getBiayaAdm());

                }
            }

            @Override
            public void onFailure(Call<DetailTransaksiResponse> call, Throwable t) {
                Log.d("asd", "onFailure: "+t);
            }
        });
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<EditStatusTransaksi> editStatusTransaksiCall = retrofitAPI.getStsTransaksi(noInvoice, String.valueOf(pos));
        editStatusTransaksiCall.enqueue(new Callback<EditStatusTransaksi>() {
            @Override
            public void onResponse(Call<EditStatusTransaksi> call, Response<EditStatusTransaksi> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(DetailTransaksi.this, "Berhasil Ubah Status Transaksi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EditStatusTransaksi> call, Throwable t) {
                Log.d("asd", "onFailure: "+t);
            }
        });
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    private String getRealPathFromUri(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getBaseContext(), contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index;
        String result = null;
        if (cursor != null) {
            column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            result = cursor.getString(column_index);
            cursor.close();
        }
        return result;
    }

    private void sendData(){
        String namaPengirim = binding.namaPengirim.getText().toString();
        String noPol = binding.noPolisi.getText().toString();
        String merkKendaraan = binding.merkKendaraan.getText().toString();
        String noHp = binding.noHpPengirim.getText().toString();
        String stsPengiriman = String.valueOf(binding.spinnerStsTransaksi.getDropDownVerticalOffset());
        String tglPengiriman = binding.tglPengiriman.getText().toString();

        RequestBody pengirim = RequestBody.create(namaPengirim,MediaType.parse("text/plain"));
        RequestBody no_pol = RequestBody.create(noPolisiPengirim,MediaType.parse("text/plain"));
        RequestBody nama_kendaraan = RequestBody.create(namaKendaraan,MediaType.parse("text/plain"));
        RequestBody telp = RequestBody.create(noHp,MediaType.parse("text/plain"));
        RequestBody status_transaksi = RequestBody.create(stsPengiriman,MediaType.parse("text/plain"));
        RequestBody tgl_kirim = RequestBody.create(tglPengiriman,MediaType.parse("text/plain"));

        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<KonfirmasiPembayaran> konfirmasiPembayaranCall = retrofitAPI.getKonfirmasiBayar(
                pengirim, no_pol, nama_kendaraan, telp, status_transaksi, tgl_kirim
        );
        konfirmasiPembayaranCall.enqueue(new Callback<KonfirmasiPembayaran>() {
            @Override
            public void onResponse(@NonNull Call<KonfirmasiPembayaran> call, @NonNull Response<KonfirmasiPembayaran> response) {
                if (response.body() != null) {
                    Toast.makeText(DetailTransaksi.this,
                            "Sukses", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<KonfirmasiPembayaran> call, @NonNull Throwable t) {
                Toast.makeText(DetailTransaksi.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}