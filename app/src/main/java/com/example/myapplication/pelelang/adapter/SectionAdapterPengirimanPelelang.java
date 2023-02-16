package com.example.myapplication.pelelang.adapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.pelelang.fragment.PengirimanPelelangFragment;
import com.example.myapplication.pelelang.fragment.SemuaPengirimanFragment;
import com.example.myapplication.pelelang.fragment.TerkirimPengirimanFragment;

public class SectionAdapterPengirimanPelelang extends FragmentStateAdapter {

    public SectionAdapterPengirimanPelelang(AppCompatActivity activity) {
        super(activity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragmentLelang = null;
        switch (position){
            case 0:
                fragmentLelang = new SemuaPengirimanFragment();
                break;

            case 1:
                fragmentLelang = new TerkirimPengirimanFragment();
                break;

            case 2:
                fragmentLelang = new PengirimanPelelangFragment();
                break;
        }
        return fragmentLelang;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
