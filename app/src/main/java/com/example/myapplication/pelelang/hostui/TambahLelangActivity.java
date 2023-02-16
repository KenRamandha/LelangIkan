package com.example.myapplication.pelelang.hostui;

import static com.example.myapplication.util.FilePath.getPath;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityTambahPenawaranPelelangBinding;
import com.example.myapplication.dialog.ChooseStorageListener;
import com.example.myapplication.dialog.ChooseStorgeDialogFragment;
import com.example.myapplication.model.ResponseData;
import com.example.myapplication.model.pelelang.KatalogPelelangModel;
import com.example.myapplication.util.ImageUtil;
import com.example.myapplication.util.RetrofitAPI;
import com.example.myapplication.util.RetrofitClient;
import com.example.myapplication.util.ServerAPI;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TambahLelangActivity extends AppCompatActivity {

    private ActivityTambahPenawaranPelelangBinding binding;

    Uri Image_Uri;
    String image1, image2, image3, image4,produk,deskripsiProduk,tglMulai,tglSelesai,keterangan;
    String jsonString;
    Double hargaAwal,hargaMinimalDiterima,incrementalValue,hargaBeliSekarang;
    Integer jumlah;
    private static final Gson gson = new Gson();
    private String pelelangId;
    KatalogPelelangModel model;
    private Boolean isEdit;

    SharedPreferences sharedPreferences;
    public static final String myLelang = "myLelang";
    public final static String TAG_KODE = "kode";

    ActivityResultLauncher<Intent> launchImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent intent = result.getData();
                    if (intent != null){
                        Uri uri = intent.getData();
                        image1 = getPath(getApplicationContext(), uri);;
                        Glide.with(getApplicationContext())
                                .load(uri)
                                .placeholder(R.drawable.imgup)
                                .into(binding.ivFotoLelang);;
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
                        image2 = getPath(getApplicationContext(), uri);;
                        Glide.with(getApplicationContext())
                                .load(uri)
                                .placeholder(R.drawable.imgup)
                                .into(binding.ivFotoLelang2);
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
                        image3 = getPath(getApplicationContext(), uri);;
                        Glide.with(getApplicationContext())
                                .load(uri)
                                .placeholder(R.drawable.imgup)
                                .into(binding.ivFotoLelang3);
                    }
                }
            }
    );

    ActivityResultLauncher<Intent> launchImage4 = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent intent = result.getData();
                    if (intent != null){
                        Uri uri = intent.getData();
                        image4 = getPath(getApplicationContext(), uri);;
                        Glide.with(getApplicationContext())
                                .load(uri)
                                .placeholder(R.drawable.imgup)
                                .into(binding.ivFotoLelang4);
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
                    Glide.with(TambahLelangActivity.this)
                            .load(file)
                            .placeholder(R.drawable.imgup)
                            .into(binding.ivFotoLelang);
                }
            }
    );

    ActivityResultLauncher<Intent> launchCamera2 = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    File file = new File(image2);
                    Glide.with(TambahLelangActivity.this)
                            .load(file)
                            .placeholder(R.drawable.imgup)
                            .into(binding.ivFotoLelang2);
                }
            }
    );


    ActivityResultLauncher<Intent> launchCamera3 = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    File file = new File(image3);
                    Glide.with(TambahLelangActivity.this)
                            .load(file)
                            .placeholder(R.drawable.imgup)
                            .into(binding.ivFotoLelang3);
                }
            }
    );


    ActivityResultLauncher<Intent> launchCamera4 = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    File file = new File(image4);
                    Glide.with(TambahLelangActivity.this)
                            .load(file)
                            .placeholder(R.drawable.imgup)
                            .into(binding.ivFotoLelang4);
                }
            }
    );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTambahPenawaranPelelangBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        jsonString = getIntent().getStringExtra("data");
        model = gson.fromJson(jsonString, KatalogPelelangModel.class);

        if (model != null){
            isEdit = true;
            setUpData();
        }else {
            isEdit = false;
        }

        binding.TglMulaiLelang.setInputType(InputType.TYPE_NULL);
        binding.TglEndLelang.setInputType(InputType.TYPE_NULL);

        sharedPreferences = getSharedPreferences(myLelang, MODE_PRIVATE);
        pelelangId = (sharedPreferences.getString(TAG_KODE, ""));
        Image_Uri = createImageUri();

        binding.TglMulaiLelang.setOnClickListener(v -> Show_TglMulaiLelang(binding.TglMulaiLelang));

        binding.TglEndLelang.setOnClickListener(v -> Show_TglEndLelang(binding.TglEndLelang));

        binding.AddSvLelang.setOnClickListener(v ->{
            postRegAdd();
        });

        binding.btnBackBuatLelang.setOnClickListener(view -> onBackPressed());

        binding.btnGaleriLelang.setOnClickListener(view -> {
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

        binding.btnGaleriLelang2.setOnClickListener(view -> {
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

        binding.btnGaleriLelang3.setOnClickListener(view -> {
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

        binding.btnGaleriLelang4.setOnClickListener(view -> {
            final Intent[] intent = new Intent[1];
            ChooseStorgeDialogFragment dialogFragment = ChooseStorgeDialogFragment.newInstance();
            dialogFragment.show(getSupportFragmentManager(), ChooseStorgeDialogFragment.class.getSimpleName());
            dialogFragment.setListener(new ChooseStorageListener(){

                @Override
                public void onSelectGallery() {
                    intent[0] = new Intent(Intent.ACTION_PICK);
                    intent[0].setType("image/*");
                    launchImage4.launch(intent[0]);
                }

//                @Override
//                public void onSelectStorage() {
//                    intent[0] = new Intent(Intent.ACTION_GET_CONTENT);
//                    intent[0].setType("image/*");
//                    launchImage4.launch(intent[0]);
//                }
            });
        });

        binding.btnCameraLelang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });

        binding.btnCameraLelang2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent2();
            }
        });

        binding.btnCameraLelang3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent3();
            }
        });

        binding.btnCameraLelang4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent4();
            }
        });

    }

    private void setUpData() {
        binding.titleToolbar.setText("Edit Lelang");
        binding.EditNamaProdukPelelang.setText(model.getProduk());
        binding.DeskProduk.setText(model.getDeskripsi_produk());
        binding.KetLelang.setText(model.getKeterangan());
        binding.EdtBeliSkrng.setText(model.getHarga_beli_sekarang());
        binding.hrgaIncremental.setText(String.valueOf(model.getIncremental_value()));
        binding.MPenawaran.setText(String.valueOf(model.getHarga_awal()));
        binding.TglEndLelang.setText(model.getTgl_selesai());
        binding.TglMulaiLelang.setText(model.getTgl_mulai());
        binding.hrgaMinimAccept.setText(String.valueOf(model.getHarga_minimal_diterima()));
        binding.JumlahBeratIkan.setText(String.valueOf(model.getJumlah()));
        Glide.with(this)
                .load(ServerAPI.URL_GET_GAMBAR+"produk/"+model.getImage3())
                .into(binding.ivFotoLelang3);
        Glide.with(this)
                .load(ServerAPI.URL_GET_GAMBAR+"produk/"+model.getImage1())
                .into(binding.ivFotoLelang);
        Glide.with(this)
                .load(ServerAPI.URL_GET_GAMBAR+"produk/"+model.getImage2())
                .into(binding.ivFotoLelang2);
        Glide.with(this)
                .load(ServerAPI.URL_GET_GAMBAR+"produk/"+model.getImage4())
                .into(binding.ivFotoLelang4);
    }

    private Uri createImageUri(){
        File file = new File(getApplicationContext().getFilesDir(), "camera-photo.png");
        return FileProvider.getUriForFile(getApplicationContext()
                ,"com.example.myapplication.fileprovider",
                file);
    }

    private void Show_TglEndLelang(EditText edt_tglEndLelang) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            TimePickerDialog.OnTimeSetListener timeSetListener = (view1, hourOfDay, minute) -> {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);

                @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm");
                edt_tglEndLelang.setText(simpleDateFormat.format(calendar.getTime()));

            };
            new TimePickerDialog(TambahLelangActivity.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
        };
        new DatePickerDialog(TambahLelangActivity.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();

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
            new TimePickerDialog(TambahLelangActivity.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
        };
        new DatePickerDialog(TambahLelangActivity.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();

    }

    private void postRegAdd() {
        produk = binding.EditNamaProdukPelelang.getText().toString();
        deskripsiProduk = binding.DeskProduk.getText().toString();
        jumlah = Integer.parseInt(binding.JumlahBeratIkan.getText().toString());
        hargaAwal = Double.parseDouble(binding.MPenawaran.getText().toString());
        hargaMinimalDiterima = Double.parseDouble(binding.hrgaMinimAccept.getText().toString());
        incrementalValue = Double.parseDouble(binding.hrgaIncremental.getText().toString());
        hargaBeliSekarang = Double.parseDouble(binding.EdtBeliSkrng.getText().toString());
        keterangan = binding.KetLelang.getText().toString();
        tglMulai = binding.TglMulaiLelang.getText().toString();
        tglSelesai = binding.TglEndLelang.getText().toString();

        if (produk == null || produk.isEmpty()){
            binding.EditNamaProdukPelelang.setError(getResources().getString(R.string.must_be_filled));
            binding.EditNamaProdukPelelang.requestFocus();
            return;
        }
        if (deskripsiProduk == null || deskripsiProduk.isEmpty()){
            binding.DeskProduk.setError(getResources().getString(R.string.must_be_filled));
            binding.DeskProduk.requestFocus();
            return;
        }
        if (jumlah == null){
            binding.JumlahBeratIkan.setError(getResources().getString(R.string.must_be_filled));
            binding.JumlahBeratIkan.requestFocus();
            return;
        }
        if (hargaAwal == null){
            binding.MPenawaran.setError(getResources().getString(R.string.must_be_filled));
            binding.MPenawaran.requestFocus();
            return;
        }
        if (hargaMinimalDiterima == null){
            binding.hrgaMinimAccept.setError(getResources().getString(R.string.must_be_filled));
            binding.hrgaMinimAccept.requestFocus();
            return;
        }
        if (incrementalValue == null){
            binding.hrgaIncremental.setError(getResources().getString(R.string.must_be_filled));
            binding.hrgaIncremental.requestFocus();
            return;
        }
        if (hargaBeliSekarang == null){
            binding.EdtBeliSkrng.setError(getResources().getString(R.string.must_be_filled));
            binding.EdtBeliSkrng.requestFocus();
            return;
        }
        if (keterangan == null || keterangan.isEmpty()){
            binding.TglMulaiLelang.setError(getResources().getString(R.string.must_be_filled));
            binding.TglMulaiLelang.requestFocus();
            return;
        }
        if (tglSelesai == null || tglSelesai.isEmpty()){
            binding.TglEndLelang.setError(getResources().getString(R.string.must_be_filled));
            binding.TglEndLelang.requestFocus();
            return;
        }
        if (tglMulai == null || tglMulai.isEmpty()){
            binding.TglMulaiLelang.setError(getResources().getString(R.string.must_be_filled));
            binding.TglMulaiLelang.requestFocus();
            return;
        }

        enableSaveButton(false);

        File photoUnCompress = new File(image1);
        File photo = ImageUtil.reduceFileImage(photoUnCompress);
        RequestBody requestBody = RequestBody.create(photo, MediaType.parse("image/*"));
        MultipartBody.Part foto = MultipartBody.Part.createFormData("image1", photo.getName(),requestBody);

        File photo2UnCompress = new File(image2);
        File photo2 = ImageUtil.reduceFileImage(photo2UnCompress);
        RequestBody requestBody2 = RequestBody.create(photo2, MediaType.parse("image/*"));
        MultipartBody.Part foto2 = MultipartBody.Part.createFormData("image2", photo2.getName(),requestBody2);

        File photo3UnCompress = new File(image3);
        File photo3 = ImageUtil.reduceFileImage(photo3UnCompress);
        RequestBody requestBody3 = RequestBody.create(photo3, MediaType.parse("image/*"));
        MultipartBody.Part foto3 = MultipartBody.Part.createFormData("image3", photo3.getName(),requestBody3);

        File photo4UnCompress = new File(image4);
        File photo4 = ImageUtil.reduceFileImage(photo4UnCompress);
        RequestBody requestBody4 = RequestBody.create(photo4, MediaType.parse("image/*"));
        MultipartBody.Part foto4 = MultipartBody.Part.createFormData("image4", photo4.getName(),requestBody4);

        RequestBody produke = RequestBody.create(produk,MediaType.parse("text/plain"));
        RequestBody lelangId = RequestBody.create(pelelangId,MediaType.parse("text/plain"));
        RequestBody desc = RequestBody.create(deskripsiProduk,MediaType.parse("text/plain"));
        RequestBody tglStart = RequestBody.create(tglMulai,MediaType.parse("text/plain"));
        RequestBody tglEnd = RequestBody.create(tglSelesai,MediaType.parse("text/plain"));
        RequestBody ket = RequestBody.create(keterangan,MediaType.parse("text/plain"));

        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofit().create(RetrofitAPI.class);
        Call<ResponseData> tambahLelangModelCall = retrofitAPI.pelelangAddProduk(
                foto,
                foto2,
                foto3,
                foto4,
                produke,
                lelangId,
                desc,
                hargaAwal,
                hargaMinimalDiterima,
                incrementalValue,
                tglStart,
                tglEnd,
                hargaBeliSekarang,
                jumlah,
                ket
        );

//        Log.i("testing",MultipartBody.Part foto);
        tambahLelangModelCall.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(@NonNull Call<ResponseData> call, @NonNull Response<ResponseData> response) {
                enableSaveButton(true);
                if (response.body() != null) {
                    if (response.body().getStatus().equals("success")){
                        Toast.makeText(TambahLelangActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Log.i("testing",response.message());
                        Intent intent = new Intent(TambahLelangActivity.this, PelelangActivity.class);
                        startActivity(intent);
                    }
                    Log.i("testing","error");
                }
                Log.i("testing","null");
            }

            @Override
            public void onFailure(@NonNull Call<ResponseData> call, @NonNull Throwable t) {
                enableSaveButton(true);
                Toast.makeText(TambahLelangActivity.this,
                        t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("testing",t.getMessage());
            }
        });
    }

    private void enableSaveButton(boolean isEnable) {
        if (isEnable){
            binding.pbLoading.getRoot().setVisibility(View.GONE);
            binding.AddSvLelang.setEnabled(true);
        } else {
            binding.pbLoading.getRoot().setVisibility(View.VISIBLE);
            binding.AddSvLelang.setEnabled(false);
        }
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

    private void dispatchTakePictureIntent4() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

            File photoFile = null;
            try {
                photoFile = createImageFile4();
            } catch (IOException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("Response", "dispatchTakePictureIntent: "+ex.getMessage());
            }

            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getBaseContext(),
                        "com.example.myapplication.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                launchCamera4.launch(takePictureIntent);
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

    private File createImageFile4() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        image4 = image.getAbsolutePath();
        return image;
    }

}
