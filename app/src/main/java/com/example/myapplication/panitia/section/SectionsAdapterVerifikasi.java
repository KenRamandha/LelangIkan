package com.example.myapplication.panitia.section;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.panitia.fragment.FragmentTerverifikasiPanitia;
import com.example.myapplication.panitia.fragment.FragmentVerifikasiPanitia;

public class SectionsAdapterVerifikasi extends FragmentStateAdapter {

    public SectionsAdapterVerifikasi(AppCompatActivity fragmentVerifikasi) {
        super(fragmentVerifikasi);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragmentVerif = null;
        switch (position){
            case 0:
                fragmentVerif = new FragmentVerifikasiPanitia();
                break;

            case 1:
                fragmentVerif = new FragmentTerverifikasiPanitia();
                break;
        }
        return fragmentVerif;
    }

    @Override
    public int getItemCount() {return 2;}
}
