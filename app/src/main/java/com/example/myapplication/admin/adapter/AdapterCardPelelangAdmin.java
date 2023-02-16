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
import com.example.myapplication.model.admin.AdminPelelangModel;

import java.util.ArrayList;
import java.util.Locale;

public class AdapterCardPelelangAdmin extends RecyclerView.Adapter <AdapterCardPelelangAdmin.ViewHolderPelelangAdmin> implements Filterable {
    private Context context;
    private static ArrayList<AdminPelelangModel> itemList;
    private ItemClickListener clickListener;
    public static ArrayList<AdminPelelangModel> listPelelang;





    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    public AdapterCardPelelangAdmin(Context context, ArrayList<AdminPelelangModel> itemList) {
        this.context = context;
        this.itemList = itemList;
        this.listPelelang=itemList;
    }
    @NonNull
    @Override
    public ViewHolderPelelangAdmin onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_pelelang_admin,parent,false);
        return new ViewHolderPelelangAdmin(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolderPelelangAdmin holder, int position) {

        AdminPelelangModel modal = listPelelang.get(position);
        holder.cart_pelelang.setText(modal.getNama());
    }


    @Override
    public int getItemCount() {
        return listPelelang.size();
    }


    // Filter
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String searchString = charSequence.toString().toLowerCase().trim();

                if (searchString.isEmpty()) {
                    listPelelang = itemList;
                } else {
                    ArrayList<AdminPelelangModel> tempFilteredList = new ArrayList<>();
                    for (AdminPelelangModel adminpelelangmodel : itemList) {
                        if (adminpelelangmodel.getNama().toLowerCase().contains(searchString)) {
                            tempFilteredList.add(adminpelelangmodel);
                        }
                    }
                    listPelelang = tempFilteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listPelelang;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listPelelang = (ArrayList<AdminPelelangModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public void setClickListener(AdapterCardPelelangAdmin.ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }







    public class ViewHolderPelelangAdmin extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView cart_pelelang,cart_detail;
        LinearLayout linearLayout;
        ImageView imageView;
        public ViewHolderPelelangAdmin(@NonNull View itemView) {
            super(itemView);
            cart_pelelang = itemView.findViewById(R.id.tv_nama_pelelang_admin);
            linearLayout = itemView.findViewById(R.id.ln_detail_pelelang_admin);
            cart_detail = itemView.findViewById(R.id.tv_detail_pelelang_admin);
            imageView = itemView.findViewById(R.id.iv_detail_pelelang_admin);

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
