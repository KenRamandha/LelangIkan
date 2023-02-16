
package com.example.myapplication.model.pelelang;

import com.google.gson.annotations.SerializedName;

public class UpdateNamaPengirim {

    @SerializedName("nama_pengirim")
    private String mNamaPengirim;

    public String getNamaPengirim() {
        return mNamaPengirim;
    }

    public void setNamaPengirim(String namaPengirim) {
        mNamaPengirim = namaPengirim;
    }

}
