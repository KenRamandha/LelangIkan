package com.example.myapplication.model.panitia;


import static com.example.myapplication.util.ServerAPI.URL_GET_GAMBAR;

import com.google.gson.annotations.SerializedName;

public class PanitiaSuratPerintahModel {
    @SerializedName("pelelang_id")
    private String pelelang_id;

    @SerializedName("nama_pelelang")
    private String nama_pelelang;

    @SerializedName("peserta_id")
    private String peserta_id;

    @SerializedName("nama_peserta")
    private String nama_peserta;

    @SerializedName("lelang_id")
    private String lelang_id;

    @SerializedName("pengiriman_id")
    private String pengiriman_id;

    @SerializedName("nama_kendaraan")
    private String nama_kendaraan;

    @SerializedName("no_polisi")
    private String no_polisi;

    @SerializedName("no_hp")
    private String no_hp;

    @SerializedName("telp")
    private String telp;

    @SerializedName("status_transaksi")
    private String 	status_transaksi;

    @SerializedName("provinsi_kirim")
    private String provinsi_kirim;

    @SerializedName("kota_kirim")
    private String kota_kirim;

    @SerializedName("kecamatan_kirim")
    private String kecamatan_kirim;

    @SerializedName("kelurahan_kirim")
    private String kelurahan_kirim;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("alamat_kirim")
    private String alamat_kirim;

    @SerializedName("status_pengiriman")
    private String status_pengiriman;

    @SerializedName("produk")
    private String produk;

    @SerializedName("jumlah")
    private String jumlah;

    @SerializedName("tgl_diumumkan")
    private String tgl_diumumkan;

    @SerializedName("kecamatan")
    private String kecamatan;

    @SerializedName("kota")
    private String kota;


    public String getKecamatan() {
        return kecamatan;
    }

    public String getKota() {
        return kota;
    }

    public String getPelelang_id() {
        return pelelang_id;
    }

    public String getNama_pelelang() {
        return nama_pelelang;
    }

    public String getPeserta_id() {
        return peserta_id;
    }

    public String getNama_peserta() {
        return nama_peserta;
    }

    public String getLelang_id() {
        return lelang_id;
    }

    public String getPengiriman_id() {
        return pengiriman_id;
    }

    public String getNama_kendaraan() {
        return nama_kendaraan;
    }

    public String getNo_polisi() {
        return no_polisi;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public String getTelp() {
        return telp;
    }

    public String getStatus_transaksi() {
        return status_transaksi;
    }

    public String getProvinsi_kirim() {
        return provinsi_kirim;
    }

    public String getKota_kirim() {
        return kota_kirim;
    }

    public String getKecamatan_kirim() {
        return kecamatan_kirim;
    }

    public String getKelurahan_kirim() {
        return kelurahan_kirim;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getAlamat_kirim() {
        return alamat_kirim;
    }

    public String getStatus_pengiriman() {
        return status_pengiriman;
    }

    public String getProduk() {
        return produk;
    }

    public String getJumlah() {
        return jumlah;
    }

    public String getTgl_diumumkan() {
        return tgl_diumumkan;
    }
}
