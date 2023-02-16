package com.example.myapplication.admin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.admin.AdminPesertaModel;

import java.util.ArrayList;

public class AdapterCardPesertaAdmin extends RecyclerView.Adapter <AdapterCardPesertaAdmin.ViewHolderPesertaAdmin> implements Filterable {

    private Context context;
    private ArrayList<AdminPesertaModel> itemList;
    private ItemClickListener clickListener;
    public static ArrayList<AdminPesertaModel> listPeserta;

    public AdapterCardPesertaAdmin(Context context, ArrayList<AdminPesertaModel> itemList) {
        this.context = context;
        this.itemList = itemList;
        this.listPeserta=itemList;
    }

    @Override
    public ViewHolderPesertaAdmin onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_peserta_admin,parent,false);
        return new ViewHolderPesertaAdmin(itemView);
    }
    @Override
    public void onBindViewHolder(ViewHolderPesertaAdmin holder, int position) {
        AdminPesertaModel modal = listPeserta.get(position);
        holder.cart_peserta.setText(modal.getNama());
    }

    @Override
    public int getItemCount() {
        return listPeserta.size();
    }

    // Filter
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String searchString = charSequence.toString().toLowerCase().trim();

                if (searchString.isEmpty()) {
                    listPeserta = itemList;
                } else {
                    ArrayList<AdminPesertaModel> tempFilteredList = new ArrayList<>();
                    for (AdminPesertaModel adminpesertamodel : itemList) {
                        if (adminpesertamodel.getNama().toLowerCase().contains(searchString)) {
                            tempFilteredList.add(adminpesertamodel);
                        }
                    }
                    listPeserta = tempFilteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listPeserta;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listPeserta = (ArrayList<AdminPesertaModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    public void setClickListener(AdapterCardPesertaAdmin.ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class ViewHolderPesertaAdmin extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView cart_peserta,cart_detail;
        LinearLayout linearLayout;
        ImageView imageView;

        public ViewHolderPesertaAdmin(View itemView) {
            super(itemView);
            cart_peserta = itemView.findViewById(R.id.tv_nama_peserta_admin);
            linearLayout = itemView.findViewById(R.id.ln_detail_peserta_admin);
            imageView = itemView.findViewById(R.id.iv_peserta_admin);
            cart_detail = itemView.findViewById(R.id.tv_detail_peserta_admin);

            linearLayout.setOnClickListener(this);
            cart_peserta.setOnClickListener(this);
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
