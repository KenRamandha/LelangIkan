package com.example.myapplication.pelelang.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.pelelang.DataPengiriman;
import com.example.myapplication.pelelang.hostui.DetailPengirimanPelelang;
import com.google.gson.Gson;

import java.util.List;

public class AdapterDataPengiriman extends RecyclerView.Adapter <AdapterDataPengiriman.ViewHolder>  {

    private static List<DataPengiriman> itemList;
    private final Context context;
    private static final Gson gson = new Gson();
    String jsonString;

    public AdapterDataPengiriman(Context context, List<DataPengiriman> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_semua_pengiriman,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DataPengiriman modal = itemList.get(position);
        checkStatus(modal.getStatusTransaksi(),holder.tvStatus);
        holder.tvTitle.setText(modal.getPengirimanId());
        holder.tvPrice.setText(modal.getTglPengiriman());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String jsonString = gson.toJson(modal);
                Intent intent = new Intent(context, DetailPengirimanPelelang.class);
                intent.putExtra("data",jsonString);
                Log.i("cek", "onClick: "+jsonString);
                context.startActivity(intent);
            }
        });
    }

    private void checkStatus(String status,TextView textView) {
        switch (status) {
            case "0":
                textView.setText("Sedang di proses");
                break;
            case "1":
                textView.setText("Terkirim");
                break;
            case "2":
                textView.setText("Selesai");
                break;
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle, tvStatus, tvPrice;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvStatus = itemView.findViewById(R.id.tvStatus);
        }
    }
}
