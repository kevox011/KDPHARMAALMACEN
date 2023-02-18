/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package control.vista;

import control.ControlAplicacion;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import modelo.Usuario;

/**
 * FXML Controller class
 *
 * @author Kevin
 */
public class FXMLIngresoController implements Initializable {

    @FXML
    private TextField txtNombreUsuario;
    @FXML
    private PasswordField pswContraseña;
    @FXML
    private Button btnIngresar;
    @FXML
    private Button btnSalir;
    
    private List<Usuario> usuarios;

    private Usuario usuario;

    private EntityManager em;

    private boolean usuarioEncontrado;

    private int intentos;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.em = ControlAplicacion.getEm();
        this.btnIngresar.disableProperty().bind(
                this.txtNombreUsuario.textProperty().length().lessThan(5).
                or(this.txtNombreUsuario.textProperty().isEmpty()).
                or(this.pswContraseña.textProperty().isEmpty()).
                or(this.pswContraseña.textProperty().length().lessThan(8)));
        usuarioEncontrado = false;
        intentos = 5;
        usuarios = new ArrayList<>();
    }    

    @FXML
    private void aciionIngresar(ActionEvent event) {
        if (intentos < 0) {
            JOptionPane.showMessageDialog(null, "Numero de intentos sobrepasado, espera 5 minutos hasta que se desbloque", "Advertencia", 2);
            limpiar();
            txtNombreUsuario.isDisable();
            pswContraseña.isDisable();
            btnIngresar.isDisable();
            try {
                Thread.sleep(10000 * 5);
            } catch (InterruptedException ex) {
            }
            txtNombreUsuario.isEditable();
            pswContraseña.isEditable();
            btnIngresar.isPressed();
        } else {
            if (this.btnIngresar.isDisable()) {
                JOptionPane.showMessageDialog(null, "Hay campos sin llenar", "Advertencia", 2);
            } else {
                Query consulta = em.createNamedQuery("Usuario.buscar");
                consulta.setParameter("nombreUsuario", "%" + this.txtNombreUsuario.getText().replace(" ", "%") + "%");
                usuarios.addAll(consulta.getResultList());
                for (int i = 0; i < usuarios.size(); i++) {
                    if (this.txtNombreUsuario.getText().equals(usuarios.get(i).getNombreUsuario())) {
                        usuarioEncontrado = true;
                        usuario = usuarios.get(i);
                    }
                }
                if (usuarioEncontrado == true) {
                    if (pswContraseña.getText().equals(usuario.getContraseña())) {
                        JOptionPane.showMessageDialog(null, "Bienvenido al sistema...", "Acceso concedido", 1);
                        try {
                            Stage stage = new Stage();
                            FXMLLoader loader = new FXMLLoader(this.getClass().
                                    getResource("/vista/FXMLMenuPrincipal.fxml"));
                            ControlAplicacion.setUsuario(usuario);
                            Parent root = loader.load();
                            FXMLMenuPrincipalController controlador = loader.getController();
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            controlador.setUsuario(usuario);
                            stage.setTitle("SISTEMA DE ALMACEN KDPHARMA - LAT PHARMA");
                            Image image = new Image("imagenes/icono2.png");
                            stage.getIcons().add(image);
                            stage.show();
                            accionSalir(event);
                        } catch (Exception e) {
                        }
                    } else {
                        intentos--;
                        JOptionPane.showMessageDialog(null, "El nombre de usuario o la contraseña son incorrectos, intente de nuevo", "Advertencia", 2);
                        limpiar();
                    }
                } else {
                    intentos--;
                    JOptionPane.showMessageDialog(null, "El nombre de usuario o la contraseña son incorrectos, intente de nuevo", "Advertencia", 2);
                    limpiar();
                }
            }
        }
    }

    @FXML
    private void accionSalir(ActionEvent event) {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }
    
    private void limpiar() {
        txtNombreUsuario.clear();
        pswContraseña.clear();
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
    
}
