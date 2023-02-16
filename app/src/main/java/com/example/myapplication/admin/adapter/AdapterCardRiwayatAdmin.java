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

import com.example.myapplication.R;
import com.example.myapplication.model.admin.AdminRiwayatModel;

import java.util.ArrayList;


public class AdapterCardRiwayatAdmin extends RecyclerView.Adapter <AdapterCardRiwayatAdmin.ViewHolderRiwayatAdmin> implements Filterable {


    private Context context;
    private static ArrayList<AdminRiwayatModel> itemList;
    private ArrayList<AdminRiwayatModel> listRiwayat;

    public AdapterCardRiwayatAdmin(Context context, ArrayList<AdminRiwayatModel> itemList) {
        this.context = context;
        this.itemList = itemList;
        this.listRiwayat=itemList;
    }

    @NonNull
    @Override
    public ViewHolderRiwayatAdmin onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_riwayat_admin,parent,false);
        return new ViewHolderRiwayatAdmin(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderRiwayatAdmin holder, int position) {
        AdminRiwayatModel modal = listRiwayat.get(position);
 
        holder.cart_peserta_riwayat.setText(modal.getNama_peserta());
        holder.cart_ikan_produk.setText(modal.getProduk());
        holder.cart_tanggal_diumumkan.setText(modal.getTgl_diumumkan());

//        String inputPattern = "yyyy-MM-dd HH:mm:ss";
//        String outputPattern = "dd/MM/yyyy";
//        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
//        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
//
//        Date date = null;
//        String result = null;
//
//        try {
//            date = inputFormat.parse(modal.getTgl_pembayaran());
//            result = outputFormat.format(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        holder.cart_tanggal_pembayaran.setText(result);



        holder.cart_nominal_bayar.setText(String.valueOf(modal.getNominal_dibayarkan()));
//        holder.cart_status.setText(modal.getBukti_pembayaran());

        String status,status1;

        status = modal.getStatus();

        if (status.equals("1")) {
            status1 = "Selesai";

//        } else if (status.equals("0")){
//            status1 = "Belum Diverivikasi";
//
//        } else if (status.equals("2")){
//            status1 = "Ditolak";
//
//        } else if (status.equals("3")){
//            status1 = "Dibatalkan Sistem";

        } else {
            status1 = "-";
        }
        holder.cart_status_lelang_admin.setText(status1);
    }

    @Override
    public int getItemCount() {
        return listRiwayat.size();
    }

    // Filter
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String searchString = charSequence.toString().toLowerCase().trim();

                if (searchString.isEmpty()) {
                    listRiwayat = itemList;
                } else {
                    ArrayList<AdminRiwayatModel> tempFilteredList = new ArrayList<>();
                    for (AdminRiwayatModel riwayatModel : itemList) {
                        if (riwayatModel.getNama_peserta().toLowerCase().contains(searchString)) {
                            tempFilteredList.add(riwayatModel);
                        }
                    }
                    listRiwayat = tempFilteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listRiwayat;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listRiwayat = (ArrayList<AdminRiwayatModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolderRiwayatAdmin extends RecyclerView.ViewHolder {
        TextView cart_peserta_riwayat, cart_ikan_produk, cart_nominal_bayar, cart_tanggal_diumumkan, cart_status_lelang_admin;
        public ViewHolderRiwayatAdmin(@NonNull View itemView) {
            super(itemView);
            cart_peserta_riwayat = itemView.findViewById(R.id.tv_peserta_riwayat);
            cart_ikan_produk = itemView.findViewById(R.id.tv_ikan_produk);
            cart_nominal_bayar = itemView.findViewById(R.id.tv_nominal_bayar);
            cart_tanggal_diumumkan = itemView.findViewById(R.id.tv_tanggal_diumumkan);
            cart_status_lelang_admin = itemView.findViewById(R.id.tv_status_lelang_admin);
        }
    }

}




