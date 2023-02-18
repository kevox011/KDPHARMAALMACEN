package modelo;

import java.math.BigDecimal;
import java.util.Calendar;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.Producto;
import modelo.Usuario;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-01-30T02:17:06", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(RegistroEntrada.class)
public class RegistroEntrada_ { 

    public static volatile SingularAttribute<RegistroEntrada, Usuario> realizaUsuario;
    public static volatile SingularAttribute<RegistroEntrada, String> codigoGuiaRemision;
    public static volatile SingularAttribute<RegistroEntrada, Boolean> porCaducar;
    public static volatile SingularAttribute<RegistroEntrada, Long> codigoEntrada;
    public static volatile SingularAttribute<RegistroEntrada, Producto> registraProducto;
    public static volatile SingularAttribute<RegistroEntrada, Calendar> fechaVencimiento;
    public static volatile SingularAttribute<RegistroEntrada, BigDecimal> costoTotal;
    public static volatile SingularAttribute<RegistroEntrada, String> observaciones;
    public static volatile SingularAttribute<RegistroEntrada, Calendar> fechaEntrada;
    public static volatile SingularAttribute<RegistroEntrada, Integer> cantidad;

}