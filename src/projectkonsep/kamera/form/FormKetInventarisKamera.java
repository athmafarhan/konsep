/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectkonsep.kamera.form;

import java.awt.Color;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import projectkonsep.Koneksi;
import projectkonsep.kamera.KeteranganInventarisKamera;
import projectkonsep.menu.MenuInventarisKamera;

/**
 *
 * @author Athma Farhan
 */
public class FormKetInventarisKamera extends javax.swing.JFrame {

    
    
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
    public FormKetInventarisKamera() {
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
        lblIDCust = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        lblKeterangan1 = new javax.swing.JLabel();
        txtKeterangan1 = new javax.swing.JTextField();
        lblKeterangan2 = new javax.swing.JLabel();
        txtKeterangan2 = new javax.swing.JTextField();
        txtKeterangan3 = new javax.swing.JTextField();
        lblKeterangan3 = new javax.swing.JLabel();
        txtKeterangan4 = new javax.swing.JTextField();
        lblKeterangan4 = new javax.swing.JLabel();
        txtKeterangan5 = new javax.swing.JTextField();
        lblKeterangan5 = new javax.swing.JLabel();
        txtKeterangan6 = new javax.swing.JTextField();
        lblKeterangan6 = new javax.swing.JLabel();
        txtKeterangan7 = new javax.swing.JTextField();
        lblKeterangan7 = new javax.swing.JLabel();
        txtKeterangan8 = new javax.swing.JTextField();
        lblKeterangan8 = new javax.swing.JLabel();
        lblKeterangan9 = new javax.swing.JLabel();
        txtKeterangan9 = new javax.swing.JTextField();
        txtKeterangan10 = new javax.swing.JTextField();
        lblKeterangan10 = new javax.swing.JLabel();
        txtKeterangan11 = new javax.swing.JTextField();
        lblKeterangan11 = new javax.swing.JLabel();
        txtKeterangan12 = new javax.swing.JTextField();
        lblKeterangan12 = new javax.swing.JLabel();
        txtKeterangan13 = new javax.swing.JTextField();
        lblKeterangan13 = new javax.swing.JLabel();
        lblKeterangan14 = new javax.swing.JLabel();
        txtKeterangan14 = new javax.swing.JTextField();
        lblKeterangan15 = new javax.swing.JLabel();
        txtKeterangan15 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblCustomerKamera = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();

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

        lblIDCust.setText("ID Inventaris");

        txtID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDActionPerformed(evt);
            }
        });

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

        lblKeterangan1.setText("Keterangan 1");

        txtKeterangan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKeterangan1ActionPerformed(evt);
            }
        });

        lblKeterangan2.setText("Keterangan 2");

        txtKeterangan2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKeterangan2ActionPerformed(evt);
            }
        });

        txtKeterangan3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKeterangan3ActionPerformed(evt);
            }
        });

        lblKeterangan3.setText("Keterangan 3");

        txtKeterangan4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKeterangan4ActionPerformed(evt);
            }
        });

        lblKeterangan4.setText("Keterangan 4");

        txtKeterangan5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKeterangan5ActionPerformed(evt);
            }
        });

        lblKeterangan5.setText("Keterangan 5");

        txtKeterangan6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKeterangan6ActionPerformed(evt);
            }
        });

        lblKeterangan6.setText("Keterangan 6");

        txtKeterangan7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKeterangan7ActionPerformed(evt);
            }
        });

        lblKeterangan7.setText("Keterangan 7");

        txtKeterangan8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKeterangan8ActionPerformed(evt);
            }
        });

        lblKeterangan8.setText("Keterangan 8");

        lblKeterangan9.setText("Keterangan 9");

        txtKeterangan9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKeterangan9ActionPerformed(evt);
            }
        });

        txtKeterangan10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKeterangan10ActionPerformed(evt);
            }
        });

        lblKeterangan10.setText("Keterangan 10");

        txtKeterangan11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKeterangan11ActionPerformed(evt);
            }
        });

        lblKeterangan11.setText("Keterangan 11");

        txtKeterangan12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKeterangan12ActionPerformed(evt);
            }
        });

        lblKeterangan12.setText("Keterangan 12");

        txtKeterangan13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKeterangan13ActionPerformed(evt);
            }
        });

        lblKeterangan13.setText("Keterangan 13");

        lblKeterangan14.setText("Keterangan 14");

        txtKeterangan14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKeterangan14ActionPerformed(evt);
            }
        });

        lblKeterangan15.setText("Keterangan 15");

        txtKeterangan15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtKeterangan15ActionPerformed(evt);
            }
        });

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
        jScrollPane2.setViewportView(tblCustomerKamera);

        jButton7.setText("New");
        jButton7.setBorder(null);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Reset");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("Delete");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("KEMBALI");
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
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lblKeterangan4)
                        .addComponent(lblKeterangan5)
                        .addComponent(lblKeterangan3)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblKeterangan9)
                                .addComponent(lblKeterangan10)
                                .addComponent(lblKeterangan11)
                                .addComponent(lblKeterangan12, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblKeterangan13)
                                .addComponent(lblKeterangan14)
                                .addComponent(lblKeterangan15))
                            .addGap(22, 22, 22)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtKeterangan12)
                                .addComponent(txtKeterangan11)
                                .addComponent(txtKeterangan9)
                                .addComponent(txtKeterangan10)
                                .addComponent(txtKeterangan13)
                                .addComponent(txtKeterangan14)
                                .addComponent(txtKeterangan15, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(lblIDCust)
                                        .addGap(34, 34, 34))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(lblKeterangan1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblKeterangan2)
                                        .addComponent(lblKeterangan6)
                                        .addComponent(lblKeterangan7)
                                        .addComponent(lblKeterangan8))
                                    .addGap(29, 29, 29)))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtKeterangan8)
                                .addComponent(txtKeterangan7)
                                .addComponent(txtKeterangan6)
                                .addComponent(txtKeterangan2)
                                .addComponent(txtID)
                                .addComponent(txtKeterangan1, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtKeterangan3, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtKeterangan5, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtKeterangan4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 699, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblIDCust)
                            .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblKeterangan1)
                            .addComponent(txtKeterangan1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblKeterangan2)
                            .addComponent(txtKeterangan2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblKeterangan3)
                            .addComponent(txtKeterangan3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblKeterangan4)
                            .addComponent(txtKeterangan4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblKeterangan5)
                            .addComponent(txtKeterangan5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblKeterangan6)
                            .addComponent(txtKeterangan6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblKeterangan7)
                            .addComponent(txtKeterangan7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblKeterangan8)
                            .addComponent(txtKeterangan8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblKeterangan9)
                            .addComponent(txtKeterangan9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblKeterangan10)
                            .addComponent(txtKeterangan10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblKeterangan11)
                            .addComponent(txtKeterangan11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblKeterangan12)
                            .addComponent(txtKeterangan12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblKeterangan13)
                            .addComponent(txtKeterangan13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblKeterangan14)
                            .addComponent(txtKeterangan14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblKeterangan15)
                            .addComponent(txtKeterangan15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton6)
                        .addComponent(jButton9)
                        .addComponent(jButton10))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton8))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton2)
                        .addComponent(jButton3)
                        .addComponent(jButton4)
                        .addComponent(jButton5)))
                .addGap(11, 11, 11))
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
        boolean berhasil = false;
        int id_inventaris = 0;
        String ket1 = txtKeterangan1.getText();
        String ket2 = txtKeterangan2.getText();
        String ket3 = txtKeterangan3.getText();
        String ket4 = txtKeterangan4.getText();
        String ket5 = txtKeterangan5.getText();
        String ket6 = txtKeterangan6.getText();
        String ket7 = txtKeterangan7.getText();
        String ket8 = txtKeterangan8.getText();
        String ket9 = txtKeterangan9.getText();
        String ket10 = txtKeterangan10.getText();
        String ket11 = txtKeterangan11.getText();
        String ket12 = txtKeterangan12.getText();
        String ket13 = txtKeterangan13.getText();
        String ket14 = txtKeterangan14.getText();
        String ket15 = txtKeterangan15.getText();
        
        try {
            id_inventaris = Integer.parseInt(txtID.getText());

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,"Data yang Anda Masukkan Salah", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
        }

        

        try {
            Connection con = new Koneksi().getCon();
            PreparedStatement myPreparedStatement = null;
            String sql = "INSERT INTO `cust`"
            + "(`id`, `nama`, `no_hp`, `alamat_rumah`, `instagram`, `blacklist`)"
            + " VALUES (?, ?, ?, ?, ?, ?)";
            
            String sqlinput = "INSERT INTO `km_ket_inventaris`"
                    + "(`id_inventaris`, `ket1`, `ket2`, `ket3`, `ket4`, `ket5`, `ket6`, `ket7`, `ket8`, `ket9`, `ket10`, `ket11`, `ket12`, `ket13`, `ket14`, `ket15`) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            myPreparedStatement = con.prepareStatement(sqlinput);
            myPreparedStatement.setInt(1, id_inventaris);
            myPreparedStatement.setString(2, ket1);
            myPreparedStatement.setString(3, ket2);
            myPreparedStatement.setString(4, ket3);
            myPreparedStatement.setString(5, ket4);
            myPreparedStatement.setString(6, ket5);
            myPreparedStatement.setString(7, ket6);
            myPreparedStatement.setString(8, ket7);
            myPreparedStatement.setString(9, ket8);
            myPreparedStatement.setString(10, ket9);
            myPreparedStatement.setString(11, ket10);
            myPreparedStatement.setString(12, ket11);
            myPreparedStatement.setString(13, ket12);
            myPreparedStatement.setString(14, ket13);
            myPreparedStatement.setString(15, ket14);
            myPreparedStatement.setString(16, ket15);
            myPreparedStatement.executeUpdate();
            berhasil = true;
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(this,"Penambahan Gagal", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
            berhasil = false;
            e.printStackTrace();
        }
        if (berhasil == true) {
            KeteranganInventarisKamera KIK = new KeteranganInventarisKamera();
            KIK.setId_inventaris(Integer.parseInt(txtID.getText()));
            KIK.setKet1(txtKeterangan1.getText());
            KIK.setKet2(txtKeterangan1.getText());
            KIK.setKet3(txtKeterangan1.getText());
            KIK.setKet4(txtKeterangan1.getText());
            KIK.setKet5(txtKeterangan1.getText());
            KIK.setKet6(txtKeterangan1.getText());
            KIK.setKet7(txtKeterangan1.getText());
            KIK.setKet8(txtKeterangan1.getText());
            KIK.setKet9(txtKeterangan1.getText());
            KIK.setKet10(txtKeterangan1.getText());
            KIK.setKet11(txtKeterangan1.getText());
            KIK.setKet12(txtKeterangan1.getText());
            KIK.setKet13(txtKeterangan1.getText());
            KIK.setKet14(txtKeterangan1.getText());
            KIK.setKet15(txtKeterangan1.getText());
            this.ListKetInvenKamera.add(KIK);
            JOptionPane.showMessageDialog(this,"Penambahan Sukses", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
            updateTableAccesories();
        }
        else if (berhasil == false) {
            JOptionPane.showMessageDialog(this,"Penambahan Gagal", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        index = 0;
        showData();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       index = ListKetInvenKamera.size()-1;
       showData();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            ++index;
            showData();
        
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            JOptionPane.showMessageDialog(this,"Data yang dicari tidak ditemukan", "Informasi", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        try {
            --index;
            showData();
        } catch (IndexOutOfBoundsException | NullPointerException e) {
            JOptionPane.showMessageDialog(this,"Data yang dicari tidak ditemukan", "Informasi", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        boolean berhasil = false;
        int id_inventaris = 0;
        String ket1 = txtKeterangan1.getText();
        String ket2 = txtKeterangan2.getText();
        String ket3 = txtKeterangan3.getText();
        String ket4 = txtKeterangan4.getText();
        String ket5 = txtKeterangan5.getText();
        String ket6 = txtKeterangan6.getText();
        String ket7 = txtKeterangan7.getText();
        String ket8 = txtKeterangan8.getText();
        String ket9 = txtKeterangan9.getText();
        String ket10 = txtKeterangan10.getText();
        String ket11 = txtKeterangan11.getText();
        String ket12 = txtKeterangan12.getText();
        String ket13 = txtKeterangan13.getText();
        String ket14 = txtKeterangan14.getText();
        String ket15 = txtKeterangan15.getText();
        
        
        
        if (JOptionPane.showConfirmDialog(this, "Yakin akan diedit?", "Edit", 0) == 0) {
            try {
            id_inventaris = Integer.parseInt(txtID.getText());

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this,"Data yang Anda Masukkan Salah", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
            }

        

        

            try {
                Connection con = new Koneksi().getCon();
                PreparedStatement myPreparedStatement = null;

                String sqlupdate = "UPDATE `km_ket_inventaris` SET "
                + "`ket1`=?,`ket2`=?,`ket3`=?, `ket4`=?, `ket5`=?, `ket6`=?,`ket7`=?,`ket8`=?, `ket9`=?, `ket10`=?, `ket11`=?,`ket12`=?,`ket13`=?, `ket14`=?, `ket15`=? "
                        + "WHERE `id`=?";
                myPreparedStatement = con.prepareStatement(sqlupdate);

                myPreparedStatement.setString(1, ket1);
                myPreparedStatement.setString(2, ket2);
                myPreparedStatement.setString(3, ket3);
                myPreparedStatement.setString(4, ket4);
                myPreparedStatement.setString(5, ket5);
                myPreparedStatement.setString(6, ket6);
                myPreparedStatement.setString(7, ket7);
                myPreparedStatement.setString(8, ket8);
                myPreparedStatement.setString(9, ket9);
                myPreparedStatement.setString(10, ket10);
                myPreparedStatement.setString(11, ket11);
                myPreparedStatement.setString(12, ket12);
                myPreparedStatement.setString(13, ket13);
                myPreparedStatement.setString(14, ket14);
                myPreparedStatement.setString(15, ket15);
                myPreparedStatement.setInt(16, id_inventaris);

                myPreparedStatement.executeUpdate();
                berhasil = true;
            }
            catch(Exception e) {

                berhasil = false;
                e.printStackTrace();
            }
            if (berhasil == true) {
                KeteranganInventarisKamera KIK = new KeteranganInventarisKamera();
                KIK.setId_inventaris(Integer.parseInt(txtID.getText()));
                KIK.setKet1(txtKeterangan1.getText());
                KIK.setKet2(txtKeterangan1.getText());
                KIK.setKet3(txtKeterangan1.getText());
                KIK.setKet4(txtKeterangan1.getText());
                KIK.setKet5(txtKeterangan1.getText());
                KIK.setKet6(txtKeterangan1.getText());
                KIK.setKet7(txtKeterangan1.getText());
                KIK.setKet8(txtKeterangan1.getText());
                KIK.setKet9(txtKeterangan1.getText());
                KIK.setKet10(txtKeterangan1.getText());
                KIK.setKet11(txtKeterangan1.getText());
                KIK.setKet12(txtKeterangan1.getText());
                KIK.setKet13(txtKeterangan1.getText());
                KIK.setKet14(txtKeterangan1.getText());
                KIK.setKet15(txtKeterangan1.getText());
                this.ListKetInvenKamera.set(index, KIK);
                JOptionPane.showMessageDialog(this,"Penambahan Sukses", "Informasi",
                    JOptionPane.INFORMATION_MESSAGE);
                updateTableAccesories();
            }
            else if (berhasil == false) {
                JOptionPane.showMessageDialog(this,"Penambahan Gagal", "Informasi",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void txtKeterangan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKeterangan1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKeterangan1ActionPerformed

    private void txtKeterangan2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKeterangan2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKeterangan2ActionPerformed

    private void txtKeterangan3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKeterangan3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKeterangan3ActionPerformed

    private void txtKeterangan4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKeterangan4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKeterangan4ActionPerformed

    private void txtKeterangan5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKeterangan5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKeterangan5ActionPerformed

    private void txtKeterangan6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKeterangan6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKeterangan6ActionPerformed

    private void txtKeterangan7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKeterangan7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKeterangan7ActionPerformed

    private void txtKeterangan8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKeterangan8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKeterangan8ActionPerformed

    private void txtKeterangan9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKeterangan9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKeterangan9ActionPerformed

    private void txtKeterangan10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKeterangan10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKeterangan10ActionPerformed

    private void txtKeterangan11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKeterangan11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKeterangan11ActionPerformed

    private void txtKeterangan12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKeterangan12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKeterangan12ActionPerformed

    private void txtKeterangan13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKeterangan13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKeterangan13ActionPerformed

    private void txtKeterangan14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKeterangan14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKeterangan14ActionPerformed

    private void txtKeterangan15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtKeterangan15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtKeterangan15ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        int id = ListKetInvenKamera.get(ListKetInvenKamera.size()-1).getId_inventaris()+1;
        txtID.setText(String.valueOf(id));
        txtKeterangan1.setText("");
        txtKeterangan2.setText("");
        txtKeterangan3.setText("");
        txtKeterangan4.setText("");
        txtKeterangan5.setText("");
        txtKeterangan6.setText("");
        txtKeterangan7.setText("");
        txtKeterangan8.setText("");
        txtKeterangan9.setText("");
        txtKeterangan10.setText("");
        txtKeterangan11.setText("");
        txtKeterangan12.setText("");
        txtKeterangan13.setText("");
        txtKeterangan14.setText("");
        txtKeterangan15.setText("");
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        txtKeterangan1.setText("");
        txtKeterangan2.setText("");
        txtKeterangan3.setText("");
        txtKeterangan4.setText("");
        txtKeterangan5.setText("");
        txtKeterangan6.setText("");
        txtKeterangan7.setText("");
        txtKeterangan8.setText("");
        txtKeterangan9.setText("");
        txtKeterangan10.setText("");
        txtKeterangan11.setText("");
        txtKeterangan12.setText("");
        txtKeterangan13.setText("");
        txtKeterangan14.setText("");
        txtKeterangan15.setText("");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        boolean berhasil = false;
        if (JOptionPane.showConfirmDialog(this, "Yakin akan dihapus?", "Hapus", 0) == 0) {
            String ketikKONFIRMASI = JOptionPane.showInputDialog(this,
                        "Ketik KONFIRMASI untuk menghapus data", null);
            String key = "KONFIRMASI";
            
            if (ketikKONFIRMASI.equals(key)) {
                PreparedStatement myPreparedStatement = null;
                con = new Koneksi().getCon();
                String sqldelete = "DELETE FROM km_inventaris WHERE `id` =?";
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
                        Logger.getLogger(FormKetInventarisKamera.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    ListKetInvenKamera.remove(index);
                    JOptionPane.showMessageDialog(this, "Data berhasil dihapus!", "Informasi", 1);
                    index = -1;
                    updateTableAccesories();
                    txtID.setText("");
                    txtKeterangan1.setText("");
                    txtKeterangan2.setText("");
                    txtKeterangan3.setText("");
                    txtKeterangan4.setText("");
                    txtKeterangan5.setText("");
                    txtKeterangan6.setText("");
                    txtKeterangan7.setText("");
                    txtKeterangan8.setText("");
                    txtKeterangan9.setText("");
                    txtKeterangan10.setText("");
                    txtKeterangan11.setText("");
                    txtKeterangan12.setText("");
                    txtKeterangan13.setText("");
                    txtKeterangan14.setText("");
                    txtKeterangan15.setText("");
                }
            }
            else if (!ketikKONFIRMASI.equals(key)) {
                JOptionPane.showMessageDialog(this, "Data tidak berhasil dihapus", "Informasi", 1);
            }
            
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        this.dispose();
        java.awt.EventQueue.invokeLater(() -> {
            new MenuInventarisKamera().setVisible(true);
        });
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
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(FormPeminjamanKamera.class.getName()).log(Level.SEVERE, null, ex);
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

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormKetInventarisKamera().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblIDCust;
    private javax.swing.JLabel lblKeterangan1;
    private javax.swing.JLabel lblKeterangan10;
    private javax.swing.JLabel lblKeterangan11;
    private javax.swing.JLabel lblKeterangan12;
    private javax.swing.JLabel lblKeterangan13;
    private javax.swing.JLabel lblKeterangan14;
    private javax.swing.JLabel lblKeterangan15;
    private javax.swing.JLabel lblKeterangan2;
    private javax.swing.JLabel lblKeterangan3;
    private javax.swing.JLabel lblKeterangan4;
    private javax.swing.JLabel lblKeterangan5;
    private javax.swing.JLabel lblKeterangan6;
    private javax.swing.JLabel lblKeterangan7;
    private javax.swing.JLabel lblKeterangan8;
    private javax.swing.JLabel lblKeterangan9;
    private javax.swing.JTable tblCustomerKamera;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtKeterangan1;
    private javax.swing.JTextField txtKeterangan10;
    private javax.swing.JTextField txtKeterangan11;
    private javax.swing.JTextField txtKeterangan12;
    private javax.swing.JTextField txtKeterangan13;
    private javax.swing.JTextField txtKeterangan14;
    private javax.swing.JTextField txtKeterangan15;
    private javax.swing.JTextField txtKeterangan2;
    private javax.swing.JTextField txtKeterangan3;
    private javax.swing.JTextField txtKeterangan4;
    private javax.swing.JTextField txtKeterangan5;
    private javax.swing.JTextField txtKeterangan6;
    private javax.swing.JTextField txtKeterangan7;
    private javax.swing.JTextField txtKeterangan8;
    private javax.swing.JTextField txtKeterangan9;
    // End of variables declaration//GEN-END:variables

    private void showData() {
        KeteranganInventarisKamera KIK = ListKetInvenKamera.get(index);
        txtID.setText(String.valueOf(KIK.getId_inventaris()));
        txtKeterangan1.setText(KIK.getKet1());
        txtKeterangan2.setText(KIK.getKet2());
        txtKeterangan3.setText(KIK.getKet3());
        txtKeterangan4.setText(KIK.getKet4());
        txtKeterangan5.setText(KIK.getKet5());
        txtKeterangan6.setText(KIK.getKet6());
        txtKeterangan7.setText(KIK.getKet7());
        txtKeterangan8.setText(KIK.getKet8());
        txtKeterangan9.setText(KIK.getKet9());
        txtKeterangan10.setText(KIK.getKet10());
        txtKeterangan11.setText(KIK.getKet11());
        txtKeterangan12.setText(KIK.getKet12());
        txtKeterangan13.setText(KIK.getKet13());
        txtKeterangan14.setText(KIK.getKet14());
        txtKeterangan15.setText(KIK.getKet15());
    }
    
}

