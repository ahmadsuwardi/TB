package com.suwardi.icheck;

public class data_pasien {
    private String url_photo;
    private String nik;
    private String riwayat_penyakit;
    private String berat_badan;
    private String jenis_kelamin;
    private String nama;
    private String umur;
    private String nomer_hp;
    private String nomer_hp_wali;
    private String tanggal_lahir;
    private String alamat;
    private String nip_dokter;

    public data_pasien() {
    }

    public data_pasien(String url_photo, String nik, String riwayat_penyakit, String berat_badan, String jenis_kelamin, String nama, String umur, String nomer_hp, String nomer_hp_wali, String tanggal_lahir, String alamat, String nip_dokter) {
        this.url_photo = url_photo;
        this.nik = nik;
        this.riwayat_penyakit = riwayat_penyakit;
        this.berat_badan = berat_badan;
        this.jenis_kelamin = jenis_kelamin;
        this.nama = nama;
        this.umur = umur;
        this.nomer_hp = nomer_hp;
        this.nomer_hp_wali = nomer_hp_wali;
        this.tanggal_lahir = tanggal_lahir;
        this.alamat = alamat;
        this.nip_dokter = nip_dokter;
    }

    public String getUrl_photo() {
        return url_photo;
    }

    public void setUrl_photo(String url_photo) {
        this.url_photo = url_photo;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getRiwayat_penyakit() {
        return riwayat_penyakit;
    }

    public void setRiwayat_penyakit(String riwayat_penyakit) {
        this.riwayat_penyakit = riwayat_penyakit;
    }

    public String getBerat_badan() {
        return berat_badan;
    }

    public void setBerat_badan(String berat_badan) {
        this.berat_badan = berat_badan;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getNomer_hp() {
        return nomer_hp;
    }

    public void setNomer_hp(String nomer_hp) {
        this.nomer_hp = nomer_hp;
    }

    public String getNomer_hp_wali() {
        return nomer_hp_wali;
    }

    public void setNomer_hp_wali(String nomer_hp_wali) {
        this.nomer_hp_wali = nomer_hp_wali;
    }

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(String tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNip_dokter() {
        return nip_dokter;
    }

    public void setNip_dokter(String nip_dokter) {
        this.nip_dokter = nip_dokter;
    }
}