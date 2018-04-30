/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectkonsep.outdoor.form;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import projectkonsep.Koneksi;
import projectkonsep.outdoor.InventarisOutdoor;

/**
 *
 * @author Athma Farhan
 */
public class Testing {
    
    public static void combo2() {
        ArrayList<InventarisOutdoor> ListInventarisKamera = new ArrayList<>();
        Connection con2 = new Koneksi().getCon();
        Statement stmt2;
        String sql = "SELECT * FROM V_InventarisOutdoor";    
        try {
            stmt2 = con2.createStatement();
            ResultSet cb2 = stmt2.executeQuery(sql);
            while (cb2.next()) {                
                int id = cb2.getInt("id");
                String merk = cb2.getString("merk");
                String jenis = cb2.getString("jenis");
                String stringwarna = cb2.getString("stringwarna");
                int harga = cb2.getInt("harga");
                InventarisOutdoor IK = new InventarisOutdoor(id, merk, jenis, stringwarna, harga);

                ListInventarisKamera.add(IK);
                System.out.println(merk+" "+jenis+" "+stringwarna);
//                cbInventaris1.addItem(merk+" "+jenis+" "+stringwarna);
//                cbInventaris2.addItem(merk+" "+jenis+" "+stringwarna);
//                cbInventaris3.addItem(merk+" "+jenis+" "+stringwarna);
//                cbInventaris4.addItem(merk+" "+jenis+" "+stringwarna);
                System.out.println(ListInventarisKamera.get(0).getId());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       //AutoCompleteDecorator.decorate(cbInventaris1);
    }
    public static void main(String[] args) {
        Testing.combo2();
    }
}
