package com.example.myapplication.model.peserta;

import com.google.gson.annotations.SerializedName;

public class RiwayatLelangPesertaModel {
    @SerializedName("produk")
    private String produk;

    @SerializedName("nama_pelelang")
    private String nama_pelelang;

    @SerializedName("tgl_diumumkan")
    private String tgl_diumumkan;

    @SerializedName("nama_peserta")
    private String nama_peserta;

    @SerializedName("alamat_kirim")
    private String alamat_kirim;

    @SerializedName("jumlah")
    private String jumlah;

    @SerializedName("total_bayar")
    private String total_bayar;

    @SerializedName("telp")
    private String telp;

    @SerializedName("status")
    private String status;

    public String getProduk() {
        return produk;
    }

    public String getNama_pelelang() {
        return nama_pelelang;
    }

    public String getTgl_diumumkan() {
        return tgl_diumumkan;
    }

    public String getNama_peserta() {
        return nama_peserta;
    }

    public String getAlamat_kirim() {
        return alamat_kirim;
    }

    public String getJumlah() {
        return jumlah;
    }

    public String getTotal_bayar() {
        return total_bayar;
    }

    public String getTelp() {
        return telp;
    }

    public String getStatus() {
        return status;
    }
}
