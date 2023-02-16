
package com.example.myapplication.model.pelelang;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailTransaksiResponse {

    @SerializedName("biaya_adm")
    private List<BiayaAdm> mBiayaAdm;
    @SerializedName("code")
    private Long mCode;
    @SerializedName("data")
    private List<List<Datum>> mData;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("status")
    private Boolean mStatus;

    public List<BiayaAdm> getBiayaAdm() {
        return mBiayaAdm;
    }

    public void setBiayaAdm(List<BiayaAdm> biayaAdm) {
        mBiayaAdm = biayaAdm;
    }

    public Long getCode() {
        return mCode;
    }

    public void setCode(Long code) {
        mCode = code;
    }

    public List<List<Datum>> getData() {
        return mData;
    }

    public void setData(List<List<Datum>> data) {
        mData = data;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public Boolean getStatus() {
        return mStatus;
    }

    public void setStatus(Boolean status) {
        mStatus = status;
    }

}
