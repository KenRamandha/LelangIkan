package com.example.myapplication.model.panitia;


import static com.example.myapplication.util.ServerAPI.URL_GET_GAMBAR;

import com.google.gson.annotations.SerializedName;

public class PanitiaTransferModel {

    @SerializedName("pelelang_id")
    private String pelelang_id;

    @SerializedName("nama")
    private String nama;

    @SerializedName("norekening")
    private String norekening;

    @SerializedName("bank")
    private String bank;

    @SerializedName("atasnama")
    private String atasnama;

    @SerializedName("tgl_diumumkan")
    private String tgl_diumumkan;

    @SerializedName("lelang_id")
    private String lelang_id;

    @SerializedName("panitia_id")
    private String panitia_id;

    @SerializedName("total_bayar")
    private String total_bayar;

    @SerializedName("bukti_bayar")
    private String bukti_bayar;

    @SerializedName("nominal_dibayarkan")
    private String nominal_dibayarkan;

    @SerializedName("tgl_pembayaran")
    private String tgl_pembayaran;


    @SerializedName("stats")
    private Boolean stats;

    @SerializedName("message")
    private String message;

    public PanitiaTransferModel(String pelelang_id, String nama, String lelang_id, String panitia_id, String bukti_bayar, String nominal_dibayarkan) {
        this.pelelang_id = pelelang_id;
        this.nama = nama;
        this.lelang_id = lelang_id;
        this.panitia_id = panitia_id;
        this.bukti_bayar = bukti_bayar;
        this.nominal_dibayarkan = nominal_dibayarkan;
    }

    public String getBukti_bayar() {
        return bukti_bayar;
    }

    public String getNominal_dibayarkan() {
        return nominal_dibayarkan;
    }

    public Boolean getStats() {
        return stats;
    }

    public String getMessage() {
        return message;
    }

    public String getTotal_bayar() {
        return total_bayar;
    }

    public String getPelelang_id() {
        return pelelang_id;
    }

    public String getNama() {
        return nama;
    }

    public String getNorekening() {
        return norekening;
    }

    public String getBank() {
        return bank;
    }

    public String getAtasnama() {
        return atasnama;
    }

    public String getTgl_diumumkan() {
        return tgl_diumumkan;
    }

    public String getLelang_id() {
        return lelang_id;
    }


}
