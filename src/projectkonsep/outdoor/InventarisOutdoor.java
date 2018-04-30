/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectkonsep.outdoor;

import projectkonsep.kamera.*;

/**
 *
 * @author Athma Farhan
 */
public class InventarisOutdoor {
    private int id;
    private int id_merk;
    private String merk;
    private int id_jenis;
    private String jenis;
    private int harga;
    private String warna;
    private String string_warna;
    private String pemilik;

    public InventarisOutdoor(int id, int id_merk, String merk, int id_jenis, String jenis, int harga, String warna, String string_warna, String pemilik) {
        this.id = id;
        this.id_merk = id_merk;
        this.merk = merk;
        this.id_jenis = id_jenis;
        this.jenis = jenis;
        this.harga = harga;
        this.warna = warna;
        this.string_warna = string_warna;
        this.pemilik = pemilik;
    }
    

    public InventarisOutdoor(int id, String merk, String jenis, String string_warna, int harga) {
        this.id = id;
        this.merk = merk;
        this.jenis = jenis;
        this.string_warna = string_warna;
        this.harga = harga;
    }

    public InventarisOutdoor() {
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getWarna() {
        return warna;
    }

    public void setWarna(String warna) {
        this.warna = warna;
    }

    public int getId_merk() {
        return id_merk;
    }

    public void setId_merk(int id_merk) {
        this.id_merk = id_merk;
    }

    public int getId_jenis() {
        return id_jenis;
    }

    public void setId_jenis(int id_jenis) {
        this.id_jenis = id_jenis;
    }

    public String getString_warna() {
        return string_warna;
    }

    public void setString_warna(String string_warna) {
        this.string_warna = string_warna;
    }

    public String getPemilik() {
        return pemilik;
    }

    public void setPemilik(String pemilik) {
        this.pemilik = pemilik;
    }
    
    
    
}
