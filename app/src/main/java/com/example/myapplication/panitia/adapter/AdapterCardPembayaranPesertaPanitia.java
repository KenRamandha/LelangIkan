package com.example.myapplication.panitia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.panitia.PanitiaDepositPesertaModel;
import com.example.myapplication.model.panitia.PanitiaPembayaranPesertaModel;

import java.util.ArrayList;

public class AdapterCardPembayaranPesertaPanitia extends RecyclerView.Adapter <AdapterCardPembayaranPesertaPanitia.ViewHolderPembayaranPesertaPanitia>{
    private Context context;
    private static ArrayList<PanitiaPembayaranPesertaModel> itemList;
    private ItemClickListener clickListener;

    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    public AdapterCardPembayaranPesertaPanitia(Context context, ArrayList<PanitiaPembayaranPesertaModel> itemList) {
        this.context = context;
        this.itemList = itemList;
    }
    @NonNull
    @Override
    public ViewHolderPembayaranPesertaPanitia onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_panitia_pembayaran_peserta,parent,false);
        return new ViewHolderPembayaranPesertaPanitia(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolderPembayaranPesertaPanitia holder, int position) {

        PanitiaPembayaranPesertaModel modal = itemList.get(position);
        holder.cart_peserta.setText(modal.getNama());



        String status,status1;

        status = modal.getStatus();

        if (status.equals("0")) {
            status1 = "Belum Diverivikasi";
        } else if (status.equals("1")){
            status1 = "Terverifikasi";
        } else if (status.equals("2")){
            status1 = "Ditolak Panitia";
        } else {
            status1 = "Ditolak Sistem";
        }

        holder.cart_status.setText(status1);
    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setClickListener(AdapterCardPembayaranPesertaPanitia.ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }


    public class ViewHolderPembayaranPesertaPanitia extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView cart_peserta,cart_detail,cart_status;
        LinearLayout linearLayout;
        ImageView imageView;
        public ViewHolderPembayaranPesertaPanitia(@NonNull View itemView) {
            super(itemView);
            cart_peserta = itemView.findViewById(R.id.tv_pembayaran_nama_peserta_panitia);
            linearLayout = itemView.findViewById(R.id.ln_pembayaran_peserta_panitia);
            imageView = itemView.findViewById(R.id.iv_pembayaran_peserta_panitia);
            cart_detail = itemView.findViewById(R.id.tv_pembayaran_peseta_panitia);
            cart_status = itemView.findViewById(R.id.tv_pembayaran_deposit_peserta_panitia);

            linearLayout.setOnClickListener(this);
            cart_peserta.setOnClickListener(this);
            imageView.setOnClickListener(this);
            cart_detail.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view,
                    getAdapterPosition());
        }
    }
}
