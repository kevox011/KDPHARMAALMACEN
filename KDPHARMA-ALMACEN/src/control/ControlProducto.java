/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import modelo.Producto;
import modelo.RegistroEntrada;

/**
 *
 * @author Kevin
 */
public class ControlProducto {
    
    private static List<Producto> productos;
    private static List<RegistroEntrada> registroEntrada;

    private EntityManager em = ControlAplicacion.getEm();
    

    public void controlarStock() {
        Query consulta = em.createNamedQuery("Producto.listar");
        this.productos = consulta.getResultList();
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getStock() < 5) {
                productos.get(i).setAgotandose(true);
            }else {
                productos.get(i).setAgotandose(false);
            }
        }
    }

    public void controlarFecha() {
        Query consulta = em.createNamedQuery("RegistroEntrada.listar");
        this.registroEntrada = consulta.getResultList();
        LocalDateTime dateTime;
        LocalDate fecha;
        LocalDate hoy = LocalDate.now();
        for (int i = 0; i < registroEntrada.size(); i++) {
            if (this.registroEntrada.get(i).getFechaVencimiento()!= null) {

                dateTime = LocalDateTime.ofInstant(this.registroEntrada.get(i).getFechaVencimiento().toInstant(),
                        ZoneId.systemDefault());
                fecha = dateTime.toLocalDate();
                if (fecha.getYear() < hoy.getYear() && (fecha.getDayOfYear() - hoy.getDayOfYear()) < 62) {
                    registroEntrada.get(i).setPorCaducar(true);
                } else {
                    registroEntrada.get(i).setPorCaducar(false);
                }
            } else {
                registroEntrada.get(i).setPorCaducar(false);
            }
        }
    }

    public void alertas() {
        Query consulta = em.createNamedQuery("Producto.listar");
        this.productos = consulta.getResultList();
        for (int i = 0; i < productos.size(); i++) {
            if (this.productos.get(i).isAgotandose() == true) {
                JOptionPane.showMessageDialog(null, "El producto" + this.productos.get(i).getNombreProducto() + " con codigo " + this.productos.get(i).getCodigoProducto() + " esta agotandose, se recomienda solicitar un reabastecimineto del producto", "Advertencia", 2);
            }
            if (this.registroEntrada.get(i).isPorCaducar()== true) {
                JOptionPane.showMessageDialog(null, "Los productos registrados por el registro de entrada:" + this.registroEntrada.get(i).getCodigoEntrada()+ " con codigo " + " esta a punto de caducar, se recomienda informar ala supervisora para tomar las medidas necesarias", "Advertencia", 2);
            }
        }
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
    
    public List<RegistroEntrada> getRegistroEntrada() {
        return registroEntrada;
    }

    public void setregistroEntrada(List<RegistroEntrada> registroEntrada) {
        this.registroEntrada = registroEntrada;
    }
    
}
