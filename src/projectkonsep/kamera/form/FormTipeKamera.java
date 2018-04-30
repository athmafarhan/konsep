/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectkonsep.kamera.form;

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
public class FormTipeKamera extends javax.swing.JFrame {
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
    int indexTipe = -1;
    int indexMerk = -1;
    int indexJenis = -1;
    ArrayList<TipeKamera> listTipeKamera = new ArrayList<>();
    ArrayList<MerkKamera> listMerkKamera = new ArrayList<>();
    ArrayList<JenisKamera> listJenisKamera = new ArrayList<>();
    /**
     * Creates new form FormTipeKamera
     */
    public FormTipeKamera() {
        initComponents();
        getDatabaseTipe();
        getDatabaseMerk();
        getDatabaseJenis();
        initComboBox1();
        initComboBox2();
        initComboBox3();
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
    
    public void initComboBox1() {
        jComboBox1.removeAllItems();
        listTipeKamera.forEach((TK) -> {
            hashTipe.put(TK.getNama(), TK.getId());
        });
        
        keyTipe = hashTipe.keySet();
        
        for (String next : keyTipe) {
            int tempNext = hashTipe.get(next);
            jComboBox1.addItem(next);
        }
        
    }
    
    public void initComboBox2() {
        
        
        keyMerk = hashMerk.keySet();
        
        for (String next : keyMerk) {
            int tempIDTipe = hashMerk.get(next);
            /*if (listMerkKamera.get(0).getId()==0) {
                
                jComboBox2.addItem(next);
                
            }*/
        }
        
        
        
        
    }
    
    public void initComboBox3() {
        jComboBox3.removeAllItems();
        for (TipeKamera TK : listTipeKamera){
            hashMerk2.put(TK.getNama(), TK.getId());
        }
        
        keyMerk2 = hashMerk2.keySet();
        
        for (TipeKamera TK : listTipeKamera){
            jComboBox3.addItem(TK.getNama());
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
        txtTipeNama = new javax.swing.JTextField();
        txtTipeID = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTipeKamera = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtMerkNama = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMerkKamera = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        txtMerkID = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jButton11 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        txtJenisID = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblMerkJenis = new javax.swing.JTable();
        txtJenisNama = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton14 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();

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

        txtTipeNama.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtTipeNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTipeNamaActionPerformed(evt);
            }
        });

        txtTipeID.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtTipeID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTipeIDActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel1.setText("ID Tipe");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel2.setText("Nama Tipe");

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

        jButton1.setText("Masukkan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Edit");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Hapus");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("New");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Refresh");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTipeNama, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                    .addComponent(txtTipeID))
                .addGap(99, 99, 99)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTipeID, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTipeNama, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5)))
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

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel9.setText("ID Tipe");

        jComboBox3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel9)
                    .addComponent(jLabel4))
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMerkID, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMerkNama, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(102, 102, 102)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton13)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addGap(25, 25, 25)
                        .addComponent(jLabel4))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(txtMerkID, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                        .addComponent(txtMerkNama, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton10)
                    .addComponent(jButton11)
                    .addComponent(jButton12)
                    .addComponent(jButton13)))
        );

        jPanel6.setBackground(new java.awt.Color(255, 232, 232));

        txtJenisID.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtJenisID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJenisIDActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel6.setText("Nama Jenis");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel5.setText("ID Jenis");

        jButton7.setText("Masukkan");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
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

        txtJenisNama.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtJenisNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJenisNamaActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel7.setText("ID Merk");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel8.setText("ID Tipe");

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jComboBox2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jButton14.setText("Edit");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton16.setText("New");

        jButton15.setText("Hapus");

        jButton17.setText("Refresh");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7))
                .addGap(26, 26, 26)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox1, 0, 185, Short.MAX_VALUE)
                    .addComponent(txtJenisID)
                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtJenisNama))
                .addGap(106, 106, 106)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton17))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
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
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(jButton7))
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton14)
                                .addComponent(jButton15)
                                .addComponent(jButton16)
                                .addComponent(jButton17))))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtJenisID, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtJenisNama, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
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

    private void txtTipeNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTipeNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipeNamaActionPerformed

    private void txtTipeIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTipeIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTipeIDActionPerformed

    private void txtMerkIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMerkIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMerkIDActionPerformed

    private void txtMerkNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMerkNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMerkNamaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        boolean berhasil = false;
        int field1 = 0;
        String field2 = txtTipeNama.getText();

        try {
            field1 = Integer.parseInt(txtTipeID.getText());
        } catch (NumberFormatException e) {
            berhasil = false;
            JOptionPane.showMessageDialog(this,"Data yang Anda Masukkan Salah", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
        }

        try {
            Connection con = new Koneksi().getCon();
            
            String sqlinput = "INSERT INTO `km_tipe_kamera`"
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
            }
            TipeKamera newTK = new TipeKamera();
            newTK.setId(field1);
            newTK.setNama(field2);
            this.listTipeKamera.add(newTK);
            JOptionPane.showMessageDialog(this,"Penambahan Sukses", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
            txtTipeID.setText("");
            txtTipeNama.setText("");
            updateTableTipe();
            initComboBox3();
            initComboBox1();
        }
        else if (berhasil == false) {
            JOptionPane.showMessageDialog(this,"Penambahan Gagal", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
        }
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        boolean berhasil = false;
        int field1 = 0;
        int field2 = 0;
        String field3 = txtMerkNama.getText();
        
        String tempTipe = (String) jComboBox3.getSelectedItem();
        
        try {
            field1 = Integer.parseInt(txtMerkID.getText());
            field2 = hashMerk2.get(tempTipe);
        } catch (NumberFormatException e) {
            //berhasil = false;
            JOptionPane.showMessageDialog(this,"Data yang Anda Masukkan Salah", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
        }

        try {
            Connection con = new Koneksi().getCon();
            
            String sqlinput = "INSERT INTO `km_merk_kamera`"
            + "(`id`, `id_tipe`, `nama`)"
            + " VALUES (?, ?, ?)";
            myPreparedStatement = con.prepareStatement(sqlinput);
            myPreparedStatement.setInt(1, field1);
            myPreparedStatement.setInt(2, field2);
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
            MerkKamera newMK = new MerkKamera();
            newMK.setId(field1);
            newMK.setId_tipe(field2);
            newMK.setTipe(tempTipe);
            newMK.setNama(field3);
            this.listMerkKamera.add(newMK);
            JOptionPane.showMessageDialog(this,"Penambahan Sukses", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
            txtMerkID.setText("");
            jComboBox3.setSelectedIndex(0);
            txtMerkNama.setText("");
            updateTableMerk();
            initComboBox1();
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

    private void txtJenisNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJenisNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtJenisNamaActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

    }//GEN-LAST:event_jButton7ActionPerformed

    private void txtJenisIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJenisIDActionPerformed
        boolean berhasil = false;
        int field1 = 0;
        
        String tempIDTipe = (String)jComboBox1.getSelectedItem();
        Integer field2 = hashTipe.get(tempIDTipe);
        
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
            + "(`id`, `id_tipe`, `id_merk`, `nama`)"
            + " VALUES (?, ?, ?, ?)";
            myPreparedStatement = con.prepareStatement(sqlinput);
            myPreparedStatement.setInt(1, field1);
            myPreparedStatement.setInt(2, field2);
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
            JenisKamera newJK = new JenisKamera();
            newJK.setId(field1);
            newJK.setId_merk(field2);
            newJK.setId_merk(field3);
            newJK.setNama(field4);
            this.listJenisKamera.add(newJK);
            JOptionPane.showMessageDialog(this,"Penambahan Sukses", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
            txtJenisID.setText("");
            jComboBox1.setSelectedIndex(0);
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

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        String tempIDMerk = (String) jComboBox1.getSelectedItem();
        int IDMerk = 0;
        try {
            IDMerk = hashTipe.get(tempIDMerk);
        } catch (NullPointerException e) {
        }
        //nullpointerexc
        System.out.println(IDMerk);
        jComboBox2.removeAllItems();
        for (MerkKamera MK : listMerkKamera) {
            //System.out.println(JK.getNama());
            if (IDMerk==MK.getId_tipe()) {
                String item = MK.getNama();
                jComboBox2.addItem(item);
            }
            else{
            }
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        String tempTipe = (String) jComboBox3.getSelectedItem();
        System.out.println(hashMerk2.get(tempTipe));
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        boolean berhasil = false;
        int field2 = 0;
        String field1 = txtTipeNama.getText();

        try {
            field2 = Integer.parseInt(txtTipeID.getText());
        } catch (NumberFormatException e) {
            berhasil = false;
            JOptionPane.showMessageDialog(this,"Data yang Anda Masukkan Salah", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
        }

        try {
            Connection con = new Koneksi().getCon();
            
            String sqlinput = "UPDATE `km_tipe_kamera` SET "
            + "`nama`=? "
            + "WHERE `id`=?";
            myPreparedStatement = con.prepareStatement(sqlinput);
            myPreparedStatement.setInt(2, field2);
            myPreparedStatement.setString(1, field1);
            
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
            TipeKamera newTK = listTipeKamera.get(indexTipe);
            newTK.setId(field2);
            newTK.setNama(field1);
            this.listTipeKamera.set(indexTipe, newTK);
            JOptionPane.showMessageDialog(this,"Penambahan Sukses", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
            txtTipeID.setText("");
            txtTipeNama.setText("");
            updateTableTipe();
            initComboBox3();
            initComboBox1();
        }
        else if (berhasil == false) {
            JOptionPane.showMessageDialog(this,"Penambahan Gagal", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        boolean berhasil = false;
        if (JOptionPane.showConfirmDialog(this, "Yakin akan dihapus?", "Hapus", 0) == 0) {
            String ketikKONFIRMASI = JOptionPane.showInputDialog(this,
                        "Ketik KONFIRMASI untuk menghapus data", null);
            String key = "KONFIRMASI";
            
            if (ketikKONFIRMASI.equals(key)) {
                Connection con = new Koneksi().getCon();
                String sqldelete = "DELETE FROM `km_tipe_kamera` WHERE `id` =?";
                try {
                    int field1 = Integer.parseInt(txtTipeID.getText());
                    myPreparedStatement = con.prepareStatement(sqldelete);
                    myPreparedStatement.setInt(1, field1);
                    myPreparedStatement.executeUpdate();
                    berhasil = true;
                }
                catch (SQLException errorSQL) {
                    berhasil = false;
                    JOptionPane.showMessageDialog(this, "Data tidak berhasil dihapus", "Informasi", 1);
                }
                catch (NumberFormatException | NullPointerException e) {
                    berhasil = false;
                    JOptionPane.showMessageDialog(this, "Pilih data yang akan dihapus!", "Informasi", 1);
                }
                if (berhasil == true) {
                    listTipeKamera.remove(indexTipe);
                    JOptionPane.showMessageDialog(this, "Data berhasil dihapus!", "Informasi", 1);
                    indexTipe = -1;
                    updateTableTipe();
                    txtTipeID.setText("");
                    txtTipeNama.setText("");
                }
            }
            else if (!ketikKONFIRMASI.equals(key)) {
                JOptionPane.showMessageDialog(this, "Data tidak berhasil dihapus", "Informasi", 1);
            }
            
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(FormPeminjamanKamera.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormTipeKamera().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
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
    private javax.swing.JTable tblMerkJenis;
    private javax.swing.JTable tblMerkKamera;
    private javax.swing.JTable tblTipeKamera;
    private javax.swing.JTextField txtJenisID;
    private javax.swing.JTextField txtJenisNama;
    private javax.swing.JTextField txtMerkID;
    private javax.swing.JTextField txtMerkNama;
    private javax.swing.JTextField txtTipeID;
    private javax.swing.JTextField txtTipeNama;
    // End of variables declaration//GEN-END:variables
}
