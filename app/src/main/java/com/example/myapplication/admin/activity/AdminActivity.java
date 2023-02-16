package com.example.myapplication.admin.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.intro.LoginActivity;
import com.example.myapplication.R;
import com.example.myapplication.intro.PeranActivity;

import java.util.Calendar;

public class AdminActivity extends AppCompatActivity {
    private Button btnLihat, btnKeluar;
    private ImageView ivPemenangLelang, ivPelelangPeserta, ivPengiriman, ivPembayaran, ivAkun, ivKonfirmasi, ivPembayaranHasil, ivRiwayat, ivPanitia, ivDepositpeserta;
    private TextView greetText, tvNama,tvKonfirmasi,tvKonfirmasi1;
    private LinearLayout lnKonfirmasi,lnKonfirmasi1;

    SharedPreferences sharedPreferences;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        ivPemenangLelang = findViewById(R.id.iv_pemenang_lelang);
        ivPelelangPeserta = findViewById(R.id.iv_pelelang_peserta);
        ivPengiriman = findViewById(R.id.iv_pemesanan_pengiriman);
        ivPembayaran = findViewById(R.id.iv_pembayaran);
//        ADD
        ivPembayaranHasil = findViewById(R.id.iv_pembayaranhasil_lelang);
        ivRiwayat = findViewById(R.id.iv_riwayatlelang);

//        ivPanitia = findViewById(R.id.iv_panitia);
        ivDepositpeserta = findViewById(R.id.iv_depositpeserta);

//        END ADD
        ivAkun = findViewById(R.id.iv_akun_admin);
        btnLihat = findViewById(R.id.btn_lihat_lelang);
        btnKeluar = findViewById(R.id.btn_keluar_sementara);
        greetText = findViewById(R.id.greeting_text_admin);
        tvNama = findViewById(R.id.nama_admin);
        tvKonfirmasi = findViewById(R.id.tv_konfirmasi_penerimaan_admin);
        tvKonfirmasi1 = findViewById(R.id.tv_konfirmasi_penerimaan_admin1);
        ivKonfirmasi = findViewById(R.id.iv_konfirmasi_penerimaan_admin);
        lnKonfirmasi = findViewById(R.id.ln_konfirmasi_penerimaan_admin);
        lnKonfirmasi1 = findViewById(R.id.ln_konfirmasi_penerimaan_admin1);

        sharedPreferences = this.getSharedPreferences(LoginActivity.myLelang, Context.MODE_PRIVATE);
        username = sharedPreferences.getString(LoginActivity.TAG_USERNAME,"");
        tvNama.setText(username);

        tvKonfirmasi.setOnClickListener(view ->{
            Intent konfirmasi= new Intent(getApplicationContext(), KonfirmasiAdminActivity.class);
            startActivity(konfirmasi);
        });
        tvKonfirmasi1.setOnClickListener(view ->{
            Intent konfirmasi1= new Intent(getApplicationContext(), KonfirmasiAdminActivity.class);
            startActivity(konfirmasi1);
        });
        ivKonfirmasi.setOnClickListener(view ->{
            Intent konfirmasi2= new Intent(getApplicationContext(), KonfirmasiAdminActivity.class);
            startActivity(konfirmasi2);
        });
        lnKonfirmasi.setOnClickListener(view ->{
            Intent konfirmasi3= new Intent(getApplicationContext(), KonfirmasiAdminActivity.class);
            startActivity(konfirmasi3);
        });
        lnKonfirmasi1.setOnClickListener(view ->{
            Intent konfirmasi4= new Intent(getApplicationContext(), KonfirmasiAdminActivity.class);
            startActivity(konfirmasi4);
        });

//        ADD
        ivPembayaranHasil.setOnClickListener(view ->{
            Intent pembayaranhasillelang= new Intent(getApplicationContext(), PembayaranHasilLelangActivity.class);
            startActivity(pembayaranhasillelang);
        });

        ivRiwayat.setOnClickListener(view ->{
            Intent riwayatlelang= new Intent(getApplicationContext(), RiwayatLelangActivity.class);
            startActivity(riwayatlelang);
        });

//        ivPanitia.setOnClickListener(view ->{
//            Intent panitialelang= new Intent(getApplicationContext(), PanitiaLelangActivity.class);
//            startActivity(panitialelang);
//        });
//
        ivDepositpeserta.setOnClickListener(view ->{
            Intent depositpeserta= new Intent(getApplicationContext(), DepositLelangActivity.class);
            startActivity(depositpeserta);
        });
//        END ADD

        ivPemenangLelang.setOnClickListener(view ->{
            Intent pemenang= new Intent(getApplicationContext(), PemenangActivity.class);
            startActivity(pemenang);
        });
        ivPembayaran.setOnClickListener(view ->{
            Intent pembayaran= new Intent(getApplicationContext(), PembayaranActivity.class);
            startActivity(pembayaran);
        });
        ivPengiriman.setOnClickListener(view ->{
            Intent pengiriman= new Intent(getApplicationContext(), PengemasanActivity.class);
            startActivity(pengiriman);
        });
        ivPelelangPeserta.setOnClickListener(view ->{
            Intent peran= new Intent(getApplicationContext(), PeranAdminActivity.class);
            startActivity(peran);
        });
        btnLihat.setOnClickListener(view ->{
            Intent lihat= new Intent(getApplicationContext(), LelangActivity.class);
            startActivity(lihat);
        });
        ivAkun.setOnClickListener(view ->{
            Intent profile = new Intent(getApplicationContext(),AdminAkunActivity.class);
            startActivity(profile);
        });
        btnKeluar.setOnClickListener(view->{
            sharedPreferences.edit().clear().commit();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        });
//        new View.OnClickListener() {
//            public void onClick(View v) {
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putBoolean(LoginActivity.session_status, false);
//                editor.putString(LoginActivity.TAG_USERNAME, null);
//                editor.commit();
//                finish();
//                startActivity(new Intent(getApplicationContext(), PeranActivity.class));
//            }
//        }
        greeting();
    }

    private void greeting() {
        Calendar calendar = Calendar.getInstance();
        int timeOfDay = calendar.get(Calendar.HOUR_OF_DAY);

        if (timeOfDay >= 0 && timeOfDay < 12) {
            greetText.setText(getString(R.string.salam_pagi));
        } else if (timeOfDay >= 12 && timeOfDay < 15) {
            greetText.setText(getString(R.string.salam_siang));
        } else if (timeOfDay >= 15 && timeOfDay < 18) {
            greetText.setText(getString(R.string.salam_sore));
        } else if (timeOfDay >= 18 && timeOfDay < 24) {
            greetText.setText(getString(R.string.salam_malam));
        }
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
}
