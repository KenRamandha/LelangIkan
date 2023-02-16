package com.example.myapplication.util;

import com.example.myapplication.model.GeneralModel;
import com.example.myapplication.model.ResponseData;
import com.example.myapplication.model.admin.AdminDepositModel;
import com.example.myapplication.model.admin.AdminKonfirmasiModel;
import com.example.myapplication.model.admin.AdminPanitiaModel;
import com.example.myapplication.model.admin.AdminPelelangModel;
import com.example.myapplication.model.admin.AdminPembayaranHasilLelangModel;
import com.example.myapplication.model.admin.AdminPembayaranModel;
import com.example.myapplication.model.admin.AdminPenawaranModel;
import com.example.myapplication.model.admin.AdminPengemasanModel;
import com.example.myapplication.model.admin.AdminPesertaModel;
import com.example.myapplication.model.admin.AdminRiwayatModel;
import com.example.myapplication.model.admin.KatalogLelangAdminModel;
import com.example.myapplication.model.panitia.PanitiaCalonPemenangModel;
import com.example.myapplication.model.panitia.PanitiaDepositPesertaModel;
import com.example.myapplication.model.panitia.PanitiaPelelangModel;
import com.example.myapplication.model.panitia.PanitiaPembayaranPemenangModel;
import com.example.myapplication.model.panitia.PanitiaPembayaranPesertaModel;
import com.example.myapplication.model.panitia.PanitiaPemenangCalonModel;
import com.example.myapplication.model.panitia.PanitiaPemenangModel;
import com.example.myapplication.model.panitia.PanitiaPengemasanModel;
import com.example.myapplication.model.panitia.PanitiaPengirimanModel;
import com.example.myapplication.model.panitia.PanitiaPesertaModel;
import com.example.myapplication.model.panitia.PanitiaPilihanPembayaranModel;
import com.example.myapplication.model.panitia.PanitiaPilihanPemenangModel;
import com.example.myapplication.model.panitia.PanitiaRiwayatBuktiTransferModel;
import com.example.myapplication.model.panitia.PanitiaRiwayatLelangModel;
import com.example.myapplication.model.panitia.PanitiaRiwayatTransferModel;
import com.example.myapplication.model.panitia.PanitiaSuratPerintahModel;
import com.example.myapplication.model.panitia.PanitiaTerkirimModel;
import com.example.myapplication.model.panitia.PanitiaTestimoniModel;
import com.example.myapplication.model.panitia.PanitiaTransferModel;
import com.example.myapplication.model.panitia.PanitiaUbahSaldoPesertaModel;
import com.example.myapplication.model.panitia.PanitiaprofileModel;
import com.example.myapplication.model.panitia.ProdukPanitiaModel;
import com.example.myapplication.model.pelelang.DeleteProdukResponse;
import com.example.myapplication.model.pelelang.DetailTransaksiResponse;
import com.example.myapplication.model.pelelang.EditStatusTransaksi;
import com.example.myapplication.model.pelelang.GetListDataPengirimanResponse;
import com.example.myapplication.model.pelelang.GetProductListResponse;
import com.example.myapplication.model.pelelang.JenisUsahaModel;
import com.example.myapplication.model.pelelang.KonfirmasiPembayaran;
import com.example.myapplication.model.pelelang.ListTransaksiResponse;
import com.example.myapplication.model.pelelang.PembayaranPanitiaModel;
import com.example.myapplication.model.pelelang.ProsesTransaksiRequest;
import com.example.myapplication.model.pelelang.ProsesTransaksiResponse;
import com.example.myapplication.model.pelelang.RiwayatLelangModel;
import com.example.myapplication.model.pelelang.RiwayatPemenangModel;
import com.example.myapplication.model.pelelang.RiwayatPenawaranModel;
import com.example.myapplication.model.pelelang.RiwayatTestimoniModel;
import com.example.myapplication.model.pelelang.UpdateNamaKendaraan;
import com.example.myapplication.model.pelelang.UpdateNamaPengirim;
import com.example.myapplication.model.pelelang.UpdateNoHpPengirim;
import com.example.myapplication.model.pelelang.UpdateNoPolisiPengirim;
import com.example.myapplication.model.pelelang.UpdateProduk;
import com.example.myapplication.model.peserta.AlamatKirimPemenangModel;
import com.example.myapplication.model.peserta.BayarSekarangBidModel;
import com.example.myapplication.model.peserta.BayarSekarangPemenangModel;
import com.example.myapplication.model.peserta.BidModel;
import com.example.myapplication.model.peserta.BuktiPembayaranPemenangModel;
import com.example.myapplication.model.peserta.DepositModel;
import com.example.myapplication.model.peserta.KatalogModel;
import com.example.myapplication.model.LoginModel;
import com.example.myapplication.model.admin.PemenangModel;
import com.example.myapplication.model.peserta.PembayaranPemenangModel;
import com.example.myapplication.model.peserta.PembayaranTransaksiPesertaModel;
import com.example.myapplication.model.peserta.PembayaranTransaksiRiwayatPesertaModel;
import com.example.myapplication.model.peserta.PemenangPesertaModel;
import com.example.myapplication.model.peserta.PenawaranModel;
import com.example.myapplication.model.peserta.PesertaModel;
import com.example.myapplication.model.admin.ProfileAdminModel;
import com.example.myapplication.model.pelelang.ProfilePelelangModel;
import com.example.myapplication.model.peserta.ProfilePesertaModel;
import com.example.myapplication.model.RegisterModel;
import com.example.myapplication.model.pelelang.TambahLelangModel;
import com.example.myapplication.model.peserta.RiwayatLelangPesertaModel;
import com.example.myapplication.model.peserta.TestimoniDetailModel;
import com.example.myapplication.model.peserta.TestimoniModel;
import com.example.myapplication.model.peserta.WaktuLelangModel;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface RetrofitAPI {
    //Login & Register
    @POST("loginPeserta")
    Call<LoginModel> userLogin(@Body LoginModel loginModel);

    @POST("loginPelelang")
    Call<LoginModel> lelangLogin(@Body LoginModel loginModel);

    @POST("loginAdmin")
    Call<LoginModel> adminLogin(@Body LoginModel loginModel);

    @POST("loginPanitia")
    Call<LoginModel> panitiaLogin(@Body LoginModel loginModel);

    //Register

    @POST("registrasiPeserta")
    Call<RegisterModel> pesertaReg(@Body RegisterModel registerModel);

    @POST("registrasiPelelang")
    Call<RegisterModel> pelelangReg(@Body RegisterModel registerModel);

    //Peserta
    @GET("produkLelang")
    Call<ArrayList<KatalogModel>> getAllProduct();

    @GET("pesertaDeposit/id/{kode}")
    Call<PesertaModel> getPeserta(@Path("kode") String path);

    @GET("detailAkunPeserta/id/{kode}")
    Call<ProfilePesertaModel> getProfile(@Path("kode") String path);

    @PUT("editPeserta")
    Call<ProfilePesertaModel> postProfile(@Body ProfilePesertaModel profilePesertaModel);

    @GET("bidLelangPeserta/id/{kode}")
    Call<ArrayList<BidModel>> getBid(@Path("kode") String lelangId);

    @GET("infoPemenangPeserta/id/{kode}")
    Call<ArrayList<PemenangPesertaModel>> getPemenangPeserta(@Path("kode") String path);

    @GET("infoTransaksiPembayaranPeserta1/id/{kode}")
    Call<ArrayList<PembayaranTransaksiPesertaModel>> getPembayaranTransaksiPeserta(@Path("kode") String path);

    @GET("infoTransaksiRiwayatPembayaranPeserta/id/{kode}")
    Call<ArrayList<PembayaranTransaksiRiwayatPesertaModel>> getPembayaranTransaksiRiwayatPeserta(@Path("kode") String path);

    @GET("infoTestimoniPeserta/id/{kode}")
    Call<ArrayList<TestimoniModel>> getTestimoniPeserta(@Path("kode") String path);

    @PUT("editTestimoniPeserta")
    Call<TestimoniModel> postTestimoni(@Body TestimoniModel testimoniModel);

    @GET("testimonilelangPeserta/id/{kode}")
    Call<ArrayList<TestimoniDetailModel>> getTestimoniDetail(@Path("kode") String lelangId);

    @GET("infoRiwayatLelangSuksesPeserta/id/{kode}")
    Call<ArrayList<RiwayatLelangPesertaModel>> getRiwayatlelangSukses(@Path("kode") String path);

    @GET("infoRiwayatLelangGagalPeserta/id/{kode}")
    Call<ArrayList<RiwayatLelangPesertaModel>> getRiwayatlelangGagal(@Path("kode") String path);

    @POST("topupDepositPeserta")
    Call<DepositModel> tambahDeposit(@Body DepositModel depositModel);

    @PUT("editWaktuLelangPeserta")
    Call<WaktuLelangModel> postWaktuLelang(@Body WaktuLelangModel waktuLelangModel);

    @POST("bayarSekarangBidPeserta")
    Call<BayarSekarangBidModel> postTambahBidBayarSekarang(@Body BayarSekarangBidModel bayarSekarangBidModel);

    @POST("bayarSekarangPemenangPeserta")
    Call<BayarSekarangPemenangModel> postTambahPememangBayarSekarang(@Body BayarSekarangPemenangModel bayarSekarangPemenangModel);

    @GET("tawarLelangPeserta/id/{kode}")
    Call<PenawaranModel> getPenawaran(@Path("kode") String lelangId);

    @POST("bidPeserta")
    Call<BidModel> postBidPeserta(@Body BidModel bidModel);

    @PUT("buktiBayarPemenang")
    Call<PembayaranPemenangModel> postbayarPemenang(@Body PembayaranPemenangModel pembayaranPemenangModel);

    @PUT("alamatPengirimanPeserta")
    Call<AlamatKirimPemenangModel> postAlamatKirim(@Body AlamatKirimPemenangModel alamatKirimPemenangModel);

    @GET("buktiBayarPeserta/id/{id}")
    Call<BuktiPembayaranPemenangModel> getBukti(@Path("id") String lelangId);

    // Pelelang
    @GET("getAllPelelangProduk/id/{id}")
    Call<GetProductListResponse> getAllPelelangProduk(@Path("id") String path);

    @GET("dataPengirimanPelelang/id/{id}")
    Call<GetListDataPengirimanResponse> getListDataPengiriman(
            @Path("id") String path
    );

    @GET("getProfilePelelangId/id/{kode}")
    Call<ProfilePelelangModel> getProfilePelelangId(@Path("kode") String path);

    @POST("getProfilePelelangId/id/{kode}")
    Call<ProfilePelelangModel> postProfilePelelang(@Body ProfilePelelangModel profilePelelangModel);

    @GET("RiwayatPemenang/id/{kode}")
    Call<ArrayList<RiwayatPemenangModel>> getRiwayatPemenang(@Path("kode") String path);

    @GET("RiwayatLelang/id/{kode}")
    Call<ArrayList<RiwayatLelangModel>>getRiwayatLelang(@Path("kode") String path);

    @GET("hasilBayar/id/{kode}")
    Call<ArrayList<PembayaranPanitiaModel>>hasilBayarPanitia(@Path("kode") String path);

    @GET("feedback/id/{kode}")
    Call<ArrayList<RiwayatTestimoniModel>>feedback(@Path("kode") String path);

    @GET("RiwayatTawar/id/{kode}")
    Call<ArrayList<RiwayatPenawaranModel>>RiwayatTawar(@Path("kode") String path);

    @GET("selectJenisUsaha")
    Call<ArrayList<JenisUsahaModel>>selectJenisUsaha();

    @Multipart
    @POST("produk")
    Call<ResponseData> pelelangAddProduk(
            @Part MultipartBody.Part image1,
            @Part MultipartBody.Part image2,
            @Part MultipartBody.Part image3,
            @Part MultipartBody.Part image4,
            @Part("produk") RequestBody produk,
            @Part("pelelang_id") RequestBody pelelang_id,
            @Part("deskripsi_produk") RequestBody deskripsi_produk,
            @Part("harga_awal") double harga_awal,
            @Part("harga_minimal_diterima") double harga_minimal_diterima,
            @Part("incremental_value") double incremental_value,
            @Part("tgl_mulai") RequestBody tgl_mulai,
            @Part("tgl_selesai") RequestBody tgl_selesai,
            @Part("harga_beli_sekarang") double harga_beli_sekarang,
            @Part("jumlah") int jumlah,
            @Part("keterangan") RequestBody keterangan
    );

    @Multipart
    @POST("EditProfilePelelang/id/{id}")
    Call<GeneralModel> editProfile(
            @Path("id") String id,
            @Part MultipartBody.Part filektp,
            @Part MultipartBody.Part filenpwp,
            @Part MultipartBody.Part fileSIUP,
            @Part("telp") RequestBody telp,
            @Part("provinsi") RequestBody provinsi,
            @Part("kota") RequestBody kota,
            @Part("jenis") int jenis,
            @Part("norekening") RequestBody norekening,
            @Part("bank") RequestBody bank,
            @Part("atasnama") RequestBody atasnama,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("kecamatan") RequestBody kecamatan,
            @Part("kelurahan") RequestBody kelurahan,
            @Part("email") RequestBody email,
            @Part("alamat") RequestBody alamat
    );

    @Multipart
    @POST("update_produk/id/{id}")
    Call<UpdateProduk> pelelangUpdateProduk(
            @Path("id") String path,
            @Part MultipartBody.Part image1,
            @Part MultipartBody.Part image2,
            @Part MultipartBody.Part image3,
            @Part MultipartBody.Part image4,
            @Part("produk") RequestBody produk,
            @Part("pelelang_id") RequestBody pelelang_id,
            @Part("deskripsi_produk") RequestBody deskripsi_produk,
            @Part("harga_awal") double harga_awal,
            @Part("harga_minimal_diterima") double harga_minimal_diterima,
            @Part("incremental_value") double incremental_value,
            @Part("tgl_mulai") RequestBody tgl_mulai,
            @Part("tgl_selesai") RequestBody tgl_selesai,
            @Part("harga_beli_sekarang") double harga_beli_sekarang,
            @Part("jumlah") int jumlah,
            @Part("keterangan") RequestBody keterangan
    );
    //
    @DELETE("produk/id/{id}")
    Call<DeleteProdukResponse> deleteProduk(@Path("id") String path);
    //
    @GET("transaksi/id/{id}")
    Call<ListTransaksiResponse> getPelelang(@Path("id") String path);

    @GET("detailbyid/id/{id}")
    Call<DetailTransaksiResponse> getAllTransaksi(@Path("id") String path);

    @FormUrlEncoded
    @PUT("edit_nama_kendaraan/id/{id}")
    Call<UpdateNamaKendaraan> getNamaKendaraan(
            @Path("id") String path,
            @Field("nama_kendaraan") String nama_kendaraan);

    @FormUrlEncoded
    @PUT("edit_no_polisi/id/{id}")
    Call<UpdateNoPolisiPengirim> getNoPolisi
            (@Path("id") String path,
             @Field("no_pol") String no_polisi);

    @FormUrlEncoded
    @PUT("edit_no_hp/id/{id}")
    Call<UpdateNoHpPengirim> getNoHp
            (@Path("id") String path,
             @Field("telp") String no_hp);

    @FormUrlEncoded
    @PUT("edit_Namapengirim/id/{id}")
    Call<UpdateNamaPengirim> getNamaPengirim
            (@Path("id") String path,
             @Field("pengirim") String nama_pengirim);

    @FormUrlEncoded
    @PUT("edit_status_transaksi/id/{id}")
    Call<EditStatusTransaksi> getStsTransaksi
            (@Path("id") String path,
             @Field("status_trans") String status_trans);

    @Multipart
    @POST("dataPengiriman")
    Call<KonfirmasiPembayaran> getKonfirmasiBayar(
            @Part("pengirim") RequestBody pengirim,
            @Part("no_pol") RequestBody no_pol,
            @Part("nama_kendaraan") RequestBody nama_kendaraan,
            @Part("telp") RequestBody telp,
            @Part("status_transaksi") RequestBody status_transaksi,
            @Part("tgl_kirim") RequestBody tgl_kirim
    );

    @POST("dataPengiriman/id/{id}")
    Call<ProsesTransaksiResponse> prosesTransaksiResponse(
            @Path("id") String path,
            @Body ProsesTransaksiRequest prosesTransaksiRequest
    );

    // Admin

    @GET("infoPemenangLelang")
    Call<ArrayList<PemenangModel>> getAllPemenang();


    @GET("InfoDetailPanitiaAdmin")
    Call<ArrayList<AdminPanitiaModel>> getDetailPanitiaAdmin(@Path("kode") String path);

    @GET("InfoPanitiaAdmin")
    Call<ArrayList<AdminPanitiaModel>> getAllPanitiaAdmin();


    @GET("infoPelelang")
    Call<ArrayList<AdminPelelangModel>> getAllPelelangAdmin();

    @GET("infoPeserta")
    Call<ArrayList<AdminPesertaModel>> getAllPesertaAdmin();



    @GET("infoDataPembayaran")
    Call<ArrayList<AdminPembayaranModel>> getAllPembayaranAdmin();

    @GET("infoKonfirmasiLelang")
    Call<ArrayList<AdminKonfirmasiModel>> getAllKonfirmasiAdmin();


    @GET("infoPengiriman")
    Call<ArrayList<AdminPengemasanModel>> getAllPengemasanAdmin();


    @GET("infoAkunAdmin/id/{kode}")
    Call<ProfileAdminModel> getProfileAdmin(@Path("kode") String path);

    @POST("produk")
    Call<TambahLelangModel> pelelangAddProduk(@Body TambahLelangModel tambahLelangModel);

    @GET("detailProdukLelang/id/{kode}")
    Call<KatalogLelangAdminModel>detailLelangAdmin(@Path("kode") String path);


    @GET("produkLelangAdmin")
    Call<ArrayList<KatalogLelangAdminModel>> produkLelangAdmin();

    @GET("infoRiwayatLelang")
    Call<ArrayList<AdminRiwayatModel>> infoRiwayatLelang();

    @GET("infoDeposit")
    Call<ArrayList<AdminDepositModel>> infoDeposit();

    @GET("infoPembayaranPanitia")
    Call<ArrayList<AdminPembayaranHasilLelangModel>> infoPembayaranPanitia();


    @GET("infoTawarjoin/id/{lelang_id}")
    Call<ArrayList<AdminPenawaranModel>> infoTawarjoin(@Path("lelang_id") String lelang_id);



    // Panitia
    @GET("infoPembukaanLelangPanitia")
    Call<ArrayList<ProdukPanitiaModel>> getAllProductPanitia();

    @GET("infoPesertaLelangPanitia")
    Call<ArrayList<PanitiaPesertaModel>> getAllPesertaPanitia();

    @PUT("updatePesertaLelangPanitia")
    Call<PanitiaPesertaModel>putVerifPesertaPanitia(@Body PanitiaPesertaModel panitiaPesertaModel);

    @GET("infoDataPelelangPanitia")
    Call<ArrayList<PanitiaPelelangModel>> getAllPelelangPanitia();

    @PUT("updateStatusPelelang")
    Call<PanitiaPelelangModel>putVerifPelelangPanitia(@Body PanitiaPelelangModel panitiaPelelangModel);

    @GET("infoPengemasanLelangPanitia")
    Call<ArrayList<PanitiaPengemasanModel>> getAllPengemasanPanitia();

    @GET("infoPengirimanLelangPanitia")
    Call<ArrayList<PanitiaPengirimanModel>> getAllPengirimanPanitia();

    @GET("infoTerkirimLelangPanitia")
    Call<ArrayList<PanitiaTerkirimModel>> getAllTerkirimPanitia();

    @PUT("verifPembukaanLelang")
    Call<ProdukPanitiaModel>putverifPembukaan(@Body ProdukPanitiaModel produkPanitiaModel);

    @GET("infoVerifLelangPanitia")
    Call<ArrayList<ProdukPanitiaModel>> getAllVerifProductPanitia();

    @GET("infoTestimoniPanitia")
    Call<ArrayList<PanitiaTestimoniModel>> getAllTestimoniPanitia();

    @GET("infoPemenangPanitia")
    Call<ArrayList<PanitiaPemenangModel>> getAllPemenangPanitia();

    @PUT("updatePemenangLelangPanitia")
    Call<PanitiaPemenangModel>putVerifPemenangPanitia(@Body PanitiaPemenangModel panitiaPemenangModel);

    @GET("infoSuratPerintahPanitia")
    Call<ArrayList<PanitiaSuratPerintahModel>> getAllSuratPerintahPanitia();

    @GET("infoPenawaranCalonPemanangPanitia/id/{kode}")
    Call<ArrayList<PanitiaCalonPemenangModel>> getCalonPemanang(@Path("kode") String path);

    @POST("pemenangPesertaPanitia")
    Call<PanitiaPilihanPemenangModel> postPilihanPemenangPanitia(@Body PanitiaPilihanPemenangModel panitiaPilihanPemenangModel);

    @POST("pembayaranPesertaPanitia")
    Call<PanitiaPilihanPembayaranModel> postPilihanPembayaranPanitia(@Body PanitiaPilihanPembayaranModel panitiaPilihanPembayaranModel);

    @GET("infoTransferPanitia")
    Call<ArrayList<PanitiaTransferModel>> getRequestTransferPanitia();

    @POST("pembayaranHasilLelangPanitia")
    Call<PanitiaTransferModel> postTransferPembayaranPanitia(@Body PanitiaTransferModel panitiaTransferModel);

    @GET("infoRiwayatTransferPanitia")
    Call<ArrayList<PanitiaRiwayatTransferModel>> getRiwayatTransferPanitia();

    @GET("riwayatLelangPanitia")
    Call<ArrayList<PanitiaRiwayatLelangModel>> getRiwayatLelangPanitia();

    @GET("profilepanitia/id/{kode}")
    Call<PanitiaprofileModel>getProfilePanitia(@Path("kode") String kode);

    @PUT("editPanitia")
    Call<PanitiaprofileModel> postProfilePanitia(@Body PanitiaprofileModel panitiaprofileModel);

    @GET("buktiTransferPanitia/id/{id}")
    Call<PanitiaRiwayatBuktiTransferModel> getRiwayatBuktiTransferPanitia(@Path("id") String lelang_id);

    @GET("pemenangLelangCalonPanitia/id/{id}")
    Call<PanitiaPemenangCalonModel> getPemenangCalonPanitia(@Path("id") String lelang_id);

    @GET("infoDepositPanitia")
    Call<ArrayList<PanitiaDepositPesertaModel>> getDepositPanitia();

    @PUT("editDepositPanitia")
    Call<PanitiaDepositPesertaModel>putStatusBayarDeposit(@Body PanitiaDepositPesertaModel panitiaDepositPesertaModel);

    @PUT("tambahDepositPeserta")
    Call<PanitiaUbahSaldoPesertaModel>putTambahDeposit(@Body PanitiaUbahSaldoPesertaModel ubahSaldoPesertaModel);

    @GET("infoBayarPemenangPanitia")
    Call<ArrayList<PanitiaPembayaranPesertaModel>> getPembayaranPanitia();

    @PUT("editPembayaranPanitia")
    Call<PanitiaPembayaranPesertaModel>putPembayaranPanitia(@Body PanitiaPembayaranPesertaModel panitiaPembayaranPesertaModel);

    @PUT("editPemenangPanitia")
    Call<PanitiaPembayaranPemenangModel>putPemenangPanitia(@Body PanitiaPembayaranPemenangModel panitiaPembayaranPemenangModel);
}
