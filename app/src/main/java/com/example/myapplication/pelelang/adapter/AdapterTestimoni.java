package com.example.myapplication.pelelang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.pelelang.RiwayatTestimoniModel;

import java.util.ArrayList;

public class AdapterTestimoni extends RecyclerView.Adapter<AdapterTestimoni.ViewHolderTestimoni> {
    private Context context;
    private static ArrayList<RiwayatTestimoniModel> itemList;

    private ItemClickListener clickListener;

    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    public AdapterTestimoni(Context context, ArrayList<RiwayatTestimoniModel> itemList) {
        this.context = context;
        this.itemList = itemList;
    }


    @Override
    public ViewHolderTestimoni onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_testimoni_pelelang, parent, false);
        return new ViewHolderTestimoni(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolderTestimoni holder, int position) {
        RiwayatTestimoniModel modal = itemList.get(position);
        holder.tv_namaproduk.setText(modal.getProduk());
        holder.tv_peserta.setText(modal.getNama());
        holder.tv_testi.setText(modal.getTestimoni());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class ViewHolderTestimoni extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_namaproduk, tv_testi, tv_peserta;

        public ViewHolderTestimoni( View itemView) {
            super(itemView);
            tv_namaproduk = itemView.findViewById(R.id.tv_produk);
            tv_peserta = itemView.findViewById(R.id.tv_nmpeserta);
            tv_testi = itemView.findViewById(R.id.tv_testimoni);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view,
                    getAdapterPosition());
        }
    }

}
