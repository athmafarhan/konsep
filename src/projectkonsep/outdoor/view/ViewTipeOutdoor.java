/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectkonsep.outdoor.view;

import projectkonsep.kamera.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import projectkonsep.Koneksi;
import projectkonsep.outdoor.JenisOutdoor;
import projectkonsep.outdoor.MerkOutdoor;

/**
 *
 * @author Athma Farhan
 */
public class ViewTipeOutdoor extends javax.swing.JFrame {
    
    
    LinkedHashMap<String, Integer> hashMerk = new LinkedHashMap<>();
    
    Collection<String> keyMerk;
    Integer keyMerkId;
    
    LinkedHashMap<String, Integer> hashMerk2 = new LinkedHashMap<>();
    
    Collection<String> keyMerk2;
    Integer keyMerkId2;
    
    Object headerTipe[]={"ID", "Nama Tipe"};
    Object headerMerk[]={"ID", "Nama Merk"};
    Object[] headerJenis = {"ID", "ID Merk", "Nama Jenis"};
    
    DefaultTableModel dataTipe = new DefaultTableModel(null, headerTipe);
    DefaultTableModel dataMerk = new DefaultTableModel(null, headerMerk);
    DefaultTableModel dataJenis = new DefaultTableModel(null, headerJenis);
    
    Connection con1 = null;
    Connection con2 = null;
    Connection con3 = null;
    
    PreparedStatement myPreparedStatement = null;

    String[] title1 = {"ID", "Nama Tipe"};
    int index = -1;
    ArrayList<MerkOutdoor> listMerkOutdoor = new ArrayList<>();
    ArrayList<JenisOutdoor> listJenisOutdoor = new ArrayList<>();
    /**
     * Creates new form FormTipeOutdoor
     */
    public ViewTipeOutdoor() {
        initComponents();
//        getDatabaseTipe();
        getDatabaseMerk();
        getDatabaseJenis();
//        initComboBox1();
//        initComboBox3();
        //tampil();
        //connectDatabaseTipe();
        //updateTableTipe();
        //connectDatabaseMerk();
        //updateTableMerk();
    }
    
    public void getDatabaseMerk() {
        //DefaultTableModel dataMerk = new DefaultTableModel(null, header);
        tblMerkOutdoor.setModel(dataMerk);
        Statement st;
        ResultSet rs;
        String sql = "select * from ou_merk_outdoor";
           try {
               st = new Koneksi().getCon().createStatement();
               rs = st.executeQuery(sql);
               while(rs.next()){
                   MerkOutdoor MO = new MerkOutdoor(
                           rs.getInt("id"), 
                           rs.getString("nama"));
                   //String kolom1 = rs.getString(1);
                   //String kolom2 = rs.getString(2);
                   listMerkOutdoor.add(MO);
                   Object []kolom = {MO.getId(),  MO.getNama()};
                   dataMerk.addRow(kolom);
                   
                   }
               
               dataMerk.fireTableDataChanged();
           }catch (Exception e){
                JOptionPane.showMessageDialog(null,"error :"+e.getMessage());
                e.printStackTrace();
                } 
           listMerkOutdoor.forEach((MO) -> {
            hashMerk.put(MO.getNama(), MO.getId());
        });
            }
    
    public void getDatabaseJenis() {
        tblMerkJenis.setModel(dataJenis);
        Statement st;
        ResultSet rs;
        String sql = "select * from V_JenisOutdoor";
           try {
               st = new Koneksi().getCon().createStatement();
               rs = st.executeQuery(sql);
               while(rs.next()){
                   JenisOutdoor JO = new JenisOutdoor(
                           rs.getInt("id"), 
                           rs.getInt("id_merk"), 
                           rs.getString("merk"), 
                           rs.getString("nama"));
                   //String kolom1 = rs.getString(1);
                   //String kolom2 = rs.getString(2);
                   listJenisOutdoor.add(JO);
                   Object []kolom = {JO.getId(), JO.getId_merk(), JO.getNama()};
                   dataJenis.addRow(kolom);
                   
                   }
               
               dataJenis.fireTableDataChanged();
           }catch (Exception e){
                JOptionPane.showMessageDialog(null,"error :"+e.getMessage());
                e.printStackTrace();
                } 
        
            }
    
    
    
    
    public void updateTableMerk() {
        int rowCount = dataMerk.getRowCount()-1;
        
        for (int i = rowCount; i >= 0; i--) {
            dataMerk.removeRow(i);
        }
        for (MerkOutdoor MO : listMerkOutdoor) {
            Object []kolom = {MO.getId(), MO.getNama()};
            dataMerk.addRow(kolom);
        }

        dataMerk.fireTableDataChanged();
    }
    
    public void updateTableJenis() {
        int rowCount = dataJenis.getRowCount()-1;
        
        for (int i = rowCount; i >= 0; i--) {
            dataJenis.removeRow(i);
        }
        for (JenisOutdoor JO : listJenisOutdoor) {
            Object []kolom = {JO.getId(), JO.getId_merk(), JO.getNama()};
            dataJenis.addRow(kolom);
        }

        dataJenis.fireTableDataChanged();
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMerkOutdoor = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblMerkJenis = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1024, 768));

        jPanel3.setBackground(new java.awt.Color(254, 0, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(255, 232, 232));

        tblMerkOutdoor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblMerkOutdoor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Title 1", "Title 2"
            }
        ));
        jScrollPane2.setViewportView(tblMerkOutdoor);

        tblMerkJenis.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblMerkJenis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        jScrollPane3.setViewportView(tblMerkJenis);

        jLabel1.setText("Merk Outdoor");

        jLabel2.setText("Jenis Outdoor");

        jLabel3.setText("Search");

        jLabel4.setText("Search");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(314, 314, 314)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
                        .addGap(2, 2, 2)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jLabel1)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(35, 35, 35)
                        .addComponent(jLabel4)
                        .addGap(168, 168, 168))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewTipeOutdoor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ViewTipeOutdoor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ViewTipeOutdoor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ViewTipeOutdoor.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewTipeOutdoor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblMerkJenis;
    private javax.swing.JTable tblMerkOutdoor;
    // End of variables declaration//GEN-END:variables
}
