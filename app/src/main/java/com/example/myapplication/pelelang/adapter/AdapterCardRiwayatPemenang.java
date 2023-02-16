package com.example.myapplication.pelelang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.pelelang.RiwayatPemenangModel;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class AdapterCardRiwayatPemenang extends RecyclerView.Adapter <AdapterCardRiwayatPemenang.ViewHolderPemenang>{
    private Context context;
    private static ArrayList<RiwayatPemenangModel> itemList;

    public AdapterCardRiwayatPemenang(Context context, ArrayList<RiwayatPemenangModel> itemList) {
        this.context = context;
        this.itemList = itemList;
    }
    @NonNull
    @Override
    public ViewHolderPemenang onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_pemenang_pelelang,parent,false);
        return new ViewHolderPemenang(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolderPemenang holder, int position) {

        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        RiwayatPemenangModel modal = itemList.get(position);
        holder.cart_tanggal_menang.setText(modal.getTgl_diumumkan());
        holder.cart_harga_menang.setText(modal.getHargatawar());
        holder.cart_peserta_menang.setText(modal.getNama());
        holder.cart_ikan_menang.setText(modal.getProduk());

        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "dd/MM/yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }


    public class ViewHolderPemenang extends RecyclerView.ViewHolder {
        TextView cart_peserta_menang, cart_ikan_menang, cart_harga_menang, cart_tanggal_menang;
        public ViewHolderPemenang(@NonNull View itemView) {
            super(itemView);
            cart_peserta_menang = itemView.findViewById(R.id.tv_namaPeserta);
            cart_ikan_menang = itemView.findViewById(R.id.tv_namaproduk);
            cart_tanggal_menang = itemView.findViewById(R.id.tv_tgldiumumkan);
            cart_harga_menang = itemView.findViewById(R.id.tv_hargaProduk);

        }
    }
}
