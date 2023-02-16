package com.example.myapplication.pelelang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.model.pelelang.PembayaranPanitiaModel;

import java.util.ArrayList;
import java.util.List;

public class AdapterPembayaranPelelang extends RecyclerView.Adapter<AdapterPembayaranPelelang.ViewHolder> {
    Context context;
    List<PembayaranPanitiaModel> pembayaranPanitiaModelList = new ArrayList<>();

    public AdapterPembayaranPelelang(Context context, List<PembayaranPanitiaModel> pembayaranPanitiaModelList) {
        this.context = context;
        this.pembayaranPanitiaModelList = pembayaranPanitiaModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_pembayaran_pelelang, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        PembayaranPanitiaModel pembayaranPanitiaModel = pembayaranPanitiaModelList.get(position);

        holder.tvIdProduk.setText(pembayaranPanitiaModel.getLelang_id());
        holder.tvNominalDiPembayarakan.setText(pembayaranPanitiaModel.getNominal_dibayarkan());
        holder.tv_nama.setText(pembayaranPanitiaModel.getNama());
        Glide.with(holder.itemView.getContext()).load(pembayaranPanitiaModel.getBukti_transfer()).into(holder.bukti_bayar);
    }

    @Override
    public int getItemCount() {
        return pembayaranPanitiaModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvIdProduk,  tvNominalDiPembayarakan,tv_nama;
        ImageView bukti_bayar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvIdProduk = itemView.findViewById(R.id.tv_idproduk);
            tvNominalDiPembayarakan = itemView.findViewById(R.id.tv_nominal_dibayarkan);
            tv_nama = itemView.findViewById(R.id.tv_nama);
            bukti_bayar=itemView.findViewById(R.id.bukti_bayar);

        }
    }
}
