/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectkonsep.finance;

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

/**
 *
 * @author Athma Farhan
 */
public class FormOwner extends javax.swing.JFrame {
    int indexOwner;
    int indexPkamera;
    int indexPOutdoor;
    
    LinkedHashMap<String, Integer> hashOwner = new LinkedHashMap<>();
    
    Collection<String> keyOwner;
    int IDOwner = 0;
    int IDOwner2 = 0;
    
    Object headerOwner[]={"ID", "Nama Tipe"};
    Object headerPKamera[]={"ID Owner","Nama","Persentase"};
    Object headerPOutdoor[]= {"ID Owner","Nama","Persentase"};
    
    DefaultTableModel dataOwner = new DefaultTableModel(null, headerOwner);
    DefaultTableModel dataPKamera = new DefaultTableModel(null, headerPKamera);
    DefaultTableModel dataPOutdoor = new DefaultTableModel(null, headerPOutdoor);
    
    Connection con1 = null;
    Connection con2 = null;
    Connection con3 = null;
    
    PreparedStatement myPreparedStatement = null;

    int index = -1;
    ArrayList<Owner> listOwner = new ArrayList<>();
    ArrayList<PKamera> listPKamera = new ArrayList<>();
    ArrayList<POutdoor> listPOutdoor = new ArrayList<>();
    /**
     * Creates new form FormTipeKamera
     */
    public FormOwner() {
        initComponents();
        getDatabaseOwner();
        getDatabasePKamera();
        getDatabasePOutdoor();
        initComboBox1();
        initComboBox2();
    }

    public void getDatabaseOwner() {
        Object header[]={"ID", "Nama Owner"};
        //DefaultTableModel dataTipe = new DefaultTableModel(null, header);
        tblOwner.setModel(dataOwner);
        Statement st;
        ResultSet rs;
        String sql = "select * from owner";
           try {
               st = new Koneksi().getCon().createStatement();
               rs = st.executeQuery(sql);
               while(rs.next()){
                   Owner OW = new Owner(rs.getInt(1), rs.getString(2));
                   listOwner.add(OW);
                   Object []kolom = {OW.getId(), OW.getOwner()};
                   dataOwner.addRow(kolom);
                   //System.out.println(listTipeKamera.size());
                   }
               
               dataOwner.fireTableDataChanged();
           }catch (Exception e){
                JOptionPane.showMessageDialog(null,"error :"+e.getMessage());
                } 
            }
    
    public void getDatabasePKamera() {
        //DefaultTableModel dataMerk = new DefaultTableModel(null, header);
        tblPKamera.setModel(dataPKamera);
        Statement st;
        ResultSet rs;
        String sql = "select * from V_PKamera";
            try {
               st = new Koneksi().getCon().createStatement();
               rs = st.executeQuery(sql);
               while(rs.next()){
                    PKamera PK = new PKamera(
                            rs.getInt("id_owner"), 
                            rs.getString("nama"),
                            rs.getInt("persentase"));
                    listPKamera.add(PK);
                    Object []kolom = {PK.getId_owner(), PK.getNama(),PK.getPersentase()};
                    dataPKamera.addRow(kolom);
                   }
               
                dataPKamera.fireTableDataChanged();
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"error :"+e.getMessage());
                e.printStackTrace();
                } 
            listPKamera.forEach((PK) -> {
        });
            }
    
    public void getDatabasePOutdoor() {
        tblPOutdoor.setModel(dataPOutdoor);
        Statement st;
        ResultSet rs;
        String sql = "select * from V_POutdoor";
           try {
               st = new Koneksi().getCon().createStatement();
               rs = st.executeQuery(sql);
               while(rs.next()){
                    POutdoor PO = new POutdoor(
                            rs.getInt("id_owner"), 
                            rs.getString("nama"),
                            rs.getInt("persentase"));
                   //String kolom1 = rs.getString(1);
                   //String kolom2 = rs.getString(2);
                   listPOutdoor.add(PO);
                   Object []kolom = {PO.getId_owner(), PO.getNama(),PO.getPersentase()};
                   dataPOutdoor.addRow(kolom);
                   
                   }
               
               dataPOutdoor.fireTableDataChanged();
           }catch (Exception e){
                JOptionPane.showMessageDialog(null,"error :"+e.getMessage());
                e.printStackTrace();
                } 
        
            }
    
    public void updateTableOwner() {
        dataOwner.setRowCount(0);
        getDatabaseOwner();
    }
    
    public void updateTablePKamera() {
        dataPKamera.setRowCount(0);
        getDatabasePKamera();
    }
    
    public void updateTablePOutdoor() {
        dataPOutdoor.setRowCount(0);
        getDatabasePOutdoor();
    }
    
    public void initComboBox1() {
        jComboBox1.removeAllItems();
        listOwner.forEach((OW) -> {
            hashOwner.put(OW.getOwner(), OW.getId());
        });
        
        keyOwner = hashOwner.keySet();
        
        for (String next : keyOwner) {
            int tempNext = hashOwner.get(next);
            jComboBox1.addItem(next);
        }
    }
    
    public void initComboBox2() {
        jComboBox2.removeAllItems();
        listOwner.forEach((OW) -> {
            hashOwner.put(OW.getOwner(), OW.getId());
        });
        
        keyOwner = hashOwner.keySet();
        
        for (String next : keyOwner) {
            int tempNext = hashOwner.get(next);
            jComboBox2.addItem(next);
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

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        txtNamaOwner = new javax.swing.JTextField();
        txtIDOwner = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblOwner = new javax.swing.JTable();
        btnInputOwner = new javax.swing.JButton();
        btnEditOwner = new javax.swing.JButton();
        btnDeleteOwner = new javax.swing.JButton();
        btnNewOwner = new javax.swing.JButton();
        btnRefreshOwner = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtPKamera = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPKamera = new javax.swing.JTable();
        btnInputPKamera = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        btnDeletePKamera = new javax.swing.JButton();
        btnEditPKamera = new javax.swing.JButton();
        btnNewPKamera = new javax.swing.JButton();
        btnRefreshPKamera = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        btnInputPOutdoor = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblPOutdoor = new javax.swing.JTable();
        txtPOutdoor = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        btnEditPOutdoor = new javax.swing.JButton();
        btnNewPOutdoor = new javax.swing.JButton();
        btnDeletePOutdoor = new javax.swing.JButton();
        btnRefreshPOutdoor = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();

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

        txtNamaOwner.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtNamaOwner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaOwnerActionPerformed(evt);
            }
        });

        txtIDOwner.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtIDOwner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDOwnerActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel1.setText("ID Owner");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel2.setText("Nama");

        tblOwner.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblOwner.setModel(new javax.swing.table.DefaultTableModel(
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
        tblOwner.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOwnerMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblOwner);

        btnInputOwner.setText("Masukkan");
        btnInputOwner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInputOwnerActionPerformed(evt);
            }
        });

        btnEditOwner.setText("Edit");
        btnEditOwner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditOwnerActionPerformed(evt);
            }
        });

        btnDeleteOwner.setText("Hapus");

        btnNewOwner.setText("New");

        btnRefreshOwner.setText("Refresh");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel3.setText("Owner");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNamaOwner, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                    .addComponent(txtIDOwner))
                .addGap(106, 106, 106)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(btnInputOwner)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEditOwner, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDeleteOwner)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNewOwner)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRefreshOwner))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIDOwner, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNamaOwner, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInputOwner)
                    .addComponent(btnEditOwner)
                    .addComponent(btnDeleteOwner)
                    .addComponent(btnNewOwner)
                    .addComponent(btnRefreshOwner)))
        );

        jPanel5.setBackground(new java.awt.Color(255, 232, 232));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel4.setText("Persentase");

        txtPKamera.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtPKamera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPKameraActionPerformed(evt);
            }
        });

        tblPKamera.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblPKamera.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblPKamera);

        btnInputPKamera.setText("Masukkan");
        btnInputPKamera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInputPKameraActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel9.setText("Persentase Bagi Hasil Kamera");

        jComboBox2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        btnDeletePKamera.setText("Hapus");

        btnEditPKamera.setText("Edit");
        btnEditPKamera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditPKameraActionPerformed(evt);
            }
        });

        btnNewPKamera.setText("New");

        btnRefreshPKamera.setText("Refresh");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel10.setText("Owner");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel10))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPKamera, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel9))
                .addGap(106, 106, 106)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnInputPKamera)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEditPKamera, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDeletePKamera)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNewPKamera)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRefreshPKamera)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9)
                        .addGap(32, 32, 32)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel10)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addComponent(txtPKamera, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnInputPKamera)
                    .addComponent(btnEditPKamera)
                    .addComponent(btnDeletePKamera)
                    .addComponent(btnNewPKamera)
                    .addComponent(btnRefreshPKamera)))
        );

        jPanel6.setBackground(new java.awt.Color(255, 232, 232));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel6.setText("Persentase");

        btnInputPOutdoor.setText("Masukkan");
        btnInputPOutdoor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInputPOutdoorActionPerformed(evt);
            }
        });

        tblPOutdoor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblPOutdoor.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tblPOutdoor);

        txtPOutdoor.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtPOutdoor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPOutdoorActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel8.setText("Owner");

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        btnEditPOutdoor.setText("Edit");
        btnEditPOutdoor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditPOutdoorActionPerformed(evt);
            }
        });

        btnNewPOutdoor.setText("New");

        btnDeletePOutdoor.setText("Hapus");

        btnRefreshPOutdoor.setText("Refresh");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel11.setText("Persentase Bagi Hasil Outdoor");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox1, 0, 185, Short.MAX_VALUE)
                            .addComponent(txtPOutdoor)))
                    .addComponent(jLabel11))
                .addGap(106, 106, 106)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(btnInputPOutdoor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnEditPOutdoor, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDeletePOutdoor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNewPOutdoor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRefreshPOutdoor))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnInputPOutdoor)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnEditPOutdoor)
                                .addComponent(btnDeletePOutdoor)
                                .addComponent(btnNewPOutdoor)
                                .addComponent(btnRefreshPOutdoor))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(txtPOutdoor, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(29, Short.MAX_VALUE))
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

    private void txtNamaOwnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaOwnerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaOwnerActionPerformed

    private void txtIDOwnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDOwnerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDOwnerActionPerformed

    private void txtPKameraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPKameraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPKameraActionPerformed

    private void btnInputOwnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInputOwnerActionPerformed
        boolean berhasil = false;
        int field1 = 0;
        String field2 = txtNamaOwner.getText();

        try {
            field1 = Integer.parseInt(txtIDOwner.getText());
        } catch (NumberFormatException e) {
            berhasil = false;
            JOptionPane.showMessageDialog(this,"Data yang Anda Masukkan Salah", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
        }

        try {
            Connection con = new Koneksi().getCon();
            
            String sqlinput = "INSERT INTO `owner`"
            + "(`id`, `nama`)"
            + " VALUES (?, ?)";
            myPreparedStatement = con.prepareStatement(sqlinput);
            myPreparedStatement.setInt(1, field1);
            myPreparedStatement.setString(2, field2);
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
            Owner newOW = new Owner();
            newOW.setId(field1);
            newOW.setOwner(field2);
//            this.listTipeKamera.add(newTK);
            JOptionPane.showMessageDialog(this,"Penambahan Sukses", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
            txtIDOwner.setText("");
            txtNamaOwner.setText("");
            updateTableOwner();
            initComboBox2();
            initComboBox1();
        }
        else if (berhasil == false) {
            JOptionPane.showMessageDialog(this,"Penambahan Gagal", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnInputOwnerActionPerformed

    private void btnInputPKameraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInputPKameraActionPerformed
        boolean berhasil = false;
        int field1 = 0;
        int field2 = 0;
        
        String tempTipe = (String) jComboBox2.getSelectedItem();
        System.out.println(tempTipe);
        try {
            field2 = Integer.parseInt(txtPKamera.getText());
            field1 = hashOwner.get(tempTipe);
        } catch (NumberFormatException e) {
            //berhasil = false;
            JOptionPane.showMessageDialog(this,"Data yang Anda Masukkan Salah", "Informasi",
            JOptionPane.INFORMATION_MESSAGE);
        }

        try {
            Connection con = new Koneksi().getCon();
            
            String sqlinput = "INSERT INTO `fin_share_kamera`"
            + "(`id_owner`, `persentase`)"
            + " VALUES (?, ?)";
            myPreparedStatement = con.prepareStatement(sqlinput);
            myPreparedStatement.setInt(1, field1);
            myPreparedStatement.setInt(2, field2);
            
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
//            this.listPKamera.add(newMK);
            JOptionPane.showMessageDialog(this,"Penambahan Sukses", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
            jComboBox2.setSelectedIndex(0);
            txtPKamera.setText("");
            updateTablePKamera();
        }
        else if (berhasil == false) {
            JOptionPane.showMessageDialog(this,"Penambahan Gagal", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
        }
        //data.addRow(kolom);
        //tampil();
    
    }//GEN-LAST:event_btnInputPKameraActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        String tempPKamera = (String) jComboBox2.getSelectedItem();
        
        try {
            IDOwner = hashOwner.get(tempPKamera);
        } catch (NullPointerException e) {
        }
        //nullpointerexc
//        System.out.println(IDMerk);
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void btnEditPKameraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditPKameraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditPKameraActionPerformed

    private void btnEditPOutdoorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditPOutdoorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditPOutdoorActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        String tempPOutdoor = (String) jComboBox1.getSelectedItem();
        
        try {
            IDOwner2 = hashOwner.get(tempPOutdoor);
        } catch (NullPointerException e) {
        }
        //nullpointerexc
//        System.out.println(IDMerk);
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void txtPOutdoorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPOutdoorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPOutdoorActionPerformed

    private void btnInputPOutdoorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInputPOutdoorActionPerformed
        boolean berhasil = false;
        int field1 = 0;
        int field2 = 0;
        
        String tempTipe = (String) jComboBox1.getSelectedItem();
        try {
            field2 = Integer.parseInt(txtPOutdoor.getText());
            field1 = hashOwner.get(tempTipe);
        } catch (NumberFormatException e) {
            //berhasil = false;
            JOptionPane.showMessageDialog(this,"Data yang Anda Masukkan Salah", "Informasi",
            JOptionPane.INFORMATION_MESSAGE);
        }

        try {
            Connection con = new Koneksi().getCon();
            
            String sqlinput = "INSERT INTO `fin_share_outdoor`"
            + "(`id_owner`, `persentase`)"
            + " VALUES (?, ?)";
            myPreparedStatement = con.prepareStatement(sqlinput);
            myPreparedStatement.setInt(1, field1);
            myPreparedStatement.setInt(2, field2);
            
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
//            this.listPKamera.add(newMK);
            JOptionPane.showMessageDialog(this,"Penambahan Sukses", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
            jComboBox1.setSelectedIndex(0);
            txtPOutdoor.setText("");
            updateTablePOutdoor();
        }
        else if (berhasil == false) {
            JOptionPane.showMessageDialog(this,"Penambahan Gagal", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnInputPOutdoorActionPerformed

    private void tblOwnerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOwnerMouseClicked
        indexOwner = tblOwner.getSelectedRow();
        txtIDOwner.setText(dataOwner.getValueAt(indexOwner, 0).toString());
        txtNamaOwner.setText(dataOwner.getValueAt(indexOwner, 1).toString());
    }//GEN-LAST:event_tblOwnerMouseClicked

    private void btnEditOwnerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditOwnerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditOwnerActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FormOwner.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(FormOwner.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FormOwner.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(FormOwner.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormOwner().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDeleteOwner;
    private javax.swing.JButton btnDeletePKamera;
    private javax.swing.JButton btnDeletePOutdoor;
    private javax.swing.JButton btnEditOwner;
    private javax.swing.JButton btnEditPKamera;
    private javax.swing.JButton btnEditPOutdoor;
    private javax.swing.JButton btnInputOwner;
    private javax.swing.JButton btnInputPKamera;
    private javax.swing.JButton btnInputPOutdoor;
    private javax.swing.JButton btnNewOwner;
    private javax.swing.JButton btnNewPKamera;
    private javax.swing.JButton btnNewPOutdoor;
    private javax.swing.JButton btnRefreshOwner;
    private javax.swing.JButton btnRefreshPKamera;
    private javax.swing.JButton btnRefreshPOutdoor;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblOwner;
    private javax.swing.JTable tblPKamera;
    private javax.swing.JTable tblPOutdoor;
    private javax.swing.JTextField txtIDOwner;
    private javax.swing.JTextField txtNamaOwner;
    private javax.swing.JTextField txtPKamera;
    private javax.swing.JTextField txtPOutdoor;
    // End of variables declaration//GEN-END:variables
}
