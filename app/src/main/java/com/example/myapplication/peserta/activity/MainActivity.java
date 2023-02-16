package com.example.myapplication.peserta.activity;

import static com.example.myapplication.util.SharedPreferences.TAG_KODE;
import static com.example.myapplication.util.SharedPreferences.myLelang;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.intro.LoginActivity;
import com.example.myapplication.peserta.fragment.AkunFragment;
import com.example.myapplication.peserta.fragment.HomeFragment;
import com.example.myapplication.peserta.fragment.LelangFragment;
import com.example.myapplication.peserta.fragment.RiwayatFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;

    private String kode;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // kita set default nya Home Fragment
        loadFragment(new HomeFragment());
        // inisialisasi BottomNavigaionView
        bottomNavigationView = findViewById(R.id.nav_view);
        // beri listener pada saat item/menu bottomnavigation terpilih
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

    }

    private boolean loadFragment(Fragment fragment){
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                fragment = new HomeFragment();
                break;
            case R.id.navigation_lelang:
                fragment = new LelangFragment();
                break;
            case R.id.navigation_riwayat:
                fragment = new RiwayatFragment();
                break;
            case R.id.navigation_akun:
                if (kode.equals("")) {
                    startActivity(new Intent(this, LoginActivity.class));
                } else {
                    fragment = new AkunFragment();
                }
                break;
        }
        return loadFragment(fragment);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setMessage("Apakah kamu ingin keluar?")
                .setPositiveButton("Iya", (dialog, which) -> {
                    int pid = android.os.Process.myPid();
                    android.os.Process.killProcess(pid);
                })
                .setNegativeButton("Tidak", (dialog, which) -> dialog.dismiss())
                .show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        SharedPreferences main = getSharedPreferences(myLelang, Context.MODE_PRIVATE);
        kode = main.getString(TAG_KODE, "");
        loadFragment(new HomeFragment());
        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.findItem(R.id.navigation_akun);
        if (kode.equals("")) {
            menuItem.setTitle("Login");
        } else {
            menuItem.setTitle("Profile");
        }
    }
}