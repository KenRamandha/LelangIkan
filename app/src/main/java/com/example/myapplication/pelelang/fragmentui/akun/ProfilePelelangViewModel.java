package com.example.myapplication.pelelang.fragmentui.akun;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class ProfilePelelangViewModel extends Fragment {
    private final MutableLiveData<String> mText;

    public ProfilePelelangViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
