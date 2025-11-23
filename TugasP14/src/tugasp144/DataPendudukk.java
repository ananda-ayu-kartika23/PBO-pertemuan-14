/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tugasp144;

/**
 *
 * @author ANANDA AYU KARTIKA S
 */
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.*;
import java.util.HashMap;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
//import jnafilechooser.api.JnaFileChooser;

public class DataPendudukk extends javax.swing.JFrame {

    private EntityManagerFactory emf;
    private EntityManager em;

    String nipLama, namaLama, kelaminLama, pekerjaanLama, alamatLama;
    String idAlamatLama, kelurahanLama, kecamatanLama, kabupatenLama, provinsiLama;

    public void connect() {
        try {
            emf = Persistence.createEntityManagerFactory("TugasP14PU");
            em = emf.createEntityManager();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Koneksi gagal: " + e.getMessage());
        }
    }

    public DataPendudukk() {
        initComponents();
        setLocationRelativeTo(null);
        connect();
        showTablePenduduk();
        showTableAlamat();

        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = jTable1.getSelectedRow();

                nipLama = jTable1.getValueAt(row, 0).toString();
                namaLama = jTable1.getValueAt(row, 1).toString();
                kelaminLama = jTable1.getValueAt(row, 2).toString();
                pekerjaanLama = jTable1.getValueAt(row, 3).toString();
                alamatLama = jTable1.getValueAt(row, 4).toString();
            }
        });

        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = jTable2.getSelectedRow();

                idAlamatLama = jTable2.getValueAt(row, 0).toString();
                kelurahanLama = jTable2.getValueAt(row, 1).toString();
                kecamatanLama = jTable2.getValueAt(row, 2).toString();
                kabupatenLama = jTable2.getValueAt(row, 3).toString();
                provinsiLama = jTable2.getValueAt(row, 4).toString();
            }
        });
    }

    public void showTablePenduduk() {
        try {
            em.clear();

            List<Penduduk> hasil = em.createNamedQuery("Penduduk.findAll", Penduduk.class).getResultList();

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);

            DefaultTableModel model = new DefaultTableModel(
                    new String[]{"NIP", "Nama", "Kelamin", "Pekerjaan", "Alamat"}, 0
            );

            for (Penduduk ps : hasil) {
                model.addRow(new Object[]{
                    ps.getNip(),
                    ps.getNama(),
                    ps.getKelamin(),
                    ps.getPekerjaan(),
                    ps.getAlamatId()
                });
            }

            jTable1.setModel(model);
            jTable1.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal tampil data: " + e.getMessage());
        }
    }

    public void showTableAlamat() {
        try {
            em.clear();

            List<Alamat> hasil = em.createNamedQuery("Alamat.findAll", Alamat.class).getResultList();

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);

            DefaultTableModel model = new DefaultTableModel(
                    new String[]{"id Alamat", "Kelurahan", "Kecamatan", "Kabupaten", "Provinsi"}, 0
            );
            for (Alamat a : hasil) {
                model.addRow(new Object[]{
                    a.getIdAlamat(),
                    a.getKelurahan(),
                    a.getKecamatan(),
                    a.getKabupaten(),
                    a.getProvinsi()
                });
            }

            jTable2.setModel(model);
            jTable2.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal tampil data: " + e.getMessage());
        }
    }

    public void exportToCSV(JTable table, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {

            TableModel model = table.getModel();

            for (int i = 0; i < model.getColumnCount(); i++) {
                writer.append(model.getColumnName(i));
                if (i < model.getColumnCount() - 1) {
                    writer.append(",");
                }
            }
            writer.append("\n");
            for (int row = 0; row < model.getRowCount(); row++) {
                for (int col = 0; col < model.getColumnCount(); col++) {
                    Object value = model.getValueAt(row, col);
                    writer.append(value == null ? "" : value.toString());
                    if (col < model.getColumnCount() - 1) {
                        writer.append(",");
                    }
                }
                writer.append("\n");
            }
            writer.flush();
            JOptionPane.showMessageDialog(this, "Data berhasil diekspor ke CSV!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal ekspor CSV: " + e.getMessage());
        }
    }

    private void imporCsvKeDatabasePenduduk() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Pilih File CSV");

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String fileName = file.getName();

            if (!fileName.toLowerCase().endsWith(".csv")) {
                JOptionPane.showMessageDialog(this,
                        "File yang dipilih bukan file CSV!\nSilakan pilih file dengan ekstensi .csv",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {

                String line;
                br.readLine(); // skip header

                boolean adaYangMasuk = false;
                StringBuilder errorLog = new StringBuilder(); // kumpulkan error

                em.getTransaction().begin();

                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length == 5) {
                        Penduduk a = new Penduduk();
                        a.setNip(data[0].trim());
                        a.setNama(data[1].trim());
                        a.setKelamin(data[2].trim());
                        a.setPekerjaan(data[3].trim());
                        String alamatKey = data[4].trim();

                        Alamat alamat = em.find(Alamat.class, alamatKey);

                        if (alamat != null) {
                            a.setAlamatId(alamat);
                            em.persist(a);
                            adaYangMasuk = true;
                        } else {
                            String errorMessage = "Alamat dengan kunci '" + alamatKey + "' tidak ditemukan. Record Penduduk diabaikan.\n";
                            errorLog.append(errorMessage);
                            continue;
                        }
                    }
                }

                em.getTransaction().commit();

                if (errorLog.length() > 0) {
                    JOptionPane.showMessageDialog(this,
                            errorLog.toString(),
                            "Kesalahan Data Alamat",
                            JOptionPane.ERROR_MESSAGE);
                }

                if (adaYangMasuk) {
                    JOptionPane.showMessageDialog(this,
                            "Data berhasil diimpor dari CSV!");
                    showTablePenduduk();
                    showTableAlamat();
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Tidak ada data valid yang dapat diimpor!",
                            "Informasi",
                            JOptionPane.WARNING_MESSAGE);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Gagal impor: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void imporCsvKeDatabaseAlamat() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Pilih File CSV");

        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String fileName = file.getName();

            // Pastikan file berekstensi .csv
            if (!fileName.toLowerCase().endsWith(".csv")) {
                JOptionPane.showMessageDialog(this,
                        "File yang dipilih bukan file CSV!\nSilakan pilih file dengan ekstensi .csv",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                br.readLine(); // Lewati header (jika ada)

                em.getTransaction().begin();

                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length == 5) {
                        Alamat a = new Alamat();
                        a.setIdAlamat(data[0].trim());
                        a.setKelurahan(data[1].trim());
                        a.setKecamatan(data[2].trim());
                        a.setKabupaten(data[3].trim());
                        a.setProvinsi(data[4].trim());

                        em.persist(a);
                    }
                }
                em.getTransaction().commit();
                JOptionPane.showMessageDialog(this, "Data Alamat berhasil diimpor dari CSV!");
                showTableAlamat(); // refresh tabel Alamat

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Gagal impor: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel4.setBackground(new java.awt.Color(255, 153, 153));
        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 153), 10));

        jPanel19.setBackground(new java.awt.Color(255, 255, 255));
        jPanel19.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 3, 40)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 102, 102));
        jLabel9.setText("DATA PENDUDUK");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(233, 233, 233))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton9.setBackground(new java.awt.Color(51, 255, 51));
        jButton9.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton9.setForeground(new java.awt.Color(0, 0, 0));
        jButton9.setText("Simpan");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 255, 0));
        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setText("Perbarui");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(204, 0, 51));
        jButton4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Hapus Data");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(255, 255, 255));
        jButton3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 0, 0));
        jButton3.setText("Cetak");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(204, 204, 255));
        jButton5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(0, 0, 0));
        jButton5.setText("Upload");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jButton6.setBackground(new java.awt.Color(204, 255, 204));
        jButton6.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton6.setForeground(new java.awt.Color(0, 0, 0));
        jButton6.setText("Download");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("tab1", jPanel1);

        jPanel20.setBackground(new java.awt.Color(255, 153, 153));
        jPanel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 153), 10));

        jPanel21.setBackground(new java.awt.Color(255, 255, 255));
        jPanel21.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 3, 40)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 102, 102));
        jLabel10.setText("DATA ALAMAT");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(265, 265, 265))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jButton10.setBackground(new java.awt.Color(51, 255, 51));
        jButton10.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton10.setForeground(new java.awt.Color(0, 0, 0));
        jButton10.setText("Simpan");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setBackground(new java.awt.Color(255, 255, 0));
        jButton11.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton11.setForeground(new java.awt.Color(0, 0, 0));
        jButton11.setText("Perbarui");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(204, 0, 51));
        jButton12.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText("Hapus Data");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setBackground(new java.awt.Color(255, 255, 255));
        jButton13.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton13.setForeground(new java.awt.Color(0, 0, 0));
        jButton13.setText("Cetak");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setBackground(new java.awt.Color(204, 204, 255));
        jButton14.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton14.setForeground(new java.awt.Color(0, 0, 0));
        jButton14.setText("Upload");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(204, 255, 204));
        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("Download");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(42, 42, 42)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton11))
                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton14)
                        .addComponent(jButton13)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("tab2", jPanel20);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane2)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        InsertPenduduk dialog = new InsertPenduduk(this, true);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

        showTablePenduduk();
        showTableAlamat();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        int row = jTable1.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data dulu di tabel!");
            return;
        }
        nipLama = jTable1.getValueAt(row, 0).toString();
        namaLama = jTable1.getValueAt(row, 1).toString();
        kelaminLama = jTable1.getValueAt(row, 2).toString();
        pekerjaanLama = jTable1.getValueAt(row, 3).toString();
        alamatLama = jTable1.getValueAt(row, 4).toString();

        UpdatePenduduk dialog = new UpdatePenduduk(this, true, nipLama, namaLama, kelaminLama, pekerjaanLama, alamatLama); // true = modal
        dialog.setLocationRelativeTo(this); // supaya muncul di tengah
        dialog.setVisible(true);

        showTablePenduduk();
        showTableAlamat();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        int row = jTable1.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data dulu di tabel!");
            return;
        }
        nipLama = jTable1.getValueAt(row, 0).toString();
        namaLama = jTable1.getValueAt(row, 1).toString();
        kelaminLama = jTable1.getValueAt(row, 2).toString();
        pekerjaanLama = jTable1.getValueAt(row, 3).toString();
        alamatLama = jTable1.getValueAt(row, 4).toString();

        DeletePenduduk dialog = new DeletePenduduk(this, true, nipLama, namaLama, kelaminLama, pekerjaanLama, alamatLama);
        dialog.setLocationRelativeTo(this); // supaya muncul di tengah
        dialog.setVisible(true);

        showTablePenduduk();
        showTableAlamat();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            String path = "src/tugasp144/ReportPendudukk.jasper";
            HashMap<String, Object> parameters = new HashMap<>();

            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PBONew", "postgres", "Renjun23032000.");

            JasperPrint jprint = JasperFillManager.fillReport(path, parameters, conn);
            JasperViewer jviewer = new JasperViewer(jprint, false);
            jviewer.setSize(800, 600);
            jviewer.setLocationRelativeTo(this);
            jviewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            jviewer.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        imporCsvKeDatabasePenduduk();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        InsertAlamat dialog = new InsertAlamat(this, true);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

        showTablePenduduk();
        showTableAlamat();         // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        int row = jTable2.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data dulu di tabel!");
            return;
        }
        idAlamatLama = jTable2.getValueAt(row, 0).toString();
        kelurahanLama = jTable2.getValueAt(row, 1).toString();
        kecamatanLama = jTable2.getValueAt(row, 2).toString();
        kabupatenLama = jTable2.getValueAt(row, 3).toString();
        provinsiLama = jTable2.getValueAt(row, 4).toString();

        UpdateAlamat dialog = new UpdateAlamat(this, true, idAlamatLama, kelurahanLama, kecamatanLama, kabupatenLama, provinsiLama); // true = modal
        dialog.setLocationRelativeTo(this); // supaya muncul di tengah
        dialog.setVisible(true);

        showTablePenduduk();
        showTableAlamat();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        int row = jTable2.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data dulu di tabel!");
            return;
        }
        idAlamatLama = jTable2.getValueAt(row, 0).toString();
        kelurahanLama = jTable2.getValueAt(row, 1).toString();
        kecamatanLama = jTable2.getValueAt(row, 2).toString();
        kabupatenLama = jTable2.getValueAt(row, 3).toString();
        provinsiLama = jTable2.getValueAt(row, 4).toString();

        DeleteAlamat dialog = new DeleteAlamat(this, true, idAlamatLama, kelurahanLama, kecamatanLama, kabupatenLama, provinsiLama); // true = modal
        dialog.setLocationRelativeTo(this); // supaya muncul di tengah
        dialog.setVisible(true);

        showTablePenduduk();
        showTableAlamat();
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        try {
            String path = "src/tugasp144/ReportAlamat.jasper";
            HashMap<String, Object> parameters = new HashMap<>();

            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/PBONew", "postgres", "Renjun23032000.");

            JasperPrint jprint = JasperFillManager.fillReport(path, parameters, conn);
            JasperViewer jviewer = new JasperViewer(jprint, false);
            jviewer.setSize(800, 600);
            jviewer.setLocationRelativeTo(this);
            jviewer.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            jviewer.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        imporCsvKeDatabaseAlamat();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            if (jTable2.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Tidak ada data untuk diekspor!");
                return;
            }

            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Simpan Data Mengajar");

            int result = chooser.showSaveDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {

                String path = chooser.getSelectedFile().getAbsolutePath();

                if (!path.toLowerCase().endsWith(".csv")) {
                    path += ".csv";
                }

                exportToCSV(jTable2, path);

                JOptionPane.showMessageDialog(this, "Data berhasil diekspor ke:\n" + path);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal ekspor: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            if (jTable1.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "Tidak ada data untuk diekspor!");
                return;
            }

            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Simpan Data Mengajar");

            int result = chooser.showSaveDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {

                String path = chooser.getSelectedFile().getAbsolutePath();

                if (!path.toLowerCase().endsWith(".csv")) {
                    path += ".csv";
                }

                exportToCSV(jTable1, path);

                JOptionPane.showMessageDialog(this, "Data berhasil diekspor ke:\n" + path);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal ekspor: " + e.getMessage());
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DataPendudukk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DataPendudukk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DataPendudukk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataPendudukk.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DataPendudukk().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables

}
