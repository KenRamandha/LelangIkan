package com.example.myapplication.pelelang.hostui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.R;
import com.example.myapplication.pelelang.sectionpelelang.SectionsAdapterDetailLelangPelelang;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class DetailLelangPelelang extends AppCompatActivity {
    private ImageButton btnBackDetailPelelang;

    @StringRes
    private final int[] TAB_DETAILPELELANG = new int[]{
            R.string.tab_peraturan,
            R.string.tab_penilaian
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_lelang_pelelang);

        btnBackDetailPelelang = findViewById(R.id.btn_back_DetailPesananPelelang);
        btnBackDetailPelelang.setOnClickListener(view -> {
            onBackPressed();
        });

        SectionsAdapterDetailLelangPelelang sectionsAdapterDetailLelangPelelang = new SectionsAdapterDetailLelangPelelang(this);
        ViewPager2 viewPagerDetailPelelang = findViewById(R.id.view_pager_detailPesananPlelang);
        viewPagerDetailPelelang.setAdapter(sectionsAdapterDetailLelangPelelang);
        TabLayout tabLayoutDetailPesananPelelang =findViewById(R.id.tabsPesnanPelelang);
        new TabLayoutMediator(tabLayoutDetailPesananPelelang, viewPagerDetailPelelang,
                (tab, position) -> tab.setText(getResources().getString(TAB_DETAILPELELANG[position]))
        ).attach();

        if (getSupportActionBar() != null){
            getSupportActionBar().setElevation(0);
        }
    }

    public void handleSelection(View view) {
        Toast.makeText(this, "Image Is can Tapped", Toast.LENGTH_SHORT).show();
    }
}