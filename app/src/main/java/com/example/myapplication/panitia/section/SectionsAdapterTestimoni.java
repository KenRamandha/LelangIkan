package com.example.myapplication.panitia.section;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.panitia.fragment.FragmentTestimoniPanitia;

public class SectionsAdapterTestimoni extends FragmentStateAdapter {

    public SectionsAdapterTestimoni (AppCompatActivity fragmentTesti){
        super(fragmentTesti);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position){
        Fragment fragmenttestimoni = null;
        switch (position){
            case 0:
                fragmenttestimoni = new FragmentTestimoniPanitia();
                break;
        }
        return fragmenttestimoni;
    }
    @Override
    public int getItemCount()
    {return 1;}
}
