/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectkonsep.outdoor;

import projectkonsep.kamera.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import projectkonsep.Koneksi;

/**
 *
 * @author Athma Farhan
 */
public class JenisOutdoor {
    private int id;
    private int id_merk;
    private String merk;
    private String nama;
    
    public JenisOutdoor() {
    }

    public JenisOutdoor(int id, int id_merk, String nama) {
        this.id = id;
        this.id_merk = id_merk;
        this.nama = nama;
    }

    public JenisOutdoor(int id, int id_merk, String merk, String nama) {
        this.id = id;
        this.id_merk = id_merk;
        this.merk = merk;
        this.nama = nama;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getId_merk() {
        return id_merk;
    }

    public void setId_merk(int id_merk) {
        this.id_merk = id_merk;
    }


    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
    
    
    
}
