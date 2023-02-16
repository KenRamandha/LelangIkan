package com.example.myapplication.pelelang.hostui;

import static com.example.myapplication.util.FilePath.getPath;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.dialog.ChooseStorageListener;
import com.example.myapplication.dialog.ChooseStorgeDialogFragment;
import com.example.myapplication.intro.LoginActivity;
import com.example.myapplication.model.GeneralModel;
import com.example.myapplication.model.pelelang.ProfilePelelangModel;
import com.example.myapplication.util.ImageUtil;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;
import com.example.myapplication.util.ServerAPI;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditAkunPelelang extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button  btnSimpan,btnKTPGALERY,btnSIUPGALERY,btnNPWPGALERY,btnKTPCAMERA,btnSIUPCAMERA,btnNPWPCAMERA;
    private EditText edProvinsi,edKota,edKecamatan,edKelurahan,edNorek,edNamaBank,edDeskripsi,edAlamat,edTelepon,edEmail,ed_atasNamaPelelang;
    private TextView edUsername,edNik,edNpwp;
    private ImageView imgKtp,imgNPWP,imgSIUP;
    String kode_pelelang, image1, image2, image3;
    private Spinner spinner;
    private LinearLayout containerSIUP, containerNPWP;
    private ConstraintLayout pbLoading;
    int jenisUsaha;
    File photo;
    File photo2;
    File photo3;
    MultipartBody.Part foto,foto2,foto3;
    SharedPreferences sharedPreferences;

    ActivityResultLauncher<Intent> launchImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        if (intent != null){
                            Uri uri = intent.getData();
                            image1 = getPath(getApplicationContext(), uri);
                            Glide.with(getApplicationContext())
                                    .load(uri)
                                    .placeholder(R.drawable.imgup)
                                    .into(imgKtp);
                        }
                    }
                }
            }
    );

    ActivityResultLauncher<Intent> launchImage2 = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent intent = result.getData();
                    if (intent != null){
                        Uri uri = intent.getData();
                        image2 = getPath(getApplicationContext(), uri);
                        Glide.with(getApplicationContext())
                                .load(uri)
                                .placeholder(R.drawable.imgup)
                                .into(imgNPWP);;
                    }
                }
            }
    );

    ActivityResultLauncher<Intent> launchImage3 = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent intent = result.getData();
                    if (intent != null){
                        Uri uri = intent.getData();
                        image3 = getPath(getApplicationContext(), uri);
                        Glide.with(getApplicationContext())
                                .load(uri)
                                .placeholder(R.drawable.imgup)
                                .into(imgSIUP);;
                    }
                }
            }
    );

    ActivityResultLauncher<Intent> launchCamera = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    File file = new File(image1);
                    Glide.with(EditAkunPelelang.this)
                            .load(file)
                            .placeholder(R.drawable.imgup)
                            .into(imgKtp);
                }
            }
    );

    ActivityResultLauncher<Intent> launchCamera2 = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    File file = new File(image2);
                    Glide.with(EditAkunPelelang.this)
                            .load(file)
                            .placeholder(R.drawable.imgup)
                            .into(imgSIUP);
                }
            }
    );

    ActivityResultLauncher<Intent> launchCamera3 = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    File file = new File(image3);
                    Glide.with(EditAkunPelelang.this)
                            .load(file)
                            .placeholder(R.drawable.imgup)
                            .into(imgNPWP);
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_akun_pelelang);

        imgKtp = findViewById(R.id.imgKTP);
        imgNPWP = findViewById(R.id.imgNPWP);
        imgSIUP = findViewById(R.id.imgSIUP);
        edProvinsi=findViewById(R.id.ed_provinsiPelelang);
        edKota =findViewById(R.id.ed_kotaPelelang);
        edKecamatan=findViewById(R.id.ed_kecamatanPelelang);
        edKelurahan=findViewById(R.id.ed_kelurahanPelelang);
        edNorek=findViewById(R.id.ed_noRekPelelang);
        edNamaBank=findViewById(R.id.ed_namaBankPelelang);
        ed_atasNamaPelelang =findViewById(R.id.ed_atasNamaPelelang);
        edDeskripsi=findViewById(R.id.ed_deskripsiusaha);
        edAlamat = findViewById(R.id.edAlamat);
        edTelepon = findViewById(R.id.edTelepon);
        edEmail = findViewById(R.id.edEmail);
        edNpwp = findViewById(R.id.edNpwp);
        edUsername = findViewById(R.id.edUsername);
        edNik = findViewById(R.id.tvNik);
        pbLoading = findViewById(R.id.pb_loading);


        btnSimpan =findViewById(R.id.btn_simpanDataPelelang);
        btnKTPGALERY=findViewById(R.id.btn_galeri_fotoKTP);
        btnKTPCAMERA=findViewById(R.id.btn_camera_fotoKTP);
        btnSIUPGALERY=findViewById(R.id.btn_galeri_fotoSIUP);
        btnSIUPCAMERA=findViewById(R.id.btn_camera_fotoSIUP);
        btnNPWPGALERY=findViewById(R.id.btn_galeri_fotoNPWP);
        btnNPWPCAMERA=findViewById(R.id.btn_camera_fotoNPWP);
        spinner = findViewById(R.id.spinnerStatusUsaha);

        containerNPWP = findViewById(R.id.containerNPWP);
        containerSIUP = findViewById(R.id.containerSIUP);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditAkunPelelang.this,
                R.array.stst_Usaha, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(EditAkunPelelang.this);

        sharedPreferences = getSharedPreferences(LoginActivity.myLelang, Context.MODE_PRIVATE);
        kode_pelelang = sharedPreferences.getString(LoginActivity.TAG_KODE,"");

        getDataProfile();

        btnKTPCAMERA.setOnClickListener(view -> {
            dispatchTakePictureIntent();
        });
        btnSIUPCAMERA.setOnClickListener(view -> {
            dispatchTakePictureIntent2();
        });
        btnNPWPCAMERA.setOnClickListener(view -> {
            dispatchTakePictureIntent3();
        });

        btnKTPGALERY.setOnClickListener(view -> {
            final Intent[] intent = new Intent[1];
            ChooseStorgeDialogFragment dialogFragment = ChooseStorgeDialogFragment.newInstance();
            dialogFragment.show(getSupportFragmentManager(), ChooseStorgeDialogFragment.class.getSimpleName());
            dialogFragment.setListener(new ChooseStorageListener(){

                @Override
                public void onSelectGallery() {
                    intent[0] = new Intent(Intent.ACTION_PICK);
                    intent[0].setType("image/*");
                    launchImage.launch(intent[0]);
                }

//                @Override
//                public void onSelectStorage() {
//                    intent[0] = new Intent(Intent.ACTION_GET_CONTENT);
//                    intent[0].setType("image/*");
//                    launchImage.launch(intent[0]);
//                }
            });
        });
        btnNPWPGALERY.setOnClickListener(view -> {
            final Intent[] intent = new Intent[1];
            ChooseStorgeDialogFragment dialogFragment = ChooseStorgeDialogFragment.newInstance();
            dialogFragment.show(getSupportFragmentManager(), ChooseStorgeDialogFragment.class.getSimpleName());
            dialogFragment.setListener(new ChooseStorageListener(){

                @Override
                public void onSelectGallery() {
                    intent[0] = new Intent(Intent.ACTION_PICK);
                    intent[0].setType("image/*");
                    launchImage2.launch(intent[0]);
                }

//                @Override
//                public void onSelectStorage() {
//                    intent[0] = new Intent(Intent.ACTION_GET_CONTENT);
//                    intent[0].setType("image/*");
//                    launchImage2.launch(intent[0]);
//                }
            });
        });
        btnSIUPGALERY.setOnClickListener(view -> {
            final Intent[] intent = new Intent[1];
            ChooseStorgeDialogFragment dialogFragment = ChooseStorgeDialogFragment.newInstance();
            dialogFragment.show(getSupportFragmentManager(), ChooseStorgeDialogFragment.class.getSimpleName());
            dialogFragment.setListener(new ChooseStorageListener(){

                @Override
                public void onSelectGallery() {
                    intent[0] = new Intent(Intent.ACTION_PICK);
                    intent[0].setType("image/*");
                    launchImage3.launch(intent[0]);
                }

//                @Override
//                public void onSelectStorage() {
//                    intent[0] = new Intent(Intent.ACTION_GET_CONTENT);
//                    intent[0].setType("image/*");
//                    launchImage3.launch(intent[0]);
//                }
            });
        });
        btnSimpan.setOnClickListener(view -> {
            postProfile();
        });

    }

    private void getDataProfile() {
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ProfilePelelangModel> getProfilePelelangCall = retrofitAPI.getProfilePelelangId(kode_pelelang);
        getProfilePelelangCall.enqueue(new Callback<ProfilePelelangModel>() {
            @Override
            public void onResponse(Call<ProfilePelelangModel> call, Response<ProfilePelelangModel> response) {
                if (response.isSuccessful()){
                    if (response.body()!= null){
                        edProvinsi.setText(response.body().getProvinsi());
                        edKota.setText(response.body().getKota());
                        edKecamatan.setText(response.body().getKecamatan());
                        edKelurahan.setText(response.body().getKelurahan());
                        edNorek.setText(response.body().getNorekening());
                        edNamaBank.setText(response.body().getBank());
                        ed_atasNamaPelelang.setText(response.body().getAtasnama());
                        edAlamat.setText(response.body().getAlamat());
                        edTelepon.setText(response.body().getTelp());
                        edEmail.setText(response.body().getEmail());
                        edNpwp.setText(response.body().getNpwp());
                        edDeskripsi.setText(response.body().getDeskripsi());
                        edUsername.setText(response.body().getUsername());
                        edNik.setText(response.body().getNik());
                        if (response.body().getStatus().equals("0")){
                            spinner.setSelection(0);
                        }
                        else if (response.body().getStatus().equals("1")){
                            spinner.setSelection(1);
                        }
                        else {
                            spinner.setSelection(2);
                        }

                        Glide.with(EditAkunPelelang.this)
                                .load(ServerAPI.URL_GET_GAMBAR+"pelelang/"+response.body().getFilektp())
                                .into(imgKtp);
                        Glide.with(EditAkunPelelang.this)
                                .load(ServerAPI.URL_GET_GAMBAR+"pelelang/"+response.body().getFilenpwp())
                                .into(imgNPWP);
                        Glide.with(EditAkunPelelang.this)
                                .load(ServerAPI.URL_GET_GAMBAR+"pelelang/"+response.body().getFileSIUP())
                                .into(imgSIUP);
                    }
                }
            }

            @Override
            public void onFailure(Call<ProfilePelelangModel> call, Throwable t) {
                Toast.makeText(EditAkunPelelang.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void postProfile() {

//        File photo = new File(image1);
//        RequestBody requestBody = RequestBody.create(photo, MediaType.parse("image/*"));
//        MultipartBody.Part foto = MultipartBody.Part.createFormData("filektp", photo.getName(),requestBody);
//
//        RequestBody requestBody2,requestBody3;
//        MultipartBody.Part foto2, foto3;

////        if (jenisUsaha == 1){
////            foto2 = MultipartBody.Part.createFormData("fileSIUP", null,null);
////            foto3 = MultipartBody.Part.createFormData("filenpwp", null,null);
////        }
////        else {
//            File photo2 = new File(image2);
//            requestBody2 = RequestBody.create(photo2, MediaType.parse("image/*"));
//            foto2 = MultipartBody.Part.createFormData("fileSIUP", photo2.getName(),requestBody2);
//
//            File photo3 = new File(image3);
//            requestBody3  = RequestBody.create(photo3, MediaType.parse("image/*"));
//            foto3 = MultipartBody.Part.createFormData("filenpwp", photo3.getName(),requestBody3);
        //}

        enableSaveButton(false);

        File photoUnCompress = new File(image1);
        File photo = ImageUtil.reduceFileImage(photoUnCompress);
        RequestBody requestBody = RequestBody.create(photo, MediaType.parse("image/*"));
        MultipartBody.Part foto = MultipartBody.Part.createFormData("filektp", photo.getName(),requestBody);

        File photo2UnCompress = new File(image2);
        File photo2 = ImageUtil.reduceFileImage(photo2UnCompress);
        RequestBody requestBody2 = RequestBody.create(photo2, MediaType.parse("image/*"));
        MultipartBody.Part foto2 = MultipartBody.Part.createFormData("filenpwp", photo2.getName(),requestBody2);

        File photo3UnCompress = new File(image3);
        File photo3 = ImageUtil.reduceFileImage(photo3UnCompress);
        RequestBody requestBody3 = RequestBody.create(photo3, MediaType.parse("image/*"));
        MultipartBody.Part foto3 = MultipartBody.Part.createFormData("fileSIUP", photo3.getName(),requestBody3);

//        RequestBody requestBody = RequestBody.create(photo, MediaType.parse("image/*"));
//        foto = MultipartBody.Part.createFormData("filektp", photo.getName(),requestBody);
//
//        RequestBody requestBody2 = RequestBody.create(photo2, MediaType.parse("image/*"));
//        foto2 = MultipartBody.Part.createFormData("filenpwp", photo2.getName(),requestBody2);
//
//        RequestBody requestBody3 = RequestBody.create(photo3, MediaType.parse("image/*"));
//        foto3 = MultipartBody.Part.createFormData("fileSIUP", photo3.getName(),requestBody3);
        RequestBody provinsi = RequestBody.create(edProvinsi.getText().toString(),MediaType.parse("text/plain"));
        RequestBody kota = RequestBody.create(edKota.getText().toString(),MediaType.parse("text/plain"));
        RequestBody kecamatan = RequestBody.create(edKecamatan.getText().toString(),MediaType.parse("text/plain"));
        RequestBody kelurahan = RequestBody.create(edKelurahan.getText().toString(),MediaType.parse("text/plain"));
        RequestBody norek = RequestBody.create(edNorek.getText().toString(),MediaType.parse("text/plain"));
        RequestBody bank = RequestBody.create(edNamaBank.getText().toString(),MediaType.parse("text/plain"));
        RequestBody atasnama = RequestBody.create(ed_atasNamaPelelang.getText().toString(),MediaType.parse("text/plain"));
        RequestBody alamat = RequestBody.create(edAlamat.getText().toString(),MediaType.parse("text/plain"));
        RequestBody telepon = RequestBody.create(edTelepon.getText().toString(),MediaType.parse("text/plain"));
        RequestBody email = RequestBody.create(edEmail.getText().toString(),MediaType.parse("text/plain"));
        RequestBody description = RequestBody.create(edDeskripsi.getText().toString(),MediaType.parse("text/plain"));

        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<GeneralModel> updateProfile = retrofitAPI.editProfile(
                kode_pelelang,
                foto,
                foto2,
                foto3,
                telepon,
                provinsi,
                kota,
                jenisUsaha,
                norek,
                bank,
                atasnama,
                description,
                kecamatan,
                kelurahan,
                email,
                alamat
        );

        updateProfile.enqueue(new Callback<GeneralModel>() {
            @Override
            public void onResponse(Call<GeneralModel> call, Response<GeneralModel> response) {
                enableSaveButton(true);
                if (response.isSuccessful()){
                    if(response.body().getStatus().equals("success")){
                        Toast.makeText(EditAkunPelelang.this, "sukses", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<GeneralModel> call, Throwable t) {
                enableSaveButton(true);
                Log.i("cek", "onFailure: "+t.getMessage());
            }
        });

    }

    private void enableSaveButton(boolean isEnable) {
        if (isEnable){
            btnSimpan.setEnabled(true);
            pbLoading.setVisibility(View.GONE);
        }else {
            btnSimpan.setEnabled(false);
            pbLoading.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        jenisUsaha = i+1;
//        if (jenisUsaha == 1){
//            containerSIUP.setVisibility(View.GONE);
//            containerNPWP.setVisibility(View.GONE);
//        }else {
//            containerNPWP.setVisibility(View.VISIBLE);
//            containerSIUP.setVisibility(View.VISIBLE);
//        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("Response", "dispatchTakePictureIntent: "+ex.getMessage());
            }

            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getBaseContext(),
                        "com.example.myapplication.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                launchCamera.launch(takePictureIntent);
            }
        }
    }

    private void dispatchTakePictureIntent2() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

            File photoFile = null;
            try {
                photoFile = createImageFile2();
            } catch (IOException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("Response", "dispatchTakePictureIntent: "+ex.getMessage());
            }

            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getBaseContext(),
                        "com.example.myapplication.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                launchCamera2.launch(takePictureIntent);
            }
        }
    }

    private void dispatchTakePictureIntent3() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

            File photoFile = null;
            try {
                photoFile = createImageFile3();
            } catch (IOException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("Response", "dispatchTakePictureIntent: "+ex.getMessage());
            }

            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getBaseContext(),
                        "com.example.myapplication.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                launchCamera3.launch(takePictureIntent);
            }
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        image1 = image.getAbsolutePath();
        return image;
    }

    private File createImageFile2() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        image2 = image.getAbsolutePath();
        return image;
    }

    private File createImageFile3() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        image3 = image.getAbsolutePath();
        return image;
    }

}