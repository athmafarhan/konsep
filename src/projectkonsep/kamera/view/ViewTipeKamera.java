/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectkonsep.kamera.view;

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
import projectkonsep.kamera.JenisKamera;
import projectkonsep.kamera.MerkKamera;
import projectkonsep.kamera.TipeKamera;

/**
 *
 * @author Athma Farhan
 */
public class ViewTipeKamera extends javax.swing.JFrame {
    LinkedHashMap<String, Integer> hashTipe = new LinkedHashMap<>();
    
    Collection<String> keyTipe;
    
    
    LinkedHashMap<String, Integer> hashMerk = new LinkedHashMap<>();
    
    Collection<String> keyMerk;
    Integer keyMerkId;
    
    LinkedHashMap<String, Integer> hashMerk2 = new LinkedHashMap<>();
    
    Collection<String> keyMerk2;
    Integer keyMerkId2;
    
    Object headerTipe[]={"ID", "Nama Tipe"};
    Object headerMerk[]={"ID", "Tipe", "Nama Merk"};
    Object[] headerJenis = {"ID", "ID Tipe", "ID Merk", "Nama Jenis"};
    
    DefaultTableModel dataTipe = new DefaultTableModel(null, headerTipe);
    DefaultTableModel dataMerk = new DefaultTableModel(null, headerMerk);
    DefaultTableModel dataJenis = new DefaultTableModel(null, headerJenis);
    
    Connection con1 = null;
    Connection con2 = null;
    Connection con3 = null;
    
    PreparedStatement myPreparedStatement = null;

    String[] title1 = {"ID", "Nama Tipe"};
    int index = -1;
    ArrayList<TipeKamera> listTipeKamera = new ArrayList<>();
    ArrayList<MerkKamera> listMerkKamera = new ArrayList<>();
    ArrayList<JenisKamera> listJenisKamera = new ArrayList<>();
    /**
     * Creates new form FormTipeKamera
     */
    public ViewTipeKamera() {
        initComponents();
        getDatabaseTipe();
        getDatabaseMerk();
        getDatabaseJenis();
        //tampil();
        //connectDatabaseTipe();
        //updateTableTipe();
        //connectDatabaseMerk();
        //updateTableMerk();
    }

    public void getDatabaseTipe() {
        Object header[]={"ID", "Nama Tipe"};
        //DefaultTableModel dataTipe = new DefaultTableModel(null, header);
        tblTipeKamera.setModel(dataTipe);
        Statement st;
        ResultSet rs;
        String sql = "select * from km_tipe_kamera";
           try {
               st = new Koneksi().getCon().createStatement();
               rs = st.executeQuery(sql);
               while(rs.next()){
                   TipeKamera TK = new TipeKamera(rs.getInt(1), rs.getString(2));
                   listTipeKamera.add(TK);
                   Object []kolom = {TK.getId(), TK.getNama()};
                   dataTipe.addRow(kolom);
                   //System.out.println(listTipeKamera.size());
                   }
               
               dataTipe.fireTableDataChanged();
           }catch (Exception e){
                JOptionPane.showMessageDialog(null,"error :"+e.getMessage());
                } 
            }
    
    public void getDatabaseMerk() {
        //DefaultTableModel dataMerk = new DefaultTableModel(null, header);
        tblMerkKamera.setModel(dataMerk);
        Statement st;
        ResultSet rs;
        String sql = "select * from V_MerkKamera";
           try {
               st = new Koneksi().getCon().createStatement();
               rs = st.executeQuery(sql);
               while(rs.next()){
                   MerkKamera MK = new MerkKamera(
                           rs.getInt("id"), 
                           rs.getInt("id_tipe"), 
                           rs.getString("tipe"),
                           rs.getString("nama"));
                   //String kolom1 = rs.getString(1);
                   //String kolom2 = rs.getString(2);
                   listMerkKamera.add(MK);
                   Object []kolom = {MK.getId(), MK.getTipe(), MK.getNama()};
                   dataMerk.addRow(kolom);
                   
                   }
               
               dataMerk.fireTableDataChanged();
           }catch (Exception e){
                JOptionPane.showMessageDialog(null,"error :"+e.getMessage());
                e.printStackTrace();
                } 
           listMerkKamera.forEach((MK) -> {
            hashMerk.put(MK.getNama(), MK.getId());
        });
            }
    
    public void getDatabaseJenis() {
        tblMerkJenis.setModel(dataJenis);
        Statement st;
        ResultSet rs;
        String sql = "select * from V_JenisKamera";
           try {
               st = new Koneksi().getCon().createStatement();
               rs = st.executeQuery(sql);
               while(rs.next()){
                   JenisKamera JK = new JenisKamera(
                           rs.getInt("id"), 
                           rs.getInt("id_tipe"), 
                           rs.getString("tipe"),
                           rs.getInt("id_merk"), 
                           rs.getString("merk"),
                           rs.getString("nama"));
                   //String kolom1 = rs.getString(1);
                   //String kolom2 = rs.getString(2);
                   listJenisKamera.add(JK);
                   Object []kolom = {JK.getId(), JK.getTipe(), JK.getMerk(), JK.getNama()};
                   dataJenis.addRow(kolom);
                   
                   }
               
               dataJenis.fireTableDataChanged();
           }catch (Exception e){
                JOptionPane.showMessageDialog(null,"error :"+e.getMessage());
                e.printStackTrace();
                } 
        
            }
    
    public void updateTableTipe() {
        int rowCount = dataTipe.getRowCount()-1;
        
        for (int i = rowCount; i >= 0; i--) {
            dataTipe.removeRow(i);
        }
        for (TipeKamera TK : listTipeKamera) {
            Object []kolom = {TK.getId(), TK.getNama()};
            dataTipe.addRow(kolom);
        }

        dataTipe.fireTableDataChanged();
    }
    
    
    
    public void updateTableMerk() {
        int rowCount = dataMerk.getRowCount()-1;
        
        for (int i = rowCount; i >= 0; i--) {
            dataMerk.removeRow(i);
        }
        for (MerkKamera MK : listMerkKamera) {
            Object []kolom = {MK.getId(), MK.getTipe(), MK.getNama()};
            dataMerk.addRow(kolom);
        }

        dataMerk.fireTableDataChanged();
    }
    
    public void updateTableJenis() {
        int rowCount = dataJenis.getRowCount()-1;
        
        for (int i = rowCount; i >= 0; i--) {
            dataJenis.removeRow(i);
        }
        for (JenisKamera JK : listJenisKamera) {
            Object []kolom = {JK.getId(), JK.getTipe(), JK.getMerk(), JK.getNama()};
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

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTipeKamera = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMerkKamera = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblMerkJenis = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1024, 768));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

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

        jPanel4.setBackground(new java.awt.Color(255, 232, 232));

        tblTipeKamera.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblTipeKamera.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblTipeKamera);

        jLabel1.setText("Tipe Kamera");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(jLabel1)
                .addGap(328, 328, 328)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(jLabel1)
                .addContainerGap(122, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 232, 232));

        tblMerkKamera.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblMerkKamera.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblMerkKamera);

        jLabel2.setText("Merk Kamera");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jLabel2)
                .addGap(293, 293, 293)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(jLabel2)
                .addContainerGap(120, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 232, 232));

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

        jLabel3.setText("Jenis Kamera");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jLabel3)
                .addGap(292, 292, 292)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jLabel3)
                .addContainerGap(95, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            Logger.getLogger(ViewTipeKamera.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ViewTipeKamera.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ViewTipeKamera.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ViewTipeKamera.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewTipeKamera().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblMerkJenis;
    private javax.swing.JTable tblMerkKamera;
    private javax.swing.JTable tblTipeKamera;
    // End of variables declaration//GEN-END:variables
}
