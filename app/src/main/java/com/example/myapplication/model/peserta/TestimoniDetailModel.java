package com.example.myapplication.model.peserta;


import com.google.gson.annotations.SerializedName;

public class TestimoniDetailModel {
    @SerializedName("lelang_id")
    private String lelang_id;

    @SerializedName("nama")
    private String nama;

    @SerializedName("testimoni_pemenang")
    private String testimoni_pemenang;

    @SerializedName("tgl_diumumkan")
    private String tgl_diumumkan;

    public String getTgl_diumumkan() {
        return tgl_diumumkan;
    }

    public String getLelang_id() {
        return lelang_id;
    }

    public String getNama() {
        return nama;
    }

    public String getTestimoni_pemenang() {
        return testimoni_pemenang;
    }
}
