package com.example.myapplication.model.panitia;


import static com.example.myapplication.util.ServerAPI.URL_GET_GAMBAR;

import com.google.gson.annotations.SerializedName;

public class PanitiaPelelangModel {
    @SerializedName("pelelang_id")
    private String pelelang_id;

    @SerializedName("nik")
    private String nik;

    @SerializedName("nama")
    private String nama;

    @SerializedName("jenis")
    private String jenis;

    @SerializedName("provinsi")
    private String provinsi;

    @SerializedName("kota")
    private String kota;

    @SerializedName("provinsi_kirim")
    private String provinsi_kirim;

    @SerializedName("kecamatan")
    private String kecamatan;

    @SerializedName("kelurahan")
    private String kelurahan;

    @SerializedName("alamat")
    private String 	alamat;

    @SerializedName("telp")
    private String telp;

    @SerializedName("email")
    private String email;

    @SerializedName("npwp")
    private String npwp;

    @SerializedName("norekening")
    private String norekening;

    @SerializedName("bank")
    private String bank;

    @SerializedName("atasnama")
    private String atasnama;

    @SerializedName("filektp")
    private String filektp;

    @SerializedName("filenpwp")
    private String filenpwp;

    @SerializedName("fileSIUP")
    private String fileSIUP;

    @SerializedName("status")
    private String status;

    @SerializedName("deskripsi")
    private String deskripsi;

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    public PanitiaPelelangModel(String pelelang_id, String status) {
        this.pelelang_id = pelelang_id;
        this.status = status;
    }

    public String getPelelang_id() {
        return pelelang_id;
    }

    public String getNama() {
        return nama;
    }

    public String getNik() {
        return nik;
    }

    public String getJenis() {
        return jenis;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public String getKota() {
        return kota;
    }

    public String getProvinsi_kirim() {
        return provinsi_kirim;
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

    public String getNorekening() {
        return norekening;
    }

    public String getBank() {
        return bank;
    }

    public String getAtasnama() {
        return atasnama;
    }

    public String getStatus() {
        return status;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public String getFilektp() {
        return URL_GET_GAMBAR + "pelelang/" + filektp;
    }

    public String getFilenpwp() {
        return URL_GET_GAMBAR + "pelelang/" + filenpwp;
    }

    public String getFileSIUP() {
        return URL_GET_GAMBAR + "pelelang/" + fileSIUP;
    }
}
