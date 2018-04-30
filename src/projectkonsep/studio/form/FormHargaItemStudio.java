/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectkonsep.studio.form;

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
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import projectkonsep.Koneksi;
import projectkonsep.studio.HargaItemStudio;
import projectkonsep.studio.ItemStudio;

/**
 *
 * @author Athma Farhan
 */
public class FormHargaItemStudio extends javax.swing.JFrame {
    LinkedHashMap<String, Integer> hashItem = new LinkedHashMap<>();
    Collection<String> keyItem;
    
    ArrayList<ItemStudio> ListIS = new ArrayList<>();
    
    private DefaultTableModel tabmode;
    String warnaRGB;
    Connection con = null;
    PreparedStatement myPreparedStatement = null;
    Object[] headerHargaItem = {"ID", "Nama Item", "Nama Paket", "Harga 1 Jam", "Harga 2 Jam", "Harga Tambahan"};
    DefaultTableModel dataHargaItem = new DefaultTableModel(null, headerHargaItem);
    int index = -1;
    ArrayList<HargaItemStudio> ListHargaItemStudio = new ArrayList<>();//

    /**
     * Creates new form FormCustomerKamera
     */
    public FormHargaItemStudio() {
        initComponents();
        initCombo1();
        connectDatabase();
    }
    
    private void initCombo1() {
        Connection con1 = new Koneksi().getCon();
        Statement stmt1;
        String sql = "SELECT * FROM st_item";    
        try {
            stmt1 = con1.createStatement();
            ResultSet cb1 = stmt1.executeQuery(sql);
            
            while (cb1.next()) { 
                
                ItemStudio newIS = new ItemStudio(
                        cb1.getInt("id"), 
                        cb1.getString("nama"));
                
                ListIS.add(newIS);
                //jComboBox1.addItem(cb1.getString("nama"));
            }
        } catch (SQLException ex) {
        }
        updateCombo1();
    }
    
    private void updateCombo1() {
        ListIS.forEach((IS) -> {
            hashItem.put(IS.getNama(), IS.getId());
        });
        
        keyItem = hashItem.keySet();
        //JenisKamera JK = new JenisKamera();
        for (ItemStudio IS : ListIS) {
            cbItem.addItem(IS.getNama());
        }
    }
    
    public void updateTableHargaItem() {
        int rowCount = dataHargaItem.getRowCount()-1;
        
        for (int i = rowCount; i >= 0; i--) {
            dataHargaItem.removeRow(i);
        }
        for (HargaItemStudio HIS : ListHargaItemStudio) {
                Object []kolom = {
                       HIS.getId(), 
                       HIS.getItem(), 
                       HIS.getNama_paket(), 
                       HIS.getHarga_1_jam(), 
                       HIS.getHarga_2_jam(), 
                       HIS.getHarga_tambahan()};           
                dataHargaItem.addRow(kolom);
        }

        dataHargaItem.fireTableDataChanged();
    }
    
    public void connectDatabase(){
        tblInventarisKamera.setModel(dataHargaItem);
        Statement st;
        ResultSet rs;
        String viewJenisKamera = "select * from V_HargaItemStudio";
           try {
               st = new Koneksi().getCon().createStatement();
               rs = st.executeQuery(viewJenisKamera);
               while(rs.next()){
                   
                   HargaItemStudio HIS = new HargaItemStudio(
                           rs.getInt("id"), 
                           rs.getInt("id_item"), 
                           rs.getString("item"), 
                           rs.getString("nama_paket"), 
                           rs.getInt("harga_1_jam"), 
                           rs.getInt("harga_2_jam"), 
                           rs.getInt("harga_tambahan"));
                   ListHargaItemStudio.add(HIS);
                   Object []kolom = {
                       HIS.getId(), 
                       HIS.getItem(), 
                       HIS.getNama_paket(), 
                       HIS.getHarga_1_jam(), 
                       HIS.getHarga_2_jam(), 
                       HIS.getHarga_tambahan()};
                   dataHargaItem.addRow(kolom);
                   
                   }
               
               dataHargaItem.fireTableDataChanged();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblInventarisKamera = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        cbItem = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtNamaPaket = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtHargaSatu = new javax.swing.JTextField();
        txtHargaDua = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtHargaTiga = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();

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

        jLabel1.setText("ID Inventaris");

        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });

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
        });
        jScrollPane1.setViewportView(tblInventarisKamera);

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

        jButton8.setText("Delete");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel5.setText("ID Item");

        cbItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbItemActionPerformed(evt);
            }
        });

        jLabel2.setText("Nama Paket");

        jLabel3.setText("Harga 1 jam");

        jLabel4.setText("Harga 2 jam");

        jLabel6.setText("Harga Tambahan");

        jButton7.setText("KEMBALI");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton9.setText("New");
        jButton9.setBorder(null);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("Reset");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNamaPaket, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHargaSatu, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHargaDua, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHargaTiga, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbItem, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(239, 239, 239)
                        .addComponent(jButton7))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel5)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel2)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel3)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel4)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel6))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtNamaPaket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtHargaSatu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtHargaDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtHargaTiga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton8)
                        .addComponent(jButton7)
                        .addComponent(jButton6)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton10)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton5)
                        .addComponent(jButton4)
                        .addComponent(jButton3)
                        .addComponent(jButton2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        String tempIDItem = (String) cbItem.getSelectedItem();
        int IDItem = 0;
        try {
            IDItem = hashItem.get(tempIDItem);
        } catch (NullPointerException e) {
            //e.printStackTrace();
        }
        
        boolean berhasil = false;
        int field1 = 0;
        int field2 = IDItem;
        String field3 = txtNamaPaket.getText();
        int field4 = 0;
        int field5 = 0;
        int field6 = 0;

        try {
            field1 = Integer.parseInt(txtID.getText());
            field4 = Integer.parseInt(txtHargaSatu.getText());
            field5 = Integer.parseInt(txtHargaDua.getText());
            field6 = Integer.parseInt(txtHargaTiga.getText());
        } catch (NumberFormatException e) {
            berhasil = false;
            JOptionPane.showMessageDialog(this,"Data yang Anda Masukkan Salah", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
        }

        try {
            Connection con = new Koneksi().getCon();
            
            String sqlinput = "INSERT INTO `st_studio`"
            + "(`id`, `id_item`, `nama_paket`, `harga_1_jam`, `harga_2_jam`, `harga_tambahan`)"
            + " VALUES (?, ?, ?, ?, ?, ?, ?)";
            myPreparedStatement = con.prepareStatement(sqlinput);
            myPreparedStatement.setInt(1, field1);
            myPreparedStatement.setInt(2, field2);
            myPreparedStatement.setString(3, field3);
            myPreparedStatement.setInt(4, field4);
            myPreparedStatement.setInt(5, field5);
            myPreparedStatement.setInt(6, field6);
            
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
            
            HargaItemStudio newHIS = new HargaItemStudio();
            newHIS.setId(field1);
            newHIS.setId_item(field2);
            newHIS.setItem(tempIDItem);
            newHIS.setNama_paket(field3);
            newHIS.setHarga_1_jam(field4);
            newHIS.setHarga_2_jam(field5);
            newHIS.setHarga_tambahan(field6);
            
            ListHargaItemStudio.add(newHIS);
            
            JOptionPane.showMessageDialog(this,"Penambahan Sukses", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
            Object []kolom = {
                       newHIS.getId(), 
                       newHIS.getItem(), 
                       newHIS.getNama_paket(), 
                       newHIS.getHarga_1_jam(), 
                       newHIS.getHarga_2_jam(), 
                       newHIS.getHarga_tambahan()};   
            dataHargaItem.addRow(kolom);
            
        }
        else if (berhasil == false) {
            JOptionPane.showMessageDialog(this,"Penambahan Gagal", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tblInventarisKameraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblInventarisKameraMouseClicked
        try {
            int rowIndex = tblInventarisKamera.getSelectedRow();
            index = rowIndex;
            showData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Penambahan Gagal", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_tblInventarisKameraMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        index = 0;
        showData();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       index = ListHargaItemStudio.size()-1;
       showData();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            ++index;
            showData();
        
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            JOptionPane.showMessageDialog(this,"Data yang dicari tidak ditemukan", "Informasi", 
                JOptionPane.INFORMATION_MESSAGE);
            index = ListHargaItemStudio.size()-1;
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
        if (JOptionPane.showConfirmDialog(this, "Yakin akan diedit?", "Hapus", 0) == 0) {
            String ketikKONFIRMASI = JOptionPane.showInputDialog(this,
                        "Ketik KONFIRMASI untuk mengedit data", null);
            String key = "KONFIRMASI";
            
            if (ketikKONFIRMASI.equals(key)) {
                String tempIDItem = (String) cbItem.getSelectedItem();
                int IDItem = 0;
                try {
                    IDItem = hashItem.get(tempIDItem);
                } catch (NullPointerException e) {
                    //e.printStackTrace();
                }

                boolean berhasil = false;
                int field1 = 0;
                int field2 = IDItem;
                String field3 = txtNamaPaket.getText();
                int field4 = 0;
                int field5 = 0;
                int field6 = 0;

                try {
                    field1 = Integer.parseInt(txtID.getText());
                    field4 = Integer.parseInt(txtHargaSatu.getText());
                    field5 = Integer.parseInt(txtHargaDua.getText());
                    field6 = Integer.parseInt(txtHargaTiga.getText());
                } catch (NumberFormatException e) {
                    berhasil = false;
                    JOptionPane.showMessageDialog(this,"Data yang Anda Masukkan Salah", "Informasi",
                        JOptionPane.INFORMATION_MESSAGE);
                }

                try {
                    Connection con = new Koneksi().getCon();
                    PreparedStatement myPreparedStatement = null;
                    String sqlupdate = "UPDATE `st_studio` "
                            + "SET `id_item`=?,`nama_paket`=?,`harga_1_jam`=?,`harga_2_jam`=?,`harga_tambahan`=? WHERE `id`=?";
                    myPreparedStatement = con.prepareStatement(sqlupdate);
                    myPreparedStatement.setInt(6, field1);
                    myPreparedStatement.setInt(1, field2);
                    myPreparedStatement.setString(2, field3);
                    myPreparedStatement.setInt(3, field4);
                    myPreparedStatement.setInt(4, field5);
                    myPreparedStatement.setInt(5, field6);
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
                        Logger.getLogger(FormHargaItemStudio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    HargaItemStudio newHIS = new HargaItemStudio();

                    newHIS.setId(field1);
                    newHIS.setId_item(field2);
                    newHIS.setItem(tempIDItem);
                    newHIS.setNama_paket(field3);
                    newHIS.setHarga_1_jam(field4);
                    newHIS.setHarga_2_jam(field5);
                    newHIS.setHarga_tambahan(field6);

                    ListHargaItemStudio.set(index, newHIS);
                    JOptionPane.showMessageDialog(this,"Penambahan Sukses", "Informasi",
                        JOptionPane.INFORMATION_MESSAGE);
                    updateTableHargaItem();
                }
                else if (berhasil == false) {
                    JOptionPane.showMessageDialog(this,"Penambahan Gagal", "Informasi",
                        JOptionPane.INFORMATION_MESSAGE);
                }
            }
	}
        
        
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        boolean berhasil = false;
        if (JOptionPane.showConfirmDialog(this, "Yakin akan dihapus?", "Hapus", 0) == 0) {
            String ketikKONFIRMASI = JOptionPane.showInputDialog(this,
                        "Ketik KONFIRMASI untuk menghapus data", null);
            String key = "KONFIRMASI";
            
            if (ketikKONFIRMASI.equals(key)) {
                con = new Koneksi().getCon();
                String sqldelete = "DELETE FROM st_studio WHERE `id` =?";
                try {
                    int field1 = Integer.parseInt(txtID.getText());
                    myPreparedStatement = con.prepareStatement(sqldelete);
                    myPreparedStatement.setInt(1, field1);
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
                    
                    try {
                        myPreparedStatement.executeUpdate();
                    } catch (SQLException ex) {
                        Logger.getLogger(FormHargaItemStudio.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    ListHargaItemStudio.remove(index);
                    JOptionPane.showMessageDialog(this, "Data berhasil dihapus!", "Informasi", 1);
                    updateTableHargaItem();
                }
            }
            else if (berhasil == false) {
                JOptionPane.showMessageDialog(this, "Data tidak berhasil dihapus", "Informasi", 1);
            }
            
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void cbItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbItemActionPerformed
//        
    }//GEN-LAST:event_cbItemActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        int id = ListHargaItemStudio.get(ListHargaItemStudio.size()-1).getId()+1;
        txtID.setText(String.valueOf(id));
        cbItem.setSelectedIndex(0);
        txtNamaPaket.setText("");
        txtHargaSatu.setText("");
        txtHargaDua.setText("");
        txtHargaTiga.setText("");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        txtNamaPaket.setText("");
        txtHargaSatu.setText("");
        txtHargaDua.setText("");
        txtHargaTiga.setText("");
    }//GEN-LAST:event_jButton10ActionPerformed

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
            java.util.logging.Logger.getLogger(FormHargaItemStudio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormHargaItemStudio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormHargaItemStudio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormHargaItemStudio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
            Logger.getLogger(FormHargaItemStudio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(FormHargaItemStudio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FormHargaItemStudio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(FormHargaItemStudio.class.getName()).log(Level.SEVERE, null, ex);
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormHargaItemStudio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbItem;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblInventarisKamera;
    private javax.swing.JTextField txtHargaDua;
    private javax.swing.JTextField txtHargaSatu;
    private javax.swing.JTextField txtHargaTiga;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtNamaPaket;
    // End of variables declaration//GEN-END:variables

    private void showData() {
        HargaItemStudio HIS = ListHargaItemStudio.get(index);
        txtID.setText(String.valueOf(HIS.getId()));
        cbItem.setSelectedItem(HIS.getItem());
        txtNamaPaket.setText(HIS.getNama_paket());
        txtHargaSatu.setText(String.valueOf(HIS.getHarga_1_jam()));
        txtHargaDua.setText(String.valueOf(HIS.getHarga_2_jam()));
        txtHargaTiga.setText(String.valueOf(HIS.getHarga_tambahan()));
    }
    
}

