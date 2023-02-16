package com.example.myapplication.admin.section;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.admin.fragment.FragmentPanitiaAdmin;
import com.example.myapplication.admin.fragment.FragmentPelelangAdmin;
import com.example.myapplication.admin.fragment.FragmentPesertaAdmin;

public class PagerAdapterPeranAdmin extends FragmentStateAdapter {

    public PagerAdapterPeranAdmin(AppCompatActivity activity) {
        super(activity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragmentPeran = null;
        switch (position){
            case 0:
                fragmentPeran= new FragmentPelelangAdmin();
                break;
            case 1:
                fragmentPeran = new FragmentPesertaAdmin();
                break;
            case 2:
                fragmentPeran = new FragmentPanitiaAdmin();
                break;
        }
        return fragmentPeran;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
