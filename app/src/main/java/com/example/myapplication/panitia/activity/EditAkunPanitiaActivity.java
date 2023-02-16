package com.example.myapplication.panitia.activity;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.intro.LoginActivity;
import com.example.myapplication.model.panitia.PanitiaprofileModel;
import com.example.myapplication.model.peserta.ProfilePesertaModel;
import com.example.myapplication.peserta.activity.EditAkunActivity;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditAkunPanitiaActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ImageButton btnKembali;
    private Button btnFoto,btnSimpan;
    private EditText etNamaPanitia,etJeniskelpanitia,etNikPanitia,etJabatanPanitia,etInstansiPanitia;
    private Spinner spJeniskel;
    private CircleImageView imageView;

    private String kode, nama, nik, jabatan, instansi,jenis,imgencoded,jeniskel;
    private Integer GALLERY_REQ;
    private SharedPreferences  profile;

    private String[] arrayJenis = {"Laki - Laki", "Perempuan"};
    private ArrayAdapter<String> adapterJenis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_akun_panitia);

        btnKembali = findViewById(R.id.btn_back_editrpofil_panitia);
        btnSimpan = findViewById(R.id.btn_simpan_edit_panitia);
        btnFoto = findViewById(R.id.btn_foto_profile);
        imageView = findViewById(R.id.iv_edit_foto);


        etNamaPanitia = findViewById(R.id.et_NamaPanitia);
        spJeniskel = findViewById(R.id.spJenisKelPanitia);
        etNikPanitia = findViewById(R.id.et_NikPanitia);
        etJabatanPanitia = findViewById(R.id.et_JabatanPanitia);
        etInstansiPanitia = findViewById(R.id.etInstansiPanitia1);

        profile=getSharedPreferences(LoginActivity.myLelang, Context.MODE_PRIVATE);
        kode=profile.getString(LoginActivity.TAG_KODE,"");

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Jkel, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spJeniskel.setAdapter(adapter);
        spJeniskel.setOnItemSelectedListener(this);

        loadprof();

        btnFoto.setOnClickListener(view ->{
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    GALLERY_REQ = 35
            );
        });

        btnKembali.setOnClickListener(view -> onBackPressed());

        btnSimpan.setOnClickListener(view -> saveprof());
    }



    private void loadprof() {
        RetrofitAPI retrofitAPI= RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<PanitiaprofileModel> panitiaprofileModels=retrofitAPI.getProfilePanitia(kode);

       panitiaprofileModels.enqueue(new Callback<PanitiaprofileModel>() {
           @Override
           public void onResponse(Call<PanitiaprofileModel> call, Response<PanitiaprofileModel> response) {
               if (response.isSuccessful()){
                   etNamaPanitia.setText(response.body().getNama());
                   etInstansiPanitia.setText(response.body().getInstansi());
                   etNikPanitia.setText(response.body().getNik());
                   etJabatanPanitia.setText(response.body().getJabatan());
                   if (response.body().getJeniskel().equals("L")) {
                       spJeniskel.setSelection(0);
                   } else {
                       spJeniskel.setSelection(1);
                   }
                   Glide.with(EditAkunPanitiaActivity.this).load(response.body().getFoto()).into(imageView);
               }
           }

           @Override
           public void onFailure(Call<PanitiaprofileModel> call, Throwable t) {
               t.printStackTrace();
           }
       });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (GALLERY_REQ != null) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Open Galley
                Intent gallery = new Intent(Intent.ACTION_PICK).setType("image/*");
                try {
                    startActivityForResult(gallery, GALLERY_REQ);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Tidak ada perizinan untuk mengakses gambar", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && GALLERY_REQ != null) {
            Uri uri = data.getData();

            try {
                Bitmap bGallery = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bGallery.compress(Bitmap.CompressFormat.JPEG, 30, stream);
                byte[] bytes = stream.toByteArray();
                imgencoded = Base64.encodeToString(bytes, Base64.DEFAULT);
                if (requestCode == 35) {
                    imageView.setImageBitmap(bGallery);
                    GALLERY_REQ = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveprof() {
        nik = etNikPanitia.getText().toString();
        nama = etNamaPanitia.getText().toString();
        instansi = etInstansiPanitia.getText().toString();
        jabatan = etJabatanPanitia.getText().toString();
        jeniskel = spJeniskel.getSelectedItem().toString();
        if (jeniskel.equals("Laki-laki")) {
            jenis = "L";
        } else if (jeniskel.equals("Perempuan")) {
            jenis = "P";
        } else {
            jenis = "";
        }

        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        final PanitiaprofileModel profilePanitiaModel = new PanitiaprofileModel(kode,nik,nama,instansi,jabatan,jenis,imgencoded);
        Call<PanitiaprofileModel> profileModelCall = retrofitAPI.postProfilePanitia(profilePanitiaModel);

        profileModelCall.enqueue(new Callback<PanitiaprofileModel>() {
            @Override
            public void onResponse(Call<PanitiaprofileModel> call, Response<PanitiaprofileModel> response) {
                if (response.body().getStats()) {
                    SharedPreferences.Editor editor = profile.edit();
                    editor.putString(LoginActivity.TAG_USERNAME, nama);
                    editor.commit();
                    startActivity(new Intent(EditAkunPanitiaActivity.this, PanitiaActivity.class));
                    EditAkunPanitiaActivity.super.onRestart();
                } else {
                    Toast.makeText(EditAkunPanitiaActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PanitiaprofileModel> call, Throwable t) {
                Toast.makeText(EditAkunPanitiaActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        jeniskel = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}