package com.gestion.controller.lote;

import com.gestion.model.Lote;
import com.gestion.model.MotivosMovimientoInventario;
import com.gestion.model.Producto;
import com.gestion.repository.ProductoRepositorio;
import com.gestion.service.LoteServicio;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoteFormularioControlador {

    private Lote lote = new Lote();

    private ProductoRepositorio productoRepositorio = new ProductoRepositorio();

    private LoteServicio loteServicio = new LoteServicio();

    private ObservableList<Producto> productos =
        FXCollections.observableArrayList()
    ;

    @FXML private TextField txtLote;

    @FXML private ComboBox<String> txtProducto;

    @FXML private DatePicker txtFechaExpedicion;

    @FXML private DatePicker txtFechaVencimiento;

    @FXML private DatePicker txtFechaRegistro;

    @FXML private TextField txtVolumen;

    @FXML private TextField txtValor;

    @FXML private ChoiceBox<String> seleccionMotivo;

    @FXML private Button botonAceptar;

    @FXML private Button botonCancelar;



    private Integer productoCadenaId(String cadena) {
        return Integer.parseInt(
            cadena.split("-")[0].trim()
        );
    }

    private String productoACadena(Producto prod){
        return prod.conseguirId() + " - "
            + prod.conseguirCodigoBarras() + " - "
            + prod.conseguirNombre() + " - "
            + prod.conseguirMarca();
    }

    private ObservableList<String> productosACadena() {
        ObservableList<String> cadenas = FXCollections.observableArrayList();
        for(Producto prod: productos){
            cadenas.add(productoACadena(prod));
        }
        return cadenas;
    }

        private void cerrarVentana() {
        Stage ventana = (Stage) botonCancelar.getScene().getWindow();
        ventana.close();
    }

    @FXML
    public void initialize() {
        productos = productoRepositorio.consultarTodo();
        txtProducto.setItems(productosACadena());
        seleccionMotivo.setItems(
            MotivosMovimientoInventario.MOTIVOS_ORIGINALES
        );
        txtFechaRegistro.setValue(
            java.time.LocalDate.now()
        );
    }

    @FXML
    private void cancelar() {
        cerrarVentana();
    }

    @FXML
    private void aceptar() {
        lote.colocarLote(txtLote.getText().trim());
//      validarProducto(){
        Producto p = new Producto();
        p.colocarId(productoCadenaId(txtProducto.getValue()));
//      }
        lote.colocarProducto(p);
        lote.colocarFechaRegistro(
            txtFechaRegistro.getValue() != null
                ? txtFechaRegistro.getValue().toString()
                : null
        );
        lote.colocarFechaExpedicion(
            txtFechaExpedicion.getValue() != null
                ? txtFechaExpedicion.getValue().toString()
                : null
        );
        lote.colocarFechaVencimiento(
            txtFechaVencimiento.getValue() != null
                ? txtFechaVencimiento.getValue().toString()
                : null
        );
        lote.colocarCantidadMovimiento(
            Double.valueOf(txtVolumen.getText())
        );
        lote.colocarValorMovimiento(
            txtValor.getText().isBlank()
                ? null
                : Double.valueOf(txtValor.getText())
        );
        lote.colocarMotivo(seleccionMotivo.getValue());

        loteServicio.agregarLote(lote);
        cerrarVentana();
    }
}
