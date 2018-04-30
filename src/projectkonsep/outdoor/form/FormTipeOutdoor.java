/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectkonsep.outdoor.form;

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
public class FormTipeOutdoor extends javax.swing.JFrame {
    
    
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
    public FormTipeOutdoor() {
        initComponents();
//        getDatabaseTipe();
        getDatabaseMerk();
        getDatabaseJenis();
//        initComboBox1();
        initComboBox3();
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
    
    
    public void initComboBox2() {
        
        
        keyMerk = hashMerk.keySet();
        
        for (String next : keyMerk) {
            int tempIDTipe = hashMerk.get(next);
            if (listMerkOutdoor.get(0).getId()==0) {
                
                jComboBox2.addItem(next);
                
            }
        }
        
        
        
        
    }
    
    public void initComboBox3() {
        jComboBox2.removeAllItems();
        for (MerkOutdoor TK : listMerkOutdoor){
            hashMerk2.put(TK.getNama(), TK.getId());
        }
        
        keyMerk2 = hashMerk2.keySet();
        
        for (MerkOutdoor TK : listMerkOutdoor){
            jComboBox2.addItem(TK.getNama());
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

        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtMerkNama = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMerkOutdoor = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtMerkID = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        txtJenisID = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtJenisNama = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblMerkJenis = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();

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

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel4.setText("Nama Merk");

        txtMerkNama.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtMerkNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMerkNamaActionPerformed(evt);
            }
        });

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

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel3.setText("ID Merk");

        txtMerkID.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtMerkID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMerkIDActionPerformed(evt);
            }
        });

        jButton6.setText("Masukkan");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton11.setText("Hapus");

        jButton10.setText("Edit");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton12.setText("New");

        jButton13.setText("Refresh");

        txtJenisID.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtJenisID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJenisIDActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel5.setText("ID Jenis");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel7.setText("ID Merk");

        jComboBox2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel6.setText("Nama Jenis");

        txtJenisNama.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtJenisNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJenisNamaActionPerformed(evt);
            }
        });

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

        jButton7.setText("Masukkan");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton14.setText("Edit");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setText("Hapus");

        jButton16.setText("New");

        jButton17.setText("Refresh");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMerkID, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMerkNama, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtJenisID)
                            .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtJenisNama, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(102, 102, 102)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
                        .addGap(2, 2, 2))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton13))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton17)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtMerkID, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtMerkNama, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton6)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton10)
                                .addComponent(jButton11)
                                .addComponent(jButton12)
                                .addComponent(jButton13)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtJenisNama, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtJenisID, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton7)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton14)
                                .addComponent(jButton15)
                                .addComponent(jButton16)
                                .addComponent(jButton17)))))
                .addGap(83, 83, 83))
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

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed

    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void txtJenisNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJenisNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJenisNamaActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

    }//GEN-LAST:event_jButton7ActionPerformed

    private void txtJenisIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJenisIDActionPerformed
        boolean berhasil = false;
        int field1 = 0;


        String tempIDMerk = (String)jComboBox2.getSelectedItem();
        Integer field3 = hashMerk.get(tempIDMerk);

        String field4 = txtJenisNama.getText();

        try {
            field1 = Integer.parseInt(txtJenisID.getText());
        } catch (NumberFormatException e) {
            berhasil = false;
            JOptionPane.showMessageDialog(this,"Data yang Anda Masukkan Salah", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
        }

        try {
            Connection con = new Koneksi().getCon();

            String sqlinput = "INSERT INTO `jenis_kamera`"
            + "(`id`, `id_merk`, `nama`)"
            + " VALUES (?, ?, ?)";
            myPreparedStatement = con.prepareStatement(sqlinput);
            myPreparedStatement.setInt(1, field1);
            myPreparedStatement.setInt(3, field3);
            myPreparedStatement.setString(4, field4);

            berhasil = true;
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(this,"Penambahan Gagal", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
            berhasil = false;
            e.printStackTrace();
        }
        if (berhasil == true) {
            try {
                myPreparedStatement.executeUpdate();
            } catch (SQLException ex) {
            }
            JenisOutdoor newJO = new JenisOutdoor();
            newJO.setId(field1);
            newJO.setId_merk(field3);
            newJO.setNama(field4);
            this.listJenisOutdoor.add(newJO);
            JOptionPane.showMessageDialog(this,"Penambahan Sukses", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
            txtJenisID.setText("");
            jComboBox2.setSelectedIndex(0);
            txtJenisNama.setText("");
            //update();
        }
        else if (berhasil == false) {
            JOptionPane.showMessageDialog(this,"Penambahan Gagal", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
        }
        //data.addRow(kolom);
        //tampil();
    }//GEN-LAST:event_txtJenisIDActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        boolean berhasil = false;
        int field1 = 0;
        String field3 = txtMerkNama.getText();

        
        try {
            field1 = Integer.parseInt(txtMerkID.getText());
        } catch (NumberFormatException e) {
            //berhasil = false;
            JOptionPane.showMessageDialog(this,"Data yang Anda Masukkan Salah", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
        }

        try {
            Connection con = new Koneksi().getCon();

            String sqlinput = "INSERT INTO `merk_kamera`"
            + "(`id`, `nama`)"
            + " VALUES (?, ?, ?)";
            myPreparedStatement = con.prepareStatement(sqlinput);
            myPreparedStatement.setInt(1, field1);
            myPreparedStatement.setString(3, field3);

            berhasil = true;
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(this,"Penambahan Gagal", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
            berhasil = false;
            e.printStackTrace();
        }
        if (berhasil == true) {
            try {
                myPreparedStatement.executeUpdate();
            } catch (SQLException ex) {
            }
            MerkOutdoor newMO = new MerkOutdoor();
            newMO.setId(field1);
            newMO.setNama(field3);
            this.listMerkOutdoor.add(newMO);
            JOptionPane.showMessageDialog(this,"Penambahan Sukses", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
            txtMerkID.setText("");
            txtMerkNama.setText("");
            updateTableMerk();
            jComboBox2.removeAllItems();
            initComboBox2();
        }
        else if (berhasil == false) {
            JOptionPane.showMessageDialog(this,"Penambahan Gagal", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
        }
        //data.addRow(kolom);
        //tampil();

    }//GEN-LAST:event_jButton6ActionPerformed

    private void txtMerkIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMerkIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMerkIDActionPerformed

    private void txtMerkNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMerkNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMerkNamaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FormTipeOutdoor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(FormTipeOutdoor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FormTipeOutdoor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(FormTipeOutdoor.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormTipeOutdoor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblMerkJenis;
    private javax.swing.JTable tblMerkOutdoor;
    private javax.swing.JTextField txtJenisID;
    private javax.swing.JTextField txtJenisNama;
    private javax.swing.JTextField txtMerkID;
    private javax.swing.JTextField txtMerkNama;
    // End of variables declaration//GEN-END:variables
}
