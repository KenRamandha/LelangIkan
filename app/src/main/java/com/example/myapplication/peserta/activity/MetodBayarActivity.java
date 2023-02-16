package com.example.myapplication.peserta.activity;

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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.myapplication.R;
import com.example.myapplication.intro.LoginActivity;
import com.example.myapplication.model.pelelang.TambahLelangModel;
import com.example.myapplication.model.peserta.DepositModel;
import com.example.myapplication.pelelang.hostui.TambahLelangActivity;
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

public class MetodBayarActivity extends AppCompatActivity {
    private ImageButton btnBack, btnBayar;
    private Button btnGaleri, btnCamera;
    private ImageView imageView;
    private ProgressBar progressBar;
    private Dialog dialog;
    private TextView tvTotal,tvKode;
    private ProgressDialog pdLoading;

    int method = 0;

    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final int CAMERA_REQUEST = 1003;
    private static final int GALLERY_REQUEST = 1004;

    Bitmap bitmap;
    Uri image_uri,camera_uri;

    private String kode, path,str,deposit_encode,bukti_pembayaran,nominal_deposit,imgencoded;
    private Integer GALLERY_REQ, CAMERA_REQ;
    private Boolean fieldsOK;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metode_bayar);

        pdLoading = new ProgressDialog(this);

        btnBack = findViewById(R.id.btn_back_pilihmetode);
        btnBayar = findViewById(R.id.btn_metod_bayar);
        btnGaleri = findViewById(R.id.btn_galeri_bukti);
        btnCamera = findViewById(R.id.btn_foto_bukti);
        imageView = findViewById(R.id.iv_bukti_bayar);
        progressBar = findViewById(R.id.progressbar_bukti);
        tvTotal = findViewById(R.id.tv_harga_metod_bayar1);

        tvKode = findViewById(R.id.tv_idPeserta_deposit);

        sharedPreferences = getSharedPreferences(LoginActivity.myLelang, Context.MODE_PRIVATE);
        path = sharedPreferences.getString(LoginActivity.TAG_KODE,"");
        Log.d("kode", path);

        Intent intent = getIntent();
        str = intent.getStringExtra("jumlah");

        tvTotal.setText(str);
        tvKode.setText(path);

//        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
//        tvTotal.setText("Rp."+decimalFormat.format(Integer.parseInt(str)));


        dialog = new Dialog(this);

        btnBack.setOnClickListener(view -> {
            onBackPressed();
        });

        btnBayar.setOnClickListener(view -> {
            fieldsOK = validate(new TextView[]{tvTotal});
            if (fieldsOK){
                new AsyncCaller().execute();
            }else {
                postRegAdd();
            }

        });
//
        //Galeri
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
            postRegAdd();
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

    private void openKetentuanDialog() {
        dialog.setContentView(R.layout.dialog_topup_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btnKembali = dialog.findViewById(R.id.btn_kembali_dtopup);

        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        dialog.show();
    }

    private void postRegAdd() {
        nominal_deposit = tvTotal.getText().toString();
        bukti_pembayaran = deposit_encode;

        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        final DepositModel depositModel = new DepositModel(path,nominal_deposit,imgencoded);
        Call<DepositModel> tambahDepositCall = retrofitAPI.tambahDeposit(depositModel);

        tambahDepositCall.enqueue(new Callback<DepositModel>() {
            @Override
            public void onResponse(Call<DepositModel> call, Response<DepositModel> response) {
                if (response.body().getStats()) {
                    pdLoading.dismiss();
                    openKetentuanDialog();
                } else {
                    Toast.makeText(MetodBayarActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<DepositModel> call, Throwable t) {
                Toast.makeText(MetodBayarActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}