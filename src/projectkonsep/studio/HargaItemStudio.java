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
public class HargaItemStudio {
    private int id;
    private int id_item;
    private String item;
    private String nama_paket;
    private int harga_1_jam;
    private int harga_2_jam;
    private int harga_tambahan;
    
    public HargaItemStudio() {
        
    }

    
    
    public HargaItemStudio(int id, int id_item, String item, String nama_paket, int harga_1_jam, int harga_2_jam, int harga_tambahan) {
        this.id = id;
        this.id_item = id_item;
        this.item = item;
        this.nama_paket = nama_paket;
        this.harga_1_jam = harga_1_jam;
        this.harga_2_jam = harga_2_jam;
        this.harga_tambahan = harga_tambahan;
    }

    public int getHarga_tambahan() {
        return harga_tambahan;
    }

    public void setHarga_tambahan(int harga_tambahan) {
        this.harga_tambahan = harga_tambahan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getNama_paket() {
        return nama_paket;
    }

    public void setNama_paket(String nama_paket) {
        this.nama_paket = nama_paket;
    }

    public int getHarga_1_jam() {
        return harga_1_jam;
    }

    public void setHarga_1_jam(int harga_1_jam) {
        this.harga_1_jam = harga_1_jam;
    }

    public int getHarga_2_jam() {
        return harga_2_jam;
    }

    public void setHarga_2_jam(int harga_2_jam) {
        this.harga_2_jam = harga_2_jam;
    }
    
    
}
