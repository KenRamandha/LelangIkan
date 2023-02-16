package com.example.myapplication.model.panitia;


import com.google.gson.annotations.SerializedName;

public class PanitiaPilihanPembayaranModel {
    @SerializedName("lelang_id")
    private String lelang_id;

    @SerializedName("panitia_id")
    private String panitia_id;

    @SerializedName("stats")
    private Boolean stats;

    @SerializedName("message")
    private String message;

    public PanitiaPilihanPembayaranModel(String lelang_id, String panitia_id) {
        this.lelang_id = lelang_id;
        this.panitia_id = panitia_id;
    }


    public Boolean getStats() {
        return stats;
    }

    public String getMessage() {
        return message;
    }
}
