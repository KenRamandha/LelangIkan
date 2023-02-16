package com.example.myapplication.pelelang.sectionpelelang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.pelelang.fragment.FragmentDetailTestimoniPelelang;
import com.example.myapplication.peserta.fragment.FragmentDetailPeraturan;

public class SectionsAdapterDetailLelangPelelang extends FragmentStateAdapter {

    public SectionsAdapterDetailLelangPelelang(AppCompatActivity fragmentDetailLelangPelelang) {
        super(fragmentDetailLelangPelelang);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position){
        Fragment fragmentDetaillelangpelelang = null;
        switch (position){
            case 0:
                fragmentDetaillelangpelelang = new FragmentDetailPeraturan();
                break;

            case 1:
                fragmentDetaillelangpelelang = new FragmentDetailTestimoniPelelang();
        }
        return fragmentDetaillelangpelelang;
    }
    @Override
    public int getItemCount()
    {return 2;}
}
