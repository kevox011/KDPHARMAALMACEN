package modelo;

import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.RegistroEntrada;
import modelo.RegistroSalida;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-01-30T02:17:06", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, String> apellidoPaterno;
    public static volatile SingularAttribute<Usuario, Long> codigoUsuario;
    public static volatile ListAttribute<Usuario, RegistroSalida> tieneRegistroSalida;
    public static volatile SingularAttribute<Usuario, Boolean> tipoDeUsuario;
    public static volatile SingularAttribute<Usuario, String> nombreUsuario;
    public static volatile ListAttribute<Usuario, RegistroEntrada> tieneRegistroEntrada;
    public static volatile SingularAttribute<Usuario, String> contraseña;
    public static volatile SingularAttribute<Usuario, String> nombres;
    public static volatile SingularAttribute<Usuario, String> apellidoMaterno;

}