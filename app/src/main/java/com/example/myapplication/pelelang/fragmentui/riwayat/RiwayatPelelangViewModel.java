package com.example.myapplication.pelelang.fragmentui.riwayat;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RiwayatPelelangViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public RiwayatPelelangViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is riwayat fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}