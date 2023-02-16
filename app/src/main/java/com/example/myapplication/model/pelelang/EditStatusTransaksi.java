
package com.example.myapplication.model.pelelang;

import com.google.gson.annotations.SerializedName;

public class EditStatusTransaksi {

    @SerializedName("status_transaksi")
    private String mStatusTransaksi;

    public String getStatusTransaksi() {
        return mStatusTransaksi;
    }

    public void setStatusTransaksi(String statusTransaksi) {
        mStatusTransaksi = statusTransaksi;
    }

}
