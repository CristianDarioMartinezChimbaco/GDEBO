package com.gestion.controller.lote;

import java.io.IOException;

import com.gestion.model.Lote;
import com.gestion.repository.LoteRepositorio;
import com.gestion.repository.ProductoRepositorio;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoteControlador {

    private final LoteRepositorio loteRepositorio =
        new LoteRepositorio();
    private final ProductoRepositorio productoRepositorio =
        new ProductoRepositorio();
    private final ObservableList<Lote> lotesObservables =
        FXCollections.observableArrayList();
    private FilteredList<String> listaFiltroLote =
        new FilteredList<>(
            loteRepositorio.consultarColumnaLote()
        );
    private FilteredList<String> listaFiltroProducto =
        new FilteredList<>(
            productoRepositorio.consultarColumnaNombreProducto()
        );
    private final FilteredList<Lote> listaFiltroTabla =
        new FilteredList<>(
            lotesObservables,
            l -> true
        );

    @FXML private Button botonAgregar;
    @FXML private ComboBox<String> filtroLote;
    @FXML private ComboBox<String> filtroProducto;
    @FXML private DatePicker filtroFechaRegistro;
    @FXML private DatePicker filtroFechaExpedicion;
    @FXML private DatePicker filtroFechaVencimiento;
    @FXML private TableView<Lote> tablaLotes;
    @FXML private TableColumn<Lote, String> columnaLote;
    @FXML private TableColumn<Lote, String> columnaProducto;
    @FXML private TableColumn<Lote, String> columnaFechaRegistro;
    @FXML private TableColumn<Lote, String> columnaFechaExpedicion;
    @FXML private TableColumn<Lote, String> columnaFechaVencimiento;
    @FXML private TableColumn<Lote, Double> columnaVolumenInventario;
    @FXML private TableColumn<Lote, Double> columnaValorTransaccion;
    @FXML private TableColumn<Lote, String> columnaMotivo;

    private void cargarListaFiltros() {
        configurarFiltro(
            filtroLote,
            listaFiltroLote
        );
        configurarFiltro(
            filtroProducto,
            listaFiltroProducto
        );
        filtroFechaRegistro.valueProperty().addListener(
            (obs, old, nuevo) -> aplicarFiltros()
        );
        filtroFechaExpedicion.valueProperty().addListener(
            (obs, old, nuevo) -> aplicarFiltros()
        );
        filtroFechaVencimiento.valueProperty().addListener(
            (obs, old, nuevo) -> aplicarFiltros()
        );
    }

    private void inicializarLotesTablaVista() {
        columnaLote.setCellValueFactory(
            celda -> new SimpleStringProperty(
                celda.getValue().conseguirLote()
            )
        );
        columnaProducto.setCellValueFactory(
            celda -> new SimpleStringProperty(
                celda.getValue()
                    .conseguirProducto()
                    .conseguirNombre()
            )
        );
        columnaFechaRegistro.setCellValueFactory(
            celda -> new SimpleStringProperty(
                celda.getValue().conseguirFechaRegistro()
            )
        );
        columnaFechaExpedicion.setCellValueFactory(
            celda -> new SimpleStringProperty(
                celda.getValue().conseguirFechaExpedicion()
            )
        );
        columnaFechaVencimiento.setCellValueFactory(
            celda -> new SimpleStringProperty(
                celda.getValue().conseguirFechaVencimiento()
            )
        );
        columnaVolumenInventario.setCellValueFactory(
            celda -> new SimpleObjectProperty<>(
                celda.getValue().conseguirCantidadMovimiento()
            )
        );
        columnaValorTransaccion.setCellValueFactory(
            celda -> new SimpleObjectProperty<>(
                celda.getValue().conseguirValorMovimiento()
            )
        );
        columnaMotivo.setCellValueFactory(
            celda -> new SimpleStringProperty(
                celda.getValue().conseguirMotivo()
            )
        );
    }

    private void cargarLotes() {
        lotesObservables.setAll(
            loteRepositorio.consultarTodo()
        );
    }

    private void aplicarFiltros() {
        String lote = filtroLote.getEditor()
            .getText()
            .trim()
            .toLowerCase();
        String producto = filtroProducto.getEditor()
            .getText()
            .trim()
            .toLowerCase();
        String fechaRegistro =
            filtroFechaRegistro.getValue() == null
                ? ""
                : filtroFechaRegistro.getValue().toString();
        String fechaExpedicion =
            filtroFechaExpedicion.getValue() == null
                ? ""
                : filtroFechaExpedicion.getValue().toString();
        String fechaVencimiento =
            filtroFechaVencimiento.getValue() == null
                ? ""
                : filtroFechaVencimiento.getValue().toString();
        listaFiltroTabla.setPredicate(
            loteMovimiento -> {
                boolean coincideLote =
                    lote.isEmpty()
                    || loteMovimiento.conseguirLote()
                        .toLowerCase()
                        .contains(lote);
                boolean coincideProducto =
                    producto.isEmpty()
                    || loteMovimiento
                        .conseguirProducto()
                        .conseguirNombre()
                        .toLowerCase()
                        .contains(producto);
                boolean coincideFechaRegistro =
                    fechaRegistro.isEmpty()
                    || loteMovimiento
                        .conseguirFechaRegistro()
                        .equals(fechaRegistro);
                boolean coincideFechaExpedicion =
                    fechaExpedicion.isEmpty()
                    || (
                        loteMovimiento
                            .conseguirFechaExpedicion() != null
                        && loteMovimiento
                            .conseguirFechaExpedicion()
                            .equals(fechaExpedicion)
                    );
                boolean coincideFechaVencimiento =
                    fechaVencimiento.isEmpty()
                    || loteMovimiento
                        .conseguirFechaVencimiento()
                        .equals(fechaVencimiento);
                return coincideLote
                    && coincideProducto
                    && coincideFechaRegistro
                    && coincideFechaExpedicion
                    && coincideFechaVencimiento;
            }
        );
    }

    private void configurarFiltro(
        ComboBox<String> comboBox,
        FilteredList<String> lista
    ) {
        comboBox.setItems(lista);
        comboBox.getEditor().textProperty().addListener(
            (obs, old, nuevo) -> {
                lista.setPredicate(
                    s ->
                        nuevo == null
                        || nuevo.isEmpty()
                        || s.toLowerCase()
                            .contains(nuevo.toLowerCase())
                );
                aplicarFiltros();
            }
        );
    }

    @FXML
    public void initialize() {
        cargarListaFiltros();
        inicializarLotesTablaVista();
        tablaLotes.setItems(listaFiltroTabla);
        cargarLotes();
    }

    @FXML
    private void abrirFormularioAgregar() {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource(
                    "/com/gestion/view/lote/LoteAgregar.fxml"
                )
            );
            Parent root = loader.load();
            Stage ventana = new Stage();
            ventana.setTitle("Agregar lote");
            ventana.setScene(new Scene(root));
            ventana.initModality(
                Modality.APPLICATION_MODAL
            );
            ventana.showAndWait();
            cargarLotes();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alerta =
                new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText(
                "No se pudo abrir el formulario"
            );
            alerta.setContentText(e.getMessage());
            alerta.showAndWait();
        }
    }
}
