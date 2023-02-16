package com.example.myapplication.model.peserta;

import com.google.gson.annotations.SerializedName;

public class ProfilePesertaModel {
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

    @SerializedName("stats")
    private Boolean stats;

    @SerializedName("message")
    private String message;

    public ProfilePesertaModel(String peserta_id, String nama, String jeniskel, String provinsi, String kota, String kecamatan, String kelurahan, String alamat, String telp, String email, String npwp, String nik, String filektp, String filenpwp) {
        this.peserta_id = peserta_id;
        this.nama = nama;
        this.jeniskel = jeniskel;
        this.provinsi = provinsi;
        this.kota = kota;
        this.kecamatan = kecamatan;
        this.kelurahan = kelurahan;
        this.alamat = alamat;
        this.telp = telp;
        this.email = email;
        this.npwp = npwp;
        this.nik = nik;
        this.filektp = filektp;
        this.filenpwp = filenpwp;
    }

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

    public String getStatus() {
        return status;
    }

    public String getFilektp() {
        return filektp;
    }

    public String getFilenpwp() {
        return filenpwp;
    }

    public Boolean getStats() {
        return stats;
    }

    public String getMessage() {
        return message;
    }
}
