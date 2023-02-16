package com.example.myapplication.intro;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.model.RegisterModel;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DaftarAkunActivity extends AppCompatActivity {

    private Button btnLogin,btnDaftar;
    private EditText edNamaL, edEmail, edUser, edPass, edHp,edNik,edNpwp,edAlamat;
    private Spinner spPeran;
    private TextView tvSyarat;

    private Dialog dialog;

    String nama, telp, email, username, password, peran,nik,npwp,alamat;
    int role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_akun);

        btnLogin = findViewById(R.id.btn_login_daftar);
        btnDaftar = findViewById(R.id.btn_daftar);
        edNamaL = findViewById(R.id.input_nama);
        edNik = findViewById(R.id.input_nik);
        edNpwp = findViewById(R.id.input_npwp);
        edAlamat = findViewById(R.id.input_alamat);
        edHp = findViewById(R.id.input_ponsel);
        edEmail = findViewById(R.id.input_email);
        edUser = findViewById(R.id.input_usernme);
        edPass = findViewById(R.id.input_password);
        spPeran = findViewById(R.id.spinnerPeran);
        tvSyarat = findViewById(R.id.tv_syarat_ketentuan);

        dialog = new Dialog(this);

        String[] jenis_kelamin = new String[]{"Peserta", "Pelelang"};
        ArrayAdapter<String> adapterJenis = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, jenis_kelamin);
        spPeran.setAdapter(adapterJenis);

        btnLogin.setOnClickListener(view -> onBackPressed());

        btnDaftar.setOnClickListener(view -> postReg());
        tvSyarat.setOnClickListener(view -> openSyaratDialog());
    }

    private void openSyaratDialog() {
        dialog.setContentView(R.layout.dialog_syarat_ketentuan);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button btnKembali = dialog.findViewById(R.id.btn_syarat_ketentuan);

        btnKembali.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }

    private void postReg() {
        nama = edNamaL.getText().toString();
        telp = edHp.getText().toString();
        email = edEmail.getText().toString();
        nik = edNik.getText().toString();
        alamat = edAlamat.getText().toString();
        npwp = edNpwp.getText().toString();
        username = edUser.getText().toString();
        password = edPass.getText().toString();
        peran = spPeran.getSelectedItem().toString();
        if (peran.equals("Peserta")){
            role = 1;
        } else if (peran.equals("Pelelang")){
            role = 2;
        } else {
            role = 0;
        }
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        final RegisterModel registerModel = new RegisterModel(role,nama,nik,npwp,alamat,username,password,telp,email);
        Call<RegisterModel> registerModelCall;
        if (role == 1){
            registerModelCall = retrofitAPI.pesertaReg(registerModel);
        } else {
            registerModelCall = retrofitAPI.pelelangReg(registerModel);
        }

        registerModelCall.enqueue(new Callback<RegisterModel>() {
            @Override
            public void onResponse(Call<RegisterModel> call, Response<RegisterModel> response) {
                if (response.body().getStats()){
                    startActivity(new Intent(DaftarAkunActivity.this, LoginActivity.class));
                    finish();
                } else {
                    Toast.makeText(DaftarAkunActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RegisterModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
