package com.example.myapplication.peserta.section;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.peserta.fragment.FragmentTransaksiPembayaranPeserta;
import com.example.myapplication.peserta.fragment.FragmentTransaksiRiwayatPeserta;

public class PagerAdapterTransaksiPeserta extends FragmentStateAdapter {

    public PagerAdapterTransaksiPeserta(AppCompatActivity activity) {
        super(activity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragmentPeran = null;
        switch (position){
            case 0:
                fragmentPeran= new FragmentTransaksiPembayaranPeserta();
                break;

            case 1:
                fragmentPeran = new FragmentTransaksiRiwayatPeserta();
                break;
        }
        return fragmentPeran;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
