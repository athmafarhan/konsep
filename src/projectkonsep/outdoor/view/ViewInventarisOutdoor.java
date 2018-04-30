/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectkonsep.outdoor.view;

import projectkonsep.outdoor.form.*;
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
import javax.swing.RowFilter;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import projectkonsep.Koneksi;
import projectkonsep.outdoor.InventarisOutdoor;
import projectkonsep.outdoor.JenisOutdoor;
import projectkonsep.outdoor.MerkOutdoor;

/**
 *
 * @author Athma Farhan
 */
public class ViewInventarisOutdoor extends javax.swing.JFrame {
    private TableRowSorter<TableModel> rowSorterInventaris;
    
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

    /**
     * Creates new form FormCustomerOutdoor
     */
    public ViewInventarisOutdoor() {
        initComponents();
        connectDatabase();
    }
    
    public final void searchTabelCustomer() {
        this.rowSorterInventaris = new TableRowSorter<>(tblInventarisOutdoor.getModel());
        tblInventarisOutdoor.setRowSorter(rowSorterInventaris);
        txtSearchInventaris.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = txtSearchInventaris.getText();

                if (text.trim().length() == 0) {
                    rowSorterInventaris.setRowFilter(null);
                } else {
                    rowSorterInventaris.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = txtSearchInventaris.getText();

                if (text.trim().length() == 0) {
                    rowSorterInventaris.setRowFilter(null);
                } else {
                    rowSorterInventaris.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblInventarisOutdoor = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtSearchInventaris = new javax.swing.JTextField();

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

        jLabel1.setText("Search Inventaris :");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSearchInventaris)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 7, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSearchInventaris, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void tblInventarisOutdoorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblInventarisOutdoorMouseClicked
        
    }//GEN-LAST:event_tblInventarisOutdoorMouseClicked

    private void tblInventarisOutdoorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblInventarisOutdoorMousePressed

    }//GEN-LAST:event_tblInventarisOutdoorMousePressed

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
            java.util.logging.Logger.getLogger(ViewInventarisOutdoor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewInventarisOutdoor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewInventarisOutdoor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewInventarisOutdoor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
            Logger.getLogger(ViewInventarisOutdoor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ViewInventarisOutdoor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ViewInventarisOutdoor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ViewInventarisOutdoor.class.getName()).log(Level.SEVERE, null, ex);
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewInventarisOutdoor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblInventarisOutdoor;
    private javax.swing.JTextField txtSearchInventaris;
    // End of variables declaration//GEN-END:variables

}

