/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package control;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.*;

/**
 *
 * @author Kevin
 */
public class KDPHARMAALMACEN extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/vista/FXMLIngreso.fxml"));
        
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("SISTEMA DE ALMACEN KDPHARMA - LAT PHARMA");
        Image image = new Image("imagenes/icono2.png");
        stage.getIcons().add(image);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ControlAplicacion.getEm();
        launch(args);
    }
    
}
