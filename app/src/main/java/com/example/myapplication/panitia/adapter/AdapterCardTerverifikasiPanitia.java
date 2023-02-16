package com.example.myapplication.panitia.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.model.panitia.ProdukPanitiaModel;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdapterCardTerverifikasiPanitia extends RecyclerView.Adapter<AdapterCardTerverifikasiPanitia.ViewHolderTerverifPanitia> {

    private static ArrayList<ProdukPanitiaModel> itemList;
    private Context context;

    public AdapterCardTerverifikasiPanitia(Context context, ArrayList<ProdukPanitiaModel> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolderTerverifPanitia onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_terverifikasi_panitia, parent, false);
        return new ViewHolderTerverifPanitia(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderTerverifPanitia holder, int position) {
        ProdukPanitiaModel modal = itemList.get(position);
        holder.cart_produk.setText(modal.getProduk());
        holder.cart_pelelang.setText(modal.getNama());

        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "dd/MM/yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String result = null;

        Date date1 = null;
        String result1 = null;

        try {
            date = inputFormat.parse(modal.getTgl_mulai());
            result = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            date1 = inputFormat.parse(modal.getTgl_selesai());
            result1 = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DecimalFormat decimalFormat = new DecimalFormat("#,##0");

        holder.cart_tgl_mulai.setText(result);

        holder.cart_harga_awal.setText("Rp. " + decimalFormat.format(Integer.parseInt(modal.getHarga_awal())));
        Glide.with(holder.itemView.getContext())
                .load(modal.getImage1())
                .into(holder.cart_image1);

        Handler handler;
        Runnable runnable;

//        holder.cart_tgl_selesai.setText(result1);

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1);
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    Date futureDate = dateFormat.parse(String.valueOf(modal.getTgl_selesai()));

                    Date currentDate = new Date();
                    if (!currentDate.after(futureDate)) {
                        long diff = futureDate.getTime() - currentDate.getTime();
                        long days = diff / (24 * 60 * 60 * 1000);
                        diff -= days * (24 * 60 * 60 * 1000);
                        long hours = diff / (60 * 60 * 1000);
                        diff -= hours * (60 * 60 * 1000);
                        long minutes = diff / (60 * 1000);
                        diff -= minutes * (60 * 1000);
                        long seconds = diff / 1000;
                        holder.tvHari.setText("" + String.format("%02d", days));
                        holder.tvJam.setText("" + String.format("%02d", hours));
                        holder.tvMenit.setText("" + String.format("%02d", minutes));
                        holder.tvDetik.setText("" + String.format("%02d", seconds));
                    } else {
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 1 * 1);

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    public class ViewHolderTerverifPanitia extends RecyclerView.ViewHolder {
        TextView cart_produk, cart_pelelang, cart_tgl_mulai, cart_harga_awal;
        TextView tvHari, tvJam, tvMenit, tvDetik;
        ImageView cart_image1;

        public ViewHolderTerverifPanitia(@NonNull View itemView) {
            super(itemView);
            cart_produk = itemView.findViewById(R.id.tv_ikan_terverif_panitia);
            cart_pelelang = itemView.findViewById(R.id.tv_nelayan_terverif_panitia);
            cart_tgl_mulai = itemView.findViewById(R.id.tv_tanggal_terverif_panitia);
            cart_harga_awal = itemView.findViewById(R.id.tv_harga_terverif_panitia);
            cart_image1 = itemView.findViewById(R.id.iv_terverif_panitia);

            tvHari = itemView.findViewById(R.id.tv_hari_terverif_panitia);
            tvJam = itemView.findViewById(R.id.tv_jam_terverif_panitia);
            tvMenit = itemView.findViewById(R.id.tv_menit_terverif_panitia);
            tvDetik = itemView.findViewById(R.id.tv_detik_terverif_panitia);

        }
    }
}
