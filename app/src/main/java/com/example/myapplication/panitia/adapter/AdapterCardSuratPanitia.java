package com.example.myapplication.panitia.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.model.panitia.PanitiaSuratPerintahModel;
import com.example.myapplication.model.panitia.ProdukPanitiaModel;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdapterCardSuratPanitia extends RecyclerView.Adapter<AdapterCardSuratPanitia.ViewHolderSuratPanitia> {

    private static ArrayList<PanitiaSuratPerintahModel> itemList;
    private Context context;
    private ItemClickListener clickListener;

    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    public AdapterCardSuratPanitia(Context context, ArrayList<PanitiaSuratPerintahModel> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ViewHolderSuratPanitia onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_surat_perintah_panitia, parent, false);
        return new ViewHolderSuratPanitia(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderSuratPanitia holder, int position) {
        PanitiaSuratPerintahModel modal = itemList.get(position);
        holder.cart_nama.setText(modal.getNama_pelelang());

        String status,status1;

        status1 = modal.getStatus_pengiriman();

        if (status1.equals("0")){
            status = "Belum di Proses";
        } else if (status1.equals("1")){
            status = "Dalam Pengiriman";
        } else if (status1.equals("2")){
            status = "Telah Sampai";
        } else {
            status = "-";
        }

        holder.cart_status.setText(status);


    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setClickListener(AdapterCardSuratPanitia.ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class ViewHolderSuratPanitia extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView cart_nama,cart_status;
        ImageButton btn_info,btn_delete;



        public ViewHolderSuratPanitia(@NonNull View itemView) {
            super(itemView);

            cart_nama = itemView.findViewById(R.id.tv_nama_surat_perintah_panitia);
            cart_status = itemView.findViewById(R.id.tv_status_surat_perintah_panitia);

            btn_info = itemView.findViewById(R.id.btn_detail_surat_perintah_panitia);
            btn_delete = itemView.findViewById(R.id.btn_delete_surat_perintah_panitia);

            btn_info.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) clickListener.onClick(v,
                    getAdapterPosition());
        }
    }
}
