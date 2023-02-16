package com.example.myapplication.peserta.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.model.peserta.KatalogModel;
import com.example.myapplication.model.peserta.PembayaranTransaksiPesertaModel;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class AdapterCardPembayaranTransaksiPeserta extends RecyclerView.Adapter <AdapterCardPembayaranTransaksiPeserta.ViewHolderPembayaranTransaksiPeserta> {

    private static ArrayList<PembayaranTransaksiPesertaModel> itemList;
    private Context context;
    private ItemClickListener clickListener;

    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    public AdapterCardPembayaranTransaksiPeserta(Context context, ArrayList<PembayaranTransaksiPesertaModel> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolderPembayaranTransaksiPeserta onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_transaksi_pembayaran_peserta,parent,false);
        return new ViewHolderPembayaranTransaksiPeserta(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPembayaranTransaksiPeserta holder, int position) {
        PembayaranTransaksiPesertaModel modal = itemList.get(position);
        holder.cart_pengirim.setText(modal.getNama_pelelang());
        holder.cart_produk.setText(modal.getProduk());
//        holder.cart_tanggal.setText(modal.getTgl_selesai());
         holder.cart_pememang.setText(modal.getNama_peserta());

        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "dd/MM/yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String result = null;

        try {
            date = inputFormat.parse(modal.getTgl_selesai());
            result = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");


        holder.cart_tanggal.setText(result);//modal.getTgl_mulai());
        holder.cart_harga_awal.setText("Rp. " + decimalFormat.format(Integer.parseInt(modal.getHarga_awal())));
        holder.cart_total_bayar.setText("Rp. " + decimalFormat.format(Integer.parseInt(modal.getTotal_bayar())));

        Glide.with(holder.itemView.getContext())
                .load(modal.getImage1())
                .into(holder.cart_image1);

        String status,status1;

        status = modal.getStatus();

        if (status.equals("0")){
            status1 = "   Menunggu Verifikasi   ";
            holder.cart_menunggu.setVisibility(View.VISIBLE);
        } else if (status.equals("1")){
            status1 = "   Terverifikasi   ";
            holder.cart_verif.setVisibility(View.VISIBLE);
        } else if (status.equals("2")){
            status1 = "   Ditolak  ";
            holder.cart_tolak.setVisibility(View.VISIBLE);
        } else {
            status1 = "Tertolak Sistem ";
            holder.cart_tolak.setVisibility(View.VISIBLE);
        }
        holder.cart_status.setText(status1);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class ViewHolderPembayaranTransaksiPeserta extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView cart_pengirim,cart_produk,cart_tanggal,cart_harga_awal,cart_total_bayar,cart_pememang,cart_status;
        ImageView cart_image1,cart_menunggu,cart_tolak,cart_verif;
        Button btnDetail;
        public ViewHolderPembayaranTransaksiPeserta(@NonNull View itemView) {
            super(itemView);
            cart_pengirim = itemView.findViewById(R.id.tv_pengirim_transaksi_peserta);
            cart_produk= itemView.findViewById(R.id.tv_produk_transaksi_peserta);
            cart_tanggal = itemView.findViewById(R.id.tv_tanggal_transaksi_peserta);
            cart_harga_awal = itemView.findViewById(R.id.tv_harga_transaksi_peserta);
            cart_total_bayar = itemView.findViewById(R.id.tv_total_transaksi_peserta);
            cart_pememang = itemView.findViewById(R.id.tv_pemenang_transaksi_peserta);
            cart_status = itemView.findViewById(R.id.tv_status_transaksi_peserta);
            cart_image1 = itemView.findViewById(R.id.iv_gambar_transaksi_peserta);
            cart_menunggu = itemView.findViewById(R.id.iv_menunggu_transaksi_peserta);
            cart_tolak = itemView.findViewById(R.id.iv_tolak_transaksi_peserta);
            cart_verif = itemView.findViewById(R.id.iv_verif_transaksi_peserta);
            btnDetail = itemView.findViewById(R.id.btn_detail_transaksi_peserta);

//            itemView.setTag(itemView);
//            itemView.setOnClickListener(this);
            btnDetail.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view,
                    getAdapterPosition());
        }
    }
}
