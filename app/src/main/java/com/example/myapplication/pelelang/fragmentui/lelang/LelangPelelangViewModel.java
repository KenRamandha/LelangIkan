package com.example.myapplication.pelelang.fragmentui.lelang;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LelangPelelangViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public LelangPelelangViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}