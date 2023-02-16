package com.example.myapplication.panitia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.panitia.PanitiaCalonPemenangModel;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AdapterCardPenawaranPemenangPanitia extends RecyclerView.Adapter<AdapterCardPenawaranPemenangPanitia.ViewHolderPenawaranPemenangPanitia> {

    private static ArrayList<PanitiaCalonPemenangModel> itemList;
    private Context context;
    private itemClickListener clickListener;

    public AdapterCardPenawaranPemenangPanitia(Context context, ArrayList<PanitiaCalonPemenangModel> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public ViewHolderPenawaranPemenangPanitia onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_penawaran_calon_panitia, parent, false);
        return new ViewHolderPenawaranPemenangPanitia(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolderPenawaranPemenangPanitia holder, int position) {
        PanitiaCalonPemenangModel modal = itemList.get(position);
        holder.cart_peserta.setText(modal.getNama());

        if (modal.getHarga_tawar() !=null){
            DecimalFormat decimalFormat = new DecimalFormat("#,##0");
            holder.cart_harga.setText("Rp. " + decimalFormat.format(Integer.parseInt(modal.getHarga_tawar())));
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public interface itemClickListener {
        void onItemClick(PanitiaCalonPemenangModel panitiaCalonPemenangModel, View view, int position);
    }

    public void setClickListener(itemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class ViewHolderPenawaranPemenangPanitia extends RecyclerView.ViewHolder {
        private TextView cart_peserta, cart_harga;
        private ImageButton btnView;

        public ViewHolderPenawaranPemenangPanitia(View itemView) {
            super(itemView);
            cart_peserta = itemView.findViewById(R.id.tv_nama_penawaran_calon_panitia);
            cart_harga = itemView.findViewById(R.id.tv_harga_penawaran_calon_panitia);
            btnView = itemView.findViewById(R.id.iv_penawaran_calon_panitia);

            btnView.setOnClickListener(view ->{
                if (clickListener != null)
                    clickListener.onItemClick(itemList.get(getAdapterPosition()), view, getAdapterPosition());
            });
        }
    }
}
