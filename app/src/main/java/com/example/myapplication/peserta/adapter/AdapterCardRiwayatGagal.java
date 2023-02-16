package com.example.myapplication.peserta.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.peserta.RiwayatLelangPesertaModel;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class AdapterCardRiwayatGagal extends RecyclerView.Adapter <AdapterCardRiwayatGagal.ViewHolderRiwayatGagal> {

    private static ArrayList<RiwayatLelangPesertaModel> itemList;
    private Context context;
    private ItemClickListener clickListener;

    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    public AdapterCardRiwayatGagal(Context context, ArrayList<RiwayatLelangPesertaModel> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolderRiwayatGagal onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_riwayat_gagal,parent,false);
        return new ViewHolderRiwayatGagal(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderRiwayatGagal holder, int position) {
        RiwayatLelangPesertaModel modal = itemList.get(position);
        holder.cart_produk.setText(modal.getProduk());
        holder.cart_nelayan.setText(modal.getNama_pelelang());

        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        holder.cart_harga.setText("Rp. " + decimalFormat.format(Integer.parseInt(modal.getTotal_bayar())));

        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "dd/MM/yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String result = null;

        try {
            date = inputFormat.parse(modal.getTgl_diumumkan());
            result = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        holder.cart_tanggal.setText(result);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setClickListener(AdapterCardRiwayatGagal.ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class ViewHolderRiwayatGagal extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView cart_produk,cart_nelayan,cart_harga, cart_tanggal;

        public ViewHolderRiwayatGagal(@NonNull View itemView) {
            super(itemView);
            cart_produk = itemView.findViewById(R.id.tv_ikan_riwayat_gagal);
            cart_nelayan = itemView.findViewById(R.id.tv_nelayan_riwayat_gagal);
            cart_harga = itemView.findViewById(R.id.tv_harga_riwayat_gagal);
            cart_tanggal = itemView.findViewById(R.id.tv_tanggal_riwayat_gagal);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) clickListener.onClick(v,
                    getAdapterPosition());
        }
    }


}
