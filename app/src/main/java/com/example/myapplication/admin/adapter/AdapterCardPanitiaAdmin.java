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
import com.example.myapplication.model.admin.AdminPanitiaModel;

import java.util.ArrayList;






public class AdapterCardPanitiaAdmin extends RecyclerView.Adapter <AdapterCardPanitiaAdmin.ViewHolderPanitiaAdmin> implements Filterable {


        private Context context;
    private static ArrayList<AdminPanitiaModel> itemList;
    private ItemClickListener clickListener;
    public static ArrayList<AdminPanitiaModel> listPanitia;

    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    public AdapterCardPanitiaAdmin(Context context, ArrayList<AdminPanitiaModel> itemList) {
        this.context = context;
        this.itemList = itemList;
        this.listPanitia=itemList;
    }
    @NonNull
    @Override
    public ViewHolderPanitiaAdmin onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_panitia_admin,parent,false);
        return new ViewHolderPanitiaAdmin(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolderPanitiaAdmin holder, int position) {

        AdminPanitiaModel modal = listPanitia.get(position);
        holder.cart_panitia.setText(modal.getNama());
    }


    @Override
    public int getItemCount() {
        return listPanitia.size();
    }


    // Filter
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String searchString = charSequence.toString().toLowerCase().trim();

                if (searchString.isEmpty()) {
                    listPanitia = itemList;
                } else {
                    ArrayList<AdminPanitiaModel> tempFilteredList = new ArrayList<>();
                    for (AdminPanitiaModel adminpanitiamodel : itemList) {
                        if (adminpanitiamodel.getNama().toLowerCase().contains(searchString)) {
                            tempFilteredList.add(adminpanitiamodel);
                        }
                    }
                    listPanitia = tempFilteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listPanitia;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listPanitia = (ArrayList<AdminPanitiaModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public void setClickListener(AdapterCardPanitiaAdmin.ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }


    public class ViewHolderPanitiaAdmin extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView cart_panitia,cart_detail;
        LinearLayout linearLayout;
        ImageView imageView;
        public ViewHolderPanitiaAdmin(@NonNull View itemView) {
            super(itemView);
            cart_panitia = itemView.findViewById(R.id.tv_nama_panitia_admin);
            linearLayout = itemView.findViewById(R.id.ln_detail_panitia_admin);
            cart_detail = itemView.findViewById(R.id.tv_detail_panitia_admin);
            imageView = itemView.findViewById(R.id.iv_detail_panitia_admin);

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

