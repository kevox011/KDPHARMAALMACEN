/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelo.Usuario;
/**
 *
 * @author Kevin
 */
public class ControlAplicacion {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("KDPHARMA-ALMACENPU");
    private static EntityManager em;
    private static Usuario usuario;

    public ControlAplicacion() {
    }

    public static EntityManager getEm() {
        em = emf.createEntityManager();
        return em;
    }

    public static Usuario getUsuario() {
        return usuario;
    }

    public static void setUsuario(Usuario usuario) {
        ControlAplicacion.usuario = usuario;
    }
    
}
