/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectkonsep.kamera;

import java.util.Date;

/**
 *
 * @author Athma Farhan
 */
public class PinjamKembaliKamera {
    private int id;
    private int id_cust;
    private String nama_cust;
    private int id_inventaris;
    private String nama_inventaris;
    private String tgl_sewa;
    private String jam_sewa;
    private String tgl_kembali;
    private String jam_kembali;
    private int harga;
    private int dp;
    private int diskon;
    private int denda;
    private int jml;
    private String cara_bayar;
    
    public PinjamKembaliKamera() {
    }

    public PinjamKembaliKamera(int id, int id_cust, String nama_cust, int id_inventaris, String nama_inventaris, String tgl_sewa, String jam_sewa, String tgl_kembali, String jam_kembali, int harga, int dp, int diskon, int denda, int jml, String cara_bayar) {
        this.id = id;
        this.id_cust = id_cust;
        this.nama_cust = nama_cust;
        this.id_inventaris = id_inventaris;
        this.nama_inventaris = nama_inventaris;
        this.tgl_sewa = tgl_sewa;
        this.jam_sewa = jam_sewa;
        this.tgl_kembali = tgl_kembali;
        this.jam_kembali = jam_kembali;
        this.harga = harga;
        this.dp = dp;
        this.diskon = diskon;
        this.denda = denda;
        this.jml = jml;
        this.cara_bayar = cara_bayar;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_cust() {
        return id_cust;
    }

    public void setId_cust(int id_cust) {
        this.id_cust = id_cust;
    }

    public String getNama_cust() {
        return nama_cust;
    }

    public void setNama_cust(String nama_cust) {
        this.nama_cust = nama_cust;
    }

    public int getId_inventaris() {
        return id_inventaris;
    }

    public void setId_inventaris(int id_inventaris) {
        this.id_inventaris = id_inventaris;
    }

    public String getNama_inventaris() {
        return nama_inventaris;
    }

    public void setNama_inventaris(String nama_inventaris) {
        this.nama_inventaris = nama_inventaris;
    }

    public String getTgl_sewa() {
        return tgl_sewa;
    }

    public void setTgl_sewa(String tgl_sewa) {
        this.tgl_sewa = tgl_sewa;
    }

    public String getJam_sewa() {
        return jam_sewa;
    }

    public void setJam_sewa(String jam_sewa) {
        this.jam_sewa = jam_sewa;
    }

    public String getTgl_kembali() {
        return tgl_kembali;
    }

    public void setTgl_kembali(String tgl_kembali) {
        this.tgl_kembali = tgl_kembali;
    }

    public String getJam_kembali() {
        return jam_kembali;
    }

    public void setJam_kembali(String jam_kembali) {
        this.jam_kembali = jam_kembali;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }
    
    public int getDiskon() {
        return diskon;
    }

    public void setDiskon(int diskon) {
        this.diskon = diskon;
    }

    public int getDenda() {
        return denda;
    }

    public void setDenda(int denda) {
        this.denda = denda;
    }

    public int getJml() {
        return jml;
    }

    public void setJml(int jml) {
        this.jml = jml;
    }

    public String getCara_bayar() {
        return cara_bayar;
    }

    public void setCara_bayar(String cara_bayar) {
        this.cara_bayar = cara_bayar;
    }

    public int getDp() {
        return dp;
    }

    public void setDp(int dp) {
        this.dp = dp;
    }

    
}
