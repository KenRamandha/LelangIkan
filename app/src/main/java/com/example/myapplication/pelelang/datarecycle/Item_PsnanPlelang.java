package com.example.myapplication.pelelang.datarecycle;

import android.os.Parcel;
import android.os.Parcelable;

public class Item_PsnanPlelang implements Parcelable {

    private String nmPetani_itemPesanan, nmIkan_itemPesanan, totalHrg_itemPesanan, hrga_itemPesanan;
    private int img_itemPesanan;

    public Item_PsnanPlelang(Parcel in) {
        nmPetani_itemPesanan = in.readString();
        nmIkan_itemPesanan = in.readString();
        totalHrg_itemPesanan = in.readString();
        hrga_itemPesanan = in.readString();
        img_itemPesanan = in.readInt();
    }

//    public Item_PsnanPlelang() {
//
//    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nmPetani_itemPesanan);
        dest.writeString(nmIkan_itemPesanan);
        dest.writeString(totalHrg_itemPesanan);
        dest.writeString(hrga_itemPesanan);
        dest.writeInt(img_itemPesanan);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Item_PsnanPlelang> CREATOR = new Creator<Item_PsnanPlelang>() {
        @Override
        public Item_PsnanPlelang createFromParcel(Parcel in) {
            return new Item_PsnanPlelang(in);
        }

        @Override
        public Item_PsnanPlelang[] newArray(int size) {
            return new Item_PsnanPlelang[size];
        }
    };

    public String getNmPetani_itemPesanan() {
        return nmPetani_itemPesanan;
    }

    public void setNmPetani_itemPesanan(String nmPetani_itemPesanan) {
        this.nmPetani_itemPesanan = nmPetani_itemPesanan;
    }

    public String getNmIkan_itemPesanan() {
        return nmIkan_itemPesanan;
    }

    public void setNmIkan_itemPesanan(String nmIkan_itemPesanan) {
        this.nmIkan_itemPesanan = nmIkan_itemPesanan;
    }

    public String getTotalHrg_itemPesanan() {
        return totalHrg_itemPesanan;
    }

    public void setTotalHrg_itemPesanan(String totalHrg_itemPesanan) {
        this.totalHrg_itemPesanan = totalHrg_itemPesanan;
    }

    public String getHrga_itemPesanan() {
        return hrga_itemPesanan;
    }

    public void setHrga_itemPesanan(String hrga_itemPesanan) {
        this.hrga_itemPesanan = hrga_itemPesanan;
    }

    public int getImg_itemPesanan() {
        return img_itemPesanan;
    }

    public void setImg_itemPesanan(int img_itemPesanan) {
        this.img_itemPesanan = img_itemPesanan;
    }
}
