package com.example.myapplication.model.peserta;


import static com.example.myapplication.util.ServerAPI.URL_GET_GAMBAR;

import com.google.gson.annotations.SerializedName;

public class PemenangPesertaModel {

    @SerializedName("peserta_id")
    private String peserta_id;

    @SerializedName("lelang_id")
    private String lelang_id;

    @SerializedName("produk")
    private String produk;

    @SerializedName("image1")
    private String image1;

    @SerializedName("harga_tawar")
    private String harga_tawar;


    public String getPeserta_id() {
        return peserta_id;
    }


    public String getImage1() {
        return URL_GET_GAMBAR + "produk/" + image1;
    }

    public String getProduk() {
        return produk;
    }

    public String getHarga_tawar() {
        return harga_tawar;
    }

    public String getLelang_id() {
        return lelang_id;
    }

}
