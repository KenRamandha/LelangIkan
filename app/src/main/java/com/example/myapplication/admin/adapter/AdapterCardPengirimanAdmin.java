package com.example.myapplication.admin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.model.admin.AdminPengemasanModel;
import com.example.myapplication.model.admin.AdminPesertaModel;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdapterCardPengirimanAdmin extends RecyclerView.Adapter <AdapterCardPengirimanAdmin.ViewHolderPengirimanAdmin>{
    private Context context;
    private static ArrayList<AdminPengemasanModel> itemList;
    private ItemClickListener clickListener;

    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    public AdapterCardPengirimanAdmin(Context context, ArrayList<AdminPengemasanModel> itemList) {
        this.context = context;
        this.itemList = itemList;
    }
    @NonNull
    @Override
    public ViewHolderPengirimanAdmin onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_admin_pengemasan,parent,false);
        return new ViewHolderPengirimanAdmin(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolderPengirimanAdmin holder, int position) {

        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        AdminPengemasanModel modal = itemList.get(position);
        holder.cart_pengirim.setText(modal.getNamaPengirim());
        holder.cart_produk.setText(modal.getProduk());
//        holder.cart_harga.setText("Rp. " + decimalFormat.format(Integer.parseInt(modal.getNominal_dibayarkan())));
        holder.cart_total_bayar.setText("Rp. " + decimalFormat.format(Integer.parseInt(modal.getTotal_bayar())));

//        holder.cart_penerima.setText(modal.getNo_polisi());
        holder.cart_alamat.setText(modal.getAlamat_kirim());

        holder.cart_nama_kendaraan.setText(modal.getNama_kendaraan());
        holder.cart_no_hp.setText(modal.getNo_hp());
        holder.cart_no_polisi.setText(modal.getNo_polisi());


        String inputPattern = "yyyy-MM-dd";
        String outputPattern = "dd/MM/yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String result = null;

        try {
            date = inputFormat.parse(modal.getTgl_pengiriman());
            result = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        holder.cart_tgl_pengiriman.setText(result);

        String status,status1;

        status = modal.getStatus_transaksi();

        if (status.equals("1")) {
            status1 = " Sedang Dikirim";
        } else if (status.equals("2")){
            status1 = " Sudah Diterima";
//        } else if (status.equals("3")){
//            status1 = " Sudah Sampai ";
        } else {
            status1 = "Sedang Diproses";
        }
        holder.cart_status.setText(status1);

        Glide.with(holder.itemView.getContext())
                .load(modal.getImage1())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setClickListener(AdapterCardPengirimanAdmin.ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class ViewHolderPengirimanAdmin extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView cart_pengirim,cart_produk,cart_harga,cart_penerima,cart_alamat,cart_tgl_pengiriman,cart_status, cart_total_bayar, cart_nama_kendaraan, cart_no_hp, cart_no_polisi;
        ImageView imageView;
        public ViewHolderPengirimanAdmin(@NonNull View itemView) {
            super(itemView);
            cart_pengirim = itemView.findViewById(R.id.tv_pengirim_pengemasan_admin);
            cart_produk = itemView.findViewById(R.id.tv_produk_pengemasan_admin);
//            cart_harga = itemView.findViewById(R.id.tv_harga_pengemasan_admin);
            cart_pengirim = itemView.findViewById(R.id.tv_pengirim_pengemasan_admin);
//            cart_penerima = itemView.findViewById(R.id.tv_penerima_pengemasan_admin);
            cart_alamat = itemView.findViewById(R.id.tv_alamat_pengemsan_admin);
            cart_tgl_pengiriman = itemView.findViewById(R.id.tv_tgl_pengiriman_admin);
            cart_status = itemView.findViewById(R.id.tv_status_pengemasan_admin);
            cart_total_bayar = itemView.findViewById(R.id.tv_harga_pengemasan_admin);
            imageView = itemView.findViewById(R.id.iv_gambar_pengemasan_admin);

            cart_nama_kendaraan = itemView.findViewById(R.id.tv_nama_kendaraan);
            cart_no_hp = itemView.findViewById(R.id.tv_no_hp);
            cart_no_polisi = itemView.findViewById(R.id.tv_no_polisi);


            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
            imageView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view,
                    getAdapterPosition());
        }
    }
}
