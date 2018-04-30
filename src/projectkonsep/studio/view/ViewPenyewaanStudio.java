/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectkonsep.studio.view;

import projectkonsep.kamera.*;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import projectkonsep.Koneksi;
import projectkonsep.studio.CustomerStudio;
import projectkonsep.studio.HargaItemStudio;
import projectkonsep.studio.ItemStudio;
import projectkonsep.studio.PenyewaanStudio;

/**
 *
 * @author Athma Farhan
 */
public class ViewPenyewaanStudio extends javax.swing.JFrame {
    SimpleDateFormat sdfThnBlnTgl = new SimpleDateFormat("yyyy-MM-dd");

    
    int jumlahSewa = 0;
    boolean tambah1 = false;
    boolean tambah2 = false;
    boolean tambah3 = false;
    boolean tambah4 = false;

    LinkedHashMap<String, Integer> hashItemStudio = new LinkedHashMap<>();
    Collection<String> keyItemStudio;

    private TableRowSorter<TableModel> rowSorterCustomer;
    private TableRowSorter<TableModel> rowSorterPenyewaan;
    
    ArrayList<PenyewaanStudio> ListPeminjamanStudio = new ArrayList<>();
    ArrayList<CustomerStudio> ListCustomerStudio = new ArrayList<>();
    ArrayList<HargaItemStudio> ListInventarisStudio = new ArrayList<>();
    
    ArrayList<HargaItemStudio> ListHargaItemStudio = new ArrayList<>();
    ArrayList<ItemStudio> ListItemStudio = new ArrayList<>();
    
    Object[] headerCustomer = {"ID", "Nama", "Nomor HP", "Alamat", "Instagram", "Status"};
    DefaultTableModel dataCustomer = new DefaultTableModel(null, headerCustomer);
    
    Object[] headerPeminjaman = {"ID", "Customer", "Nama Item", "Tgl Sewa", "Jam Mulai", "Jam Selesai", "Harga", "DP", "Diskon", "Denda", "Jumlah", "Cara Bayar"};
    DefaultTableModel dataPeminjaman = new DefaultTableModel(null, headerPeminjaman);
    
    String nama_cust;
    
    int index;

        
    /**
     * Creates new form FormTransaksiStudio
     */
    public ViewPenyewaanStudio() {
        
    
        initComponents();
        connectDatabase();
        connectDatabaseHarga();
        connectDatabasePeminjaman();
        
        searchTabelPenyewaan();
        searchTabelCustomer();
    }
    
    
    
    public final void searchTabelCustomer() {
        this.rowSorterCustomer = new TableRowSorter<>(tblCustomer.getModel());
        tblCustomer.setRowSorter(rowSorterCustomer);
        txtSearchCustomer.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = txtSearchCustomer.getText();

                if (text.trim().length() == 0) {
                    rowSorterCustomer.setRowFilter(null);
                } else {
                    rowSorterCustomer.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = txtSearchCustomer.getText();

                if (text.trim().length() == 0) {
                    rowSorterCustomer.setRowFilter(null);
                } else {
                    rowSorterCustomer.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            });
    }
    
    public final void searchTabelPenyewaan() {
        this.rowSorterPenyewaan = new TableRowSorter<>(txtPenyewaanStudio.getModel());
        txtPenyewaanStudio.setRowSorter(rowSorterPenyewaan);
        txtSearchCustomer.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = txtSearchCustomer.getText();

                if (text.trim().length() == 0) {
                    rowSorterPenyewaan.setRowFilter(null);
                } else {
                    rowSorterPenyewaan.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = txtSearchCustomer.getText();

                if (text.trim().length() == 0) {
                    rowSorterPenyewaan.setRowFilter(null);
                } else {
                    rowSorterPenyewaan.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            });
    }
    
    public void connectDatabase(){
        tblCustomer.setModel(dataCustomer);
        Statement st3;
        ResultSet rs3;
        String viewJenisStudio = "SELECT * FROM  `st_cust` ";
           try {
               st3 = new Koneksi().getCon().createStatement();
               rs3 = st3.executeQuery(viewJenisStudio);
               int i = 0;
            while (rs3.next() == true){
                CustomerStudio newCK = new CustomerStudio(
                rs3.getInt("id"),
                rs3.getString("nama"),
                rs3.getString("no_hp"),
                rs3.getString("alamat_rumah"),
                rs3.getString("instagram"),
                String.valueOf(rs3.getInt("blacklist")));
                ListCustomerStudio.add(newCK);
                if (ListCustomerStudio.get(i).getStatus().equals("0")||ListCustomerStudio.get(i).getStatus().equals("Verified")) {
                    ListCustomerStudio.get(i).setStatus("Verified");
                }
                else {
                    ListCustomerStudio.get(i).setStatus("Blacklist");
                }
                i++;
                   Object []kolom = {newCK.getId(), newCK.getNama(), newCK.getNo_hp(), newCK.getAlamat(), newCK.getInstagram(), newCK.getStatus()};
                   dataCustomer.addRow(kolom);
                   
                   }
               
               dataCustomer.fireTableDataChanged();
           }catch (Exception e){
                JOptionPane.showMessageDialog(null,"error :"+e.getMessage());
                e.printStackTrace();
                } 
    }
    
    public void connectDatabasePeminjaman(){
        txtPenyewaanStudio.setModel(dataPeminjaman);
        Statement st;
        ResultSet rs;
        String viewPeminjaman = "select * from V_PeminjamanStudio";
           try {
               st = new Koneksi().getCon().createStatement();
               rs = st.executeQuery(viewPeminjaman);
               while(rs.next()){
                   //Ngerjain yang ini
                   PenyewaanStudio PS = new PenyewaanStudio(
                           rs.getInt("id"), 
                           rs.getInt("id_cust"), 
                           rs.getString("nama_cust"), 
                           rs.getInt("id_item"), 
                           rs.getString("nama_item"), 
                           rs.getString("tgl_sewa"), 
                           rs.getString("jam_mulai"), 
                           rs.getString("jam_berakhir"), 
                           rs.getInt("harga"), 
                           rs.getInt("dp"), 
                           rs.getInt("diskon"), 
                           rs.getInt("denda"), 
                           rs.getInt("jml"), 
                           rs.getString("cara_bayar"));
                   
                   ListPeminjamanStudio.add(PS);
                   
                   Object []kolom = {PS.getId(),
                           PS.getNama_cust(),
                           PS.getNama_item(),
                           PS.getTgl_sewa(),
                           PS.getJam_mulai(),
                           PS.getJam_berakhir(),
                           PS.getHarga(),
                           PS.getDp(),
                           PS.getDiskon(),
                           PS.getDenda(),
                           PS.getJml(),
                           PS.getCara_bayar()};
                   
                   dataPeminjaman.addRow(kolom);
                   
                   }
               
               dataPeminjaman.fireTableDataChanged();
           }catch (Exception e){
                JOptionPane.showMessageDialog(null,"error :"+e.getMessage());
                e.printStackTrace();
                }
    }
    
    public void connectDatabaseHarga(){
        //tblPeminjamanStudio.setModel(dataPeminjaman);
        Statement st;
        ResultSet rs;
        String viewPeminjaman = "select * from V_HargaItemStudio";
           try {
               st = new Koneksi().getCon().createStatement();
               rs = st.executeQuery(viewPeminjaman);
               while(rs.next()){
                   //Ngerjain yang ini
                   HargaItemStudio HIS = new HargaItemStudio(
                           rs.getInt("id"), 
                           rs.getInt("id_item"), 
                           rs.getString("item"), 
                           rs.getString("nama_paket"), 
                           rs.getInt("harga_1_jam"), 
                           rs.getInt("harga_2_jam"), 
                           rs.getInt("harga_tambahan"));
                   ListHargaItemStudio.add(HIS);
                   
                   
                   }
               
           }catch (Exception e){
                JOptionPane.showMessageDialog(null,"error :"+e.getMessage());
                e.printStackTrace();
                } 
        
            
    }
    
    public void updateTablePeminjaman() {
        int rowCount = dataPeminjaman.getRowCount()-1;
        
        for (int i = rowCount; i >= 0; i--) {
            dataPeminjaman.removeRow(i);
        }
        for (PenyewaanStudio PS : ListPeminjamanStudio) {
            Object []kolom = {PS.getId(),
                           PS.getNama_cust(),
                           PS.getNama_item(),
                           PS.getTgl_sewa(),
                           PS.getJam_mulai(),
                           PS.getJam_berakhir(),
                           PS.getHarga(),
                           PS.getDp(),
                           PS.getDiskon(),
                           PS.getDenda(),
                           PS.getJml(),
                           PS.getCara_bayar()};         
            dataPeminjaman.addRow(kolom);
        }

        dataPeminjaman.fireTableDataChanged();
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtPenyewaanStudio = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        txtSearchPenyewaan = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCustomer = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtSearchCustomer = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
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
            .addGap(0, 100, Short.MAX_VALUE)
        );

        txtPenyewaanStudio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        txtPenyewaanStudio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                txtPenyewaanStudioMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(txtPenyewaanStudio);

        jLabel6.setText("Search Transaksi Penyewaan");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 995, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtSearchPenyewaan)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 10, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtSearchPenyewaan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Tabel Peminjaman", jPanel3);

        tblCustomer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblCustomer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCustomerMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblCustomerMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblCustomer);

        jLabel8.setText("Search Customer :");

        txtSearchCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchCustomerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 995, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(txtSearchCustomer)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtSearchCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87))
        );

        jTabbedPane1.addTab("Tabel Customer", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1024, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchCustomerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchCustomerActionPerformed

    private void tblCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCustomerMouseClicked
        
    }//GEN-LAST:event_tblCustomerMouseClicked

    private void tblCustomerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCustomerMousePressed
      
    }//GEN-LAST:event_tblCustomerMousePressed

    private void txtPenyewaanStudioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPenyewaanStudioMousePressed

    }//GEN-LAST:event_txtPenyewaanStudioMousePressed

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
            java.util.logging.Logger.getLogger(ViewPenyewaanStudio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewPenyewaanStudio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewPenyewaanStudio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewPenyewaanStudio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewPenyewaanStudio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ViewPenyewaanStudio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ViewPenyewaanStudio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ViewPenyewaanStudio.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewPenyewaanStudio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblCustomer;
    private javax.swing.JTable txtPenyewaanStudio;
    private javax.swing.JTextField txtSearchCustomer;
    private javax.swing.JTextField txtSearchPenyewaan;
    // End of variables declaration//GEN-END:variables
    
}