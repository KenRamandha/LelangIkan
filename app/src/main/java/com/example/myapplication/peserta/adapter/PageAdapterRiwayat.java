package com.example.myapplication.peserta.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.peserta.fragment.RiwayatGagalFragment;
import com.example.myapplication.peserta.fragment.RiwayatSuksesFragment;


public class PageAdapterRiwayat extends FragmentStateAdapter {
    public PageAdapterRiwayat(Fragment fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragmentRiwayat = null;
        switch (position){
            case 0:
                fragmentRiwayat = new RiwayatSuksesFragment();
                break;

            case 1:
                fragmentRiwayat = new RiwayatGagalFragment();
                break;
        }
        return fragmentRiwayat;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
