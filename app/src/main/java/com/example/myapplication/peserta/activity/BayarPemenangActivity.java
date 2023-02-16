package com.example.myapplication.peserta.activity;

import static com.example.myapplication.util.ServerAPI.URL_GET_PHOTO;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.intro.LoginActivity;
import com.example.myapplication.model.peserta.BuktiPembayaranPemenangModel;
import com.example.myapplication.model.peserta.DepositModel;
import com.example.myapplication.model.peserta.PembayaranPemenangModel;
import com.example.myapplication.peserta.FilePath;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BayarPemenangActivity extends AppCompatActivity{
    private ImageButton btnBack, btnBayar;
    private Button btnGaleri, btnCamera;
    private ImageView imageView,ivBukti;
    private ProgressBar progressBar;
    private Dialog dialog;
    private TextView tvBayar;
    private LinearLayout lnUpload,lnBukti;
    private Boolean fieldsOK;
    private ProgressDialog pdLoading;

    private BuktiPembayaranPemenangModel model;

    String bayar,bayar1,imgencoded,lelang_id;

    int method = 0;

    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final int CAMERA_REQUEST = 1003;
    private static final int GALLERY_REQUEST = 1004;

    Bitmap bitmap;
    Uri image_uri,camera_uri;

    private Integer GALLERY_REQ, CAMERA_REQ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bayar_pemenang);

        btnBack = findViewById(R.id.btn_back_bayar_pemenang);
        btnBayar = findViewById(R.id.btn_metod_bayar_pemenang);
        btnGaleri = findViewById(R.id.btn_galeri_bukti_bayar_pemenang);
        btnCamera = findViewById(R.id.btn_foto_bukti_bayar_pemenang);
        imageView = findViewById(R.id.iv_bukti_bayar_pemenang);
        tvBayar = findViewById(R.id.tv_harga_bayar_pemenang1);

        lnUpload = findViewById(R.id.ln_upload_bukti_bayar);
        lnBukti = findViewById(R.id.ln_bukti_bayar_peserta);
        ivBukti = findViewById(R.id.iv_bukti_bayar_peserta);
        pdLoading = new ProgressDialog(this);

        dialog = new Dialog(this);

        Intent intent = getIntent();
        this.bayar = intent.getStringExtra("harga_tawar");
        this.bayar1 = intent.getStringExtra("harga_tawar");
        this.lelang_id = intent.getStringExtra("lelang_id");
        Log.d("id", lelang_id);

        generateItem();

        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        tvBayar.setText("Rp. " +decimalFormat.format(Integer.parseInt(bayar)));

        btnBack.setOnClickListener(view -> {
            onBackPressed();
        });

        btnBayar.setOnClickListener(view ->{
            fieldsOK = validate(new TextView[]{tvBayar});
            if (fieldsOK){
                new AsyncCaller().execute();
            }else {
                postPembayaran();
            }
        });

        btnGaleri.setOnClickListener(view -> {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    GALLERY_REQ = 35
            );
        });

        //Camera
        btnCamera.setOnClickListener(view ->{
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    CAMERA_REQ = 45
            );
        });
    }

    private void generateItem() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<BuktiPembayaranPemenangModel> buktiBayarCall = retrofitAPI.getBukti(lelang_id);

        buktiBayarCall.enqueue(new Callback<BuktiPembayaranPemenangModel>() {
            @Override
            public void onResponse(Call<BuktiPembayaranPemenangModel> call, Response<BuktiPembayaranPemenangModel> response) {
                if (response.isSuccessful()){
                    if(response.body().getBukti_pembayaran().equals(URL_GET_PHOTO + "peserta/bukti_transfer_pembayaran/null")){
                        lnUpload.setVisibility(View.VISIBLE);
                    }else{
                        lnBukti.setVisibility(View.VISIBLE);
                        Glide.with(BayarPemenangActivity.this).load(response.body().getBukti_pembayaran()).into(ivBukti);
                    }
                }
            }

            @Override
            public void onFailure(Call<BuktiPembayaranPemenangModel> call, Throwable t) {
                Toast.makeText(BayarPemenangActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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
            postPembayaran();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

        }
    }

    private boolean validate(TextView[] fields){
        for(int i = 0; i < fields.length; i++){
            TextView currentField = fields[i];
            if(currentField.getText().toString().length() <= 0){
                currentField.setError("Belum Ada Harga!");
                return false;
            }
        }
        return true;
    }



    private void postPembayaran() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        final PembayaranPemenangModel pembayaranPemenangModel = new PembayaranPemenangModel(lelang_id,bayar1,imgencoded);
        Call<PembayaranPemenangModel> tambahDepositCall = retrofitAPI.postbayarPemenang(pembayaranPemenangModel);

        tambahDepositCall.enqueue(new Callback<PembayaranPemenangModel>() {
            @Override
            public void onResponse(Call<PembayaranPemenangModel> call, Response<PembayaranPemenangModel> response) {
                if (response.body().getStats()) {
                    pdLoading.dismiss();
                    openKetentuanDialog();
                } else {
                    Toast.makeText(BayarPemenangActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<PembayaranPemenangModel> call, Throwable t) {
                Toast.makeText(BayarPemenangActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openKetentuanDialog() {
        dialog.setContentView(R.layout.dialog_topup_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btnKembali = dialog.findViewById(R.id.btn_kembali_dtopup);

        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), PemenangPengirimanPesertaActivity.class);
//                startActivity(intent);
                onBackPressed();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (GALLERY_REQ != null){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
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
        } else {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE,"SCAN");
                values.put(MediaStore.Images.Media.DESCRIPTION,"myScan");
                camera_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                camera.putExtra(MediaStore.EXTRA_OUTPUT, camera_uri);
                try {
                    startActivityForResult(camera,CAMERA_REQ);
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Tidak ada perizinan untuk mengakses camera", Toast.LENGTH_SHORT).show();
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
                imgencoded = Base64.encodeToString(bytes, Base64.DEFAULT);
                if (requestCode == 35) {
                    imageView.setImageBitmap(bGallery);
                    GALLERY_REQ = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                Bitmap bCamera = MediaStore.Images.Media.getBitmap(getContentResolver(), camera_uri);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bCamera.compress(Bitmap.CompressFormat.JPEG, 30, stream);
                byte[] bytes = stream.toByteArray();
                imgencoded = Base64.encodeToString(bytes, Base64.DEFAULT);
                if (requestCode == 45) {
                    imageView.setImageBitmap(bCamera);
//                    CAMERA_REQ = null;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

}