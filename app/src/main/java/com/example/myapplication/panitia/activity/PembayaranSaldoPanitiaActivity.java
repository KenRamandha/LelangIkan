
package com.example.myapplication.panitia.activity;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.R;
import com.example.myapplication.panitia.section.SectionsAdapterPembayaranSaldo;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class PembayaranSaldoPanitiaActivity extends AppCompatActivity {

    private ImageButton btnBack;

    @StringRes
    private final int[]TabPembayaran = new int[]{
            R.string.tab_request,
            R.string.tab_selesai
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembayaran_saldo_panitia);

        btnBack = findViewById(R.id.btn_kembali_pembayaran);
        btnBack.setOnClickListener(view -> {
            onBackPressed();
        });

        SectionsAdapterPembayaranSaldo sectionsPembayaran = new SectionsAdapterPembayaranSaldo(this);
        ViewPager2 viewPagerDetail = findViewById(R.id.vp_pembayaran);
        viewPagerDetail.setAdapter(sectionsPembayaran);
        TabLayout tabsDetail =findViewById(R.id.tab_pembayaran_panitia);
        new TabLayoutMediator(tabsDetail, viewPagerDetail,
                (tab, position) -> tab.setText(getResources().getString(TabPembayaran[position]))
        ).attach();

        if (getSupportActionBar() != null){
            getSupportActionBar().setElevation(0);
        }
    }
}