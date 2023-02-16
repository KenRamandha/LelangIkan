
package com.example.myapplication.model.pelelang;

import com.google.gson.annotations.SerializedName;

public class KonfirmasiPembayaran {

    @SerializedName("nama_kendaraan")
    private String mNamaKendaraan;
    @SerializedName("no_pol")
    private String mNoPol;
    @SerializedName("pengirim")
    private String mPengirim;
    @SerializedName("status_transaksi")
    private String mStatusTransaksi;
    @SerializedName("telp")
    private String mTelp;
    @SerializedName("tgl_kirim")
    private String mTglKirim;

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
