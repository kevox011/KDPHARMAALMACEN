package modelo;

import java.math.BigDecimal;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import modelo.RegistroEntrada;
import modelo.RegistroSalida;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2023-01-30T02:17:06", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Producto.class)
public class Producto_ { 

    public static volatile SingularAttribute<Producto, Boolean> agotandose;
    public static volatile ListAttribute<Producto, RegistroSalida> esRegistradoSalida;
    public static volatile SingularAttribute<Producto, BigDecimal> precioUnitario;
    public static volatile SingularAttribute<Producto, String> observaciones;
    public static volatile SingularAttribute<Producto, Long> codigoProducto;
    public static volatile SingularAttribute<Producto, Integer> stock;
    public static volatile ListAttribute<Producto, RegistroEntrada> esRegistradoEntrada;
    public static volatile SingularAttribute<Producto, String> nombreProducto;

}