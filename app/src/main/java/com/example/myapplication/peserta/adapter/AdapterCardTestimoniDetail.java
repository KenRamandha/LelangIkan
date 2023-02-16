package com.example.myapplication.peserta.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.admin.PemenangModel;
import com.example.myapplication.model.peserta.TestimoniDetailModel;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class AdapterCardTestimoniDetail extends RecyclerView.Adapter <AdapterCardTestimoniDetail.ViewHolderTestimoniDetail> {

    private Context context;
    private static ArrayList<TestimoniDetailModel> itemList;

    public AdapterCardTestimoniDetail(Context context, ArrayList<TestimoniDetailModel> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolderTestimoniDetail onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.card_testimoni,parent,false);
        return new ViewHolderTestimoniDetail(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderTestimoniDetail holder, int position) {
        TestimoniDetailModel modal = itemList.get(position);
        holder.cart_nama.setText(modal.getNama());
        holder.cart_deskripsi.setText(modal.getTestimoni_pemenang());
        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "dd/MM/yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String result = null;

        if (modal.getTgl_diumumkan() != null) {
            try {
                date = inputFormat.parse(modal.getTgl_diumumkan());
                result = outputFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        holder.cart_tanggal.setText(result);

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolderTestimoniDetail extends RecyclerView.ViewHolder {
        TextView cart_nama, cart_deskripsi, cart_tanggal;
        public ViewHolderTestimoniDetail(@NonNull View itemView) {
            super(itemView);
            cart_nama = (TextView)itemView.findViewById(R.id.tv_nama_detail_testimoni);
            cart_deskripsi = (TextView)itemView.findViewById(R.id.tv_deskripsi_testimoni);
            cart_tanggal = (TextView)itemView.findViewById(R.id.tv_waktu_detail_testimoni);
        }
    }
}
