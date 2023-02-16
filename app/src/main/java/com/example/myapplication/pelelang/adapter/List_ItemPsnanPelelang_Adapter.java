package com.example.myapplication.pelelang.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.pelelang.ListDataTransaksi;
import com.example.myapplication.pelelang.hostui.PdfActivity;
import com.example.myapplication.pelelang.hostui.transaksidetail.DetailTransaksi;
import com.example.myapplication.pelelang.hostui.transaksidetail.ProsesTransaksiMainActivity;

import java.util.ArrayList;

public class List_ItemPsnanPelelang_Adapter extends RecyclerView.Adapter<List_ItemPsnanPelelang_Adapter.listViewHolder> {

    private ArrayList<ListDataTransaksi> listItemPsananPelelang;

    public List_ItemPsnanPelelang_Adapter(ArrayList<ListDataTransaksi> listItemPsananPelelang) {
        this.listItemPsananPelelang = listItemPsananPelelang;
    }

    @NonNull
    @Override
    public listViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflateList = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_transaksi, parent, false);
        return new listViewHolder(inflateList);
    }

    @Override
    public void onBindViewHolder(@NonNull final listViewHolder holder, int position) {
        ListDataTransaksi item = listItemPsananPelelang.get(position);
        holder.noInvoice.setText(item.getLelangId());
        holder.nmPemesan.setText(item.getNama());
        holder.subtotal.setText(item.getNominalDibayarkan());

        holder.crdVw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailTransaksi.class);
                intent.putExtra("id", item.getLelangId());
                view.getContext().startActivity(intent);
            }
        });

        holder.btnProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), ProsesTransaksiMainActivity.class);
                i.putExtra("id", item.getLelangId());
                i.putExtra("nama_pelelang", item.getNama());
                view.getContext().startActivity(i);
            }
        });

        holder.btnUnduh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), PdfActivity.class);
                i.putExtra("id", item.getLelangId());
                view.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItemPsananPelelang.size();
    }

    static class listViewHolder extends RecyclerView.ViewHolder {
        TextView noInvoice, nmPemesan, subtotal;
        CardView crdVw;
        Button btnProses;
        Button btnUnduh;

        listViewHolder(View itemView) {
            super(itemView);
            noInvoice = itemView.findViewById(R.id.noInvoice);
            nmPemesan = itemView.findViewById(R.id.nmPemesan);
            subtotal = itemView.findViewById(R.id.subtotal);
            crdVw = itemView.findViewById(R.id.cvDetailTransaksi);
            btnProses = itemView.findViewById(R.id.btnProsesTransaksi);
            btnUnduh = itemView.findViewById(R.id.btnUnduh);
        }
    }
}
