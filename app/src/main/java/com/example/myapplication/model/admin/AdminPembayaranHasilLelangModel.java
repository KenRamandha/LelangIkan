package com.example.myapplication.model.admin;


import static com.example.myapplication.util.ServerAPI.URL_GET_GAMBAR;

import com.google.gson.annotations.SerializedName;

public class AdminPembayaranHasilLelangModel {

    @SerializedName("lelang_id")
    private String lelang_id;

    @SerializedName("nama")
    private String nama;

    @SerializedName("nominal_dibayarkan")
    private String nominal_dibayarkan;

    @SerializedName("nama_panitia")
    private String nama_panitia;

    @SerializedName("panitia_id")
    private String panitia_id;

    @SerializedName("telp")
    private String telp;

    @SerializedName("email")
    private String email;

    @SerializedName("norekening")
    private String norekening;

    @SerializedName("atasnama")
    private String atasnama;

    @SerializedName("bukti_transfer")
    private  String bukti_transfer;

    @SerializedName("total_bayar")
    private String total_bayar;

    public String getLelang_id() {
        return lelang_id;
    }

    public String getNominal_dibayarkan() {
        return nominal_dibayarkan;
    }

    public String getNama() {
        return nama;
    }

    public String getPanitia_id() {
        return panitia_id;
    }

    public String getNama_panitia() {
        return nama_panitia;
    }

    public String getTelp() {
        return telp;
    }

    public String getEmail() {
        return email;
    }

    public String getNorekening() {
        return norekening;
    }

    public  String getAtasnama() {
        return atasnama;
    }



    public String getTotal_bayar() {
        return total_bayar;
    }

    public String getBukti_transfer() {
        return URL_GET_GAMBAR + "panitia/buktitransfer/" + bukti_transfer;
    }


//    public String getBukti_transfer() {
//        return URL_GET_GAMBAR + "uploads/panitia/buktitransfer/" + bukti_transfer;
//    }


}
