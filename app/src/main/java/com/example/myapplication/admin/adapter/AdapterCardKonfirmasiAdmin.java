package com.example.myapplication.admin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.admin.AdminKonfirmasiModel;

import java.util.ArrayList;

public class AdapterCardKonfirmasiAdmin extends RecyclerView.Adapter <AdapterCardKonfirmasiAdmin.ViewHolderKonfirmasiAdmin> implements Filterable {

    private Context context;
    private static ArrayList<AdminKonfirmasiModel> itemList;
    private ItemClickListener clickListener;
    public static ArrayList<AdminKonfirmasiModel> listKonfirmasi;



    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    public AdapterCardKonfirmasiAdmin(Context context, ArrayList<AdminKonfirmasiModel> itemList) {
        this.context = context;
        this.itemList = itemList;
        this.listKonfirmasi=itemList;
    }

    @Override
    public ViewHolderKonfirmasiAdmin onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_konfirmasi_admin,parent,false);
        return new ViewHolderKonfirmasiAdmin(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolderKonfirmasiAdmin holder, int position) {
        AdminKonfirmasiModel modal = listKonfirmasi.get(position);
        holder.cart_peserta.setText(modal.getNama());
    }

    @Override
    public int getItemCount() {
        return listKonfirmasi.size();
    }


    // Filter
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String searchString = charSequence.toString().toLowerCase().trim();

                if (searchString.isEmpty()) {
                    listKonfirmasi = itemList;
                } else {
                    ArrayList<AdminKonfirmasiModel> tempFilteredList = new ArrayList<>();
                    for (AdminKonfirmasiModel adminkonfirmasimodel : itemList) {
                        if (adminkonfirmasimodel.getNama().toLowerCase().contains(searchString)) {
                            tempFilteredList.add(adminkonfirmasimodel);
                        }
                    }
                    listKonfirmasi = tempFilteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listKonfirmasi;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listKonfirmasi = (ArrayList<AdminKonfirmasiModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public void setClickListener(AdapterCardKonfirmasiAdmin.ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class ViewHolderKonfirmasiAdmin extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView cart_peserta;
        LinearLayout linearLayout;

        public ViewHolderKonfirmasiAdmin(View itemView) {
            super(itemView);
            cart_peserta = itemView.findViewById(R.id.tv_nama_konfirmasi_admin);
            linearLayout = itemView.findViewById(R.id.ln_detail_konfirmasi_admin);

            linearLayout.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view,
                    getAdapterPosition());
        }
    }
}
