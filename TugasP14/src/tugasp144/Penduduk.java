/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tugasp144;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author ANANDA AYU KARTIKA S
 */
@Entity
@Table(name = "penduduk")
@NamedQueries({
    @NamedQuery(name = "Penduduk.findAll", query = "SELECT p FROM Penduduk p"),
    @NamedQuery(name = "Penduduk.findByNip", query = "SELECT p FROM Penduduk p WHERE p.nip = :nip"),
    @NamedQuery(name = "Penduduk.findByNama", query = "SELECT p FROM Penduduk p WHERE p.nama = :nama"),
    @NamedQuery(name = "Penduduk.findByKelamin", query = "SELECT p FROM Penduduk p WHERE p.kelamin = :kelamin"),
    @NamedQuery(name = "Penduduk.findByPekerjaan", query = "SELECT p FROM Penduduk p WHERE p.pekerjaan = :pekerjaan")})
public class Penduduk implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nip")
    private String nip;
    @Column(name = "nama")
    private String nama;
    @Column(name = "kelamin")
    private String kelamin;
    @Column(name = "pekerjaan")
    private String pekerjaan;
    @JoinColumn(name = "alamat_id", referencedColumnName = "id_alamat")
    @ManyToOne
    private Alamat alamatId;

    public Penduduk() {
    }

    public Penduduk(String nip) {
        this.nip = nip;
    }

    public String getNip() {
        return nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKelamin() {
        return kelamin;
    }

    public void setKelamin(String kelamin) {
        this.kelamin = kelamin;
    }

    public String getPekerjaan() {
        return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
        this.pekerjaan = pekerjaan;
    }

    public Alamat getAlamatId() {
        return alamatId;
    }

    public void setAlamatId(Alamat alamatId) {
        this.alamatId = alamatId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nip != null ? nip.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Penduduk)) {
            return false;
        }
        Penduduk other = (Penduduk) object;
        if ((this.nip == null && other.nip != null) || (this.nip != null && !this.nip.equals(other.nip))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "tugasp12.Penduduk[ nip=" + nip + " ]";
    }
    
}
