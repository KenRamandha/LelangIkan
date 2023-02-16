package com.example.myapplication.model.peserta;


import com.google.gson.annotations.SerializedName;

public class BayarSekarangPemenangModel {
    @SerializedName("lelang_id")
    private String lelang_id;

    @SerializedName("peserta_id")
    private String peserta_id;

    @SerializedName("nominal_dibayarkan")
    private String nominal_dibayarkan;

    @SerializedName("status")
    private Integer status;


    @SerializedName("stats")
    private Boolean stats;

    @SerializedName("message")
    private String message;

    public BayarSekarangPemenangModel(String lelang_id) {
        this.lelang_id = lelang_id;
    }

    public Boolean getStats() {
        return stats;
    }

    public String getMessage() {
        return message;
    }
}
