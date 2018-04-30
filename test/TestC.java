
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Athma Farhan
 */
public class TestC {
    static ArrayList<AL> cobaa = new ArrayList<>();
    static ArrayList<AL> cobah = new ArrayList<>();
    static ArrayList<ArrayList<AL>> coba = new ArrayList();
    
    public static void main(String[] args) {
        
        
            cobaa.add(new AL(0,1,20000));
        
            
        
        AL he = new AL(9,5,30000);
        AL hehe = new AL(9,5,30000);
        cobah.add(hehe);
        cobaa.add(he);
            coba.add(cobaa);
            coba.add(cobah);
        
        for (ArrayList<AL> i: coba) {
                
            System.out.println(i.get(0).getHarga());
        }
    }
}
