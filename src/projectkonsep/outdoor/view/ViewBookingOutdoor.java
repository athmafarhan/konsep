/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectkonsep.Outdoor.view;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import projectkonsep.outdoor.CustomerOutdoor;
import projectkonsep.outdoor.InventarisOutdoor;
import projectkonsep.outdoor.PinjamKembaliOutdoor;

/**
 *
 * @author Athma Farhan
 */
public class ViewBookingOutdoor extends javax.swing.JFrame {
    String sqlinput = "INSERT INTO `booking`\n" +
"            (`id`, `id_cust`, `id_inven`, `tgl_sewa`, `jam_sewa`, `tgl_kembali`, `jam_kembali`, `cara_bayar`, `dp`, `diskon`, `denda`) \n" +
"            VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    
    SimpleDateFormat sdfThnBlnTgl = new SimpleDateFormat("yyyy-MM-dd");

    
    int jumlahSewa = 0;
    boolean tambah1 = false;
    boolean tambah2 = false;
    boolean tambah3 = false;
    boolean tambah4 = false;

    LinkedHashMap<String, Integer> hashInventaris = new LinkedHashMap<>();
    Collection<String> keyInventaris;

    private TableRowSorter<TableModel> rowSorterBooking;
    private TableRowSorter<TableModel> rowSorterCustomer;
    
    ArrayList<PinjamKembaliOutdoor> ListBookingOutdoor = new ArrayList<>();
    ArrayList<CustomerOutdoor> ListCustomerOutdoor = new ArrayList<>();
    ArrayList<InventarisOutdoor> ListInventarisOutdoor = new ArrayList<>();
    
    Object[] headerCustomer = {"ID", "Nama", "Nomor HP", "Alamat", "Instagram", "Status"};
    DefaultTableModel dataCustomer = new DefaultTableModel(null, headerCustomer);
    
    Object[] headerBooking = {"ID", "Customer", "Inventaris", "Tgl Sewa", "Jam Sewa", "Tgl Kembali", "Jam Kembali", "Harga", "DP", "Diskon", "Denda", "Jumlah", "Cara Bayar"};
    DefaultTableModel dataBooking = new DefaultTableModel(null, headerBooking);
    
    String nama_cust;
    
    int index;

        
    /**
     * Creates new form FormTransaksiOutdoor
     */
    public ViewBookingOutdoor() {
        
    
        initComponents();
        connectDatabase();
        connectDatabaseBooking();
        
        searchTabelCustomer();
    }
    
    public final void searchTabelBooking() {
        this.rowSorterBooking = new TableRowSorter<>(tblBookingOutdoor.getModel());
        tblBookingOutdoor.setRowSorter(rowSorterBooking);
        txtSearchBooking.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = txtSearchBooking.getText();

                if (text.trim().length() == 0) {
                    rowSorterBooking.setRowFilter(null);
                } else {
                    rowSorterBooking.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = txtSearchBooking.getText();

                if (text.trim().length() == 0) {
                    rowSorterBooking.setRowFilter(null);
                } else {
                    rowSorterBooking.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            });
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
    
    public void connectDatabase(){
        tblCustomer.setModel(dataCustomer);
        Statement st3;
        ResultSet rs3;
        String viewJenisOutdoor = "SELECT * FROM `ou_cust` ";
           try {
               st3 = new Koneksi().getCon().createStatement();
               rs3 = st3.executeQuery(viewJenisOutdoor);
               int i = 0;
            while (rs3.next() == true){
                CustomerOutdoor newCK = new CustomerOutdoor(
                rs3.getInt("id"),
                rs3.getString("nama"),
                rs3.getString("no_hp"),
                rs3.getString("alamat_rumah"),
                rs3.getString("instagram"),
                String.valueOf(rs3.getInt("blacklist")));
                ListCustomerOutdoor.add(newCK);
                if (ListCustomerOutdoor.get(i).getStatus().equals("0")) {
                    ListCustomerOutdoor.get(i).setStatus("Verified");
                }
                else {
                    ListCustomerOutdoor.get(i).setStatus("Blacklist");
                }
                i++;
                   Object []kolom = {newCK.getId(), newCK.getNama(), newCK.getNo_hp(), newCK.getAlamat_rumah(), newCK.getInstagram(), newCK.getStatus()};
                   dataCustomer.addRow(kolom);
                   
                   }
               
               dataCustomer.fireTableDataChanged();
           }catch (Exception e){
                JOptionPane.showMessageDialog(null,"error :"+e.getMessage());
                e.printStackTrace();
                } 
    }
    
    public void connectDatabaseBooking(){
        tblBookingOutdoor.setModel(dataBooking);
        Statement st;
        ResultSet rs;
        String viewBooking = "select * from V_BookingOutdoor";
           try {
               st = new Koneksi().getCon().createStatement();
               rs = st.executeQuery(viewBooking);
               while(rs.next()){
                   //Ngerjain yang ini
                   PinjamKembaliOutdoor PK = new PinjamKembaliOutdoor(
                           rs.getInt("id"),
                           rs.getInt("id_cust"),
                           rs.getString("nama_cust"),
                           rs.getInt("id_inventaris"),
                           rs.getString("nama_inventaris"),
                           rs.getString("tgl_sewa"),
                           rs.getString("jam_sewa"),
                           rs.getString("tgl_kembali"),
                           rs.getString("jam_kembali"),
                           rs.getInt("harga"),
                           rs.getInt("dp"),
                           rs.getInt("diskon"),
                           rs.getInt("denda"),
                           rs.getInt("jml"),
                           rs.getString("cara_bayar"));
                   
                   
                   ListBookingOutdoor.add(PK);
                   Object []kolom = {rs.getInt("id"),
                           rs.getString("nama_cust"),
                           rs.getString("nama_inventaris"),
                           rs.getString("tgl_sewa"),
                           rs.getString("jam_sewa"),
                           rs.getString("tgl_kembali"),
                           rs.getString("jam_kembali"),
                           rs.getInt("harga"),
                           rs.getInt("dp"),
                           rs.getInt("diskon"),
                           rs.getInt("denda"),
                           rs.getInt("jml"),
                           rs.getString("cara_bayar")};
                   
                   dataBooking.addRow(kolom);
                   
                   }
               
               dataBooking.fireTableDataChanged();
           }catch (Exception e){
                JOptionPane.showMessageDialog(null,"error :"+e.getMessage());
                e.printStackTrace();
                } 
        
            
    }
    
    public void updateTableBooking() {
        int rowCount = dataBooking.getRowCount()-1;
        
        for (int i = rowCount; i >= 0; i--) {
            dataBooking.removeRow(i);
        }
        for (PinjamKembaliOutdoor PKK : ListBookingOutdoor) {
            Object []kolom = {PKK.getId(),
                           PKK.getNama_cust(),
                           PKK.getNama_inventaris(),
                           PKK.getTgl_sewa(),
                           PKK.getJam_sewa(),
                           PKK.getTgl_kembali(),
                           PKK.getJam_kembali(),
                           PKK.getHarga(),
                           PKK.getDp(),
                           PKK.getDiskon(),
                           PKK.getDenda(),
                           PKK.getJml(),
                           PKK.getCara_bayar()};          
            dataBooking.addRow(kolom);
        }

        dataBooking.fireTableDataChanged();
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
        tblBookingOutdoor = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtSearchBooking = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCustomer = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtSearchCustomer = new javax.swing.JTextField();

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
            .addGap(0, 100, Short.MAX_VALUE)
        );

        tblBookingOutdoor.setModel(new javax.swing.table.DefaultTableModel(
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
        tblBookingOutdoor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblBookingOutdoorMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblBookingOutdoor);

        jLabel1.setText("Search Transaksi Booking");

        jLabel2.setText(":");

        txtSearchBooking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchBookingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 995, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearchBooking)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 28, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(txtSearchBooking, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Tabel Booking", jPanel3);

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void tblBookingOutdoorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBookingOutdoorMousePressed

    }//GEN-LAST:event_tblBookingOutdoorMousePressed

    private void txtSearchBookingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchBookingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchBookingActionPerformed

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
            java.util.logging.Logger.getLogger(ViewBookingOutdoor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewBookingOutdoor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewBookingOutdoor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewBookingOutdoor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
            Logger.getLogger(ViewBookingOutdoor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ViewBookingOutdoor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ViewBookingOutdoor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ViewBookingOutdoor.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewBookingOutdoor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblCustomer;
    private javax.swing.JTable tblBookingOutdoor;
    private javax.swing.JTextField txtSearchCustomer;
    private javax.swing.JTextField txtSearchBooking;
    // End of variables declaration//GEN-END:variables
    
}