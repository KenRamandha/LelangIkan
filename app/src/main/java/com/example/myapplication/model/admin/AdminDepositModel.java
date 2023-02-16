package com.example.myapplication.model.admin;

import static com.example.myapplication.util.ServerAPI.URL_GET_GAMBAR;
import static com.example.myapplication.util.ServerAPI.URL_GET_PHOTO;

import com.google.gson.annotations.SerializedName;

public class AdminDepositModel {



    @SerializedName("deposit_id")
    private String deposit_id;

    @SerializedName("peserta_id")
    private String peserta_id;


    @SerializedName("nama")
    private String nama;

    @SerializedName("tgl_deposit")
    private String tgl_deposit;

    @SerializedName("nominal_deposit")
    private String nominal_deposit;

    @SerializedName("bukti_pembayaran")
    private String bukti_pembayaran;

    @SerializedName("status_deposit")
    private String status_deposit;

    @SerializedName("panitia_id")
    private String panitia_id;

    @SerializedName("keterangan")
    private String keterangan;


    public String getNama() {
        return nama;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public String getDeposit_id() {
        return deposit_id;
    }

    public String getPeserta_id() {
        return peserta_id;
    }

    public String getTgl_deposit() {
        return tgl_deposit;
    }

    public String getNominal_deposit() {
        return nominal_deposit;
    }


    public String getBukti_pembayaran() {
        return URL_GET_PHOTO + "peserta/bukti_transfer_deposit/" + bukti_pembayaran;
    }

//    public String getBukti_pembayaran() {
//        return bukti_pembayaran;
//    }

    public String getStatus_deposit() {
        return status_deposit;
    }

    public String getPanitia_id() {
        return panitia_id;
    }


}


