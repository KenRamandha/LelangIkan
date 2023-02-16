package com.example.myapplication.model.peserta;


import com.google.gson.annotations.SerializedName;

public class PembayaranPemenangModel {
    @SerializedName("lelang_id")
    private String lelang_id;

    @SerializedName("tgl_pembayaran")
    private String tgl_pembayaran;

    @SerializedName("nominal_dibayarkan")
    private String nominal_dibayarkan;

    @SerializedName("bukti_pembayaran")
    private String bukti_pembayaran;

    @SerializedName("status")
    private String status;

    @SerializedName("panitia_id")
    private String panitia_id;

    @SerializedName("keterangan")
    private String keterangan;

    @SerializedName("bukti_bayar")
    private String bukti_bayar;

    @SerializedName("stats")
    private Boolean stats;

    @SerializedName("message")
    private String message;

    public PembayaranPemenangModel(String lelang_id, String nominal_dibayarkan, String bukti_pembayaran) {
        this.lelang_id = lelang_id;
        this.nominal_dibayarkan = nominal_dibayarkan;
        this.bukti_pembayaran = bukti_pembayaran;
    }

    public Boolean getStats() {
        return stats;
    }

    public String getMessage() {
        return message;
    }
}
