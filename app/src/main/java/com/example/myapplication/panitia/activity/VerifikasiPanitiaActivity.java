package com.example.myapplication.panitia.activity;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.R;
import com.example.myapplication.panitia.section.SectionsAdapterVerifikasi;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class VerifikasiPanitiaActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;

    private ImageButton btnBack;

    @StringRes
    private final int[] TabVerifikasi = new int[]{
            R.string.tab_list,
            R.string.tab_terverifikasi
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifikasi_panitia);

        btnBack = findViewById(R.id.btn_kembali_verifikasi);

        btnBack.setOnClickListener(view -> {
            onBackPressed();
        });

        SectionsAdapterVerifikasi sectionsSectionsPanitia = new SectionsAdapterVerifikasi(this);
        ViewPager2 viewPagerDetail = findViewById(R.id.vp_verifikasi);
        viewPagerDetail.setAdapter(sectionsSectionsPanitia);
        TabLayout tabsDetail =findViewById(R.id.tab_verifikasi_panitia);
        new TabLayoutMediator(tabsDetail, viewPagerDetail,
                (tab, position) -> tab.setText(getResources().getString(TabVerifikasi[position]))
        ).attach();

        if (getSupportActionBar() != null){
            getSupportActionBar().setElevation(0);
        }
    }
}