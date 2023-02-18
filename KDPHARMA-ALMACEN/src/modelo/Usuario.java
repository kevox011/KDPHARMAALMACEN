/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Kevin
 */

@Entity
@Table(name = "usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.listar", query = "select u from Usuario u"),
    @NamedQuery(name = "Usuario.buscar", query = "select u from Usuario u where u.nombreUsuario like :nombreUsuario")

})

public class Usuario implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "codigoUsuario")
    private long codigoUsuario;
    @Column(name = "nombreUsuario", length = 10, unique = true)
    private String nombreUsuario;
    @Column(name = "contraseña", length = 15)
    private String contraseña;
    @Column(name = "nombres", length = 20)
    private String nombres;
    @Column(name = "apellidoPaterno", length = 10)
    private String apellidoPaterno;
    @Column(name = "apellidoMaterno", length = 10)
    private String apellidoMaterno;
    @Column(name = "tipoDeUsuario")
    private Boolean tipoDeUsuario;
    @OneToMany(mappedBy = "realizaUsuario")
    private List<RegistroEntrada> tieneRegistroEntrada;
    @OneToMany(mappedBy = "realizaUsuario")
    private List<RegistroSalida> tieneRegistroSalida;

    public long getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(long codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Boolean getTipoDeUsuario() {
        return tipoDeUsuario;
    }

    public void setTipoDeUsuario(Boolean tipoDeUsuario) {
        this.tipoDeUsuario = tipoDeUsuario;
    }

    public List<RegistroEntrada> getTieneRegistroEntrada() {
        if (this.tieneRegistroEntrada == null) {
            this.tieneRegistroEntrada = new ArrayList<>();
        }
        return tieneRegistroEntrada;
    }

    public void setTieneRegistroEntrada(List<RegistroEntrada> tieneRegistroEntrada) {
        this.tieneRegistroEntrada = tieneRegistroEntrada;
    }

    public List<RegistroSalida> getTieneRegistroSalida() {
        if (this.tieneRegistroSalida == null) {
            this.tieneRegistroSalida = new ArrayList<>();
        }
        return tieneRegistroSalida;
    }

    public void setTieneRegistroSalida(List<RegistroSalida> tieneRegistroSalida) {
        this.tieneRegistroSalida = tieneRegistroSalida;
    }
    
    
}
