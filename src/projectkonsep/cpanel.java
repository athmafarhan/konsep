/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectkonsep;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import projectkonsep.kamera.JenisKamera;
public class cpanel {
    Connection con;//
    public Connection getCon(){//
        if (con!=null){
            return con;
        }
        else{
            try {
                con = DriverManager.getConnection("jdbc:mysql://mykonsep.com/lxdxguei_sewa","lxdxguei","!63areYbs4");
                //con = DriverManager.getConnection("jdbc:mysql://localhost/konsepstudio","root","");
                //con = DriverManager.getConnection("jdbc:mysql://localhost/konsepstudio","root","");
                //JOptionPane.showMessageDialog(null, "Konesi ke Database BERHASIL");
                System.out.println("Berhasil");
            } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Koneksi ke Database GAGAL");
            e.printStackTrace();
            }
        }
        return con;
    }
    
    public static void main(String[] args) throws NoSuchPaddingException, ShortBufferException, IllegalBlockSizeException, BadPaddingException {
        
        Connection tes = new cpanel().getCon();
    }
}