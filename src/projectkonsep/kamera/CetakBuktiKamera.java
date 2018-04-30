/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectkonsep.kamera;

/**
 *
 * @author Athma Farhan
 */
public class CetakBuktiKamera extends PinjamKembaliKamera{
    private String no_hp_cust;
    private String instagram;

    public CetakBuktiKamera(int id, int id_cust, String nama_cust, String no_hp_cust, String instagram, int id_inventaris, String nama_inventaris, String tgl_sewa, String jam_sewa, String tgl_kembali, String jam_kembali, int harga, int dp, int diskon, int denda, int jml, String cara_bayar) {
        super(id, id_cust, nama_cust, id_inventaris, nama_inventaris, tgl_sewa, jam_sewa, tgl_kembali, jam_kembali, harga, dp, diskon, denda, jml, cara_bayar);
        this.no_hp_cust = no_hp_cust;
        this.instagram = instagram;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getNo_hp_cust() {
        return no_hp_cust;
    }

    public void setNo_hp_cust(String no_hp_cust) {
        this.no_hp_cust = no_hp_cust;
    }
    
    
}
