package com.example.myapplication.model.admin;


import com.google.gson.annotations.SerializedName;

public class PemenangModel {
    @SerializedName("peserta_id")
    private String peserta_id;

    @SerializedName("nik")
    private String nik;

    @SerializedName("npwp")
    private String npwp;

    @SerializedName("nama")
    private String nama;

    @SerializedName("jeniskel")
    private String jeniskel;

    @SerializedName("telp")
    private String telp;

    @SerializedName("email")
    private String email;

    @SerializedName("produk")
    private String produk;

    @SerializedName("tgl_diumumkan")
    private String tgl_diumumkan;

    @SerializedName("status")
    private String status;

    @SerializedName("lelang_id")
    private String lelang_id;

    @SerializedName("bukti_bayar")
    private String bukti_bayar;

    @SerializedName("tgl_bayar")
    private String tgl_bayar;

    @SerializedName("alamat_kirim")
    private String alamat_kirim;

    @SerializedName("kota_kirim")
    private String kota_kirim;

    @SerializedName("kelurahan_kirim")
    private String kelurahan_kirim;

    @SerializedName("kecamatan_kirim")
    private String kecamatan_kirim;

    @SerializedName("provinsi_kirim")
    private String provinsi_kirim;

    @SerializedName("testimoni_pemenang")
    private String testimoni_pemenang;

    @SerializedName("total_bayar")
    private String total_bayar;

    public String getPeserta_id() {
        return peserta_id;
    }

    public String getNik() {
        return nik;
    }

    public String getNpwp() {
        return npwp;
    }

    public String getNama() {
        return nama;
    }

    public String getJeniskel() {
        return jeniskel;
    }

    public String getTelp() {
        return telp;
    }

    public String getEmail() {
        return email;
    }

    public String getProduk() {
        return produk;
    }

    public String getTgl_diumumkan() {
        return tgl_diumumkan;
    }

    public String getStatus() {
        return status;
    }

    public String getLelang_id() {
        return lelang_id;
    }

    public String getBukti_bayar() {
        return bukti_bayar;
    }

    public String getTotal_bayar() {
        return total_bayar;
    }

    public String getTgl_bayar() {
        return tgl_bayar;
    }

    public String getAlamat_kirim() {
        return alamat_kirim;
    }

    public String getKota_kirim() {
        return kota_kirim;
    }

    public String getKelurahan_kirim() {
        return kelurahan_kirim;
    }

    public String getKecamatan_kirim() {
        return kecamatan_kirim;
    }

    public String getProvinsi_kirim() {
        return provinsi_kirim;
    }

    public String getTestimoni_pemenang() {
        return testimoni_pemenang;
    }
}
