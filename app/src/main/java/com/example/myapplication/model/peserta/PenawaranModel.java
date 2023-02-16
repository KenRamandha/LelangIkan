package com.example.myapplication.model.peserta;


import com.google.gson.annotations.SerializedName;

public class PenawaranModel {
    @SerializedName("lelang_id")
    private String lelang_id;

    @SerializedName("tgl_bid")
    private String tgl_bid;

    @SerializedName("harga_tawar")
    private Integer harga_tawar;

    @SerializedName("nama")
    private String nama;

    @SerializedName("harga_awal")
    private Integer harga_awal;

    @SerializedName("incremental_value")
    private Integer incremental_value;

    public String getLelang_id() {
        return lelang_id;
    }

    public String getTgl_bid() {
        return tgl_bid;
    }

    public Integer getHarga_tawar() {
        return harga_tawar;
    }

    public String getNama() {
        return nama;
    }

    public Integer getHarga_awal() {
        return harga_awal;
    }

    public Integer getIncremental_value() {
        return incremental_value;
    }
}
