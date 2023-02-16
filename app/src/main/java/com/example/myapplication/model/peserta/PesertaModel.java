package com.example.myapplication.model.peserta;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PesertaModel {
    @SerializedName("peserta_id")
    private String peserta_id;

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

    @SerializedName("nik")
    private String nik;

    @SerializedName("filektp")
    private String filektp;

    @SerializedName("filenpwp")
    private String filenpwp;

    @SerializedName("deposit")
    private String deposit;

    @SerializedName("status")
    private String status;

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String  password;

    public String getDeposit() {
        return deposit;
    }
}
