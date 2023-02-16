package com.example.myapplication.pelelang.fragmentui.akun;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.intro.LoginActivity;
import com.example.myapplication.pelelang.hostui.EditAkunPelelang;

public class ProfilePelelangFragment extends Fragment {
    SharedPreferences sharedPreferences;
    private String path;
    String username;
    EditText Edit_NamaPelelang,ed_provinsiPelelang,ed_alamatPelelang,ed_notelpPelelang,ed_emailPelelang,ed_nikPelelang;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_edit_akun_pelelang, container, false);

//        Button btnUpdateProfile =  view.findViewById(R.id.btn_updateProfile);
        Button btnSimpanProfile = view.findViewById(R.id.btn_simpanDataPelelang);
//        ImageButton btnKembaliPelelangAkun = view.findViewById(R.id.btn_back);

        sharedPreferences = this.getActivity().getSharedPreferences(LoginActivity.myLelang, Context.MODE_PRIVATE);
        username = sharedPreferences.getString(LoginActivity.TAG_USERNAME,"");

        btnSimpanProfile.setOnClickListener(v -> {
            Intent akunPelelang = new Intent(getActivity(), EditAkunPelelang.class);
            startActivity(akunPelelang);
        });

//        btnUpdateProfile.setOnClickListener(v -> {
//            Intent EditProfilepelelang = new Intent(getActivity(), EditAkunPelelang.class);
//            startActivity(EditProfilepelelang);
//        });

//        btnKembaliPelelangAkun.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putBoolean(LoginActivity.session_status, false);
//                editor.putString(LoginActivity.TAG_USERNAME, null);
//                editor.commit();
//                getActivity().finish();
//                startActivity(new Intent(view.getContext(), PelelangActivity.class));
//            }
//        });
        return view;

    }
}
