package com.example.myapplication.model.peserta;

import static com.example.myapplication.util.ServerAPI.URL_GET_GAMBAR;

import com.google.gson.annotations.SerializedName;

public class WaktuLelangModel {
    @SerializedName("lelang_id")
    private String lelang_id;

    @SerializedName("tgl_selesai")
    private String tgl_selesai;

    @SerializedName("stats")
    private Boolean stats;

    @SerializedName("message")
    private String message;

    public WaktuLelangModel(String lelang_id, String tgl_selesai) {
        this.lelang_id = lelang_id;
        this.tgl_selesai = tgl_selesai;
    }

    public Boolean getStats() {
        return stats;
    }

    public String getMessage() {
        return message;
    }
}
