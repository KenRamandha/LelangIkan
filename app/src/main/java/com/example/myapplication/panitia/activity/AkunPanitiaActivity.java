package com.example.myapplication.panitia.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapplication.intro.LoginActivity;
import com.example.myapplication.intro.PeranActivity;
import com.example.myapplication.R;
import com.example.myapplication.model.panitia.PanitiaprofileModel;
import com.example.myapplication.peserta.activity.MainActivity;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;
import com.makeramen.roundedimageview.RoundedImageView;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AkunPanitiaActivity extends AppCompatActivity {

    private LinearLayout btnPembayaran, btnEdit;
    private ImageButton btnBack, btnEditAkun, btnRiwayat,btnTransfer,btnTestimoni;
    private Button btnKeluar;
    private TextView tvNama;
    private CircleImageView imageView;

    SharedPreferences sharedPreferences;
    String username,kode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akun_panitia);

        btnPembayaran = findViewById(R.id.pembayaran_saldo_panitia);
        btnEdit = findViewById(R.id.edit_akun_panitia);
        btnKeluar = findViewById(R.id.btn_keluar_panitia);
        btnBack = findViewById(R.id.btn_back_panitia);
        btnRiwayat = findViewById(R.id.btn_riwayat_lelang_panitia);
        btnTransfer = findViewById(R.id.btn_transfer_lelang_panitia);
        btnTestimoni = findViewById(R.id.btn_testimoni_lelang_panitia);
        tvNama = findViewById(R.id.tv_nama_panitia_akun);
        btnEditAkun = findViewById(R.id.btn_edit_akun);
        imageView = findViewById(R.id.iv_akun_panitia);

        sharedPreferences = getSharedPreferences(LoginActivity.myLelang, Context.MODE_PRIVATE);
        kode = sharedPreferences.getString(LoginActivity.TAG_KODE,"");
        username = sharedPreferences.getString(LoginActivity.TAG_USERNAME,"");
        tvNama.setText(username);

        loadprof();

        btnEditAkun.setOnClickListener(view -> {
            startActivity(new Intent(this, EditAkunPanitiaActivity.class));
        });

        btnPembayaran.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), PembayaranSaldoPanitiaActivity.class);
            startActivity(i);
        });

        btnBack.setOnClickListener(view ->{
            startActivity(new Intent(this,PanitiaActivity.class));
            finish();
        });

        btnEdit.setOnClickListener(view -> {
            Intent j = new Intent(getApplicationContext(), ActivityEditAkunPanitia.class);
            startActivity(j);
        });

        btnTransfer.setOnClickListener(view -> {
            Intent p = new Intent(getApplicationContext(),PembayaranSaldoPanitiaActivity.class);
            startActivity(p);
        });
        btnTestimoni.setOnClickListener(view -> {
            Intent l = new Intent(getApplicationContext(), TestimoniPanitiaActivity.class);
            startActivity(l);
        });
        btnRiwayat.setOnClickListener(view->{
            Intent r = new Intent(getApplicationContext(),RiwayatPanitiaActivity.class);
            startActivity(r);
        });

        btnKeluar.setOnClickListener(view ->{
            sharedPreferences.edit().clear().commit();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        });
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putBoolean(LoginActivity.session_status, false);
//        editor.putString(LoginActivity.TAG_USERNAME, null);
//        editor.commit();
//        finish();
//        startActivity(new Intent(getApplicationContext(), PeranActivity.class));
    }

    private void loadprof() {
        RetrofitAPI retrofitAPI= RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<PanitiaprofileModel> panitiaprofileModels=retrofitAPI.getProfilePanitia(kode);
        panitiaprofileModels.enqueue(new Callback<PanitiaprofileModel>() {
            @Override
            public void onResponse(Call<PanitiaprofileModel> call, Response<PanitiaprofileModel> response) {
                if (response.isSuccessful()){
                    Glide.with(AkunPanitiaActivity.this).load(response.body().getFoto()).into(imageView);
                }
            }

            @Override
            public void onFailure(Call<PanitiaprofileModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}