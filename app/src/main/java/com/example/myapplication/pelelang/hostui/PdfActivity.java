package com.example.myapplication.pelelang.hostui;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.databinding.ActivityPdfBinding;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PdfActivity extends AppCompatActivity {

    private ActivityPdfBinding binding;
    String pdfUrl = "http://pasariwak.com/lelang/Panitia/Pengiriman/suratPerintah/";
    private WebView webViewClient;
    private String pelelangId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPdfBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        pelelangId = getIntent().getStringExtra("id");
//        binding.pdfView.fromUri(Uri.parse(pdfUrl+pelelangId));
        new RetrivePDFfromUrl().execute(pdfUrl+pelelangId);

        binding.btnUnduh.setOnClickListener(view -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(pdfUrl+pelelangId));
            view.getContext().startActivity(browserIntent);
        });
    }

    private class RetrivePDFfromUrl extends AsyncTask<String, Void, InputStream> {
        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            binding.loading.setVisibility(View.VISIBLE);
            try {
                URL url = new URL(strings[0]);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                if (urlConnection.getResponseCode() == 200) {
                    inputStream = new BufferedInputStream(urlConnection.getInputStream());
                }

            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            binding.pdfView.setVisibility(View.VISIBLE);
            binding.loading.setVisibility(View.GONE);
            binding.pdfView.fromStream(inputStream).load();
        }
    }
}