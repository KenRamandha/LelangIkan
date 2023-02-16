package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class LoginModel {

    @SerializedName("kode")
    private String kode;

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    @SerializedName("nama")
    private String nama;

    @SerializedName("role")
    private String role;

    @SerializedName("stats")
    private Boolean stats;

    @SerializedName("kota")
    private String kota;

    @SerializedName("prov")
    private String prov;

    public LoginModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getKode() {
        return kode;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getNama() {
        return nama;
    }

    public String getRole() {
        return role;
    }

    public Boolean getStats() {
        return stats;
    }

    public String getKota() {
        return kota;
    }

    public String getProv() {
        return prov;
    }
}