package com.example.myapplication.model.pelelang;

import com.google.gson.annotations.SerializedName;

public class KatalogPelelangModel {
    @SerializedName("lelang_id")
    private String lelang_id;

    @SerializedName("pelelang_id")
    private String pelelang_id;

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
    private int jumlah;

    public KatalogPelelangModel(String produk, String image1, String harga_awal, String tgl_mulai) {
        this.produk = produk;
        this.image1 = image1;
        this.harga_awal = harga_awal;
        this.tgl_mulai = tgl_mulai;
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
        return image1;
    }

    public String getImage2() {
        return image2;
    }

    public String getImage3() {
        return image3;
    }

    public String getImage4() {
        return image4;
    }

    public String getHarga_awal() {
        return harga_awal;
    }

    public String getHarga_minimal_diterima() {return harga_minimal_diterima;    }

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

    public int getJumlah() {
        return jumlah;
    }
}
