package com.example.myapplication.peserta.activity;

import static com.example.myapplication.util.SharedPreferences.TAG_KODE;
import static com.example.myapplication.util.SharedPreferences.TAG_USERNAME;
import static com.example.myapplication.util.SharedPreferences.myLelang;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.intro.LoginActivity;
import com.example.myapplication.peserta.fragment.HomeFragment;

public class TopUpActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private Button btnPembayaran;
    private EditText etTopUp;
    private LinearLayout linearLayout, linearLayout1;

    private String path;

    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topup);

        btnBack = findViewById(R.id.btn_back_topup);
        btnPembayaran = findViewById(R.id.btn_pilih_pembayaran);
        etTopUp = findViewById(R.id.et_top_up);
        linearLayout = findViewById(R.id.ln_topup_peserta);
        linearLayout1 = findViewById(R.id.ln_belum_login_topup);

        btnBack.setOnClickListener(view -> {
            onBackPressed();
        });
        btnPembayaran.setOnClickListener(view -> {
            String str = etTopUp.getText().toString();
            Intent intent = new Intent(getApplicationContext(), MetodBayarActivity.class);
            intent.putExtra("jumlah",str);
            startActivity(intent);
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        sharedPreferences = getSharedPreferences(LoginActivity.myLelang, Context.MODE_PRIVATE);
        path = sharedPreferences.getString(TAG_KODE, "");
        if (path.equals("")) {
            linearLayout.setVisibility(View.GONE);
            linearLayout1.setVisibility(View.VISIBLE);
        } else {
            linearLayout.setVisibility(View.VISIBLE);
        }
    }
}
