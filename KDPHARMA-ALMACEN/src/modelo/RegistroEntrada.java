/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 *
 * @author Kevin
 */
@Entity
@Table(name = "registroEntrada")
@NamedQueries({
    @NamedQuery(name = "RegistroEntrada.listar", query = "select re from RegistroEntrada re"),
    @NamedQuery(name = "RegistroEntrada.buscarCodigo", query = "select re from RegistroEntrada re where re.registraProducto.codigoProducto like :codigoDeProducto")
})
public class RegistroEntrada implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigoEntrada")
    private long codigoEntrada;
    @Column(name = "cantidad")
    private int cantidad;
    @Temporal(TemporalType.DATE)
    @Column(name = "fechaEntrada")
    private Calendar fechaEntrada;
    @Temporal(TemporalType.DATE)
    @Column(name = "fechaVencimiento")
    private Calendar fechaVencimiento;
    @Column(name = "costoTotal")
    private BigDecimal costoTotal;
    @Column(name = "codigoGuiaRemision", length = 20)
    private String codigoGuiaRemision;
    @Column(name = "observaciones", length = 50)
    private String observaciones;
    @Column(name = "porCaducar")
    private Boolean porCaducar;
    @ManyToOne
    @JoinColumn(name = "realizaUsuario", referencedColumnName = "codigoUsuario")
    private Usuario realizaUsuario;
    @ManyToOne
    @JoinColumn(name = "registraProducto", referencedColumnName = "codigoProducto")
    private Producto registraProducto;

    public long getCodigoEntrada() {
        return codigoEntrada;
    }

    public void setCodigoEntrada(long codigoEntrada) {
        this.codigoEntrada = codigoEntrada;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Calendar getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Calendar fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Calendar getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Calendar fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public BigDecimal getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(BigDecimal costoTotal) {
        this.costoTotal = costoTotal;
    }

    public String getCodigoGuiaRemision() {
        return codigoGuiaRemision;
    }

    public void setCodigoGuiaRemision(String codigoGuiaRemision) {
        this.codigoGuiaRemision = codigoGuiaRemision;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public boolean isPorCaducar() {
        return porCaducar;
    }

    public void setPorCaducar(Boolean porCaducar) {
        this.porCaducar = porCaducar;
    }   

    public Usuario getRealizaUsuario() {
        return realizaUsuario;
    }

    public void setRealizaUsuario(Usuario realizaUsuario) {
        this.realizaUsuario = realizaUsuario;
    }

    public Producto getRegistraProducto() {
        return registraProducto;
    }

    public void setRegistraProducto(Producto registraProducto) {
        this.registraProducto = registraProducto;
    }
    
    
}
