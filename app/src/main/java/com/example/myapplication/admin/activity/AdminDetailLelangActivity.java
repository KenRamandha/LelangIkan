package com.example.myapplication.admin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.admin.adapter.AdapterCardLelangAdmin;
import com.example.myapplication.admin.adapter.AdapterPenawaranLelang;
import com.example.myapplication.model.admin.AdminPenawaranModel;
import com.example.myapplication.model.admin.KatalogLelangAdminModel;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AdminDetailLelangActivity extends AppCompatActivity{

    private ImageButton btnBack;
    private TextView tvHargaAwal, tvPelelang, tvIkan, tvstatus, tvKeterangan, tvBayarNow, tvJam, tvMenit, tvDetik, tvHari, tvJumlah, tvtanggal, tvtgl_selesai,tvharga_minimal_diterima, tvincremental_value;
    private ImageView ivImage1, ivImage2, ivImage3, ivImage4;

    private Handler handler;
    private Runnable runnable;

    private ArrayList<AdminPenawaranModel> penawaranAdminModelArrayList;

    String lelang_id, status_pemeriksaan, image1, image2, image3, image4, judul, pelelang, keterangan, waktu, stats, harga, bayarsekarang, jumlah, tgl_mulai, tgl_selesai, incremental_value, harga_minimal_diterima;
    Integer status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_detail_lelang);

        btnBack = findViewById(R.id.btn_back_detail_lelang_admin);

//        Tambahan
        tvharga_minimal_diterima = findViewById(R.id.tv_hargaminditerima_detail_lelang_admin);
        tvincremental_value = findViewById(R.id.tv_kelipatanharga_detail_lelang_admin);
//       END Tambahan
        tvJumlah = findViewById(R.id.tv_jumlah_detail_lelang_admin);
        tvHargaAwal = findViewById(R.id.tv_penawaran_detail_lelang_admin);
        tvIkan = findViewById(R.id.tv_nama_detail_lelang_admin);
        tvPelelang = findViewById(R.id.tv_pelelang_detail_lelang_admin);
        tvKeterangan = findViewById(R.id.tv_keterangan_detail_lelang_admin);
        tvBayarNow = findViewById(R.id.tv_beliskg_detail_lelang_admin);
        tvJam = findViewById(R.id.tv_jam_detail_lelang_admin);
        tvMenit = findViewById(R.id.tv_menit_detail_lelang_admin);
        tvDetik = findViewById(R.id.tv_detik_detail_lelang_admin);
        tvHari = findViewById(R.id.tv_hari_detail_lelang_admin);
        tvstatus = findViewById(R.id.tv_status_detail_lelang_admin);
        tvtanggal = findViewById(R.id.tv_tanggal_detail_lelang_admin);
        // tambahan
        tvtgl_selesai = findViewById(R.id.tv_tanggalselesai_detail_lelang_admin);
        // END Tambahan
        ivImage1 = findViewById(R.id.iv_image1_detail_lelang_admin);
        ivImage2 = findViewById(R.id.iv_image2_detail_lelang_admin);
        ivImage3 = findViewById(R.id.iv_image3_detail_lelang_admin);
        ivImage4 = findViewById(R.id.iv_image4_detail_lelang_admin);

        btnBack.setOnClickListener(view ->{
            onBackPressed();
        });

//        String stringVariableName = extras.getString("StringVariableName");
//        int intVariableName = Integer.parseInt(stringVariableName);

        Intent intent = getIntent();
        this.image1 = intent.getStringExtra("image1");
        this.image2 = intent.getStringExtra("image2");
        this.image3 = intent.getStringExtra("image3");
        this.image4 = intent.getStringExtra("image4");
        this.judul = intent.getStringExtra("produk");
        this.waktu = intent.getStringExtra("waktu_selesai");
        this.harga = intent.getStringExtra("harga_awal");
        this.pelelang = intent.getStringExtra("nama");
        this.keterangan = intent.getStringExtra("desktipsi_produk");
        this.bayarsekarang = intent.getStringExtra("bayar_sekarang");
        this.jumlah = intent.getStringExtra("jumlah");
        this.tgl_mulai = intent.getStringExtra("tgl_mulai");
        // Tambahan
        this.tgl_selesai = intent.getStringExtra("tgl_selesai");
        // END Tambahan
        this.status = intent.getIntExtra("status",0);
        // Tambahan
        this.incremental_value = intent.getStringExtra("incremental_value");
        this.harga_minimal_diterima = intent.getStringExtra("harga_minimal_diterima");
        this.lelang_id = intent.getStringExtra("lelang_id");
        // END Tambahan




//        status =response.body().getStatus();
        tvIkan.setText(judul);
        tvPelelang.setText(pelelang);
        tvKeterangan.setText(keterangan);
        tvJumlah.setText(jumlah + " (Kg)");
        tvtanggal.setText(tgl_mulai);
        tvtgl_selesai.setText(tgl_selesai);
//      tvstatus.setText(status);

//        tvJumlah.setText("Rp. " + jumlah);

        if(status == 1) {
            status_pemeriksaan = "Telah Diperiksa";
        } else {
            status_pemeriksaan = "Belum Diperiksa";
        }

        tvstatus.setText(status_pemeriksaan);
        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "HH:mm:ss";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String result = null;

        try {
            date = inputFormat.parse(waktu);
            result = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        countDownLelang();


//        Locale localeID = new Locale("in", "ID");
//        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
//        tvHargaAwal.setText(formatRupiah.format((double)harga));


//        Locale localeID = new Locale("in", "ID");
//        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
//        tvHargaAwal.setText(formatRupiah.format(Integer.parseInt(harga)));



        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        tvHargaAwal.setText(decimalFormat.format(Integer.parseInt(harga)));
        tvBayarNow.setText(decimalFormat.format(Integer.parseInt(bayarsekarang)));


        // Tambahan
        tvharga_minimal_diterima.setText(decimalFormat.format(Integer.parseInt(harga_minimal_diterima)));
        tvincremental_value.setText(decimalFormat.format(Integer.parseInt(incremental_value)));
        // END Tambahan



        // OPSI TRUE Bilion
//        tvHargaAwal.setText(harga);
//        tvBayarNow.setText(bayarsekarang);
//        tvharga_minimal_diterima.setText(harga_minimal_diterima);
//        tvincremental_value.setText(incremental_value);





        // -------------------------------> OLD <----------------------------///
        //        tvHargaAwal.setText("Rp. " +harga);
        //        tvBayarNow.setText("Rp. " +bayarsekarang);

        Glide.with(AdminDetailLelangActivity.this)
                .load(image1)
                .into(ivImage1);
        Glide.with(AdminDetailLelangActivity.this)
                .load(image2)
                .into(ivImage2);
        Glide.with(AdminDetailLelangActivity.this)
                .load(image3)
                .into(ivImage3);
        Glide.with(AdminDetailLelangActivity.this)
                .load(image4)
                .into(ivImage4);



        findViewById(R.id.btn_penawaran).setOnClickListener(view -> {
            startActivity(new Intent(this, AdminPenawaranLelangActivity.class).putExtra("lelang_id", lelang_id));
        });
    }



    private void countDownLelang() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1);
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    Date futureDate = dateFormat.parse(waktu);

                    Date currentDate = new Date();
                    if (!currentDate.after(futureDate)) {
                        long diff = futureDate.getTime() - currentDate.getTime();
                        long days = diff / (24 * 60 * 60 * 1000);
                        diff -= days * (24 * 60 * 60 * 1000);
                        long hours = diff / (60 * 60 * 1000);
                        diff -= hours * (60 * 60 * 1000);
                        long minutes = diff / (60 * 1000);
                        diff -= minutes * (60 * 1000);
                        long seconds = diff / 1000;
                        tvHari.setText("" + String.format("%02d", days));
                        tvJam.setText("" + String.format("%02d", hours));
                        tvMenit.setText("" + String.format("%02d", minutes));
                        tvDetik.setText("" + String.format("%02d", seconds));
                    } else {

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 1 * 1);
    }
//
////    @Override
////    public void onClick(View view, int position) {
////
////    }
//
//
//    @Override
//    public void onClick(View view, int position) {
//        final AdminPenawaranModel AdminPenawaranModel = penawaranAdminModelArrayList.get(position);
//        switch (view.getId()) {
//            default:
//                Intent intent = new Intent(AdminDetailLelangActivity.this, AdminDetailLelangActivity.class);
//                // Tambahan
//                intent.putExtra("nama", AdminPenawaranModel.getNama());
//                intent.putExtra("harga_tawar", AdminPenawaranModel.getHarga_tawar());
//                intent.putExtra("tgl_bid", AdminPenawaranModel.getTgl_bid());
//                intent.putExtra("lelang_id", AdminPenawaranModel.getLelang_id());
//
//                startActivity(intent);
//                break;
//        }
//    }

}
