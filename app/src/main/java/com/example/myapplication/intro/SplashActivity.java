package com.example.myapplication.intro;

import static com.example.myapplication.util.SharedPreferences.TAG_PERAN;
import static com.example.myapplication.util.SharedPreferences.myLelang;
import static com.example.myapplication.util.SharedPreferences.myLogin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.admin.activity.AdminActivity;
import com.example.myapplication.panitia.activity.PanitiaActivity;
import com.example.myapplication.pelelang.hostui.PelelangActivity;
import com.example.myapplication.peserta.activity.MainActivity;

public class SplashActivity extends AppCompatActivity {

    private Animation topAnim,bottomAnim;
    private ImageView logo;
    private String peran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.acivity_splash);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.splash_top);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.splash_bottom);
        logo = findViewById(R.id.logo);
        logo.setAnimation(topAnim);

//        peran = getIntent().getStringExtra(LoginActivity.TAG_AKUN);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //setelah loading maka akan langsung berpindah ke home activity
                SharedPreferences splash = getSharedPreferences(LoginActivity.myLelang, Context.MODE_PRIVATE);
                Boolean session = splash.getBoolean(LoginActivity.session_status, false);
                SharedPreferences peran = getSharedPreferences(myLelang, Context.MODE_PRIVATE);
                String getPeran = peran.getString(TAG_PERAN, "");
                if (session && getPeran.equals("admin")){
                    startActivity(new Intent(SplashActivity.this, AdminActivity.class));
                    finish();
                } else if (session && getPeran.equals("pelelang")){
                    startActivity(new Intent(SplashActivity.this, PelelangActivity.class));
                    finish();
                } else if (session && getPeran.equals("panitia")){
                    startActivity(new Intent(SplashActivity.this, PanitiaActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }
                Log.d("peran", getPeran);
                Log.d("session", String.valueOf(session));

            }
        },3000);
    }
}
