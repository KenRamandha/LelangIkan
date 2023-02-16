package com.example.myapplication.model.panitia;


import static com.example.myapplication.util.ServerAPI.URL_GET_PHOTO;

import com.google.gson.annotations.SerializedName;

public class PanitiaPembayaranPesertaModel {

    @SerializedName("panitia_id")
    private String panitia_id;

    @SerializedName("lelang_id")
    private String lelang_id;

    @SerializedName("tgl_pembayaran")
    private String tgl_pembayaran;

    @SerializedName("nominal_dibayarkan")
    private String nominal_dibayarkan;

    @SerializedName("bukti_pembayaran")
    private String bukti_pembayaran;

    @SerializedName("bukti_text")
    private String bukti_text;

    @SerializedName("peserta_id")
    private String peserta_id;

    @SerializedName("nama")
    private String nama;

    @SerializedName("produk")
    private String produk;

    @SerializedName("status")
    private String status;

    @SerializedName("bukti_bayar")
    private String bukti_bayar;

    @SerializedName("stats")
    private Boolean stats;

    @SerializedName("message")
    private String message;

    public PanitiaPembayaranPesertaModel(String panitia_id, String lelang_id) {
        this.panitia_id = panitia_id;
        this.lelang_id = lelang_id;
    }

    public String getBukti_bayar() {
        return bukti_bayar;
    }

    public String getBukti_text() {
        return bukti_text;
    }

    public String getLelang_id() {
        return lelang_id;
    }

    public String getProduk() {
        return produk;
    }

    public String getTgl_pembayaran() {
        return tgl_pembayaran;
    }

    public String getNominal_dibayarkan() {
        return nominal_dibayarkan;
    }

    public String getBukti_pembayaran() {
        return
                URL_GET_PHOTO + "peserta/bukti_transfer_pembayaran/" +bukti_pembayaran;
    }

    public String getPeserta_id() {
        return peserta_id;
    }

    public String getNama() {
        return nama;
    }

    public String getStatus() {
        return status;
    }

    public Boolean getStats() {
        return stats;
    }

    public String getMessage() {
        return message;
    }
}

