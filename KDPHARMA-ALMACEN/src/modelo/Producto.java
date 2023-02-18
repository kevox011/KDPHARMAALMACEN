/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
@Table(name = "producto")
@NamedQueries({
    @NamedQuery(name = "Producto.listar", query = "select p from Producto p"),
    @NamedQuery(name = "Producto.buscarNombre", query = "select p from Producto p where p.nombreProducto like :nombreProducto"),
    @NamedQuery(name = "Producto.buscarCodigo", query = "select p from Producto p where p.codigoProducto like :codigoProducto")
    })
public class Producto implements Serializable{
    
    @Id
    @Column(name = "codigoProducto")
    private Long codigoProducto;
    @Column(name = "nombreProducto", length = 20)
    private String nombreProducto;
    @Column(name = "precioUnitario")
    private BigDecimal precioUnitario;
    @Column(name = "stock")
    private int stock;
    @Column(name = "observaciones", length = 50)
    private String observaciones;
    @Column(name = "agotandose")
    private Boolean agotandose;
    @OneToMany(mappedBy = "registraProducto")
    private List<RegistroEntrada> esRegistradoEntrada;
    @OneToMany(mappedBy = "registraProducto")
    private List<RegistroSalida> esRegistradoSalida;

    public long getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(long codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public boolean isAgotandose() {
        return agotandose;
    }

    public void setAgotandose(Boolean agotandose) {
        this.agotandose = agotandose;
    }

    public List<RegistroEntrada> getEsRegistradoEntrada() {
        if (this.esRegistradoEntrada == null) {
            this.esRegistradoEntrada = new ArrayList<>();
        }
        return esRegistradoEntrada;
    }

    public void setEsRegistradoEntrada(List<RegistroEntrada> esRegistradoEntrada) {
        this.esRegistradoEntrada = esRegistradoEntrada;
    }

    public List<RegistroSalida> getEsRegistradoSalida() {
        if (this.esRegistradoSalida == null) {
            this.esRegistradoSalida = new ArrayList<>();
        }
        return esRegistradoSalida;
    }

    public void setEsRegistradoSalida(List<RegistroSalida> esRegistradoSalida) {
        this.esRegistradoSalida = esRegistradoSalida;
    }  
    
}
