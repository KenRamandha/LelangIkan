package com.example.myapplication.panitia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.panitia.PanitiaPesertaModel;
import com.example.myapplication.model.panitia.PanitiaTestimoniModel;

import java.util.ArrayList;

public class AdapterCardTestimoniPanitia extends RecyclerView.Adapter <AdapterCardTestimoniPanitia.ViewHolderTestimoniPanitia>{
    private Context context;
    private static ArrayList<PanitiaTestimoniModel> itemList;


    public AdapterCardTestimoniPanitia(Context context, ArrayList<PanitiaTestimoniModel> itemList) {
        this.context = context;
        this.itemList = itemList;
    }
    @NonNull
    @Override
    public ViewHolderTestimoniPanitia onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_testimoni_panitia,parent,false);
        return new ViewHolderTestimoniPanitia(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolderTestimoniPanitia holder, int position) {

        PanitiaTestimoniModel modal = itemList.get(position);
        holder.cart_peserta.setText(modal.getNama());
        holder.cart_keterangan.setText(modal.getTestimoni_pemenang());

    }


    public class ViewHolderTestimoniPanitia extends RecyclerView.ViewHolder {
        TextView cart_peserta,cart_keterangan;
        public ViewHolderTestimoniPanitia(@NonNull View itemView) {
            super(itemView);
            cart_peserta = itemView.findViewById(R.id.tv_nama_testimoni_panitia);
            cart_keterangan = itemView.findViewById(R.id.tv_keterangan_testimoni_panitia);
        }
    }
    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
