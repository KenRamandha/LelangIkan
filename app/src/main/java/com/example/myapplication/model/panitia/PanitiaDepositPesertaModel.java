package com.example.myapplication.model.panitia;


import static com.example.myapplication.util.ServerAPI.URL_GET_PHOTO;

import com.google.gson.annotations.SerializedName;

public class PanitiaDepositPesertaModel {

    @SerializedName("peserta_id")
    private String peserta_id;

    @SerializedName("nominal_deposit")
    private String nominal_deposit;

    @SerializedName("bukti_pembayaran")
    private String bukti_pembayaran;

    @SerializedName("nama")
    private String nama;

    @SerializedName("keterangan")
    private String keterangan;

    @SerializedName("deposit")
    private String deposit;

    @SerializedName("status")
    private String status;

    @SerializedName("panitia_id")
    private String panitia_id;

    @SerializedName("stats")
    private Boolean stats;

    @SerializedName("message")
    private String message;

    public PanitiaDepositPesertaModel(String peserta_id,String keterangan, String panitia_id) {
        this.peserta_id = peserta_id;
        this.keterangan = keterangan;
        this.panitia_id = panitia_id;
    }

    public String getPeserta_id() {
        return peserta_id;
    }

    public String getNominal_deposit() {
        return nominal_deposit;
    }

    public String getBukti_pembayaran() {
        return URL_GET_PHOTO + "peserta/bukti_transfer_deposit/" + bukti_pembayaran;
    }

    public String getNama() {
        return nama;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public String getDeposit() {
        return  deposit;
    }

    public String getStatus() {
        return status;
    }

    public String getPanitia_id() {
        return panitia_id;
    }

    public Boolean getStats() {
        return stats;
    }

    public String getMessage() {
        return message;
    }
}
