/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectkonsep.outdoor;


/**
 *
 * @author Athma Farhan
 */
public class CustomerOutdoor {
    private int id;
    private String nama;
    private String no_hp;
    private String alamat_rumah;
    private String instagram;
    private String status;

    public CustomerOutdoor() {
    }

    public CustomerOutdoor(int id, String nama, String no_hp, String alamat_rumah, String instagram, String status) {
        this.id = id;
        this.nama = nama;
        this.no_hp = no_hp;
        this.alamat_rumah = alamat_rumah;
        this.instagram = instagram;
        this.status = status;
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

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getAlamat_rumah() {
        return alamat_rumah;
    }

    public void setAlamat_rumah(String alamat_rumah) {
        this.alamat_rumah = alamat_rumah;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    
    
}
