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
import com.example.myapplication.model.admin.AdminPesertaModel;
import com.example.myapplication.model.panitia.PanitiaPesertaModel;

import java.util.ArrayList;

public class AdapterCardPesertaPanitia extends RecyclerView.Adapter <AdapterCardPesertaPanitia.ViewHolderPesertaPanitia>{
    private Context context;
    private static ArrayList<PanitiaPesertaModel> itemList;
    private ItemClickListener clickListener;

    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    public AdapterCardPesertaPanitia(Context context, ArrayList<PanitiaPesertaModel> itemList) {
        this.context = context;
        this.itemList = itemList;
    }
    @NonNull
    @Override
    public ViewHolderPesertaPanitia onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_status_peserta_panitia,parent,false);
        return new ViewHolderPesertaPanitia(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolderPesertaPanitia holder, int position) {

        PanitiaPesertaModel modal = itemList.get(position);
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

    public void setClickListener(AdapterCardPesertaPanitia.ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }


    public class ViewHolderPesertaPanitia extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView cart_peserta,cart_detail,cart_status;
        LinearLayout linearLayout;
        ImageView imageView;
        public ViewHolderPesertaPanitia(@NonNull View itemView) {
            super(itemView);
            cart_peserta = itemView.findViewById(R.id.tv_nama_peserta_panitia);
            linearLayout = itemView.findViewById(R.id.ln_peserta_panitia);
            imageView = itemView.findViewById(R.id.iv_peserta_panitia);
            cart_detail = itemView.findViewById(R.id.tv_peseta_panitia);
            cart_status = itemView.findViewById(R.id.tv_status_peserta_panitia);

            //itemView.setTag(itemView);
//            itemView.setOnClickListener(this);
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
