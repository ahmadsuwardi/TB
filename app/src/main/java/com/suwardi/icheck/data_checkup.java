package com.suwardi.icheck;

public class data_checkup {
    private String url;
    private String pasien;
    private String umur;
    private String nomer_hp_wali;
    private String nomer_hp;
    private String alamat;
    private String tanggal_lahir;
    private String date;
    private String time;

    public data_checkup() {
    }

    public data_checkup(String url, String pasien, String umur, String nomer_hp_wali, String nomer_hp, String alamat, String tanggal_lahir, String date, String time) {
        this.url = url;
        this.pasien = pasien;
        this.umur = umur;
        this.nomer_hp_wali = nomer_hp_wali;
        this.nomer_hp = nomer_hp;
        this.alamat = alamat;
        this.tanggal_lahir = tanggal_lahir;
        this.date = date;
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public String getPasien() {
        return pasien;
    }

    public String getUmur() {
        return umur;
    }

    public String getNomer_hp_wali() {
        return nomer_hp_wali;
    }

    public String getNomer_hp() {
        return nomer_hp;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
