package com.suwardi.icheck;

public class data_laporan {
    private String url_photo;
    private String nama;
    private String umur;
    private String nomer_hp;
    private String nomer_hp_wali;
    private String tanggal_lahir;
    private String alamat;
    private String total;
    private String jadwal;
    private String checkup;

    public data_laporan() {
    }

    public data_laporan(String url_photo, String nama, String umur, String nomer_hp, String nomer_hp_wali, String tanggal_lahir, String alamat, String total, String jadwal, String checkup) {
        this.url_photo = url_photo;
        this.nama = nama;
        this.umur = umur;
        this.nomer_hp = nomer_hp;
        this.nomer_hp_wali = nomer_hp_wali;
        this.tanggal_lahir = tanggal_lahir;
        this.alamat = alamat;
        this.total = total;
        this.jadwal = jadwal;
        this.checkup = checkup;
    }

    public String getUrl_photo() {
        return url_photo;
    }

    public String getNama() {
        return nama;
    }

    public String getUmur() {
        return umur;
    }

    public String getNomer_hp() {
        return nomer_hp;
    }

    public String getNomer_hp_wali() {
        return nomer_hp_wali;
    }

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getTotal() {
        return total;
    }

    public String getJadwal() {
        return jadwal;
    }

    public String getCheckup() {
        return checkup;
    }
}
