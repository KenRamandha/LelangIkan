package com.example.myapplication.pelelang.hostui;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.pelelang.fragmentui.akun.AkunPelelangFragment;
import com.example.myapplication.pelelang.fragmentui.home.HomePelelangFragment;
import com.example.myapplication.pelelang.fragmentui.lelang.LelangPelelangFragment;
import com.example.myapplication.pelelang.fragmentui.riwayat.RiwayatPelelangFragment;
import com.example.myapplication.pelelang.fragmentui.riwayat.RiwayatPenawaranFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class PelelangActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelelang);


        // kita set default nya Home Fragment
        loadFragment(new HomePelelangFragment());
        // inisialisasi BottomNavigaionView
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view_pelelang);
        // beri listener pada saat item/menu bottomnavigation terpilih
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container_pelelang, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
//        Log.d("#DEV", getFragmentManager()..toString());
        switch (menuItem.getItemId()) {
            case R.id.navigation_home_pelelang:
                fragment = new HomePelelangFragment();
                break;
            case R.id.navigation_lelang_pelelang:
                fragment = new LelangPelelangFragment();
                break;
            case R.id.navigation_riwayat_penawaran:
                fragment = new RiwayatPenawaranFragment();
                break;
            case R.id.navigation_riwayat_pelelang:
                fragment = new RiwayatPelelangFragment();
                break;

            case R.id.navigation_akun_pelelang:
                fragment = new AkunPelelangFragment();
                break;
        }
        return loadFragment(fragment);
    }
}