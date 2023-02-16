package com.example.myapplication.model.panitia;


import com.google.gson.annotations.SerializedName;

public class PanitiaPilihanPemenangModel {
    @SerializedName("lelang_id")
    private String lelang_id;

    @SerializedName("peserta_id")
    private String peserta_id;

    @SerializedName("tgl_diumumkan")
    private String tgl_diumumkan;

    @SerializedName("status")
    private String status;

    @SerializedName("panitia_id")
    private String panitia_id;

    @SerializedName("total_bayar")
    private String total_bayar;

    @SerializedName("stats")
    private Boolean stats;

    @SerializedName("message")
    private String message;

    public PanitiaPilihanPemenangModel(String lelang_id, String peserta_id, String tgl_diumumkan, String status, String panitia_id, String total_bayar) {
        this.lelang_id = lelang_id;
        this.peserta_id = peserta_id;
        this.tgl_diumumkan = tgl_diumumkan;
        this.status = status;
        this.panitia_id = panitia_id;
        this.total_bayar = total_bayar;
    }

    public Boolean getStats() {
        return stats;
    }

    public String getMessage() {
        return message;
    }
}
