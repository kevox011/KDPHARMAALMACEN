/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package control.vista;

import control.ControlAplicacion;
import control.ControlMantenimiento;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.persistence.EntityManager;
import modelo.Usuario;

/**
 * FXML Controller class
 *
 * @author Kevin
 */
public class FXMLVerUsuarioController extends ControlVista implements Initializable {

    @FXML
    private Button btnEditar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnSalir;
    @FXML
    private TableView<Usuario> tbDatos;
    @FXML
    private TableColumn<Usuario, Long> columnaCodigo;
    @FXML
    private TableColumn<Usuario, String> columnaUsuario;
    @FXML
    private TableColumn<Usuario, String> columnaNombres;
    @FXML
    private TableColumn<Usuario, String> columnaPaterno;
    @FXML
    private TableColumn<Usuario, String> columnaMaterno;
    @FXML
    private TableColumn<Usuario, String> columnaTipo;
    
    private EntityManager em;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.em = ControlAplicacion.getEm();

        this.btnEditar.disableProperty().bind(
                this.tbDatos.getSelectionModel().selectedItemProperty().isNull());
        this.btnEliminar.disableProperty().bind(
                this.tbDatos.getSelectionModel().selectedItemProperty().isNull());

        this.tbDatos.getItems().addAll(ControlMantenimiento.listarUsuario(em));
        this.columnaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigoUsuario"));
        this.columnaUsuario.setCellValueFactory(new PropertyValueFactory<>("nombreUsuario"));
        this.columnaTipo.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Usuario, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Usuario, String> param) {
                String salida = "";
                if (param.getValue().getTipoDeUsuario() == true) {
                    salida = "Supervisora";
                }else{
                    salida = "Personal";
                }
                return new ReadOnlyObjectWrapper<>(salida);
            }
        });
        this.columnaNombres.setCellValueFactory(new PropertyValueFactory<>("nombres"));
        this.columnaPaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoPaterno"));
        this.columnaMaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoMaterno"));

    }    

    @FXML
    private void accionEditar(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(this.getClass()
                    .getResource("/vista/FXMLRegistrarUsuario.fxml"));
            Parent root = loader.load();
            FXMLRegistrarUsuarioController controlador = loader.getController();
            controlador.setUsuario(this.tbDatos.getSelectionModel().getSelectedItem());
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("SISTEMA DE ALMACEN KDPHARMA - LAT PHARMA");
            Image image = new Image("imagenes/icono2.png");
            stage.getIcons().add(image);
            stage.showAndWait();
            this.mostrarDatos();
        } catch (Exception e) {
        }
    }

    @FXML
    private void accionEliminar(ActionEvent event) {
        Dialog dialog = new Alert(Alert.AlertType.CONFIRMATION);
        dialog.setContentText("Desea eliminar el registro seleccionado?");
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            ControlMantenimiento.eliminarUsuario(em,
                    this.tbDatos.getSelectionModel().getSelectedItem());
        }
        this.mostrarDatos();
    }

    @FXML
    private void accionSalir(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(this.getClass().
                    getResource("/vista/FXMLMenuPrincipal.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
        } catch (Exception e) {
        }
    }
    
    private void mostrarDatos() {
        this.tbDatos.getItems().clear();
        this.tbDatos.getItems().addAll(ControlMantenimiento.listarUsuario(em));
    }
    
}
