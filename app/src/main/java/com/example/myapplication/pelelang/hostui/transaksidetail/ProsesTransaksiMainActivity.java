package com.example.myapplication.pelelang.hostui.transaksidetail;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityProsesTransaksiMainBinding;
import com.example.myapplication.model.pelelang.DetailTransaksiResponse;
import com.example.myapplication.model.pelelang.ProsesTransaksiRequest;
import com.example.myapplication.model.pelelang.ProsesTransaksiResponse;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProsesTransaksiMainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private ActivityProsesTransaksiMainBinding binding;
    DetailTransaksiResponse model;
    String noInvoice, namaPelelang, pelelangId;
    String stsTransaksi = "0";
    SharedPreferences sharedPreferences;
    public static final String myLelang = "myLelang";
    public final static String TAG_KODE = "kode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProsesTransaksiMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        noInvoice = getIntent().getStringExtra("id");
        Log.d("cek ", "onCreate: "+noInvoice);
        sharedPreferences = getSharedPreferences(myLelang, MODE_PRIVATE);
        pelelangId = (sharedPreferences.getString(TAG_KODE, ""));
//        namaPelelang = getIntent().getStringExtra("nama_pelelang");
//        Log.d("cek ", "onCreate: "+namaPelelang);
        setUpData();
    }

    private void setUpData() {

        binding.noInvoice.setText(noInvoice);
//        binding.namaPelelang.setText(namaPelelang);
        binding.etTglPengiriman.setInputType(InputType.TYPE_NULL);
        binding.etTglPengiriman.setOnClickListener(v -> Show_TglMulaiLelang(binding.etTglPengiriman));
        Spinner spinner = binding.spinnerStsTransaksi;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(ProsesTransaksiMainActivity.this,
                R.array.sts_transaksi, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(ProsesTransaksiMainActivity.this);

        binding.btnSimpanProsesTransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.etNamaPengirim.getText().toString().isEmpty()) {
                    Toast.makeText(ProsesTransaksiMainActivity.this, "Tolong Isi Nama", Toast.LENGTH_SHORT).show();
                    return;
                }if (binding.etNoPolisi.getText().toString().isEmpty()) {
                    Toast.makeText(ProsesTransaksiMainActivity.this, "Tolong Isi No Polisi", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (binding.etMerkKendaraan.getText().toString().isEmpty()) {
                    Toast.makeText(ProsesTransaksiMainActivity.this, "Tolong Isi Merk Kendaraan", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (binding.etNoHpPengirim.getText().toString().isEmpty()) {
                    Toast.makeText(ProsesTransaksiMainActivity.this, "Tolong Isi No HP Pengirim", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (binding.etTglPengiriman.getText().toString().isEmpty()) {
                    Toast.makeText(ProsesTransaksiMainActivity.this, "Tolong Isi Tanggal", Toast.LENGTH_SHORT).show();
                    return;
                }

                ProsesTransaksiRequest model = new ProsesTransaksiRequest(
                        binding.etMerkKendaraan.getText().toString(),
                        binding.etNoPolisi.getText().toString(),
                        pelelangId,
                        binding.etNamaPengirim.getText().toString(),
                        stsTransaksi,
                        binding.etNoHpPengirim.getText().toString(),
                        binding.etTglPengiriman.getText().toString()
                );
                RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
                Call<ProsesTransaksiResponse> prosesTransaksiResponseCall = retrofitAPI.prosesTransaksiResponse(noInvoice, model);
                prosesTransaksiResponseCall.enqueue(new Callback<ProsesTransaksiResponse>() {
                    @Override
                    public void onResponse(Call<ProsesTransaksiResponse> call, Response<ProsesTransaksiResponse> response) {
                        if (response.isSuccessful()) {
                            if (response.body()!=null){
                                if (response.body().getData()!=null || response.body().getCode() == 201){
                                    Toast.makeText(ProsesTransaksiMainActivity.this, "Berhasil Ubah Status Transaksi", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<ProsesTransaksiResponse> call, Throwable t) {
                        Log.d("asd", "onFailure: "+t);
                    }
                });
            }
        });
    }

    private void Show_TglMulaiLelang(EditText edt_tglMulaiLelang) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            TimePickerDialog.OnTimeSetListener timeSetListener = (view1, hourOfDay, minute) -> {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);

                @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm");
                edt_tglMulaiLelang.setText(simpleDateFormat.format(calendar.getTime()));
            };
            new TimePickerDialog(ProsesTransaksiMainActivity.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
        };
        new DatePickerDialog(ProsesTransaksiMainActivity.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        stsTransaksi = String.valueOf(pos);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
}