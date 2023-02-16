package com.example.myapplication.panitia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.gkemon.XMLtoPDF.PdfGenerator;
import com.gkemon.XMLtoPDF.PdfGeneratorListener;
import com.gkemon.XMLtoPDF.model.FailureResponse;
import com.gkemon.XMLtoPDF.model.SuccessResponse;

public class SuratPerintahPengiriman extends AppCompatActivity {

    //
    private static final String TAG = "Invoice";

    private TextView tvPelelang,tvKecamatanp,tvKotap,tvLelang_id,tvProduk,tvJumlah,tvPeserta,tvTgl_menang,tvStatusbyr,tvAlamatkrm,tvKelurahankrm,tvKecamatankrm,tvKotakrm,tvProvinsikr;
    String pelelang,kecamatanp,kotap,lelang_id,produk,jumlah,peserta,tgl_menang,statusbyr,alamatkrm,kelurahankrm,kecamatankrm,kotakrm,provinsikr,statusbyr1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdf_surat_perintah_pengiriman);

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

        Intent inten = getIntent();
        this.pelelang = inten.getStringExtra("pelelang");
        this.kecamatanp = inten.getStringExtra("kecamatan");
        this.kotap = inten.getStringExtra("kota");
        this.lelang_id = inten.getStringExtra("lelangid");
        this.jumlah = inten.getStringExtra("jumlah");
        this.tgl_menang = inten.getStringExtra("tglmenang");
        this.statusbyr = inten.getStringExtra("status");
        this.alamatkrm = inten.getStringExtra("alamat");
        this.kelurahankrm = inten.getStringExtra("kelurahan");
        this.kecamatankrm = inten.getStringExtra("kecamatan");
        this.kotakrm = inten.getStringExtra("kota");
        this.provinsikr = inten.getStringExtra("provinsi");

        tvPelelang.setText(pelelang);
        tvKecamatanp.setText(kecamatanp);
        tvKotap.setText(kotap);
        tvLelang_id.setText(lelang_id);
        tvPeserta.setText(peserta);
        tvJumlah.setText(jumlah);
        tvProduk.setText(produk);
        tvTgl_menang.setText(tgl_menang);
        if (statusbyr.equals("0")) {
            statusbyr1 = "Belum Dibayar";
        } else if (statusbyr.equals("1")) {
            statusbyr1 = "Sudah Dibayar";
        }else {
            statusbyr1 = "-";
        }
        tvAlamatkrm.setText(alamatkrm);
        tvKelurahankrm.setText(kelurahankrm);
        tvKecamatankrm.setText(kecamatankrm);
        tvKotakrm.setText(kotakrm);
        tvProvinsikr.setText(provinsikr);

        //
        findViewById(R.id.printttt).setOnClickListener(v -> {
            PdfGenerator.getBuilder()
                    .setContext(this)
                    .fromViewSource()
                    .fromView(findViewById(R.id.printttt))
                    /* "fromLayoutXML()" takes array of layout resources.
                     * You can also invoke "fromLayoutXMLList()" method here which takes list of layout resources instead of array. */
                    /* It takes default page size like A4,A5. You can also set custom page size in pixel
                     * by calling ".setCustomPageSize(int widthInPX, int heightInPX)" here. */
                    .setFileName("NYOBAAA")
                    /* It is file name */
                    .setFolderNameOrPath("invoice/")
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
                            Toast.makeText(SuratPerintahPengiriman.this, "Failure : " + failureResponse.getErrorMessage(), Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(SuratPerintahPengiriman.this, "Success: " + response.getPath(), Toast.LENGTH_LONG).show();
                            Log.d(TAG, "Success: " + response.getPath());
                        }
                    });
        });
    }
}
