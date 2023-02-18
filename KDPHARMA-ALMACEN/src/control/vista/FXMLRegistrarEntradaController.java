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
import modelo.Usuario;

/**
 * FXML Controller class
 *
 * @author Kevin
 */
public class FXMLRegistrarEntradaController implements Initializable {

    @FXML
    private TextField txtGuiaRemision;
    @FXML
    private ComboBox<Producto> cbProductos;
    @FXML
    private DatePicker dpEntrada;
    @FXML
    private DatePicker dpVencimiento;
    @FXML
    private TextArea taObservaciones;
    @FXML
    private TextField txtCantidad;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;
    
    @FXML
    private Label lbTitulo = new Label();

    private EntityManager em;

    private List<RegistroEntrada> registrosEntrada;

    private RegistroEntrada registroDeEntrada;


    /**
     * Initializes the controller class.
     * @param url
     * @param rb
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
                or(this.dpEntrada.getEditor().textProperty().isEmpty()).
                or(this.dpVencimiento.getEditor().textProperty().isEmpty()).
                or(this.txtGuiaRemision.textProperty().isEmpty()));
        registrosEntrada = new ArrayList<>();
    }    

    @FXML
    private void aacionGuardar(ActionEvent event) {
        if (this.registroDeEntrada == null) {
            Calendar calobj = Calendar.getInstance();
            RegistroEntrada nuevaEntrada = new RegistroEntrada();
            nuevaEntrada.setRegistraProducto(this.cbProductos.getSelectionModel().getSelectedItem());
            nuevaEntrada.setPorCaducar(false);
            nuevaEntrada.setCantidad(Integer.parseInt(txtCantidad.getText()));
            LocalDate fecha = this.dpEntrada.getValue();
            Calendar fecahC = new GregorianCalendar();
            fecahC.setTime(Date.from(fecha.atStartOfDay().
                    atZone(ZoneId.systemDefault()).toInstant()));
            nuevaEntrada.setFechaEntrada(fecahC);
            LocalDate fecha2 = this.dpVencimiento.getValue();
            Calendar fecahC2 = new GregorianCalendar();
            fecahC2.setTime(Date.from(fecha2.atStartOfDay().
                    atZone(ZoneId.systemDefault()).toInstant()));
            nuevaEntrada.setFechaVencimiento(fecahC2);
            nuevaEntrada.setCostoTotal(this.cbProductos.getSelectionModel().getSelectedItem().getPrecioUnitario().multiply(
                    BigDecimal.valueOf(Double.parseDouble(this.txtCantidad.getText()))));
            nuevaEntrada.setCodigoGuiaRemision(this.txtGuiaRemision.getText());
            nuevaEntrada.setObservaciones(this.taObservaciones.getText());
            nuevaEntrada.setRealizaUsuario(ControlAplicacion.getUsuario());  
            ControlMantenimiento.registrarRegistroEntrada(em, nuevaEntrada, this.cbProductos.getSelectionModel().getSelectedItem() ,ControlAplicacion.getUsuario());
                this.accionCancelar(event);   
        } else {
            this.registroDeEntrada.setRegistraProducto(this.cbProductos.getSelectionModel().getSelectedItem());
            this.registroDeEntrada.setCantidad(Integer.parseInt(txtCantidad.getText()));
            LocalDate fecha = this.dpEntrada.getValue();
            Calendar fecahC = new GregorianCalendar();
            fecahC.setTime(Date.from(fecha.atStartOfDay().
                    atZone(ZoneId.systemDefault()).toInstant()));
            this.registroDeEntrada.setFechaEntrada(fecahC);
            LocalDate fecha2 = this.dpVencimiento.getValue();
            Calendar fecahC2 = new GregorianCalendar();
            fecahC2.setTime(Date.from(fecha2.atStartOfDay().
                    atZone(ZoneId.systemDefault()).toInstant()));
            this.registroDeEntrada.setFechaVencimiento(fecahC2);
            this.registroDeEntrada.setCostoTotal(this.cbProductos.getSelectionModel().getSelectedItem().getPrecioUnitario().multiply(
                    BigDecimal.valueOf(Double.parseDouble(this.txtCantidad.getText()))));
            this.registroDeEntrada.setCodigoGuiaRemision(this.txtGuiaRemision.getText());
            this.registroDeEntrada.setObservaciones(this.taObservaciones.getText());
            this.registroDeEntrada.setRealizaUsuario(ControlAplicacion.getUsuario());
            ControlMantenimiento.editarRegistroEntrada(em, registroDeEntrada);
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

    public RegistroEntrada getRegistroDeEntrada() {
        return registroDeEntrada;
    }

    public void setRegistroEntrada(RegistroEntrada registroDeEntrada) {
        this.lbTitulo.setText("Editar Registro de Entrada");
        this.registroDeEntrada = registroDeEntrada;
        this.txtCantidad.setText(String.valueOf(registroDeEntrada.getCantidad()));
        this.txtGuiaRemision.setText(registroDeEntrada.getCodigoGuiaRemision());
        this.taObservaciones.setText(registroDeEntrada.getObservaciones());
        LocalDateTime dateTime = LocalDateTime.ofInstant(registroDeEntrada.getFechaEntrada().toInstant(),
                ZoneId.systemDefault());
        LocalDate fecha = dateTime.toLocalDate();
        this.dpEntrada.setValue(fecha);
        LocalDateTime dateTime2 = LocalDateTime.ofInstant(registroDeEntrada.getFechaVencimiento().toInstant(),
                ZoneId.systemDefault());
        LocalDate fecha2 = dateTime2.toLocalDate();
        this.dpVencimiento.setValue(fecha2);
        for (int i = 0; i < this.cbProductos.getItems().size(); i++) {
            if (this.cbProductos.getItems().get(i).getCodigoProducto()== registroDeEntrada.getRegistraProducto().getCodigoProducto()) {
                this.cbProductos.getSelectionModel().select(i);
            }
        }
    }

    private void limpiar() {
        txtCantidad.clear();
        txtGuiaRemision.clear();
        cbProductos.getSelectionModel().clearSelection();
        dpEntrada.getEditor().clear();
        dpVencimiento.getEditor().clear();
        taObservaciones.clear();
    }
    
}
