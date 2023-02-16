package com.example.myapplication.peserta.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.peserta.BidModel;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class AdapterCardRiwayatDetail extends RecyclerView.Adapter <AdapterCardRiwayatDetail.ViewHolderRiwayatDetail> {

    private Context context;
    private static ArrayList<BidModel> itemList;

    public AdapterCardRiwayatDetail(Context context, ArrayList<BidModel> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolderRiwayatDetail onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_lelang,parent,false);
        return new ViewHolderRiwayatDetail(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderRiwayatDetail holder, int position) {
        BidModel modal = itemList.get(position);
        holder.cart_nama.setText(modal.getNama());

        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        if (modal.getHarga_tawar() != null){
            holder.cart_harga.setText("Rp. " + decimalFormat.format(modal.getHarga_tawar()));
        }
        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "HH:mm:ss";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String result = null;

        try {
            date = inputFormat.parse(modal.getTgl_bid());
            result = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.cart_waktu.setText(result);
//        holder.cart_nama.setText(itemList.get(position).getNama());
//        holder.cart_harga.setText(itemList.get(position).getHarga());
//        holder.cart_waktu.setText(itemList.get(position).getWaktu());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolderRiwayatDetail extends RecyclerView.ViewHolder {
        TextView cart_nama, cart_harga, cart_waktu;
        public ViewHolderRiwayatDetail(@NonNull View itemView) {
            super(itemView);
            cart_nama = itemView.findViewById(R.id.tv_nama_detail_riwayat_peserta);
            cart_harga = itemView.findViewById(R.id.tv_harga_detail_riwayat_peserta);
            cart_waktu = itemView.findViewById(R.id.tv_waktu_detail_riwayat_peserta);
        }
    }

}
