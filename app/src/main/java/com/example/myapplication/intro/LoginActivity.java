package com.example.myapplication.intro;

import static com.example.myapplication.util.SharedPreferences.TAG_INGAT;
import static com.example.myapplication.util.SharedPreferences.TAG_PASSWORD;
import static com.example.myapplication.util.SharedPreferences.TAG_PERAN;
import static com.example.myapplication.util.SharedPreferences.myLogin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.admin.activity.AdminActivity;
import com.example.myapplication.model.LoginModel;
import com.example.myapplication.panitia.activity.PanitiaActivity;
import com.example.myapplication.pelelang.hostui.PelelangActivity;
import com.example.myapplication.peserta.activity.BayarSekarangActivity;
import com.example.myapplication.peserta.activity.MainActivity;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    private Spinner spPeran;
    private Button btnMasuk,btnDaftar;
    private TextInputEditText edUser, edPass;
    private CheckBox checkBox;
    String username,password, nama, kode, prnLelang, stats;

    public final static String TAG_USERNAME = "username";
    public final static String TAG_KODE = "kode";
    public final static String TAG_STATS = "stats";
    public final static String TAG_KOTA = "kota";
    public final static String TAG_PROV = "prov";
    public final static String TAG_AKUN = "akun";

    SharedPreferences sharedPreferences, saveLogin, peran;
    public static final String myLelang = "myLelang";
    public static final String session_status = "session_status";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnMasuk = findViewById(R.id.masuk);
        btnDaftar = findViewById(R.id.daftar_login);
        edUser = findViewById(R.id.edt_username);
        edPass = findViewById(R.id.edt_password);
        spPeran = findViewById(R.id.spinnerPeran);
        checkBox = findViewById(R.id.box_login);

//        prnLelang = getIntent().getStringExtra(PeranActivity.TAG_PERAN);

        sharedPreferences = getSharedPreferences(myLelang, Context.MODE_PRIVATE);
        peran = getSharedPreferences(com.example.myapplication.util.SharedPreferences.myLelang, Context.MODE_PRIVATE);
        saveLogin = getSharedPreferences(myLogin, Context.MODE_PRIVATE);
        edUser.setText(saveLogin.getString(TAG_USERNAME, ""));
        edPass.setText(saveLogin.getString(TAG_PASSWORD, ""));
        checkBox.setChecked(saveLogin.getBoolean(TAG_INGAT, false));

        String[] jenis_kelamin = new String[]{"Peserta", "Pelelang", "Panitia", "Admin"};
        ArrayAdapter<String> adapterJenis = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, jenis_kelamin);
        spPeran.setAdapter(adapterJenis);

        btnMasuk.setOnClickListener(view -> postLogin());

        btnDaftar.setOnClickListener(view -> {
            startActivity(new Intent(this, DaftarAkunActivity.class));
        });
    }

    private void postLogin() {
        username = edUser.getText().toString();
        password = edPass.getText().toString();
        if (spPeran.getSelectedItemPosition() == 0) {
            prnLelang = "peserta";
        } else if (spPeran.getSelectedItemPosition() == 1) {
            prnLelang = "pelelang";
        } else if (spPeran.getSelectedItemPosition() == 2) {
            prnLelang = "panitia";
        } else {
            prnLelang = "admin";
        }

        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        final LoginModel loginModel = new LoginModel(username, password);
        Call<LoginModel> loginModelCall = null;
        if (prnLelang.equals("peserta")){
            loginModelCall = retrofitAPI.userLogin(loginModel);
        } else if (prnLelang.equals("pelelang")){
            loginModelCall = retrofitAPI.lelangLogin(loginModel);
        }else if (prnLelang.equals("admin")){
            loginModelCall = retrofitAPI.adminLogin(loginModel);
        }else if (prnLelang.equals("panitia")) {
            loginModelCall = retrofitAPI.panitiaLogin(loginModel);
        }

        loginModelCall.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if (response.body().getStats()){
                    nama = response.body().getNama();
                    kode = response.body().getKode();
                    stats = response.body().getStatus();
                    if (stats.equals("3")){
                        Toast.makeText(LoginActivity.this, "Akun di Banned", Toast.LENGTH_LONG).show();
                    }else{
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean(session_status,true);
                        editor.putString(TAG_USERNAME, nama);
                        editor.putString(TAG_STATS, stats);
                        editor.putString(TAG_KODE, kode);
                        editor.putString(TAG_PERAN, prnLelang);
                        peran.edit().putString(TAG_PERAN, prnLelang);
                        editor.commit();
                        Intent i = null;

                        if (checkBox.isChecked()){
                            SharedPreferences.Editor edit = saveLogin.edit();
                            edit.putString(TAG_USERNAME, username);
                            edit.putString(TAG_PASSWORD, password);
                            edit.putString(PeranActivity.TAG_PERAN, String.valueOf(spPeran));
                            edit.putBoolean(TAG_INGAT, true);
                            edit.commit();
                        }else {
                            saveLogin.edit().clear().commit();
                        }

                        if (prnLelang.equals("peserta")){
                            i = new Intent(LoginActivity.this, MainActivity.class);
                        } else if (prnLelang.equals("pelelang")){
                            i = new Intent(LoginActivity.this, PelelangActivity.class);
                        }else if (prnLelang.equals("admin")){
                            i = new Intent(LoginActivity.this, AdminActivity.class);
                        }else if (prnLelang.equals("panitia")){
                            i = new Intent(LoginActivity.this, PanitiaActivity.class);
                        }

                        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(i);
                        finish();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}


