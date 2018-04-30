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
public class CetakBuktiStudio extends PenyewaanStudio {
    private String no_hp_cust;
    private String instagram;
    
    public CetakBuktiStudio() {
        
    }

    public CetakBuktiStudio(int id, int id_cust, String nama_cust, String no_hp_cust, String instagram, int id_item, String nama_item, String tgl_sewa, String jam_mulai, String jam_berakhir, int harga, int dp, int diskon, int denda, int jml, String cara_bayar) {
        super(id, id_cust, nama_cust, id_item, nama_item, tgl_sewa, jam_mulai, jam_berakhir, harga, dp, diskon, denda, jml, cara_bayar);
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
