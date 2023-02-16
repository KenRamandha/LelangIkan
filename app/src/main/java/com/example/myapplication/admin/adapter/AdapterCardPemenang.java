package com.example.myapplication.admin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.admin.PemenangModel;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdapterCardPemenang extends RecyclerView.Adapter <AdapterCardPemenang.ViewHolderPemenang> implements Filterable {
    private Context context;
    private static ArrayList<PemenangModel> itemList;
    private ArrayList<PemenangModel> listPemenang;

    public AdapterCardPemenang(Context context, ArrayList<PemenangModel> itemList) {
        this.context = context;
        this.itemList = itemList;
        this.listPemenang=itemList;
    }
    @NonNull
    @Override
    public ViewHolderPemenang onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_pemenang_admin,parent,false);
        return new ViewHolderPemenang(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolderPemenang holder, int position) {

        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        PemenangModel modal = listPemenang.get(position);
        holder.cart_peserta_menang.setText(modal.getNama());
        holder.cart_ikan_menang.setText(modal.getProduk());

        if (modal.getTgl_diumumkan() !=null){
            String inputPattern = "yyyy-MM-dd HH:mm:ss";
            String outputPattern = "dd/MM/yyyy";
            SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
            SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

            Date date = null;
            String result = null;

            try {
                date = inputFormat.parse(modal.getTgl_diumumkan());
                result = outputFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            holder.cart_tanggal_menang.setText(result);
        }


        holder.cart_totalbayar_menang.setText("Rp. " +modal.getTotal_bayar());

        String status,status1;

        status = modal.getStatus();

        if (status.equals("1")) {
            status1 = "Terbayar";
            holder.ivSilang.setVisibility(View.GONE);
        } else if (status.equals("0")){
            status1 = "Belum Diverivikasi";
            holder.ivCentang.setVisibility(View.GONE);
        } else if (status.equals("2")){
            status1 = "Ditolak";
            holder.ivCentang.setVisibility(View.GONE);
        } else if (status.equals("3")){
            status1 = "Dibatalkan Sistem";
            holder.ivCentang.setVisibility(View.GONE);
        } else {
            status1 = "-";
            holder.ivCentang.setVisibility(View.GONE);
            holder.ivSilang.setVisibility(View.GONE);
        }
        holder.cart_status.setText(status1);


        //        if (status.equals("1")) {
        //            status1 = "Sudah Diverivikasi";
        //            holder.ivSilang.setVisibility(View.GONE);
        //        } else if (status.equals("0")){
        //            status1 = "Belum Diverivikasi";
        //            holder.ivCentang.setVisibility(View.GONE);
        //        } else {
        //            status1 = "-";
        //            holder.ivCentang.setVisibility(View.GONE);
        //            holder.ivSilang.setVisibility(View.GONE);
        //        }
        //        holder.cart_status.setText(status1);
    }


    @Override
    public int getItemCount() {
        return listPemenang.size();
    }

    // Filter
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String searchString = charSequence.toString().toLowerCase().trim();

                if (searchString.isEmpty()) {
                    listPemenang = itemList;
                } else {
                    ArrayList<PemenangModel> tempFilteredList = new ArrayList<>();
                    for (PemenangModel pemenangModel : itemList) {
                        if (pemenangModel.getNama().toLowerCase().contains(searchString)) {
                            tempFilteredList.add(pemenangModel);
                        }
                    }
                    listPemenang = tempFilteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listPemenang;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listPemenang = (ArrayList<PemenangModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    public class ViewHolderPemenang extends RecyclerView.ViewHolder {
        TextView cart_peserta_menang, cart_ikan_menang, cart_totalbayar_menang, cart_tanggal_menang,cart_status;
        ImageView ivCentang,ivSilang;
        public ViewHolderPemenang(@NonNull View itemView) {
            super(itemView);
            cart_peserta_menang = itemView.findViewById(R.id.tv_peserta_pemenang);
            cart_ikan_menang = itemView.findViewById(R.id.tv_ikan_pemenang);
            cart_tanggal_menang = itemView.findViewById(R.id.tv_tanggal_pemenang);
            cart_totalbayar_menang = itemView.findViewById(R.id.tv_tawar_pemenang);
            cart_status = itemView.findViewById(R.id.tv_status_pemenang_admin);
            ivCentang = itemView.findViewById(R.id.iv_centang_pemenang_admin);
            ivSilang = itemView.findViewById(R.id.iv_silang_pemenang_admin);
        }
    }
}
