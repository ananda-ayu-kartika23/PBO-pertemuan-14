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
public class DeleteAlamat extends javax.swing.JDialog {

    private EntityManagerFactory emf;
    private EntityManager em;

    String idAlamatLama, kelurahanLama, kecamatanLama, kabupatenLama, provinsiLama;

    public void connect() {
        try {

            emf = Persistence.createEntityManagerFactory("TugasP14PU");
            em = emf.createEntityManager();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Koneksi gagal: " + e.getMessage());
        }

    }

    public DeleteAlamat(Frame parent, boolean modal,
            String idAlamatLama, String kelurahanLama,
            String kecamatanLama, String kabupatenLama,
            String provinsiLama) {
        super(parent, modal);
        initComponents();
        connect();

        this.idAlamatLama = idAlamatLama;
        this.kelurahanLama = kelurahanLama;
        this.kecamatanLama = kecamatanLama;
        this.kabupatenLama = kabupatenLama;
        this.provinsiLama = provinsiLama;

        StringBuilder sb = new StringBuilder();
        sb.append("ID Alamat: ").append(idAlamatLama).append("<br>")
                .append("Kelurahan: ").append(kelurahanLama).append("<br>")
                .append("Kecamatan: ").append(kecamatanLama).append("<br>")
                .append("Kabupaten: ").append(kabupatenLama).append("<br>")
                .append("Provinsi: ").append(provinsiLama);

        // tampilkan data yang mau dihapus di label
        jLabel1.setText("<html>" + sb.toString().replaceAll("\n", "<br>") + "</html>");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 153, 153));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Apakah yakin menghapus data?");

        jButton1.setBackground(new java.awt.Color(255, 204, 204));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setText("Yes");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 204, 204));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setText("No");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Apakah yakin menghapus data?");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 122, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     try {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // Cek apakah alamat masih digunakan oleh tabel Penduduk
//        TypedQuery<Long> cekRelasi = em.createQuery(
//                "SELECT COUNT(p) FROM Penduduk p WHERE p.alamatId.idAlamat = :idAlamat", Long.class);
//        cekRelasi.setParameter("idAlamat", idAlamatLama);
//
//        Long jumlah = cekRelasi.getSingleResult();
//
//        if (jumlah > 0) {
//            JOptionPane.showMessageDialog(this,
//                    "Data alamat dengan ID " + idAlamatLama
//                    + " tidak dapat dihapus karena masih digunakan pada tabel Penduduk.",
//                    "Peringatan", JOptionPane.WARNING_MESSAGE);
//            return;
//        }

        // Hapus data alamat yang sesuai
        TypedQuery<Alamat> query = em.createQuery(
                "SELECT a FROM Alamat a WHERE a.idAlamat = :idAlamat "
                + "AND a.kelurahan = :kelurahan "
                + "AND a.kecamatan = :kecamatan "
                + "AND a.kabupaten = :kabupaten "
                + "AND a.provinsi = :provinsi",
                Alamat.class);

        query.setParameter("idAlamat", idAlamatLama);
        query.setParameter("kelurahan", kelurahanLama);
        query.setParameter("kecamatan", kecamatanLama);
        query.setParameter("kabupaten", kabupatenLama);
        query.setParameter("provinsi", provinsiLama);

        List<Alamat> hasil = query.getResultList();

        if (!hasil.isEmpty()) {
            Alamat a = hasil.get(0);
            em.remove(a);
            JOptionPane.showMessageDialog(this, "Data alamat " + idAlamatLama + " berhasil dihapus!");
        } else {
            JOptionPane.showMessageDialog(this, "Data alamat tidak ditemukan.");
        }

        em.getTransaction().commit();
        em.close();
        dispose();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error saat menghapus: " + e.getMessage());
    }
    }//GEN-LAST:event_jButton1ActionPerformed
        
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
