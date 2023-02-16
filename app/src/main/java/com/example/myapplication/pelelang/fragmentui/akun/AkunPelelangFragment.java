package com.example.myapplication.pelelang.fragmentui.akun;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.intro.LoginActivity;
import com.example.myapplication.intro.PeranActivity;
import com.example.myapplication.pelelang.hostui.Activity_Penilaian_Lelang;
import com.example.myapplication.pelelang.hostui.AkunPelelangActivity;
import com.example.myapplication.pelelang.hostui.PembayaranPelelangActivity;

public class AkunPelelangFragment extends Fragment {

    SharedPreferences sharedPreferences;
    String username;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_akun_pelelang, container, false);

        ImageButton btnEditAkun = (ImageButton) view.findViewById(R.id.btn_edit_akunPelelang);
        ImageButton btnTarikSaldo = (ImageButton) view.findViewById(R.id.btn_tariksaldo_profile);
        ImageButton btnTestimoniPelelang = (ImageButton) view.findViewById(R.id.btn_PenilaianLelang);
        Button btnKeluar = (Button) view.findViewById(R.id.btn_keluar);
        TextView tvNama = (TextView) view.findViewById(R.id.tvNama);
        TextView tvKota = (TextView) view.findViewById(R.id.tvKota);

        sharedPreferences = this.getActivity().getSharedPreferences(LoginActivity.myLelang, Context.MODE_PRIVATE);

        tvNama.setText(sharedPreferences.getString(LoginActivity.TAG_USERNAME,""));
        tvKota.setText(sharedPreferences.getString(LoginActivity.TAG_KOTA,""));

        btnEditAkun.setOnClickListener(v -> {
            Intent akunPelelang = new Intent(getActivity(), AkunPelelangActivity.class);
            startActivity(akunPelelang);
        });

        btnTarikSaldo.setOnClickListener(v -> {
            Intent TarikSaldoPelelang = new Intent(getActivity(), PembayaranPelelangActivity.class);
            startActivity(TarikSaldoPelelang);
        });

        btnTestimoniPelelang.setOnClickListener(v -> {
            Intent testimonilelang = new Intent(getActivity(), Activity_Penilaian_Lelang.class);
            startActivity(testimonilelang);
        });

        btnKeluar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(LoginActivity.session_status, false);
                editor.putString(LoginActivity.TAG_USERNAME, null);
                editor.commit();
                getActivity().finish();
                startActivity(new Intent(view.getContext(), PeranActivity.class));
            }
        });
        return view;
    }
}