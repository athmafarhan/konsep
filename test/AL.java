/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Athma Farhan
 */
public class AL {
    private Integer id;
    private Integer nomer;
    private Integer harga;

    public AL(Integer id, Integer nomer, Integer harga) {
        this.id = id;
        this.nomer = nomer;
        this.harga = harga;
    }

    public AL() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNomer() {
        return nomer;
    }

    public void setNomer(Integer nomer) {
        this.nomer = nomer;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }
    
    
}
