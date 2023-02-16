package com.example.myapplication.panitia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.model.panitia.PanitiaPesertaModel;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailSuratPerintahPanitiaActivity extends AppCompatActivity {

    private ImageButton btnBack;
    private TextView tvIdPeserta,tvNamaPeserta,tvIdLelang,tvIdPengiriman,tvNoHp,tvKendaraan,tvNopol,tvNoTelp,tvProvinsi,tvKota,tvKecamatan,tvKelurahan,tvAlamat;
    private Spinner spStatus;

    String idPeserta,namaPeserta,idLelang,idPelelang,idPengiriman,noHp,kendaraan,nopol,noTelp,provinsi,kota,kecamatan,kelurahan,alamat,status;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panitia_detail_surat_pengiriman);

        btnBack = findViewById(R.id.btn_back_detail_surat_panitia);
        tvIdPeserta = findViewById(R.id.tv_idpeserta_detail_surat_panitia);
        tvNamaPeserta = findViewById(R.id.tv_peserta_detail_surat_panitia);
        tvIdLelang = findViewById(R.id.tv_idlelang_detail_surat_panitia);
        tvIdPengiriman = findViewById(R.id.tv_idpengiriman_detail_surat_panitia);
        tvNoHp = findViewById(R.id.tv_nohp_detail_surat_panitia);
        tvKendaraan = findViewById(R.id.tv_namakendaraan_detail_surat_panitia);
        tvNopol = findViewById(R.id.tv_nokendaraan_detail_surat_panitia);
        tvNoTelp = findViewById(R.id.tv_telp_detail_surat_panitia);
        tvProvinsi = findViewById(R.id.tv_provinsi_detail_surat_panitia);
        tvKota = findViewById(R.id.tv_kota_detail_surat_panitia);
        tvKecamatan = findViewById(R.id.tv_kecamatan_detail_surat_panitia);
        tvKelurahan = findViewById(R.id.tv_kelurahan_detail_surat_panitia);
        tvAlamat = findViewById(R.id.tv_alamat_detail_surat_panitia);

        spStatus = findViewById(R.id.spinnerStatusPengiriman);



    }
}
