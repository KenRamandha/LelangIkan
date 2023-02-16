package com.example.myapplication.model.peserta;


import com.google.gson.annotations.SerializedName;

public class AlamatKirimPemenangModel {
    @SerializedName("lelang_id")
    private String lelang_id;

    @SerializedName("provinsi_kirim")
    private String provinsi_kirim;

    @SerializedName("kota_kirim")
    private String kota_kirim;

    @SerializedName("kecamatan_kirim")
    private String kecamatan_kirim;

    @SerializedName("kelurahan_kirim")
    private String kelurahan_kirim;

    @SerializedName("alamat_kirim")
    private String alamat_kirim;

    @SerializedName("stats")
    private Boolean stats;

    @SerializedName("message")
    private String message;

    public AlamatKirimPemenangModel(String lelang_id, String provinsi_kirim, String kota_kirim, String kecamatan_kirim, String kelurahan_kirim, String alamat_kirim) {
        this.lelang_id = lelang_id;
        this.provinsi_kirim = provinsi_kirim;
        this.kota_kirim = kota_kirim;
        this.kecamatan_kirim = kecamatan_kirim;
        this.kelurahan_kirim = kelurahan_kirim;
        this.alamat_kirim = alamat_kirim;
    }

    public Boolean getStats() {
        return stats;
    }

    public String getMessage() {
        return message;
    }
}
