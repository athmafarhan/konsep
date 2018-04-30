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
public class MerkKamera {
    private int id;
    private int id_tipe;
    private String tipe;
    private String nama;
    
    public MerkKamera() {
        
    }

    public MerkKamera(int id, int id_tipe, String tipe, String nama) {
        this.id = id;
        this.id_tipe = id_tipe;
        this.tipe = tipe;
        this.nama = nama;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_tipe() {
        return id_tipe;
    }

    public void setId_tipe(int id_tipe) {
        this.id_tipe = id_tipe;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
    
    
}
