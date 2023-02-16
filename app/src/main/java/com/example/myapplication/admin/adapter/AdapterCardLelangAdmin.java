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

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.model.admin.AdminKonfirmasiModel;
import com.example.myapplication.model.admin.KatalogLelangAdminModel;
import com.example.myapplication.model.peserta.KatalogModel;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class AdapterCardLelangAdmin extends RecyclerView.Adapter <AdapterCardLelangAdmin.ViewHolderLelangAdmin> implements Filterable {

    private static ArrayList<KatalogLelangAdminModel> itemList;
    public static ArrayList<KatalogLelangAdminModel> listProduk;
    private Context context;
    private ItemClickListener clickListener;


//    @Override
//    public Filter getFilter() {
//        return null;
//    }

    public interface ItemClickListener {
        void onClick(View view, int position);
    }

    public AdapterCardLelangAdmin(Context context, ArrayList<KatalogLelangAdminModel> itemList) {
        this.context = context;
        this.itemList = itemList;
        this.listProduk=itemList;
    }

    @NonNull
    @Override
    public ViewHolderLelangAdmin onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_pembukaan_lelang_admin,parent,false);
        return new ViewHolderLelangAdmin(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderLelangAdmin holder, int position) {
        KatalogLelangAdminModel modal = listProduk.get(position);



        holder.cart_produk.setText(modal.getProduk());
        holder.cart_pelelang.setText(modal.getNamaPelelang());
//        holder.status_pemeriksaan.setText(modal.getStatus());
        String inputPattern = "yyyy-MM-dd HH:mm:ss";
        String outputPattern = "dd/MM/yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String result = null;

        try {
            date = inputFormat.parse(modal.getTgl_mulai());
            result = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        holder.cart_harga_awal.setText(decimalFormat.format(Integer.parseInt(modal.getHarga_awal())));
//        holder.cart_harga_awal.setText("Rp. " + (modal.getHarga_awal()));


        holder.cart_tgl_mulai.setText(result);//modal.getTgl_mulai());





        Glide.with(holder.itemView.getContext())
                .load(modal.getImage1())
                .into(holder.cart_image1);
    }

    @Override
    public int getItemCount() {
        return listProduk.size();
    }

    // Filter
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String searchString = charSequence.toString().toLowerCase().trim();

                if (searchString.isEmpty()) {
                    listProduk = itemList;
                } else {
                    ArrayList<KatalogLelangAdminModel> tempFilteredList = new ArrayList<>();
                    for (KatalogLelangAdminModel kataloglelangadminmodel : itemList) {
                        if (kataloglelangadminmodel.getProduk().toLowerCase().contains(searchString)) {
                            tempFilteredList.add(kataloglelangadminmodel);
                        }
                    }
                    listProduk = tempFilteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listProduk;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listProduk = (ArrayList<KatalogLelangAdminModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class ViewHolderLelangAdmin extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView cart_produk, cart_pelelang, cart_tgl_mulai, cart_harga_awal,status_pemeriksaan;
        ImageView cart_image1;
        public ViewHolderLelangAdmin(@NonNull View itemView) {
            super(itemView);
            cart_produk= itemView.findViewById(R.id.tv_judul_lelang_admin);
            cart_pelelang = itemView.findViewById(R.id.tv_nelayan_lelang_admin);
            cart_tgl_mulai = itemView.findViewById(R.id.tv_waktu_lelang_admin);
            cart_harga_awal = itemView.findViewById(R.id.tv_harga_lelang_admin);
            cart_image1 = itemView.findViewById(R.id.iv_gambar_lelang_admin);
            status_pemeriksaan = itemView.findViewById(R.id.tv_status_detail_lelang_admin);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
            cart_image1.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) clickListener.onClick(view,
                    getAdapterPosition());
        }
    }
}
