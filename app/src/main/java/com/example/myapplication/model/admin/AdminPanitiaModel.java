package com.example.myapplication.model.admin;

import static com.example.myapplication.util.ServerAPI.URL_GET_GAMBAR;

import com.google.gson.annotations.SerializedName;

public class AdminPanitiaModel {

    @SerializedName("panitia_id")
    private String panitia_id;

    @SerializedName("nik")
    private String nik;

    @SerializedName("nama")
    private String nama;

    @SerializedName("instansi")
    private String instansi;

    @SerializedName("jabatan")
    private String jabatan;

    @SerializedName("jeniskel")
    private String jeniskel;

    @SerializedName("foto")
    private String foto;

    @SerializedName("username")
    private String username;



    @SerializedName("password")
    private String password;

    public String getPanitia_id() {
        return panitia_id;
    }

    public String getNama() {
        return nama;
    }

    public String getNik() {
        return nik;
    }

    public String getInstansi() {
        return instansi;
    }

    public String getJabatan() {
        return jabatan;
    }

    public String getJeniskel() {
        return jeniskel;
    }

    public String getUsername() {
        return username;
    }



    public String getFoto() {
        return URL_GET_GAMBAR + "panitia/" + foto;
    }

//
//    public String getFoto() {
//        return URL_GET_GAMBAR + "uploads/panitia/" + foto;
//    }


//
//    public String getFilenpwp() {
//        return URL_GET_GAMBAR + "uploads/pelelang/" + filenpwp;
//    }
//
//    public String getFileSIUP() {
//        return URL_GET_GAMBAR + "uploads/pelelang/" + fileSIUP;
//    }


}
