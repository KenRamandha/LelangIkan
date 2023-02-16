
package com.example.myapplication.model.pelelang;

import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("bukti_pembayaran")
    private String mBuktiPembayaran;
    @SerializedName("jumlah")
    private String mJumlah;
    @SerializedName("lelang_id")
    private String mLelangId;
    @SerializedName("metode_bayar")
    private Integer mMetodeBayar;
    @SerializedName("nama_kendaraan")
    private String mNamaKendaraan;
    @SerializedName("nama_pengirim")
    private String mNamaPengirim;
    @SerializedName("no_hp")
    private String mNoHp;
    @SerializedName("no_polisi")
    private String mNoPolisi;
    @SerializedName("nominal_dibayarkan")
    private String mNominalDibayarkan;
    @SerializedName("pelelang_id")
    private String mPelelangId;
    @SerializedName("pengiriman_id")
    private String mPengirimanId;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("status_transaksi")
    private String mStatusTransaksi;
    @SerializedName("tgl_pengiriman")
    private String mTglPengiriman;

    public String getmTglPengiriman() {
        return mTglPengiriman;
    }

    public void setmTglPengiriman(String mTglPengiriman) {
        this.mTglPengiriman = mTglPengiriman;
    }

    public String getBuktiPembayaran() {
        return mBuktiPembayaran;
    }

    public void setBuktiPembayaran(String buktiPembayaran) {
        mBuktiPembayaran = buktiPembayaran;
    }

    public String getJumlah() {
        return mJumlah;
    }

    public void setJumlah(String jumlah) {
        mJumlah = jumlah;
    }

    public String getLelangId() {
        return mLelangId;
    }

    public void setLelangId(String lelangId) {
        mLelangId = lelangId;
    }

    public Integer getMetodeBayar() {
        return mMetodeBayar;
    }

    public  Integer setMetodeBayar() {return mMetodeBayar;}
//    public Integer setMetodeBayar(Integer metodeBayar) {
//        mMetodeBayar = metodeBayar;
//    }

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

    public String getNominalDibayarkan() {
        return mNominalDibayarkan;
    }

    public void setNominalDibayarkan(String nominalDibayarkan) {
        mNominalDibayarkan = nominalDibayarkan;
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

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getStatusTransaksi() {
        return mStatusTransaksi;
    }

    public void setStatusTransaksi(String statusTransaksi) {
        mStatusTransaksi = statusTransaksi;
    }


}
