package com.example.myapplication.model.pelelang;

import com.google.gson.annotations.SerializedName;

public class TambahLelangModel {

    @SerializedName("code")
    private String code;

    @SerializedName("lelang_id")
    private String lelangId;

    @SerializedName("pelelang_id")
    private String pelelangId;

    @SerializedName("produk")
    private String produk;

    @SerializedName("deskripsi_produk")
    private String deskripsiProduk;

    @SerializedName("image1")
    private String image1;

    @SerializedName("image2")
    private String image2;

    @SerializedName("image3")
    private String image3;

    @SerializedName("image4")
    private String image4;

    @SerializedName("harga_awal")
    private Double hargaAwal;

    @SerializedName("harga_minimal_diterima")
    private Double hargaMinimalDiterima;

    @SerializedName("incremental_value")
    private Double incrementalValue;

    @SerializedName("tgl_mulai")
    private String tglMulai;

    @SerializedName("tgl_selesai")
    private String tglSelesai;

    @SerializedName("harga_beli_sekarang")
    private Double hargaBeliSekarang;

    @SerializedName("keterangan")
    private String keterangan;

    @SerializedName("jumlah")
    private String jumlah;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private Boolean status;

    public TambahLelangModel (
            String produk,
            String deskripsi_produk,
            String image1,
            String image2,
            String image3,
            String image4,
            Double harga_awal,
            Double harga_minimal_diterima,
            Double incremental_value,
            String tgl_mulai,
            String tgl_selesai,
            Double harga_beli_sekarang,
            String keterangan,
            String jumlah
    ){
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
        this.produk = produk;
        this.deskripsiProduk = deskripsi_produk;
        this.hargaAwal = harga_awal;
        this.hargaMinimalDiterima = harga_minimal_diterima;
        this.incrementalValue = incremental_value;
        this.tglMulai = tgl_mulai;
        this.tglSelesai = tgl_selesai;
        this.hargaBeliSekarang = harga_beli_sekarang;
        this.jumlah = jumlah;
        this.keterangan = keterangan;
    }

    public String getLelangId() {
        return lelangId;
    }

    public void setLelangId(String lelangId) {
        this.lelangId = lelangId;
    }

    public String getPelelangId() {
        return pelelangId;
    }

    public void setPelelangId(String pelelangId) {
        this.pelelangId = pelelangId;
    }

    public String getProduk() {
        return produk;
    }

    public void setProduk(String produk) {
        this.produk = produk;
    }

    public String getDeskripsiProduk() {
        return deskripsiProduk;
    }

    public void setDeskripsiProduk(String deskripsiProduk) {
        this.deskripsiProduk = deskripsiProduk;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getImage4() {
        return image4;
    }

    public void setImage4(String image4) {
        this.image4 = image4;
    }

    public Double getHargaAwal() {
        return hargaAwal;
    }

    public void setHargaAwal(Double hargaAwal) {
        this.hargaAwal = hargaAwal;
    }

    public Double getHargaMinimalDiterima() {
        return hargaMinimalDiterima;
    }

    public void setHargaMinimalDiterima(Double hargaMinimalDiterima) {
        this.hargaMinimalDiterima = hargaMinimalDiterima;
    }

    public Double getIncrementalValue() {
        return incrementalValue;
    }

    public void setIncrementalValue(Double incrementalValue) {
        this.incrementalValue = incrementalValue;
    }

    public Double getHargaBeliSekarang() {
        return hargaBeliSekarang;
    }

    public void setHargaBeliSekarang(Double hargaBeliSekarang) {
        this.hargaBeliSekarang = hargaBeliSekarang;
    }

    public String getTglMulai() {
        return tglMulai;
    }

    public void setTglMulai(String tglMulai) {
        this.tglMulai = tglMulai;
    }

    public String getTglSelesai() {
        return tglSelesai;
    }

    public void setTglSelesai(String tglSelesai) {
        this.tglSelesai = tglSelesai;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getMessageAddProduk() {
        return message;
    }

    public void setMessageAddProduk(String message) {
        this.message = message;
    }

    public Boolean getStatusAddProduk() {
        return status;
    }

    public void setStatusAddProduk(Boolean status) {
        this.status = status;
    }
}
