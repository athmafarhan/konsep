/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectkonsep.Outdoor.form;

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
import projectkonsep.Koneksi;
import projectkonsep.kamera.CetakBuktiKamera;
import projectkonsep.menu.Menu;
import projectkonsep.menu.MenuOutdoor;
import projectkonsep.outdoor.*;
import projectkonsep.studio.form.FormHargaItemStudio;

/**
 *
 * @author Athma Farhan
 */
public class FormCetakBuktiOutdoor extends javax.swing.JFrame {
    Object[] headerPeminjaman = {"No", "Jenis Outdoor", "Keterangan", "Jumlah Hari", "Tanggal Kembali", "Harga Sewa"};
    DefaultTableModel dataPeminjaman = new DefaultTableModel(null, headerPeminjaman);
    
    ArrayList<CetakBuktiKamera> ListPeminjamanOutdoor = new ArrayList<>();
    
    SimpleDateFormat sdfThnBlnTgl = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdfPukul = new SimpleDateFormat("HH:mm");
    
    Date a = new Date();
    
    PrinterJob pj = PrinterJob.getPrinterJob();
    PageFormat pageFormat = pj.defaultPage();
    
    static int id_transaksi = 0;
    
    /**
     * Creates new form Cobacoba
     * @param id_transaksi
     */
    public FormCetakBuktiOutdoor(int id_transaksi) {
        if (id_transaksi==0) {
            String tanyaNoTrans = JOptionPane.showInputDialog(this,
                        "No. Transaksi berapa yang akan Anda cetak?", null);
            try {
                int tempNoTrans = Integer.parseInt(tanyaNoTrans);
                FormCetakBuktiOutdoor.id_transaksi = tempNoTrans;
                
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Masukkan No. Transaksi dengan benar!1", "Informasi", 1);
            }
            while (FormCetakBuktiOutdoor.id_transaksi==0) {                    
                    tanyaNoTrans = JOptionPane.showInputDialog(this,
                        "No. Transaksi berapa yang akan Anda cetak?", null);
            try {
                int tempNoTrans = Integer.parseInt(tanyaNoTrans);
                FormCetakBuktiOutdoor.id_transaksi = tempNoTrans;
                
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Masukkan No. Transaksi dengan benar!1", "Informasi", 1);
            }
                }
            id_transaksi=FormCetakBuktiOutdoor.id_transaksi;
        }
        
        FormCetakBuktiOutdoor.id_transaksi=id_transaksi;
        initComponents();
        connectDatabasePeminjaman();
        tblPeminjamanOutdoor.getColumnModel().getColumn(0).setPreferredWidth(23);
        tblPeminjamanOutdoor.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblPeminjamanOutdoor.getColumnModel().getColumn(2).setPreferredWidth(80);
        tblPeminjamanOutdoor.getColumnModel().getColumn(3).setPreferredWidth(45);
        tblPeminjamanOutdoor.getColumnModel().getColumn(4).setPreferredWidth(90);
        tblPeminjamanOutdoor1.getColumnModel().getColumn(0).setPreferredWidth(23);
        tblPeminjamanOutdoor1.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblPeminjamanOutdoor1.getColumnModel().getColumn(2).setPreferredWidth(80);
        tblPeminjamanOutdoor1.getColumnModel().getColumn(3).setPreferredWidth(45);
        tblPeminjamanOutdoor1.getColumnModel().getColumn(4).setPreferredWidth(90);
        lblTanggalValue.setText(sdfThnBlnTgl.format(a));
        lblPukulValue.setText(sdfPukul.format(a));
        lblTanggalValue1.setText(sdfThnBlnTgl.format(a));
        lblPukulValue1.setText(sdfPukul.format(a));
        
    }

    public void connectDatabasePeminjaman(){
        tblPeminjamanOutdoor.setModel(dataPeminjaman);
        tblPeminjamanOutdoor1.setModel(dataPeminjaman);
        Statement st;
        ResultSet rs;
        System.out.println(id_transaksi);
        String viewPeminjaman = "select * from V_CetakBuktiOutdoor where `id`="+id_transaksi+"";
        int indexNomor = 1;
        Date tgl_sewa = null;
        Date tgl_kembali = null;
        int hargaJumlah = 0;
            try {
                st = new Koneksi().getCon().createStatement();
                rs = st.executeQuery(viewPeminjaman);
                while(rs.next()){
                    
                    CetakBuktiKamera CBK = new CetakBuktiKamera(
                            rs.getInt("id"),
                            rs.getInt("id_cust"),
                            rs.getString("nama_cust"),
                            rs.getString("no_hp_cust"),
                            rs.getString("instagram_cust"),
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
                    
                    String tanggal_sewa = CBK.getTgl_sewa();
                    String tanggal_kembali = CBK.getTgl_kembali();
                    try {
                       tgl_sewa = sdfThnBlnTgl.parse(tanggal_sewa);
                       tgl_kembali = sdfThnBlnTgl.parse(tanggal_kembali);
                       tgl_sewa = sdfThnBlnTgl.parse(tanggal_sewa);
                       tgl_kembali = sdfThnBlnTgl.parse(tanggal_kembali);
                    } catch (ParseException ex) {
                        ex.printStackTrace();
                    }
                    
                    long diff = tgl_kembali.getTime() - tgl_sewa.getTime();
                    String jumlah_hari =  String.valueOf(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
                    ListPeminjamanOutdoor.add(CBK);
                    lblNoTransValue.setText(String.valueOf(CBK.getId()));
                    lblNoTransValue1.setText(String.valueOf(CBK.getId()));
                    lblNamaValue.setText(CBK.getNama_cust());
                    lblNamaValue1.setText(CBK.getNama_cust());
                    lblNoHPValue.setText(CBK.getNo_hp_cust());
                    lblNoHPValue1.setText(CBK.getNo_hp_cust());
                    lblInstagramValue.setText(CBK.getInstagram());
                    lblInstagramValue1.setText(CBK.getInstagram());
                    lblDownPaymentValue.setText(String.valueOf(CBK.getDp()));
                    lblDownPaymentValue1.setText(String.valueOf(CBK.getDp()));
                    lblCaraBayarValue.setText(CBK.getCara_bayar());
                    lblCaraBayarValue1.setText(CBK.getCara_bayar());
                    lblJumlahItemValue.setText(String.valueOf(indexNomor));
                    lblJumlahItemValue1.setText(String.valueOf(indexNomor));
                    hargaJumlah = CBK.getHarga()-CBK.getDiskon();
                    Object []kolom = {
                        indexNomor,
                        CBK.getNama_inventaris(),
                        "",
                        jumlah_hari,
                        tanggal_kembali,
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
            for (CetakBuktiKamera CBK : ListPeminjamanOutdoor) {
                hargaJumlah = CBK.getHarga()-CBK.getDiskon();
                jumlahDP+=CBK.getDp();
                hargaJumlahSewa+=hargaJumlah;
            }
            lblDownPaymentValue.setText(String.valueOf(jumlahDP));
            lblTotalSewaValue.setText(String.valueOf(hargaJumlahSewa));
            lblDownPaymentValue1.setText(String.valueOf(jumlahDP));
            lblTotalSewaValue1.setText(String.valueOf(hargaJumlahSewa));
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
                double panelWidth = jPanel5.getWidth();
                double panelHeight = jPanel5.getHeight();
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
                jPanel5.paintAll(g2);
                
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

        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
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
        tblPeminjamanOutdoor = new javax.swing.JTable();
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
        jPanel3 = new javax.swing.JPanel();
        lblNotransProp1 = new javax.swing.JLabel();
        lblTanggalProp1 = new javax.swing.JLabel();
        lblPukulProp1 = new javax.swing.JLabel();
        lblNamaProp1 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        lblJaminanProp1 = new javax.swing.JLabel();
        lblNoHPProp1 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        lblNoTransValue1 = new javax.swing.JLabel();
        lblTanggalValue1 = new javax.swing.JLabel();
        lblPukulValue1 = new javax.swing.JLabel();
        lblNamaValue1 = new javax.swing.JLabel();
        lblJaminanValue1 = new javax.swing.JLabel();
        lblNoHPValue1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPeminjamanOutdoor1 = new javax.swing.JTable();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        lblDownPaymentValue1 = new javax.swing.JLabel();
        lblCaraBayarValue1 = new javax.swing.JLabel();
        lblJumlahItemValue1 = new javax.swing.JLabel();
        lblTotalSewaValue1 = new javax.swing.JLabel();
        lblInstagramProp1 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        lblInstagramValue1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

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

        lblJaminanValue.setText("___________");
        jPanel1.add(lblJaminanValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(578, 89, -1, -1));

        lblNoHPValue.setText("jLabel13");
        jPanel1.add(lblNoHPValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 140, -1, -1));

        tblPeminjamanOutdoor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "No", "Jenis Outdoor", "Keterangan", "Jumlah Hari", "Tanggal Kembali", "Harga Sewa"
            }
        ));
        jScrollPane1.setViewportView(tblPeminjamanOutdoor);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 198, 670, 155));

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

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/logo-konsep - Copy.jpg"))); // NOI18N

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Bukti Penyewaan Outdoor");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel15))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, -1));

        jPanel3.setBackground(new java.awt.Color(255, 232, 232));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblNotransProp1.setText("No Transaksi");
        jPanel3.add(lblNotransProp1, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 77, -1, -1));

        lblTanggalProp1.setText("Tanggal");
        jPanel3.add(lblTanggalProp1, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 106, -1, -1));

        lblPukulProp1.setText("Pukul");
        jPanel3.add(lblPukulProp1, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 135, -1, -1));

        lblNamaProp1.setText("Nama");
        jPanel3.add(lblNamaProp1, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 164, -1, -1));

        jLabel16.setText(":");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 77, -1, -1));

        jLabel17.setText(":");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 106, -1, -1));

        jLabel18.setText(":");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 135, -1, -1));

        jLabel38.setText(":");
        jPanel3.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 164, -1, -1));

        lblJaminanProp1.setText("Jaminan");
        jPanel3.add(lblJaminanProp1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 90, -1, -1));

        lblNoHPProp1.setText("No HP");
        jPanel3.add(lblNoHPProp1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 140, -1, -1));

        jLabel39.setText(":");
        jPanel3.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 90, -1, -1));

        jLabel40.setText(":");
        jPanel3.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 140, -1, -1));

        lblNoTransValue1.setText("jLabel13");
        jPanel3.add(lblNoTransValue1, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 77, -1, -1));

        lblTanggalValue1.setText("jLabel13");
        jPanel3.add(lblTanggalValue1, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 106, -1, -1));

        lblPukulValue1.setText("jLabel13");
        jPanel3.add(lblPukulValue1, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 135, -1, -1));

        lblNamaValue1.setText("jLabel13");
        jPanel3.add(lblNamaValue1, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 164, -1, -1));

        lblJaminanValue1.setText("____________");
        jPanel3.add(lblJaminanValue1, new org.netbeans.lib.awtextra.AbsoluteConstraints(578, 89, -1, -1));

        lblNoHPValue1.setText("jLabel13");
        jPanel3.add(lblNoHPValue1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 140, -1, -1));

        tblPeminjamanOutdoor1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "No", "Jenis Outdoor", "Keterangan", "Jumlah Hari", "Tanggal Kembali", "Harga Sewa"
            }
        ));
        jScrollPane2.setViewportView(tblPeminjamanOutdoor1);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 198, 670, 155));

        jLabel41.setText("Down Payment");
        jPanel3.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 371, -1, -1));

        jLabel42.setText("Cara Pembayaran");
        jPanel3.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 405, -1, -1));

        jLabel43.setText("Jumlah Item");
        jPanel3.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(432, 371, -1, -1));

        jLabel44.setText("Total Sewa");
        jPanel3.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(432, 405, -1, -1));

        jLabel45.setText(":");
        jPanel3.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 371, -1, -1));

        jLabel46.setText(":");
        jPanel3.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(127, 405, -1, -1));

        jLabel47.setText(":");
        jPanel3.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(514, 371, -1, -1));

        jLabel48.setText(":");
        jPanel3.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(515, 405, -1, -1));

        jLabel49.setText("Tanda Terima,");
        jPanel3.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(415, 458, -1, -1));

        jLabel50.setText("Tanda Terima,");
        jPanel3.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(565, 458, -1, -1));

        jLabel51.setText("(........................)");
        jPanel3.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(407, 542, -1, -1));

        jLabel52.setText("(........................)");
        jPanel3.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 542, -1, -1));

        jLabel53.setText("Contact Person (KONSEP)");
        jPanel3.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 458, -1, -1));

        jLabel54.setText("Phone");
        jPanel3.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 475, -1, -1));

        jLabel55.setText("Line");
        jPanel3.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 493, -1, -1));

        jLabel56.setText("Instagram");
        jPanel3.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 511, -1, -1));

        jLabel57.setText("Facebook");
        jPanel3.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(13, 531, -1, -1));

        jLabel58.setText(":");
        jPanel3.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 493, -1, -1));

        jLabel59.setText(":");
        jPanel3.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 511, -1, -1));

        jLabel60.setText(":");
        jPanel3.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 531, -1, -1));

        jLabel61.setText(":");
        jPanel3.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 476, -1, -1));

        jLabel62.setText("088745949574");
        jPanel3.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 476, -1, -1));

        jLabel63.setText("@konsep_id");
        jPanel3.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 493, -1, -1));

        jLabel64.setText("@konsep_id");
        jPanel3.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 511, -1, -1));

        jLabel65.setText("KONSEP");
        jPanel3.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(111, 531, -1, -1));

        lblDownPaymentValue1.setText("jLabel13");
        jPanel3.add(lblDownPaymentValue1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 370, -1, -1));

        lblCaraBayarValue1.setText("jLabel13");
        jPanel3.add(lblCaraBayarValue1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 405, -1, -1));

        lblJumlahItemValue1.setText("jLabel13");
        jPanel3.add(lblJumlahItemValue1, new org.netbeans.lib.awtextra.AbsoluteConstraints(537, 371, -1, -1));

        lblTotalSewaValue1.setText("jLabel13");
        jPanel3.add(lblTotalSewaValue1, new org.netbeans.lib.awtextra.AbsoluteConstraints(538, 405, -1, -1));

        lblInstagramProp1.setText("Instagram");
        jPanel3.add(lblInstagramProp1, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 170, -1, -1));

        jLabel66.setText(":");
        jPanel3.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 170, -1, -1));

        lblInstagramValue1.setText("jLabel13");
        jPanel3.add(lblInstagramValue1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 170, -1, -1));

        jPanel4.setBackground(new java.awt.Color(254, 0, 0));

        jLabel67.setIcon(new javax.swing.ImageIcon(getClass().getResource("/assets/logo-konsep - Copy.jpg"))); // NOI18N

        jLabel68.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(255, 255, 255));
        jLabel68.setText("Bukti Penyewaan Outdoor");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(jLabel68)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel67)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel67)
                    .addComponent(jLabel68))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 690, -1));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane3.setViewportView(jPanel5);

        jButton1.setText("Cetak");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("KEMBALI");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(762, 762, 762)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 740, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 129, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jButton1)
                .addGap(448, 448, 448)
                .addComponent(jButton2)
                .addContainerGap(42, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 555, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 13, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        tblPeminjamanOutdoor.clearSelection();
        printSet();
        printWork();
        JOptionPane.showMessageDialog(this,"Bukti Pembayaran Berhasil dicetak!", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
        java.awt.EventQueue.invokeLater(() -> {
            new MenuOutdoor().setVisible(true);
        });
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(FormHargaItemStudio.class.getName()).log(Level.SEVERE, null, ex);
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormCetakBuktiOutdoor(id_transaksi).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
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
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblCaraBayarValue;
    private javax.swing.JLabel lblCaraBayarValue1;
    private javax.swing.JLabel lblDownPaymentValue;
    private javax.swing.JLabel lblDownPaymentValue1;
    private javax.swing.JLabel lblInstagramProp;
    private javax.swing.JLabel lblInstagramProp1;
    private javax.swing.JLabel lblInstagramValue;
    private javax.swing.JLabel lblInstagramValue1;
    private javax.swing.JLabel lblJaminanProp;
    private javax.swing.JLabel lblJaminanProp1;
    private javax.swing.JLabel lblJaminanValue;
    private javax.swing.JLabel lblJaminanValue1;
    private javax.swing.JLabel lblJumlahItemValue;
    private javax.swing.JLabel lblJumlahItemValue1;
    private javax.swing.JLabel lblNamaProp;
    private javax.swing.JLabel lblNamaProp1;
    private javax.swing.JLabel lblNamaValue;
    private javax.swing.JLabel lblNamaValue1;
    private javax.swing.JLabel lblNoHPProp;
    private javax.swing.JLabel lblNoHPProp1;
    private javax.swing.JLabel lblNoHPValue;
    private javax.swing.JLabel lblNoHPValue1;
    private javax.swing.JLabel lblNoTransValue;
    private javax.swing.JLabel lblNoTransValue1;
    private javax.swing.JLabel lblNotransProp;
    private javax.swing.JLabel lblNotransProp1;
    private javax.swing.JLabel lblPukulProp;
    private javax.swing.JLabel lblPukulProp1;
    private javax.swing.JLabel lblPukulValue;
    private javax.swing.JLabel lblPukulValue1;
    private javax.swing.JLabel lblTanggalProp;
    private javax.swing.JLabel lblTanggalProp1;
    private javax.swing.JLabel lblTanggalValue;
    private javax.swing.JLabel lblTanggalValue1;
    private javax.swing.JLabel lblTotalSewaValue;
    private javax.swing.JLabel lblTotalSewaValue1;
    private javax.swing.JTable tblPeminjamanOutdoor;
    private javax.swing.JTable tblPeminjamanOutdoor1;
    // End of variables declaration//GEN-END:variables
}
