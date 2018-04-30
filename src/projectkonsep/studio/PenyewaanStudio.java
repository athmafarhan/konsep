/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectkonsep.studio;

/**
 *
 * @author Athma Farhan
 */
public class PenyewaanStudio {
    private int id;
    private int id_cust;
    private String nama_cust;
    private int id_item;
    private String nama_item;
    private String tgl_sewa;
    private String jam_mulai;
    private String jam_berakhir;
    private int harga;
    private int dp;
    private int diskon;
    private int denda;
    private int jml;
    private String cara_bayar;

    public PenyewaanStudio() {
    }

    public PenyewaanStudio(int id, int id_cust, String nama_cust, int id_item, String nama_item, String tgl_sewa, String jam_mulai, String jam_berakhir, int harga, int dp, int diskon, int denda, int jml, String cara_bayar) {
        this.id = id;
        this.id_cust = id_cust;
        this.nama_cust = nama_cust;
        this.id_item = id_item;
        this.nama_item = nama_item;
        this.tgl_sewa = tgl_sewa;
        this.jam_mulai = jam_mulai;
        this.jam_berakhir = jam_berakhir;
        this.harga = harga;
        this.dp = dp;
        this.diskon = diskon;
        this.denda = denda;
        this.jml = jml;
        this.cara_bayar = cara_bayar;
    }

    public String getCara_bayar() {
        return cara_bayar;
    }

    public void setCara_bayar(String cara_bayar) {
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

    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    public String getNama_item() {
        return nama_item;
    }

    public void setNama_item(String nama_item) {
        this.nama_item = nama_item;
    }

    public String getTgl_sewa() {
        return tgl_sewa;
    }

    public void setTgl_sewa(String tgl_sewa) {
        this.tgl_sewa = tgl_sewa;
    }

    public String getJam_mulai() {
        return jam_mulai;
    }

    public void setJam_mulai(String jam_mulai) {
        this.jam_mulai = jam_mulai;
    }

    public String getJam_berakhir() {
        return jam_berakhir;
    }

    public void setJam_berakhir(String jam_berakhir) {
        this.jam_berakhir = jam_berakhir;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getDp() {
        return dp;
    }

    public void setDp(int dp) {
        this.dp = dp;
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
    
    
}
