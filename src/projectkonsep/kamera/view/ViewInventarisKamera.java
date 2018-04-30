/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectkonsep.kamera.view;

import projectkonsep.Item;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import projectkonsep.Koneksi;
import projectkonsep.kamera.InventarisKamera;
import projectkonsep.kamera.JenisKamera;
import projectkonsep.kamera.MerkKamera;
import projectkonsep.kamera.TipeKamera;
import projectkonsep.kamera.form.FormPeminjamanKamera;

/**
 *
 * @author Athma Farhan
 */
public class ViewInventarisKamera extends javax.swing.JFrame {
    LinkedHashMap<String, Integer> hashPemilik = new LinkedHashMap<>();
    Collection<String> keyPemilik;
    
    LinkedHashMap<String, Integer> hashJenis = new LinkedHashMap<>();
    Collection<String> keyJenis;
    
    LinkedHashMap<String, Integer> hashMerk = new LinkedHashMap<>();
    Collection<String> keyMerk;
    
    LinkedHashMap<String, Integer> hashTipe = new LinkedHashMap<>();
    Collection<String> keyTipe;
    
    

    ArrayList<JenisKamera> ListJK = new ArrayList<>();
    ArrayList<MerkKamera> ListMK = new ArrayList<>();
    ArrayList<TipeKamera> ListTK = new ArrayList<>();
    
    private DefaultTableModel tabmode;
    String warnaRGB = "255255255";
    Connection con = null;
    PreparedStatement myPreparedStatement = null;
    Object[] headerInventaris = {"ID", "Tipe", "Merk", "Jenis", "Harga", "Warna", "Warna", "Pemilik"};
    DefaultTableModel dataInventaris = new DefaultTableModel(null, headerInventaris);
    int index = -1;
    ArrayList<InventarisKamera> ListInventarisKamera = new ArrayList<>();//

    ArrayList<Item<TipeKamera>> listItemTipeKamera = new ArrayList<>();
    
    ArrayList<Item<MerkKamera>> listItemMerkKamera = new ArrayList<>();
    
    ArrayList<Item<JenisKamera>> listItemJenisKamera = new ArrayList<>();
    
    int IDMerk = 0;
    int IDJenis = 0;
    /**
     * Creates new form FormCustomerKamera
     */
    public ViewInventarisKamera() {
        initComponents();
        connectDatabase();
    }
    
    public void updateTableInventaris() {
        int rowCount = dataInventaris.getRowCount()-1;
        
        for (int i = rowCount; i >= 0; i--) {
            dataInventaris.removeRow(i);
        }
        for (InventarisKamera IK : ListInventarisKamera) {
            Object []kolom = {
                       IK.getId(),
                       IK.getTipe(),
                       IK.getMerk(),
                       IK.getJenis(),
                       IK.getHarga(),
                       IK.getWarna(),
                       IK.getString_warna(),
                       IK.getPemilik()};
            dataInventaris.addRow(kolom);
        }

        dataInventaris.fireTableDataChanged();
    }
    
    public void connectDatabase(){
        tblInventarisKamera.setModel(dataInventaris);
        Statement st;
        ResultSet rs;
        String viewJenisKamera = "select * from V_InventarisKamera order by id";
           try {
               st = new Koneksi().getCon().createStatement();
               rs = st.executeQuery(viewJenisKamera);
               while(rs.next()){
                   InventarisKamera IK = new InventarisKamera(
                           rs.getInt("id"), 
                           rs.getInt("id_tipe"), 
                           rs.getString("tipe"),
                           rs.getInt("id_merk"), 
                           rs.getString("merk"),
                           rs.getInt("id_jenis"), 
                           rs.getString("jenis"),
                           rs.getInt("harga"),
                           rs.getString("warna"),
                           rs.getString("stringwarna"),
                           rs.getString("pemilik"));
                   
                   //String kolom1 = rs.getString(1);
                   //String kolom2 = rs.getString(2);
                   ListInventarisKamera.add(IK);
                   Object []kolom = {
                       IK.getId(),
                       IK.getTipe(),
                       IK.getMerk(),
                       IK.getJenis(),
                       IK.getHarga(),
                       IK.getWarna(),
                       IK.getString_warna(),
                       IK.getPemilik()};
                   dataInventaris.addRow(kolom);
                   
                   }
               
               dataInventaris.fireTableDataChanged();
           }catch (Exception e){
                JOptionPane.showMessageDialog(null,"error :"+e.getMessage());
                e.printStackTrace();
                }        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblInventarisKamera = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1024, 768));

        jPanel1.setBackground(new java.awt.Color(254, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 104, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 232, 232));

        tblInventarisKamera.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        tblInventarisKamera.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblInventarisKameraMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblInventarisKameraMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblInventarisKamera);

        jLabel1.setText("Seaech");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblInventarisKameraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblInventarisKameraMouseClicked
        
    }//GEN-LAST:event_tblInventarisKameraMouseClicked

    private void tblInventarisKameraMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblInventarisKameraMousePressed
        try {
        } catch (Exception e) {
        }    }//GEN-LAST:event_tblInventarisKameraMousePressed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewInventarisKamera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewInventarisKamera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewInventarisKamera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewInventarisKamera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FormPeminjamanKamera.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(FormPeminjamanKamera.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FormPeminjamanKamera.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(FormPeminjamanKamera.class.getName()).log(Level.SEVERE, null, ex);
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewInventarisKamera().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tblInventarisKamera;
    // End of variables declaration//GEN-END:variables

}