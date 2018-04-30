/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectkonsep;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import projectkonsep.kamera.CetakBuktiKamera;
import projectkonsep.kamera.PinjamKembaliKamera;
import projectkonsep.studio.CetakBuktiStudio;
import projectkonsep.studio.form.FormHargaItemStudio;

/**
 *
 * @author Athma Farhan
 */
public class FormCetakBuktiStudio extends javax.swing.JFrame {
    Object[] headerPeminjaman = {"No", "Jenis Kamera", "Keterangan", "Jumlah Hari", "Tanggal Kembali", "Harga Sewa"};
    DefaultTableModel dataPeminjaman = new DefaultTableModel(null, headerPeminjaman);
    
    ArrayList<CetakBuktiStudio> ListPeminjamanKamera = new ArrayList<>();
    
    SimpleDateFormat sdfTglBlnThn = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat sdfPukul = new SimpleDateFormat("HH:mm");
    
    Date a = new Date();
    
    PrinterJob pj = PrinterJob.getPrinterJob();
    PageFormat pageFormat = pj.defaultPage();
    
    
    
    /**
     * Creates new form Cobacoba
     */
    public FormCetakBuktiStudio() {
        initComponents();
        lblTanggalValue.setText(sdfTglBlnThn.format(a));
        lblPukulValue.setText(sdfPukul.format(a));
        connectDatabasePeminjaman();
    }

    public void connectDatabasePeminjaman(){
        tblPeminjamanKamera.setModel(dataPeminjaman);
        Statement st;
        ResultSet rs;
        String viewPeminjaman = "select * from V_CetakBuktiStudio where `id`=1";
        int indexNomor = 1;
        Date tgl_sewa = null;
        int hargaJumlah = 0;
        
            try {
                st = new Koneksi().getCon().createStatement();
                rs = st.executeQuery(viewPeminjaman);
                while(rs.next()){
                    CetakBuktiStudio CBS = new CetakBuktiStudio(
                            rs.getInt("id"), 
                            rs.getInt("id_cust"), 
                            rs.getString("nama_cust"), 
                            rs.getString("no_hp_cust"), 
                            rs.getString("instagram_cust"), 
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
                    
                    String jam_mulai = CBS.getJam_mulai();
                    String jam_berakhir = CBS.getJam_berakhir();
                    
                    String tanggal_sewa = CBS.getTgl_sewa();
                    try {
                       tgl_sewa = sdfTglBlnThn.parse(tanggal_sewa);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                    
                    ListPeminjamanKamera.add(CBS);
                    lblNoTransValue.setText(String.valueOf(CBS.getId()));
                    lblNamaValue.setText(CBS.getNama_cust());
                    lblNoHPValue.setText(CBS.getNo_hp_cust());
                    lblInstagramValue.setText(CBS.getInstagram());
                    lblDownPaymentValue.setText(String.valueOf(CBS.getDp()));
                    lblCaraBayarValue.setText(CBS.getCara_bayar());
                    lblJumlahItemValue.setText(String.valueOf(indexNomor));
                    hargaJumlah = CBS.getHarga()-CBS.getDiskon();
                    Object []kolom = {
                        indexNomor,
                        CBS.getNama_item(),
                        "",
                        tanggal_sewa,
                        jam_mulai,
                        jam_berakhir,
                        hargaJumlah};
                    
                   dataPeminjaman.addRow(kolom);
                   
                   indexNomor++;
                   }
               
               dataPeminjaman.fireTableDataChanged();
           }catch (Exception e){
                JOptionPane.showMessageDialog(null,"error :"+e.getMessage());
                e.printStackTrace();
              }
            int jumlahDP = 0;
            int hargaJumlahSewa = 0;
            hargaJumlah = 0;
            for (CetakBuktiStudio CBS : ListPeminjamanKamera) {
                hargaJumlah = CBS.getHarga()-CBS.getDiskon();
                jumlahDP += CBS.getDp();
                hargaJumlahSewa += hargaJumlah;
            }
            lblDownPaymentValue.setText(String.valueOf(jumlahDP));
            lblTotalSewaValue.setText(String.valueOf(hargaJumlahSewa-jumlahDP));
    }
    
    public void printWork()
    {
        
        pj.setJobName(" Opt De Solver Printing ");

        pj.setPrintable(new Printable()
        {
            @Override
            public int print(Graphics pg , PageFormat pf, int pageNum)
            {
                if(pageNum > 0)
                    return Printable.NO_SUCH_PAGE;

                Graphics2D g2 = (Graphics2D)pg;
                
                g2.translate(pf.getImageableX(), pf.getImageableY());
                System.out.println(pf.getImageableX()+"\n"+pf.getImageableY());
                double panelWidth = jPanel1.getWidth();
                double panelHeight = jPanel1.getHeight();
                double pageWidth = pageFormat.getImageableWidth();
                double pageHeight = pageFormat.getImageableHeight();
                
                double scaleX = pageWidth / panelWidth;
                double scaleY = pageHeight / panelHeight;

                if(scaleX < scaleY){
                    g2.scale(scaleX, scaleX);
                }else{
                    g2.scale(scaleY, scaleY);
                }
//                jpanel sing kon print  
                jPanel1.paintAll(g2);
                
                return Printable.PAGE_EXISTS;
            }
        });
        if(pj.printDialog() == false)
            return;
        try
        {
            pj.print();
        }
        catch(PrinterException xcp)
        {
            xcp.printStackTrace(System.err);
        }
    }
    public void printSet()
    {
        pageFormat = pj.pageDialog(pageFormat);
        pj.validatePage(pageFormat);
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
        lblNotransProp = new javax.swing.JLabel();
        lblTanggalProp = new javax.swing.JLabel();
        lblPukulProp = new javax.swing.JLabel();
        lblNamaProp = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblJaminanProp = new javax.swing.JLabel();
        lblNoHPProp = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lblNoTransValue = new javax.swing.JLabel();
        lblTanggalValue = new javax.swing.JLabel();
        lblPukulValue = new javax.swing.JLabel();
        lblNamaValue = new javax.swing.JLabel();
        lblJaminanValue = new javax.swing.JLabel();
        lblNoHPValue = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPeminjamanKamera = new javax.swing.JTable();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        lblDownPaymentValue = new javax.swing.JLabel();
        lblCaraBayarValue = new javax.swing.JLabel();
        lblJumlahItemValue = new javax.swing.JLabel();
        lblTotalSewaValue = new javax.swing.JLabel();
        lblInstagramProp = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lblInstagramValue = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 232, 232));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNotransProp.setText("No Transaksi");
        jPanel1.add(lblNotransProp, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 77, -1, -1));

        lblTanggalProp.setText("Tanggal");
        jPanel1.add(lblTanggalProp, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 106, -1, -1));

        lblPukulProp.setText("Pukul");
        jPanel1.add(lblPukulProp, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 135, -1, -1));

        lblNamaProp.setText("Nama");
        jPanel1.add(lblNamaProp, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 164, -1, -1));

        jLabel5.setText(":");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 77, -1, -1));

        jLabel6.setText(":");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 106, -1, -1));

        jLabel7.setText(":");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 135, -1, -1));

        jLabel8.setText(":");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 164, -1, -1));

        lblJaminanProp.setText("Jaminan");
        jPanel1.add(lblJaminanProp, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 90, -1, -1));

        lblNoHPProp.setText("No HP");
        jPanel1.add(lblNoHPProp, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 140, -1, -1));

        jLabel11.setText(":");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 90, -1, -1));

        jLabel12.setText(":");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 140, -1, -1));

        lblNoTransValue.setText("jLabel13");
        jPanel1.add(lblNoTransValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 77, -1, -1));

        lblTanggalValue.setText("jLabel13");
        jPanel1.add(lblTanggalValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 106, -1, -1));

        lblPukulValue.setText("jLabel13");
        jPanel1.add(lblPukulValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 135, -1, -1));

        lblNamaValue.setText("jLabel13");
        jPanel1.add(lblNamaValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 164, -1, -1));

        lblJaminanValue.setText("jLabel13");
        jPanel1.add(lblJaminanValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(578, 89, -1, -1));

        lblNoHPValue.setText("jLabel13");
        jPanel1.add(lblNoHPValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 140, -1, -1));

        tblPeminjamanKamera.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "No", "Jenis Item", "Keterangan", "Tanggal Sewa", "Jam Mulai", "Jam Selesai", "Harga Sewa"
            }
        ));
        jScrollPane1.setViewportView(tblPeminjamanKamera);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 198, 660, 155));

        jLabel19.setText("Down Payment");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 371, -1, -1));

        jLabel20.setText("Cara Pembayaran");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 405, -1, -1));

        jLabel21.setText("Jumlah Item");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(432, 371, -1, -1));

        jLabel22.setText("Total Sewa");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(432, 405, -1, -1));

        jLabel1.setText(":");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 371, -1, -1));

        jLabel2.setText(":");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 405, -1, -1));

        jLabel3.setText(":");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(514, 371, -1, -1));

        jLabel4.setText(":");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(515, 405, -1, -1));

        jLabel9.setText("Tanda Terima,");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(415, 458, -1, -1));

        jLabel10.setText("Tanda Terima,");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(565, 458, -1, -1));

        jLabel23.setText("(........................)");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(407, 542, -1, -1));

        jLabel24.setText("(........................)");
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 542, -1, -1));

        jLabel25.setText("Contact Person (KONSEP)");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 458, -1, -1));

        jLabel26.setText("Phone");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 475, -1, -1));

        jLabel27.setText("Line");
        jPanel1.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 493, -1, -1));

        jLabel28.setText("Instagram");
        jPanel1.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 511, -1, -1));

        jLabel29.setText("Facebook");
        jPanel1.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 531, -1, -1));

        jLabel31.setText(":");
        jPanel1.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 493, -1, -1));

        jLabel32.setText(":");
        jPanel1.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 511, -1, -1));

        jLabel33.setText(":");
        jPanel1.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 531, -1, -1));

        jLabel34.setText(":");
        jPanel1.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 476, -1, -1));

        jLabel30.setText("088745949574");
        jPanel1.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 476, -1, -1));

        jLabel35.setText("@konsep_id");
        jPanel1.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 493, -1, -1));

        jLabel36.setText("@konsep_id");
        jPanel1.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 511, -1, -1));

        jLabel37.setText("KONSEP");
        jPanel1.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 531, -1, -1));

        lblDownPaymentValue.setText("jLabel13");
        jPanel1.add(lblDownPaymentValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 370, -1, -1));

        lblCaraBayarValue.setText("jLabel13");
        jPanel1.add(lblCaraBayarValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 405, -1, -1));

        lblJumlahItemValue.setText("jLabel13");
        jPanel1.add(lblJumlahItemValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(537, 371, -1, -1));

        lblTotalSewaValue.setText("jLabel13");
        jPanel1.add(lblTotalSewaValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(538, 405, -1, -1));

        lblInstagramProp.setText("Instagram");
        jPanel1.add(lblInstagramProp, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 170, -1, -1));

        jLabel14.setText(":");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 170, -1, -1));

        lblInstagramValue.setText("jLabel13");
        jPanel1.add(lblInstagramValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 170, -1, -1));

        jPanel2.setBackground(new java.awt.Color(254, 0, 0));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/projectkonsep/logo-konsep - Copy.jpg"))); // NOI18N

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Bukti Penyewaan Studio Konsep");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addGap(0, 12, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel13))
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(jButton1)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        tblPeminjamanKamera.clearSelection();
        printSet();
        printWork();
        JOptionPane.showMessageDialog(this,"Bukti Pembayaran Berhasil dicetak!", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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
                new FormCetakBuktiStudio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCaraBayarValue;
    private javax.swing.JLabel lblDownPaymentValue;
    private javax.swing.JLabel lblInstagramProp;
    private javax.swing.JLabel lblInstagramValue;
    private javax.swing.JLabel lblJaminanProp;
    private javax.swing.JLabel lblJaminanValue;
    private javax.swing.JLabel lblJumlahItemValue;
    private javax.swing.JLabel lblNamaProp;
    private javax.swing.JLabel lblNamaValue;
    private javax.swing.JLabel lblNoHPProp;
    private javax.swing.JLabel lblNoHPValue;
    private javax.swing.JLabel lblNoTransValue;
    private javax.swing.JLabel lblNotransProp;
    private javax.swing.JLabel lblPukulProp;
    private javax.swing.JLabel lblPukulValue;
    private javax.swing.JLabel lblTanggalProp;
    private javax.swing.JLabel lblTanggalValue;
    private javax.swing.JLabel lblTotalSewaValue;
    private javax.swing.JTable tblPeminjamanKamera;
    // End of variables declaration//GEN-END:variables
}
