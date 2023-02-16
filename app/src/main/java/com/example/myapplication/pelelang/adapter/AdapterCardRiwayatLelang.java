package com.example.myapplication.pelelang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.pelelang.RiwayatLelangModel;

import java.util.ArrayList;

public class AdapterCardRiwayatLelang extends RecyclerView.Adapter <AdapterCardRiwayatLelang.ViewHolderHasilLelang>{
    private Context context;
    private static ArrayList<RiwayatLelangModel> itemList;
    String statusJenis;
    private ItemClickListener clickListener;

    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    public AdapterCardRiwayatLelang(Context context, ArrayList<RiwayatLelangModel> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolderHasilLelang onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_riwayat_pelelang,parent,false);
        return new ViewHolderHasilLelang(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderHasilLelang holder, int position) {
        RiwayatLelangModel modal = itemList.get(position);
        Integer status = (modal.getStatus() == null)?0:modal.getStatus();

        if(status==0){
            statusJenis = "Belum diterima";
        }else if(status==1){
            statusJenis = "Telah diterima";
        }
        holder.tv_Status.setText(statusJenis);
        holder.tv_namaproduk.setText(modal.getProduk());
        holder.tv_TotalBayar.setText(modal.getTotalBayar());
        holder.tv_jumlahProduk.setText(modal.getJumlahProduk()+"Kg");
        holder.tv_namaPeserta.setText(modal.getNamaPeserta());
        holder.tv_hargaProduk.setText(modal.getHarga_awal());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class ViewHolderHasilLelang extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tv_namaproduk,tv_Status,tv_hargaProduk,tv_namaPeserta,tv_TotalBayar,tv_jumlahProduk;
        public ViewHolderHasilLelang(@NonNull View itemView){
            super(itemView);
            tv_namaproduk=itemView.findViewById(R.id.tv_namaproduk);
            tv_hargaProduk=itemView.findViewById(R.id.tv_hargaProduk);
            tv_Status =itemView.findViewById(R.id.tv_Status);
            tv_namaPeserta=itemView.findViewById(R.id.tv_namaPeserta);
            tv_TotalBayar=itemView.findViewById(R.id.tv_TotalBayar);
            tv_jumlahProduk=itemView.findViewById(R.id.tv_jumlahProduk);

            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view){
            if (clickListener != null) clickListener.onClick(view,
                    getAdapterPosition());
        }
    }
}
