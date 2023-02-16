package com.example.myapplication.admin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;

public class DetailPembayaranDeposit extends AppCompatActivity {
    private ImageButton btnKembali;
    private TextView tvNama,tv_Tgl_deposit,  tv_Deposit, tv_Bukti_pembayaran;
    private ImageView ivBukti_pembayaran;

    String nama,tgl_deposit, nominal_deposit, bukti_pembayaran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_deposit);

        btnKembali = findViewById(R.id.btn_back_detail_peserta);



        tvNama = findViewById(R.id.tv_nama);
        tv_Tgl_deposit = findViewById(R.id.tv_tgl_deposit);
        tv_Deposit = findViewById(R.id.tv_deposit);


        ivBukti_pembayaran = findViewById(R.id.ivbukti_pembayaran);



        btnKembali.setOnClickListener(view -> {
            onBackPressed();
        });

        Intent intent = getIntent();
        nama = intent.getStringExtra("nama");
        tgl_deposit = intent.getStringExtra("tgl_deposit");
        nominal_deposit= intent.getStringExtra("nominal_deposit");

        bukti_pembayaran =intent.getStringExtra("bukti_pembayaran");




        //        Mas Ken
        //        tvNama.setText(nama);
        //        if (jeniskel.equals("L")) {
        //            jenis = "Laki - Laki";
        //        } else if (jeniskel.equals("P")){
        //            jenis = "Perempuan";
        //        } else {
        //            jenis = "Lainnya";
        //        }


//        tvNama.setText(nama);
//        if ((jeniskel.equals("L")) || (jeniskel.equals("l"))) {
//            jenis = "Laki - Laki";
//        } else if ((jeniskel.equals("P")) || (jeniskel.equals("p"))) {
//            jenis = "Perempuan";
//        } else {
//            jenis = "Lainnya";
//        }

        tvNama.setText(nama);
        tv_Tgl_deposit.setText(tgl_deposit);
        tv_Deposit.setText(nominal_deposit);
        //tvJeniskel.setText(jeniskel);

//        tvTelp.setText(telp);


        Glide.with(DetailPembayaranDeposit.this).load(bukti_pembayaran).into(ivBukti_pembayaran);
    }
}
