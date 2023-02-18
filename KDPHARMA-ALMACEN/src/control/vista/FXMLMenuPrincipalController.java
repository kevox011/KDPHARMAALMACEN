/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package control.vista;

import control.ControlAplicacion;
import control.ControlProducto;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import modelo.Usuario;

/**
 * FXML Controller class
 *
 * @author Kevin
 */
public class FXMLMenuPrincipalController extends ControlVista implements Initializable {

    /**
     * Initializes the controller class.
     */
    private Usuario usuario;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ControlProducto cp = new ControlProducto();
        cp.controlarStock();
        cp.controlarFecha();
           
        cp.alertas();
        
    }    
    

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
