package com.example.myapplication.pelelang.hostui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.intro.LoginActivity;
import com.example.myapplication.model.pelelang.ProfilePelelangModel;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AkunPelelangActivity extends AppCompatActivity {

    private TextView tvNama,tvJenis,tvProvinsi,tvalamat,tvNotelp,tvEmail,tvNik,tvStatus;
    private String path;
    Integer status,jenis;
    String status1,statusJenis;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akun_pelelang);
        Button btnUpdateProfile =findViewById(R.id.btn_updateProfile);
        ImageButton btnKembaliPelelangAkun = findViewById(R.id.btn_back);

        tvNama = findViewById(R.id.tv_nama);
//        tvJenis = findViewById(R.id.tv_jenisusaha);
        tvalamat = findViewById(R.id.tv_alamat);
//        tvProvinsi = findViewById(R.id.tv_provinsi);
        tvEmail = findViewById(R.id.tv_email);
        tvNotelp = findViewById(R.id.tv_notelp);
        tvNik = findViewById(R.id.tv_nik);
        tvStatus = findViewById(R.id.tv_Statuspelelang);

        sharedPreferences = getSharedPreferences(LoginActivity.myLelang, Context.MODE_PRIVATE);
        path = sharedPreferences.getString(LoginActivity.TAG_KODE,"");

        loadProfile();
        
        btnKembaliPelelangAkun.setOnClickListener(view -> {
            Intent kembali = new Intent(this, PelelangActivity.class);
            startActivity(kembali);
        });

        btnUpdateProfile.setOnClickListener(view -> {
            Intent update = new Intent(this, EditAkunPelelang.class);
            startActivity(update);
        });
    }

    private void loadProfile() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ProfilePelelangModel> getProfilePelelangCall = retrofitAPI.getProfilePelelangId(path);
        getProfilePelelangCall.enqueue(new Callback<ProfilePelelangModel>() {
            @Override
            public void onResponse(Call<ProfilePelelangModel> call, Response<ProfilePelelangModel> response) {
                if(response.isSuccessful()){
                    tvNama.setText(response.body().getNama());
//                    jenis = response.body().getJenis();
////                    tvJenis.setText(response.body().getJenis());
//                    if (jenis==1) {
//                        statusJenis = "Perorangan";
//                    } else if (jenis==2){
//                        statusJenis = "Pelelang";
//                    } else if(jenis==3){
//                        statusJenis = "Koperasi";
//                    }
//
//                    tvJenis.setText(statusJenis);

//                    tvProvinsi.setText(response.body().getProvinsi());
                    tvalamat.setText(response.body().getAlamat());
                    tvNotelp.setText(response.body().getTelp());
                    tvEmail.setText(response.body().getEmail());
                    tvNik.setText(response.body().getNik());
                     status = Integer.parseInt(response.body().getStatus());
//                    Log.d("dddd", String.valueOf(status));
//                    tvStatus.setText(Integer.toString(status));
//                    String status2= Integer.toString(status),status1;

//                    status = tvStatus;
                    if (status==1) {
                        status1 = "Sudah Diverivikasi";
                    } else if (status==2){
                        status1 = "DiTolak";
                    } else {
                        status1 = "Belum diverifikasi";
                    }

                    tvStatus.setText(status1);

                }
            }

            @Override
            public void onFailure(Call<ProfilePelelangModel> call, Throwable t) {
                Toast.makeText(AkunPelelangActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}