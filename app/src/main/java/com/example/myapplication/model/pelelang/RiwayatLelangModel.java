package com.example.myapplication.model.pelelang;

import com.google.gson.annotations.SerializedName;

public class RiwayatLelangModel {

    @SerializedName("pelelang_id")
    private String pelelang_id;

    @SerializedName("nominal_dibayarkan")
    private String nominal_dibayarkan;

    @SerializedName("nama")
    private String nama;

    @SerializedName("jumlah")
    private String jumlah;

    @SerializedName("produk")
    private String produk;

    @SerializedName("harga_awal")
    private String harga_awal;

    @SerializedName("lelang_id")
    private String lelang_id;

    @SerializedName("konfirmasi_terimaproduk")
    private Integer konfirmasi_terimaproduk;


    public  String getNominal() {return nominal_dibayarkan;}

    public String getNamaPeserta(){return nama;}

    public String getJumlahProduk(){return jumlah;}

    public String getProduk() {return produk;}

    public String getHarga_awal(){return harga_awal;}

    public String getLelang_id() {
        return lelang_id;
    }

    public  Integer getStatus() {return konfirmasi_terimaproduk;}

    public String getTotalBayar() {return nominal_dibayarkan; }

    public String getPelelang_id(){return pelelang_id;}

}
