
package com.example.myapplication.model.pelelang;

import com.google.gson.annotations.SerializedName;

public class BiayaAdm {

    @SerializedName("biaya_adm")
    private String mBiayaAdm;

    public String getBiayaAdm() {
        return mBiayaAdm;
    }

    public void setBiayaAdm(String biayaAdm) {
        mBiayaAdm = biayaAdm;
    }

}
