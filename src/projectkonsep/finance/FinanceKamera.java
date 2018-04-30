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
import java.util.Set;
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
public class FinanceKamera extends javax.swing.JFrame {
    int indexOwner;
    int indexPkamera;
    int indexPOutdoor;
    int bulan;
    int tahun;
    
    LinkedHashMap<String, Integer> hashBulan = new LinkedHashMap<>();
    
    Collection<String> keyBulan;
    int IDOwner = 0;
    int IDOwner2 = 0;
    
    Object headerInventaris[]={"ID", "Nama Inventaris", "Jumlah Hari", "Jumlah"};
    Object headerOwner[]={"ID","Nama","Total","Bagian Owner", "Bagian KONSEP"};
    Object headerPOutdoor[]= {"ID Owner","Nama","Persentase"};
    
    DefaultTableModel dataInventaris = new DefaultTableModel(null, headerInventaris);
    DefaultTableModel dataOwner = new DefaultTableModel(null, headerOwner);
    
    Connection con1 = null;
    Connection con2 = null;
    Connection con3 = null;
    
    PreparedStatement myPreparedStatement = null;

    int index = -1;
    ArrayList<Bulan> listBulan = new ArrayList<>();
    ArrayList<Inventaris> listInventaris = new ArrayList<>();
    ArrayList<Owner> listOwner = new ArrayList<>();
    ArrayList<POutdoor> listPOutdoor = new ArrayList<>();
    
    
    
    /**
     * Creates new form FormTipeKamera
     */
    public FinanceKamera() {
        initComponents();
        initComboBox3();
    }

    public void getDatabaseInventaris() {
        Object header[]={"ID", "Nama Inventaris", "Jumlah Hari", "Jumlah"};
        //DefaultTableModel dataTipe = new DefaultTableModel(null, header);
        tblOwner.setModel(dataInventaris);
        Statement st;
        ResultSet rs;
        String sql = "SELECT i.id as 'id_inventaris', CONCAT(m.nama, '  ',j.nama, '  ', i.stringwarna) AS 'nama_inventaris', SUM(p.tgl_kembali-p.tgl_sewa) AS jml_hari, SUM((((datediff (p.tgl_kembali, p.tgl_sewa))*i.harga)-p.diskon)+p.denda-p.dp) as 'jml'\n" +
                     "FROM km_pengembalian p\n" +
                     "JOIN km_inventaris i ON p.id_inven = i.id \n" +
                     "JOIN km_jenis_kamera j on i.merk = j.id \n" +
                     "JOIN km_cust c  ON p.id_cust = c.id \n" +
                     "JOIN km_merk_kamera m ON i.merk = m.id\n" +
                     "WHERE MONTH (p.tgl_sewa) ="+ bulan +"&& YEAR(p.tgl_sewa)="+tahun+"\n" +
                     "GROUP by i.id";
           try {
               st = new Koneksi().getCon().createStatement();
               rs = st.executeQuery(sql);
               while(rs.next()){
                   Inventaris IN = new Inventaris(rs.getInt(1), rs.getString(2),rs.getInt(3),rs.getInt(4));
                   listInventaris.add(IN);
                   Object []kolom = {IN.getId(), IN.getNama(), IN.getJml_hari(), IN.getJml()};
                   dataInventaris.addRow(kolom);
                   //System.out.println(listTipeKamera.size());
                   }
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"error :"+e.getMessage());
                } 
            }
    
    public void getDatabasePerOwner() {
        //DefaultTableModel dataMerk = new DefaultTableModel(null, header);
        tblPerOwner.setModel(dataOwner);
        Statement st;
        ResultSet rs;
        String sql = "SELECT o.id 'id_owner', o.nama, SUM((((datediff (p.tgl_kembali, p.tgl_sewa))*i.harga)-p.diskon)+p.denda) as 'jml', SUM((((datediff (p.tgl_kembali, p.tgl_sewa))*i.harga)-p.diskon)+p.denda)*(s.persentase/100) as 'share', (SUM((((datediff (p.tgl_kembali, p.tgl_sewa))*i.harga)-p.diskon)+p.denda)) - SUM((((datediff (p.tgl_kembali, p.tgl_sewa))*i.harga)-p.diskon)+p.denda)*(s.persentase/100) as 'share_owner'\n" +
                     "FROM km_pengembalian p\n" +
                     "JOIN km_inventaris i ON p.id_inven = i.id \n" +
                     "JOIN km_jenis_kamera j on i.merk = j.id \n" +
                     "JOIN km_cust c  ON p.id_cust = c.id \n" +
                     "JOIN km_merk_kamera m ON i.merk = m.id\n" +
                     "JOIN owner o ON i.pemilik = o.id\n" +
                     "JOIN fin_share_kamera s ON s.id_owner = i.pemilik\n" +
                     "WHERE MONTH (p.tgl_sewa) ="+bulan+"\n && (YEAR(p.tgl_sewa)="+tahun+")"+
                     "group by i.pemilik";
            try {
               st = new Koneksi().getCon().createStatement();
               rs = st.executeQuery(sql);
               while(rs.next()){
                    Owner OW = new Owner(
                            rs.getInt(1), 
                            rs.getString(2),
                            rs.getInt(3),
                            rs.getDouble(4),
                            rs.getDouble(5));
                    listOwner.add(OW);
                    Object []kolom = {OW.getId(), OW.getOwner(), OW.getJml(), OW.getShare(), OW.getShare_owner()};
                    dataOwner.addRow(kolom);
                   }
               
                dataOwner.fireTableDataChanged();
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"error :"+e.getMessage());
                e.printStackTrace();
                } 
            listOwner.forEach((PK) -> {
        });
            }
    
    public void getDatabaseTotalOwnerLain() {
        Statement st;
        ResultSet rs;
        String sql = "SELECT (SUM((((datediff (p.tgl_kembali, p.tgl_sewa))*i.harga)-p.diskon)+p.denda)) - SUM((((datediff (p.tgl_kembali, p.tgl_sewa))*i.harga)-p.diskon)+p.denda)*(s.persentase/100) as 'share_owner'\n" +
                     "FROM km_pengembalian p\n" +
                     "JOIN km_inventaris i ON p.id_inven = i.id \n" +
                     "JOIN km_jenis_kamera j on i.merk = j.id \n" +
                     "JOIN km_cust c  ON p.id_cust = c.id \n" +
                     "JOIN km_merk_kamera m ON i.merk = m.id\n" +
                     "JOIN owner o ON i.pemilik = o.id\n" +
                     "JOIN fin_share_kamera s ON s.id_owner = i.pemilik\n" +
                     "WHERE (MONTH (p.tgl_sewa) = "+bulan+") and (YEAR (p.tgl_sewa)="+tahun+") and (i.pemilik != 1)";
           try {
               st = new Koneksi().getCon().createStatement();
               rs = st.executeQuery(sql);
               while(rs.next()){
                    Owner OW = new Owner(
                            rs.getDouble(1)
                            );
                    txtOwnerLain.setText(String.valueOf(OW.getShare()));
                   }
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"error :"+e.getMessage());
                e.printStackTrace();
                } 
        
    }
    
    public void getDatabaseTotalKonsep() {
        Statement st;
        ResultSet rs;
        String sql = "SELECT (SUM((((datediff (p.tgl_kembali, p.tgl_sewa))*i.harga)-p.diskon)+p.denda)) - SUM((((datediff (p.tgl_kembali, p.tgl_sewa))*i.harga)-p.diskon)+p.denda)*(s.persentase/100) +\n" +
                     "( SELECT SUM((((datediff (p.tgl_kembali, p.tgl_sewa))*i.harga)-p.diskon)+p.denda) as 'tes'\n" +
                     "FROM km_pengembalian p\n" +
                     "JOIN km_inventaris i ON p.id_inven = i.id\n" +
                     "WHERE i.pemilik=1 and (MONTH (p.tgl_sewa) = "+bulan+") and (YEAR (p.tgl_sewa)="+tahun+"))"+
                     "as 'share_owner' \n" +
                     "FROM km_pengembalian p\n" +
                     "JOIN km_inventaris i ON p.id_inven = i.id \n" +
                     "JOIN km_jenis_kamera j on i.merk = j.id \n" +
                     "JOIN km_cust c  ON p.id_cust = c.id \n" +
                     "JOIN km_merk_kamera m ON i.merk = m.id\n" +
                     "JOIN owner o ON i.pemilik = o.id\n" +
                     "JOIN fin_share_kamera s ON s.id_owner = i.pemilik\n" +
                     "WHERE (MONTH (p.tgl_sewa) = "+bulan+") and (YEAR (p.tgl_sewa)="+tahun+")and (i.pemilik != 1)";
           try {
               st = new Koneksi().getCon().createStatement();
               rs = st.executeQuery(sql);
               while(rs.next()){
                    Owner OW = new Owner(
                            rs.getDouble(1)
                            );
                    txtTotal.setText(String.valueOf(OW.getShare()));
                   }
            }catch (Exception e){
                JOptionPane.showMessageDialog(null,"error :"+e.getMessage());
                e.printStackTrace();
                } 
        
    }
    
    public void initComboBox3() {
        jComboBox3.removeAllItems();
        Bulan bl1 = new Bulan("Januari",1);
        Bulan bl2 = new Bulan("Februari",2);
        Bulan bl3 = new Bulan("Maret",3);
        Bulan bl4 = new Bulan("April",4);
        Bulan bl5 = new Bulan("Mei",5);
        Bulan bl6 = new Bulan("Juni",6);
        Bulan bl7 = new Bulan("Juli",7);
        Bulan bl8 = new Bulan("Agustus",8);
        Bulan bl9 = new Bulan("September",9);
        Bulan bl10 = new Bulan("Oktober",10);
        Bulan bl11 = new Bulan("November",11);
        Bulan bl12 = new Bulan("Desember",12);
        
        listBulan.add(bl1);
        listBulan.add(bl2);
        listBulan.add(bl3);
        listBulan.add(bl4);
        listBulan.add(bl5);
        listBulan.add(bl6);
        listBulan.add(bl7);
        listBulan.add(bl8);
        listBulan.add(bl9);
        listBulan.add(bl10);
        listBulan.add(bl11);
        listBulan.add(bl12);
        
        listBulan.forEach((BL) -> {
            hashBulan.put(BL.getStrBulan(), BL.getIntBulan());
        });
        
        keyBulan = hashBulan.keySet();
        
        for (String next : keyBulan) {
            jComboBox3.addItem(next);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblOwner = new javax.swing.JTable();
        btnCek = new javax.swing.JButton();
        jComboBox3 = new javax.swing.JComboBox<>();
        txtTahun = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPerOwner = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtOwnerLain = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();

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
            .addGap(0, 67, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(255, 232, 232));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel1.setText("Tahun");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel2.setText("Bulan");

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

        btnCek.setText("Cek");
        btnCek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekActionPerformed(evt);
            }
        });

        jComboBox3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        txtTahun.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtTahun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTahunActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel12.setText("Total Per Kamera");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(65, 65, 65)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTahun, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCek)))
                    .addComponent(jLabel12))
                .addGap(109, 109, 109)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel12)
                .addGap(35, 35, 35)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTahun, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnCek)
                .addContainerGap(40, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 232, 232));

        tblPerOwner.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblPerOwner.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tblPerOwner);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel9.setText("Bagi Hasil Kamera per Owner");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel9)
                .addGap(154, 154, 154)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(jLabel9))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 232, 232));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel6.setText("Total Pendapatan Kotor");

        txtOwnerLain.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtOwnerLain.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOwnerLainActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel8.setText("Pendapatan Dari Owner Lain");

        txtTotal.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel6))
                .addGap(164, 164, 164)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtOwnerLain, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtOwnerLain, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
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
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTahunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTahunActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTahunActionPerformed

    private void btnCekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekActionPerformed
        boolean berhasil = false;

        try {
            dataOwner.setRowCount(0);
            dataInventaris.setRowCount(0);
            tahun = Integer.parseInt(txtTahun.getText());
            getDatabaseTotalOwnerLain();
            getDatabaseInventaris();
            getDatabasePerOwner();
            getDatabaseTotalKonsep();
        } catch (NumberFormatException e) {
            berhasil = false;
            JOptionPane.showMessageDialog(this,"Tahun yang Anda Masukkan Salah", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnCekActionPerformed
    private void txtOwnerLainActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOwnerLainActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOwnerLainActionPerformed

    private void tblOwnerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOwnerMouseClicked
       
    }//GEN-LAST:event_tblOwnerMouseClicked

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        String tempStrBL = (String) jComboBox3.getSelectedItem();
        bulan = hashBulan.get(tempStrBL);
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FinanceKamera.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(FinanceKamera.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FinanceKamera.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(FinanceKamera.class.getName()).log(Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FinanceKamera().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCek;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
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
    private javax.swing.JTable tblOwner;
    private javax.swing.JTable tblPerOwner;
    private javax.swing.JTextField txtOwnerLain;
    private javax.swing.JTextField txtTahun;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
