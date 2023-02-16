package com.example.myapplication.model.peserta;


import static com.example.myapplication.util.ServerAPI.URL_GET_GAMBAR;

import com.google.gson.annotations.SerializedName;

public class PembayaranTransaksiPesertaModel {

    @SerializedName("nama_pelelang")
    private String nama_pelelang;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("telp")
    private String telp;

    @SerializedName("produk")
    private String produk;

    @SerializedName("image1")
    private String image1;

    @SerializedName("harga_awal")
    private String harga_awal;

    @SerializedName("tgl_selesai")
    private String tgl_selesai;

    @SerializedName("nama_peserta")
    private String nama_peserta;

    @SerializedName("tgl_diumumkan")
    private String tgl_diumumkan;

    @SerializedName("total_bayar")
    private String total_bayar;

    @SerializedName("status")
    private String status;

    @SerializedName("stats")
    private Boolean stats;

    @SerializedName("message")
    private String message;

    public Boolean getStats() {
        return stats;
    }

    public String getMessage() {
        return message;
    }

    public String getNama_pelelang() {
        return nama_pelelang;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getTelp() {
        return telp;
    }

    public String getProduk() {
        return produk;
    }

    public String getImage1() {
        return
                URL_GET_GAMBAR + "produk/" + image1;
    }

    public String getHarga_awal() {
        return harga_awal;
    }

    public String getTgl_selesai() {
        return tgl_selesai;
    }

    public String getNama_peserta() {
        return nama_peserta;
    }

    public String getTgl_diumumkan() {
        return tgl_diumumkan;
    }

    public String getTotal_bayar() {
        return total_bayar;
    }

    public String getStatus() {
        return status;
    }
}
