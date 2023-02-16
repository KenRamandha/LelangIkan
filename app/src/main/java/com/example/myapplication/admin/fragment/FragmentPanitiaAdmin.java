package com.example.myapplication.admin.fragment;

import static com.example.myapplication.admin.adapter.AdapterCardPanitiaAdmin.listPanitia;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.admin.activity.DetailPanitiaActivity;
import com.example.myapplication.admin.adapter.AdapterCardPanitiaAdmin;
import com.example.myapplication.model.admin.AdminPanitiaModel;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentPanitiaAdmin extends Fragment implements AdapterCardPanitiaAdmin.ItemClickListener {


    private RecyclerView recyclerView;
    private AdapterCardPanitiaAdmin adapter;
    private ArrayList<AdminPanitiaModel> adminPanitiaModelArrayList;
    private TextInputEditText tisearch;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_panitia_admin, container, false);

        recyclerView = view.findViewById(R.id.rv_panitia_admin);
        tisearch = view.findViewById(R.id.et_query);

        tisearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                adapter.getFilter().filter(editable);
            }
        });

        generateItem();

        return view;
    }

    private void generateItem() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ArrayList<AdminPanitiaModel>> panitiaAdminListCall = retrofitAPI.getAllPanitiaAdmin();
        panitiaAdminListCall.enqueue(new Callback<ArrayList<AdminPanitiaModel>>() {
            @Override
            public void onResponse(Call<ArrayList<AdminPanitiaModel>> call, Response<ArrayList<AdminPanitiaModel>> response) {
                if (response.isSuccessful()) {
                    adminPanitiaModelArrayList = new ArrayList<>(response.body());
                    adapter = new AdapterCardPanitiaAdmin(getActivity(), adminPanitiaModelArrayList);
                    //
                    String tes = tisearch.getText().toString();
                    if (!tes.equals(""))  adapter.getFilter().filter(tisearch.getText().toString());
                    //
                    recyclerView.setAdapter(adapter);
                    adapter.setClickListener(FragmentPanitiaAdmin.this);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<AdminPanitiaModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onClick(View view, int position) {
        final AdminPanitiaModel panitiaModel = listPanitia.get(position);
        switch (view.getId()){
            default:
                Intent intent = new Intent(getActivity(), DetailPanitiaActivity.class);
                intent.putExtra("nama",panitiaModel.getNama());
                intent.putExtra("jeniskel",panitiaModel.getJeniskel());
                intent.putExtra("instansi",panitiaModel.getInstansi());
                intent.putExtra("jabatan",panitiaModel.getJabatan());
                intent.putExtra("nik",panitiaModel.getNik());
                intent.putExtra("foto",panitiaModel.getFoto());
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        generateItem();
    }
}
