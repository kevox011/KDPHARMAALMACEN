/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Kevin
 */
@Entity
@Table(name = "registroSalida")
@NamedQueries({
    @NamedQuery(name = "RegistroSalida.listar", query = "select rs from RegistroSalida rs"),
    @NamedQuery(name = "RegistroSalida.buscarCodigo", query = "select rs from RegistroSalida rs where rs.registraProducto.codigoProducto like :codigoDeProducto")
})

public class RegistroSalida implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigoSalida")
    private long codigoSalida;
    @Column(name = "cantidad")
    private int cantidad;
    @Temporal(TemporalType.DATE)
    @Column(name = "fechaSalida")
    private Calendar fechaSalida;
    @Column(name = "costoTotal")
    private BigDecimal costoTotal;
    @Column(name = "cliente", length = 50)
    private String cliente;
    @Column(name = "tipoDocumentoCliente")
    private Boolean tipoDocumentoCliente;
    @Column(name = "documenteoCliente", length = 50)
    private String documenteoCliente;
    @Column(name = "observaciones", length = 50)
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "realizaUsuario", referencedColumnName = "codigoUsuario")
    private Usuario realizaUsuario;
    @ManyToOne
    @JoinColumn(name = "registraProducto", referencedColumnName = "codigoProducto")
    private Producto registraProducto;

    public long getCodigoSalida() {
        return codigoSalida;
    }

    public void setCodigoSalida(long codigoSalida) {
        this.codigoSalida = codigoSalida;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Calendar getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Calendar fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public BigDecimal getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(BigDecimal costoTotal) {
        this.costoTotal = costoTotal;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public boolean istipoDocumentoCliente() {
        return tipoDocumentoCliente;
    }

    public void settipoDocumentoCliente(Boolean tipoDocumentoCliente) {
        this.tipoDocumentoCliente = tipoDocumentoCliente;
    }

    public String getDocumenteoCliente() {
        return documenteoCliente;
    }

    public void setDocumenteoCliente(String documenteoCliente) {
        this.documenteoCliente = documenteoCliente;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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
