/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import modelo.Producto;
import modelo.RegistroEntrada;
import modelo.RegistroSalida;
import modelo.Usuario;
/**
 *
 * @author Kevin
 */
public class ControlMantenimiento {

public static List<Usuario> listarUsuario(EntityManager em) {
        Query consulta = em.createNamedQuery("Usuario.listar");
        return consulta.getResultList();
    }

    public static void registrarUsuario(EntityManager em, Usuario usuario) {
        em.getTransaction().begin();
        em.persist(usuario);
        em.getTransaction().commit();
    }

    public static void editarUsuario(EntityManager em, Usuario usuario) {
        em.getTransaction().begin();
        Usuario usuarioBD = em.find(Usuario.class, usuario.getCodigoUsuario());
        usuarioBD.setNombreUsuario(usuario.getNombreUsuario());
        usuarioBD.setContraseña(usuario.getContraseña());
        usuarioBD.setNombres(usuario.getNombres());
        usuarioBD.setApellidoPaterno(usuario.getApellidoPaterno());
        usuarioBD.setApellidoMaterno(usuario.getApellidoMaterno());
        usuarioBD.setTipoDeUsuario(usuario.getTipoDeUsuario());
        em.getTransaction().commit();
    }
    
    public static void eliminarUsuario(EntityManager em, Usuario usuario) {
        em.getTransaction().begin();
        Usuario usuarioDB = em.find(Usuario.class, usuario.getCodigoUsuario());
        em.remove(usuarioDB);
        em.getTransaction().commit();
    }

    public static List<Producto> listarProducto(EntityManager em) {
        Query consulta = em.createNamedQuery("Producto.listar");
        return consulta.getResultList();
    }

    public static void registrarProducto(EntityManager em, Producto producto) {
        em.getTransaction().begin();
        em.persist(producto);
        em.getTransaction().commit();
    }

    public static void editarProducto(EntityManager em, Producto producto) {
        em.getTransaction().begin();
        Producto productoBD = em.find(Producto.class, producto.getCodigoProducto());
        productoBD.setNombreProducto(producto.getNombreProducto());
        productoBD.setPrecioUnitario(producto.getPrecioUnitario());
        productoBD.setObservaciones(producto.getObservaciones());
        productoBD.setStock(producto.getStock());
        productoBD.setAgotandose(producto.isAgotandose());
        em.getTransaction().commit();
    }
    
    public static void eliminarProducto(EntityManager em, Producto producto) {
        em.getTransaction().begin();
        Producto productoDB = em.find(Producto.class, producto.getCodigoProducto());
        em.remove(productoDB);
        em.getTransaction().commit();
    }    
    
    public static List<RegistroEntrada> listarRegistroEntrada(EntityManager em) {
        Query consulta = em.createNamedQuery("RegistroEntrada.listar");
        return consulta.getResultList();
    }

    public static void registrarRegistroEntrada(EntityManager em, RegistroEntrada registroEntrada, Producto producto, Usuario usuario) {
        em.getTransaction().begin();
        em.persist(registroEntrada);
        registroEntrada.setRealizaUsuario(usuario);
        registroEntrada.setRegistraProducto(producto);
        usuario.getTieneRegistroEntrada().add(registroEntrada);
        producto.getEsRegistradoEntrada().add(registroEntrada);
        Producto productoBD = em.find(Producto.class, producto.getCodigoProducto());
        productoBD.setStock(producto.getStock() + registroEntrada.getCantidad());
        if (productoBD.getStock() > 5) {
            productoBD.setAgotandose(false);
        }
        em.getTransaction().commit();
    }

    public static void editarRegistroEntrada(EntityManager em, RegistroEntrada registroEntrada) {
        em.getTransaction().begin();
        RegistroEntrada registroEntradaBD = em.find(RegistroEntrada.class, registroEntrada.getCodigoEntrada());
        registroEntradaBD.setCantidad(registroEntrada.getCantidad());
        registroEntradaBD.setFechaEntrada(registroEntrada.getFechaEntrada());
        registroEntradaBD.setFechaVencimiento(registroEntrada.getFechaVencimiento());
        registroEntradaBD.setCostoTotal(registroEntrada.getCostoTotal());
        registroEntradaBD.setCodigoGuiaRemision(registroEntrada.getCodigoGuiaRemision());
        registroEntradaBD.setObservaciones(registroEntrada.getObservaciones());
        registroEntradaBD.setPorCaducar(registroEntrada.isPorCaducar());

        registroEntradaBD.setRealizaUsuario(registroEntrada.getRealizaUsuario());
        registroEntradaBD.setRegistraProducto(registroEntrada.getRegistraProducto());
        em.getTransaction().commit();
    }

    public static void eliminarRegistroEntrada(EntityManager em, RegistroEntrada registroEntrada) {
        em.getTransaction().begin();
        RegistroEntrada registroEntradaDB = em.find(RegistroEntrada.class, registroEntrada.getCodigoEntrada());
        Producto productoDB = em.find(Producto.class, registroEntrada.getRegistraProducto().getCodigoProducto());
        productoDB.setStock(productoDB.getStock()-registroEntrada.getCantidad());
        em.remove(registroEntradaDB);
        em.getTransaction().commit();
    }
    
    public static List<RegistroSalida> listarRegistroSalida(EntityManager em) {
        Query consulta = em.createNamedQuery("RegistroSalida.listar");
        return consulta.getResultList();
    }

    public static void registrarRegistroSalida(EntityManager em, RegistroSalida registroSalida, Producto producto, Usuario usuario) {
        em.getTransaction().begin();
        em.persist(registroSalida);
        registroSalida.setRealizaUsuario(usuario);
        registroSalida.setRegistraProducto(producto);
        usuario.getTieneRegistroSalida().add(registroSalida);
        producto.getEsRegistradoSalida().add(registroSalida);
        Producto productoBD = em.find(Producto.class, producto.getCodigoProducto());
        productoBD.setStock(producto.getStock() - registroSalida.getCantidad());
        if (productoBD.getStock() < 5) {
            productoBD.setAgotandose(true);
        }
        em.getTransaction().commit();
    }

    public static void editarRegistroSalida(EntityManager em, RegistroSalida registroSalida) {
        em.getTransaction().begin();
        RegistroSalida registroSalidaBD = em.find(RegistroSalida.class, registroSalida.getCodigoSalida());
        registroSalidaBD.setCantidad(registroSalida.getCantidad());
        registroSalidaBD.setFechaSalida(registroSalida.getFechaSalida());
        registroSalidaBD.setCostoTotal(registroSalida.getCostoTotal());
        registroSalidaBD.setCliente(registroSalida.getCliente());
        registroSalidaBD.settipoDocumentoCliente(registroSalida.istipoDocumentoCliente());
        registroSalidaBD.setDocumenteoCliente(registroSalida.getDocumenteoCliente());
        registroSalidaBD.setObservaciones(registroSalida.getObservaciones());

        registroSalidaBD.setRealizaUsuario(registroSalida.getRealizaUsuario());
        registroSalidaBD.setRegistraProducto(registroSalida.getRegistraProducto());

        em.getTransaction().commit();
    }

    public static void eliminarRegistroSalida(EntityManager em, RegistroSalida registroSalida) {
        em.getTransaction().begin();
        RegistroSalida registroSalidaDB = em.find(RegistroSalida.class, registroSalida.getCodigoSalida());
        Producto productoDB = em.find(Producto.class, registroSalida.getRegistraProducto().getCodigoProducto());
        productoDB.setStock(productoDB.getStock()+registroSalida.getCantidad());
        em.remove(registroSalidaDB);
        em.getTransaction().commit();
    }
}
