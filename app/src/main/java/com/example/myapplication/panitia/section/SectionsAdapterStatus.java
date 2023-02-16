package com.example.myapplication.panitia.section;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.panitia.fragment.FragmentStatusPelelangPanitia;
import com.example.myapplication.panitia.fragment.FragmentStatusPesertaPanitia;

public class SectionsAdapterStatus extends FragmentStateAdapter {

    public SectionsAdapterStatus(AppCompatActivity fragmentStatus)
    {
        super(fragmentStatus);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position){
        Fragment fragmentstatus = null;
        switch (position){
            case 0:
                fragmentstatus = new FragmentStatusPesertaPanitia();
                break;

            case 1:
                fragmentstatus = new FragmentStatusPelelangPanitia();
                break;
        }
         return fragmentstatus;
    }
    @Override
    public int getItemCount()
    {return 2;}
}
