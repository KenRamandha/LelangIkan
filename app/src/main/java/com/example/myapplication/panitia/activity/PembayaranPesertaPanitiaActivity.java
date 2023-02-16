package com.example.myapplication.panitia.activity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageButton;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.R;
import com.example.myapplication.panitia.section.SectionsAdapterPembayaranPeserta;
import com.example.myapplication.panitia.section.SectionsAdapterStatus;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class PembayaranPesertaPanitiaActivity extends AppCompatActivity {
    private ImageButton btnBack;


    @StringRes
    private final int[]TabStatus = new int[]{
            R.string.tab_deposit,
            R.string.tab_pemenang
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panitia_bayar_peserta);

        btnBack = findViewById(R.id.btn_kembali_bayar_peserta);
        btnBack.setOnClickListener(view -> onBackPressed());

        SectionsAdapterPembayaranPeserta sectionsStatus = new SectionsAdapterPembayaranPeserta(this);
        ViewPager2 viewPagerDetail = findViewById(R.id.vp_bayar_peserta);
        viewPagerDetail.setAdapter(sectionsStatus);
        TabLayout tabsDetail =findViewById(R.id.tab_bayar_peserta);
        new TabLayoutMediator(tabsDetail, viewPagerDetail,
                (tab, position) -> tab.setText(getResources().getString(TabStatus[position]))
        ).attach();

        if (getSupportActionBar() != null){
            getSupportActionBar().setElevation(0);
        }

        Handler handler = new Handler();

        handler.postDelayed(runnable, 1000);
    }

    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {

        }
    };

    protected void onPause() {
        super.onPause();
        finish();
    }
}