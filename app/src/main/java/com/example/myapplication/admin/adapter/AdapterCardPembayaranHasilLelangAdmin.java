package com.example.myapplication.admin.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.admin.AdminPembayaranHasilLelangModel;
import com.example.myapplication.model.admin.PemenangModel;


import java.util.ArrayList;

public class AdapterCardPembayaranHasilLelangAdmin extends RecyclerView.Adapter <AdapterCardPembayaranHasilLelangAdmin.ViewHolderPembayaranHasilLelangAdmin> implements Filterable {

    private Context context;
    private static ArrayList<AdminPembayaranHasilLelangModel> itemList;
    public static ArrayList<AdminPembayaranHasilLelangModel> listPembayaranPanitia;
    private ItemClickListener clickListener;

    public AdapterCardPembayaranHasilLelangAdmin(Context context, ArrayList<AdminPembayaranHasilLelangModel> itemList) {
        this.context = context;
        this.itemList = itemList;
        this.listPembayaranPanitia=itemList;
    }


    @NonNull
    @Override
    public AdapterCardPembayaranHasilLelangAdmin.ViewHolderPembayaranHasilLelangAdmin onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_pembayaranhasillelang_admin,parent,false);
        return new AdapterCardPembayaranHasilLelangAdmin.ViewHolderPembayaranHasilLelangAdmin(itemView);
    }




    @Override
    public void onBindViewHolder(@NonNull AdapterCardPembayaranHasilLelangAdmin.ViewHolderPembayaranHasilLelangAdmin holder, int position) {
        AdminPembayaranHasilLelangModel modal = itemList.get(position);


        holder.cart_lelang_id.setText(modal.getLelang_id());
        holder.cart_nama.setText(modal.getNama());
        holder.cart_nama_panitia.setText(modal.getNama_panitia());

        holder.cart_nominal_dibayarkan.setText(String.valueOf(modal.getNominal_dibayarkan()));
    }

    @Override
    public int getItemCount() {
        return listPembayaranPanitia.size();
    }

    // Filter
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String searchString = charSequence.toString().toLowerCase().trim();

                if (searchString.isEmpty()) {
                    listPembayaranPanitia = itemList;
                } else {
                    ArrayList<AdminPembayaranHasilLelangModel> tempFilteredList = new ArrayList<>();
                    for (AdminPembayaranHasilLelangModel adminpembayaranhasillelangmodel : itemList) {
                        if (adminpembayaranhasillelangmodel.getNama().toLowerCase().contains(searchString)) {
                            tempFilteredList.add(adminpembayaranhasillelangmodel);
                        }
                    }
                    listPembayaranPanitia = tempFilteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listPembayaranPanitia;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listPembayaranPanitia = (ArrayList<AdminPembayaranHasilLelangModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class ViewHolderPembayaranHasilLelangAdmin extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView cart_lelang_id, cart_nominal_dibayarkan, cart_nama, cart_nama_panitia;
        private LinearLayout cart_buktitransfer;

        public ViewHolderPembayaranHasilLelangAdmin(View itemView) {
            super(itemView);
            cart_lelang_id = itemView.findViewById(R.id.tv_lelangid);
            cart_nominal_dibayarkan = itemView.findViewById(R.id.tv_nominal_dibayarkan);
            cart_nama = itemView.findViewById(R.id.tv_nama);
            cart_nama_panitia = itemView.findViewById(R.id.tv_nama_panitia);
            cart_buktitransfer = itemView.findViewById(R.id.ln_detail_konfirmasi_admin);

            cart_buktitransfer.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }
}
