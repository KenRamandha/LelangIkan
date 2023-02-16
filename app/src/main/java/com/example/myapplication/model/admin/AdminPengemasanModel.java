package com.example.myapplication.model.admin;


import static com.example.myapplication.util.ServerAPI.URL_GET_GAMBAR;

import com.google.gson.annotations.SerializedName;

public class AdminPengemasanModel {

    @SerializedName("pengiriman_id")
    private String pengiriman_id;

    @SerializedName("total_bayar")
    private String total_bayar;

    @SerializedName("lelang_id")
    private String lelang_id;

    @SerializedName("pelelang_id")
    private String pelelang_id;

    @SerializedName("nama_pelelang")
    private String nama_pelelang;

    @SerializedName("nama_pengirim")
    private String nama_pengirim;

    @SerializedName("nama")
    private String nama;

    @SerializedName("no_polisi")
    private String no_polisi;

    @SerializedName("nama_kendaraan")
    private String nama_kendaraan;

    @SerializedName("no_hp")
    private String no_hp;

    @SerializedName("status_transaksi")
    private String status_transaksi;

    @SerializedName("alamat_kirim")
    private String alamat_kirim;

    @SerializedName("nominal_dibayarkan")
    private String nominal_dibayarkan;

    @SerializedName("produk")
    private String produk;

    @SerializedName("tgl_selesai")
    private String tgl_selesai;


    @SerializedName("tgl_pengiriman")
    private String tgl_pengiriman;

    @SerializedName("image1")
    private String image1;

    public String getPengiriman_id() {
        return pengiriman_id;
    }

    public String getNama() {
        return nama;
    }

    public String getTotal_bayar() {
        return total_bayar;
    }

    public String getNamaPengirim() {
        return nama_pengirim;
    }

    public String getNo_polisi() {
        return no_polisi;
    }

    public String getNama_kendaraan() {
        return nama_kendaraan;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public String getStatus_transaksi() {
        return status_transaksi;
    }

    public String getAlamat_kirim() {
        return alamat_kirim;
    }

    public String getNominal_dibayarkan() {
        return nominal_dibayarkan;
    }

    public String getProduk() {
        return produk;
    }

    public String getTgl_selesai() {
        return tgl_selesai;
    }

    public String getTgl_pengiriman() {
        return tgl_pengiriman;
    }

    public String getImage1() {
        return URL_GET_GAMBAR + "produk/" + image1;
    }


//    public String getImage1() {
//        return URL_GET_GAMBAR + "uploads/produk/" + image1;
//    }
}
