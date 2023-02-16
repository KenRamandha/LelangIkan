package com.example.myapplication.model.pelelang;
import com.google.gson.annotations.SerializedName;

public class JenisUsahaModel {
    @SerializedName("id")
    private Integer id;

    @SerializedName("jenis")
    private String jenis;

    public Integer getKodeJenisUsaha(){return id;}
    public String getJenisUsaha(){return jenis;}
}
