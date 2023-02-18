/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package control.vista;

import control.ControlAplicacion;
import control.ControlMantenimiento;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import modelo.Producto;

/**
 * FXML Controller class
 *
 * @author Kevin
 */
public class FXMLVerProductoController extends ControlVista implements Initializable {

    @FXML
    private Button btnEditar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnSalir;
    @FXML
    private TableView<Producto> tbDatos;
    @FXML
    private TableColumn<Producto, Long> columnaCodigo;
    @FXML
    private TableColumn<Producto, String> columnaProducto;
    @FXML
    private TableColumn<Producto, String> columnaPrecio;
    @FXML
    private TableColumn<Producto, String> columnaObservaciones;
    @FXML
    private TableColumn<Producto, Integer> columnaStock;
    @FXML
    private TableColumn<Producto, String> columnaAgotado;
    
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

        this.tbDatos.getItems().addAll(ControlMantenimiento.listarProducto(em));
        this.columnaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigoProducto"));
        this.columnaProducto.setCellValueFactory(new PropertyValueFactory<>("nombreProducto"));
        this.columnaPrecio.setCellValueFactory((TableColumn.CellDataFeatures<Producto, String> param) -> {
            String salida = "";
            salida = String.valueOf(param.getValue().getPrecioUnitario());
            return new ReadOnlyObjectWrapper<>(salida);
        });
        this.columnaObservaciones.setCellValueFactory(new PropertyValueFactory<>("observaciones"));
        this.columnaStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        this.columnaAgotado.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Producto, String>, ObservableValue<String>>() {

        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<Producto, String> param) {
            String salida = "";
            if (param.getValue().isAgotandose()== false) {
                salida = "No";
            } else {
                salida = "Si";
            }
            return new ReadOnlyObjectWrapper<>(salida);
        }
        });
    }    

    @FXML
    private void accionEditar(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(this.getClass()
                    .getResource("/vista/FXMLRegistrarProducto.fxml"));
            Parent root = loader.load();
            FXMLRegistrarProductoController controlador = loader.getController();
            controlador.setProducto(this.tbDatos.getSelectionModel().getSelectedItem());
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
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
            ControlMantenimiento.eliminarProducto(em,
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

    private void mostrarDatos() {
        this.tbDatos.getItems().clear();
        this.tbDatos.getItems().addAll(ControlMantenimiento.listarProducto(em));
    }
    
}
