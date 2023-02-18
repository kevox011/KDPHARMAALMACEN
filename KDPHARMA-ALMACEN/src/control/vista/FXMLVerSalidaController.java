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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.persistence.EntityManager;
import modelo.RegistroSalida;


/**
 * FXML Controller class
 *
 * @author Kevin
 */
public class FXMLVerSalidaController implements Initializable {

    @FXML
    private Button btnEditar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnSalir;
    @FXML
    private TableView<RegistroSalida> tbDatos;
    @FXML
    private TableColumn<RegistroSalida, Long> columnaCodigo;
    @FXML
    private TableColumn<RegistroSalida, String> columnaProducto;
    @FXML
    private TableColumn<RegistroSalida, Integer> columnaCantidad;
    @FXML
    private TableColumn<RegistroSalida, String> columnaFechaSalida;
    @FXML
    private TableColumn<RegistroSalida, String> columnaCosto;
    @FXML
    private TableColumn<RegistroSalida, String> columnaCliente;
    @FXML
    private TableColumn<RegistroSalida, String> columnaTipo;
    @FXML
    private TableColumn<RegistroSalida, String> columnaDocumento;
    @FXML
    private TableColumn<RegistroSalida, String> columnaUsuario;
    @FXML
    private TableColumn<RegistroSalida, String> columnaObservaciones;
    
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

        this.tbDatos.getItems().addAll(ControlMantenimiento.listarRegistroSalida(em));

        this.columnaCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        this.columnaCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        this.columnaDocumento.setCellValueFactory(new PropertyValueFactory<>("documenteoCliente"));
        this.columnaObservaciones.setCellValueFactory(new PropertyValueFactory<>("observaciones"));
        this.columnaFechaSalida.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RegistroSalida, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<RegistroSalida, String> param) {
                String salida = "";
                SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                if (param.getValue().getFechaSalida()!= null) {
                    salida = formato.format(param.getValue().getFechaSalida().getTime());
                } else {
                    salida = "";
                }
                return new ReadOnlyObjectWrapper<>(salida);
            }
        });
        this.columnaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigoSalida"));
        this.columnaProducto.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RegistroSalida, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<RegistroSalida, String> param) {
                String salida = "";
                if (param.getValue().getRegistraProducto()!= null) {
                    salida = param.getValue().getRegistraProducto().getNombreProducto();
                }else{
                    salida = "Producto eliminado";
                }
                
                return new ReadOnlyObjectWrapper<>(salida);
            }
        });
        this.columnaTipo.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RegistroSalida, String>, ObservableValue<String>>() {
        @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<RegistroSalida, String> param) {
                String salida = "";
                if (param.getValue().istipoDocumentoCliente()== false) {
                    salida = "Persona";
                } else {
                    salida = "Empresa";
                }
                return new ReadOnlyObjectWrapper<>(salida);
            }
        });
        this.columnaUsuario.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<RegistroSalida, String>, ObservableValue<String>>() {

            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<RegistroSalida, String> param) {
                String salida = param.getValue().getRealizaUsuario().getNombreUsuario();
                return new ReadOnlyObjectWrapper<>(salida);
            }
        });
        this.columnaCosto.setCellValueFactory((TableColumn.CellDataFeatures<RegistroSalida, String> param) -> {
            String salida = "";
            salida = String.valueOf(param.getValue().getCostoTotal());
            return new ReadOnlyObjectWrapper<>(salida);
        }); 
    }    

    @FXML
    private void accionEditar(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(this.getClass()
                    .getResource("/vista/FXMLRegistrarSalida.fxml"));
            Parent root = loader.load();
            FXMLRegistrarSalidaController controlador = loader.getController();
            controlador.setEm(em);
            controlador.setRegistroSalida(this.tbDatos.getSelectionModel().getSelectedItem());
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
            ControlMantenimiento.eliminarRegistroSalida(em,
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
       this.tbDatos.getItems().addAll(ControlMantenimiento.listarRegistroSalida(em));
    }
    
}
