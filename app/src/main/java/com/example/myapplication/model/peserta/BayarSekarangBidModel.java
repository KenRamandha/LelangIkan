package com.example.myapplication.model.peserta;


import com.google.gson.annotations.SerializedName;

public class BayarSekarangBidModel {
    @SerializedName("lelang_id")
    private String lelang_id;

    @SerializedName("peserta_id")
    private String peserta_id;

    @SerializedName("tgl_bid")
    private String tgl_bid;

    @SerializedName("harga_tawar")
    private String harga_tawar;

    @SerializedName("stats")
    private Boolean stats;

    @SerializedName("message")
    private String message;

    public BayarSekarangBidModel(String lelang_id, String peserta_id, String tgl_bid, String harga_tawar) {
        this.lelang_id = lelang_id;
        this.peserta_id = peserta_id;
        this.tgl_bid = tgl_bid;
        this.harga_tawar = harga_tawar;
    }

    public Boolean getStats() {
        return stats;
    }

    public String getMessage() {
        return message;
    }
}
