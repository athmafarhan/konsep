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
public class Owner {
    private int id;
    private String owner;
    private int jml;
    private double share;
    private double share_owner;

    public Owner(double share) {
        this.share = share;
    }
    
    public Owner(int id, String owner) {
        this.id = id;
        this.owner = owner;
    }

    public Owner(int id, String owner, int jml, double share, double share_owner) {
        this.id = id;
        this.owner = owner;
        this.jml = jml;
        this.share = share;
        this.share_owner = share_owner;
    }
    
    public Owner() {
    }

    public int getJml() {
        return jml;
    }

    public void setJml(int jml) {
        this.jml = jml;
    }

    public double getShare() {
        return share;
    }

    public void setShare(double share) {
        this.share = share;
    }

    public double getShare_owner() {
        return share_owner;
    }

    public void setShare_owner(double share_owner) {
        this.share_owner = share_owner;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    
}
