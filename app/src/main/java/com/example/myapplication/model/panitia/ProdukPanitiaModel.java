package com.example.myapplication.model.panitia;

import static com.example.myapplication.util.ServerAPI.URL_GET_GAMBAR;

import com.google.gson.annotations.SerializedName;

public class ProdukPanitiaModel {
    @SerializedName("lelang_id")
    private String lelang_id;

    @SerializedName("pelelang_id")
    private String pelelang_id;

    @SerializedName("nama")
    private String nama;

    @SerializedName("produk")
    private String produk;

    @SerializedName("deskripsi_produk")
    private String deskripsi_produk;

    @SerializedName("image1")
    private String image1;

    @SerializedName("image2")
    private String image2;

    @SerializedName("image3")
    private String image3;

    @SerializedName("image4")
    private String image4;

    @SerializedName("harga_awal")
    private String harga_awal;

    @SerializedName("harga_minimal_diterima")
    private String harga_minimal_diterima;

    @SerializedName("incremental_value")
    private String incremental_value;

    @SerializedName("tgl_mulai")
    private String tgl_mulai;

    @SerializedName("tgl_selesai")
    private String tgl_selesai;

    @SerializedName("harga_beli_sekarang")
    private String harga_beli_sekarang;

    @SerializedName("metode_bayar")
    private String metode_bayar;

    @SerializedName("status")
    private String status;

    @SerializedName("panitia_id")
    private String panitia_id;

    @SerializedName("keterangan")
    private String keterangan;

    @SerializedName("jumlah")
    private String jumlah;

    @SerializedName("stats")
    private Boolean stats;

    @SerializedName("message")
    private String message;


    public ProdukPanitiaModel(String lelang_id, String status, String panitia_id) {
        this.lelang_id = lelang_id;
        this.status = status;
        this.panitia_id = panitia_id;
    }

    public String getLelang_id() {
        return lelang_id;
    }

    public String getPelelang_id() {
        return pelelang_id;
    }

    public String getProduk() {
        return produk;
    }

    public String getDeskripsi_produk() {
        return deskripsi_produk;
    }

    public String getImage1() {
        return URL_GET_GAMBAR + "produk/" + image1;
    }

    public String getImage2() {
        return URL_GET_GAMBAR + "produk/" + image2;
    }

    public String getImage3() {
        return URL_GET_GAMBAR + "produk/" + image3;
    }

    public String getImage4() {
        return URL_GET_GAMBAR + "produk/" + image4;
    }

    public String getHarga_awal() {
        return harga_awal;
    }

    public String getHarga_minimal_diterima() {
        return harga_minimal_diterima;
    }

    public String getIncremental_value() {
        return incremental_value;
    }

    public String getTgl_mulai() {
        return tgl_mulai;
    }

    public String getTgl_selesai() {
        return tgl_selesai;
    }

    public String getHarga_beli_sekarang() {
        return harga_beli_sekarang;
    }

    public String getMetode_bayar() {
        return metode_bayar;
    }

    public String getStatus() {
        return status;
    }

    public String getPanitia_id() {
        return panitia_id;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public String getJumlah() {
        return jumlah;
    }

    public String getNama() {
        return nama;
    }

    public Boolean getStats() {
        return stats;
    }

    public String getMessage() {
        return message;
    }
}