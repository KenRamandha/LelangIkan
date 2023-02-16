package com.example.myapplication.panitia.section;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.panitia.fragment.FragmentDepositPesertaPanitia;
import com.example.myapplication.panitia.fragment.FragmentPembayaranPesertaPanitia;
import com.example.myapplication.panitia.fragment.FragmentStatusPelelangPanitia;
import com.example.myapplication.panitia.fragment.FragmentStatusPesertaPanitia;

public class SectionsAdapterPembayaranPeserta extends FragmentStateAdapter {

    public SectionsAdapterPembayaranPeserta(AppCompatActivity fragmentStatus)
    {
        super(fragmentStatus);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position){
        Fragment fragmentstatus = null;
        switch (position){
            case 0:
                fragmentstatus = new FragmentDepositPesertaPanitia();
                break;

            case 1:
                fragmentstatus = new FragmentPembayaranPesertaPanitia();
                break;
        }
         return fragmentstatus;
    }
    @Override
    public int getItemCount()
    {return 2;}
}
