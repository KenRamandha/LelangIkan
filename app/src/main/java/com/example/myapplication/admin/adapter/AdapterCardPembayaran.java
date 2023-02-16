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
import com.example.myapplication.model.admin.AdminPembayaranModel;
import com.example.myapplication.model.admin.PemenangModel;

import java.util.ArrayList;


public class AdapterCardPembayaran extends RecyclerView.Adapter <AdapterCardPembayaran.ViewHolderPembayaranAdmin> implements Filterable {


    private Context context;
    private static ArrayList<AdminPembayaranModel> itemList;
    public static ArrayList<AdminPembayaranModel> listPembayaran;
    private ItemClickListener clickListener;

    public AdapterCardPembayaran(Context context, ArrayList<AdminPembayaranModel> itemList) {
        this.context = context;
        this.itemList = itemList;
        this.listPembayaran=itemList;
    }

    @NonNull
    @Override
    public ViewHolderPembayaranAdmin onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_pembayaran_admin,parent,false);
        return new AdapterCardPembayaran.ViewHolderPembayaranAdmin(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCardPembayaran.ViewHolderPembayaranAdmin holder, int position) {

        AdminPembayaranModel modal = listPembayaran.get(position);


        holder.cart_peserta_pembayaran.setText(modal.getNama_peserta());
        holder.cart_ikan_pembayaran.setText(modal.getProduk());

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
        holder.cart_tanggal_pembayaran.setText(modal.getTgl_pembayaran());

        holder.cart_harga_pembayaran.setText(String.valueOf(modal.getNominal_dibayarkan()));
//        holder.cart_status.setText(modal.getBukti_pembayaran());

        String status,status1;

        status = modal.getStatus();

        if (status.equals("1")) {
            status1 = "Terbayar";

        } else if (status.equals("0")){
            status1 = "Belum Diverivikasi";

        } else if (status.equals("2")){
            status1 = "Ditolak";

        } else if (status.equals("3")){
            status1 = "Dibatalkan Sistem";

        } else {
            status1 = "-";
        }
        holder.cart_status.setText(status1);
    }

    @Override
    public int getItemCount() {
        return listPembayaran.size();
    }

    // Filter
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String searchString = charSequence.toString().toLowerCase().trim();

                if (searchString.isEmpty()) {
                    listPembayaran = itemList;
                } else {
                    ArrayList<AdminPembayaranModel> tempFilteredList = new ArrayList<>();
                    for (AdminPembayaranModel  pembayaranModel: itemList) {
                        if (pembayaranModel.getNama_peserta().toLowerCase().contains(searchString)) {
                            tempFilteredList.add(pembayaranModel);
                        }
                    }
                    listPembayaran = tempFilteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listPembayaran;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listPembayaran = (ArrayList<AdminPembayaranModel>) filterResults.values;
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

    public class ViewHolderPembayaranAdmin extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView cart_peserta_pembayaran, cart_ikan_pembayaran, cart_harga_pembayaran, cart_tanggal_pembayaran, cart_status;
        private LinearLayout cart_buktitransfer;


        public ViewHolderPembayaranAdmin(@NonNull View itemView) {
            super(itemView);
            cart_peserta_pembayaran = itemView.findViewById(R.id.tv_peserta_pembayaran);
            cart_ikan_pembayaran = itemView.findViewById(R.id.tv_ikan_pembayaran);
            cart_tanggal_pembayaran = itemView.findViewById(R.id.tv_tanggal_pembayaran);
            cart_harga_pembayaran = itemView.findViewById(R.id.tv_hagra_pembayaran);
            cart_status = itemView.findViewById(R.id.tv_status_pembayaran_admin);

            cart_buktitransfer = itemView.findViewById(R.id.ln_detail_transfer_admin);
            cart_buktitransfer.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view, getAdapterPosition());
        }
    }

}
