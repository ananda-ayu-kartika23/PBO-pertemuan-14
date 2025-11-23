package tugasp144;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tugasp144.Penduduk;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-11-20T15:46:28", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Alamat.class)
public class Alamat_ { 

    public static volatile SingularAttribute<Alamat, String> idAlamat;
    public static volatile SingularAttribute<Alamat, String> provinsi;
    public static volatile SingularAttribute<Alamat, String> kecamatan;
    public static volatile CollectionAttribute<Alamat, Penduduk> pendudukCollection;
    public static volatile SingularAttribute<Alamat, String> kabupaten;
    public static volatile SingularAttribute<Alamat, String> kelurahan;

}