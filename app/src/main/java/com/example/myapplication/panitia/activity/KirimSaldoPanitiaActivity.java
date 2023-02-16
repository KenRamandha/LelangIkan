package com.example.myapplication.panitia.activity;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.intro.LoginActivity;
import com.example.myapplication.model.panitia.PanitiaRiwayatBuktiTransferModel;
import com.example.myapplication.model.panitia.PanitiaRiwayatTransferModel;
import com.example.myapplication.model.panitia.PanitiaTransferModel;
import com.example.myapplication.model.peserta.DepositModel;
import com.example.myapplication.panitia.adapter.AdapterCardRiwayatTransferPanitia;
import com.example.myapplication.peserta.activity.MainActivity;
import com.example.myapplication.peserta.activity.MetodBayarActivity;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KirimSaldoPanitiaActivity extends AppCompatActivity {

    private TextView tvNama,tvNorek,tvBukti,tvNominal;
    private ImageButton btnBack,btnSimpan;
    private LinearLayout lnGaleri, lnSudah, lnBelum;
    private Dialog dialog;
    private ImageView imageView;
    private ProgressDialog pdLoading;

    SharedPreferences sharedPreferences;
    private PanitiaRiwayatBuktiTransferModel model;

    String path,nama,norek,nominal,lelang_id,imgencode,pelelang_id,gambar;

    private Integer GALLERY_REQ;
    private Boolean fieldsOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kirim_saldo_panitia);

        pdLoading = new ProgressDialog(this);

        btnBack = findViewById(R.id.btn_kembali_pembayaran_panitia);
        btnSimpan = findViewById(R.id.btn_kirim_pembayaran_panitia);
        lnGaleri = findViewById(R.id.btn_galeri_pembayaran_panitia);
        lnSudah = findViewById(R.id.ln_berhasil_panitia);
        lnBelum = findViewById(R.id.ln_tf_panitia);

        tvNama = findViewById(R.id.tv_nama_pembayaran_panitia);
        tvNorek = findViewById(R.id.tv_norek_pembayaran_panitia);
        tvBukti = findViewById(R.id.tv_bukti_pembayaran_panitia);
        tvNominal = findViewById(R.id.tv_nominal_pembayaran_panitia);
        imageView = findViewById(R.id.iv_bukti_transfer_panitia);

        dialog = new Dialog(this);

        sharedPreferences = getSharedPreferences(LoginActivity.myLelang, Context.MODE_PRIVATE);
        path = sharedPreferences.getString(LoginActivity.TAG_KODE,"");
        Log.d("panitia_id", path);

        btnBack.setOnClickListener(view-> onBackPressed());


        Intent intent = getIntent();
        this.nama = intent.getStringExtra("nama");
        this.norek = intent.getStringExtra("norek");
        this.nominal = intent.getStringExtra("nominal");
        this.lelang_id = intent.getStringExtra("lelang_id");
        this.pelelang_id = intent.getStringExtra("pelelang_id");
        Log.d("lelang_id", lelang_id);

        tvNama.setText(nama);
        tvNorek.setText(norek);
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        if (nominal !=null){
            tvNominal.setText("Rp. " + decimalFormat.format(Integer.parseInt(nominal)));
        }


        lnGaleri.setOnClickListener(view ->{
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    GALLERY_REQ = 35
            );
        });

        generateItem();
        btnSimpan.setOnClickListener(view ->{
            fieldsOK = validate(new TextView[]{tvNominal});
            if (fieldsOK){
                new AsyncCaller().execute();
            }else{
                postBukti();
            }
        });

    }

    private class AsyncCaller extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //this method will be running on UI thread
            pdLoading.setMessage("Tunggu sebentar..");
            pdLoading.setCancelable(false);
            pdLoading.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            //this method will be running on background thread so don't update UI frome here
            //do your long running http tasks here,you dont want to pass argument and u can access the parent class' variable url over here
            postBukti();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            //this method will be running on UI thread
        }
    }
    private boolean validate(TextView[] fields){
        for(int i = 0; i < fields.length; i++){
            TextView currentField = fields[i];
            if(currentField.getText().toString().length() <= 0){
                currentField.setError("Harga Tidak boleh kosong!");
                return false;
            }
        }
        return true;
    }

    private void generateItem() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<PanitiaRiwayatBuktiTransferModel> buktiPanitiaListCall = retrofitAPI.getRiwayatBuktiTransferPanitia(lelang_id);
        buktiPanitiaListCall.enqueue(new Callback<PanitiaRiwayatBuktiTransferModel>() {
            @Override
            public void onResponse(Call<PanitiaRiwayatBuktiTransferModel> call, Response<PanitiaRiwayatBuktiTransferModel> response) {
                if (response.isSuccessful()){
                    lnBelum.setVisibility(View.GONE);
                    lnSudah.setVisibility(View.VISIBLE);
                    Glide.with(KirimSaldoPanitiaActivity.this).load(response.body().getBukti_transfer()).into(imageView);
                }
            }

            @Override
            public void onFailure(Call<PanitiaRiwayatBuktiTransferModel> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
//                Log.d("eror", t.getMessage());
            }
        });
    }

    private void postBukti() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        final PanitiaTransferModel panitiaTransferModel = new PanitiaTransferModel(pelelang_id,nama,lelang_id,path,imgencode,nominal);
        Call<PanitiaTransferModel> transferpanitiaCall = retrofitAPI.postTransferPembayaranPanitia(panitiaTransferModel);

        transferpanitiaCall.enqueue(new Callback<PanitiaTransferModel>() {
            @Override
            public void onResponse(Call<PanitiaTransferModel> call, Response<PanitiaTransferModel> response) {
                if (response.body().getStats()) {
                    openSuksesDialog();
                } else {
                    Toast.makeText(KirimSaldoPanitiaActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PanitiaTransferModel> call, Throwable t) {
                Toast.makeText(KirimSaldoPanitiaActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openSuksesDialog() {
        dialog.setContentView(R.layout.dialog_topup_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btnKembali = dialog.findViewById(R.id.btn_kembali_dtopup);

        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PanitiaActivity.class);
                startActivity(intent);
            }
        });
        dialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (GALLERY_REQ != null) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Open Galley
                Intent gallery = new Intent(Intent.ACTION_PICK).setType("image/*");
                try {
                    startActivityForResult(gallery, GALLERY_REQ);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Tidak ada perizinan untuk mengakses gambar", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && GALLERY_REQ != null) {
            Uri uri = data.getData();

            try {
                Bitmap bGallery = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bGallery.compress(Bitmap.CompressFormat.JPEG, 30, stream);
                byte[] bytes = stream.toByteArray();
                imgencode = Base64.encodeToString(bytes, Base64.DEFAULT);

                if (requestCode == 35) {
//                    imageView.setImageBitmap(bGallery);
                    tvBukti.setText("Bukti Bayar");
                    GALLERY_REQ = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}