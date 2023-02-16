package com.example.myapplication.pelelang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.pelelang.RiwayatPenawaranModel;

import java.util.ArrayList;

public class AdapterCardRiwayatPenawaran extends RecyclerView.Adapter <AdapterCardRiwayatPenawaran.ViewHolderHasilPenawaran> {
    private Context context;
    private static ArrayList<RiwayatPenawaranModel> itemList;

    private ItemClickListener clickListener;

    public interface ItemClickListener {
        void onClick(View view, int position);
    }
    public AdapterCardRiwayatPenawaran(Context context, ArrayList<RiwayatPenawaranModel> itemList) {
        this.context = context;
        this.itemList = itemList;
    }
    @Override
    public ViewHolderHasilPenawaran onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_riwayat_penawaran,parent,false);
        return new ViewHolderHasilPenawaran(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolderHasilPenawaran holder, int position) {
        RiwayatPenawaranModel modal = itemList.get(position);
        holder.tv_namaproduk.setText(modal.getProduk());
        holder.tv_TotalBayar.setText(modal.getHarga_tawar());
        holder.tv_jumlahProduk.setText(modal.getJumlah()+"Kg");
        holder.tv_namaPeserta.setText(modal.getNama());

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class ViewHolderHasilPenawaran extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_namaproduk, tv_idproduk, tv_TotalBayar, tv_namaPeserta, tv_jumlahProduk;

        public ViewHolderHasilPenawaran(@NonNull View itemView) {
            super(itemView);
            tv_namaproduk = itemView.findViewById(R.id.tv_namaproduk);
            tv_TotalBayar = itemView.findViewById(R.id.tv_TotalBayar);
            tv_namaPeserta = itemView.findViewById(R.id.tv_namaPeserta);
            tv_jumlahProduk = itemView.findViewById(R.id.tv_jumlahProduk);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view,
                    getAdapterPosition());
        }
    }
}
