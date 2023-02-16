package com.example.myapplication.admin.activity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.widget.ImageButton;
        import android.widget.ImageView;
        import android.widget.TextView;

        import androidx.appcompat.app.AppCompatActivity;

        import com.bumptech.glide.Glide;
        import com.example.myapplication.R;

public class DetailPembayaranPanitia extends AppCompatActivity {
    private ImageButton btnKembali;
    private TextView tvNama,tvNominal_dibayarkan,  tvNama_panitia, tvTelp,tvEmail,tvNorekening,tvAtasnama,tvBukti_transfer,tvTotal_bayar, tvPelelang;
    private ImageView  ivBukti_transfer;

    String nama,nominal_dibayarkan,nama_panitia,telp,email,norekening,atasnama,bukti_transfer,total_bayar, pelelang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pembayaranpanitia);

        btnKembali = findViewById(R.id.btn_back_detail_peserta);

        tvNama_panitia = findViewById(R.id.tv_nama_panitia);
        tvNominal_dibayarkan = findViewById(R.id.tv_nominal_dibayarkan);
        tvNama = findViewById(R.id.tv_nama_pelelang);

        ivBukti_transfer = findViewById(R.id.iv_bukti_transfer);



        btnKembali.setOnClickListener(view -> {
            onBackPressed();
        });

        Intent intent = getIntent();
        nama = intent.getStringExtra("nama");
        nama_panitia = intent.getStringExtra("nama_panitia");
        nominal_dibayarkan= intent.getStringExtra("nominal_dibayarkan");
//        this.provinsi = intent.getStringExtra("provinsi");
//        this.kota = intent.getStringExtra("kota");
//        this.kecamatan = intent.getStringExtra("kecamatan");
//        this.alamat = intent.getStringExtra("alamat");
        telp =intent.getStringExtra("telp");
        email = intent.getStringExtra("email");
        bukti_transfer = intent.getStringExtra("bukti_transfer");



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
        tvNama_panitia.setText(nama_panitia);
        //tvJeniskel.setText(jeniskel);
        tvNominal_dibayarkan.setText(nominal_dibayarkan);
//        tvTelp.setText(telp);

        Glide.with(DetailPembayaranPanitia.this).load(bukti_transfer).into(ivBukti_transfer);
    }
}
