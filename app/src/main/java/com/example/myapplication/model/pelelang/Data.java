
package com.example.myapplication.model.pelelang;


import com.google.gson.annotations.SerializedName;


public class Data {

    @SerializedName("lelang_id")
    private String mLelangId;
    @SerializedName("nama_kendaraan")
    private String mNamaKendaraan;
    @SerializedName("nama_pengirim")
    private String mNamaPengirim;
    @SerializedName("no_hp")
    private String mNoHp;
    @SerializedName("no_polisi")
    private String mNoPolisi;
    @SerializedName("pelelang_id")
    private String mPelelangId;
    @SerializedName("pengiriman_id")
    private String mPengirimanId;
    @SerializedName("status_transaksi")
    private String mStatusTransaksi;
    @SerializedName("tgl_pengiriman")
    private String mTglPengiriman;

    public String getLelangId() {
        return mLelangId;
    }

    public void setLelangId(String lelangId) {
        mLelangId = lelangId;
    }

    public String getNamaKendaraan() {
        return mNamaKendaraan;
    }

    public void setNamaKendaraan(String namaKendaraan) {
        mNamaKendaraan = namaKendaraan;
    }

    public String getNamaPengirim() {
        return mNamaPengirim;
    }

    public void setNamaPengirim(String namaPengirim) {
        mNamaPengirim = namaPengirim;
    }

    public String getNoHp() {
        return mNoHp;
    }

    public void setNoHp(String noHp) {
        mNoHp = noHp;
    }

    public String getNoPolisi() {
        return mNoPolisi;
    }

    public void setNoPolisi(String noPolisi) {
        mNoPolisi = noPolisi;
    }

    public String getPelelangId() {
        return mPelelangId;
    }

    public void setPelelangId(String pelelangId) {
        mPelelangId = pelelangId;
    }

    public String getPengirimanId() {
        return mPengirimanId;
    }

    public void setPengirimanId(String pengirimanId) {
        mPengirimanId = pengirimanId;
    }

    public String getStatusTransaksi() {
        return mStatusTransaksi;
    }

    public void setStatusTransaksi(String statusTransaksi) {
        mStatusTransaksi = statusTransaksi;
    }

    public String getTglPengiriman() {
        return mTglPengiriman;
    }

    public void setTglPengiriman(String tglPengiriman) {
        mTglPengiriman = tglPengiriman;
    }

}
