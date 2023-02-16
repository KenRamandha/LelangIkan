package com.example.myapplication.model.peserta;


import com.google.gson.annotations.SerializedName;

public class DepositModel {
    @SerializedName("kode")
    private String kode;

    @SerializedName("tgl_deposit")
    private String tgl_deposit;

    @SerializedName("nominal_deposit")
    private String nominal_deposit;

    @SerializedName("bukti_pembayaran")
    private String bukti_pembayaran;

    @SerializedName("status")
    private String status;

    @SerializedName("panitia_id")
    private String panitia_id;

    @SerializedName("keterangan")
    private String keterangan;

    @SerializedName("stats")
    private Boolean stats;

    @SerializedName("message")
    private String message;

    public DepositModel(String kode, String nominal_deposit, String bukti_pembayaran) {
        this.kode = kode;
        this.nominal_deposit = nominal_deposit;
        this.bukti_pembayaran = bukti_pembayaran;
    }

    public Boolean getStats() {
        return stats;
    }

    public String getMessage() {
        return message;
    }
}
