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
import java.util.List;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.persistence.EntityManager;
import modelo.Producto;
import modelo.RegistroEntrada;
import modelo.Usuario;

/**
 * FXML Controller class
 *
 * @author Kevin
 */
public class FXMLVerEntradaController implements Initializable {

    @FXML
    private Button btnEditar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnSalir;
    @FXML
    private TableView<RegistroEntrada> tbDatos;
    @FXML
    private TableColumn<RegistroEntrada, Long> columnaCodigo;
    @FXML
    private TableColumn<RegistroEntrada, String> columnaProducto;
    @FXML
    private TableColumn<RegistroEntrada, String> columnaCantidad;
    @FXML
    private TableColumn<RegistroEntrada, String> columnaFechaEntrada;
    @FXML
    private TableColumn<RegistroEntrada, String> columnaFechaVencimiento;
    @FXML
    private TableColumn<RegistroEntrada, String> columnaCosto;
    @FXML
    private TableColumn<RegistroEntrada, String> columnaGuia;
    @FXML
    private TableColumn<RegistroEntrada, String> columnaUsuario;
    @FXML
    private TableColumn<RegistroEntrada, String> columnaCaducar;
    @FXML
    private TableColumn<RegistroEntrada, String> columnaObservaciones;
    
    private EntityManager em;
    
    private List<RegistroEntrada> registros;
    
    private Boolean existe;

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

        this.tbDatos.getItems().addAll(ControlMantenimiento.listarRegistroEntrada(em));
        this.columnaCaducar.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RegistroEntrada, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<RegistroEntrada, String> param) {
                String salida = "";
                if (param.getValue().isPorCaducar()== false) {
                    salida = "No";
                } else {
                    salida = "Si";
                }
                return new ReadOnlyObjectWrapper<>(salida);
            }
        });
        this.columnaCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        this.columnaCosto.setCellValueFactory((TableColumn.CellDataFeatures<RegistroEntrada, String> param) -> {
            String salida = "";
            salida = String.valueOf(param.getValue().getCostoTotal());
            return new ReadOnlyObjectWrapper<>(salida);
        });       
        this.columnaObservaciones.setCellValueFactory(new PropertyValueFactory<>("observaciones"));
        this.columnaFechaEntrada.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RegistroEntrada, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<RegistroEntrada, String> param) {
                String salida = "";
                SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
                if (param.getValue().getFechaEntrada()!= null) {
                    salida = formato.format(param.getValue().getFechaEntrada().getTime());
                } else {
                    salida = "";
                }
                return new ReadOnlyObjectWrapper<>(salida);
            }
        });
        this.columnaFechaVencimiento.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RegistroEntrada, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<RegistroEntrada, String> param) {
                String salida = "";
                SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                if (param.getValue().getFechaVencimiento()!= null) {
                    salida = formato.format(param.getValue().getFechaVencimiento().getTime());
                } else {
                    salida = "";
                }
                return new ReadOnlyObjectWrapper<>(salida);
            }
        });
        this.columnaGuia.setCellValueFactory(new PropertyValueFactory<>("codigoGuiaRemision"));
        this.columnaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigoEntrada"));
        this.columnaProducto.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RegistroEntrada, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<RegistroEntrada, String> param) {
                 String salida = "";
                if (param.getValue().getRegistraProducto()!= null) {
                    salida = param.getValue().getRegistraProducto().getNombreProducto();
                }else{
                    salida = "Producto eliminado";
                }
                
                return new ReadOnlyObjectWrapper<>(salida);
            }
        });
        this.columnaUsuario.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RegistroEntrada, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<RegistroEntrada, String> param) {
                String salida = param.getValue().getRealizaUsuario().getNombreUsuario();
                return new ReadOnlyObjectWrapper<>(salida);
            }
        });
    }    

    @FXML
    private void accionEditar(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(this.getClass()
                    .getResource("/vista/FXMLRegistrarEntrada.fxml"));
            Parent root = loader.load();
            FXMLRegistrarEntradaController controlador = loader.getController();
            controlador.setEm(em);
            controlador.setRegistroEntrada(this.tbDatos.getSelectionModel().getSelectedItem());
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            this.mostrarDatos();
        } catch (Exception e) {
           System.err.println("Caught IOException: " + e.getMessage());
        }
    }

    @FXML
    private void accionEliminar(ActionEvent event) {
        Dialog dialog = new Alert(Alert.AlertType.CONFIRMATION);
        dialog.setContentText("Desea eliminar el registro seleccionado?");
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            ControlMantenimiento.eliminarRegistroEntrada(em,
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
        this.tbDatos.getItems().addAll(ControlMantenimiento.listarRegistroEntrada(em));
    }
    
}
