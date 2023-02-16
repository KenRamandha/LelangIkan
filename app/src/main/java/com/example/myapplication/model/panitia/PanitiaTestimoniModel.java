package com.example.myapplication.model.panitia;


import static com.example.myapplication.util.ServerAPI.URL_GET_GAMBAR;

import com.google.gson.annotations.SerializedName;

public class PanitiaTestimoniModel {
    @SerializedName("peserta_id")
    private String peserta_id;

    @SerializedName("testimoni_pemenang")
    private String testimoni_pemenang;

    @SerializedName("nama")
    private String nama;

    public String getPeserta_id() {
        return peserta_id;
    }

    public String getTestimoni_pemenang() {
        return testimoni_pemenang;
    }

    public String getNama() {
        return nama;
    }
}
