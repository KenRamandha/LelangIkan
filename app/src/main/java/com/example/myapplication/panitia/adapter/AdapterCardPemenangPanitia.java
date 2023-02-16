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
import com.example.myapplication.model.panitia.PanitiaPemenangModel;
import com.example.myapplication.model.panitia.PanitiaPesertaModel;

import java.util.ArrayList;

public class AdapterCardPemenangPanitia extends RecyclerView.Adapter <AdapterCardPemenangPanitia.ViewHolderPemenangPanitia>{
    private Context context;
    private static ArrayList<PanitiaPemenangModel> itemList;
    private ItemClickListener clickListener;

    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    public AdapterCardPemenangPanitia(Context context, ArrayList<PanitiaPemenangModel> itemList) {
        this.context = context;
        this.itemList = itemList;
    }
    @NonNull
    @Override
    public ViewHolderPemenangPanitia onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_status_pemenang_panitia,parent,false);
        return new ViewHolderPemenangPanitia(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolderPemenangPanitia holder, int position) {

        PanitiaPemenangModel modal = itemList.get(position);
        holder.cart_nama.setText(modal.getProduk());

        String status,status1;

        status = modal.getStatus();

        if (status.equals("0")) {
            status1 = "Belum Diverivikasi";
        } else if (status.equals("1")){
            status1 = "Terverifikasi";
        }else if (status.equals("2")){
            status1 = "Ditolak";
        }
        else {
            status1 = "Ditolak Sistem";
        }

        holder.cart_status.setText(status1);
    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setClickListener(AdapterCardPemenangPanitia.ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }


    public class ViewHolderPemenangPanitia extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView cart_nama,cart_detail,cart_status;
        LinearLayout linearLayout;
        ImageView imageView;
        public ViewHolderPemenangPanitia(@NonNull View itemView) {
            super(itemView);
            cart_nama = itemView.findViewById(R.id.tv_nama_pemenang_panitia);
            linearLayout = itemView.findViewById(R.id.ln_data_pemenang_panitia);
            imageView = itemView.findViewById(R.id.iv_data_pemenang_panitia);
            cart_detail = itemView.findViewById(R.id.tv_data_pemenang_panitia);
            cart_status = itemView.findViewById(R.id.tv_status_pemenang_panitia);

            //itemView.setTag(itemView);
//            itemView.setOnClickListener(this);
            linearLayout.setOnClickListener(this);
            cart_nama.setOnClickListener(this);
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
