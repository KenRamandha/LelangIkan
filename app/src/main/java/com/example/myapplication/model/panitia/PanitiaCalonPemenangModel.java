package com.example.myapplication.model.panitia;


import static com.example.myapplication.util.ServerAPI.URL_GET_GAMBAR;

import com.google.gson.annotations.SerializedName;

public class PanitiaCalonPemenangModel {
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
    private String 	telp;

    @SerializedName("email")
    private String email;

    @SerializedName("npwp")
    private String npwp;

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
    private String password;

    @SerializedName("bid_id")
    private String bid_id;

    @SerializedName("lelang_id")
    private String lelang_id;

    @SerializedName("tgl_bid")
    private String tgl_bid;

    @SerializedName("harga_tawar")
    private String harga_tawar;



    public String getPeserta_id() {
        return peserta_id;
    }

    public String getNik() {
        return nik;
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

    public String getFilektp() {
        return filektp;
    }

    public String getFilenpwp() {
        return filenpwp;
    }

    public String getDeposit() {
        return deposit;
    }

    public String getStatus() {
        return status;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getBid_id() {
        return bid_id;
    }

    public String getLelang_id() {
        return lelang_id;
    }

    public String getTgl_bid() {
        return tgl_bid;
    }

    public String getHarga_tawar() {
        return harga_tawar;
    }

    //    public String getFilektp() {
//        return URL_GET_GAMBAR + "pelelang/" + filektp;
//    }
//
//    public String getFilenpwp() {
//        return URL_GET_GAMBAR + "pelelang/" + filenpwp;
//    }
//
//    public String getFileSIUP() {
//        return URL_GET_GAMBAR + "pelelang/" + fileSIUP;
//    }
}
