/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package control.vista;

import control.ControlAplicacion;
import control.ControlMantenimiento;
import java.math.BigDecimal;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.Property;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import modelo.Producto;
import modelo.RegistroEntrada;
import modelo.RegistroSalida;
import modelo.Usuario;

/**
 * FXML Controller class
 *
 * @author Kevin
 */
public class FXMLRegistrarSalidaController extends ControlVista implements Initializable {

    @FXML
    private ComboBox<Producto> cbProductos;
    @FXML
    private DatePicker dpSalida;
    @FXML
    private TextArea taObservaciones;
    @FXML
    private TextField txtCantidad;
    @FXML
    private TextField txtCliente;
    @FXML
    private TextField txtDocumento;
    @FXML
    private RadioButton rbPersona;
    @FXML
    private RadioButton rbEmpresa;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;
    
    @FXML
    private Label lbTitulo = new Label();

    private EntityManager em;

    private List<RegistroSalida> registrosSalida;

    private RegistroSalida registroDeSalida;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.em = ControlAplicacion.getEm();
        this.cbProductos.getItems().addAll(ControlMantenimiento.listarProducto(em));
        this.cbProductos.setConverter(new StringConverter<Producto>() {

            @Override
            public String toString(Producto object) {
                return object.getNombreProducto();
            }

            @Override
            public Producto fromString(String string) {
                return null;
            }
        });
        
        this.em = ControlAplicacion.getEm();
        this.btnGuardar.disableProperty().bind(this.cbProductos.getSelectionModel().selectedItemProperty().isNull().
                or(this.txtCantidad.textProperty().isEmpty()).
                or(this.dpSalida.getEditor().textProperty().isEmpty()).
                or(this.txtCliente.textProperty().isEmpty()).
                or(this.txtDocumento.textProperty().isEmpty()));
        registrosSalida = new ArrayList<>();
    }    

    @FXML
    private void aacionGuardar(ActionEvent event) {
        if (this.registroDeSalida == null) {
            Calendar calobj = Calendar.getInstance();
            RegistroSalida nuevaSalida = new RegistroSalida();
            nuevaSalida.setRegistraProducto(this.cbProductos.getSelectionModel().getSelectedItem());
            nuevaSalida.setCantidad(Integer.parseInt(txtCantidad.getText()));
            LocalDate fecha = this.dpSalida.getValue();
            Calendar fecahC = new GregorianCalendar();
            fecahC.setTime(Date.from(fecha.atStartOfDay().
                    atZone(ZoneId.systemDefault()).toInstant()));
            nuevaSalida.setFechaSalida(fecahC);
            BigDecimal igv = BigDecimal.valueOf(1.18);
            nuevaSalida.setCostoTotal((this.cbProductos.getSelectionModel().getSelectedItem().getPrecioUnitario().multiply(
                    BigDecimal.valueOf(Double.parseDouble(this.txtCantidad.getText())))).multiply(igv));
            nuevaSalida.setCliente(this.txtCliente.getText());
            nuevaSalida.setObservaciones(this.taObservaciones.getText());
            nuevaSalida.setRealizaUsuario(ControlAplicacion.getUsuario());  
            nuevaSalida.setDocumenteoCliente(this.txtDocumento.getText());
            if (this.rbEmpresa.isSelected()) {
                    nuevaSalida.settipoDocumentoCliente(true);
                } else if (this.rbPersona.isSelected()) {
                    nuevaSalida.settipoDocumentoCliente(false);
                }
            ControlMantenimiento.registrarRegistroSalida(em, nuevaSalida, this.cbProductos.getSelectionModel().getSelectedItem() ,ControlAplicacion.getUsuario());
                this.accionCancelar(event);   
        } else {
            this.registroDeSalida.setRegistraProducto(this.cbProductos.getSelectionModel().getSelectedItem());
            this.registroDeSalida.setCantidad(Integer.parseInt(txtCantidad.getText()));
            LocalDate fecha = this.dpSalida.getValue();
            Calendar fecahC = new GregorianCalendar();
            fecahC.setTime(Date.from(fecha.atStartOfDay().
                    atZone(ZoneId.systemDefault()).toInstant()));
            this.registroDeSalida.setFechaSalida(fecahC);
            BigDecimal igv = BigDecimal.valueOf(1.18);
            this.registroDeSalida.setCostoTotal((this.cbProductos.getSelectionModel().getSelectedItem().getPrecioUnitario().multiply(
                    BigDecimal.valueOf(Double.parseDouble(this.txtCantidad.getText())))).multiply(igv));
            this.registroDeSalida.setCliente(this.txtCliente.getText());
            this.registroDeSalida.setObservaciones(this.taObservaciones.getText());
            this.registroDeSalida.setRealizaUsuario(ControlAplicacion.getUsuario());  
            this.registroDeSalida.setDocumenteoCliente(this.txtDocumento.getText());
            if (this.rbEmpresa.isSelected()) {
                    this.registroDeSalida.settipoDocumentoCliente(true);
                } else if (this.rbPersona.isSelected()) {
                    this.registroDeSalida.settipoDocumentoCliente(false);
                }
            ControlMantenimiento.editarRegistroSalida(em, registroDeSalida);
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

    public RegistroSalida getRegistroDeEntrada() {
        return registroDeSalida;
    }

    public void setRegistroSalida(RegistroSalida registroDeSalidaa) {
        this.lbTitulo.setText("Editar Registro de Entrada");
        this.registroDeSalida = registroDeSalidaa;
        this.txtCantidad.setText(String.valueOf(registroDeSalidaa.getCantidad()));
        this.txtCliente.setText(registroDeSalidaa.getCliente());
        this.taObservaciones.setText(registroDeSalidaa.getObservaciones());
        LocalDateTime dateTime = LocalDateTime.ofInstant(registroDeSalidaa.getFechaSalida().toInstant(),
                ZoneId.systemDefault());
        LocalDate fecha = dateTime.toLocalDate();
        this.dpSalida.setValue(fecha);
        this.txtDocumento.setText(registroDeSalidaa.getDocumenteoCliente());
        for (int i = 0; i < this.cbProductos.getItems().size(); i++) {
            if (this.cbProductos.getItems().get(i).getCodigoProducto()== registroDeSalidaa.getRegistraProducto().getCodigoProducto()) {
                this.cbProductos.getSelectionModel().select(i);
            }
        }
        if (this.registroDeSalida.istipoDocumentoCliente()== true) {
            this.rbEmpresa.isSelected();
        } else if (this.registroDeSalida.istipoDocumentoCliente()== false) {
            this.rbPersona.isSelected();
        }
    }

    private void limpiar() {
        txtCantidad.clear();
        txtCliente.clear();
        cbProductos.getSelectionModel().clearSelection();
        txtDocumento.clear();
        dpSalida.getEditor().clear();
        taObservaciones.clear();
    }
    
    
}
