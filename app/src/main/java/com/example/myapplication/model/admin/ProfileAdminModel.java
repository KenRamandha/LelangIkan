package com.example.myapplication.model.admin;

import com.google.gson.annotations.SerializedName;

public class ProfileAdminModel {
    @SerializedName("userid")
    private String userid;

    @SerializedName("nama")
    private String nama;

    @SerializedName("telp")
    private String telp;

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String  password;








    public String getNama() {
        return nama;
    }

    public String getUserid() {
        return userid;
    }

    public String getTelp()
    {
        return telp;
    }

    public String getUsername()
    {
        return username;
    }


}
