package com.example.myapplication.pelelang.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.model.pelelang.KatalogPelelangModel;
import com.example.myapplication.pelelang.InterfacePelelang;
import com.example.myapplication.util.ServerAPI;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class AdapterCardProdukPelelang extends RecyclerView.Adapter <AdapterCardProdukPelelang.ViewHolderProdukPelelang> {

    private static List<KatalogPelelangModel> itemList;
    private final Context context;
    InterfacePelelang interfacePelelang;


    public AdapterCardProdukPelelang(Context context, List<KatalogPelelangModel> itemList, InterfacePelelang interfacePelelang) {
        this.context = context;
        this.itemList = itemList;
        this.interfacePelelang = interfacePelelang;
    }

    @NonNull
    @Override
    public ViewHolderProdukPelelang onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_katalog_lelang, parent,false);
        return new ViewHolderProdukPelelang(itemView);
    }


    @SuppressLint("SimpleDateFormat")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderProdukPelelang holder, int position) {

        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        KatalogPelelangModel modal = itemList.get(position);
        holder.cart_produk.setText(modal.getProduk());
        holder.cart_pelelang.setText(modal.getPelelang_id());
        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "dd/MM/yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        Log.d("TAG ", "onBindViewHolder: "+modal.getImage3()+" "+modal.getImage1());
        Date date = null;
        String result = null;

        try {
            date = inputFormat.parse(modal.getTgl_mulai());
            result = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.cart_tgl_mulai.setText(result);
        String price = "Rp."+modal.getHarga_awal();
        holder.cart_harga_tawar.setText(price);
        Glide.with(context)
                .load(ServerAPI.URL_GET_GAMBAR+"produk/"+modal.getImage3())
                .into(holder.cart_image1);
        holder.itemView.setOnClickListener(view -> {
            interfacePelelang.onClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }


    public class ViewHolderProdukPelelang extends RecyclerView.ViewHolder{
        TextView cart_produk, cart_pelelang, cart_tgl_mulai, cart_harga_tawar;
        ImageView cart_image1;
        public ViewHolderProdukPelelang(@NonNull View itemView) {
            super(itemView);
            cart_produk= itemView.findViewById(R.id.tv_kjudul_pelelang);
            cart_pelelang = itemView.findViewById(R.id.tv_JumlahProduk);
            cart_tgl_mulai = itemView.findViewById(R.id.tv_ktanggal_pelelang);
            cart_harga_tawar = itemView.findViewById(R.id.tv_kharga_pelelang);
            cart_image1 = itemView.findViewById(R.id.iv_kfoto_pelelang);
            itemView.setTag(itemView);
        }

    }
}
