package com.example.myapplication.panitia.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.intro.LoginActivity;
import com.example.myapplication.model.panitia.PanitiaprofileModel;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PanitiaActivity extends AppCompatActivity {
    private LinearLayout suratPerintah, btnInformasi, btnPengemasan, btnTesti,
            btnStatus, btnPemenang, btnCalonPemenang, btnPembayaran, lnPeserta;
    private ImageView akun;
    private TextView greetText, tvNama;
    private CircleImageView imageView;

    SharedPreferences sharedPreferences;
    String username,kode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_panitia);

        suratPerintah = findViewById(R.id.btnSuratPerintah);
        btnInformasi = findViewById(R.id.btnInformasiPanitia);
        btnPengemasan = findViewById(R.id.btnPengemasanPanitia);
        btnTesti = findViewById(R.id.btnTestimoniPanitia);
        btnStatus = findViewById(R.id.btnStatusPanitia);
        btnPemenang = findViewById(R.id.btnPemenangPanitia);
        btnCalonPemenang = findViewById(R.id.btnCalonPemenangPanitia);
        btnPembayaran = findViewById(R.id.btnPembayaranHasilLelangPanitia);
        imageView = findViewById(R.id.gambar_akun_panitia);
        lnPeserta = findViewById(R.id.btnPembayaranPesertaPanitia);

        greetText = findViewById(R.id.greeting_text_panitia);
        tvNama = findViewById(R.id.tv_nama_panitia);

        sharedPreferences = getSharedPreferences(LoginActivity.myLelang, Context.MODE_PRIVATE);
        kode = sharedPreferences.getString(LoginActivity.TAG_KODE,"");
        username = sharedPreferences.getString(LoginActivity.TAG_USERNAME,"");
        tvNama.setText(username);

        loadprof();

        suratPerintah.setOnClickListener(view -> {
            startActivity(new Intent(this, SuratPanitiaActivity.class));
        });

        btnInformasi.setOnClickListener(view -> {
            startActivity(new Intent(this, VerifikasiPanitiaActivity.class));
        });

        btnPengemasan.setOnClickListener(view -> {
            startActivity(new Intent(this, PengemasanPanitiaActivity.class));
        });

        btnTesti.setOnClickListener(view -> {
            startActivity(new Intent(this, TestimoniPanitiaActivity.class));
        });

        btnStatus.setOnClickListener(view -> {
            startActivity(new Intent(this, StatusPanitiaActivity.class));
        });

        btnPemenang.setOnClickListener(view -> {
            startActivity(new Intent(this, StatusPemenangPanitiaActivity.class));
        });

        imageView.setOnClickListener(view -> {
            startActivity(new Intent(this, AkunPanitiaActivity.class));
        });

        btnCalonPemenang.setOnClickListener(view -> {
            startActivity(new Intent(this, CalonPemenangPanitiaActivity.class));
        });

        btnPembayaran.setOnClickListener(view -> {
            startActivity(new Intent(this, PembayaranSaldoPanitiaActivity.class));
        });

        lnPeserta.setOnClickListener(view->{
            startActivity(new Intent(this, PembayaranPesertaPanitiaActivity.class));
        });
        greeting();
    }

    private void loadprof() {
        RetrofitAPI retrofitAPI= RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<PanitiaprofileModel> panitiaprofileModels=retrofitAPI.getProfilePanitia(kode);

        panitiaprofileModels.enqueue(new Callback<PanitiaprofileModel>() {
            @Override
            public void onResponse(Call<PanitiaprofileModel> call, Response<PanitiaprofileModel> response) {
                if (response.isSuccessful()){
                    Glide.with(PanitiaActivity.this).load(response.body().getFoto()).into(imageView);
                }
            }
            @Override
            public void onFailure(Call<PanitiaprofileModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void greeting() {
        Calendar calendar = Calendar.getInstance();
        int timeOfDay = calendar.get(Calendar.HOUR_OF_DAY);

        if (timeOfDay >= 0 && timeOfDay < 12) {
            greetText.setText(getString(R.string.salam_pagi));
        } else if (timeOfDay >= 12 && timeOfDay < 15) {
            greetText.setText(getString(R.string.salam_siang));
        } else if (timeOfDay >= 15 && timeOfDay < 18) {
            greetText.setText(getString(R.string.salam_sore));
        } else if (timeOfDay >= 18 && timeOfDay < 24) {
            greetText.setText(getString(R.string.salam_malam));
        }
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setMessage("Apakah kamu ingin keluar?")
                .setPositiveButton("Iya", (dialog, which) -> {
                    int pid = android.os.Process.myPid();
                    android.os.Process.killProcess(pid);
                })
                .setNegativeButton("Tidak", (dialog, which) -> dialog.dismiss())
                .show();
    }
}
