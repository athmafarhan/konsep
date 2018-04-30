/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectkonsep.kamera.view;

import projectkonsep.outdoor.*;
import projectkonsep.kamera.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import projectkonsep.Koneksi;
import projectkonsep.kamera.KeteranganInventarisKamera;

/**
 *
 * @author Athma Farhan
 */
public class ViewKetInventarisKamera extends javax.swing.JFrame {

    
    
    private DefaultTableModel tabmode;

    Connection con = null;


    int index = -1;

    Object[] headerCustomer = {
        "ID",
        "ket1", 
        "ket2", 
        "ket3", 
        "ket4", 
        "ket5", 
        "ket6", 
        "ket7", 
        "ket8", 
        "ket9", 
        "ket10", 
        "ket11", 
        "ket12", 
        "ket13", 
        "ket14", 
        "ket15"};
    DefaultTableModel dataCustomer = new DefaultTableModel(null, headerCustomer);
    
    ArrayList<KeteranganInventarisKamera> ListKetInvenKamera = new ArrayList<>();//

    /**
     * Creates new form FormCustomerKamera
     */
    public ViewKetInventarisKamera() {
        initComponents();
        connectDatabase();
        //updateTable();
    }
    
    
    
   
    
    public void connectDatabase(){
        tblCustomerKamera.setModel(dataCustomer);
        Statement st;
        ResultSet rs;
        String viewJenisKamera = "select * from km_ket_inventaris";
           try {
               st = new Koneksi().getCon().createStatement();
               rs = st.executeQuery(viewJenisKamera);
               int i = 0;
               while(rs.next()){
                   
                   KeteranganInventarisKamera KIK = new KeteranganInventarisKamera(
                           rs.getInt("id_inventaris"), 
                           rs.getString("ket1"), 
                           rs.getString("ket2"), 
                           rs.getString("ket3"), 
                           rs.getString("ket4"),
                           rs.getString("ket5"), 
                           rs.getString("ket6"), 
                           rs.getString("ket7"), 
                           rs.getString("ket8"),
                           rs.getString("ket9"), 
                           rs.getString("ket10"), 
                           rs.getString("ket11"), 
                           rs.getString("ket12"),
                           rs.getString("ket13"), 
                           rs.getString("ket14"), 
                           rs.getString("ket15"));
                   ListKetInvenKamera.add(KIK);
                
                i++;
                   
                   Object []kolom = {
                       KIK.getId_inventaris(), 
                       KIK.getKet1(), 
                       KIK.getKet2(), 
                       KIK.getKet3(), 
                       KIK.getKet4(), 
                       KIK.getKet5(), 
                       KIK.getKet6(), 
                       KIK.getKet7(), 
                       KIK.getKet8(), 
                       KIK.getKet9(), 
                       KIK.getKet10(), 
                       KIK.getKet11(), 
                       KIK.getKet12(), 
                       KIK.getKet13(), 
                       KIK.getKet14(), 
                       KIK.getKet15()};
                   
                   dataCustomer.addRow(kolom);
                   
                   }
               
               dataCustomer.fireTableDataChanged();
           }catch (Exception e){
                JOptionPane.showMessageDialog(null,"error :"+e.getMessage());
                e.printStackTrace();
                } 
        
            
    }
        
    
    
    public void updateTableAccesories() {
        int rowCount = dataCustomer.getRowCount()-1;
        
        for (int i = rowCount; i >= 0; i--) {
            dataCustomer.removeRow(i);
        }
        for (KeteranganInventarisKamera KIK : ListKetInvenKamera) {
            Object []kolom = {
                       KIK.getId_inventaris(), 
                       KIK.getKet1(), 
                       KIK.getKet2(), 
                       KIK.getKet3(), 
                       KIK.getKet4(), 
                       KIK.getKet5(), 
                       KIK.getKet6(), 
                       KIK.getKet7(), 
                       KIK.getKet8(), 
                       KIK.getKet9(), 
                       KIK.getKet10(), 
                       KIK.getKet11(), 
                       KIK.getKet12(), 
                       KIK.getKet13(), 
                       KIK.getKet14(), 
                       KIK.getKet15()};
            dataCustomer.addRow(kolom);
        }

        dataCustomer.fireTableDataChanged();
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
        tblCustomerKamera = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

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

        tblCustomerKamera.setModel(new javax.swing.table.DefaultTableModel(
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
        tblCustomerKamera.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCustomerKameraMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCustomerKamera);

        jLabel1.setText("Search");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField1)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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

    private void tblCustomerKameraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCustomerKameraMouseClicked

    }//GEN-LAST:event_tblCustomerKameraMouseClicked

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

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
            java.util.logging.Logger.getLogger(ViewKetInventarisKamera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewKetInventarisKamera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewKetInventarisKamera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewKetInventarisKamera.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewKetInventarisKamera().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTable tblCustomerKamera;
    // End of variables declaration//GEN-END:variables

}

