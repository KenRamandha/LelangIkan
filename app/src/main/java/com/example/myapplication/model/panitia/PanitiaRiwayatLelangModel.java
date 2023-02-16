package com.example.myapplication.model.panitia;

import static com.example.myapplication.util.ServerAPI.URL_GET_GAMBAR;

import com.google.gson.annotations.SerializedName;

public class PanitiaRiwayatLelangModel {
    @SerializedName("nama_pelelang")
    private String nama_pelelang;

    @SerializedName("produk")
    private String produk;

    @SerializedName("total_bayar")
    private String total_bayar;

    @SerializedName("harga_awal")
    private String harga_awal;

    @SerializedName("tgl_selesai")
    private String tgl_selesai;

    @SerializedName("nama_peserta")
    private String nama_peserta;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("deskripsi_produk")
    private String deskripsi_produk;

    @SerializedName("keterangan")
    private String keterangan;

    @SerializedName("tgl_diumumkan")
    private String tgl_diumumkan;

    @SerializedName("bukti_bayar")
    private String bukti_bayar;

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

    @SerializedName("status")
    private String status;

    @SerializedName("image1")
    private String image1;

    @SerializedName("image2")
    private String image2;

    @SerializedName("image3")
    private String image3;

    @SerializedName("image4")
    private String image4;

    public String getTotal_bayar() {
        return total_bayar;
    }

    public String getImage1() {
        return URL_GET_GAMBAR + "produk/" + image1;
    }

    public String getImage2() {
        return URL_GET_GAMBAR + "produk/" + image2;
    }

    public String getImage3() {
        return URL_GET_GAMBAR + "produk/" + image3;
    }

    public String getImage4() {
        return URL_GET_GAMBAR + "produk/" + image4;
    }

    public String getHarga_awal() {
        return harga_awal;
    }

    public String getNama_pelelang() {
        return nama_pelelang;
    }

    public String getProduk() {
        return produk;
    }

    public String getTgl_selesai() {
        return tgl_selesai;
    }

    public String getNama_peserta() {
        return nama_peserta;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getDeskripsi_produk() {
        return deskripsi_produk;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public String getTgl_diumumkan() {
        return tgl_diumumkan;
    }

    public String getBukti_bayar() {
        return bukti_bayar;
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

    public String getAlamat_kirim() {
        return alamat_kirim;
    }

    public String getStatus() {
        return status;
    }
}
