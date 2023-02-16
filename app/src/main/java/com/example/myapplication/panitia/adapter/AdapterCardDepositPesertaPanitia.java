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
import com.example.myapplication.model.panitia.PanitiaPesertaModel;

import java.util.ArrayList;

public class AdapterCardDepositPesertaPanitia extends RecyclerView.Adapter <AdapterCardDepositPesertaPanitia.ViewHolderDepositPesertaPanitia>{
    private Context context;
    private static ArrayList<PanitiaDepositPesertaModel> itemList;
    private ItemClickListener clickListener;

    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    public AdapterCardDepositPesertaPanitia(Context context, ArrayList<PanitiaDepositPesertaModel> itemList) {
        this.context = context;
        this.itemList = itemList;
    }
    @NonNull
    @Override
    public ViewHolderDepositPesertaPanitia onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_panitia_deposit_peserta,parent,false);
        return new ViewHolderDepositPesertaPanitia(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolderDepositPesertaPanitia holder, int position) {

        PanitiaDepositPesertaModel modal = itemList.get(position);
        holder.cart_peserta.setText(modal.getNama());



        String status,status1;

        status = modal.getStatus();

        if (status.equals("0")) {
            status1 = "Belum Diverivikasi";
        } else if (status.equals("1")){
            status1 = "Terverifikasi";
        } else {
            status1 = "Tertolak";
        }

        holder.cart_status.setText(status1);
    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setClickListener(AdapterCardDepositPesertaPanitia.ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }


    public class ViewHolderDepositPesertaPanitia extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView cart_peserta,cart_detail,cart_status;
        LinearLayout linearLayout;
        ImageView imageView;
        public ViewHolderDepositPesertaPanitia(@NonNull View itemView) {
            super(itemView);
            cart_peserta = itemView.findViewById(R.id.tv_deposit_nama_peserta_panitia);
            linearLayout = itemView.findViewById(R.id.ln_deposit_peserta_panitia);
            imageView = itemView.findViewById(R.id.iv_deposit_peserta_panitia);
            cart_detail = itemView.findViewById(R.id.tv_deposit_peseta_panitia);
            cart_status = itemView.findViewById(R.id.tv_status_deposit_peserta_panitia);

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
