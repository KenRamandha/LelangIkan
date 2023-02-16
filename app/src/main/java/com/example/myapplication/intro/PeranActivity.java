package com.example.myapplication.intro;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.admin.activity.AdminActivity;
import com.example.myapplication.panitia.activity.PanitiaActivity;
import com.example.myapplication.pelelang.hostui.PelelangActivity;
import com.example.myapplication.peserta.activity.MainActivity;

public class PeranActivity extends AppCompatActivity {
    Button btnPeserta, btnPelelang, btnPanitia, btnAdmin;
    String spUser, NYOBAJA, prnLelang;

    public final static String TAG_USERNAME = "username";
    public final static String TAG_PERAN = "peran";

    SharedPreferences sharedPreferences;
    Boolean session = false;

    public static final String myLelang = "myLelang";
    public static final String session_status = "session_status";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_peran);

        btnPeserta = findViewById(R.id.btn_peserta);
        btnPelelang = findViewById(R.id.btn_pelelang);
        btnAdmin = findViewById(R.id.btn_admin);
        btnPanitia = findViewById(R.id.btn_panitia);

        sharedPreferences = getSharedPreferences(myLelang, Context.MODE_PRIVATE);
        session = sharedPreferences.getBoolean(session_status, false);
        spUser = sharedPreferences.getString(TAG_USERNAME,null);
        prnLelang = sharedPreferences.getString(TAG_PERAN, "");

        if (session) {
            Intent intent;
            if (prnLelang.equals("peserta")){
                intent = new Intent(PeranActivity.this, MainActivity.class);
                intent.putExtra(LoginActivity.TAG_USERNAME, spUser);
                startActivity(intent);
                finish();
            } else if (prnLelang.equals("pelelang")){
                intent = new Intent(PeranActivity.this, PelelangActivity.class);
                intent.putExtra(LoginActivity.TAG_USERNAME, spUser);
                startActivity(intent);
                finish();
            } else if (prnLelang.equals("admin")){
                intent = new Intent(PeranActivity.this, AdminActivity.class);
                intent.putExtra(LoginActivity.TAG_USERNAME, spUser);
                startActivity(intent);
                finish();
            } else if (prnLelang.equals("panitia")){
                intent = new Intent(PeranActivity.this, PanitiaActivity.class);
                intent.putExtra(LoginActivity.TAG_USERNAME, spUser);
                startActivity(intent);
                finish();
            }
        }

        NYOBAJA = String.valueOf(session);

        btnPeserta.setOnClickListener(view -> {
            if (NYOBAJA.equals("true")){
                Toast.makeText(this, "tesssssssss", Toast.LENGTH_SHORT).show();
            } else {
                Intent peserta = new Intent(getApplicationContext(), LoginActivity.class);
                peserta.putExtra(TAG_PERAN, "peserta");
                startActivity(peserta);
                finish();
            }
        });
        btnPelelang.setOnClickListener(view -> {
            if (NYOBAJA.equals("true")){
                Toast.makeText(this, "tesssssssss", Toast.LENGTH_SHORT).show();
            } else {
                Intent pelelang = new Intent(getApplicationContext(), LoginActivity.class);
                pelelang.putExtra(TAG_PERAN, "pelelang");
                startActivity(pelelang);
                finish();
            }
        });
        btnAdmin.setOnClickListener(view -> {
            if (NYOBAJA.equals("true")){
                Toast.makeText(this, "tesssssssss", Toast.LENGTH_SHORT).show();
            } else {
                Intent admin = new Intent(getApplicationContext(), LoginActivity.class);
                admin.putExtra(TAG_PERAN, "admin");
                startActivity(admin);
                finish();
            }
        });
        btnPanitia.setOnClickListener(view -> {
            if (NYOBAJA.equals("true")){
                Toast.makeText(this, "tesssssssss", Toast.LENGTH_SHORT).show();
            } else {
                Intent panitia = new Intent(getApplicationContext(), LoginActivity.class);
                panitia.putExtra(TAG_PERAN, "panitia");
                startActivity(panitia);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {

    }
}
