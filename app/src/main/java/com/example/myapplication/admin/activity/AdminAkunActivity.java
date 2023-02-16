package com.example.myapplication.admin.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.intro.LoginActivity;
import com.example.myapplication.model.admin.ProfileAdminModel;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminAkunActivity extends AppCompatActivity {

    private TextView tvNama;
    private TextView tvTelp;
    private TextView tvUsername;
    private TextView tvRole;
    private TextView tvUserid;
    private String path;
    private ImageButton ibBack;

    String nama, telp, username, role, userid;


    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akun_admin);

        tvNama = findViewById(R.id.tv_nama_admin);
        tvTelp = findViewById(R.id.tv_telp_admin);
        tvUsername = findViewById(R.id.tv_username_admin);
        tvUserid = findViewById(R.id.tv_userid_admin);
        ibBack = findViewById(R.id.ib_back_akun_admin);

        sharedPreferences = getSharedPreferences(LoginActivity.myLelang, Context.MODE_PRIVATE);
        path = sharedPreferences.getString(LoginActivity.TAG_KODE,"");

        ibBack.setOnClickListener(view ->{
            onBackPressed();
        });
        loadProfile();
    }

    private void loadProfile() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ProfileAdminModel> profileModelCall = retrofitAPI.getProfileAdmin(path);
        profileModelCall.enqueue(new Callback<ProfileAdminModel>(){

            @Override
            public void onResponse(Call<ProfileAdminModel> call, Response<ProfileAdminModel> response) {
                if (response.isSuccessful()) {
                    tvUserid.setText(response.body().getUserid());
                    tvNama.setText(response.body().getNama());
                    tvTelp.setText(response.body().getTelp());
                    tvUsername.setText(response.body().getUsername());

                }
            }

            @Override
            public void onFailure(Call<ProfileAdminModel> call, Throwable t) {
                Toast.makeText(AdminAkunActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
