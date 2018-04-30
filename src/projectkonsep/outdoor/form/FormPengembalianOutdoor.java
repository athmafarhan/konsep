package projectkonsep.outdoor.form;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import projectkonsep.menu.MenuOrderOutdoor;
import projectkonsep.outdoor.CustomerOutdoor;
import projectkonsep.outdoor.InventarisOutdoor;
import projectkonsep.outdoor.PinjamKembaliOutdoor;

/**
 *
 * @author Athma Farhan
 */
public class FormPengembalianOutdoor extends javax.swing.JFrame {
    String sqlinput = "INSERT INTO `Pengembalian`\n" +
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

    private TableRowSorter<TableModel> rowSorter;
    
    ArrayList<PinjamKembaliOutdoor> ListPeminjamanOutdoor = new ArrayList<>();
    ArrayList<CustomerOutdoor> ListCustomerOutdoor = new ArrayList<>();
    ArrayList<InventarisOutdoor> ListInventarisOutdoor = new ArrayList<>();
    
    Object[] headerCustomer = {"ID", "Nama", "Nomor HP", "Alamat", "Instagram", "Status"};
    DefaultTableModel dataCustomer = new DefaultTableModel(null, headerCustomer);
    
    Object[] headerPeminjaman = {"ID", "Customer", "Inventaris", "Tgl Sewa", "Jam Sewa", "Tgl Kembali", "Jam Kembali", "Harga", "DP", "Diskon", "Denda", "Jumlah", "Cara Bayar"};
    DefaultTableModel dataPeminjaman = new DefaultTableModel(null, headerPeminjaman);
    
    String nama_cust;
    
    int index;

        
    /**
     * Creates new form FormTransaksiOutdoor
     */
    public FormPengembalianOutdoor() {
        
    
        initComponents();
        txtIDCustomer1.setEnabled(false);
        connectDatabase();
        connectDatabasePeminjaman();
        
        combo2();
        
        searchTabel();
        jScrollPane3.getVerticalScrollBar().setUnitIncrement(16);
        noShow2();
        noShow3();
        noShow4();
    }
    
    public void noShow2(){
        jSeparator1.setVisible(tambah1);
        lblIDInventaris2.setVisible(tambah1);
        lblTanggal2.setVisible(tambah1);
        lblJam2.setVisible(tambah1);
        lblJml2.setVisible(tambah1);
        cbInventaris2.setVisible(tambah1);
        dpTanggal2.setVisible(tambah1);
        cbJam2.setVisible(tambah1);
        cbMenit2.setVisible(tambah1);
        txtJml2.setVisible(tambah1);
        btnCekHarga2.setVisible(tambah1);
        jButton3.setVisible(tambah1);
        lblDiskon2.setVisible(tambah1);
        lblHarga2.setVisible(tambah1);
        lblCekHarga2.setVisible(tambah1);
        txtDiskon2.setVisible(tambah1);
        lblCaraBayar2.setVisible(tambah1);
        lblDownPayment2.setVisible(tambah1);
        txtCaraBayar2.setVisible(tambah1);
        txtDownPayment2.setVisible(tambah1);
        jButton12.setVisible(tambah1);
        lblJumlah2.setVisible(tambah1);
        lblJumlahHarga2.setVisible(tambah1);
    }
    
    public void noShow3() {
        jSeparator2.setVisible(tambah2);
        lblIDInventaris3.setVisible(tambah2);
        lblTanggal3.setVisible(tambah2);
        lblJam3.setVisible(tambah2);
        lblJml3.setVisible(tambah2);
        cbInventaris3.setVisible(tambah2);
        dpTanggal3.setVisible(tambah2);
        cbJam3.setVisible(tambah2);
        cbMenit3.setVisible(tambah2);
        txtJml3.setVisible(tambah2);
        btnCekHarga3.setVisible(tambah2);
        jButton5.setVisible(tambah2);
        lblDiskon3.setVisible(tambah2);
        lblHarga3.setVisible(tambah2);
        lblCekHarga3.setVisible(tambah2);
        txtDiskon3.setVisible(tambah2);
        lblCaraBayar3.setVisible(tambah2);
        lblDownPayment3.setVisible(tambah2);
        txtCaraBayar3.setVisible(tambah2);
        txtDownPayment3.setVisible(tambah2);
        jButton13.setVisible(tambah2);
        lblJumlah3.setVisible(tambah2);
        lblJumlahHarga3.setVisible(tambah2);
    }
    
    public void noShow4() {
        jSeparator3.setVisible(tambah3);
        lblIDInventaris4.setVisible(tambah3);
        lblTanggal4.setVisible(tambah3);
        lblJam4.setVisible(tambah3);
        lblJml4.setVisible(tambah3);
        cbInventaris4.setVisible(tambah3);
        dpTanggal4.setVisible(tambah3);
        cbJam4.setVisible(tambah3);
        cbMenit4.setVisible(tambah3);
        txtJml4.setVisible(tambah3);
        btnCekHarga4.setVisible(tambah3);
        lblDiskon4.setVisible(tambah3);
        lblHarga4.setVisible(tambah3);
        lblCekHarga4.setVisible(tambah3);
        txtDiskon4.setVisible(tambah3);
        lblCaraBayar4.setVisible(tambah3);
        lblDownPayment4.setVisible(tambah3);
        txtCaraBayar4.setVisible(tambah3);
        txtDownPayment4.setVisible(tambah3);
        jButton14.setVisible(tambah3);
        lblJumlah4.setVisible(tambah3);
        lblJumlahHarga4.setVisible(tambah3);
    }

    
    
    private void combo2() {
        Connection con2 = new Koneksi().getCon();
        Statement stmt2;
        String sql = "SELECT * FROM V_InventarisOutdoor";    
        try {
            stmt2 = con2.createStatement();
            ResultSet cb2 = stmt2.executeQuery(sql);
            cbInventaris1.setPrototypeDisplayValue("XXXXXXXXXXXXXX");
            while (cb2.next()) {                
                int id = cb2.getInt("id");
                String merk = cb2.getString("merk");
                String jenis = cb2.getString("jenis");
                String stringwarna = cb2.getString("stringwarna");
                int harga = cb2.getInt("harga");
                InventarisOutdoor IK = new InventarisOutdoor(id, merk, jenis, stringwarna, harga);

                ListInventarisOutdoor.add(IK);
                cbInventaris1.addItem(merk+" "+jenis+" "+stringwarna);
                cbInventaris2.addItem(merk+" "+jenis+" "+stringwarna);
                cbInventaris3.addItem(merk+" "+jenis+" "+stringwarna);
                cbInventaris4.addItem(merk+" "+jenis+" "+stringwarna);
                System.out.println(ListInventarisOutdoor.get(0).getId());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       //AutoCompleteDecorator.decorate(cbInventaris1);
       updateCombo1();
    }
    
    private void updateCombo1() {
        for (InventarisOutdoor IO : ListInventarisOutdoor) {
            String key = IO.getMerk()+""+IO.getJenis()+" "+IO.getString_warna();
            hashInventaris.put(key, IO.getId());

        }
        keyInventaris = hashInventaris.keySet();
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
        String viewJenisOutdoor = "SELECT * FROM  `ou_cust` ";
           try {
               st3 = new Koneksi().getCon().createStatement();
               rs3 = st3.executeQuery(viewJenisOutdoor);
               int i = 0;
            while (rs3.next() == true){
                CustomerOutdoor newCO = new CustomerOutdoor(
                rs3.getInt("id"),
                rs3.getString("nama"),
                rs3.getString("no_hp"),
                rs3.getString("alamat_rumah"),
                rs3.getString("instagram"),
                String.valueOf(rs3.getInt("blacklist")));
                ListCustomerOutdoor.add(newCO);
                if (ListCustomerOutdoor.get(i).getStatus().equals("0")) {
                    ListCustomerOutdoor.get(i).setStatus("Verified");
                }
                else {
                    ListCustomerOutdoor.get(i).setStatus("Blacklist");
                }
                i++;
                   Object []kolom = {newCO.getId(), newCO.getNama(), newCO.getNo_hp(), newCO.getAlamat_rumah(), newCO.getInstagram(), newCO.getStatus()};
                   dataCustomer.addRow(kolom);
                   
                   }
               
               dataCustomer.fireTableDataChanged();
           }catch (Exception e){
                JOptionPane.showMessageDialog(null,"error :"+e.getMessage());
                e.printStackTrace();
                } 
    }
    
    public void connectDatabasePeminjaman(){
        tblPeminjamanOutdoor.setModel(dataPeminjaman);
        Statement st;
        ResultSet rs;
        String viewPeminjaman = "select * from V_PengembalianOutdoor";
           try {
               st = new Koneksi().getCon().createStatement();
               rs = st.executeQuery(viewPeminjaman);
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
                   
                   
                   ListPeminjamanOutdoor.add(PK);
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
                   
                   dataPeminjaman.addRow(kolom);
                   
                   }
               
               dataPeminjaman.fireTableDataChanged();
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
        for (PinjamKembaliOutdoor PKK : ListPeminjamanOutdoor) {
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
        tblPeminjamanOutdoor = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCustomer = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtSearch = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
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
        txtJml1 = new javax.swing.JTextField();
        lblIDInventaris2 = new javax.swing.JLabel();
        cbInventaris2 = new javax.swing.JComboBox<>();
        lblTanggal2 = new javax.swing.JLabel();
        dpTanggal2 = new org.jdesktop.swingx.JXDatePicker();
        lblJam2 = new javax.swing.JLabel();
        cbJam2 = new javax.swing.JComboBox<>();
        cbMenit2 = new javax.swing.JComboBox<>();
        txtJml2 = new javax.swing.JTextField();
        lblJml2 = new javax.swing.JLabel();
        btnCekHarga2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        lblIDInventaris3 = new javax.swing.JLabel();
        cbInventaris3 = new javax.swing.JComboBox<>();
        lblTanggal3 = new javax.swing.JLabel();
        dpTanggal3 = new org.jdesktop.swingx.JXDatePicker();
        lblJam3 = new javax.swing.JLabel();
        cbJam3 = new javax.swing.JComboBox<>();
        cbMenit3 = new javax.swing.JComboBox<>();
        lblJml3 = new javax.swing.JLabel();
        txtJml3 = new javax.swing.JTextField();
        btnCekHarga3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        lblIDInventaris4 = new javax.swing.JLabel();
        cbInventaris4 = new javax.swing.JComboBox<>();
        lblTanggal4 = new javax.swing.JLabel();
        dpTanggal4 = new org.jdesktop.swingx.JXDatePicker();
        lblJam4 = new javax.swing.JLabel();
        cbJam4 = new javax.swing.JComboBox<>();
        cbMenit4 = new javax.swing.JComboBox<>();
        lblJml4 = new javax.swing.JLabel();
        txtJml4 = new javax.swing.JTextField();
        btnCekHarga4 = new javax.swing.JButton();
        lblHarga1 = new javax.swing.JLabel();
        lblDiskon = new javax.swing.JLabel();
        txtDiskon1 = new javax.swing.JTextField();
        txtDiskon2 = new javax.swing.JTextField();
        lblHarga2 = new javax.swing.JLabel();
        lblDiskon2 = new javax.swing.JLabel();
        txtDiskon3 = new javax.swing.JTextField();
        lblHarga3 = new javax.swing.JLabel();
        lblDiskon3 = new javax.swing.JLabel();
        lblHarga4 = new javax.swing.JLabel();
        lblDiskon4 = new javax.swing.JLabel();
        txtDiskon4 = new javax.swing.JTextField();
        txtDownPayment2 = new javax.swing.JTextField();
        lblDownPayment2 = new javax.swing.JLabel();
        lblCaraBayar2 = new javax.swing.JLabel();
        txtCaraBayar2 = new javax.swing.JTextField();
        lblCaraBayar1 = new javax.swing.JLabel();
        lblDownPayment1 = new javax.swing.JLabel();
        txtCaraBayar1 = new javax.swing.JTextField();
        txtDownPayment1 = new javax.swing.JTextField();
        txtDownPayment3 = new javax.swing.JTextField();
        lblDownPayment3 = new javax.swing.JLabel();
        lblCaraBayar3 = new javax.swing.JLabel();
        txtCaraBayar3 = new javax.swing.JTextField();
        lblCaraBayar4 = new javax.swing.JLabel();
        txtCaraBayar4 = new javax.swing.JTextField();
        lblDownPayment4 = new javax.swing.JLabel();
        txtDownPayment4 = new javax.swing.JTextField();
        lblCekHarga1 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        lblJumlah1 = new javax.swing.JLabel();
        lblJumlahHarga1 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        lblJumlah2 = new javax.swing.JLabel();
        lblJumlahHarga2 = new javax.swing.JLabel();
        lblJumlah3 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        lblJumlahHarga3 = new javax.swing.JLabel();
        lblJumlahHarga4 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        lblJumlah4 = new javax.swing.JLabel();
        lblCekHarga2 = new javax.swing.JLabel();
        lblCekHarga3 = new javax.swing.JLabel();
        lblCekHarga4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

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

        tblPeminjamanOutdoor.setModel(new javax.swing.table.DefaultTableModel(
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
        tblPeminjamanOutdoor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblPeminjamanOutdoorMousePressed(evt);
            }
        });
        jScrollPane2.setViewportView(tblPeminjamanOutdoor);

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

        jLabel5.setText("Jam Sewa");

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

        jLabel9.setText("Jumlah Hari Sewa");

        lblIDInventaris2.setText("ID Inventaris");

        cbInventaris2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbInventaris2ActionPerformed(evt);
            }
        });

        lblTanggal2.setText("Tanggal Sewa");

        lblJam2.setText("Jam Sewa");

        cbJam2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20" }));

        cbMenit2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "15", "30", "45" }));

        lblJml2.setText("Jumlah Hari Sewa");

        btnCekHarga2.setText("Cek Harga Sewa");
        btnCekHarga2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekHarga2ActionPerformed(evt);
            }
        });

        jButton3.setText("Tambah");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Tambah");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        lblIDInventaris3.setText("ID Inventaris");

        cbInventaris3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbInventaris3ActionPerformed(evt);
            }
        });

        lblTanggal3.setText("Tanggal Sewa");

        lblJam3.setText("Jam Sewa");

        cbJam3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20" }));

        cbMenit3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "15", "30", "45" }));

        lblJml3.setText("Jumlah Hari Sewa");

        btnCekHarga3.setText("Cek Harga Sewa");
        btnCekHarga3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekHarga3ActionPerformed(evt);
            }
        });

        jButton5.setText("Tambah");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator2.setForeground(new java.awt.Color(0, 0, 0));

        jSeparator3.setForeground(new java.awt.Color(0, 0, 0));

        lblIDInventaris4.setText("ID Inventaris");

        cbInventaris4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbInventaris4ActionPerformed(evt);
            }
        });

        lblTanggal4.setText("Tanggal Sewa");

        lblJam4.setText("Jam Sewa");

        cbJam4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20" }));

        cbMenit4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "00", "15", "30", "45" }));

        lblJml4.setText("Jumlah Hari Sewa");

        btnCekHarga4.setText("Cek Harga Sewa");
        btnCekHarga4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCekHarga4ActionPerformed(evt);
            }
        });

        lblHarga1.setText("Harga");

        lblDiskon.setText("Diskon");

        lblHarga2.setText("Harga");

        lblDiskon2.setText("Diskon");

        lblHarga3.setText("Harga");

        lblDiskon3.setText("Diskon");

        lblHarga4.setText("Harga");

        lblDiskon4.setText("Diskon");

        lblDownPayment2.setText("Down Payment");

        lblCaraBayar2.setText("Cara Bayar");

        lblCaraBayar1.setText("Cara Bayar");

        lblDownPayment1.setText("Down Payment");

        lblDownPayment3.setText("Down Payment");

        lblCaraBayar3.setText("Cara Bayar");

        lblCaraBayar4.setText("Cara Bayar");

        lblDownPayment4.setText("Down Payment");

        lblCekHarga1.setText("Klik Cek Harga");

        jButton11.setText("Cek Jumlah Harga Keseluruhan");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        lblJumlah1.setText("Jumlah");

        lblJumlahHarga1.setText("Klik Cek Jumlah Harga");

        jButton12.setText("Cek Jumlah Harga Keseluruhan");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        lblJumlah2.setText("Jumlah");

        lblJumlahHarga2.setText("Klik Cek Jumlah Harga");

        lblJumlah3.setText("Jumlah");

        jButton13.setText("Cek Jumlah Harga Keseluruhan");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        lblJumlahHarga3.setText("Klik Cek Jumlah Harga");

        lblJumlahHarga4.setText("Klik Cek Jumlah Harga");

        jButton14.setText("Cek Jumlah Harga Keseluruhan");

        lblJumlah4.setText("Jumlah");

        lblCekHarga2.setText("Klik Cek Harga");

        lblCekHarga3.setText("Klik Cek Harga");

        lblCekHarga4.setText("Klik Cek Harga");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel5)
                        .addGap(77, 77, 77)
                        .addComponent(cbJam1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(cbMenit1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addComponent(jLabel9)
                        .addGap(33, 33, 33)
                        .addComponent(txtJml1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDiskon)
                            .addComponent(lblHarga1))
                        .addGap(102, 102, 102)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDiskon1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCekHarga1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDiskon2)
                            .addComponent(lblHarga2))
                        .addGap(102, 102, 102)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDiskon2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCekHarga2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDiskon3)
                            .addComponent(lblHarga3))
                        .addGap(102, 102, 102)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDiskon3, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCekHarga3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDiskon4)
                            .addComponent(lblHarga4))
                        .addGap(102, 102, 102)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDiskon4, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCekHarga4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblJam2)
                        .addGap(79, 79, 79)
                        .addComponent(cbJam2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(cbMenit2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblJam3)
                        .addGap(79, 79, 79)
                        .addComponent(cbJam3, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(cbMenit3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblJam4)
                        .addGap(79, 79, 79)
                        .addComponent(cbJam4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(cbMenit4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblIDInventaris2)
                                .addGap(66, 66, 66)
                                .addComponent(cbInventaris2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblTanggal2)
                                .addGap(56, 56, 56)
                                .addComponent(dpTanggal2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblJml2)
                                .addGap(35, 35, 35)
                                .addComponent(txtJml2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnCekHarga2, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblCaraBayar2)
                                .addGap(76, 76, 76)
                                .addComponent(txtCaraBayar2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblDownPayment2)
                                .addGap(54, 54, 54)
                                .addComponent(txtDownPayment2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblJumlah1)
                                .addGap(100, 100, 100)
                                .addComponent(lblJumlahHarga1))
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblIDInventaris3)
                                .addGap(66, 66, 66)
                                .addComponent(cbInventaris3, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblTanggal3)
                                .addGap(56, 56, 56)
                                .addComponent(dpTanggal3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblJml3)
                                .addGap(35, 35, 35)
                                .addComponent(txtJml3, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnCekHarga3, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblCaraBayar3)
                                .addGap(76, 76, 76)
                                .addComponent(txtCaraBayar3, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblDownPayment3)
                                .addGap(54, 54, 54)
                                .addComponent(txtDownPayment3, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblIDInventaris4)
                                .addGap(66, 66, 66)
                                .addComponent(cbInventaris4, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblTanggal4)
                                .addGap(56, 56, 56)
                                .addComponent(dpTanggal4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblJml4)
                                .addGap(35, 35, 35)
                                .addComponent(txtJml4, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnCekHarga4, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblCaraBayar4)
                                .addGap(76, 76, 76)
                                .addComponent(txtCaraBayar4, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblDownPayment4)
                                .addGap(54, 54, 54)
                                .addComponent(txtDownPayment4, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblJumlah2)
                                .addGap(100, 100, 100)
                                .addComponent(lblJumlahHarga2))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblJumlah3)
                                .addGap(100, 100, 100)
                                .addComponent(lblJumlahHarga3))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(lblJumlah4)
                                .addGap(100, 100, 100)
                                .addComponent(lblJumlahHarga4)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel5))
                            .addComponent(cbJam1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbMenit1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel9))
                    .addComponent(txtJml1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addComponent(jButton1)
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblJumlah1)
                    .addComponent(lblJumlahHarga1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addGap(13, 13, 13)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblIDInventaris2))
                    .addComponent(cbInventaris2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lblTanggal2))
                    .addComponent(dpTanggal2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblJam2))
                    .addComponent(cbJam2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbMenit2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblJml2))
                    .addComponent(txtJml2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(btnCekHarga2)
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHarga2)
                    .addComponent(lblCekHarga2))
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblDiskon2))
                    .addComponent(txtDiskon2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblCaraBayar2))
                    .addComponent(txtCaraBayar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblDownPayment2))
                    .addComponent(txtDownPayment2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton12)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblJumlah2)
                    .addComponent(lblJumlahHarga2))
                .addGap(15, 15, 15)
                .addComponent(jButton3)
                .addGap(11, 11, 11)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblIDInventaris3))
                    .addComponent(cbInventaris3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lblTanggal3))
                    .addComponent(dpTanggal3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblJam3))
                    .addComponent(cbJam3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbMenit3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblJml3))
                    .addComponent(txtJml3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(btnCekHarga3)
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHarga3)
                    .addComponent(lblCekHarga3))
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblDiskon3))
                    .addComponent(txtDiskon3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblCaraBayar3))
                    .addComponent(txtCaraBayar3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblDownPayment3))
                    .addComponent(txtDownPayment3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton13)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblJumlah3)
                    .addComponent(lblJumlahHarga3))
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addGap(13, 13, 13)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblIDInventaris4))
                    .addComponent(cbInventaris4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lblTanggal4))
                    .addComponent(dpTanggal4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblJam4))
                    .addComponent(cbJam4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbMenit4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblJml4))
                    .addComponent(txtJml4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addComponent(btnCekHarga4)
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHarga4)
                    .addComponent(lblCekHarga4))
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblDiskon4))
                    .addComponent(txtDiskon4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblCaraBayar4))
                    .addComponent(txtCaraBayar4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lblDownPayment4))
                    .addComponent(txtDownPayment4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton14)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblJumlah4)
                    .addComponent(lblJumlahHarga4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane3.setViewportView(jPanel2);

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

        jButton20.setText("New");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton7.setText("Reset");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton10)
                                .addContainerGap())))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton15)
                            .addComponent(jButton16)
                            .addComponent(jButton17)
                            .addComponent(jButton18))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton6)
                            .addComponent(jButton9)
                            .addComponent(jButton10)
                            .addComponent(jButton20)
                            .addComponent(jButton7))
                        .addContainerGap())
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbInventaris1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbInventaris1ActionPerformed
        String tempID = (String) cbInventaris1.getSelectedItem();
        int ID = 0;
        
        try {
            ID = hashInventaris.get(tempID);

        } catch (NullPointerException e) {
        }
        System.out.println(ID);
    }//GEN-LAST:event_cbInventaris1ActionPerformed

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String tempIDInventaris = (String) cbInventaris1.getSelectedItem();
        int IDInventaris = 0;
        boolean cekharga = false;
        int hargaSewa = 0;

        try {
            IDInventaris = hashInventaris.get(tempIDInventaris);
        } catch (NullPointerException e) {
        }
        
        System.out.println(IDInventaris);
        
        for (InventarisOutdoor IK : ListInventarisOutdoor) {
            if (IDInventaris==IK.getId()) {
                hargaSewa = IK.getHarga();

            }
        }
        System.out.println(hargaSewa);
        
        try {
            int jumlahHari = Integer.parseInt(txtJml1.getText());
            jumlahSewa = hargaSewa * jumlahHari;
            cekharga = true;

        } catch (NumberFormatException e) {
            cekharga = false;
        }
        
        
        if (cekharga) {
            lblCekHarga1.setText(String.valueOf(jumlahSewa));

        }
        else if (cekharga==false) {
            //gagal
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cbInventaris2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbInventaris2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbInventaris2ActionPerformed

    private void btnCekHarga2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekHarga2ActionPerformed
        String tempIDInventaris = (String) cbInventaris2.getSelectedItem();
        int IDInventaris = 0;
        boolean cekharga = false;
        int hargaSewa = 0;

        try {
            IDInventaris = hashInventaris.get(tempIDInventaris);
        } catch (NullPointerException e) {
        }
        
        System.out.println(IDInventaris);
        
        for (InventarisOutdoor IK : ListInventarisOutdoor) {
            if (IDInventaris==IK.getId()) {
                hargaSewa = IK.getHarga();

            }
        }
        System.out.println(hargaSewa);
        
        try {
            int jumlahHari = Integer.parseInt(txtJml2.getText());
            jumlahSewa = hargaSewa * jumlahHari;
            cekharga = true;

        } catch (NumberFormatException e) {
            cekharga = false;
        }
        
        
        if (cekharga) {
            lblCekHarga2.setText(String.valueOf(jumlahSewa));

        }
        else if (cekharga==false) {
            //gagal
        }
    }//GEN-LAST:event_btnCekHarga2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (tambah2 == false) {
            tambah2 = true;
            noShow3();
            jButton3.setText("Sembunyikan");
        }
        else {
            tambah2 = false;
            noShow3();
            jButton3.setText("Tambah");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        //jPanel1.setSize(jPanel1.getWidth(), jPanel1.getHeight()+50);
        
        if (tambah1 == false) {
            tambah1 = true;
            noShow2();
            jButton4.setText("Sembunyikan");
        }
        else{
            tambah1 = false;
            noShow2();
            jButton4.setText("Tambah");

        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void cbInventaris3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbInventaris3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbInventaris3ActionPerformed

    private void btnCekHarga3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekHarga3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCekHarga3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if (tambah3 == false) {
            tambah3 = true;
            noShow4();
            jButton5.setText("Sembunyikan");
        }
        else{
            tambah3 = false;
            noShow4();
            jButton5.setText("Tambah");
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void cbInventaris4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbInventaris4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbInventaris4ActionPerformed

    private void btnCekHarga4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCekHarga4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCekHarga4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (tambah4) {
            inputDatabase1();
            inputDatabase2();
            inputDatabase3();
            inputDatabase4();
            updateTablePeminjaman();
        }
        else if (tambah3) {
            inputDatabase1();
            inputDatabase2();
            inputDatabase3();
            updateTablePeminjaman();
        }
        else if (tambah2) {
            inputDatabase1();
            inputDatabase2();
            updateTablePeminjaman();
        }
        else if (tambah4==false&&tambah3==false&&tambah2==false) {
            inputDatabase1();
            updateTablePeminjaman();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if (index!=0) {
            editDatabase1();
        }
        else if (index==-1){
            
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        boolean berhasil = false;
        int cekHarga = 0;
        int diskon = 0;
        int dp = 0;
        int total = 0;
        int jumlah = 0;
        try {
            cekHarga = Integer.parseInt(lblCekHarga2.getText());
            diskon = Integer.parseInt(txtDiskon2.getText());
            dp = Integer.parseInt(txtDownPayment2.getText());
            jumlah = Integer.parseInt(txtJml2.getText());
            berhasil = true;
        } catch (NumberFormatException e) {
        }
        if (berhasil) {
            total = cekHarga - (diskon + dp);
            lblJumlahHarga2.setText(String.valueOf(total));
        }
        else {
            System.out.println("Salah hahahaaha");
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        boolean berhasil = false;
        int cekHarga = 0;
        int diskon = 0;
        int dp = 0;
        int total = 0;
        int jumlah = 0;
        try {
            cekHarga = Integer.parseInt(lblCekHarga1.getText());
            diskon = Integer.parseInt(txtDiskon1.getText());
            dp = Integer.parseInt(txtDownPayment1.getText());
            jumlah = Integer.parseInt(txtJml1.getText());
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

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        boolean berhasil = false;
        int cekHarga = 0;
        int diskon = 0;
        int dp = 0;
        int total = 0;
        int jumlah = 0;
        try {
            cekHarga = Integer.parseInt(lblCekHarga2.getText());
            diskon = Integer.parseInt(txtDiskon2.getText());
            dp = Integer.parseInt(txtDownPayment2.getText());
            jumlah = Integer.parseInt(txtJml2.getText());
            berhasil = true;
        } catch (NumberFormatException e) {
        }
        if (berhasil) {
            total = cekHarga - (diskon + dp);
            lblJumlahHarga2.setText(String.valueOf(total));
        }
        else {
            System.out.println("Salah hahahaaha");
        }
    }//GEN-LAST:event_jButton13ActionPerformed

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
        this.index = ListPeminjamanOutdoor.size()-1;
        this.showData1();
    }//GEN-LAST:event_jButton18ActionPerformed

    private void tblPeminjamanOutdoorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPeminjamanOutdoorMousePressed
        int rowIndex = tblPeminjamanOutdoor.getSelectedRow();
        index = rowIndex;
        showData1();
        try {
            showData1();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Penambahan Gagal", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_tblPeminjamanOutdoorMousePressed

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
            if (this.ListPeminjamanOutdoor.size() - 1 > this.index) {
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
                Connection con = new Koneksi().getCon();
                PreparedStatement myPreparedStatement = null;
                String sqldelete = "DELETE FROM ou_pengembalian WHERE `id` =?";
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
                        Logger.getLogger(FormPengembalianOutdoor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    ListPeminjamanOutdoor.remove(index);
                    JOptionPane.showMessageDialog(this, "Data berhasil dihapus!", "Informasi", 1);
                    index = -1;
                    updateTablePeminjaman();
                    txtID.setText("");
                    txtIDCustomer1.setText("");
                    cbInventaris1.setSelectedIndex(0);
                    cbJam1.setSelectedIndex(0);
                    cbMenit1.setSelectedIndex(0);
                    txtJml1.setText("");
                    lblCekHarga1.setText("Klik Cek Harga");
                    txtDiskon1.setText("");
                    txtCaraBayar1.setText("");
                    txtDownPayment1.setText("");
                }
            }
            else if (berhasil == false) {
                JOptionPane.showMessageDialog(this, "Data tidak berhasil dihapus", "Informasi", 1);
            }
            
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        int id = ListPeminjamanOutdoor.get(ListPeminjamanOutdoor.size()-1).getId()+1;
        txtID.setText(String.valueOf(id));
        txtIDCustomer1.setText("");
        cbInventaris1.setSelectedIndex(0);
        cbJam1.setSelectedIndex(0);
        cbMenit1.setSelectedIndex(0);
        txtJml1.setText("");
        lblCekHarga1.setText("Klik Cek Harga");
        txtDiskon1.setText("");
        txtCaraBayar1.setText("");
        txtDownPayment1.setText("");
        lblJumlahHarga1.setText("Klik Cek Jumlah Harga");
        
        cbInventaris2.setSelectedIndex(0);
        cbJam2.setSelectedIndex(0);
        cbMenit2.setSelectedIndex(0);
        txtJml2.setText("");
        lblCekHarga2.setText("Klik Cek Harga");
        txtDiskon2.setText("");
        txtCaraBayar2.setText("");
        txtDownPayment2.setText("");
        lblJumlahHarga2.setText("Klik Cek Jumlah Harga");
        
        cbInventaris3.setSelectedIndex(0);
        cbJam3.setSelectedIndex(0);
        cbMenit3.setSelectedIndex(0);
        txtJml3.setText("");
        lblCekHarga3.setText("Klik Cek Harga");
        txtDiskon3.setText("");
        txtCaraBayar3.setText("");
        txtDownPayment3.setText("");
        lblJumlahHarga3.setText("Klik Cek Jumlah Harga");
        
        cbInventaris4.setSelectedIndex(0);
        cbJam4.setSelectedIndex(0);
        cbMenit4.setSelectedIndex(0);
        txtJml4.setText("");
        lblCekHarga4.setText("Klik Cek Harga");
        txtDiskon4.setText("");
        txtCaraBayar4.setText("");
        txtDownPayment4.setText("");
        lblJumlahHarga4.setText("Klik Cek Jumlah Harga");
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        txtIDCustomer1.setText("");
        cbInventaris1.setSelectedIndex(0);
        cbJam1.setSelectedIndex(0);
        cbMenit1.setSelectedIndex(0);
        txtJml1.setText("");
        lblCekHarga1.setText("Klik Cek Harga");
        txtDiskon1.setText("");
        txtCaraBayar1.setText("");
        txtDownPayment1.setText("");
        lblJumlahHarga1.setText("Klik Cek Jumlah Harga");
        
        cbInventaris2.setSelectedIndex(0);
        cbJam2.setSelectedIndex(0);
        cbMenit2.setSelectedIndex(0);
        txtJml2.setText("");
        lblCekHarga2.setText("Klik Cek Harga");
        txtDiskon2.setText("");
        txtCaraBayar2.setText("");
        txtDownPayment2.setText("");
        lblJumlahHarga2.setText("Klik Cek Jumlah Harga");
        
        cbInventaris3.setSelectedIndex(0);
        cbJam3.setSelectedIndex(0);
        cbMenit3.setSelectedIndex(0);
        txtJml3.setText("");
        lblCekHarga3.setText("Klik Cek Harga");
        txtDiskon3.setText("");
        txtCaraBayar3.setText("");
        txtDownPayment3.setText("");
        lblJumlahHarga3.setText("Klik Cek Jumlah Harga");
        
        cbInventaris4.setSelectedIndex(0);
        cbJam4.setSelectedIndex(0);
        cbMenit4.setSelectedIndex(0);
        txtJml4.setText("");
        lblCekHarga4.setText("Klik Cek Harga");
        txtDiskon4.setText("");
        txtCaraBayar4.setText("");
        txtDownPayment4.setText("");
        lblJumlahHarga4.setText("Klik Cek Jumlah Harga");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        this.dispose();
        java.awt.EventQueue.invokeLater(() -> {
            new MenuOrderOutdoor().setVisible(true);
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
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormPengembalianOutdoor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPengembalianOutdoor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPengembalianOutdoor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPengembalianOutdoor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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

        /* Create and display the form */
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FormPengembalianOutdoor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(FormPengembalianOutdoor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FormPengembalianOutdoor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(FormPengembalianOutdoor.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormPengembalianOutdoor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCekHarga2;
    private javax.swing.JButton btnCekHarga3;
    private javax.swing.JButton btnCekHarga4;
    private javax.swing.JComboBox<String> cbInventaris1;
    private javax.swing.JComboBox<String> cbInventaris2;
    private javax.swing.JComboBox<String> cbInventaris3;
    private javax.swing.JComboBox<String> cbInventaris4;
    private javax.swing.JComboBox<String> cbJam1;
    private javax.swing.JComboBox<String> cbJam2;
    private javax.swing.JComboBox<String> cbJam3;
    private javax.swing.JComboBox<String> cbJam4;
    private javax.swing.JComboBox<String> cbMenit1;
    private javax.swing.JComboBox<String> cbMenit2;
    private javax.swing.JComboBox<String> cbMenit3;
    private javax.swing.JComboBox<String> cbMenit4;
    private org.jdesktop.swingx.JXDatePicker dpTanggal2;
    private org.jdesktop.swingx.JXDatePicker dpTanggal3;
    private org.jdesktop.swingx.JXDatePicker dpTanggal4;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private javax.swing.JLabel lblCaraBayar1;
    private javax.swing.JLabel lblCaraBayar2;
    private javax.swing.JLabel lblCaraBayar3;
    private javax.swing.JLabel lblCaraBayar4;
    private javax.swing.JLabel lblCekHarga1;
    private javax.swing.JLabel lblCekHarga2;
    private javax.swing.JLabel lblCekHarga3;
    private javax.swing.JLabel lblCekHarga4;
    private javax.swing.JLabel lblDiskon;
    private javax.swing.JLabel lblDiskon2;
    private javax.swing.JLabel lblDiskon3;
    private javax.swing.JLabel lblDiskon4;
    private javax.swing.JLabel lblDownPayment1;
    private javax.swing.JLabel lblDownPayment2;
    private javax.swing.JLabel lblDownPayment3;
    private javax.swing.JLabel lblDownPayment4;
    private javax.swing.JLabel lblHarga1;
    private javax.swing.JLabel lblHarga2;
    private javax.swing.JLabel lblHarga3;
    private javax.swing.JLabel lblHarga4;
    private javax.swing.JLabel lblIDInventaris2;
    private javax.swing.JLabel lblIDInventaris3;
    private javax.swing.JLabel lblIDInventaris4;
    private javax.swing.JLabel lblJam2;
    private javax.swing.JLabel lblJam3;
    private javax.swing.JLabel lblJam4;
    private javax.swing.JLabel lblJml2;
    private javax.swing.JLabel lblJml3;
    private javax.swing.JLabel lblJml4;
    private javax.swing.JLabel lblJumlah1;
    private javax.swing.JLabel lblJumlah2;
    private javax.swing.JLabel lblJumlah3;
    private javax.swing.JLabel lblJumlah4;
    private javax.swing.JLabel lblJumlahHarga1;
    private javax.swing.JLabel lblJumlahHarga2;
    private javax.swing.JLabel lblJumlahHarga3;
    private javax.swing.JLabel lblJumlahHarga4;
    private javax.swing.JLabel lblTanggal2;
    private javax.swing.JLabel lblTanggal3;
    private javax.swing.JLabel lblTanggal4;
    private javax.swing.JTable tblCustomer;
    private javax.swing.JTable tblPeminjamanOutdoor;
    private javax.swing.JTextField txtCaraBayar1;
    private javax.swing.JTextField txtCaraBayar2;
    private javax.swing.JTextField txtCaraBayar3;
    private javax.swing.JTextField txtCaraBayar4;
    private javax.swing.JTextField txtDiskon1;
    private javax.swing.JTextField txtDiskon2;
    private javax.swing.JTextField txtDiskon3;
    private javax.swing.JTextField txtDiskon4;
    private javax.swing.JTextField txtDownPayment1;
    private javax.swing.JTextField txtDownPayment2;
    private javax.swing.JTextField txtDownPayment3;
    private javax.swing.JTextField txtDownPayment4;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtIDCustomer1;
    private javax.swing.JTextField txtJml1;
    private javax.swing.JTextField txtJml2;
    private javax.swing.JTextField txtJml3;
    private javax.swing.JTextField txtJml4;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
    public void inputDatabase1() {
        PreparedStatement myPS1 = null;
        
        boolean berhasil = false;
        String cbJam = (String) cbJam1.getSelectedItem();
        String cbMenit = (String) cbMenit1.getSelectedItem();
        
        int id = 0;
        int id_cust = 0;
        int id_inven = 0;
        String tgl_sewa = null;
        String jam_sewa = cbJam +":"+ cbMenit;
        String tgl_kembali = null;
        String jam_kembali = jam_sewa;
        
        int jumlahHari = 0;//tidak input ke db
        
        int cekHarga = 0;//tidak input ke db
        
        int diskon = 0;
        
        int dp = 0;
        
        String cara_bayar = txtCaraBayar1.getText();
        int denda = 0;
        int jumlahHarga = 0;//tidak input ke db
        
        String tempInventaris = (String) cbInventaris1.getSelectedItem();
        
        try {
            id_inven = hashInventaris.get(tempInventaris);
        } catch (NullPointerException e) {
        }
        
        
        try {
            id = Integer.parseInt(txtID.getText());
            id_cust = Integer.parseInt(txtIDCustomer1.getText());
            jumlahHari = Integer.parseInt(txtJml1.getText());
            cekHarga = Integer.parseInt(lblCekHarga1.getText());
            diskon = Integer.parseInt(txtDiskon1.getText());
            dp = Integer.parseInt(txtDownPayment1.getText());
            jumlahHarga = Integer.parseInt(lblJumlahHarga1.getText());
            
            sdfThnBlnTgl = new SimpleDateFormat("yyyy-MM-dd");
            Date input = jXDatePicker1.getDate();
            Instant instant = input.toInstant();
            ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
            LocalDate date = zdt.toLocalDate();
            
            tgl_sewa = date.toString();
            tgl_kembali = date.plusDays(jumlahHari).toString();
            System.out.println(tgl_kembali);
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,"Data yang Anda Masukkan Salah", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
        }

        try {
            Connection con = new Koneksi().getCon();
            
            String sqlinput = "INSERT INTO `ou_pengembalian`\n" +
"            (`id`, `id_cust`, `id_inven`, `tgl_sewa`, `jam_sewa`, `tgl_kembali`, `jam_kembali`, `cara_bayar`, `dp`, `diskon`, `denda`) \n" +
"            VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            
            myPS1 = con.prepareStatement(sqlinput);
            myPS1.setInt(1, id);
            myPS1.setInt(2, id_cust);
            myPS1.setInt(3, id_inven);
            myPS1.setString(4, tgl_sewa);
            myPS1.setString(5, jam_sewa);
            myPS1.setString(6, tgl_kembali);
            myPS1.setString(7, jam_kembali);
            myPS1.setString(8, cara_bayar);
            myPS1.setInt(9, dp);
            myPS1.setInt(10, diskon);
            myPS1.setInt(11, denda);
            berhasil = true;


        }
        catch(MySQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            berhasil = false;
        } catch (SQLException ex) {
            berhasil = false;
            Logger.getLogger(FormPengembalianOutdoor.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (berhasil == true) {
            try {
                myPS1.executeUpdate();
                
                if (!tambah2) {
                    JOptionPane.showMessageDialog(this,"Data telah berhasil ditambahkan", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    
                }
                
            } catch (SQLException ex) {
            }
            PinjamKembaliOutdoor PKK = new PinjamKembaliOutdoor();
            
            PKK.setId(id);
            PKK.setId_cust(id_cust);
            PKK.setNama_cust(nama_cust);
            PKK.setId_inventaris(id_inven);
            PKK.setNama_inventaris(tempInventaris);
            PKK.setTgl_sewa(tgl_sewa);
            PKK.setJam_sewa(jam_sewa);
            PKK.setTgl_kembali(tgl_kembali);
            PKK.setJam_kembali(jam_kembali);
            PKK.setHarga(cekHarga);
            PKK.setDp(dp);
            PKK.setDiskon(diskon);
            PKK.setDenda(denda);
            PKK.setJml(jumlahHarga);
            PKK.setCara_bayar(cara_bayar);
            
            this.ListPeminjamanOutdoor.add(PKK);
            
            
            
//            MerkOutdoor newMK = new MerkOutdoor();
//            newMK.setId(field1);
//            newMK.setId_tipe(field2);
//            newMK.setTipe(tempTipe);
//            newMK.setNama(field3);
//            this.listMerkOutdoor.add(newMK);
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
    
    public void inputDatabase2() {
        PreparedStatement myPS2 = null;
        
        boolean berhasil = false;
        String cbJam = (String) cbJam2.getSelectedItem();
        String cbMenit = (String) cbMenit2.getSelectedItem();
        
        int id = 0;
        int id_cust = 0;
        int id_inven = 0;
        String tgl_sewa = null;
        String jam_sewa = cbJam +":"+ cbMenit;
        String tgl_kembali = null;
        String jam_kembali = jam_sewa;
        
        int jumlahHari = 0;//tidak input ke db
        
        int cekHarga = 0;//tidak input ke db
        
        int diskon = 0;
        
        int dp = 0;
        
        String cara_bayar = txtCaraBayar2.getText();
        int denda = 0;
        int jumlahHarga = 0;//tidak input ke db
        
        String tempInventaris = (String) cbInventaris2.getSelectedItem();
        
        try {
            id_inven = hashInventaris.get(tempInventaris);
        } catch (NullPointerException e) {
        }
        
        
        try {
            id = Integer.parseInt(txtID.getText());
            id_cust = Integer.parseInt(txtIDCustomer1.getText());
            jumlahHari = Integer.parseInt(txtJml2.getText());
            cekHarga = Integer.parseInt(lblCekHarga2.getText());
            diskon = Integer.parseInt(txtDiskon2.getText());
            dp = Integer.parseInt(txtDownPayment2.getText());
            jumlahHarga = Integer.parseInt(lblJumlahHarga2.getText());
            
            sdfThnBlnTgl = new SimpleDateFormat("yyyy-MM-dd");
            Date input = dpTanggal2.getDate();
            Instant instant = input.toInstant();
            ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
            LocalDate date = zdt.toLocalDate();
            
            tgl_sewa = date.toString();
            tgl_kembali = date.plusDays(jumlahHari).toString();
            System.out.println(tgl_kembali);
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,"Data yang Anda Masukkan Salah", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
        }
        

        try {
            Connection con = new Koneksi().getCon();
            
            String sqlinput = "INSERT INTO `ou_pengembalian`\n" +
"            (`id`, `id_cust`, `id_inven`, `tgl_sewa`, `jam_sewa`, `tgl_kembali`, `jam_kembali`, `cara_bayar`, `dp`, `diskon`, `denda`) \n" +
"            VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            
            myPS2 = con.prepareStatement(sqlinput);
            myPS2.setInt(1, id);
            myPS2.setInt(2, id_cust);
            myPS2.setInt(3, id_inven);
            myPS2.setString(4, tgl_sewa);
            myPS2.setString(5, jam_sewa);
            myPS2.setString(6, tgl_kembali);
            myPS2.setString(7, jam_kembali);
            myPS2.setString(8, cara_bayar);
            myPS2.setInt(9, dp);
            myPS2.setInt(10, diskon);
            myPS2.setInt(11, denda);

            berhasil = true;
            
        }
        catch (MySQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
        }
        catch(SQLException e) {
            e.printStackTrace();
            berhasil = false;
        }
        if (berhasil == true) {
            try {
                myPS2.executeUpdate();
                if (!tambah3) {
                    JOptionPane.showMessageDialog(this,"Data telah berhasil ditambahkan", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    
                }
            } catch (SQLException ex) {
            }
            PinjamKembaliOutdoor PKK = new PinjamKembaliOutdoor();
            
            PKK.setId(id);
            PKK.setId_cust(id_cust);
            PKK.setNama_cust(nama_cust);
            PKK.setId_inventaris(id_inven);
            PKK.setNama_inventaris(tempInventaris);
            PKK.setTgl_sewa(tgl_sewa);
            PKK.setJam_sewa(jam_sewa);
            PKK.setTgl_kembali(tgl_kembali);
            PKK.setJam_kembali(jam_kembali);
            PKK.setHarga(cekHarga);
            PKK.setDp(dp);
            PKK.setDiskon(diskon);
            PKK.setDenda(denda);
            PKK.setJml(jumlahHarga);
            PKK.setCara_bayar(cara_bayar);
            
            this.ListPeminjamanOutdoor.add(PKK);
            
        }
        else if (berhasil == false) {
            JOptionPane.showMessageDialog(this,"Penambahan Gagal", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
        }
    
    }
    
    public void inputDatabase3() {
        PreparedStatement myPS3 = null;
        
        boolean berhasil = false;
        String cbJam = (String) cbJam3.getSelectedItem();
        String cbMenit = (String) cbMenit3.getSelectedItem();
        
        int id = 0;
        int id_cust = 0;
        int id_inven = 0;
        String tgl_sewa = null;
        String jam_sewa = cbJam +":"+ cbMenit;
        String tgl_kembali = null;
        String jam_kembali = jam_sewa;
        
        int jumlahHari = 0;//tidak input ke db
        
        int cekHarga = 0;//tidak input ke db
        
        int diskon = 0;
        
        int dp = 0;
        
        String cara_bayar = txtCaraBayar3.getText();
        int denda = 0;
        int jumlahHarga = 0;//tidak input ke db
        
        String tempInventaris = (String) cbInventaris3.getSelectedItem();
        
        try {
            id_inven = hashInventaris.get(tempInventaris);
        } catch (NullPointerException e) {
        }
        
        
        try {
            id = Integer.parseInt(txtID.getText());
            id_cust = Integer.parseInt(txtIDCustomer1.getText());
            jumlahHari = Integer.parseInt(txtJml3.getText());
            cekHarga = Integer.parseInt(lblCekHarga3.getText());
            diskon = Integer.parseInt(txtDiskon3.getText());
            dp = Integer.parseInt(txtDownPayment3.getText());
            jumlahHarga = Integer.parseInt(lblJumlahHarga3.getText());
            
            sdfThnBlnTgl = new SimpleDateFormat("yyyy-MM-dd");
            Date input = dpTanggal3.getDate();
            Instant instant = input.toInstant();
            ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
            LocalDate date = zdt.toLocalDate();
            
            tgl_sewa = date.toString();
            tgl_kembali = date.plusDays(jumlahHari).toString();
            System.out.println(tgl_kembali);
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,"Data yang Anda Masukkan Salah", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
        }

        try {
            Connection con = new Koneksi().getCon();
            
            String sqlinput = "INSERT INTO `ou_pengembalian`\n" +
"            (`id`, `id_cust`, `id_inven`, `tgl_sewa`, `jam_sewa`, `tgl_kembali`, `jam_kembali`, `cara_bayar`, `dp`, `diskon`, `denda`) \n" +
"            VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            
            myPS3 = con.prepareStatement(sqlinput);
            myPS3.setInt(1, id);
            myPS3.setInt(2, id_cust);
            myPS3.setInt(3, id_inven);
            myPS3.setString(4, tgl_sewa);
            myPS3.setString(5, jam_sewa);
            myPS3.setString(6, tgl_kembali);
            myPS3.setString(7, jam_kembali);
            myPS3.setString(8, cara_bayar);
            myPS3.setInt(9, dp);
            myPS3.setInt(10, diskon);
            myPS3.setInt(11, denda);

            berhasil = true;
        }
        catch (MySQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
        }
        catch(SQLException e) {
            e.printStackTrace();
            berhasil = false;
        }
        if (berhasil == true) {
            try {
                myPS3.executeUpdate();
                
                if (!tambah4) {
                    JOptionPane.showMessageDialog(this,"Data telah berhasil ditambahkan", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    
                }
            } catch (SQLException ex) {
            }
            
            PinjamKembaliOutdoor PKK = new PinjamKembaliOutdoor();
            
            PKK.setId(id);
            PKK.setId_cust(id_cust);
            PKK.setNama_cust(nama_cust);
            PKK.setId_inventaris(id_inven);
            PKK.setNama_inventaris(tempInventaris);
            PKK.setTgl_sewa(tgl_sewa);
            PKK.setJam_sewa(jam_sewa);
            PKK.setTgl_kembali(tgl_kembali);
            PKK.setJam_kembali(jam_kembali);
            PKK.setHarga(cekHarga);
            PKK.setDp(dp);
            PKK.setDiskon(diskon);
            PKK.setDenda(denda);
            PKK.setJml(jumlahHarga);
            PKK.setCara_bayar(cara_bayar);
            
            this.ListPeminjamanOutdoor.add(PKK);
            
        }
        else if (berhasil == false) {
            JOptionPane.showMessageDialog(this,"Penambahan Gagal", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
        }
    
    }
    
    public void inputDatabase4() {
        PreparedStatement myPS4 = null;
        
        boolean berhasil = false;
        String cbJam = (String) cbJam4.getSelectedItem();
        String cbMenit = (String) cbMenit4.getSelectedItem();
        
        int id = 0;
        int id_cust = 0;
        int id_inven = 0;
        String tgl_sewa = null;
        String jam_sewa = cbJam +":"+ cbMenit;
        String tgl_kembali = null;
        String jam_kembali = jam_sewa;
        
        int jumlahHari = 0;//tidak input ke db
        
        int cekHarga = 0;//tidak input ke db
        
        int diskon = 0;
        
        int dp = 0;
        
        String cara_bayar = txtCaraBayar4.getText();
        int denda = 0;
        int jumlahHarga = 0;//tidak input ke db
        
        String tempInventaris = (String) cbInventaris4.getSelectedItem();
        
        try {
            id_inven = hashInventaris.get(tempInventaris);
        } catch (NullPointerException e) {
        }
        
        
        try {
            id = Integer.parseInt(txtID.getText());
            id_cust = Integer.parseInt(txtIDCustomer1.getText());
            jumlahHari = Integer.parseInt(txtJml4.getText());
            cekHarga = Integer.parseInt(lblCekHarga4.getText());
            diskon = Integer.parseInt(txtDiskon4.getText());
            dp = Integer.parseInt(txtDownPayment4.getText());
            jumlahHarga = Integer.parseInt(lblJumlahHarga4.getText());
            
            sdfThnBlnTgl = new SimpleDateFormat("yyyy-MM-dd");
            Date input = dpTanggal4.getDate();
            Instant instant = input.toInstant();
            ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
            LocalDate date = zdt.toLocalDate();
            
            tgl_sewa = date.toString();
            tgl_kembali = date.plusDays(jumlahHari).toString();
            System.out.println(tgl_kembali);
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,"Data yang Anda Masukkan Salah", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
        }

        try {
            Connection con = new Koneksi().getCon();
            
            String sqlinput = "INSERT INTO `ou_pengembalian`\n" +
"            (`id`, `id_cust`, `id_inven`, `tgl_sewa`, `jam_sewa`, `tgl_kembali`, `jam_kembali`, `cara_bayar`, `dp`, `diskon`, `denda`) \n" +
"            VALUES (?,?,?,?,?,?,?,?,?,?,?)";
            
            myPS4 = con.prepareStatement(sqlinput);
            myPS4.setInt(1, id);
            myPS4.setInt(2, id_cust);
            myPS4.setInt(3, id_inven);
            myPS4.setString(4, tgl_sewa);
            myPS4.setString(5, jam_sewa);
            myPS4.setString(6, tgl_kembali);
            myPS4.setString(7, jam_kembali);
            myPS4.setString(8, cara_bayar);
            myPS4.setInt(9, dp);
            myPS4.setInt(10, diskon);
            myPS4.setInt(11, denda);

            berhasil = true;

        }
        catch (MySQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
        }
        catch(SQLException e) {
            e.printStackTrace();
            berhasil = false;
        }
        if (berhasil == true) {
            try {
                myPS4.executeUpdate();
                    JOptionPane.showMessageDialog(this,"Data telah berhasil ditambahkan", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
                
            } catch (SQLException ex) {
            }
            PinjamKembaliOutdoor PKK = new PinjamKembaliOutdoor();
            
            PKK.setId(id);
            PKK.setId_cust(id_cust);
            PKK.setNama_cust(nama_cust);
            PKK.setId_inventaris(id_inven);
            PKK.setNama_inventaris(tempInventaris);
            PKK.setTgl_sewa(tgl_sewa);
            PKK.setJam_sewa(jam_sewa);
            PKK.setTgl_kembali(tgl_kembali);
            PKK.setHarga(cekHarga);
            PKK.setJam_kembali(jam_kembali);
            PKK.setHarga(cekHarga);
            PKK.setDp(dp);
            PKK.setDiskon(diskon);
            PKK.setDenda(denda);
            PKK.setJml(jumlahHarga);
            PKK.setCara_bayar(cara_bayar);
            
            this.ListPeminjamanOutdoor.add(PKK);
            
            
        }
        else if (berhasil == false) {
            JOptionPane.showMessageDialog(this,"Penambahan Gagal", "Informasi",
                JOptionPane.INFORMATION_MESSAGE);
        }
    
    }
    
    public void editDatabase1() {
        PreparedStatement myEditPS1 = null;
        
        boolean berhasil = false;
        String cbJam = (String) cbJam1.getSelectedItem();
        String cbMenit = (String) cbMenit1.getSelectedItem();
        
        int id = 0;
        int id_cust = 0;
        int id_inven = 0;
        String tgl_sewa = null;
        String jam_sewa = cbJam +":"+ cbMenit;
        String tgl_kembali = null;
        String jam_kembali = jam_sewa;
        
        int jumlahHari = 0;//tidak input ke db
        
        int cekHarga = 0;//tidak input ke db
        
        int diskon = 0;
        
        int dp = 0;
        
        String cara_bayar = txtCaraBayar1.getText();
        int denda = 0;
        int jumlahHarga = 0;//tidak input ke db
        
        String tempInventaris = (String) cbInventaris1.getSelectedItem();
        
        
        if (JOptionPane.showConfirmDialog(this, "Yakin akan dihapus?", "Hapus", 0) == 0) {
            String ketikKONFIRMASI = JOptionPane.showInputDialog(this,
                        "Ketik KONFIRMASI untuk menghapus data", null);
            String key = "KONFIRMASI";
            
            if (ketikKONFIRMASI.equals(key)) {
                try {
                    id_inven = hashInventaris.get(tempInventaris);
                } catch (NullPointerException e) {
                }


                try {
                    id = Integer.parseInt(txtID.getText());
                    id_cust = Integer.parseInt(txtIDCustomer1.getText());
                    jumlahHari = Integer.parseInt(txtJml1.getText());
                    cekHarga = Integer.parseInt(lblCekHarga1.getText());
                    diskon = Integer.parseInt(txtDiskon1.getText());
                    dp = Integer.parseInt(txtDownPayment1.getText());
                    jumlahHarga = Integer.parseInt(lblJumlahHarga1.getText());

                    sdfThnBlnTgl = new SimpleDateFormat("yyyy-MM-dd");
                    Date input = jXDatePicker1.getDate();
                    Instant instant = input.toInstant();
                    ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
                    LocalDate date = zdt.toLocalDate();

                    tgl_sewa = date.toString();
                    tgl_kembali = date.plusDays(jumlahHari).toString();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this,"Data yang Anda Masukkan Salah", "Informasi",
                        JOptionPane.INFORMATION_MESSAGE);
                }

                try {
                    Connection con = new Koneksi().getCon();

                    String sqledit = "UPDATE `ou_pengembalian` SET "
                            + "`id_cust`=?,"
                            + "`id_inven`=?,"
                            + "`tgl_sewa`=?,"
                            + "`jam_sewa`=?,"
                            + "`tgl_kembali`=?,"
                            + "`jam_kembali`=?,"
                            + "`cara_bayar`=?,"
                            + "`dp`=?,"
                            + "`diskon`=?,"
                            + "`denda`=? "
                            + "WHERE `id`=?";

                    myEditPS1 = con.prepareStatement(sqledit);

                    myEditPS1.setInt(1, id_cust);
                    myEditPS1.setInt(2, id_inven);
                    myEditPS1.setString(3, tgl_sewa);
                    myEditPS1.setString(4, jam_sewa);
                    myEditPS1.setString(5, tgl_kembali);
                    myEditPS1.setString(6, jam_kembali);
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
                    PinjamKembaliOutdoor newPKK = new PinjamKembaliOutdoor();

                    newPKK.setId(id);
                    newPKK.setId_cust(id_cust);
                    newPKK.setNama_cust(nama_cust);
                    newPKK.setId_inventaris(id_inven);
                    newPKK.setNama_inventaris(tempInventaris);
                    newPKK.setTgl_sewa(tgl_sewa);
                    newPKK.setJam_sewa(jam_sewa);
                    newPKK.setTgl_kembali(tgl_kembali);
                    newPKK.setJam_kembali(jam_kembali);
                    newPKK.setHarga(cekHarga);
                    newPKK.setDp(dp);
                    newPKK.setDiskon(diskon);
                    newPKK.setDenda(denda);
                    newPKK.setJml(jumlahHarga);
                    newPKK.setCara_bayar(cara_bayar);

                    this.ListPeminjamanOutdoor.set(index, newPKK);

                    updateTablePeminjaman();

                }
                else if (berhasil == false) {
                    JOptionPane.showMessageDialog(this,"Penambahan Gagal", "Informasi",
                        JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else if (!ketikKONFIRMASI.equals(key)) {
                JOptionPane.showMessageDialog(this, "Data tidak berhasil dihapus", "Informasi", 1);
            }
        }
    }
    
    public void showData1() {
        PinjamKembaliOutdoor PKK = ListPeminjamanOutdoor.get(index);
        Date tgl_sewa = null;
        Date tgl_kembali = null;

        
        txtID.setText(String.valueOf(PKK.getId()));
        txtIDCustomer1.setText(String.valueOf(PKK.getId_cust()));
        nama_cust = PKK.getNama_cust();
        cbInventaris1.setSelectedItem(PKK.getNama_inventaris());

        String tanggal_sewa = PKK.getTgl_sewa();
        String tanggal_kembali = PKK.getTgl_kembali();
        try {
            tgl_sewa = sdfThnBlnTgl.parse(tanggal_sewa);
            tgl_kembali = sdfThnBlnTgl.parse(tanggal_kembali);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        jXDatePicker1.setDate(tgl_sewa);
        
        long diff = tgl_kembali.getTime() - tgl_sewa.getTime();
        String jumlah_hari =  String.valueOf(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
        txtJml1.setText(jumlah_hari);
        
        
        String tempJamSewa = PKK.getJam_kembali();
        String jam = tempJamSewa.substring(0,1);
        String menit = tempJamSewa.substring(3,4);
        
        cbJam1.setSelectedItem(jam);
        cbMenit1.setSelectedItem(menit);
        lblCekHarga1.setText(String.valueOf(PKK.getHarga()));
        
        txtDownPayment1.setText(String.valueOf(PKK.getDp()));
        
        txtDiskon1.setText(String.valueOf(PKK.getDiskon()));
        lblJumlahHarga1.setText(String.valueOf(PKK.getJml()));
        
        txtCaraBayar1.setText(PKK.getCara_bayar());
        
        System.out.println(nama_cust);
        
    }
}