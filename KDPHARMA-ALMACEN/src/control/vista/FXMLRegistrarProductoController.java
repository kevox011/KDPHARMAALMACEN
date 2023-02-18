/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package control.vista;

import control.ControlAplicacion;
import control.ControlMantenimiento;
import java.math.BigDecimal;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.print.DocFlavor;
import javax.swing.JOptionPane;
import modelo.Producto;

/**
 * FXML Controller class
 *
 * @author Kevin
 */
public class FXMLRegistrarProductoController extends ControlVista implements Initializable {

    @FXML
    private TextField txtCodigoProducto;
    @FXML
    private TextField txtNombreProducto;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TextArea atObservaciones;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Label lbTitulo = new Label();

    private EntityManager em;

    private Producto producto;

    private String s;

    private List<Producto> productos;
    
    private List<Producto> productos2;
    
    private Boolean existe;
    
    private Long codigo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.em = ControlAplicacion.getEm();
        this.btnGuardar.disableProperty().bind(this.txtCodigoProducto.textProperty().isEmpty().
                or(this.txtNombreProducto.textProperty().isEmpty()).
                or(this.txtPrecio.textProperty().isEmpty()));
    }    

    @FXML
    private void aacionGuardar(ActionEvent event) {

        if (this.producto == null) {
            this.productos2 = new ArrayList<>();

            this.productos2.addAll(ControlMantenimiento.listarProducto(em));
            this.codigo = Long.parseLong(this.txtCodigoProducto.getText());
            if (this.productos2.isEmpty()) {
                existe = false;
            }else{
                for (int i = 0; i < this.productos2.size(); i++) {
                if (this.codigo == this.productos2.get(i).getCodigoProducto()) {
                     existe = true;
                }else{
                     existe = false;
                }
              }    
            }
            if (existe == true) {
                JOptionPane.showMessageDialog(null, "El codigo del producto ya existe vuelva a ingresar los datos", "Advertencia", 2);
                limpiar();

            } else {
                Producto nuevoProducto = new Producto();
                nuevoProducto.setAgotandose(true);
                nuevoProducto.setCodigoProducto(Integer.parseInt(this.txtCodigoProducto.getText()));
                nuevoProducto.setNombreProducto(this.txtNombreProducto.getText());
                nuevoProducto.setPrecioUnitario(BigDecimal.valueOf(Double.parseDouble(this.txtPrecio.getText())));
                nuevoProducto.setStock(0);
                nuevoProducto.setObservaciones(this.atObservaciones.getText());
                ControlMantenimiento.registrarProducto(em, nuevoProducto);
                this.accionCancelar(event);   
                               
            }
        } else {
            this.producto.setCodigoProducto(Integer.parseInt(this.txtCodigoProducto.getText()));
            this.producto.setNombreProducto(this.txtNombreProducto.getText());
            this.producto.setPrecioUnitario(BigDecimal.valueOf(Double.parseDouble(this.txtPrecio.getText())));
            this.producto.setObservaciones(this.atObservaciones.getText());
            ControlMantenimiento.editarProducto(em, producto);
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

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
        this.lbTitulo.setText("Edicion de producto");
        this.txtCodigoProducto.setText(String.valueOf(this.producto.getCodigoProducto()));
        this.txtNombreProducto.setText(this.producto.getNombreProducto());
        this.txtPrecio.setText(this.producto.getPrecioUnitario().toString());
        this.atObservaciones.setText(this.producto.getObservaciones());
    }

    private void limpiar() {
        txtCodigoProducto.clear();
        txtNombreProducto.clear();
        txtPrecio.clear();
        atObservaciones.clear();
        txtPrecio.clear();
    }
    
}
