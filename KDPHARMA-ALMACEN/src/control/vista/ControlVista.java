/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package control.vista;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.Usuario;

/**
 *
 * @author Kevin
 */
public class ControlVista {
    
    @FXML
    protected MenuBar barraMenu;
    
    private Usuario usuario;

    public ControlVista() {
    }

    private void cambiarVentana(String url) {
        try {
            Stage stage = (Stage) this.barraMenu.getScene().getWindow();
            Parent root = FXMLLoader.load(this.getClass().
                    getResource(url));
            VBox vbox = (VBox) root;
            vbox.getChildren().add(0, this.barraMenu);
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("SISTEMA DE ALMACEN KDPHARMA - LAT PHARMA");
            Image image = new Image("imagenes/icono2.png");
            stage.getIcons().add(image);
            stage.show();
        } catch (Exception e) {
        }
    }

    @FXML
    private void accionCrearUsuario(ActionEvent event) {
        this.cambiarVentana("/vista/FXMLRegistrarUsuario.fxml");
    }

    @FXML
    private void accionVerUsuario(ActionEvent event) {
        this.cambiarVentana("/vista/FXMLVerUsuario.fxml");
    }

    @FXML
    private void accionSalir(ActionEvent event) {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

    @FXML
    private void accionCrearProducto(ActionEvent event) {
        this.cambiarVentana("/vista/FXMLRegistrarProducto.fxml");
    }

    @FXML
    private void accionVerProducto(ActionEvent event) {
        this.cambiarVentana("/vista/FXMLVerProducto.fxml");
    }

    @FXML
    private void accionCrearRegistroEntrada(ActionEvent event) {
        this.cambiarVentana("/vista/FXMLRegistrarEntrada.fxml");
    }

    @FXML
    private void accionCrearRegistroSalida(ActionEvent event) {
        this.cambiarVentana("/vista/FXMLRegistrarSalida.fxml");
    }

    @FXML
    private void accionVerRegistroEntrada(ActionEvent event) {
        this.cambiarVentana("/vista/FXMLVerEntrada.fxml");
    }

    @FXML
    private void accionVerRegistroSalida(ActionEvent event) {
        this.cambiarVentana("/vista/FXMLVerSalida.fxml");
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
