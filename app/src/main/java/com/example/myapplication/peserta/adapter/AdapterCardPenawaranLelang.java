package com.example.myapplication.peserta.adapter;

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
import com.example.myapplication.model.peserta.KatalogModel;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class AdapterCardPenawaranLelang extends RecyclerView.Adapter <AdapterCardPenawaranLelang.ViewHolderPenawaranLelang> {

    private static ArrayList<KatalogModel> itemList;
    private Context context;
    private ItemClickListener clickListener;

    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    public AdapterCardPenawaranLelang(Context context, ArrayList<KatalogModel> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolderPenawaranLelang onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_penawaran_lelang,parent,false);
        return new ViewHolderPenawaranLelang(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPenawaranLelang holder, int position) {
        KatalogModel modal = itemList.get(position);
        holder.cart_produk.setText(modal.getProduk());
        holder.cart_pelelang.setText(modal.getPelelang_id());

        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "dd/MM/yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String result = null;

        try {
            date = inputFormat.parse(modal.getTgl_mulai());
            result = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");


        holder.cart_tgl_mulai.setText(result);//modal.getTgl_mulai());
        holder.cart_harga_awal.setText("Rp. " + decimalFormat.format(Integer.parseInt(modal.getHarga_awal())));
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

    public class ViewHolderPenawaranLelang extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView cart_produk, cart_pelelang, cart_tgl_mulai, cart_harga_awal;
        ImageView cart_image1;
        public ViewHolderPenawaranLelang(@NonNull View itemView) {
            super(itemView);
            cart_produk= itemView.findViewById(R.id.tv_judul_penawaran_lelang);
            cart_pelelang = itemView.findViewById(R.id.tv_nelayan_penawaran_lelang);
            cart_tgl_mulai = itemView.findViewById(R.id.tv_waktu_penawaran_lelang);
            cart_harga_awal = itemView.findViewById(R.id.tv_harga_penawaran_lelang);
            cart_image1 = itemView.findViewById(R.id.iv_penawaran_lelang);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
            cart_image1.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view,
                    getAdapterPosition());
        }
    }
}
