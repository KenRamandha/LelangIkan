package com.example.myapplication.model.pelelang;


import com.google.gson.annotations.SerializedName;

public class RiwayatPemenangModel {

    @SerializedName("pelelang_id")
    private String pelelang_id;

    @SerializedName("nama")
    private String nama;

    @SerializedName("produk")
    private String produk;

    @SerializedName("lelang_id")
    private String lelang_id;

    @SerializedName("harga_tawar")
    private String harga_tawar;

    @SerializedName("tgl_diumumkan")
    private String tgl_diumumkan;


    public String getNama() {
        return nama;
    }

    public String getProduk() {
        return produk;
    }

    public String getTgl_diumumkan() {
        return tgl_diumumkan;
    }

    public String getHargatawar() {
        return harga_tawar;
    }

    public String getLelang_id() {
        return lelang_id;
    }

    public String getPelelang_id(){return pelelang_id;}


}
