package com.example.myapplication.panitia.section;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.panitia.fragment.FragmentRequestPembayaranPanitia;
import com.example.myapplication.panitia.fragment.FragmentSelesaiPembayaranPanitia;

public class SectionsAdapterPembayaranSaldo extends FragmentStateAdapter {

    public SectionsAdapterPembayaranSaldo(AppCompatActivity fragmentPembayaran){
        super(fragmentPembayaran);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position){
        Fragment fragmentpembayaran = null;
        switch (position){
            case 0:
                fragmentpembayaran = new FragmentRequestPembayaranPanitia();
                break;

            case 1:
                fragmentpembayaran = new FragmentSelesaiPembayaranPanitia();
                break;
        }
        return fragmentpembayaran;
    }
    @Override
    public int getItemCount()
    {return 2;}
}
