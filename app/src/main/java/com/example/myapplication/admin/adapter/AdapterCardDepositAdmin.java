package com.example.myapplication.admin.adapter;

import android.content.Context;
import android.graphics.Color;
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
import com.example.myapplication.model.admin.AdminDepositModel;
import com.example.myapplication.model.admin.AdminRiwayatModel;

import java.util.ArrayList;

public class AdapterCardDepositAdmin extends RecyclerView.Adapter <AdapterCardDepositAdmin.ViewHolderDepositAdmin> implements Filterable{


    private Context context;
    private static ArrayList<AdminDepositModel> itemList;
    public static ArrayList<AdminDepositModel> listDeposit;
    private ItemClickListener clickListener;


    public AdapterCardDepositAdmin(Context context, ArrayList<AdminDepositModel> itemList) {
        this.context = context;
        this.itemList = itemList;
        this.listDeposit=itemList;
    }

    @NonNull
    @Override
    public ViewHolderDepositAdmin onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_deposit_admin,parent,false);
        return new ViewHolderDepositAdmin(itemView);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolderDepositAdmin holder, int position) {
        AdminDepositModel modal = listDeposit.get(position);

        holder.cart_nama.setText(modal.getNama());
        holder.cart_tgl_deposit.setText(modal.getTgl_deposit());
        holder.cart_deposit.setText(modal.getNominal_deposit());





//        holder.cart_keterangan.setText(modal.getKeterangan());
//        holder.cart_status.setText(modal.getBukti_pembayaran());

        String status,status1;

        status = modal.getStatus_deposit();

        int conditionColor = 0;
        if (status.equals("1")) {
            status1 = "Terverifikasi";
            conditionColor = Color.parseColor("#005a5c");
        } else if (status.equals("0")){
            status1 = "Belum Diverifikasi";
            conditionColor = Color.parseColor("#F74000");
        } else if (status.equals("2")){
            status1 = "Ditolak";
            conditionColor = Color.parseColor("#000000");

        } else {
            status1 = "-";
        }
        holder.cart_status_deposit.setText(status1);



        holder.cart_status_deposit.setTextColor(conditionColor);
    }

    @Override
    public int getItemCount() {
        return listDeposit.size();
    }

    // Filter
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String searchString = charSequence.toString().toLowerCase().trim();

                if (searchString.isEmpty()) {
                    listDeposit= itemList;
                } else {
                    ArrayList<AdminDepositModel> tempFilteredList = new ArrayList<>();
                    for (AdminDepositModel depositModel : itemList) {
                        if (depositModel.getNama().toLowerCase().contains(searchString)) {
                            tempFilteredList.add(depositModel);
                        }
                    }
                    listDeposit = tempFilteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listDeposit;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listDeposit = (ArrayList<AdminDepositModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    public void setClickListener(AdapterCardDepositAdmin.ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }


    public class ViewHolderDepositAdmin extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView cart_nama, cart_tgl_deposit, cart_deposit, cart_keterangan, cart_status_deposit;
        private LinearLayout cart_buktitransfer;


        public ViewHolderDepositAdmin(@NonNull View itemView) {
            super(itemView);
            cart_nama = itemView.findViewById(R.id.tv_nama);
            cart_tgl_deposit = itemView.findViewById(R.id.tv_tgl_deposit);
            cart_deposit = itemView.findViewById(R.id.tv_deposit);
//            cart_keterangan = itemView.findViewById(R.id.tv_keterangan);
            cart_status_deposit = itemView.findViewById(R.id.tv_status_deposit);


            cart_buktitransfer = itemView.findViewById(R.id.ln_detail_deposit_admin);
            cart_buktitransfer.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }

}





