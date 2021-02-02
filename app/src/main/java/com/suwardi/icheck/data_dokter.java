package com.suwardi.icheck;

public class data_dokter {
    private String url_photo;
    private String nama;
    private String nip;
    private String spesialis;
    private String alamat;
    private String nomer_hp;

    public data_dokter() {
    }

    public data_dokter(String url_photo, String nama, String nip, String spesialis, String alamat, String nomer_hp) {
        this.url_photo = url_photo;
        this.nip = nip;
        this.nama = nama;
        this.spesialis = spesialis;
        this.alamat = alamat;
        this.nomer_hp = nomer_hp;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getUrl_photo() {
        return url_photo;
    }

    public void setUrl_photo(String url_photo) {
        this.url_photo = url_photo;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getSpesialis() {
        return spesialis;
    }

    public void setSpesialis(String spesialis) {
        this.spesialis = spesialis;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNomer_hp() {
        return nomer_hp;
    }

    public void setNomer_hp(String nomer_hp) {
        this.nomer_hp = nomer_hp;
    }

}
