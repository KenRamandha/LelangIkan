package com.example.myapplication.model.admin;

import com.google.gson.annotations.SerializedName;

public class AdminRiwayatModel {

    @SerializedName("lelang_id")
    private String lelang_id;

    @SerializedName("panitia_id")
    private String panitia_id;

    @SerializedName("nama")
    private String nama;

    @SerializedName("produk")
    private String produk;

    @SerializedName("jumlah")
    private String jumlah;

    @SerializedName("nominal_dibayarkan")
    private String nominal_dibayarkan;

    @SerializedName("tgl_pembayaran")
    private String tgl_pembayaran;

    @SerializedName("tgl_diumumkan")
    private String tgl_diumumkan;



    @SerializedName("bukti_pembayaran")
    private String bukti_pembayaran;

    @SerializedName("konfirmasi_terimaproduk")
    private String konfirmasi_terimaproduk;


    @SerializedName("status")
    private String status;

    public String getLelang_id() {
        return lelang_id;
    }

    public String getPanitia_id() {
        return panitia_id;
    }

    public String getNama_peserta() {
        return nama;
    }

    public String getProduk() {
        return produk;
    }

    public String getJumlah() {
        return jumlah;
    }

    public String getNominal_dibayarkan() {
        return nominal_dibayarkan;
    }

    public String getTgl_pembayaran() {
        return tgl_pembayaran;
    }

    public String getTgl_diumumkan() {
        return tgl_diumumkan;
    }

    public String getBukti_pembayaran() {
        return bukti_pembayaran;
    }

    public String getKonfirmasi_terimaproduk() {
        return konfirmasi_terimaproduk;
    }

    public String getStatus() {
        return status;
    }

    //    public String getLelang_id() {
//        return lelang_id;
//    }
//
//    public String getPanitia_id() {
//        return panitia_id;
//    }
//
//    public String getNama_peserta() {
//        return nama_peserta;
//    }
//
//    public String getTgl_pembayaran() {
//        return tgl_pembayaran;
//    }
//
//    public double getNominal_dibayarkan() {
//        return nominal_dibayarkan;
//    }
//
//
////    Mas Ken
////    public String getBukti_pembayaran() {
////        return bukti_pembayaran;
////    }
//
//
//    public String getBukti_pembayaran() {
//        return status;
//    }
//
//
//    public String getStatus() {
//        return status;
//    }
//
//
//    public String getProduk() {
//        return produk;
//    }
}

