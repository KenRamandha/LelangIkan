package com.example.myapplication.model.pelelang;

import com.google.gson.annotations.SerializedName;

public class RiwayatPenawaranModel {

    @SerializedName("pelelang_id")
    private String pelelang_id;

    @SerializedName("bid_id")
    private String bid_id;

    @SerializedName("lelang_id")
    private String lelang_id;

    @SerializedName("nama")
    private String nama;

    @SerializedName("produk")
    private String produk;

    @SerializedName("jumlah")
    private String jumlah;

    @SerializedName("harga_tawar")
    private String harga_tawar;

    public String getLelang_id() {
        return lelang_id;
    }

    public String getNama() {
        return nama;
    }

    public String getJumlah() {
        return jumlah;
    }

    public String getHarga_tawar() {return harga_tawar;}

    public String getProduk() {
        return   produk;
    }

    public String getBid_id(){return bid_id;}
    public String getPelelang_id(){return pelelang_id;}

}
