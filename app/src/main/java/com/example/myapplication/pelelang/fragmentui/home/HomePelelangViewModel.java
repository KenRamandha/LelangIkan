package com.example.myapplication.pelelang.fragmentui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomePelelangViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomePelelangViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}