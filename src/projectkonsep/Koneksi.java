/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectkonsep;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import projectkonsep.kamera.JenisKamera;
public class Koneksi {
    Connection con;//
    public Connection getCon(){//
        if (con!=null){
            return con;
        }
        else{
            try {
                //!63areYbs4
            //con = DriverManager.getConnection("jdbc:mysql://konsepstudio.jux.in/konsepst_konsep","konsepst_studio","PoiLkj123");
            con = DriverManager.getConnection("jdbc:mysql://mykonsep.com/lxdxguei_sewa","lxdxguei","!63areYbs4");
                //if (con==null) {
                    //con = DriverManager.getConnection("jdbc:mysql://localhost/konsep","root","");
                //}
            //"jdbc:mysql://localhost/project", "root",""
            //con = DriverManager.getConnection("jdbc:mysql://localhost/konsepstudio","root","");
            //JOptionPane.showMessageDialog(null, "Konesi ke Database BERHASIL");
            } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Koneksi ke Database GAGAL");
            }
        }
        return con;
    }
    
    
}