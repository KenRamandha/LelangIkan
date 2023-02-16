package com.example.myapplication.model.pelelang;

import static com.example.myapplication.util.ServerAPI.URL_GET_GAMBAR;

import com.google.gson.annotations.SerializedName;

public class PembayaranPanitiaModel {
    @SerializedName("pelelang_id")
    private String pelelang_id;

    @SerializedName("nama")
    private String nama;

    @SerializedName("bukti_transfer")
    private String bukti_transfer;

    @SerializedName("nominal_dibayarkan")
    private String nominal_dibayarkan;

    @SerializedName("panitia_id")
    private String panitia_id;

    @SerializedName("lelang_id")
    private String lelang_id;

    public String getPelelang_id() {
        return pelelang_id;
    }

    public String getNama() {
        return nama;
    }

    public String getBukti_transfer() {
        return URL_GET_GAMBAR + "uploads/panitia/buktitransfer/" +bukti_transfer;
    }

    public String getNominal_dibayarkan() {
        return nominal_dibayarkan;
    }

    public String getPanitia_id() {
        return panitia_id;
    }

    public String getLelang_id() {
        return lelang_id;
    }
}
