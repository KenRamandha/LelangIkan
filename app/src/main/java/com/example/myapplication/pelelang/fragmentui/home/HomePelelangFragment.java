package com.example.myapplication.pelelang.fragmentui.home;

import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.intro.LoginActivity;
import com.example.myapplication.model.pelelang.GetProductListResponse;
import com.example.myapplication.model.pelelang.KatalogPelelangModel;
import com.example.myapplication.pelelang.InterfacePelelang;
import com.example.myapplication.pelelang.adapter.AdapterCardProdukPelelang;
import com.example.myapplication.pelelang.hostui.DetailProdukActivity;
import com.example.myapplication.pelelang.hostui.PembayaranPelelangActivity;
import com.example.myapplication.pelelang.hostui.PengirimanPelelangActivity;
import com.example.myapplication.pelelang.hostui.Pesanan_Pelelang;
import com.example.myapplication.pelelang.hostui.RiwayatPemenangActivity;
import com.example.myapplication.pelelang.hostui.TambahLelangActivity;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;
import com.google.gson.Gson;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePelelangFragment extends Fragment {

    //private BannerSlider bannerSlider;
    //private LinearLayout mLinearLayout;
    private Dialog dialog;
    private String id;
    private RecyclerView recyclerView;
    public static final String myLelang = "myLelang";
    public final static String TAG_KODE = "kode";
    private List<KatalogPelelangModel> arrayListProduk;
    private InterfacePelelang interfacePelelang;
    KatalogPelelangModel model;
    private AdapterCardProdukPelelang adapter;
    private final Gson gson = new Gson();
    private TextView greetText, tvNama;
    private Button btnTambahLelang;
    private ImageButton btnKetentuan, btnTarikSaldo, btnPesanan, btnPengiriman,btnRiwayatPememang;

    public static String username;
    SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_pelelang, container, false);
        // bannerSlider = view.findViewById(R.id.sliderView);
        //mLinearLayout = view.findViewById(R.id.pagesContainer);



        btnKetentuan = view.findViewById(R.id.btn_ketentuan);
        btnTarikSaldo = view.findViewById(R.id.btn_tarik_saldo);
        btnPesanan = view.findViewById(R.id.btn_pesanan_home);
        btnPengiriman = view.findViewById(R.id.btn_pengiriman_home);
        btnRiwayatPememang = view.findViewById(R.id.btn_riwayat_pemenang);
        greetText = view.findViewById(R.id.greeting_text_pelelang);
        tvNama = view.findViewById(R.id.nama_pelelang);

        sharedPreferences = this.getActivity().getSharedPreferences(LoginActivity.myLelang, Context.MODE_PRIVATE);
        username = sharedPreferences.getString(LoginActivity.TAG_USERNAME, "");
        tvNama.setText(username);

        btnTambahLelang = view.findViewById(R.id.btn_tambah_lelang);

        dialog = new Dialog(getActivity());

        btnTarikSaldo.setOnClickListener(v -> {
            Intent topup = new Intent(getActivity(), PembayaranPelelangActivity.class);
            startActivity(topup);
        });

        btnPesanan.setOnClickListener(v -> {
            Intent testimoni = new Intent(getActivity(), Pesanan_Pelelang.class);
            startActivity(testimoni);
        });

        btnRiwayatPememang.setOnClickListener(v ->{
            Intent pemenang =new Intent(getActivity(), RiwayatPemenangActivity.class);
            startActivity(pemenang);
        });
        btnTambahLelang.setOnClickListener(v -> {
            Intent tambah = new Intent(getActivity(), TambahLelangActivity.class);
            startActivity(tambah);
        });

        btnPengiriman.setOnClickListener(v -> {
            Intent pengiriman = new Intent(getActivity(), PengirimanPelelangActivity.class);
            startActivity(pengiriman);
        });

        btnKetentuan.setOnClickListener(view1 -> openKetentuanDialog());
        //setupSlider();
        greeting();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        btnAdd = view.findViewById(R.id.btnAddLelang);
        recyclerView =view.findViewById(R.id.rv_product_terbaru);

        sharedPreferences = requireContext().getSharedPreferences(myLelang, MODE_PRIVATE);
        id = (sharedPreferences.getString(TAG_KODE, ""));

//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), TambahLelangActivity.class);
//                startActivity(intent);
//            }
//        });
        getData();
    }

    private void getData() {

        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<GetProductListResponse> getProductListResponseCall = retrofitAPI.getAllPelelangProduk(id);

        getProductListResponseCall.enqueue(new Callback<GetProductListResponse>() {
            @Override
            public void onResponse(Call<GetProductListResponse> call, Response<GetProductListResponse> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        if (response.body().getStatus().equalsIgnoreCase("success")){
                            arrayListProduk = response.body().getData();
                            interfacePelelang = position -> {
                                model = arrayListProduk.get(position);
                                Intent intent = new Intent(requireContext(), DetailProdukActivity.class);
                                String jsonString = gson.toJson(model);
                                intent.putExtra("data",jsonString);
                                startActivity(intent);
                            };
                            adapter = new AdapterCardProdukPelelang(getActivity(),arrayListProduk,interfacePelelang);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<GetProductListResponse> call, Throwable t) {
                Log.d("TAG ", "onFailure: "+t);
                Toast.makeText(requireContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

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

    private void openKetentuanDialog() {
        dialog.setContentView(R.layout.dialog_ketentuan_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Button btnKembaliKetentuan = dialog.findViewById(R.id.btn_kembali_dketentuan);
        btnKembaliKetentuan.setOnClickListener(view -> dialog.dismiss());
        dialog.show();
    }

   /* private void setupSlider() {
        bannerSlider.setDurationScroll(800);
        List<Fragment> fragments = new ArrayList<>();
        //link image
        fragments.add(FragmentSlider.newInstance("https://gobiz.co.id/pusat-pengetahuan/wp-content/uploads/2019/12/perilaku-konsumen-era-digital.jpg"));
        fragments.add(FragmentSlider.newInstance("https://gobiz.co.id/pusat-pengetahuan/wp-content/uploads/2019/12/perilaku-konsumen-era-digital.jpg"));
        fragments.add(FragmentSlider.newInstance("https://gobiz.co.id/pusat-pengetahuan/wp-content/uploads/2019/12/perilaku-konsumen-era-digital.jpg"));
        fragments.add(FragmentSlider.newInstance("https://gobiz.co.id/pusat-pengetahuan/wp-content/uploads/2019/12/perilaku-konsumen-era-digital.jpg"));

        SliderPagerAdapter mAdapter = new SliderPagerAdapter(getParentFragmentManager(), fragments);
        bannerSlider.setAdapter(mAdapter);
        SliderIndicator mIndicator = new SliderIndicator(getActivity(), mLinearLayout, bannerSlider, R.drawable.indicator_slider);
        mIndicator.setPageCount(fragments.size());
        mIndicator.show();
    } */
}