package com.example.myapplication.panitia.activity;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.R;
import com.example.myapplication.panitia.section.SectionsAdapterPengemasan;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class PengemasanPanitiaActivity extends AppCompatActivity {
   private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private ImageButton btnBack;


    @StringRes
    private final int[] TabPengemasan = new int[]{
            R.string.tab_pengemasan,
            R.string.tab_pengiriman,
            R.string.tab_terkirim
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengemasan_panitia);

        btnBack = findViewById(R.id.btn_kembali_pengemasan);
        btnBack.setOnClickListener(view -> {
            onBackPressed();
        });

        SectionsAdapterPengemasan sectionsPengemasan = new SectionsAdapterPengemasan(this);
        ViewPager2 viewPagerDetail = findViewById(R.id.vp_pengemasan);
        viewPagerDetail.setAdapter(sectionsPengemasan);
        TabLayout tabsDetail =findViewById(R.id.tab_pengemasan_panitia);
        new TabLayoutMediator(tabsDetail, viewPagerDetail,
                (tab, position) -> tab.setText(getResources().getString(TabPengemasan[position]))
        ).attach();

        if (getSupportActionBar() != null){
            getSupportActionBar().setElevation(0);
        }
    }
}