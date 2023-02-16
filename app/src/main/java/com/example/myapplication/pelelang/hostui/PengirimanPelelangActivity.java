package com.example.myapplication.pelelang.hostui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.R;
import com.example.myapplication.pelelang.adapter.SectionAdapterPengirimanPelelang;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class PengirimanPelelangActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengiriman_pelelang);

        ImageButton btnBack = findViewById(R.id.btn_kembali_Pengiriman);

        btnBack.setOnClickListener(view -> {
            Intent kembali = new Intent(getApplicationContext(), PelelangActivity.class);
            startActivity(kembali);
        });

        SectionAdapterPengirimanPelelang sectionAdapterPengirimanPelelang2 = new SectionAdapterPengirimanPelelang(this);
        ViewPager2 viewPager2 = findViewById(R.id.vp_pengiriman_pelelang);
        viewPager2.setAdapter(sectionAdapterPengirimanPelelang2);
        TabLayout tabLayout = findViewById(R.id.tab_pengiriman_pelelang);

        new TabLayoutMediator(tabLayout, viewPager2,
                (tab, position) -> tab.setText(getResources().getString(TAB_TITLES[position]))
        ).attach();

        if(getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0);
        }

    }


    @StringRes
    private final int[] TAB_TITLES = new int[]{
            R.string.tab_Semua,
            R.string.tab_Terkirim,
            R.string.tab_Pengiriman,
            R.string.tab_ProsesPengiriman
    };
}
