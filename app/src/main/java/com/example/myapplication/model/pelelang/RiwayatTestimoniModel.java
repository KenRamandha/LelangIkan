package com.example.myapplication.model.pelelang;

import com.google.gson.annotations.SerializedName;

public class RiwayatTestimoniModel {
    @SerializedName("pelelang_id")
    private String pelelang_id;

    @SerializedName("peserta_id")
    private String peserta_id;

    @SerializedName("nama")
    private String nama;

    @SerializedName("produk")
    private String produk;

    @SerializedName("testimoni_pemenang")
    private String testimoni_pemenang;



    public String getPeserta_id() {
        return peserta_id;
    }

    public String getNama() {
        return nama;
    }

    public String getTestimoni() {
        return testimoni_pemenang;
    }

    public String getProduk() {
        return produk;
    }
    public String getPelelang_id(){return pelelang_id;}
    
}
