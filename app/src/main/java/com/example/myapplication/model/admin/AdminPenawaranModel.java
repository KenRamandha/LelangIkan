package com.example.myapplication.model.admin;

import com.google.gson.annotations.SerializedName;

public class AdminPenawaranModel {

    @SerializedName("lelang_id")
    private String lelang_id;

    @SerializedName("nama")
    private String nama;

    @SerializedName("harga_tawar")
    private String harga_tawar;

    @SerializedName("tgl_bid")
    private String tgl_bid;

    @SerializedName("bid_id")
    private double bid_id;

    @SerializedName("peserta_id")
    private String peserta_id;

    @SerializedName("produk")
    private String produk;

    public String getLelang_id() {
        return lelang_id;
    }

    public String getBid_id() {
        return String.valueOf(bid_id);
    }

    public String getPeserta_id() {
        return peserta_id;
    }

    public String getNama() {
        return nama;
    }

    public String getTgl_bid() {
        return tgl_bid;
    }

    public String getHarga_tawar() {
        return harga_tawar;
    }

    public String getProduk() {
        return produk;
    }
}
