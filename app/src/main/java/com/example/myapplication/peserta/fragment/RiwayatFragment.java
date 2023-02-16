package com.example.myapplication.peserta.fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.R;
import com.example.myapplication.peserta.adapter.PageAdapterRiwayat;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class RiwayatFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager2 viewPager;

    @StringRes
    private final int[] TAB_TITLES = new int[]{
            R.string.tab_riwayat_sukses,
            R.string.tab_riwayat_gagal
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_riwayat, container, false);


        tabLayout = view.findViewById(R.id.tab_layout_riwayat);
        viewPager = view.findViewById(R.id.view_pager_riwayat);


        PageAdapterRiwayat pageAdapterRiwayat = new PageAdapterRiwayat(this);
        ViewPager2 viewPagerRiwayat = view.findViewById(R.id.view_pager_riwayat);
        viewPagerRiwayat.setAdapter(pageAdapterRiwayat);
        TabLayout tabsRiwayat =view.findViewById(R.id.tab_layout_riwayat);
        new TabLayoutMediator(tabsRiwayat, viewPagerRiwayat,
                (tab, position) -> tab.setText(getResources().getString(TAB_TITLES[position]))
        ).attach();
        return view;
    }

}