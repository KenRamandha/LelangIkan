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
import com.example.myapplication.model.panitia.PanitiaTransferModel;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdapterCardRequestTransferPanitia extends RecyclerView.Adapter <AdapterCardRequestTransferPanitia.ViewHolderRequestPanitia>{
    private Context context;
    private static ArrayList<PanitiaTransferModel> itemList;
    private ItemClickListener clickListener;

    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    public AdapterCardRequestTransferPanitia(Context context, ArrayList<PanitiaTransferModel> itemList) {
        this.context = context;
        this.itemList = itemList;
    }
    @NonNull
    @Override
    public ViewHolderRequestPanitia onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_request_penarikan_panitia,parent,false);
        return new ViewHolderRequestPanitia(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolderRequestPanitia holder, int position) {

        PanitiaTransferModel modal = itemList.get(position);
        if (modal.getBukti_bayar() !=null){
            holder.itemView.setVisibility(View.GONE);
        }
        holder.cart_nama.setText(modal.getNama());

        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "dd/MM/yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String result = null;

        try {
            date = inputFormat.parse(modal.getTgl_diumumkan());
            result = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.cart_tanggal.setText(result);

        DecimalFormat decimalFormat = new DecimalFormat("#,##0");

        if (modal.getTotal_bayar() !=null){
            holder.cart_nominal.setText("Rp. " + decimalFormat.format(Integer.parseInt(modal.getTotal_bayar())));
        }




    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void setClickListener(AdapterCardRequestTransferPanitia.ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }


    public class ViewHolderRequestPanitia extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView cart_nama,cart_tanggal,cart_nominal;
        LinearLayout linearLayout;
        public ViewHolderRequestPanitia(@NonNull View itemView) {
            super(itemView);
            cart_nama = itemView.findViewById(R.id.tv_nama_request_panitia);
            linearLayout = itemView.findViewById(R.id.ln_kirim_request_panitia);
            cart_tanggal = itemView.findViewById(R.id.tv_tanggal_request_panitia);
            cart_nominal = itemView.findViewById(R.id.tv_jumlah_request_panitia);

            //itemView.setTag(itemView);
//            itemView.setOnClickListener(this);
            linearLayout.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view,
                    getAdapterPosition());
        }
    }
}
