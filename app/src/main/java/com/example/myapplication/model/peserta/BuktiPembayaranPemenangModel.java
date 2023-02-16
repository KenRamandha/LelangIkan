package com.example.myapplication.model.peserta;


import static com.example.myapplication.util.ServerAPI.URL_GET_GAMBAR;
import static com.example.myapplication.util.ServerAPI.URL_GET_PHOTO;

import com.google.gson.annotations.SerializedName;

public class BuktiPembayaranPemenangModel {

    @SerializedName("lelang_id")
    private String lelang_id;

    @SerializedName("bukti_pembayaran")
    private String bukti_pembayaran;

//    @SerializedName("bukti_bayar")
//    private String bukti_bayar;

    public String getLelang_id() {
        return lelang_id;
    }

    public String getBukti_pembayaran() {
        return URL_GET_PHOTO + "peserta/bukti_transfer_pembayaran/" + bukti_pembayaran;
    }
}
