package com.example.myapplication.model.panitia;


import static com.example.myapplication.util.ServerAPI.URL_GET_GAMBAR;
import static com.example.myapplication.util.ServerAPI.URL_GET_PHOTO;

import com.google.gson.annotations.SerializedName;

public class PanitiaPemenangModel {
    @SerializedName("peserta_id")
    private String peserta_id;

    @SerializedName("lelang_id")
    private String lelang_id;

    @SerializedName("panitia_id")
    private String panitia_id;

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
    private String 	alamat;

    @SerializedName("telp")
    private String telp;

    @SerializedName("email")
    private String email;

    @SerializedName("produk")
    private String produk;

    @SerializedName("tgl_selesai")
    private String tgl_selesai;

    @SerializedName("tgl_diumumkan")
    private String tgl_diumumkan;

    @SerializedName("total_bayar")
    private String total_bayar;

    @SerializedName("status")
    private String status;

    @SerializedName("bukti_bayar")
    private String bukti_bayar;

    public PanitiaPemenangModel(String lelang_id, String status, String panitia_id, String tgl_diumumkan) {
        this.lelang_id = lelang_id;
        this.status = status;
        this.panitia_id = panitia_id;
        this.tgl_diumumkan = tgl_diumumkan;
    }

    public String getPeserta_id() {
        return peserta_id;
    }

    public String getLelang_id() {
        return lelang_id;
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

    public String getProduk() {
        return produk;
    }

    public String getTgl_selesai() {
        return tgl_selesai;
    }

    public String getTotal_bayar() {
        return total_bayar;
    }

    public String getStatus() {
        return status;
    }

    public String getBukti_bayar() {
        return URL_GET_PHOTO + "peserta/bukti_transfer_pembayaran/" + bukti_bayar;
//        return "http://192.168.10.201/lelang/assets/uploads/" + bukti_bayar;
    }

//    http://192.168.10.201/lelang/assets/uploads/

}
