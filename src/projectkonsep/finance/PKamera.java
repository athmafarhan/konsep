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
public class PKamera {
    private int id_owner;
    private String nama;
    private int persentase;

    public PKamera() {
    }

    public PKamera(int id_owner,String nama, int persentase) {
        this.id_owner = id_owner;
        this.nama = nama;
        this.persentase = persentase;
    }

    public int getId_owner() {
        return id_owner;
    }

    public void setId_owner(int id_owner) {
        this.id_owner = id_owner;
    }

    public int getPersentase() {
        return persentase;
    }

    public void setPersentase(int persentase) {
        this.persentase = persentase;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
    
}
