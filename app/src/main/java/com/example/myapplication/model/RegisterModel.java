package com.example.myapplication.model;

import com.google.gson.annotations.SerializedName;

public class RegisterModel {

    @SerializedName("role")
    private int role;

    @SerializedName("nama")
    private String nama;

    @SerializedName("nik")
    private String nik;

    @SerializedName("npwp")
    private String npwp;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;

    @SerializedName("telp")
    private String telp;

    @SerializedName("email")
    private String email;

    @SerializedName("stats")
    private Boolean stats;

    @SerializedName("message")
    private String message;

    public RegisterModel(int role, String nama, String nik, String npwp, String alamat, String username, String password, String telp, String email) {
        this.role = role;
        this.nama = nama;
        this.nik = nik;
        this.npwp = npwp;
        this.alamat = alamat;
        this.username = username;
        this.password = password;
        this.telp = telp;
        this.email = email;
    }

    public Boolean getStats() {
        return stats;
    }

    public String getMessage() {
        return message;
    }
}
