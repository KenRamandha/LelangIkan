package com.example.myapplication.panitia.adapter;

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
import com.example.myapplication.model.panitia.PanitiaRiwayatLelangModel;
import com.example.myapplication.model.peserta.KatalogModel;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class AdapterCardRiwayatLelangPanitia extends RecyclerView.Adapter <AdapterCardRiwayatLelangPanitia.ViewHolderRiwayatLelangPanitia> {

    private static ArrayList<PanitiaRiwayatLelangModel> itemList;
    private Context context;
    private ItemClickListener clickListener;

    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    public AdapterCardRiwayatLelangPanitia(Context context, ArrayList<PanitiaRiwayatLelangModel> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolderRiwayatLelangPanitia onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_riwayat_panitia,parent,false);
        return new ViewHolderRiwayatLelangPanitia(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderRiwayatLelangPanitia holder, int position) {
        PanitiaRiwayatLelangModel modal = itemList.get(position);
        holder.cart_pelelang.setText(modal.getNama_pelelang());
        holder.cart_produk.setText(modal.getProduk());
        holder.cart_pemenang.setText(modal.getNama_peserta());
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
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");


        holder.cart_tanggal.setText(result);//modal.getTgl_mulai());
        if (modal.getHarga_awal() != null){
            holder.cart_harga.setText("Rp. " + decimalFormat.format(Integer.parseInt(modal.getHarga_awal())));
        }

        if (modal.getTotal_bayar() != null){
            holder.cart_total.setText("Rp. " + decimalFormat.format(Integer.parseInt(modal.getTotal_bayar())));
        }


        Glide.with(holder.itemView.getContext())
                .load(modal.getImage1())
                .into(holder.cart_image1);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class ViewHolderRiwayatLelangPanitia extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView cart_pelelang,cart_produk,cart_harga,cart_total,cart_tanggal,cart_pemenang,cart_alamat;
        ImageView cart_image1;
        Button cart_btn;
        public ViewHolderRiwayatLelangPanitia(@NonNull View itemView) {
            super(itemView);
            cart_pelelang= itemView.findViewById(R.id.tv_pelelang_riwayat_panitia);
            cart_produk = itemView.findViewById(R.id.tv_produk_riwayat_panitia);
            cart_harga = itemView.findViewById(R.id.tv_harga_riwayat_panitia);
            cart_total = itemView.findViewById(R.id.tv_total_riwayat_panitia);
            cart_tanggal = itemView.findViewById(R.id.tv_tanggal_riwayat_panitia);
            cart_pemenang = itemView.findViewById(R.id.tv_pemenang_riwayat_panitia);
            cart_alamat = itemView.findViewById(R.id.tv_alamat_riwayat_panitia);
            cart_image1 = itemView.findViewById(R.id.iv_gambar_riwayat_panitia);
            cart_btn = itemView.findViewById(R.id.btn_riwayat_lelang1_panitia);

            cart_btn.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view,
                    getAdapterPosition());
        }
    }
}
