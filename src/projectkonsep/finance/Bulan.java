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
public class Bulan {
    private String StrBulan;

    public Bulan() {
    }
    private int IntBulan;

    public Bulan(String StrBulan, int IntBulan) {
        this.StrBulan = StrBulan;
        this.IntBulan = IntBulan;
    }

    public String getStrBulan() {
        return StrBulan;
    }

    public void setStrBulan(String StrBulan) {
        this.StrBulan = StrBulan;
    }

    public int getIntBulan() {
        return IntBulan;
    }

    public void setIntBulan(int IntBulan) {
        this.IntBulan = IntBulan;
    }

}
