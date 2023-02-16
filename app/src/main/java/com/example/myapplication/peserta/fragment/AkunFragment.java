package com.example.myapplication.peserta.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.intro.PeranActivity;
import com.example.myapplication.model.peserta.PesertaModel;
import com.example.myapplication.peserta.activity.MainActivity;
import com.example.myapplication.peserta.activity.PesertaTransaksiActivity;
import com.example.myapplication.peserta.activity.TopUpActivity;
import com.example.myapplication.peserta.activity.DaftarMenangActivity;
import com.example.myapplication.peserta.activity.EditAkunActivity;
import com.example.myapplication.intro.LoginActivity;
import com.example.myapplication.peserta.activity.TestimoniActivity;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.text.DecimalFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AkunFragment extends Fragment {
    private ImageButton btnEditAkun,btnTopup,btnMenang,btnTestimoni,btnTransaksi;
//    private Button btnKeluar;
    private TextView tvNama, tvSaldo;
    private List<PesertaModel> pesertaModels;
    private LinearLayout lnEdit,lnDepo,lnMenang,lnTestimoni,lnTransaksi;

    SharedPreferences sharedPreferences;
    String username,deposit;
    String path;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_akun, container, false);

        btnEditAkun = view.findViewById(R.id.btn_edit_akun);
        btnTopup = view.findViewById(R.id.btn_topup_profile);
        btnMenang = view.findViewById(R.id.btn_menang_profile);
        btnTestimoni = view.findViewById(R.id.btn_testimoni_profile);
        btnTransaksi = view.findViewById(R.id.btn_transaksi_profile);
//        btnKeluar = view.findViewById(R.id.btn_keluar);
        tvNama = view.findViewById(R.id.tv_nama_akun);
        tvSaldo = view.findViewById(R.id.tv_saldo_deposit_profile);

        lnEdit = view.findViewById(R.id.ln_edit_akun_peserta);
        lnDepo = view.findViewById(R.id.ln_depo_akun_peserta);
        lnMenang = view.findViewById(R.id.ln_menang_akun_peserta);
        lnTestimoni = view.findViewById(R.id.ln_testimoni_akun_peserta);
        lnTransaksi = view.findViewById(R.id.ln_transaksi_akun_peserta);

        sharedPreferences = this.getActivity().getSharedPreferences(LoginActivity.myLelang, Context.MODE_PRIVATE);
        username = sharedPreferences.getString(LoginActivity.TAG_USERNAME,"");
        path = sharedPreferences.getString(LoginActivity.TAG_KODE, "");
        tvNama.setText(username);

        loadDeposit();

        btnEditAkun.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent editakun = new Intent(getActivity(), EditAkunActivity.class);
                startActivity(editakun);
            }
        });

//        lnEdit.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent editakun1 = new Intent(getActivity(), EditAkunActivity.class);
//                startActivity(editakun1);
//            }
//        });

        lnEdit.setOnClickListener(view1 ->{
            Intent editakun1 = new Intent(getActivity(), EditAkunActivity.class);
            startActivity(editakun1);
        });

        btnTopup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent depo = new Intent(getActivity(), TopUpActivity.class);
                startActivity(depo);
            }
        });

        lnDepo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent depo1 = new Intent(getActivity(), TopUpActivity.class);
                startActivity(depo1);
            }
        });

        btnMenang.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent menang = new Intent(getActivity(), DaftarMenangActivity.class);
                startActivity(menang);
            }
        });

        lnMenang.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent menang1 = new Intent(getActivity(), DaftarMenangActivity.class);
                startActivity(menang1);
            }
        });

        btnTestimoni.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent testimoni = new Intent(getActivity(), TestimoniActivity.class);
                startActivity(testimoni);
            }
        });

        lnTestimoni.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent testimoni1 = new Intent(getActivity(), TestimoniActivity.class);
                startActivity(testimoni1);
            }
        });

        btnTransaksi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent transaksi = new Intent(getActivity(), PesertaTransaksiActivity.class);
                startActivity(transaksi);
            }
        });
        lnTransaksi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent transaksi1 = new Intent(getActivity(), PesertaTransaksiActivity.class);
                startActivity(transaksi1);
            }
        });
        view.findViewById(R.id.btn_keluar).setOnClickListener(view1 -> {
            sharedPreferences.edit().clear().commit();
            startActivity(new Intent(getActivity(), MainActivity.class));

        });
        return view;
    }

    private void loadDeposit() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<PesertaModel> pesertaModelCall = retrofitAPI.getPeserta(path);
        pesertaModelCall.enqueue(new Callback<PesertaModel>() {
            @Override
            public void onResponse(Call<PesertaModel> call, Response<PesertaModel> response) {
                if (response.isSuccessful()){
                    DecimalFormat decimalFormat = new DecimalFormat("#,##0");
                    tvSaldo.setText(decimalFormat.format(Integer.parseInt(response.body().getDeposit())));
                }
            }

            @Override
            public void onFailure(Call<PesertaModel> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}