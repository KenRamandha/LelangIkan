package com.example.myapplication.model.panitia;


import com.google.gson.annotations.SerializedName;

public class PanitiaRiwayatTransferModel {

    @SerializedName("lelang_id")
    private String lelang_id;

    @SerializedName("pelelang_id")
    private String pelelang_id;

    @SerializedName("panitia_id")
    private String panitia_id;

    @SerializedName("nama")
    private String nama;

    @SerializedName("bukti_transfer")
    private String bukti_transfer;

    @SerializedName("nominal_dibayarkan")
    private String nominal_dibayarkan;

    public String getLelang_id() {
        return lelang_id;
    }

    public String getPelelang_id() {
        return pelelang_id;
    }

    public String getPanitia_id() {
        return panitia_id;
    }

    public String getNama() {
        return nama;
    }

    public String getBukti_transfer() {
        return  bukti_transfer;
    }
    public String getNominal_dibayarkan() {
        return nominal_dibayarkan;
    }
}
