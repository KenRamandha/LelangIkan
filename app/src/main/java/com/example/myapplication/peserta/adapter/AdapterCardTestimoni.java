package com.example.myapplication.peserta.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.example.myapplication.model.peserta.KatalogModel;
import com.example.myapplication.model.peserta.TestimoniModel;
import com.example.myapplication.peserta.activity.KonfirmasiProdukActivity;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class AdapterCardTestimoni extends RecyclerView.Adapter <AdapterCardTestimoni.ViewHolderTestimoni> {

    private static ArrayList<TestimoniModel> itemList;
    private Context context;
    private ItemClickListener clickListener;

    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    public AdapterCardTestimoni(Context context, ArrayList<TestimoniModel> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolderTestimoni onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_testimoni,parent,false);
        return new ViewHolderTestimoni(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderTestimoni holder, int position) {
        TestimoniModel modal = itemList.get(position);
        holder.cart_produk.setText(modal.getProduk());
        holder.cart_pelelang.setText(modal.getNama());

        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "dd/MM/yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String result = null;

//        try {
//            date = inputFormat.parse(modal.getTgl_diumumkan());
//            result = outputFormat.format(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");

        if (modal.getTgl_diumumkan() != null) {
            try {
                date = inputFormat.parse(modal.getTgl_diumumkan());
                result = outputFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
//        holder.cart_tanggal.setText(result);
        holder.cart_tgl_diumumkan.setText(result);

        if (modal.getTotal_bayar() !=null){
            holder.cart_total_bayar.setText("Rp. " + decimalFormat.format(Integer.parseInt(modal.getTotal_bayar())));
        }

//        holder.cart_total_bayar.setText("Rp. " + decimalFormat.format(Integer.parseInt(modal.getTotal_bayar())));


        Glide.with(holder.itemView.getContext())
                .load(modal.getImage1())
                .into(holder.cart_image1);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class ViewHolderTestimoni extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView cart_produk, cart_pelelang, cart_tgl_diumumkan, cart_total_bayar;
        ImageView cart_image1;
        Button btnKonfirmasi;
        public ViewHolderTestimoni(@NonNull View itemView) {
            super(itemView);
            cart_produk= itemView.findViewById(R.id.tv_judul_testimoni);
            cart_pelelang = itemView.findViewById(R.id.tv_nelayan_testimoni);
            cart_tgl_diumumkan = itemView.findViewById(R.id.tv_tanggal_testimoni);
            cart_total_bayar= itemView.findViewById(R.id.tv_harga_testimoni);
            cart_image1 = itemView.findViewById(R.id.iv_gambar_testimoni);
            btnKonfirmasi = itemView.findViewById(R.id.btn_konfirmasi_produk);
//            itemView.setTag(itemView);
//            itemView.setOnClickListener(this);
            btnKonfirmasi.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view,
                    getAdapterPosition());
        }
    }
}
