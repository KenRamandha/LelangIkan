package com.example.myapplication.model.admin;


import static com.example.myapplication.util.ServerAPI.URL_GET_GAMBAR;
import static com.example.myapplication.util.ServerAPI.URL_GET_PHOTO;

import com.google.gson.annotations.SerializedName;

public class AdminPembayaranModel {
    @SerializedName("lelang_id")
    private String lelang_id;

    @SerializedName("nama_peserta")
    private String nama_peserta;

    @SerializedName("produk")
    private String produk;

    @SerializedName("tgl_pembayaran")
    private String tgl_pembayaran;

    @SerializedName("nominal_dibayarkan")
    private String nominal_dibayarkan;

    @SerializedName("bukti_pembayaran")
    private String bukti_pembayaran;

    @SerializedName("panitia_id")
    private String panitia_id;

    @SerializedName("status")
    private String status;

    public String getLelang_id() {
        return lelang_id;
    }

    public String getTgl_pembayaran() {
        return tgl_pembayaran;
    }

    public String getNominal_dibayarkan() {
        return nominal_dibayarkan;
    }


//    Mas Ken
//    public String getBukti_pembayaran() {
//        return bukti_pembayaran;
//    }

//
//    public String getBukti_pembayaran() {
//        return bukti_pembayaran;
//    }

    public String getPanitia_id() {
        return panitia_id;
    }

    public String getStatus() {
        return status;
    }

    public String getNama_peserta() {
        return nama_peserta;
    }

    public String getProduk() {
        return produk;
    }


    public String getBukti_pembayaran() {
        return URL_GET_PHOTO+ "peserta/bukti_transfer_pembayaran/" + bukti_pembayaran;
    }


//    public String getBukti_pembayaran() {
//        return URL_GET_GAMBAR + "images/peserta/bukti_transfer_pembayaran/" + bukti_pembayaran;
//    }
}
