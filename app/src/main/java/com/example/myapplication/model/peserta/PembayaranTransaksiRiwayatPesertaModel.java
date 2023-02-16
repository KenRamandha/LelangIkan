package com.example.myapplication.model.peserta;


import static com.example.myapplication.util.ServerAPI.URL_GET_GAMBAR;

import com.google.gson.annotations.SerializedName;

public class PembayaranTransaksiRiwayatPesertaModel {

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

    @SerializedName("harga_awal")
    private String harga_awal;

    @SerializedName("nama")
    private String nama;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("status")
    private String status;

    @SerializedName("nama_pelelang")
    private String nama_pelelang;

    @SerializedName("no_hp")
    private String no_hp;

    @SerializedName("no_polisi")
    private String no_polisi;

    @SerializedName("tgl_pengiriman")
    private String tgl_pengiriman;

    @SerializedName("stats")
    private Boolean stats;

    @SerializedName("message")
    private String message;

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

    public String getImage1() {
        return URL_GET_GAMBAR + "produk/" + image1;
    }

    public String getTgl_selesai() {
        return tgl_selesai;
    }

    public String getHarga_awal() {
        return harga_awal;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getStatus() {
        return status;
    }

    public String getNama_pelelang() {
        return nama_pelelang;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public String getNo_polisi() {
        return no_polisi;
    }

    public String getTgl_pengiriman() {
        return tgl_pengiriman;
    }

    public Boolean getStats() {
        return stats;
    }

    public String getMessage() {
        return message;
    }
}
