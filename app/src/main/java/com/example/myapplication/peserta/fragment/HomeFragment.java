package com.example.myapplication.peserta.fragment;

import static com.example.myapplication.util.SharedPreferences.TAG_KODE;
import static com.example.myapplication.util.SharedPreferences.TAG_USERNAME;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.myapplication.R;
import com.example.myapplication.intro.LoginActivity;
import com.example.myapplication.peserta.adapter.AdapterCardPenawaranLelang;
import com.example.myapplication.model.peserta.KatalogModel;
import com.example.myapplication.model.peserta.PesertaModel;
import com.example.myapplication.peserta.adapter.FragmentSlider;
import com.example.myapplication.peserta.adapter.SliderPagerAdapter;

import com.example.myapplication.peserta.activity.BannerSlider;
import com.example.myapplication.peserta.activity.SliderIndicator;
import com.example.myapplication.peserta.activity.TopUpActivity;
import com.example.myapplication.peserta.activity.DetailLelangActivity;
import com.example.myapplication.peserta.activity.TestimoniActivity;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements AdapterCardPenawaranLelang.ItemClickListener {

    private LinearLayout linearKetentuan;

    private ImageButton btnKetentuan, btnTopup, btnTestimoni, btnJadwal;
    private Button btnSelengkapnya;
    private TextView greetText, tvNama, tvSaldo;

    private RecyclerView recyclerView;
    private AdapterCardPenawaranLelang adapter;
    private ArrayList<KatalogModel> katalogModelArrayList;

    String path,deposit;

    private Dialog dialog;

    public static String username;
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        linearKetentuan = view.findViewById(R.id.ln_ketentuan);

        btnKetentuan = view.findViewById(R.id.btn_ketentuan);
        btnTopup = view.findViewById(R.id.btn_topup);
        btnTestimoni = view.findViewById(R.id.btn_testimoni_home);
        btnJadwal = view.findViewById(R.id.btn_jadwal_home);
        btnSelengkapnya = view.findViewById(R.id.btn_selengkapnya);
        greetText = view.findViewById(R.id.greeting_text);
        tvNama = view.findViewById(R.id.nama_peserta);
        tvSaldo = view.findViewById(R.id.tv_saldo_deposit);

        recyclerView = view.findViewById(R.id.rv_katalog_home);
        recyclerView.setHasFixedSize(true);

        dialog = new Dialog(getActivity());

//        loadDeposit();

        btnTopup.setOnClickListener(view1 -> {
            Intent topup = new Intent(getActivity(), TopUpActivity.class);
            startActivity(topup);
        });

        btnTestimoni.setOnClickListener(view1 -> {
            Intent testimoni = new Intent(getActivity(), TestimoniActivity.class);
            startActivity(testimoni);
        });

        btnJadwal.setOnClickListener(view1 -> {
            FragmentTransaction jadwal = getFragmentManager().beginTransaction();
            jadwal.replace(R.id.fl_container, new LelangFragment());
            jadwal.commit();
        });

        btnSelengkapnya.setOnClickListener(view1 -> {
            FragmentTransaction selengkapnya = getFragmentManager().beginTransaction();
            selengkapnya.replace(R.id.fl_container, new LelangFragment());
            selengkapnya.commit();
        });

        btnKetentuan.setOnClickListener(view1 -> openKetentuanDialog());

//        generateItem();
//        setupSlider();
        greeting();
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

    private void generateItem() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ArrayList<KatalogModel>> katalogListCall = retrofitAPI.getAllProduct();
        katalogListCall.enqueue(new Callback<ArrayList<KatalogModel>>() {
            @Override
            public void onResponse(Call<ArrayList<KatalogModel>> call, Response<ArrayList<KatalogModel>> response) {
                if (response.isSuccessful()) {
                    katalogModelArrayList = response.body();
                    for (int i = 0; i < katalogModelArrayList.size(); i++) {
                        adapter = new AdapterCardPenawaranLelang(getActivity(), katalogModelArrayList);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recyclerView.setAdapter(adapter);
                        adapter.setClickListener(HomeFragment.this);
                    }
                } else {
                    Toast.makeText(getActivity(), "Fail to get data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<KatalogModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void openKetentuanDialog() {
        dialog.setContentView(R.layout.dialog_ketentuan_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        Button btnKembaliKetentuan = dialog.findViewById(R.id.btn_kembali_dketentuan);
        btnKembaliKetentuan.setOnClickListener(view1 -> dialog.dismiss());
    }

//    private void setupSlider() {
//        bannerSlider.setDurationScroll(800);
//        List<Fragment> fragments = new ArrayList<>();
//        //link image
//        fragments.add(FragmentSlider.newInstance("https://gobiz.co.id/pusat-pengetahuan/wp-content/uploads/2019/12/perilaku-konsumen-era-digital.jpg"));
//        fragments.add(FragmentSlider.newInstance("https://gobiz.co.id/pusat-pengetahuan/wp-content/uploads/2019/12/perilaku-konsumen-era-digital.jpg"));
//        fragments.add(FragmentSlider.newInstance("https://gobiz.co.id/pusat-pengetahuan/wp-content/uploads/2019/12/perilaku-konsumen-era-digital.jpg"));
//        fragments.add(FragmentSlider.newInstance("https://gobiz.co.id/pusat-pengetahuan/wp-content/uploads/2019/12/perilaku-konsumen-era-digital.jpg"));
//
//        mAdapter = new SliderPagerAdapter(getParentFragmentManager(), fragments);
//        bannerSlider.setAdapter(mAdapter);
//        mIndicator = new SliderIndicator(getActivity(), mLinearLayout, bannerSlider, R.drawable.indicator_slider);
//        mIndicator.setPageCount(fragments.size());
//        mIndicator.show();
//    }

    private void greeting() {
        Calendar calendar = Calendar.getInstance();
        int timeOfDay = calendar.get(Calendar.HOUR_OF_DAY);

        if (timeOfDay >= 0 && timeOfDay < 12) {
            greetText.setText(getString(R.string.salam_pagi));
        } else if (timeOfDay >= 12 && timeOfDay < 15) {
            greetText.setText(getString(R.string.salam_siang));
        } else if (timeOfDay >= 15 && timeOfDay < 18) {
            greetText.setText(getString(R.string.salam_sore));
        } else if (timeOfDay >= 18 && timeOfDay < 24) {
            greetText.setText(getString(R.string.salam_malam));
        }
    }

    @Override
    public void onClick(View view, int position) {
        final KatalogModel katalogModel = katalogModelArrayList.get(position);
        switch (view.getId()) {
            default:
                Intent intent = new Intent(getActivity(), DetailLelangActivity.class);
                intent.putExtra("lelang_id", katalogModel.getLelang_id());
                intent.putExtra("image1", katalogModel.getImage1());
                intent.putExtra("image2", katalogModel.getImage2());
                intent.putExtra("image3", katalogModel.getImage3());
                intent.putExtra("image4", katalogModel.getImage4());
                intent.putExtra("produk", katalogModel.getProduk());
                intent.putExtra("waktu_awal", katalogModel.getTgl_mulai());
                intent.putExtra("waktu_selesai", katalogModel.getTgl_selesai());
                intent.putExtra("harga_awal", katalogModel.getHarga_awal());
                intent.putExtra("pelelang_id", katalogModel.getPelelang_id());
                intent.putExtra("deskripsi_produk", katalogModel.getDeskripsi_produk());
                intent.putExtra("bayar_sekarang", katalogModel.getHarga_beli_sekarang());
                intent.putExtra("jumlah", katalogModel.getJumlah());
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        sharedPreferences = getActivity().getSharedPreferences(LoginActivity.myLelang, Context.MODE_PRIVATE);
        username = sharedPreferences.getString(TAG_USERNAME, "");
        path = sharedPreferences.getString(TAG_KODE, "");
        tvNama.setText(username);
        loadDeposit();
        generateItem();
    }
}