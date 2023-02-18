package modelo;

import java.math.BigDecimal;
import java.util.Calendar;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Producto;
import modelo.Usuario;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-01-30T02:17:06", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(RegistroSalida.class)
public class RegistroSalida_ { 

    public static volatile SingularAttribute<RegistroSalida, Usuario> realizaUsuario;
    public static volatile SingularAttribute<RegistroSalida, String> cliente;
    public static volatile SingularAttribute<RegistroSalida, Producto> registraProducto;
    public static volatile SingularAttribute<RegistroSalida, Boolean> tipoDocumentoCliente;
    public static volatile SingularAttribute<RegistroSalida, String> documenteoCliente;
    public static volatile SingularAttribute<RegistroSalida, Long> codigoSalida;
    public static volatile SingularAttribute<RegistroSalida, Calendar> fechaSalida;
    public static volatile SingularAttribute<RegistroSalida, BigDecimal> costoTotal;
    public static volatile SingularAttribute<RegistroSalida, String> observaciones;
    public static volatile SingularAttribute<RegistroSalida, Integer> cantidad;

}