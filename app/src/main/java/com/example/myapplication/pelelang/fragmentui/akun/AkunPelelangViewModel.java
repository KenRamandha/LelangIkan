package com.example.myapplication.pelelang.fragmentui.akun;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AkunPelelangViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AkunPelelangViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}