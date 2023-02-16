package com.example.myapplication.model.panitia;


import static com.example.myapplication.util.ServerAPI.URL_GET_PHOTO;

import com.google.gson.annotations.SerializedName;

public class PanitiaPemenangCalonModel {

    @SerializedName("lelang_id")
    private String lelang_id;

    @SerializedName("tgl_pembayaran")
    private String tgl_pembayaran;

    @SerializedName("nominal_dibayarkan")
    private String nominal_dibayarkan;

    @SerializedName("bukti_pembayaran")
    private String bukti_pembayaran;

    @SerializedName("panitia_id")
    private String panitia_id;

    @SerializedName("peserta_id")
    private String peserta_id;

    @SerializedName("nama")
    private String nama;

    public String getLelang_id() {
        return lelang_id;
    }

    public String getTgl_pembayaran() {
        return tgl_pembayaran;
    }

    public String getNominal_dibayarkan() {
        return nominal_dibayarkan;
    }

    public String getBukti_pembayaran() {
        return bukti_pembayaran;
    }

    public String getPanitia_id() {
        return panitia_id;
    }

    public String getPeserta_id() {
        return peserta_id;
    }

    public String getNama() {
        return nama;
    }
}
