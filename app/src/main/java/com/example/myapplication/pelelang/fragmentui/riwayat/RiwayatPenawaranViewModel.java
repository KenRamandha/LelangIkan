package com.example.myapplication.pelelang.fragmentui.riwayat;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RiwayatPenawaranViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public RiwayatPenawaranViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is riwayat penawaran fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}