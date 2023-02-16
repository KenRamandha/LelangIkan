package com.example.myapplication.model.pelelang;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataPengiriman {
    @SerializedName("pengiriman_id")
    @Expose
    private String pengirimanId;
    @SerializedName("lelang_id")
    @Expose
    private String lelangId;
    @SerializedName("pelelang_id")
    @Expose
    private String pelelangId;
    @SerializedName("nama_pengirim")
    @Expose
    private String namaPengirim;
    @SerializedName("no_polisi")
    @Expose
    private String noPolisi;
    @SerializedName("nama_kendaraan")
    @Expose
    private String namaKendaraan;
    @SerializedName("no_hp")
    @Expose
    private String noHp;
    @SerializedName("status_transaksi")
    @Expose
    private String statusTransaksi;
    @SerializedName("tgl_pengiriman")
    @Expose
    private String tglPengiriman;

    public String getPengirimanId() {
        return pengirimanId;
    }

    public void setPengirimanId(String pengirimanId) {
        this.pengirimanId = pengirimanId;
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

    public String getNamaPengirim() {
        return namaPengirim;
    }

    public void setNamaPengirim(String namaPengirim) {
        this.namaPengirim = namaPengirim;
    }

    public String getNoPolisi() {
        return noPolisi;
    }

    public void setNoPolisi(String noPolisi) {
        this.noPolisi = noPolisi;
    }

    public String getNamaKendaraan() {
        return namaKendaraan;
    }

    public void setNamaKendaraan(String namaKendaraan) {
        this.namaKendaraan = namaKendaraan;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getStatusTransaksi() {
        return statusTransaksi;
    }

    public void setStatusTransaksi(String statusTransaksi) {
        this.statusTransaksi = statusTransaksi;
    }

    public String getTglPengiriman() {
        return tglPengiriman;
    }

    public void setTglPengiriman(String tglPengiriman) {
        this.tglPengiriman = tglPengiriman;
    }
}
