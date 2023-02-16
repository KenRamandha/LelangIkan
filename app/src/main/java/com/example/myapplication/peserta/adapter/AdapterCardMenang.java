package com.example.myapplication.peserta.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.model.peserta.PemenangPesertaModel;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class AdapterCardMenang extends RecyclerView.Adapter <AdapterCardMenang.ViewHolderMenang> {

    private Context context;
    private static ArrayList<PemenangPesertaModel> itemList;
    private ItemClickListener clickListener;

    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    public AdapterCardMenang(Context context, ArrayList<PemenangPesertaModel> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolderMenang onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_menang_lelang,parent,false);
        return new ViewHolderMenang(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderMenang holder, int position) {
        PemenangPesertaModel modal = itemList.get(position);
        holder.cart_judul.setText(modal.getProduk());

        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
//        tvBayar.setText("Rp. " +decimalFormat.format(Integer.parseInt(bayar)));
        if (modal.getHarga_tawar() !=null){
            holder.cart_harga.setText("Rp. "+decimalFormat.format(Integer.parseInt(modal.getHarga_tawar())));
        }

        Glide.with(holder.itemView.getContext())
                .load(modal.getImage1())
                .into(holder.cart_gambar);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class ViewHolderMenang extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView cart_judul, cart_tanggal, cart_harga;
        ImageView cart_gambar;
        public ViewHolderMenang(@NonNull View itemView) {
            super(itemView);
            cart_judul = itemView.findViewById(R.id.tv_judul_menang_lelang);
            cart_harga = itemView.findViewById(R.id.tv_harga_menang_lelang);
            cart_gambar = itemView.findViewById(R.id.iv_menang_lelang);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
            cart_gambar.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view,
                    getAdapterPosition());
        }
    }

}
