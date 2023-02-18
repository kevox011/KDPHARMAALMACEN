/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package control.vista;

import control.ControlAplicacion;
import control.ControlMantenimiento;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
public class FXMLRegistrarUsuarioController extends ControlVista implements Initializable {

    @FXML
    private TextField txtNombreUsuario;
    @FXML
    private TextField txtNombres;
    @FXML
    private TextField txtApellidoPaterno;
    @FXML
    private RadioButton rbSupervisor;
    @FXML
    private RadioButton rbPersonal;
    @FXML
    private TextField txtApellidoMaterno;
    @FXML
    private PasswordField pswContraseña;
    @FXML
    private PasswordField pswContraseñaConfirmar;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;
    
    private EntityManager em;

    private Usuario usuario;

    private List<Usuario> usuarios;

    @FXML
    private Label lblTitulo = new Label();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.em = ControlAplicacion.getEm();
        this.btnGuardar.disableProperty().bind(this.txtNombreUsuario.textProperty().isEmpty().
                or(this.pswContraseña.textProperty().isEmpty()).
                or(this.pswContraseñaConfirmar.textProperty().isEmpty()).
                or(this.pswContraseña.textProperty().length().lessThan(8)).
                or(this.txtNombres.textProperty().isEmpty()).
                or(this.txtApellidoPaterno.textProperty().isEmpty()).
                or(this.txtApellidoMaterno.textProperty().isEmpty()));

        usuarios = new ArrayList<>();
    }    

    @FXML
    private void aacionGuardar(ActionEvent event) {
        usuarios.clear();
        if (this.usuario == null) {
            Query consulta = em.createNamedQuery("Usuario.buscar");
            consulta.setParameter("nombreUsuario", "%" + this.txtNombreUsuario.getText().replace(" ", "%") + "%");
            usuarios.addAll(consulta.getResultList());
            if (!usuarios.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El nombre de usuario ya existe vuelva a ingresar los datos", "Advertencia", 2);
                limpiar();

            } else {
                Usuario nuevoUsuario = new Usuario();
                nuevoUsuario.setNombreUsuario(this.txtNombreUsuario.getText());
                nuevoUsuario.setContraseña(this.pswContraseña.getText());
                if (this.rbSupervisor.isSelected()) {
                    nuevoUsuario.setTipoDeUsuario(Boolean.TRUE);
                } else if (this.rbPersonal.isSelected()) {
                    nuevoUsuario.setTipoDeUsuario(Boolean.FALSE);
                }
                nuevoUsuario.setNombres(this.txtNombres.getText());
                nuevoUsuario.setApellidoPaterno(this.txtApellidoPaterno.getText());
                nuevoUsuario.setApellidoMaterno(this.txtApellidoMaterno.getText());
                ControlMantenimiento.registrarUsuario(em, nuevoUsuario);
                this.accionCancelar(event);
            }
        } else {
            this.usuario.setNombreUsuario(this.txtNombreUsuario.getText());
            this.usuario.setContraseña(this.pswContraseña.getText());
            if (this.rbSupervisor.isSelected()) {
                this.usuario.setTipoDeUsuario(Boolean.TRUE);
            } else if (this.rbPersonal.isSelected()) {
                this.usuario.setTipoDeUsuario(Boolean.FALSE);
                }
            this.usuario.setNombres(this.txtNombres.getText());
            this.usuario.setApellidoPaterno(this.txtApellidoPaterno.getText());
            this.usuario.setApellidoMaterno(this.txtApellidoMaterno.getText());
            ControlMantenimiento.editarUsuario(em, usuario);
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
        }
    }

    @FXML
    private void accionCancelar(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(this.getClass().
                    getResource("/vista/FXMLMenuPrincipal.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("SISTEMA DE ALMACEN KDPHARMA - LAT PHARMA");
            Image image = new Image("imagenes/icono2.png");
            stage.getIcons().add(image);
            stage.show();
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
        } catch (Exception e) {
        }
    }
    
    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
        this.lblTitulo.setText("Edicion de usuario");
        this.txtNombreUsuario.setText(this.usuario.getNombreUsuario());
        this.pswContraseña.setText(this.usuario.getContraseña());
        this.pswContraseñaConfirmar.setText(this.usuario.getContraseña());
        if (this.usuario.getTipoDeUsuario() == true) {
            this.rbSupervisor.isSelected();
        } else if (this.usuario.getTipoDeUsuario() == false) {
            this.rbPersonal.isSelected();
        }
        this.txtNombres.setText(this.usuario.getNombres());
        this.txtApellidoPaterno.setText(this.usuario.getApellidoPaterno());
        this.txtApellidoMaterno.setText(this.usuario.getApellidoMaterno());
    }

    private void limpiar() {
        txtNombreUsuario.clear();
        pswContraseña.clear();
        pswContraseñaConfirmar.clear();
        txtNombres.clear();
        txtApellidoPaterno.clear();
        txtApellidoMaterno.clear();
    }
    
}
