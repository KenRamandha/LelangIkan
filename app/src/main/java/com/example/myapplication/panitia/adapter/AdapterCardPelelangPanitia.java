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
import com.example.myapplication.model.admin.AdminPelelangModel;
import com.example.myapplication.model.panitia.PanitiaPelelangModel;

import java.util.ArrayList;

public class AdapterCardPelelangPanitia extends RecyclerView.Adapter <AdapterCardPelelangPanitia.ViewHolderPelelangPanitia>{
    private Context context;
    private static ArrayList<PanitiaPelelangModel> itemList;
    private ItemClickListener clickListener;

    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    public AdapterCardPelelangPanitia(Context context, ArrayList<PanitiaPelelangModel> itemList) {
        this.context = context;
        this.itemList = itemList;
    }
    @NonNull
    @Override
    public ViewHolderPelelangPanitia onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_status_pelelang_panitia,parent,false);
        return new ViewHolderPelelangPanitia(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolderPelelangPanitia holder, int position) {

        PanitiaPelelangModel modal = itemList.get(position);
        holder.cart_pelelang.setText(modal.getNama());

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

    public void setClickListener(AdapterCardPelelangPanitia.ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }


    public class ViewHolderPelelangPanitia extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView cart_pelelang,cart_detail,cart_status;
        LinearLayout linearLayout;
        ImageView imageView;
        public ViewHolderPelelangPanitia(@NonNull View itemView) {
            super(itemView);
            cart_pelelang = itemView.findViewById(R.id.tv_pelelang_panitia);
            linearLayout = itemView.findViewById(R.id.ln_data_pelelang_panitia);
            cart_detail = itemView.findViewById(R.id.tv_data_pelelang_panitia);
            imageView = itemView.findViewById(R.id.iv_data_pelelang_panitia);
            cart_status = itemView.findViewById(R.id.tv_status_pelelang_panitia);

//            itemView.setTag(itemView);
//            itemView.setOnClickListener(this);
            linearLayout.setOnClickListener(this);
            cart_detail.setOnClickListener(this);
            imageView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view,
                    getAdapterPosition());
        }
    }
}
