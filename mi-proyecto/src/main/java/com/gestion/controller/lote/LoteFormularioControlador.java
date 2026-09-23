package com.gestion.controller.lote;

import java.util.Optional;

import com.gestion.model.Producto;
import com.gestion.repository.ProductoRepositorio;
import com.gestion.service.UnidadesMedida;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class LoteFormularioControlador {
    private Producto producto = new Producto();
    private ProductoRepositorio productoRepositorio = new ProductoRepositorio();
    private static final ObservableList<String> UNIDADES_ORIGINALES = UnidadesMedida.UNIDADES_ORIGINALES;
    private ObservableList<Producto> unidadesAgrupadas = productoRepositorio.consultarTodo();
    private FilteredList<String> unidadesFiltradas  = new FilteredList<>(UNIDADES_ORIGINALES);
    private FilteredList<String> unidadesFiltradasProdRepo = new FilteredList<>(productosACadena());
    @FXML private TextField txtCodigoBarras;
    @FXML private TextField txtNombreProducto;
    @FXML private TextField txtMarca;
    @FXML private TextField txtCantidadProducto;
    @FXML private RadioButton unidadMedida;
    @FXML private RadioButton unidadAgrupada;
    @FXML private ToggleGroup grupoTipoUnidad;
    @FXML private ComboBox<String> txtUnidadMedida;
    @FXML private ComboBox<String> txtUnidadAgrupada;
    @FXML private TextField txtPrecio;
    @FXML private TextField txtExistencias;
    @FXML private TextField txtMinimoExistencias;
    @FXML private Button botonCancelar;


    // Setters
    public void colocarProducto(Producto producto) {
		this.producto = producto;
	}

    // Metodos
    @FXML
    public void initialize() {
        // Ayuda a no ecritura de ComboBox sin selccion del radioButton
        // Configuración inicial
        configurarTipoUnidad();
        // Detectar cuando cambia el RadioButton
        grupoTipoUnidad.selectedToggleProperty().addListener((obs, anterior, nuevo) -> {
            configurarTipoUnidad();
        });
        // ComboBox unidad de medida
        txtUnidadMedida.setItems(unidadesFiltradas);
        txtUnidadMedida.getEditor().textProperty().addListener((obs, old, nuevo) -> {
            if (nuevo == null || nuevo.isEmpty()) {
                unidadesFiltradas.setPredicate(s -> true);
            } else {
                unidadesFiltradas.setPredicate(s ->
                    s.toLowerCase().contains(nuevo.toLowerCase())
                );
            }
        });
        // ComboBox unidad agrupada
        txtUnidadAgrupada.setItems(unidadesFiltradasProdRepo);
        txtUnidadAgrupada.getEditor().textProperty().addListener((obs, old, nuevo) -> {
            if (nuevo == null || nuevo.isEmpty()) {
                unidadesFiltradasProdRepo.setPredicate(p -> true);
            } else {
                unidadesFiltradasProdRepo.setPredicate(s ->
                    s.toLowerCase().contains(nuevo.toLowerCase())
                );
            }
        });
    }

    public void cargarProductoCampoTexto (Producto producto) {
        colocarProducto(producto);
        txtCodigoBarras.setText(producto.conseguirCodigoBarras());
        txtNombreProducto.setText(producto.conseguirNombre());
        txtMarca.setText(producto.conseguirMarca());
        txtCantidadProducto.setText(String.valueOf(producto.conseguirCantidadProducto()));
        System.out.println(producto.conseguirUnidadAgrupada() + " <-A & M-> " + producto.conseguirUnidadMedida());
        txtUnidadMedida.setValue(producto.conseguirUnidadMedida());
        if (producto.conseguirUnidadAgrupada() == null){
            unidadMedida.setSelected(true);
            unidadAgrupada.setSelected(false);
            txtUnidadAgrupada.setValue("");
        } else {
            unidadMedida.setSelected(false);
            unidadAgrupada.setSelected(true);
            txtUnidadAgrupada.setValue(String.valueOf(producto.conseguirUnidadAgrupada()));
        }
        txtPrecio.setText(String.valueOf(producto.conseguirPrecio()));
        txtExistencias.setText(String.valueOf(producto.conseguirExistencias()));
        txtMinimoExistencias.setText(String.valueOf(producto.conseguirMinimoExistencias()));
    }

    private ObservableList<String> productosACadena() {
        ObservableList<String> cadenas = FXCollections.observableArrayList();
        for(Producto prod: unidadesAgrupadas){
            if (prod.conseguirUnidadAgrupada() == null) {
                cadenas.add(prod.conseguirId() + " - "
                    + prod.conseguirCodigoBarras() + " - "
                    + prod.conseguirNombre() + " - "
                    + prod.conseguirMarca()
                );
            }
        }
        return cadenas;
    }

    private void configurarTipoUnidad() {
        if (unidadMedida.isSelected()) {
            txtUnidadMedida.setDisable(false);
            txtUnidadAgrupada.setDisable(true);
        } else if (unidadAgrupada.isSelected()) {
            txtUnidadMedida.setDisable(true);
            txtUnidadAgrupada.setDisable(false);
        }
    }

    @FXML
    private void aceptar() {
            // Validacion Codigo de barras
        if (txtCodigoBarras.getText() == null || txtCodigoBarras.getText().isBlank()) {
            if (!
                mostrarAlertaAdvertencia("Alerta",
                    "Codigo de barras vacio", 
                    "¿Esta seguro de esta accion?"
                )
            ) {
                return;
            } else {
                producto.colocarCodigoBarras(null);
            }
        } else {
            producto.colocarCodigoBarras(txtCodigoBarras.getText().trim());
        }
            // Validacion Nobre de producto ////////////////
        if (txtNombreProducto.getText() == null || txtNombreProducto.getText().isBlank()) {
            new Alert(
                AlertType.ERROR,
                "Nombre de producto vacio, por favor corrijalo para continuar"
            ).showAndWait();
            return;
        }
        producto.colocarNombre(txtNombreProducto.getText().trim());
            // Validacion Marca
        if (txtMarca.getText().isBlank()) {
            new Alert(
                AlertType.ERROR,
                "Marca o distribuidor vacia, por favor corrijala para continuar"
            ).showAndWait();
            return;
        }
        producto.colocarMarca(txtMarca.getText().trim());
            // Validacion Cantidad de producto
        if (txtCantidadProducto.getText() == null || txtCantidadProducto.getText().isBlank()) {
             new Alert(
                    AlertType.ERROR,
                    "Cantidad producto vacio, por favor indique \"0\" o corríjala para continuar"
            ).showAndWait();
            return;
        }
        try {
            if (Double.parseDouble(txtCantidadProducto.getText().trim()) <= 0) {
                new Alert(
                    AlertType.ERROR,
                    "Cantidad producto es inferior o igual a 0, por favor corríjala para continuar"
                ).showAndWait();
                return;
            }
            producto.colocarCantidadProducto(
                Double.parseDouble(txtCantidadProducto.getText().trim())
            );
        } catch (NumberFormatException e) {
            new Alert(
                AlertType.ERROR,
                "Cantidad producto no es numérica, por favor corríjala para continuar"
            ).showAndWait();
            return;
        }
        if (unidadMedida.isSelected() 
            && txtUnidadMedida.getValue() != null 
            && txtUnidadMedida.getValue().equals(UnidadesMedida.UNIDAD)
        ) {
            double numero =  Double.parseDouble(txtCantidadProducto.getText().trim());
            if (!(numero % 1 == 0)) {
                 new Alert(
                    AlertType.ERROR,
                    "Campo \"Unidad\" seleccionado pero Cantidad de producto no es un entero, por favor corríjala para continuar"
                ).showAndWait();
                return;
            }
        }
            // Validacion Unidad medida
        if (unidadMedida.isSelected()) {
            if (txtUnidadMedida.getValue() == null || txtUnidadMedida.getValue().isBlank()) {
                txtUnidadMedida.getEditor().clear();
                new Alert(
                    AlertType.ERROR,
                    "Debe seleccionar una unidad de medida valida."
                ).showAndWait();
                return;
            }
            producto.colocarUnidadMedida(txtUnidadMedida.getValue());
            producto.colocarUnidadAgrupada(null);
        } else if (unidadAgrupada.isSelected()) {
            if (txtUnidadAgrupada.getSelectionModel().getSelectedItem() == null) {
                txtUnidadAgrupada.getEditor().clear();
                new Alert(
                    AlertType.ERROR,
                    "Debe seleccionar un producto padre valido."
                ).showAndWait();
                return;
            }
            if(productoRepositorio.consultarColumnaUnidadAgrupada()
                .contains(
                    String.valueOf(
                        producto.conseguirId()
                    )
                )
            ) {
                new Alert(
                    AlertType.ERROR,
                    "Este producto cuenta con unidades hijas, no puede ser una unidad agrupada."
                ).showAndWait();
                return;
            }
            producto.colocarUnidadMedida(null);
            producto.colocarUnidadAgrupada(
                productoCadenaId(
                    txtUnidadAgrupada.getSelectionModel().getSelectedItem()
                )
            );
        }
            // validacion Precio
        if (txtPrecio.getText() == null || txtPrecio.getText().isBlank()) {
            new Alert(
                AlertType.ERROR,
                "Precio vacio, por favor corrijalo para continuar"
            ).showAndWait();
            return;
        }
        try {
            if (Double.parseDouble(txtPrecio.getText().trim()) <= 0 ) {
                new Alert(
                    AlertType.ERROR,
                    "Precio es inferior o igual a 0, por favor corrijalo para continuar"
                ).showAndWait();
                return;
            }
            producto.colocarPrecio(Double.parseDouble(txtPrecio.getText().trim()));
        } catch (NumberFormatException e) {
            new Alert(
                AlertType.ERROR,
                "Precio no es numerico, por favor corrijalo para continuar"
            ).showAndWait();
            return;
        }
            // Validacion Exixtencias
        if (txtExistencias.getText() == null || txtExistencias.getText().isBlank()) {
            new Alert(
                AlertType.ERROR,
                "Existencias vacias, por favor corrijalo para continuar"
            ).showAndWait();
            return;
        }
        try {
            if (Double.parseDouble(txtExistencias.getText().trim()) < 0 ) {
                new Alert(
                    AlertType.ERROR,
                    "Existencias es inferior a 0, por favor corrijalo para continuar"
                ).showAndWait();
                return;
            }
            producto.colocarExistencias(Double.parseDouble(txtExistencias.getText().trim()));
        } catch (NumberFormatException e) {
            new Alert(
                AlertType.ERROR,
                "Existencias no es un numerico, por favor indique \"0\" o corrijalo para continuar"
            ).showAndWait();
            return;
        }
            // Validacion Minimo de existencias
        if (txtMinimoExistencias.getText() == null || txtMinimoExistencias.getText().isBlank()) {
            new Alert(
                AlertType.ERROR,
                "Minimo existencias esta vacio, por favor indique \"0\" o corrijalo para continuar"
            ).showAndWait();
            return;
        }
        try {
            if (Double.parseDouble(txtMinimoExistencias.getText().trim()) < 0 ) {
                new Alert(
                    AlertType.ERROR,
                    "Minimo de existencias es inferior a 0, por favor corrijalo para continuar"
                ).showAndWait();
                return;
            }
            producto.colocarMinimoExistencias(Double.parseDouble(txtMinimoExistencias.getText().trim()));
        } catch (NumberFormatException e) {
            new Alert(
                AlertType.ERROR,
                "Minimo de existencias no es un numerico, por favor indique \"0\" o corrijalo para continuar"
            ).showAndWait();
            return;
        }
        System.out.println(producto.toString());
            // Guardar o editar
        try {
            if (producto.conseguirId() == null) {
                productoRepositorio.agregarProducto(producto);
                new Alert(
                    AlertType.INFORMATION,
                    "Producto guardado correctamente"
                ).showAndWait();
            } else {
                productoRepositorio.editarProducto(producto);
                new Alert(
                    AlertType.INFORMATION,
                    "Producto editado correctamente"
                ).showAndWait();
            }
        } catch (RuntimeException e) {
            System.out.print("ERROR: " + e);
            new Alert(
                AlertType.ERROR,
                "No se pudo guardar el producto: " + e.getMessage()
            ).showAndWait();
            return;
        }
        cerrarVentana();
    }

    private boolean mostrarAlertaAdvertencia(String titulo, String cabecera, String cuerpo) {
        boolean bandera = false;
        Alert alerta = new Alert(AlertType.CONFIRMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecera);
        alerta.setContentText(cuerpo);
        // Definimos botones personalizados
        ButtonType botonContinuar = new ButtonType("Si", ButtonBar.ButtonData.OK_DONE);
        ButtonType botonRevisar   = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        alerta.getButtonTypes().setAll(botonContinuar, botonRevisar);
        // Capturamos la respuesta
        Optional<ButtonType> resultado = alerta.showAndWait();
        if (resultado.isPresent() && resultado.get() == botonContinuar) {
            bandera = true;
        } else if (resultado.isPresent() && resultado.get() == botonRevisar) {
            bandera = false;
        }
        return bandera;
    }

    private Integer productoCadenaId(String cadena) {
        return Integer.parseInt(
            cadena.split("-")[0].trim()
        );
    }

    @FXML
    private void cancelar() {
        cerrarVentana();
    }

    private void cerrarVentana() {
        Stage ventana = (Stage) botonCancelar.getScene().getWindow();
        ventana.close();
    }

    @FXML
    private void borrarProducto() {  
        if(mostrarAlertaAdvertencia("AVISO",
            "Se eliminara el producto.", 
            "¿Esta seguro de esta accion?"
        )){
            productoRepositorio.borrarProducto(producto.conseguirId());
            cerrarVentana();
        }
    }

}
