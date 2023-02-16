package com.example.myapplication.admin.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;

import com.example.myapplication.R;
import com.example.myapplication.admin.activity.AdminPenawaranLelangActivity;
import com.example.myapplication.model.admin.AdminPenawaranModel;


import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class AdapterPenawaranLelang extends RecyclerView.Adapter <AdapterPenawaranLelang.ViewHolderPenawaranLelang> implements  Filterable{

    private Context context;
    private static ArrayList<AdminPenawaranModel> itemList;
    private ArrayList<AdminPenawaranModel> listPenawaran;
    private ItemClickListener clickListener;

    public AdapterPenawaranLelang(Context context, ArrayList<AdminPenawaranModel> penawaranModelArrayList) {
        this.context = context;
        this.itemList = penawaranModelArrayList;
        this.listPenawaran=itemList;

    }


    @NonNull
    @Override
    public AdapterPenawaranLelang.ViewHolderPenawaranLelang onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_penawaran_admin,parent,false);
        return new AdapterPenawaranLelang.ViewHolderPenawaranLelang(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPenawaranLelang.ViewHolderPenawaranLelang holder, int position) {
        AdminPenawaranModel modal = listPenawaran.get(position);
//        holder.cart_lelang_id.setText(modal.getLelang_id());
        holder.cart_nama.setText(modal.getNama());
//        holder.cart_harga_tawar.setText(modal.getHarga_tawar());
        holder.cart_tgl_bid.setText(modal.getTgl_bid());
//        holder.cart_produk.setText(modal.getProduk());


        DecimalFormat decimalFormat = new DecimalFormat("#,###");

        holder.cart_harga_tawar.setText(decimalFormat.format(Integer.parseInt(modal.getHarga_tawar())));

    }

    @Override
    public int getItemCount() {
        return listPenawaran.size();
    }

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }



    // Filter
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String searchString = charSequence.toString().toLowerCase().trim();

                if (searchString.isEmpty()) {
                    listPenawaran = itemList;
                } else {
                    ArrayList<AdminPenawaranModel> tempFilteredList = new ArrayList<>();
                    for (AdminPenawaranModel adminpenawaranmodel : itemList) {
                        if (adminpenawaranmodel.getNama().toLowerCase().contains(searchString)) {
                            tempFilteredList.add(adminpenawaranmodel);
                        }
                    }
                    listPenawaran = tempFilteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listPenawaran;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listPenawaran = (ArrayList<AdminPenawaranModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    public class ViewHolderPenawaranLelang extends RecyclerView.ViewHolder {
        TextView cart_lelang_id, cart_harga_tawar, cart_nama, cart_tgl_bid, cart_produk;
        public ViewHolderPenawaranLelang(@NonNull View itemView) {
            super(itemView);
//            cart_lelang_id = itemView.findViewById(R.id.tv_lelangid);
            cart_harga_tawar = itemView.findViewById(R.id.tv_harga_tawar);
            cart_nama = itemView.findViewById(R.id.tv_nama);
            cart_tgl_bid = itemView.findViewById(R.id.tv_tgl_bid);
//            cart_produk = itemView.findViewById(R.id.tv_produk);

        }
    }
}
