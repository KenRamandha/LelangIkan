package com.example.myapplication.model.panitia;


import static com.example.myapplication.util.ServerAPI.URL_GET_GAMBAR;

import com.google.gson.annotations.SerializedName;

public class PanitiaPengirimanModel {

    @SerializedName("nama_pengirim")
    private String nama_pengirim;

    @SerializedName("status_transaksi")
    private String status_transaksi;

    @SerializedName("nominal_dibayarkan")
    private String nominal_dibayarkan;

    @SerializedName("produk")
    private String produk;

    @SerializedName("image1")
    private String image1;

    @SerializedName("tgl_selesai")
    private String tgl_selesai;

    @SerializedName("nama")
    private String nama;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("tgl_diumumkan")
    private String tgl_diumumkan;

    public String getNama_pengirim() {
        return nama_pengirim;
    }

    public String getStatus_transaksi() {
        return status_transaksi;
    }

    public String getNominal_dibayarkan() {
        return nominal_dibayarkan;
    }

    public String getProduk() {
        return produk;
    }

    public String getTgl_selesai() {
        return tgl_selesai;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getTgl_diumumkan() {
        return tgl_diumumkan;
    }

    public String getImage1() {
        return URL_GET_GAMBAR + "produk/" + image1;
    }
}
