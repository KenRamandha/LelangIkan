package com.example.myapplication.model.admin;


import static com.example.myapplication.util.ServerAPI.URL_GET_GAMBAR;

import com.google.gson.annotations.SerializedName;

public class AdminKonfirmasiModel {

    @SerializedName("nama_peserta")
    private String nama_peserta;

    @SerializedName("status")
    private String status;

    @SerializedName("konfirmasi_terimaproduk")
    private String konfirmasi_terimaproduk;

    @SerializedName("peserta_id")
    private String peserta_id;

    @SerializedName("lelang_id")
    private String lelang_id;

    @SerializedName("produk")
    private String produk;

    @SerializedName("jumlah")
    private String jumlah;

    @SerializedName("testimoni_pemenang")
    private String testimoni_pemenang;

    public String getNama() {
        return nama_peserta;
    }

    public String getStatus() {
        return status;
    }

    public String getKonfirmasi_terimaproduk() {
        return konfirmasi_terimaproduk;
    }

    public String getPeserta_id() {
        return peserta_id;
    }

    public String getLelang_id() {
        return lelang_id;
    }

    public String getProduk() {
        return produk;
    }

    public String getJumlah() {
        return jumlah;
    }

    public String getTestimoni_pemenang() {
        return testimoni_pemenang;
    }

}
