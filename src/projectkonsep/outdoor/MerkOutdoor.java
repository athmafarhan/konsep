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
public class MerkOutdoor {
    private int id;
    private String nama;
    
    public MerkOutdoor() {
        
    }

    public MerkOutdoor(int id, String nama) {
        this.id = id;
        this.nama = nama;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
    
    
}
