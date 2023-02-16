package com.example.myapplication.peserta.activity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.myapplication.intro.LoginActivity;
import com.example.myapplication.model.peserta.ProfilePesertaModel;
import com.example.myapplication.R;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditAkunActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener {

    private ImageButton btnKembali;
    private Button btnFileNPWP,btnFileKTP,btnSimpan;
    private EditText etNama,etProvinsi,etKota,etKecamatan,etAlamat,etTelp,etEmail,etNpwp,etNik,etKelurahan;
    private Spinner spJeniskel;
    private TextView tvFileNPWP,tvFileKTP;

    private ProgressDialog pdLoading;

    private ProgressBar progressBar;

    private Boolean fieldsOK;

    String path,status, nama, jenis, provinsi, kota, kecamatan, alamat, telp, email, npwp, nik, kelurahan;
    String jeniskel, filenpwp, filektp, ktp_encode, npwp_encode;

    int  method = 0;

    List<ProfilePesertaModel> profileDataList;

    SharedPreferences sharedPreferences;

    //private static final int ALL_FILE_REQUEST = 102;
    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final int GALLERY_REQUEST_NPWP = 104;
    private static final int GALLERY_REQUEST_KTP = 105;

    //private static int IMAGE_PICK_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_akun);

        pdLoading = new ProgressDialog(this);

        btnKembali = findViewById(R.id.btn_back_editrpofil);
        btnFileNPWP = findViewById(R.id.btn_file_npwp);
        btnFileKTP = findViewById(R.id.btn_file_ktp);
        btnSimpan = findViewById(R.id.btn_simpan_edit);

        tvFileNPWP = findViewById(R.id.tv_file_npwp);
        tvFileKTP = findViewById(R.id.tv_file_ktp);
        progressBar = findViewById(R.id.progressbar);

        etNama = findViewById(R.id.et_nama_profile_peserta);
        etProvinsi = findViewById(R.id.et_provinsi_profile_peserta);
        etKota = findViewById(R.id.et_kota_profile_peserta);
        etKecamatan = findViewById(R.id.et_kecamatan_profile_peserta);
        etAlamat = findViewById(R.id.et_alamat_profile_peserta);
        etTelp = findViewById(R.id.et_telp_profile_peserta);
        etEmail = findViewById(R.id.et_email_profile_peserta);
        etNpwp = findViewById(R.id.et_npwp_profile_peserta);
        etNik = findViewById(R.id.et_nik_profile_peserta);
        etKelurahan = findViewById(R.id.et_kelurahan_profile_peserta);

        spJeniskel = findViewById(R.id.spinnergender_profile_peserta);

        btnFileNPWP.setOnClickListener(EditAkunActivity.this);
        btnFileKTP.setOnClickListener(EditAkunActivity.this);
        btnSimpan.setOnClickListener(EditAkunActivity.this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Jkel, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spJeniskel.setAdapter(adapter);
        spJeniskel.setOnItemSelectedListener(this);

        sharedPreferences = getSharedPreferences(LoginActivity.myLelang, Context.MODE_PRIVATE);
        path = sharedPreferences.getString(LoginActivity.TAG_KODE, "");
        status = sharedPreferences.getString(LoginActivity.TAG_STATS, "0");
        Log.d("kode", path);

//        profileDataList = new ArrayList<>();

        loadProf();

        btnKembali.setOnClickListener(view -> {
            onBackPressed();
        });

        btnSimpan.setOnClickListener(view -> {
            fieldsOK = validate(new EditText[]{etNama,etProvinsi,etKota,etKecamatan,etAlamat,etTelp,etEmail,etNpwp,etNik,etKelurahan});
            if (fieldsOK){
               new AsyncCaller().execute();
            } else {
                putProf();
            }
        });

    }

    private class AsyncCaller extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //this method will be running on UI thread
            pdLoading.setMessage("Tunggu sebentar..");
            pdLoading.setCancelable(false);
            pdLoading.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            //this method will be running on background thread so don't update UI frome here
            //do your long running http tasks here,you dont want to pass argument and u can access the parent class' variable url over here
            putProf();
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            //this method will be running on UI thread
        }
    }

    private boolean validate(EditText[] fields){
        for(int i = 0; i < fields.length; i++){
            EditText currentField = fields[i];
            if(currentField.getText().toString().length() <= 0){
                currentField.setError("Tidak boleh kosong!");
                return false;
            }
        }
        return true;
    }

    private void putProf() {
        nama = etNama.getText().toString();
        email = etEmail.getText().toString();
        telp = etTelp.getText().toString();
        jenis = spJeniskel.getSelectedItem().toString();
        if (jenis.equals("Laki-laki")) {
            jeniskel = "L";
        } else if (jenis.equals("Perempuan")) {
            jeniskel = "P";
        } else {
            jeniskel = "";
        }
        provinsi = etProvinsi.getText().toString();
        kota = etKota.getText().toString();
        kecamatan = etKecamatan.getText().toString();
        kelurahan = etKelurahan.getText().toString();
        alamat = etAlamat.getText().toString();
        npwp = etNpwp.getText().toString();
        nik = etNik.getText().toString();
        filektp = ktp_encode;
        filenpwp = npwp_encode;

        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        final ProfilePesertaModel profilePesertaModel = new ProfilePesertaModel(path,nama,jeniskel,provinsi,kota,kecamatan,kelurahan,alamat,telp,email,npwp,nik,filektp,filenpwp);
        Call<ProfilePesertaModel> profileModelCall = retrofitAPI.postProfile(profilePesertaModel);

        profileModelCall.enqueue(new Callback<ProfilePesertaModel>() {
            @Override
            public void onResponse(Call<ProfilePesertaModel> call, Response<ProfilePesertaModel> response) {
                if (response.body().getStats()) {
                    sharedPreferences.edit()
                    .putString(LoginActivity.TAG_USERNAME, nama)
                    .putString(LoginActivity.TAG_KOTA, kota)
                    .putString(LoginActivity.TAG_PROV, provinsi)
                    .commit();
                    pdLoading.dismiss();
                    onBackPressed();
                    EditAkunActivity.super.onRestart();
                } else {
                    Toast.makeText(EditAkunActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProfilePesertaModel> call, Throwable t) {
                Toast.makeText(EditAkunActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadProf() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ProfilePesertaModel> profileModelCall = retrofitAPI.getProfile(path);
        profileModelCall.enqueue(new Callback<ProfilePesertaModel>() {
            @Override
            public void onResponse(Call<ProfilePesertaModel> call, Response<ProfilePesertaModel> response) {
                if (response.isSuccessful()) {
                    nama = response.body().getNama();
                    etNama.setText(nama);
                    jeniskel = response.body().getJeniskel();
                    if (jeniskel.equals("L")) {
                        jenis = "Laki - Laki";
                    } else if (jeniskel.equals("P")){
                        jenis = "Perempuan";
                    } else {
                        jenis = "Lainnya";
                    }
                    spJeniskel.setSelection(getPosisi(spJeniskel, jenis));

                    provinsi = response.body().getProvinsi();
                    etProvinsi.setText(provinsi);

                    kota = response.body().getKota();
                    etKota.setText(kota);

                    kecamatan = response.body().getKecamatan();
                    etKecamatan.setText(kecamatan);

                    kelurahan = response.body().getKelurahan();
                    etKelurahan.setText(kelurahan);

                    alamat = response.body().getAlamat();
                    etAlamat.setText(alamat);

                    telp = response.body().getTelp();
                    etTelp.setText(telp);

                    email = response.body().getEmail();
                    etEmail.setText(email);

                    npwp = response.body().getNpwp();
                    etNpwp.setText(npwp);

                    nik = response.body().getNik();
                    etNik.setText(nik);

                    filenpwp = response.body().getFilenpwp();
                    tvFileNPWP.setText(filenpwp);

                    filektp = response.body().getFilektp();
                    tvFileKTP.setText(filektp);
                }
            }

            @Override
            public void onFailure(Call<ProfilePesertaModel> call, Throwable t) {
                Toast.makeText(EditAkunActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("ERRORNYA APA YA", t.getMessage());
            }
        });
    }

    private int getPosisi(Spinner spJeniskel, String jenis) {
        for (int i = 0; i < spJeniskel.getCount(); i++){
            if (jenis.equals(spJeniskel.getItemAtPosition(i))){
                return i;
            }
        }
        return 0;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_file_npwp) {
            method = 0;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    filePicker(0);
                } else {
                    requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                }
            } else {
                filePicker(0);
            }
        } else if (v.getId() == R.id.btn_file_ktp) {
            method = 1;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    filePicker(1);
                } else {
                    requestPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                }
            } else {
                filePicker(1);
            }
        }
    }

    private void filePicker(int i) {
        if (i == 0) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_PICK);
            startActivityForResult(intent, GALLERY_REQUEST_NPWP);
            //startActivityForResult(Intent.createChooser(intent, "Choose File to Upload"), ALL_FILE_REQUEST);
        }
        if (i == 1) {
            Intent ktp = new Intent();
            ktp.setType("image/*");
            ktp.setAction(Intent.ACTION_PICK);
            startActivityForResult(ktp, GALLERY_REQUEST_KTP);
        }
    }

    private boolean checkPermission(String permission) {
        int result = ContextCompat.checkSelfPermission(EditAkunActivity.this, permission);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }
    private void requestPermission(String permission) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(EditAkunActivity.this, permission)) {
            Toast.makeText(EditAkunActivity.this, "Please Allow Permission", Toast.LENGTH_SHORT).show();
        } else {
            ActivityCompat.requestPermissions(EditAkunActivity.this, new String[]{permission}, PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(EditAkunActivity.this, "Permission Successfull", Toast.LENGTH_SHORT).show();
                    filePicker(method);
                } else {
                    Toast.makeText(EditAkunActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == GALLERY_REQUEST_NPWP) {
                if (data == null) {
                    return;
                }

                Uri uri = data.getData();
                String paths = FilePathEditAkun.getFilePath(EditAkunActivity.this, uri);
                Bitmap bitmap = BitmapFactory.decodeFile(paths);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 30, stream);
                byte[] bytes = stream.toByteArray();
                npwp_encode = Base64.encodeToString(bytes, Base64.DEFAULT);
                if (paths != null) {
                    tvFileNPWP.setText("NPWP");//new File(paths).getName());
                }
                //npwp_file_path = paths;
            } else if (requestCode == GALLERY_REQUEST_KTP) {
                if (data == null) {
                    return;
                }

                Uri uri = data.getData();
                String selectedPath = FilePathEditAkun.getFilePath(EditAkunActivity.this, uri);
                Bitmap bitmap = BitmapFactory.decodeFile(selectedPath);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 30, stream);
                byte[] bytes = stream.toByteArray();
                ktp_encode = Base64.encodeToString(bytes, Base64.DEFAULT);
                if (selectedPath != null) {
                    tvFileKTP.setText("KTP");
                }
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        jenis = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}
