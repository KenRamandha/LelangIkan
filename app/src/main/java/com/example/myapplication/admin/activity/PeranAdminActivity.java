package com.example.myapplication.admin.activity;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.R;

import com.example.myapplication.admin.section.PagerAdapterPeranAdmin;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class PeranAdminActivity extends AppCompatActivity {
    private ImageButton btnKembali;

    @StringRes
    private final int[] TAB_TITLES = new int[]{
            R.string.tab_pelelang,
            R.string.tab_peserta,
            R.string.tab_panitia
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_peserta_pelelang);

        btnKembali = findViewById(R.id.btn_kembali_peran_admin);

        btnKembali.setOnClickListener(view->{
            onBackPressed();
        });

        PagerAdapterPeranAdmin pagerAdapterPengirimanAdmin = new PagerAdapterPeranAdmin(this);
        ViewPager2 viewPagerPeran = findViewById(R.id.vp_peran_admin);
        viewPagerPeran.setAdapter(pagerAdapterPengirimanAdmin);
        TabLayout tabsPeran =findViewById(R.id.tab_peran_admin);
        new TabLayoutMediator(tabsPeran, viewPagerPeran,
                (tab, position) -> tab.setText(getResources().getString(TAB_TITLES[position]))
        ).attach();
        if (getSupportActionBar() != null){
            getSupportActionBar().setElevation(0);
        }
    }
}
