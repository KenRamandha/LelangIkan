package com.example.myapplication.model.panitia;


import static com.example.myapplication.util.ServerAPI.URL_GET_PHOTO;

import com.google.gson.annotations.SerializedName;

public class PanitiaUbahSaldoPesertaModel {

    @SerializedName("peserta_id")
    private String peserta_id;

    @SerializedName("nominal_deposit")
    private String nominal_deposit;

    @SerializedName("deposit")
    private String deposit;

    @SerializedName("stats")
    private Boolean stats;

    @SerializedName("message")
    private String message;

    public PanitiaUbahSaldoPesertaModel(String peserta_id, String nominal_deposit, String deposit) {
        this.peserta_id = peserta_id;
        this.nominal_deposit = nominal_deposit;
        this.deposit = deposit;
    }

    public Boolean getStats() {
        return stats;
    }

    public String getMessage() {
        return message;
    }
}
