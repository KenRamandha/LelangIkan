package com.example.myapplication.model.admin;


import static com.example.myapplication.util.ServerAPI.URL_GET_GAMBAR;
import static com.example.myapplication.util.ServerAPI.URL_GET_PHOTO;

import com.google.gson.annotations.SerializedName;

public class AdminPesertaModel {

    @SerializedName("peserta_id")
    private String peserta_id;

    @SerializedName("nik")
    private String nik;

    @SerializedName("nama")
    private String nama;

    @SerializedName("jeniskel")
    private String jeniskel;

    @SerializedName("provinsi")
    private String provinsi;

    @SerializedName("kota")
    private String kota;

    @SerializedName("kecamatan")
    private String kecamatan;

    @SerializedName("kelurahan")
    private String kelurahan;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("telp")
    private String telp;

    @SerializedName("email")
    private String email;

    @SerializedName("npwp")
    private String npwp;


    @SerializedName("filektp")
    private String filektp;

    @SerializedName("filenpwp")
    private String filenpwp;

    @SerializedName("deposit")
    private Double deposit;

    @SerializedName("status")
    private int status;

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String  password;

//    @SerializedName("stats")
//    private Boolean stats;
//
//    @SerializedName("message")
//    private String message;


    public String getPeserta_id() {
        return peserta_id;
    }

    public String getNama() {
        return nama;
    }

    public String getJeniskel() {
        return jeniskel;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public String getKota() {
        return kota;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public String getKelurahan() {
        return kelurahan;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getTelp() {
        return telp;
    }

    public String getEmail() {
        return email;
    }

    public String getNpwp() {
        return npwp;
    }

    public String getNik() {
        return nik;
    }

    public String getFilektp() {
        return URL_GET_PHOTO + "peserta/" + filektp;
    }

    public String getFilenpwp() {
        return URL_GET_PHOTO+ "peserta/" + filenpwp;
    }
}
