package tugasp144;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import tugasp144.Alamat;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-11-20T15:46:28", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Penduduk.class)
public class Penduduk_ { 

    public static volatile SingularAttribute<Penduduk, String> kelamin;
    public static volatile SingularAttribute<Penduduk, Alamat> alamatId;
    public static volatile SingularAttribute<Penduduk, String> nip;
    public static volatile SingularAttribute<Penduduk, String> nama;
    public static volatile SingularAttribute<Penduduk, String> pekerjaan;

}