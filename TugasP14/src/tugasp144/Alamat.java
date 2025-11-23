/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tugasp144;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author ANANDA AYU KARTIKA S
 */
@Entity
@Table(name = "alamat")
@NamedQueries({
    @NamedQuery(name = "Alamat.findAll", query = "SELECT a FROM Alamat a"),
    @NamedQuery(name = "Alamat.findByIdAlamat", query = "SELECT a FROM Alamat a WHERE a.idAlamat = :idAlamat"),
    @NamedQuery(name = "Alamat.findByKelurahan", query = "SELECT a FROM Alamat a WHERE a.kelurahan = :kelurahan"),
    @NamedQuery(name = "Alamat.findByKecamatan", query = "SELECT a FROM Alamat a WHERE a.kecamatan = :kecamatan"),
    @NamedQuery(name = "Alamat.findByKabupaten", query = "SELECT a FROM Alamat a WHERE a.kabupaten = :kabupaten"),
    @NamedQuery(name = "Alamat.findByProvinsi", query = "SELECT a FROM Alamat a WHERE a.provinsi = :provinsi")})
public class Alamat implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_alamat")
    private String idAlamat;
    @Column(name = "kelurahan")
    private String kelurahan;
    @Column(name = "kecamatan")
    private String kecamatan;
    @Column(name = "kabupaten")
    private String kabupaten;
    @Column(name = "provinsi")
    private String provinsi;
    @OneToMany(mappedBy = "alamatId", cascade = CascadeType.ALL)
    private Collection<Penduduk> pendudukCollection;

    public Alamat() {
    }

    public Alamat(String idAlamat) {
        this.idAlamat = idAlamat;
    }

    public String getIdAlamat() {
        return idAlamat;
    }

    public void setIdAlamat(String idAlamat) {
        this.idAlamat = idAlamat;
    }

    public String getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKabupaten() {
        return kabupaten;
    }

    public void setKabupaten(String kabupaten) {
        this.kabupaten = kabupaten;
    }

    public String getProvinsi() {
        return provinsi;
    }

    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }

    public Collection<Penduduk> getPendudukCollection() {
        return pendudukCollection;
    }

    public void setPendudukCollection(Collection<Penduduk> pendudukCollection) {
        this.pendudukCollection = pendudukCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAlamat != null ? idAlamat.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alamat)) {
            return false;
        }
        Alamat other = (Alamat) object;
        if ((this.idAlamat == null && other.idAlamat != null) || (this.idAlamat != null && !this.idAlamat.equals(other.idAlamat))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return idAlamat ;
    }
    
}
