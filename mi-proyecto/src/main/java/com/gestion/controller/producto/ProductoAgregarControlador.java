package com.gestion.controller.producto;

import java.util.Optional;

import com.gestion.model.Producto;
import com.gestion.repository.ProductoRepositorio;

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

public class ProductoAgregarControlador {
    private Producto producto = new Producto();
    private ProductoRepositorio productoRepositorio = new ProductoRepositorio();
    private static final ObservableList<String> UNIDADES_ORIGINALES = 
        FXCollections.observableArrayList( 
            "kilo gramo", "litro", "libra", "mililitro", "gramo"
        );
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
        if (txtCodigoBarras.getText().trim().isBlank()) {
            if (!
                mostrarAlertaAdvertencia("Alerta",
                    "Codigo de barras vacio", 
                    "¿Esta seguro de esta accion?"
                )
            ) {
                return;
            }
        }
        producto.colocarCodigoBarras(txtCodigoBarras.getText().trim());
        if (txtNombreProducto.getText().trim().isBlank()) {
            new Alert(
                AlertType.ERROR,
                "Nombre de producto vacio, por favor corrijalo para continuar"
            ).showAndWait();
            return;
        }
        producto.colocarNombre(txtNombreProducto.getText().trim());
        if (txtMarca.getText().trim().isBlank()) {
            new Alert(
                AlertType.ERROR,
                "Marca o distribuidor vacia, por favor corrijala para continuar"
            ).showAndWait();
            return;
        }
        producto.colocarMarca(txtMarca.getText().trim());
        try {
            producto.colocarCantidadProducto(Double.parseDouble(txtCantidadProducto.getText().trim()));
        } catch (NumberFormatException e) {
            if (txtCantidadProducto.getText().trim().isBlank()) {    
                if (!
                    mostrarAlertaAdvertencia("Alerta",
                        "Cantidad producto no es numerico", 
                        "¿Esta seguro de esta accion?"
                    )
                ) {
                    return;
                } else {
                    producto.colocarCantidadProducto(null);
                }
            }
        }
        if (unidadMedida.isSelected()) {
            if (txtUnidadMedida.getValue() == null) {
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
            producto.colocarUnidadMedida(null);
            producto.colocarUnidadAgrupada(
                productoCadenaId(
                    txtUnidadAgrupada.getSelectionModel().getSelectedItem()
                )
            );
        }
        if (txtPrecio.getText().trim().isBlank()) {
            new Alert(
                AlertType.ERROR,
                "Precio vacio, por favor corrijalo para continuar"
            ).showAndWait();
            return;
        }
        try {
            producto.colocarPrecio(Double.parseDouble(txtPrecio.getText().trim()));
        } catch (NumberFormatException e) {
            new Alert(
                AlertType.ERROR,
                "Precio no es numerico, por favor corrijalo para continuar"
            ).showAndWait();
            return;
        }
        if (txtExistencias.getText().trim().isBlank()) {
            new Alert(
                AlertType.ERROR,
                "Existencias vacias, por favor corrijalo para continuar"
            ).showAndWait();
            return;
        }
        try {
            producto.colocarExistencias(Double.parseDouble(txtExistencias.getText().trim()));
        } catch (NumberFormatException e) {
            new Alert(
                AlertType.ERROR,
                "Existencias no es un numerico, por favor indique \"0\" o corrijalo para continuar"
            ).showAndWait();
            return;
        }
        if (txtMinimoExistencias.getText().trim().isBlank()) {
            new Alert(
                AlertType.ERROR,
                "Minimo existencias esta vacio, por favor indique \"0\" o corrijalo para continuar"
            ).showAndWait();
            return;
        }
        try {
            producto.colocarMinimoExistencias(Double.parseDouble(txtMinimoExistencias.getText().trim()));
        } catch (NumberFormatException e) {
            new Alert(
                AlertType.ERROR,
                "Existencias no es un numerico, por favor indique \"0\" o corrijalo para continuar"
            ).showAndWait();
            return;
        }
        System.out.println(producto.toString());
        try {
            productoRepositorio.agregarProducto(producto);
        } catch (RuntimeException e) {
            System.out.print("ERROR: " + e);
            new Alert(
                AlertType.ERROR,
                "No se pudo guardar el producto " + e
            ).showAndWait();
            return;
        }
        new Alert(
                AlertType.CONFIRMATION,
                "Producto guardado correctamente"
        ).showAndWait();
        cerrarVentana();
    }


    private boolean mostrarAlertaAdvertencia(String titulo, String cabecera, String cuerpo) {
        boolean bandera = false;
        Alert alerta = new Alert(AlertType.WARNING);
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

}
