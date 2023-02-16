package com.example.myapplication.panitia.activity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.R;
import com.example.myapplication.panitia.section.SectionsAdapterStatus;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class StatusPanitiaActivity extends AppCompatActivity {
    private ImageButton btnBack;


    @StringRes
    private final int[]TabStatus = new int[]{
            R.string.tab_peserta,
            R.string.tab_pelelang
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_panitia);

        btnBack = findViewById(R.id.btn_kembali_status);
        btnBack.setOnClickListener(view -> {
            onBackPressed();
        });

        SectionsAdapterStatus sectionsStatus = new SectionsAdapterStatus(this);
        ViewPager2 viewPagerDetail = findViewById(R.id.vp_status);
        viewPagerDetail.setAdapter(sectionsStatus);
        TabLayout tabsDetail =findViewById(R.id.tab_status_panitia);
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