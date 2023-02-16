package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class GeneralModel {

    @SerializedName("code")
    private String code;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private Boolean status;

    public String getCode() {
        return code;
    }

    public Boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
