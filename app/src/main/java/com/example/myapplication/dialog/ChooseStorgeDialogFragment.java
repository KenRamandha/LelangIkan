package com.example.myapplication.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.myapplication.databinding.DialogChooseStorgeBinding;

public class ChooseStorgeDialogFragment extends DialogFragment {

    DialogChooseStorgeBinding binding;
    private ChooseStorageListener chooseStorageListener;

    public static ChooseStorgeDialogFragment newInstance() {
        ChooseStorgeDialogFragment fragment = new ChooseStorgeDialogFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btGallery.setOnClickListener(v -> {
            chooseStorageListener.onSelectGallery();
            dismiss();
        });

//        binding.btStorage.setOnClickListener(v -> {
//            chooseStorageListener.onSelectStorage();
//            dismiss();
//        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DialogChooseStorgeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    public void setListener(ChooseStorageListener chooseStorageListener) {
        this.chooseStorageListener = chooseStorageListener;
    }
}