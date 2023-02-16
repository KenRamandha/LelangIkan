
package com.example.myapplication.model.pelelang;

import com.google.gson.annotations.SerializedName;

public class ProsesTransaksiRequest {

    @SerializedName("nama_kendaraan")
    private String mNamaKendaraan;
    @SerializedName("no_pol")
    private String mNoPol;
    @SerializedName("pelelang")
    private String mPelelang;
    @SerializedName("pengirim")
    private String mPengirim;
    @SerializedName("status_transaksi")
    private String mStatusTransaksi;
    @SerializedName("telp")
    private String mTelp;
    @SerializedName("tgl_kirim")
    private String mTglKirim;

    public ProsesTransaksiRequest(String mNamaKendaraan, String mNoPol, String mPelelang, String mPengirim, String mStatusTransaksi, String mTelp, String mTglKirim) {
        this.mNamaKendaraan = mNamaKendaraan;
        this.mNoPol = mNoPol;
        this.mPelelang = mPelelang;
        this.mPengirim = mPengirim;
        this.mStatusTransaksi = mStatusTransaksi;
        this.mTelp = mTelp;
        this.mTglKirim = mTglKirim;
    }

    public String getNamaKendaraan() {
        return mNamaKendaraan;
    }

    public void setNamaKendaraan(String namaKendaraan) {
        mNamaKendaraan = namaKendaraan;
    }

    public String getNoPol() {
        return mNoPol;
    }

    public void setNoPol(String noPol) {
        mNoPol = noPol;
    }

    public String getPelelang() {
        return mPelelang;
    }

    public void setPelelang(String pelelang) {
        mPelelang = pelelang;
    }

    public String getPengirim() {
        return mPengirim;
    }

    public void setPengirim(String pengirim) {
        mPengirim = pengirim;
    }

    public String getStatusTransaksi() {
        return mStatusTransaksi;
    }

    public void setStatusTransaksi(String statusTransaksi) {
        mStatusTransaksi = statusTransaksi;
    }

    public String getTelp() {
        return mTelp;
    }

    public void setTelp(String telp) {
        mTelp = telp;
    }

    public String getTglKirim() {
        return mTglKirim;
    }

    public void setTglKirim(String tglKirim) {
        mTglKirim = tglKirim;
    }

}
