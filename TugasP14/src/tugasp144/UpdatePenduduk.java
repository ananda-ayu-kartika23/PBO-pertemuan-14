/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package tugasp144;

/**
 *
 * @author ANANDA AYU KARTIKA S
 */
import java.awt.Frame;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

/**
 *
 * @author ANANDA AYU KARTIKA S
 */
public class UpdatePenduduk extends javax.swing.JDialog {

    private EntityManagerFactory emf;
    private EntityManager em;

    String nipLama, namaLama, kelaminLama, pekerjaanLama, alamatLama;

    public void connect() {
        try {
            emf = Persistence.createEntityManagerFactory("TugasP14PU");
            em = emf.createEntityManager();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Koneksi gagal: " + e.getMessage());
        }

    }

    public UpdatePenduduk(java.awt.Frame parent, boolean modal, String nipLama, String namaLama, String kelaminLama, String pekerjaanLama, String alamatLama) {
        super(parent, modal);
        initComponents();
        connect();
        isiComboBoxAlamat();

        this.nipLama = nipLama;
        this.namaLama = namaLama;
        this.kelaminLama = kelaminLama;
        this.pekerjaanLama = pekerjaanLama;
        this.alamatLama = alamatLama;

        txtNIP.setText(nipLama);
        txtNIP.setEnabled(false);
        txtNama.setText(namaLama);
        CmbKelamin.setSelectedItem(kelaminLama);
        txtPekerjaan.setText(pekerjaanLama);
        for (int i = 0; i < CmbAlamat.getItemCount(); i++) {
            Alamat a = CmbAlamat.getItemAt(i); 
            if (a.getIdAlamat().equals(alamatLama)) {
                CmbAlamat.setSelectedIndex(i);
                break;
            }
        }
    }

    private void isiComboBoxAlamat() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Alamat> query = em.createQuery("SELECT a FROM Alamat a", Alamat.class);

            List<Alamat> hasil = query.getResultList();

            if (!hasil.isEmpty()) {
                for (Alamat a : hasil) {
                    CmbAlamat.addItem(a);
                }

            }

        } finally {
            em.close();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNIP = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        txtPekerjaan = new javax.swing.JTextField();
        CmbKelamin = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        CmbAlamat = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));
        jPanel1.setForeground(new java.awt.Color(255, 153, 153));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nama");

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("NIP");

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Kelamin");

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Alamat");

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Pekerjaan");

        txtNIP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNIPActionPerformed(evt);
            }
        });

        CmbKelamin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "P", "L" }));
        CmbKelamin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CmbKelaminActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 204, 204));
        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setText("Perbarui");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        CmbAlamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CmbAlamatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtNIP)
                            .addComponent(txtNama)
                            .addComponent(txtPekerjaan)
                            .addComponent(CmbKelamin, 0, 205, Short.MAX_VALUE)
                            .addComponent(CmbAlamat, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(CmbKelamin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(CmbAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtPekerjaan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jButton2)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNIPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNIPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNIPActionPerformed

    private void CmbKelaminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CmbKelaminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CmbKelaminActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (txtNIP.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Isi Semua Data!");
        } else if (txtNama.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Isi Semua Data!");
        } else if (CmbKelamin.getSelectedItem().equals("")) {
            JOptionPane.showMessageDialog(this, "Isi Semua Data!");
        } else if (txtPekerjaan.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Isi Semua Data!");
        } else if (CmbAlamat.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Isi Semua Data!");
        } else {
            try {

                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();

                TypedQuery<Penduduk> query = (TypedQuery<Penduduk>) em.createQuery(
                        "SELECT p FROM Penduduk p WHERE p.nip = :nip AND p.nama = :nama "
                        + "AND p.kelamin = :kelamin AND p.pekerjaan = :pekerjaan AND p.alamatId.idAlamat = :idAlamat", Penduduk.class);

                query.setParameter("nip", nipLama);
                query.setParameter("nama", namaLama);
                query.setParameter("kelamin", kelaminLama);
                query.setParameter("pekerjaan", pekerjaanLama);
                query.setParameter("idAlamat", alamatLama);

                List<Penduduk> hasil = query.getResultList();

                if (!hasil.isEmpty()) {
                    Penduduk ps = hasil.get(0);

                    ps.setNip(txtNIP.getText());
                    ps.setNama(txtNama.getText());
                    ps.setKelamin(CmbKelamin.getSelectedItem().toString());
                    ps.setPekerjaan(txtPekerjaan.getText());

                    Alamat a = (Alamat) CmbAlamat.getSelectedItem();
                    ps.setAlamatId(a);

                    em.persist(ps);
                    em.getTransaction().commit();

                    JOptionPane.showMessageDialog(this, "Data berhasil diperbarui!");
                    dispose();

                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        }             // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void CmbAlamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CmbAlamatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CmbAlamatActionPerformed

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Alamat> CmbAlamat;
    private javax.swing.JComboBox<String> CmbKelamin;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtNIP;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtPekerjaan;
    // End of variables declaration//GEN-END:variables

}
