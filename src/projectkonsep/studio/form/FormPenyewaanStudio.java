/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projectkonsep.studio.form;

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
public class FormPenyewaanStudio extends javax.swing.JFrame {
    SimpleDateFormat sdfThnBlnTgl = new SimpleDateFormat("yyyy-MM-dd");

    
    int jumlahSewa = 0;
    boolean tambah1 = false;
    boolean tambah2 = false;
    boolean tambah3 = false;
    boolean tambah4 = false;

    LinkedHashMap<String, Integer> hashItemStudio = new LinkedHashMap<>();
    Collection<String> keyItemStudio;

    private TableRowSorter<TableModel> rowSorter;
    
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
    public FormPenyewaanStudio() {
        
    
        initComponents();
        txtIDCustomer1.setEnabled(false);
        connectDatabase();
        connectDatabaseHarga();
        connectDatabasePeminjaman();
        
        combo2();
        
        searchTabel();
    }
    
    
    
    private void combo2() {
        Connection con2 = new Koneksi().getCon();
        Statement stmt2;
        String sql = "SELECT * FROM st_item";    
        try {
            stmt2 = con2.createStatement();
            ResultSet cb2 = stmt2.executeQuery(sql);
            cbInventaris1.setPrototypeDisplayValue("XXXXXXXXXXXXXX");
            while (cb2.next()) {
                
                int id = cb2.getInt("id");
                String nama = cb2.getString("nama");
                
                ItemStudio IS = new ItemStudio(id, nama);
                
                ListItemStudio.add(IS);
                
                cbInventaris1.addItem(nama);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       AutoCompleteDecorator.decorate(cbInventaris1);
       updateCombo1();
    }
    
    private void updateCombo1() {
        for (ItemStudio IS : ListItemStudio) {
            hashItemStudio.put(IS.getNama(), IS.getId());

        }
        keyItemStudio = hashItemStudio.keySet();
    }

    
    public final void searchTabel() {
        this.rowSorter = new TableRowSorter<>(tblCustomer.getModel());
        tblCustomer.setRowSorter(rowSorter);
        txtSearch.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = txtSearch.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = txtSearch.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
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
        tblPeminjamanStudio.setModel(dataPeminjaman);
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
        tblPeminjamanStudio = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCustomer = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        cbInventaris1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        cbJam1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        cbMenit1 = new javax.swing.JComboBox<>();
        txtIDCustomer1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        lblHarga1 = new javax.swing.JLabel();
        lblDiskon = new javax.swing.JLabel();
        txtDiskon1 = new javax.swing.JTextField();
        lblCaraBayar1 = new javax.swing.JLabel();
        lblDownPayment1 = new javax.swing.JLabel();
        txtCaraBayar1 = new javax.swing.JTextField();
        txtDownPayment1 = new javax.swing.JTextField();
        lblCekHarga1 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        lblJumlah1 = new javax.swing.JLabel();
        lblJumlahHarga1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();

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

        tblPeminjamanStudio.setModel(new javax.swing.table.DefaultTableModel(
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
        tblPeminjamanStudio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblPeminjamanStudioMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblPeminjamanStudio);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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

        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(txtSearch)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87))
        );

        jTabbedPane1.addTab("Tabel Customer", jPanel4);

        jPanel2.setBackground(new java.awt.Color(255, 232, 232));
        jPanel2.setPreferredSize(new java.awt.Dimension(300, 1650));

        cbInventaris1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbInventaris1ActionPerformed(evt);
            }
        });

        jLabel1.setText("ID Transaksi");

        jLabel2.setText("ID Customer");

        jLabel3.setText("ID Inventaris");

        jLabel4.setText("Tanggal Sewa");

        cbJam1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20" }));

        jLabel5.setText("Jam Mulai");

        cbMenit1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "15", "30", "45" }));
        cbMenit1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMenit1ActionPerformed(evt);
            }
        });

        jButton1.setText("Cek Harga Sewa");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel9.setText("Durasi Sewa");

        lblHarga1.setText("Harga");

        lblDiskon.setText("Diskon");

        txtDiskon1.setText("0");

        lblCaraBayar1.setText("Cara Bayar");

        lblDownPayment1.setText("Down Payment");

        txtDownPayment1.setText("0");

        lblCekHarga1.setText("Klik Cek Harga");

        jButton11.setText("Cek Jumlah Harga Keseluruhan");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        lblJumlah1.setText("Jumlah");

        lblJumlahHarga1.setText("Klik Cek Jumlah Harga");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel1)
                        .addGap(65, 65, 65)
                        .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel2)
                        .addGap(65, 65, 65)
                        .addComponent(txtIDCustomer1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel3)
                        .addGap(64, 64, 64)
                        .addComponent(cbInventaris1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel4)
                        .addGap(54, 54, 54)
                        .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblHarga1)
                        .addGap(105, 105, 105)
                        .addComponent(lblCekHarga1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblDiskon)
                        .addGap(102, 102, 102)
                        .addComponent(txtDiskon1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblCaraBayar1)
                        .addGap(76, 76, 76)
                        .addComponent(txtCaraBayar1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblDownPayment1)
                        .addGap(54, 54, 54)
                        .addComponent(txtDownPayment1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblJumlah1)
                        .addGap(100, 100, 100)
                        .addComponent(lblJumlahHarga1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel9))
                        .addGap(62, 62, 62)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbJam1, 0, 64, Short.MAX_VALUE)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(cbMenit1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel1))
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel2))
                    .addComponent(txtIDCustomer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel3))
                    .addComponent(cbInventaris1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel4))
                    .addComponent(jXDatePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel5))
                    .addComponent(cbJam1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(cbMenit1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(jButton1)
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblHarga1)
                    .addComponent(lblCekHarga1))
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblDiskon))
                    .addComponent(txtDiskon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblCaraBayar1))
                    .addComponent(txtCaraBayar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblDownPayment1))
                    .addComponent(txtDownPayment1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton11)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblJumlah1)
                    .addComponent(lblJumlahHarga1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton2.setText("Masukkan");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton6.setText("Edit");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("jButton7");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("jButton8");

        jButton9.setText("Delete");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("jButton10");

        jButton15.setText("|<");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setText("<<");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setText(">>");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setText(">|");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(7, 7, 7)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(7, 7, 7)
                        .addComponent(jButton10))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton15)
                            .addComponent(jButton16)
                            .addComponent(jButton17)
                            .addComponent(jButton18))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2)
                            .addComponent(jButton6)
                            .addComponent(jButton7)
                            .addComponent(jButton8)
                            .addComponent(jButton9)
                            .addComponent(jButton10)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbInventaris1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbInventaris1ActionPerformed
//        String tempID = (String) cbInventaris1.getSelectedItem();
//        int ID = 0;
//        
//        try {
//            ID = hashItemStudio.get(tempID);
//
//        } catch (NullPointerException e) {
//        }
//        System.out.println(ID);
    }//GEN-LAST:event_cbInventaris1ActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String tempID = String.valueOf(cbInventaris1.getSelectedItem());
        int ID = 0;
        
        try {
            ID = hashItemStudio.get(tempID);

        } catch (NullPointerException e) {
        }
        
        DayOfWeek senin = DayOfWeek.MONDAY;
        DayOfWeek jumat = DayOfWeek.FRIDAY;
        DayOfWeek sabtu = DayOfWeek.SATURDAY;
        DayOfWeek minggu = DayOfWeek.SUNDAY;
        int seninInt = senin.getValue();
        int jumatInt = jumat.getValue();
        int sabtuInt = sabtu.getValue();
        int mingguInt = minggu.getValue();
        
        Date ambilTanggal = jXDatePicker1.getDate();
        Calendar hari = Calendar.getInstance();
        hari.setTime(ambilTanggal);
        int hariInt = hari.get(Calendar.DAY_OF_WEEK)-1;
        
        
        String jamString = (String) cbJam1.getSelectedItem();
        String menitString = (String) cbMenit1.getSelectedItem();
        int jamInt = Integer.parseInt(jamString+menitString);
        int durasiSewa = Integer.parseInt((String)jComboBox1.getSelectedItem()+"00");
        int kalkulasi = jamInt + durasiSewa;
        if (hariInt==0) {
            hariInt=7;
        }
        System.out.println(hariInt);
        System.out.println(jamInt);
        System.out.println(kalkulasi);
        if (ID==1) {
            if (seninInt<=hariInt&&jumatInt>=hariInt){
                System.out.println("masuk weekdays");
                if ((800<=jamInt&&1600>jamInt)&&kalkulasi>1600) {
                    int hargaSatu;
                    int hargaDua;
                    int hargatotal;
                    int hargaTambahan;

                    switch (durasiSewa) {
                        case 100:
                            String harga1jam = String.valueOf(ListHargaItemStudio.get(0).getHarga_1_jam());
                            lblCekHarga1.setText(harga1jam);  
                            break;
                        case 200:
                            hargaSatu = (ListHargaItemStudio.get(0).getHarga_1_jam());
                            hargaDua = (ListHargaItemStudio.get(1).getHarga_1_jam());
                            hargatotal = hargaSatu + hargaDua;
                            lblCekHarga1.setText(String.valueOf(hargatotal));
                            break;
                        case 300:
                            hargaSatu = (ListHargaItemStudio.get(0).getHarga_1_jam());
                            hargaDua = (ListHargaItemStudio.get(1).getHarga_2_jam());
                            hargatotal = hargaSatu+hargaDua;
                            //hargatotal = (hargaSatu+hargaDua) + (((durasiSewa-200)/100)*hargaTambahan);
                            lblCekHarga1.setText(String.valueOf(hargatotal));
                            break;
                        default:
                            hargaSatu = (ListHargaItemStudio.get(0).getHarga_1_jam());
                            hargaDua = (ListHargaItemStudio.get(1).getHarga_2_jam());
                            hargaTambahan =  ListHargaItemStudio.get(0).getHarga_tambahan();
                            //hargatotal = hargaSatu+hargaDua;
                            hargatotal = (hargaSatu+hargaDua) + (((durasiSewa-300)/100)*hargaTambahan);
                            lblCekHarga1.setText(String.valueOf(hargatotal));
                    }
                    System.out.println("zona 3");
                }
                else if (800<=jamInt&&1600>jamInt) {
                    switch (durasiSewa) {
                        case 100:
                            String harga1jam = String.valueOf(ListHargaItemStudio.get(0).getHarga_1_jam());
                            lblCekHarga1.setText(harga1jam);  
                            break;
                        case 200:
                            String harga2jam = String.valueOf(ListHargaItemStudio.get(0).getHarga_2_jam());
                            lblCekHarga1.setText(harga2jam);
                            break;
                        default:
                            int harga2 = (ListHargaItemStudio.get(0).getHarga_2_jam());
                            int hargaTambahan =  ListHargaItemStudio.get(0).getHarga_tambahan();
                            int harga = harga2 + (((durasiSewa-200)/100)*hargaTambahan);
                            lblCekHarga1.setText(String.valueOf(harga));
                    }
                }
                else if (1600<=jamInt&&2000>=jamInt) {
                    switch (durasiSewa) {
                        case 100:
                            String harga1jam = String.valueOf(ListHargaItemStudio.get(1).getHarga_1_jam());
                            lblCekHarga1.setText(harga1jam);  
                            break;
                        case 200:
                            String harga2jam = String.valueOf(ListHargaItemStudio.get(1).getHarga_2_jam());
                            lblCekHarga1.setText(harga2jam);
                            break;
                        default:
                            int harga2 = (ListHargaItemStudio.get(1).getHarga_2_jam());
                            int hargaTambahan =  ListHargaItemStudio.get(1).getHarga_tambahan();
                            int harga = harga2 + (((durasiSewa-200)/100)*hargaTambahan);
                            lblCekHarga1.setText(String.valueOf(harga));
                    }
                }
            }
            else if (hariInt==sabtuInt){
                switch (durasiSewa) {
                        case 100:
                            String harga1jam = String.valueOf(ListHargaItemStudio.get(2).getHarga_1_jam());
                            lblCekHarga1.setText(harga1jam);  
                            break;
                        case 200:
                            String harga2jam = String.valueOf(ListHargaItemStudio.get(2).getHarga_2_jam());
                            lblCekHarga1.setText(harga2jam);
                            break;
                        default:
                            int harga2 = (ListHargaItemStudio.get(2).getHarga_2_jam());
                            int hargaTambahan =  ListHargaItemStudio.get(2).getHarga_tambahan();
                            int harga = harga2 + (((durasiSewa-200)/100)*hargaTambahan);
                            lblCekHarga1.setText(String.valueOf(harga));
                }
                System.out.println("sabtu");
            }
            else if (hariInt==mingguInt){
                switch (durasiSewa) {
                        case 100:
                            String harga1jam = String.valueOf(ListHargaItemStudio.get(3).getHarga_1_jam());
                            lblCekHarga1.setText(harga1jam);  
                            break;
                        case 200:
                            String harga2jam = String.valueOf(ListHargaItemStudio.get(3).getHarga_2_jam());
                            lblCekHarga1.setText(harga2jam);
                            break;
                        default:
                            int harga2 = (ListHargaItemStudio.get(3).getHarga_2_jam());
                            int hargaTambahan =  ListHargaItemStudio.get(3).getHarga_tambahan();
                            int harga = harga2 + (((durasiSewa-200)/100)*hargaTambahan);
                            lblCekHarga1.setText(String.valueOf(harga));
                }
            }
        } 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        if (tambah4==false&&tambah3==false&&tambah2==false) {
            inputDatabase1();
            updateTablePeminjaman();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        
        editDatabase1();
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        boolean berhasil = false;
        int cekHarga = 0;
        int diskon = 0;
        int dp = 0;
        int total = 0;
        try {
            cekHarga = Integer.parseInt(lblCekHarga1.getText());
            diskon = Integer.parseInt(txtDiskon1.getText());
            dp = Integer.parseInt(txtDownPayment1.getText());
            berhasil = true;
        } catch (NumberFormatException e) {
        }
        if (berhasil) {
            total = cekHarga - (diskon + dp);
            lblJumlahHarga1.setText(String.valueOf(total));
        }
        else {
            System.out.println("Salah hahahaaha");
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void cbMenit1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMenit1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbMenit1ActionPerformed

    private void tblCustomerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCustomerMouseClicked
        
    }//GEN-LAST:event_tblCustomerMouseClicked

    private void tblCustomerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCustomerMousePressed
        try {
            int rowIndex = tblCustomer.getSelectedRow();
            nama_cust = (String) tblCustomer.getValueAt(rowIndex, 1);
            int selectedID = (int) tblCustomer.getValueAt(rowIndex, 0);
            txtIDCustomer1.setText(String.valueOf(selectedID));
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Penambahan Gagal", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_tblCustomerMousePressed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        this.index = ListPeminjamanStudio.size()-1;
        this.showData1();
    }//GEN-LAST:event_jButton18ActionPerformed

    private void tblPeminjamanStudioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPeminjamanStudioMousePressed
        int rowIndex = tblPeminjamanStudio.getSelectedRow();
        index = rowIndex;
        try {
            showData1();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Penambahan Gagal", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_tblPeminjamanStudioMousePressed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        this.index = 0;
        this.showData1();
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        try {
            if (this.index > 0) {
                --this.index;
            }
            this.showData1();
        }
        catch (IndexOutOfBoundsException e) {
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        try {
            if (this.ListPeminjamanStudio.size() - 1 > this.index) {
                ++this.index;
                this.showData1();
            }
        }
        catch (IndexOutOfBoundsException e) {
        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
boolean berhasil = false;
        if (JOptionPane.showConfirmDialog(this, "Yakin akan dihapus?", "Hapus", 0) == 0) {
            String ketikKONFIRMASI = JOptionPane.showInputDialog(this,
                        "Ketik KONFIRMASI untuk menghapus data", null);
            String key = "KONFIRMASI";
            
            if (ketikKONFIRMASI.equals(key)) {
                Connection con = null;
                PreparedStatement myPreparedStatement = null;
                con = new Koneksi().getCon();
                String sqldelete = "DELETE FROM st_studio WHERE `id` =?";
                try {
                    int field1 = Integer.parseInt(txtID.getText());
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
                    ListPeminjamanStudio.remove(index);
                    JOptionPane.showMessageDialog(this, "Data berhasil dihapus!", "Informasi", 1);
                    updateTablePeminjaman();
                }
            }
            else if (berhasil == false) {
                JOptionPane.showMessageDialog(this, "Data tidak berhasil dihapus", "Informasi", 1);
            }
            
        }        
    }//GEN-LAST:event_jButton9ActionPerformed

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
            java.util.logging.Logger.getLogger(FormPenyewaanStudio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPenyewaanStudio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPenyewaanStudio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPenyewaanStudio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
            Logger.getLogger(FormPenyewaanStudio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(FormPenyewaanStudio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FormPenyewaanStudio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(FormPenyewaanStudio.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormPenyewaanStudio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbInventaris1;
    private javax.swing.JComboBox<String> cbJam1;
    private javax.swing.JComboBox<String> cbMenit1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private javax.swing.JLabel lblCaraBayar1;
    private javax.swing.JLabel lblCekHarga1;
    private javax.swing.JLabel lblDiskon;
    private javax.swing.JLabel lblDownPayment1;
    private javax.swing.JLabel lblHarga1;
    private javax.swing.JLabel lblJumlah1;
    private javax.swing.JLabel lblJumlahHarga1;
    private javax.swing.JTable tblCustomer;
    private javax.swing.JTable tblPeminjamanStudio;
    private javax.swing.JTextField txtCaraBayar1;
    private javax.swing.JTextField txtDiskon1;
    private javax.swing.JTextField txtDownPayment1;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtIDCustomer1;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
    public void inputDatabase1() {
        PreparedStatement myPS1 = null;
        
        boolean berhasil = false;
        int id = 0;
        int id_cust = 0;
        int id_item = 0;
        String tgl_sewa = null;
        String jam_mulai = null;
        String jam_berakhir = null;
        int harga = 0;
        String cara_bayar = null;
        int dp = 0;
        int diskon = 0;
        int denda = 0;
        String tempInventaris = (String) cbInventaris1.getSelectedItem();
        
        String nama_item = tempInventaris;//tidak input ke db
        int jml = 0;//tidak input ke db
        int durasiSewa;//tidak input ke db
        
        try {
            id_item = hashItemStudio.get(tempInventaris);
        } catch (NullPointerException e) {
        }
        
        
        try {
            
            durasiSewa = (int) jComboBox1.getSelectedItem();
            int jamMulaiInt = (int) cbJam1.getSelectedItem();
            int jamBerakhhirInt = jamMulaiInt + durasiSewa;
            String jamBerakhir = String.valueOf(jamBerakhhirInt);
            if (jamBerakhhirInt<10) {
                jamBerakhir = "0"+jamBerakhir;
            }
            jam_mulai=cbJam1.getSelectedItem()+":"+cbMenit1.getSelectedItem();
            jam_berakhir=jamBerakhir+":"+cbMenit1.getSelectedItem();
            
            
            id = Integer.parseInt(txtID.getText());
            id_cust = Integer.parseInt(txtIDCustomer1.getText());
            harga = Integer.parseInt(lblCekHarga1.getText());
            diskon = Integer.parseInt(txtDiskon1.getText());
            dp = Integer.parseInt(txtDownPayment1.getText());
            jml = Integer.parseInt(lblJumlahHarga1.getText());
            sdfThnBlnTgl = new SimpleDateFormat("yyyy-MM-dd");
            Date input = jXDatePicker1.getDate();
            
            tgl_sewa = sdfThnBlnTgl.format(input);
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,"Data yang Anda Masukkan Salah", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
        }
//        
//        System.out.println(id);
//        System.out.println(id_cust);
//        System.out.println(id_inven);
//        System.out.println(tgl_sewa);
//        System.out.println(jam_sewa);
//        System.out.println(tgl_kembali);
//        System.out.println(jam_kembali);
//        System.out.println(cara_bayar);
//        System.out.println(dp);
//        System.out.println(diskon);
//        System.out.println(denda);
//        System.out.println("");
//        System.out.println(jumlahHari);
//        System.out.println(jumlahHarga);
//        System.out.println(cekHarga);
//        

        try {
            Connection con = new Koneksi().getCon();
            String sqlinput = "INSERT INTO `st_penyewaan`"
                    + "(`id`, `id_cust`, `id_item`, `tgl_sewa`, `jam_mulai`, `jam_berakhir`, `harga`, `cara_bayar`, `dp`, `diskon`, `denda`) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            
            
            myPS1 = con.prepareStatement(sqlinput);
            myPS1.setInt(1, id);
            myPS1.setInt(2, id_cust);
            myPS1.setInt(3, id_item);
            myPS1.setString(4, tgl_sewa);
            myPS1.setString(5, jam_mulai);
            myPS1.setString(6, jam_berakhir);
            myPS1.setInt(7, harga);
            myPS1.setString(8, cara_bayar);
            myPS1.setInt(9, dp);
            myPS1.setInt(10, diskon);
            myPS1.setInt(11, denda);
            myPS1.executeUpdate();
            berhasil = true;


        }
        catch(MySQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            berhasil = false;
        } catch (SQLException ex) {
            berhasil = false;
            Logger.getLogger(FormPenyewaanStudio.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (berhasil == true) {
            
            PenyewaanStudio PS = new PenyewaanStudio();
            PS.setId(id);
            PS.setId_cust(id_cust);
            PS.setNama_cust(nama_cust);
            PS.setId_item(id_item);
            PS.setNama_item(nama_item);
            PS.setTgl_sewa(tgl_sewa);
            PS.setJam_mulai(jam_mulai);
            PS.setJam_berakhir(jam_berakhir);
            PS.setHarga(harga);
            PS.setDp(dp);
            PS.setDiskon(diskon);
            PS.setDenda(denda);
            PS.setJml(jml);
            PS.setCara_bayar(cara_bayar);
            
            ListPeminjamanStudio.add(PS);
           
            
            
//            MerkStudio newMK = new MerkStudio();
//            newMK.setId(field1);
//            newMK.setId_tipe(field2);
//            newMK.setTipe(tempTipe);
//            newMK.setNama(field3);
//            this.listMerkStudio.add(newMK);
//            JOptionPane.showMessageDialog(this,"Penambahan Sukses", "Informasi",
//                JOptionPane.INFORMATION_MESSAGE);
//            txtMerkID.setText("");
//            cbMenit1.setSelectedIndex(0);
//            txtMerkNama.setText("");
//            updateTableMerk();
//            initComboBox1();
//            jComboBox2.removeAllItems();
//            initComboBox2();
        }
        else if (berhasil == false) {
            JOptionPane.showMessageDialog(this,"Penambahan Gagal", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
        }
    
    }
    
    
    public void editDatabase1() {
        PreparedStatement myEditPS1 = null;
        
        boolean berhasil = false;
        int id = 0;
        int id_cust = 0;
        int id_item = 0;
        String tgl_sewa = null;
        String jam_mulai = null;
        String jam_berakhir = null;
        int harga = 0;
        String cara_bayar = null;
        int dp = 0;
        int diskon = 0;
        int denda = 0;
        String tempInventaris = (String) cbInventaris1.getSelectedItem();
        
        String nama_item = tempInventaris;//tidak input ke db
        int jml = 0;//tidak input ke db
        int durasiSewa;//tidak input ke db
        
        try {
            id_item = hashItemStudio.get(tempInventaris);
        } catch (NullPointerException e) {
        }
        
        
        try {
            
            durasiSewa = Integer.parseInt(String.valueOf(jComboBox1.getSelectedItem()));
            int jamMulaiInt = Integer.parseInt(String.valueOf(cbJam1.getSelectedItem()));
            int jamBerakhhirInt = jamMulaiInt + durasiSewa;
            String jamBerakhir = String.valueOf(jamBerakhhirInt);
            if (jamBerakhhirInt<10) {
                jamBerakhir = "0"+jamBerakhir;
            }
            jam_mulai=cbJam1.getSelectedItem()+":"+cbMenit1.getSelectedItem();
            jam_berakhir=jamBerakhir+":"+cbMenit1.getSelectedItem();
            
            
            id = Integer.parseInt(txtID.getText());
            id_cust = Integer.parseInt(txtIDCustomer1.getText());
            cara_bayar = txtCaraBayar1.getText();
            harga = Integer.parseInt(lblCekHarga1.getText());
            diskon = Integer.parseInt(txtDiskon1.getText());
            dp = Integer.parseInt(txtDownPayment1.getText());
            jml = Integer.parseInt(lblJumlahHarga1.getText());
            sdfThnBlnTgl = new SimpleDateFormat("yyyy-MM-dd");
            Date input = jXDatePicker1.getDate();
            
            //tgl_sewa = input.toString();
            tgl_sewa = sdfThnBlnTgl.format(input);
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,"Data yang Anda Masukkan Salah", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
        }
        
        try {
            Connection con = new Koneksi().getCon();
            String sqledit = "UPDATE `st_penyewaan` SET "
                    + "`id_cust`=?,"
                    + "`id_item`=?,"
                    + "`tgl_sewa`=?,"
                    + "`jam_mulai`=?,"
                    + "`jam_berakhir`=?,"
                    + "`harga`=?,"
                    + "`cara_bayar`=?,"
                    + "`dp`=?,"
                    + "`diskon`=?,"
                    + "`denda`=? "
                    + "WHERE `id`=?";
            
            myEditPS1 = con.prepareStatement(sqledit);
            
            myEditPS1.setInt(1, id_cust);
            myEditPS1.setInt(2, id_item);
            myEditPS1.setString(3, tgl_sewa);
            myEditPS1.setString(4, jam_mulai);
            myEditPS1.setString(5, jam_berakhir);
            myEditPS1.setInt(6, harga);
            myEditPS1.setString(7, cara_bayar);
            myEditPS1.setInt(8, dp);
            myEditPS1.setInt(9, diskon);
            myEditPS1.setInt(10, denda);
            myEditPS1.setInt(11, id);

            berhasil = true;
        }
        catch(SQLException e) {
            berhasil = false;
            e.printStackTrace();
        }
        
        
        if (berhasil) {
            try {
                myEditPS1.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            PenyewaanStudio newPS = new PenyewaanStudio();
            newPS.setId(id);
            newPS.setId_cust(id_cust);
            newPS.setNama_cust(nama_cust);
            newPS.setId_item(id_item);
            newPS.setNama_item(nama_item);
            newPS.setTgl_sewa(tgl_sewa);
            newPS.setJam_mulai(jam_mulai);
            newPS.setJam_berakhir(jam_berakhir);
            newPS.setHarga(harga);
            newPS.setDp(dp);
            newPS.setDiskon(diskon);
            newPS.setDenda(denda);
            newPS.setJml(jml);
            newPS.setCara_bayar(cara_bayar);
            
            ListPeminjamanStudio.set(index,newPS);            
            updateTablePeminjaman();
            
        }
        else if (berhasil == false) {
            JOptionPane.showMessageDialog(this,"Penambahan Gagal", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void showData1() {
        PenyewaanStudio PS = ListPeminjamanStudio.get(index);
        
        txtID.setText(String.valueOf(PS.getId()));
        txtIDCustomer1.setText(String.valueOf(PS.getId_cust()));
        nama_cust = PS.getNama_cust();
        cbInventaris1.setSelectedItem(PS.getNama_item());
        Date tgl_sewa = null;
        
        String tanggal_sewa = PS.getTgl_sewa();
        try {
            tgl_sewa = sdfThnBlnTgl.parse(tanggal_sewa);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        jXDatePicker1.setDate(tgl_sewa);
        String tempJamMulai = PS.getJam_mulai();
        String jamMulai = tempJamMulai.substring(0,2);
        String menitMulai = tempJamMulai.substring(3,5);
        
        String tempJamBerakhirString = PS.getJam_berakhir().replace(":", "");
        int tempJamBerakhirInt = Integer.parseInt(tempJamBerakhirString);
        String tempJamMulaiString = jamMulai+menitMulai;
        int tempJamMulaiInt = Integer.parseInt(tempJamMulaiString);
        
        int durasi_sewa = (tempJamBerakhirInt-tempJamMulaiInt)/100;
        cbJam1.setSelectedItem(jamMulai);
        cbMenit1.setSelectedItem(menitMulai);
        System.out.println(durasi_sewa);
        jComboBox1.setSelectedItem(String.valueOf(durasi_sewa));
        
        lblCekHarga1.setText(String.valueOf(PS.getHarga()));
        
        txtDownPayment1.setText(String.valueOf(PS.getDp()));
        
        txtDiskon1.setText(String.valueOf(PS.getDiskon()));
        lblJumlahHarga1.setText(String.valueOf(PS.getJml()));
        
        txtCaraBayar1.setText(PS.getCara_bayar());
    }
}