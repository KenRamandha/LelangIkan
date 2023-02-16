package com.example.myapplication.model.peserta;

import static com.example.myapplication.util.ServerAPI.URL_GET_GAMBAR;

import com.google.gson.annotations.SerializedName;

public class TestimoniModel {
    @SerializedName("peserta_id")
    private String peserta_id;

    @SerializedName("lelang_id")
    private String lelang_id;

    @SerializedName("testimoni_pemenang")
    private String testimoni_pemenang;

    @SerializedName("total_bayar")
    private String total_bayar;

    @SerializedName("tgl_diumumkan")
    private String tgl_diumumkan;

    @SerializedName("image1")
    private String image1;
    @SerializedName("image2")
    private String image2;
    @SerializedName("image3")
    private String image3;
    @SerializedName("image4")
    private String image4;

    @SerializedName("produk")
    private String produk;

    @SerializedName("tgl_selesai")
    private String tgl_selesai;

    @SerializedName("nama")
    private String nama;

    @SerializedName("stats")
    private Boolean stats;

    @SerializedName("message")
    private String message;


    public TestimoniModel(String lelang_id, String testimoni_pemenang) {
        this.lelang_id = lelang_id;
        this.testimoni_pemenang = testimoni_pemenang;
    }

    public String getLelang_id() {
        return lelang_id;
    }
    public String getPeserta_id() {
        return peserta_id;
    }

    public String getTestimoni_pemenang() {
        return testimoni_pemenang;
    }

    public String getTotal_bayar() {
        return total_bayar;
    }

    public String getTgl_diumumkan() {
        return tgl_diumumkan;
    }

    public String getImage1() {
        return URL_GET_GAMBAR + "produk/" + image1;
    }

    public String getProduk() {
        return produk;
    }

    public String getTgl_selesai() {
        return tgl_selesai;
    }

    public String getNama() {
        return nama;
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

    public Boolean getStats() {
        return stats;
    }

    public String getMessage() {
        return message;
    }
}
