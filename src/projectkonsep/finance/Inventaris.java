/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectkonsep.finance;

/**
 *
 * @author Dhandy
 */
public class Inventaris {
    private int id;
    private String nama;
    private int jml_hari;
    private int jml;

    public Inventaris(int id, String nama, int jml_hari, int jml) {
        this.id = id;
        this.nama = nama;
        this.jml_hari = jml_hari;
        this.jml = jml;
    }

    public Inventaris() {
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

    public int getJml_hari() {
        return jml_hari;
    }

    public void setJml_hari(int jml_hari) {
        this.jml_hari = jml_hari;
    }

    public int getJml() {
        return jml;
    }

    public void setJml(int jml) {
        this.jml = jml;
    }
}
