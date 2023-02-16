package com.example.myapplication.panitia.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.myapplication.R;
import com.gkemon.XMLtoPDF.PdfGenerator;
import com.gkemon.XMLtoPDF.PdfGeneratorListener;
import com.gkemon.XMLtoPDF.model.FailureResponse;
import com.gkemon.XMLtoPDF.model.SuccessResponse;
import com.google.android.material.button.MaterialButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ActivityDetailSuratPengirimanPanitia extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ImageButton btnBack;
    private TextView tvIdPeserta,tvNamaPeserta,tvIdLelang,tvIdPelelang,tvIdPengiriman,tvNoHp,tvNamaKendaraan,tvNoKendaraan,tvTelp,tvProvinsi,tvKota,tvKecamatan,tvKelurahan,tvAlamat;
    private Spinner spStatus;
    private MaterialButton btnCetak;

    //surat perintah
    private static final String TAG = "Invoice";

    private TextView tvPelelang,tvKecamatanp,tvKotap,tvLelang_id,tvProduk,tvJumlah,tvPeserta,tvTgl_menang,tvStatusbyr,tvAlamatkrm,tvKelurahankrm,tvKecamatankrm,tvKotakrm,tvProvinsikr;


    String idPeserta,NamaPeserta,IdLelang,IdPelelang,IdPengirim,NoHp,
            NamaKendaraan,NoKendaraan,status1,Provinsi,Kota,Kecamatan,
            Kelurahan,Alamat,status,telp,kotap,kecamatanp,nama_pelelang,
            status_transaksi,jumlah,tgl_menang,status2,produk;

    //Coba PDF
    Bitmap bitmap, scaleBitmap;
    private SimpleDateFormat parseFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy", new Locale("id"));
    private Date date = new Date();
    private DecimalFormat decimalFormat = new DecimalFormat("#,##0");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panitia_detail_surat_pengiriman);

        btnBack =findViewById(R.id.btn_back_detail_surat_panitia);
        btnCetak = findViewById(R.id.btn_cetak_detail_surat_perintah);

        tvIdPeserta = findViewById(R.id.tv_idpeserta_detail_surat_panitia);
        tvNamaPeserta = findViewById(R.id.tv_peserta_detail_surat_panitia);
        tvIdLelang = findViewById(R.id.tv_idlelang_detail_surat_panitia);
        tvIdPelelang = findViewById(R.id.tv_idpelelang_detail_surat_panitia);
        tvIdPengiriman = findViewById(R.id.tv_idpengiriman_detail_surat_panitia);
        tvNamaKendaraan = findViewById(R.id.tv_namakendaraan_detail_surat_panitia);
        tvNoKendaraan =findViewById(R.id.tv_nokendaraan_detail_surat_panitia);
        tvNoHp = findViewById(R.id.tv_nohp_detail_surat_panitia);
        tvTelp = findViewById(R.id.tv_telp_detail_surat_panitia);
        tvProvinsi =findViewById(R.id.tv_provinsi_detail_surat_panitia);
        tvKota = findViewById(R.id.tv_kota_detail_surat_panitia);
        tvKecamatan = findViewById(R.id.tv_kecamatan_detail_surat_panitia);
        tvKelurahan = findViewById(R.id.tv_kelurahan_detail_surat_panitia);
        tvAlamat = findViewById(R.id.tv_alamat_detail_surat_panitia);
        spStatus = findViewById(R.id.spinnerStatusPengiriman);

        Intent intent = getIntent();
        this.idPeserta = intent.getStringExtra("peserta_id");
        this.NamaPeserta = intent.getStringExtra("nama_peserta");
        this.IdLelang = intent.getStringExtra("lelang_id");
        this.IdPelelang = intent.getStringExtra("pelelang_id");
        this.IdPengirim = intent.getStringExtra("pengiriman_id");
        this.NoHp = intent.getStringExtra("no_hp");
        this.NamaKendaraan = intent.getStringExtra("kendaraan");
        this.NoKendaraan = intent.getStringExtra("nopol");
        this.Provinsi = intent.getStringExtra("provinsi");
        this.Kota = intent.getStringExtra("kota");
        this.Kecamatan = intent.getStringExtra("kecamatan");
        this.Kelurahan = intent.getStringExtra("kelurahan");
        this.Alamat = intent.getStringExtra("alamat");
        this.status1 = intent.getStringExtra("status");
        this.telp = intent.getStringExtra("telp");
        this.kotap = intent.getStringExtra("kotap");
        this.kecamatanp = intent.getStringExtra("kecamatanp");
        this.nama_pelelang = intent.getStringExtra("nama_pelelang");
        this.jumlah = intent.getStringExtra("jumlah");
        this.tgl_menang = intent.getStringExtra("diumumkan");
        this.status_transaksi = intent.getStringExtra("status_transaksi");
        this.produk = intent.getStringExtra("produk");

        // surat perintah
        tvPelelang = findViewById(R.id.tv_pelelang_surat_pdf);
        tvKecamatanp = findViewById(R.id.tv_kecamatan_surat_pdf);
        tvKotap = findViewById(R.id.tv_kota_surat_pdf);

        tvLelang_id = findViewById(R.id.tv_lelangId_surat_pdf);
        tvProduk = findViewById(R.id.tv_produk_surat_pdf);
        tvPeserta = findViewById(R.id.tv_peserta_surat_pdf);
        tvTgl_menang = findViewById(R.id.tv_tanggal_surat_pdf);
        tvStatusbyr = findViewById(R.id.tv_status_surat_pdf);
        tvJumlah = findViewById(R.id.tv_jumlah_surat_pdf);
        tvAlamatkrm = findViewById(R.id.tv_alamat_kirim_surat_pdf);
        tvKelurahankrm = findViewById(R.id.tv_kelurahan_kirim_surat_pdf);
        tvKecamatankrm = findViewById(R.id.tv_kecamatan_kirim_surat_pdf);
        tvKotakrm = findViewById(R.id.tv_kota_kirim_surat_pdf);
        tvProvinsikr = findViewById(R.id.tv_provinsi_kirim_surat_pdf);

        tvPelelang.setText(nama_pelelang);
        tvKecamatanp.setText(kecamatanp);
        tvKotap.setText(kotap);
        tvLelang_id.setText(IdLelang);
        tvPeserta.setText(NamaPeserta);
        tvJumlah.setText(jumlah);
        tvProduk.setText(produk);
        tvTgl_menang.setText(tgl_menang);
        if (status_transaksi.equals("0")) {
            status2 = "Belum Dibayar";
        } else if (status_transaksi.equals("1")) {
            status2 = "Sudah Dibayar";
        }else {
            status2 = "-";
        }
        tvStatusbyr.setText(status2);
        tvAlamatkrm.setText(Alamat);
        tvKelurahankrm.setText(Kelurahan);
        tvKecamatankrm.setText(Kecamatan);
        tvKotakrm.setText(Kota);
        tvProvinsikr.setText(Provinsi);

        //end


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.status_surat, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spStatus.setAdapter(adapter);
        spStatus.setOnItemSelectedListener(this);

        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.logo_new);
        scaleBitmap = Bitmap.createScaledBitmap(bitmap, 1200, 518, false);
        //Permision
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        btnBack.setOnClickListener(view-> {
            onBackPressed();
        });

        btnCetak.setOnClickListener(view ->{
            IdLelang = tvIdLelang.getText().toString();
//            startActivity(new Intent(this, SuratPerintahPengiriman.class)
//                    .putExtra("pelelang", IdPelelang)
//                    .putExtra("kecamatan", kecamatanp)
//                    .putExtra("kota", kotap)
//                    .putExtra("lelangid",IdLelang)
//                    .putExtra("jumlah",jumlah)
//                    .putExtra("peserta",NamaPeserta)
//                    .putExtra("tglmenang",tgl_menang)
//                    .putExtra("status",status_transaksi)
//                    .putExtra("alamat",Alamat)
//                    .putExtra("kelurahan",Kelurahan)
//                    .putExtra("kecamatan",Kecamatan)
//                    .putExtra("kota",Kota)
//                    .putExtra("provinsi",Provinsi));
            PdfGenerator.getBuilder()
                    .setContext(this)
                    .fromViewSource()
                    .fromView(findViewById(R.id.print))
                    /* "fromLayoutXML()" takes array of layout resources.
                     * You can also invoke "fromLayoutXMLList()" method here which takes list of layout resources instead of array. */
                    /* It takes default page size like A4,A5. You can also set custom page size in pixel
                     * by calling ".setCustomPageSize(int widthInPX, int heightInPX)" here. */
                    .setFileName("Surat Perintah Pengiriman"+"-"+IdLelang)
                    /* It is file name */
                    .setFolderNameOrPath("surat/")
                    /* It is folder name. If you set the folder name like this pattern (FolderA/FolderB/FolderC), then
                     * FolderA creates first.Then FolderB inside FolderB and also FolderC inside the FolderB and finally
                     * the pdf file named "Test-PDF.pdf" will be store inside the FolderB. */
                    .actionAfterPDFGeneration(PdfGenerator.ActionAfterPDFGeneration.NONE)
                    /* It true then the generated pdf will be shown after generated. */
                    .build(new PdfGeneratorListener() {
                        @Override
                        public void onFailure(FailureResponse failureResponse) {
                            super.onFailure(failureResponse);
                            Log.d(TAG, "onFailure: " + failureResponse.getErrorMessage());
                            /* If pdf is not generated by an error then you will findout the reason behind it
                             * from this FailureResponse. */
                            Toast.makeText(ActivityDetailSuratPengirimanPanitia.this, "Failure : " + failureResponse.getErrorMessage(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void showLog(String log) {
                            super.showLog(log);
                            Log.d(TAG, "log: " + log);
                            /*It shows logs of events inside the pdf generation process*/
                        }

                        @Override
                        public void onStartPDFGeneration() {

                        }

                        @Override
                        public void onFinishPDFGeneration() {

                        }

                        @Override
                        public void onSuccess(SuccessResponse response) {
                            super.onSuccess(response);
                            /* If PDF is generated successfully then you will find SuccessResponse
                             * which holds the PdfDocument,File and path (where generated pdf is stored)*/
                            Toast.makeText(ActivityDetailSuratPengirimanPanitia.this, "Success: " + response.getPath(), Toast.LENGTH_LONG).show();
                            Log.d(TAG, "Success: " + response.getPath());
                        }
                    });

        });


        tvIdPeserta.setText(idPeserta);
        tvNamaPeserta.setText(NamaPeserta);
        tvIdLelang.setText(IdLelang);
        tvIdPelelang.setText(IdPelelang);
        tvIdPengiriman.setText(IdPengirim);
        tvNoHp.setText(telp);
        tvTelp.setText(NoHp);
        tvNamaKendaraan.setText(NamaKendaraan);
        tvNoKendaraan.setText(NoKendaraan);
        tvProvinsi.setText(Provinsi);
        tvKota.setText(Kota);
        tvKecamatan.setText(Kecamatan);
        tvKelurahan.setText(Kelurahan);
        tvAlamat.setText(Alamat);


        if (status1.equals("0")){
            status = "Belum di Proses";
        } else if (status1.equals("1")){
            status = "Dalam Pengiriman";
        } else if (status1.equals("2")){
            status = "Telah Sampai";
        } else {
            status = "-";
        }
        spStatus.setSelection(getPosisi(spStatus,status));

    }



    private int getPosisi(Spinner spStatus, String status) {
        for (int i = 0; i < spStatus.getCount(); i++){
            if (status.equals(spStatus.getItemAtPosition(i))){
                return i;
            }
        }
        return 0;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}