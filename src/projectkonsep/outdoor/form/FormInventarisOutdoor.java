/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectkonsep.outdoor.form;

import projectkonsep.kamera.*;
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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import projectkonsep.Item;
import projectkonsep.Koneksi;
import projectkonsep.menu.MenuInventarisOutdoor;
import projectkonsep.outdoor.InventarisOutdoor;
import projectkonsep.outdoor.JenisOutdoor;
import projectkonsep.outdoor.MerkOutdoor;

/**
 *
 * @author Athma Farhan
 */
public class FormInventarisOutdoor extends javax.swing.JFrame {
    LinkedHashMap<String, Integer> hashJenis = new LinkedHashMap<>();
    Collection<String> keyJenis;
    
    LinkedHashMap<String, Integer> hashMerk = new LinkedHashMap<>();
    Collection<String> keyMerk;
    
    LinkedHashMap<String, Integer> hashPemilik = new LinkedHashMap<>();
    Collection<String> keyPemilik;

    ArrayList<JenisOutdoor> ListJO = new ArrayList<>();
    ArrayList<MerkOutdoor> ListMO = new ArrayList<>();
    
    String warnaRGB;
    Connection con = null;
    PreparedStatement myPreparedStatement = null;
    Object[] headerInventaris = {"ID", "Merk", "Jenis", "Harga", "Warna", "Warna", "Pemilik"};
    DefaultTableModel dataInventaris = new DefaultTableModel(null, headerInventaris);
    int index = -1;
    ArrayList<InventarisOutdoor> ListInventarisOutdoor = new ArrayList<>();//

    ArrayList<Item<MerkOutdoor>> listItemMerkOutdoor = new ArrayList<>();
    
    ArrayList<Item<JenisOutdoor>> listItemJenisOutdoor = new ArrayList<>();
    
    /**
     * Creates new form FormCustomerOutdoor
     */
    public FormInventarisOutdoor() {
        initComponents();
        initCombo2();
        initCombo1();
        initCombo3();
        connectDatabase();
        jComboBox2.setSelectedIndex(0);
    }
    
    private void initCombo1() {
        Connection con1 = new Koneksi().getCon();
        Statement stmt1;
        String sql = "SELECT * FROM ou_jenis_outdoor";    
        try {
            stmt1 = con1.createStatement();
            ResultSet cb1 = stmt1.executeQuery(sql);
            
            while (cb1.next()) {   
                JenisOutdoor newJO = new JenisOutdoor(
                        cb1.getInt("id"),
                        cb1.getInt("id_merk"), 
                        cb1.getString("nama"));
                ListJO.add(newJO);
                //jComboBox1.addItem(cb1.getString("nama"));
            }
        } catch (SQLException ex) {
        }
        updateCombo1();
    }
    
    private void initCombo2() {
        Connection con1 = new Koneksi().getCon();
        Statement stmt1;
        String sql = "SELECT * FROM ou_merk_outdoor";    
        try {
            stmt1 = con1.createStatement();
            ResultSet cb1 = stmt1.executeQuery(sql);
            
            while (cb1.next()) {   
                MerkOutdoor newMO = new MerkOutdoor(
                        cb1.getInt("id"),
                        cb1.getString("nama"));
                ListMO.add(newMO);
                //jComboBox1.addItem(cb1.getString("nama"));
                
            }
        } catch (SQLException ex) {
        }
       updateCombo2();
    }
    
    private void initCombo3() {
        jComboBox3.removeAllItems();
        Connection con1 = new Koneksi().getCon();
        Statement stmt1;
        String sql = "SELECT * FROM `owner`";    
        try {
            stmt1 = con1.createStatement();
            ResultSet cb1 = stmt1.executeQuery(sql);
            
            while (cb1.next()) {  
                Integer id = cb1.getInt("id");
                String nama = cb1.getString("nama");
                
                hashPemilik.put(nama, id);
                jComboBox3.addItem(nama);
                //jComboBox1.addItem(cb1.getString("nama"));
                
            }
            keyPemilik = hashPemilik.keySet();
        } catch (SQLException ex) {
        }
    }
    
    
    private void updateCombo1() {
        int indexComboBox = jComboBox2.getSelectedIndex()+1;

        jComboBox1.removeAllItems();
        ListJO.forEach((JO) -> {
//            hashJenis.put(JK.getNama(), JK.getId());
        Item iJO = new Item(JO,  
                        JO.getId_merk(), 
                        JO.getId(), 
                        JO.getNama());
            listItemJenisOutdoor.add(iJO);
            jComboBox1.addItem(iJO);
        });
    }
    
    private void updateCombo2() {
        jComboBox2.removeAllItems();
        ListMO.forEach((MO) -> {
//            hashMerk.put(MK.getNama(), MK.getId());
            Item iMO = new Item(MO, MO.getId(), MO.getNama());
            listItemMerkOutdoor.add(iMO);
            jComboBox2.addItem(iMO);
        });
        
    }
    
    

    
    
    
//    private void updateCombo3() {
//        jComboBox3.removeAllItems();
//        ListTO.forEach((TO) -> {
//            hashTipe.put(TO.getNama(), TO.getId());
//        });
//        
//        keyTipe = hashTipe.keySet();
//        
//        for (String next : keyTipe) {
//            //int tempNext = hashJenis.get(next);
//            jComboBox3.addItem(next);
//        }
//    }
    
    public void updateTableInventaris() {
        int rowCount = dataInventaris.getRowCount()-1;
        
        for (int i = rowCount; i >= 0; i--) {
            dataInventaris.removeRow(i);
        }
        for (InventarisOutdoor IO : ListInventarisOutdoor) {
            Object []kolom = {IO.getId(), IO.getMerk(), IO.getJenis(), IO.getHarga(), IO.getWarna(), IO.getString_warna(), IO.getPemilik()};
            dataInventaris.addRow(kolom);
        }

        dataInventaris.fireTableDataChanged();
    }
    
    public void connectDatabase(){
        tblInventarisOutdoor.setModel(dataInventaris);
        Statement st;
        ResultSet rs;
        String viewJenisOutdoor = "select * from V_InventarisOutdoor";
           try {
               st = new Koneksi().getCon().createStatement();
               rs = st.executeQuery(viewJenisOutdoor);
               while(rs.next()){
                   InventarisOutdoor IO = new InventarisOutdoor(
                           rs.getInt("id"), 
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
                   ListInventarisOutdoor.add(IO);
                   Object []kolom = {IO.getId(), IO.getMerk(), IO.getJenis(), IO.getHarga(), IO.getWarna(), IO.getString_warna(), IO.getPemilik()};
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
        jLabel1 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtHarga = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblInventarisOutdoor = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        lblWarna = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        panelColor = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<Item<JenisOutdoor>>();
        jComboBox2 = new javax.swing.JComboBox<Item<MerkOutdoor>>();
        jLabel6 = new javax.swing.JLabel();
        txtStringWarna = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jButton11 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1024, 768));

        jPanel1.setBackground(new java.awt.Color(254, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1024, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 104, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 232, 232));

        jLabel1.setText("ID Inventaris");

        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });

        jLabel2.setText("Merk");

        jLabel3.setText("Jenis");

        jLabel4.setText("Harga");

        tblInventarisOutdoor.setModel(new javax.swing.table.DefaultTableModel(
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
        tblInventarisOutdoor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblInventarisOutdoorMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblInventarisOutdoorMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblInventarisOutdoor);

        jButton1.setText("Masukkan");
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText(">|");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText(">>");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("<<");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("|<");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Edit");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        lblWarna.setText("Warna");

        jButton7.setText("Warna");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        panelColor.setBackground(new java.awt.Color(255, 255, 255));
        panelColor.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout panelColorLayout = new javax.swing.GroupLayout(panelColor);
        panelColor.setLayout(panelColorLayout);
        panelColorLayout.setHorizontalGroup(
            panelColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 85, Short.MAX_VALUE)
        );
        panelColorLayout.setVerticalGroup(
            panelColorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 25, Short.MAX_VALUE)
        );

        jButton8.setText("Delete");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new DefaultComboBoxModel<Item<JenisOutdoor>>());
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new DefaultComboBoxModel<Item<MerkOutdoor>>());
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel6.setText("Warna");

        jLabel5.setText("Pemilik");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton11.setText("New");
        jButton11.setBorder(null);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton10.setText("Reset");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton9.setText("KEMBALI");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(lblWarna)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtID)
                            .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtHarga)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(panelColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton7))
                            .addComponent(txtStringWarna)
                            .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton9))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel2)
                                .addGap(24, 24, 24)
                                .addComponent(jLabel3)
                                .addGap(24, 24, 24)
                                .addComponent(jLabel4)
                                .addGap(30, 30, 30)
                                .addComponent(lblWarna)
                                .addGap(21, 21, 21)
                                .addComponent(jLabel6))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(panelColor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton7))
                                .addGap(18, 18, 18)
                                .addComponent(txtStringWarna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 601, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5)
                    .addComponent(jButton4)
                    .addComponent(jButton3)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton6)
                        .addComponent(jButton8)
                        .addComponent(jButton9))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton10))
                    .addComponent(jButton2))
                .addContainerGap())
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

    private void txtIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Item itemMO = (Item)jComboBox2.getSelectedItem();
        MerkOutdoor MO = (MerkOutdoor)itemMO.getValue();
        
        Item itemJO = (Item)jComboBox1.getSelectedItem();
        JenisOutdoor JO = (JenisOutdoor)itemJO.getValue();
        
        String tempPemilik = (String) jComboBox3.getSelectedItem();
        int pemilik = hashPemilik.get(tempPemilik);
        
        boolean berhasil = false;
        int field1 = 0;
        String field3 = MO.getNama();
        int field3x = MO.getId();
        String field4 = JO.getNama();
        int field4x = JO.getId();
        
        int field5 = 0;
        String field6 = warnaRGB;
        String field7 = txtStringWarna.getText();
        int field8 = pemilik;

        try {
            field1 = Integer.parseInt(txtID.getText());
            field5 = Integer.parseInt(txtHarga.getText());
        } catch (NumberFormatException e) {
            berhasil = false;
            JOptionPane.showMessageDialog(this,"Data yang Anda Masukkan Salah", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
        }

        try {
            Connection con = new Koneksi().getCon();
            
            String sqlinput = "INSERT INTO `ou_inventaris`"
            + "(`id`, `merk`, `jenis`, `harga`, `warna`, `stringwarna`, `pemilik`)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            myPreparedStatement = con.prepareStatement(sqlinput);
            myPreparedStatement.setInt(1, field1);
            myPreparedStatement.setInt(2, field3x);
            myPreparedStatement.setInt(3, field4x);
            myPreparedStatement.setInt(4, field5);
            myPreparedStatement.setString(5, field6);
            myPreparedStatement.setString(6, field7);
            myPreparedStatement.setInt(7, field8);
            
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
                ex.printStackTrace();
            }
            InventarisOutdoor newIK = new InventarisOutdoor();
            newIK.setId(Integer.parseInt(txtID.getText()));
            newIK.setId_merk(field3x);
            newIK.setMerk(field3);
            newIK.setId_jenis(field4x);
            newIK.setJenis(field4);
            newIK.setHarga(Integer.parseInt(txtHarga.getText()));
            newIK.setWarna(warnaRGB);
            newIK.setString_warna(field7);
            newIK.setPemilik(tempPemilik);
            this.ListInventarisOutdoor.add(newIK);
            JOptionPane.showMessageDialog(this,"Penambahan Sukses", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
            Object []kolom = {newIK.getId(), newIK.getMerk(), newIK.getJenis(), newIK.getHarga(), newIK.getWarna()};
            dataInventaris.addRow(kolom);
            
        }
        else if (berhasil == false) {
            JOptionPane.showMessageDialog(this,"Penambahan Gagal", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tblInventarisOutdoorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblInventarisOutdoorMouseClicked
        
    }//GEN-LAST:event_tblInventarisOutdoorMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        index = 0;
        showData();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       index = ListInventarisOutdoor.size()-1;
       showData();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            ++index;
            showData();
        
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            JOptionPane.showMessageDialog(this,"Data yang dicari tidak ditemukan", "Informasi", 
                JOptionPane.INFORMATION_MESSAGE);
            index = ListInventarisOutdoor.size()-1;
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            --index;
            showData();
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            JOptionPane.showMessageDialog(this,"Data yang dicari tidak ditemukan", "Informasi", 
                JOptionPane.INFORMATION_MESSAGE);
            index = 0;
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        Item itemMO = (Item)jComboBox2.getSelectedItem();
        MerkOutdoor MO = (MerkOutdoor)itemMO.getValue();
        
        Item itemJO = (Item)jComboBox1.getSelectedItem();
        JenisOutdoor JO = (JenisOutdoor)itemJO.getValue();
        
        String tempPemilik = (String) jComboBox3.getSelectedItem();
        int pemilik = hashPemilik.get(tempPemilik);
        
        boolean berhasil = false;
        
        String field2 = (String) jComboBox2.getSelectedItem();
        int field2x = MO.getId();
        String field3 = (String) jComboBox1.getSelectedItem();
        int field3x = JO.getId();
        
        int field4 = 0;
        String field5 = warnaRGB;
        String field6 = txtStringWarna.getText();
        int field7 = pemilik;
        
        int field8 = 0;

        
        
        if (JOptionPane.showConfirmDialog(this, "Yakin akan diedit?", "Edit", 0) == 0) {
            String ketikKONFIRMASI = JOptionPane.showInputDialog(this,
                        "Ketik KONFIRMASI untuk mengedit data", null);
            String key = "KONFIRMASI";
            
            if (ketikKONFIRMASI.equals(key)) {
                try {
            field8 = Integer.parseInt(txtID.getText());
            field4 = Integer.parseInt(txtHarga.getText());
        } catch (NumberFormatException e) {
            berhasil = false;
            JOptionPane.showMessageDialog(this,"Data yang Anda Masukkan Salah", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
        }

        try {
            Connection con = new Koneksi().getCon();
            myPreparedStatement = null;
            String sqlupdate = "UPDATE `ou_inventaris` "
                    + "`merk`=?"
                    + "`jenis`=?,"
                    + "`harga`=?,"
                    + "`warna`=?,"
                    + "`stringwarna`=?,"
                    + "`pemilik`=? "
                    + "WHERE `id`=?";
            myPreparedStatement = con.prepareStatement(sqlupdate);
            myPreparedStatement.setInt(1, field2x);
            myPreparedStatement.setInt(2, field3x);
            myPreparedStatement.setInt(3, field4);
            myPreparedStatement.setString(4, field5);
            myPreparedStatement.setString(5, field6);
            myPreparedStatement.setInt(6, field7);
            myPreparedStatement.setInt(7, field8);
            berhasil = true;
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(this,"Penambahan Gagal", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
            berhasil = false;
            e.printStackTrace();
        }
        if (berhasil == true) {
            
                    try {
                        myPreparedStatement.executeUpdate();
                    } catch (SQLException ex) {
                        Logger.getLogger(FormInventarisOutdoor.class.getName()).log(Level.SEVERE, null, ex);
                    }
            InventarisOutdoor newIO = ListInventarisOutdoor.get(index);
            newIO.setId_merk(field2x);
            newIO.setMerk(field2);
            newIO.setId_jenis(field3x);
            newIO.setJenis(field3);
            newIO.setHarga(Integer.parseInt(txtHarga.getText()));
            newIO.setWarna(warnaRGB);
            newIO.setString_warna(field6);
            newIO.setPemilik(tempPemilik);
            this.ListInventarisOutdoor.set(index, newIO);
            JOptionPane.showMessageDialog(this,"Penambahan Sukses", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
            updateTableInventaris();
        }
        else if (berhasil == false) {
            JOptionPane.showMessageDialog(this,"Penambahan Gagal", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
        }
            }
            else if (!ketikKONFIRMASI.equals(key)) {
                JOptionPane.showMessageDialog(this, "Data tidak berhasil diedit", "Informasi", 1);
            }
            
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        Color c = JColorChooser.showDialog(null, "Choose a Color", panelColor.getBackground());
        if (c != null){
            panelColor.setBackground(c);
        }
        int intRed = c.getRed();
        int intGreen = c.getGreen();
        int intBlue = c.getBlue();
        String stringRed = String.valueOf(intRed);
        String stringGreen = String.valueOf(intGreen);
        String stringBlue = String.valueOf(intBlue);
        
        if (intRed <=9){
            stringRed = "00"+stringRed;
        }
        else if (intRed <=99) {
            stringRed = "0"+stringRed;
        }
        if (intGreen <=9){
            stringGreen = "00"+stringGreen;
        }
        else if (intGreen <=99) {
            stringGreen = "0"+stringGreen;
        }
        if (intBlue <=9){
            stringBlue = "00"+stringBlue;
        }
        else if (intBlue <=99) {
            stringBlue = "0"+stringBlue;
        }
        warnaRGB = stringRed+stringGreen+stringBlue;
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        boolean berhasil = false;
        if (JOptionPane.showConfirmDialog(this, "Yakin akan dihapus?", "Hapus", 0) == 0) {
            String ketikKONFIRMASI = JOptionPane.showInputDialog(this,
                        "Ketik KONFIRMASI untuk menghapus data", null);
            String key = "KONFIRMASI";
            
            if (ketikKONFIRMASI.equals(key)) {
                con = new Koneksi().getCon();
                String sqldelete = "DELETE FROM ou_inventaris WHERE `id` =?";
                try {
                    int field1 = Integer.parseInt(txtID.getText());
                    myPreparedStatement = con.prepareStatement(sqldelete);
                    myPreparedStatement.setInt(1, field1);
                    berhasil = true;
                }
                catch (SQLException errorSQL) {
                    errorSQL.printStackTrace();
                    berhasil = false;
                    JOptionPane.showMessageDialog(this, "Data tidak berhasil dihapus", "Informasi", 1);
                }
                catch (NumberFormatException | NullPointerException e) {
                    berhasil = false;
                    JOptionPane.showMessageDialog(this, "Pilih data yang akan dihapus!", "Informasi", 1);
                }
                if (berhasil == true) {
                    try {
                        myPreparedStatement.executeUpdate();
                    } catch (SQLException ex) {
                        Logger.getLogger(FormInventarisOutdoor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    ListInventarisOutdoor.remove(index);
                    JOptionPane.showMessageDialog(this, "Data berhasil dihapus!", "Informasi", 1);
                    updateTableInventaris();
                }
            }
            else if (berhasil == false) {
                JOptionPane.showMessageDialog(this, "Data tidak berhasil dihapus", "Informasi", 1);
            }
            
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        try {
            Item itemMO = (Item)jComboBox2.getSelectedItem();
            MerkOutdoor MO = (MerkOutdoor)itemMO.getValue();
            
//            IDJenis = hashMerk.get(tempIDMerk);
            jComboBox1.removeAllItems();
            for (Item LJO : listItemJenisOutdoor) {
            if (MO.getId()==LJO.getId_tipe()) {
                jComboBox1.addItem(LJO);
            }
        }
        } catch (NullPointerException e) {
            //e.printStackTrace();
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void tblInventarisOutdoorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblInventarisOutdoorMousePressed
        try {
            int rowIndex = tblInventarisOutdoor.getSelectedRow();
            index = rowIndex;
            showData();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,"Penambahan Gagal", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_tblInventarisOutdoorMousePressed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        int id = ListInventarisOutdoor.get(ListInventarisOutdoor.size()-1).getId()+1;
        txtID.setText(String.valueOf(id));
        jComboBox2.setSelectedIndex(0);
        txtHarga.setText("");
        panelColor.setBackground(Color.white);
        warnaRGB="255255255";
        txtStringWarna.setText("");
        jComboBox3.setSelectedIndex(0);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        jComboBox2.setSelectedIndex(0);
        txtHarga.setText("");
        panelColor.setBackground(Color.white);
        warnaRGB="255255255";
        txtStringWarna.setText("");
        jComboBox3.setSelectedIndex(0);
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        this.dispose();
        java.awt.EventQueue.invokeLater(() -> {//FormBooking
            new MenuInventarisOutdoor().setVisible(true);
        });
    }//GEN-LAST:event_jButton9ActionPerformed

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
            java.util.logging.Logger.getLogger(FormInventarisOutdoor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormInventarisOutdoor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormInventarisOutdoor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormInventarisOutdoor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
            Logger.getLogger(FormInventarisOutdoor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(FormInventarisOutdoor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FormInventarisOutdoor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(FormInventarisOutdoor.class.getName()).log(Level.SEVERE, null, ex);
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormInventarisOutdoor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<Item<JenisOutdoor>> jComboBox1;
    private javax.swing.JComboBox<Item<MerkOutdoor>> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblWarna;
    private javax.swing.JPanel panelColor;
    private javax.swing.JTable tblInventarisOutdoor;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtStringWarna;
    // End of variables declaration//GEN-END:variables

    private void showData() {
        InventarisOutdoor IO = ListInventarisOutdoor.get(index);
        txtID.setText(String.valueOf(IO.getId()));
        
        MerkOutdoor MO = new MerkOutdoor(IO.getId_merk(), IO.getMerk());
        Item itemMerkOutdoor = new Item<>(MO, IO.getId_merk(), IO.getMerk());
        
        for (Item LMO : listItemMerkOutdoor) {
            if (LMO.getId_tipe()==(itemMerkOutdoor.getId_tipe())) {
                //System.out.println("Tes MO " +LMO.getId_merk()+" "+itemMerkOutdoor.getId_merk());
                jComboBox2.setSelectedItem(LMO);
                //System.out.println(LMO);
            }
        }
        
        JenisOutdoor JO = new JenisOutdoor(IO.getId_jenis(), IO.getId_merk(), IO.getMerk(), IO.getJenis());
        Item itemJenisOutdoor = new Item<>(JO, IO.getId_merk(), IO.getId_jenis(), IO.getJenis());
        for (Item LJO : listItemJenisOutdoor) {
            if (LJO.getId_merk()==(itemJenisOutdoor.getId_merk())) {
                jComboBox1.setSelectedItem(LJO);
                //System.out.println(LJO);
            }
        }
        
        txtHarga.setText(String.valueOf(IO.getHarga()));
        String warnaRGBTemp = IO.getWarna();
        int warnaRedTemp = Integer.parseInt(warnaRGBTemp.substring(0, 3));
        int warnaGreenTemp = Integer.parseInt(warnaRGBTemp.substring(3, 6));
        int warnaBlueTemp = Integer.parseInt(warnaRGBTemp.substring(6, 9));
        Color colorTemp = new Color(warnaRedTemp, warnaGreenTemp, warnaBlueTemp);
        panelColor.setBackground(colorTemp);
        setRGBColor();
        txtStringWarna.setText(IO.getString_warna());
        jComboBox3.setSelectedItem(IO.getPemilik());
    }
    
    private void setRGBColor() {
        int intRed = panelColor.getBackground().getRed();
        int intGreen = panelColor.getBackground().getGreen();
        int intBlue = panelColor.getBackground().getBlue();
        String stringRed = String.valueOf(intRed);
        String stringGreen = String.valueOf(intGreen);
        String stringBlue = String.valueOf(intBlue);
        
        if (intRed <=9){
            stringRed = "00"+stringRed;
        }
        else if (intRed <=99) {
            stringRed = "0"+stringRed;
        }
        if (intGreen <=9){
            stringGreen = "00"+stringGreen;
        }
        else if (intGreen <=99) {
            stringGreen = "0"+stringGreen;
        }
        if (intBlue <=9){
            stringBlue = "00"+stringBlue;
        }
        else if (intBlue <=99) {
            stringBlue = "0"+stringBlue;
        }
        warnaRGB = stringRed+stringGreen+stringBlue;
    
    }
}

