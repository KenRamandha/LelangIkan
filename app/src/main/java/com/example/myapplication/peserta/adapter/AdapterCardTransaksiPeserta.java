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
import com.example.myapplication.model.panitia.PanitiaPengemasanModel;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdapterCardTransaksiPeserta extends RecyclerView.Adapter <AdapterCardTransaksiPeserta.ViewHolderPengemasanPanitia>{
    private Context context;
    private static ArrayList<PanitiaPengemasanModel> itemList;
    private ItemClickListener clickListener;

    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    public AdapterCardTransaksiPeserta(Context context, ArrayList<PanitiaPengemasanModel> itemList) {
        this.context = context;
        this.itemList = itemList;
    }
    @NonNull
    @Override
    public ViewHolderPengemasanPanitia onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_transaksi_pembayaran_peserta,parent,false);
        return new ViewHolderPengemasanPanitia(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolderPengemasanPanitia holder, int position) {

        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        PanitiaPengemasanModel modal = itemList.get(position);
        holder.cart_pengirim.setText(modal.getNama_pengirim());
        holder.cart_produk.setText(modal.getProduk());
        holder.cart_harga.setText("Rp. " + decimalFormat.format(Integer.parseInt(modal.getNominal_dibayarkan())));

        holder.cart_pemenang.setText(modal.getNama());
        holder.cart_alamat.setText(modal.getAlamat());


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

        holder.cart_tanggal.setText(result);

        String status,status1;

        status = modal.getStatus_transaksi();

        if (status.equals("1")) {
            status1 = "    Sedang di Proses    ";
        } else if (status.equals("2")){
            status1 = "    Sedang di Kirim    ";
        } else if (status.equals("3")){
            status1 = "    Sudah Sampai    ";
        } else {
            status1 = "-";
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

    public void setClickListener(AdapterCardTransaksiPeserta.ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class ViewHolderPengemasanPanitia extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView cart_pengirim,cart_produk,cart_harga,cart_pemenang,cart_alamat,cart_tanggal,cart_status,cart_nominal;
        ImageView imageView,status1,status2,status3;
        Button btnDetail;
        public ViewHolderPengemasanPanitia(@NonNull View itemView) {
            super(itemView);
            cart_pengirim = itemView.findViewById(R.id.tv_pengirim_transaksi_peserta);
            cart_produk = itemView.findViewById(R.id.tv_produk_transaksi_peserta);
            cart_harga = itemView.findViewById(R.id.tv_harga_transaksi_peserta);
            cart_pemenang = itemView.findViewById(R.id.tv_pemenang_transaksi_peserta);
//            cart_alamat = itemView.findViewById(R.id.tv_alamat_transaksi_peserta);
            cart_tanggal = itemView.findViewById(R.id.tv_tanggal_transaksi_peserta);
            cart_nominal = itemView.findViewById(R.id.tv_total_transaksi_peserta);
            imageView = itemView.findViewById(R.id.iv_gambar_transaksi_peserta);
            status1 = itemView.findViewById(R.id.iv_menunggu_transaksi_peserta);
            status2 = itemView.findViewById(R.id.iv_tolak_transaksi_peserta);
            status3= itemView.findViewById(R.id.iv_verif_transaksi_peserta);
            btnDetail = itemView.findViewById(R.id.btn_detail_transaksi_peserta);

            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
            btnDetail.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view,
                    getAdapterPosition());
        }
    }
}
