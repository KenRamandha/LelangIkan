package com.example.myapplication.panitia.section;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.panitia.fragment.FragmentPengemasanPanitia;
import com.example.myapplication.panitia.fragment.FragmentPengirimanPanitia;
import com.example.myapplication.panitia.fragment.FragmentTerkirimPanitia;

public class SectionsAdapterPengemasan extends FragmentStateAdapter {

    public SectionsAdapterPengemasan(AppCompatActivity fragmentPengemasan) {
        super(fragmentPengemasan);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position){
        Fragment fragmentpengemasan = null;
        switch (position){
            case 0:
                fragmentpengemasan = new FragmentPengemasanPanitia();
                break;

            case 1:
                fragmentpengemasan = new FragmentPengirimanPanitia();
                break;

            case 2:
                fragmentpengemasan = new FragmentTerkirimPanitia();
        }
        return fragmentpengemasan;
    }
    @Override
    public int getItemCount()
    {return 3;}
}
