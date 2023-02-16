package com.example.myapplication.panitia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.panitia.PanitiaRiwayatTransferModel;
import com.example.myapplication.model.panitia.PanitiaTransferModel;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdapterCardRiwayatTransferPanitia extends RecyclerView.Adapter <AdapterCardRiwayatTransferPanitia.ViewHolderRiwayatPanitia>{
    private Context context;
    private static ArrayList<PanitiaRiwayatTransferModel> itemList;
//    private ItemClickListener clickListener;
//
//    public interface ItemClickListener {
//        void onClick(View view, int position);
//    }

    public AdapterCardRiwayatTransferPanitia(Context context, ArrayList<PanitiaRiwayatTransferModel> itemList) {
        this.context = context;
        this.itemList = itemList;
    }
    @NonNull
    @Override
    public ViewHolderRiwayatPanitia onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_selesai_penarikan_panitia,parent,false);
        return new ViewHolderRiwayatPanitia(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolderRiwayatPanitia holder, int position) {

        PanitiaRiwayatTransferModel modal = itemList.get(position);
        holder.cart_nama.setText(modal.getNama());

        holder.cart_bukti.setText(modal.getBukti_transfer());

        DecimalFormat decimalFormat = new DecimalFormat("#,##0");

        holder.cart_nominal.setText("Rp. " + decimalFormat.format(Integer.parseInt(modal.getNominal_dibayarkan())));



    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }

//    public void setClickListener(AdapterCardRiwayatTransferPanitia.ItemClickListener clickListener) {
//        this.clickListener = clickListener;
//    }


    public class ViewHolderRiwayatPanitia extends RecyclerView.ViewHolder {
        TextView cart_nama,cart_bukti,cart_nominal;
        public ViewHolderRiwayatPanitia(@NonNull View itemView) {
            super(itemView);
            cart_nama = itemView.findViewById(R.id.tv_nama_riwayat_transfer_panitia);
            cart_bukti = itemView.findViewById(R.id.tv_bukti_riwayat_transfer_panitia);
            cart_nominal = itemView.findViewById(R.id.tv_nominal_riwayat_transfer_panitia);

        }
//        @Override
//        public void onClick(View view) {
//            if (clickListener != null) clickListener.onClick(view,
//                    getAdapterPosition());
//        }
    }
}
