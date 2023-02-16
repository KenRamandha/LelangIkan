package com.example.myapplication.peserta.section;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.peserta.fragment.FragmentDetailPeraturan;
import com.example.myapplication.peserta.fragment.FragmentDetailRiwayat;
import com.example.myapplication.peserta.fragment.FragmentDetailTestimoni;


public class SectionsPagerAdapter extends FragmentStateAdapter {

    public SectionsPagerAdapter(AppCompatActivity activity) {
        super(activity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragmentDetail = null;
        switch (position){
            case 0:
                fragmentDetail = new FragmentDetailPeraturan();
                break;

            case 1:
                fragmentDetail = new FragmentDetailRiwayat();
                break;

            case 2:
                fragmentDetail = new FragmentDetailTestimoni();
                break;
        }
        return fragmentDetail;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
