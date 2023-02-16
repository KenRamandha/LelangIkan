package com.example.myapplication.model.pelelang;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListDataTransaksi {
    @SerializedName("lelang_id")
    @Expose
    private String lelangId;

    @SerializedName("peserta_id")
    @Expose
    private String pesertaId;

    @SerializedName("tgl_diumumkan")
    @Expose
    private String tglDiumumkan;

    @SerializedName("bukti_bayar")
    @Expose
    private Object buktiBayar;

    @SerializedName("tgl_bayar")
    @Expose
    private Object tglBayar;

    @SerializedName("provinsi_kirim")
    @Expose
    private Object provinsiKirim;

    @SerializedName("kota_kirim")
    @Expose
    private Object kotaKirim;

    @SerializedName("kecamatan_kirim")
    @Expose
    private Object kecamatanKirim;

    @SerializedName("kelurahan_kirim")
    @Expose
    private Object kelurahanKirim;

    @SerializedName("alamat_kirim")
    @Expose
    private Object alamatKirim;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("konfirmasi_terimaproduk")
    @Expose
    private String konfirmasiTerimaproduk;

    @SerializedName("testimoni_pemenang")
    @Expose
    private Object testimoniPemenang;

    @SerializedName("panitia_id")
    @Expose
    private Object panitiaId;

    @SerializedName("total_bayar")
    @Expose
    private String totalBayar;

    @SerializedName("nik")
    @Expose
    private String nik;

    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("jeniskel")
    @Expose
    private String jeniskel;

    @SerializedName("provinsi")
    @Expose
    private String provinsi;

    @SerializedName("kota")
    @Expose
    private String kota;

    @SerializedName("kecamatan")
    @Expose
    private String kecamatan;

    @SerializedName("kelurahan")
    @Expose
    private String kelurahan;

    @SerializedName("alamat")
    @Expose
    private String alamat;

    @SerializedName("telp")
    @Expose
    private String telp;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("npwp")
    @Expose
    private String npwp;

    @SerializedName("filektp")
    @Expose
    private String filektp;

    @SerializedName("deposit")
    @Expose
    private String deposit;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("pelelang_id")
    @Expose
    private String pelelangId;

    @SerializedName("produk")
    @Expose
    private String produk;

    @SerializedName("deskripsi_produk")
    @Expose
    private String deskripsiProduk;

    @SerializedName("image1")
    @Expose
    private String image1;

    @SerializedName("image2")
    @Expose
    private String image2;

    @SerializedName("image3")
    @Expose
    private String image3;

    @SerializedName("image4")
    @Expose
    private String image4;

    @SerializedName("harga_awal")
    @Expose
    private String hargaAwal;

    @SerializedName("harga_minimal_diterima")
    @Expose
    private String hargaMinimalDiterima;

    @SerializedName("incremental_value")
    @Expose
    private String incrementalValue;

    @SerializedName("tgl_mulai")
    @Expose
    private String tglMulai;

    @SerializedName("tgl_selesai")
    @Expose
    private String tglSelesai;

    @SerializedName("harga_beli_sekarang")
    @Expose
    private String hargaBeliSekarang;

    @SerializedName("metode_bayar")
    @Expose
    private String metodeBayar = null;

    @SerializedName("keterangan")
    @Expose
    private String keterangan;

    @SerializedName("jumlah")
    @Expose
    private String jumlah;

    @SerializedName("tgl_pembayaran")
    @Expose
    private String tglPembayaran;

    @SerializedName("nominal_dibayarkan")
    @Expose
    private String nominalDibayarkan;

    @SerializedName("bukti_pembayaran")
    @Expose
    private String buktiPembayaran;

    public String getLelangId() {
        return lelangId;
    }

    public void setLelangId(String lelangId) {
        this.lelangId = lelangId;
    }

    public String getPesertaId() {
        return pesertaId;
    }

    public void setPesertaId(String pesertaId) {
        this.pesertaId = pesertaId;
    }

    public String getTglDiumumkan() {
        return tglDiumumkan;
    }

    public void setTglDiumumkan(String tglDiumumkan) {
        this.tglDiumumkan = tglDiumumkan;
    }

    public Object getBuktiBayar() {
        return buktiBayar;
    }

    public void setBuktiBayar(Object buktiBayar) {
        this.buktiBayar = buktiBayar;
    }

    public Object getTglBayar() {
        return tglBayar;
    }

    public void setTglBayar(Object tglBayar) {
        this.tglBayar = tglBayar;
    }

    public Object getProvinsiKirim() {
        return provinsiKirim;
    }

    public void setProvinsiKirim(Object provinsiKirim) {
        this.provinsiKirim = provinsiKirim;
    }

    public Object getKotaKirim() {
        return kotaKirim;
    }

    public void setKotaKirim(Object kotaKirim) {
        this.kotaKirim = kotaKirim;
    }

    public Object getKecamatanKirim() {
        return kecamatanKirim;
    }

    public void setKecamatanKirim(Object kecamatanKirim) {
        this.kecamatanKirim = kecamatanKirim;
    }

    public Object getKelurahanKirim() {
        return kelurahanKirim;
    }

    public void setKelurahanKirim(Object kelurahanKirim) {
        this.kelurahanKirim = kelurahanKirim;
    }

    public Object getAlamatKirim() {
        return alamatKirim;
    }

    public void setAlamatKirim(Object alamatKirim) {
        this.alamatKirim = alamatKirim;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKonfirmasiTerimaproduk() {
        return konfirmasiTerimaproduk;
    }

    public void setKonfirmasiTerimaproduk(String konfirmasiTerimaproduk) {
        this.konfirmasiTerimaproduk = konfirmasiTerimaproduk;
    }

    public Object getTestimoniPemenang() {
        return testimoniPemenang;
    }

    public void setTestimoniPemenang(Object testimoniPemenang) {
        this.testimoniPemenang = testimoniPemenang;
    }

    public Object getPanitiaId() {
        return panitiaId;
    }

    public void setPanitiaId(Object panitiaId) {
        this.panitiaId = panitiaId;
    }

    public String getTotalBayar() {
        return totalBayar;
    }

    public void setTotalBayar(String totalBayar) {
        this.totalBayar = totalBayar;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJeniskel() {
        return jeniskel;
    }

    public void setJeniskel(String jeniskel) {
        this.jeniskel = jeniskel;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public String getKota() {
        return kota;
    }

    public void setKota(String kota) {
        this.kota = kota;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelp() {
        return telp;
    }

    public void setTelp(String telp) {
        this.telp = telp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNpwp() {
        return npwp;
    }

    public void setNpwp(String npwp) {
        this.npwp = npwp;
    }

    public String getFilektp() {
        return filektp;
    }

    public void setFilektp(String filektp) {
        this.filektp = filektp;
    }

    public String getDeposit() {
        return deposit;
    }

    public void setDeposit(String deposit) {
        this.deposit = deposit;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPelelangId() {
        return pelelangId;
    }

    public void setPelelangId(String pelelangId) {
        this.pelelangId = pelelangId;
    }

    public String getProduk() {
        return produk;
    }

    public void setProduk(String produk) {
        this.produk = produk;
    }

    public String getDeskripsiProduk() {
        return deskripsiProduk;
    }

    public void setDeskripsiProduk(String deskripsiProduk) {
        this.deskripsiProduk = deskripsiProduk;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getImage4() {
        return image4;
    }

    public void setImage4(String image4) {
        this.image4 = image4;
    }

    public String getHargaAwal() {
        return hargaAwal;
    }

    public void setHargaAwal(String hargaAwal) {
        this.hargaAwal = hargaAwal;
    }

    public String getHargaMinimalDiterima() {
        return hargaMinimalDiterima;
    }

    public void setHargaMinimalDiterima(String hargaMinimalDiterima) {
        this.hargaMinimalDiterima = hargaMinimalDiterima;
    }

    public String getIncrementalValue() {
        return incrementalValue;
    }

    public void setIncrementalValue(String incrementalValue) {
        this.incrementalValue = incrementalValue;
    }

    public String getTglMulai() {
        return tglMulai;
    }

    public void setTglMulai(String tglMulai) {
        this.tglMulai = tglMulai;
    }

    public String getTglSelesai() {
        return tglSelesai;
    }

    public void setTglSelesai(String tglSelesai) {
        this.tglSelesai = tglSelesai;
    }

    public String getHargaBeliSekarang() {
        return hargaBeliSekarang;
    }

    public void setHargaBeliSekarang(String hargaBeliSekarang) {
        this.hargaBeliSekarang = hargaBeliSekarang;
    }

    public String getMetodeBayar() {
        return metodeBayar;
    }

    public void setMetodeBayar(String metodeBayar) {
        this.metodeBayar = metodeBayar;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getTglPembayaran() {
        return tglPembayaran;
    }

    public void setTglPembayaran(String tglPembayaran) {
        this.tglPembayaran = tglPembayaran;
    }

    public String getNominalDibayarkan() {
        return nominalDibayarkan;
    }

    public void setNominalDibayarkan(String nominalDibayarkan) {
        this.nominalDibayarkan = nominalDibayarkan;
    }

    public String getBuktiPembayaran() {
        return buktiPembayaran;
    }

    public void setBuktiPembayaran(String buktiPembayaran) {
        this.buktiPembayaran = buktiPembayaran;
    }
}
