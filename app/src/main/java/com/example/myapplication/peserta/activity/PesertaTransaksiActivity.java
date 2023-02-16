package com.example.myapplication.peserta.activity;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.R;
import com.example.myapplication.peserta.section.PagerAdapterTransaksiPeserta;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class PesertaTransaksiActivity extends AppCompatActivity {
    private ImageButton btnKembali;

    @StringRes
    private final int[] TAB_TITLES = new int[]{
            R.string.tab_pembayaran_peserta,
            R.string.tab_riwayat_peserta
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peserta_transaksi);

        btnKembali = findViewById(R.id.btn_kembali_transaksi_peserta);

        btnKembali.setOnClickListener(view->{
            onBackPressed();
        });

        PagerAdapterTransaksiPeserta pagerAdapterTransaksiPeserta = new PagerAdapterTransaksiPeserta(this);
        ViewPager2 viewPagerPeran = findViewById(R.id.vp_transaksi_peserta);
        viewPagerPeran.setAdapter(pagerAdapterTransaksiPeserta);
        TabLayout tabsPeran =findViewById(R.id.tab_transaksi);
        new TabLayoutMediator(tabsPeran, viewPagerPeran,
                (tab, position) -> tab.setText(getResources().getString(TAB_TITLES[position]))
        ).attach();
        if (getSupportActionBar() != null){
            getSupportActionBar().setElevation(0);
        }
    }
}
