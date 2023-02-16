package com.example.myapplication.model.pelelang;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ListTransaksiResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("code")
    private int code;

    @SerializedName("data")
    private ArrayList<ArrayList<ListDataTransaksi>> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ArrayList<ArrayList<ListDataTransaksi>> getData() {
        return data;
    }

    public void setData(ArrayList<ArrayList<ListDataTransaksi>> data) {
        this.data = data;
    }
}
