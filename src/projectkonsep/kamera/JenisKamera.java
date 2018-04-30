/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectkonsep.kamera;

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
public class JenisKamera {
    private int id;
    private int id_tipe;
    private String tipe;
    private int id_merk;
    private String merk;
    private String nama;
    
    public JenisKamera() {
    }

    public JenisKamera(int id, int id_tipe, String tipe, int id_merk, String merk, String nama) {
        this.id = id;
        this.id_tipe = id_tipe;
        this.tipe = tipe;
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

    public int getId_merk() {
        return id_merk;
    }

    public void setId_merk(int id_merk) {
        this.id_merk = id_merk;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
    
    
    
}
