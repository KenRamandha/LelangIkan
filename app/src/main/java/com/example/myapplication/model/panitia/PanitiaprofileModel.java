package com.example.myapplication.model.panitia;

import static com.example.myapplication.util.ServerAPI.URL_GET_PHOTO;

import com.google.gson.annotations.SerializedName;

public class PanitiaprofileModel {

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

    @SerializedName("stats")
    private Boolean stats;

    @SerializedName("message")
    private String message;

    public PanitiaprofileModel(String panitia_id, String nik, String nama, String instansi, String jabatan, String jeniskel, String foto) {
        this.panitia_id = panitia_id;
        this.nik = nik;
        this.nama = nama;
        this.instansi = instansi;
        this.jabatan = jabatan;
        this.jeniskel = jeniskel;
        this.foto = foto;
    }

    public String getNik() {
        return nik;
    }

    public String getNama() {
        return nama;
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

    public Boolean getStats() {
        return stats;
    }

    public String getMessage() {
        return message;
    }

    public String getFoto() {
        return URL_GET_PHOTO + "panitia/" + foto;
    }
}
