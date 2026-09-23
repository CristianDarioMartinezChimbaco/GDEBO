package com.gestion.controller.lote;

import java.io.IOException;

import com.gestion.model.Producto;
import com.gestion.repository.ProductoRepositorio;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoteControlador {

    private final ProductoRepositorio productoRepositorio = new ProductoRepositorio();
    private final ObservableList<Producto> productosObservables = FXCollections.observableArrayList();
    @FXML private TableView<Producto> tablaProductos;
    @FXML private TableColumn<Producto, String> columnaCodigo;
    @FXML private TableColumn<Producto, String> columnaNombre;
    @FXML private TableColumn<Producto, String> columnaMarca;
    @FXML private TableColumn<Producto, Double> columnaCantidad;
    @FXML private TableColumn<Producto, String> columnaUnidadMedida;
    @FXML private TableColumn<Producto, Integer> columnaUnidadAgrupada;
    @FXML private TableColumn<Producto, Double> columnaPrecio;
    @FXML private TableColumn<Producto, Double> columnaExistencias;
    @FXML private TableColumn<Producto, Double> columnaMinimoExistencias;
    @FXML private TableColumn<Producto, Void> columnaEditar;

    @FXML
    public void initialize() {
        inicializarProductosTablaVista();
        inicializarColumnaEdicion();
        tablaProductos.setItems(productosObservables);
        cargarProductos();
    }

    private void inicializarProductosTablaVista() {
        columnaCodigo.setCellValueFactory(
            celda -> new SimpleStringProperty(celda.getValue().conseguirCodigoBarras()));
        columnaNombre.setCellValueFactory(
            celda -> new SimpleStringProperty(celda.getValue().conseguirNombre()));
        columnaMarca.setCellValueFactory(
            celda -> new SimpleStringProperty(celda.getValue().conseguirMarca()));
        columnaCantidad.setCellValueFactory(
            celda -> new SimpleObjectProperty<>(celda.getValue().conseguirCantidadProducto()));
        columnaUnidadMedida.setCellValueFactory(
            celda -> new SimpleStringProperty(celda.getValue().conseguirUnidadMedida()));
        columnaUnidadAgrupada.setCellValueFactory(
            celda -> new SimpleObjectProperty<>(celda.getValue().conseguirUnidadAgrupada()));
        columnaPrecio.setCellValueFactory(
            celda -> new SimpleObjectProperty<>(celda.getValue().conseguirPrecio()));
        columnaExistencias.setCellValueFactory(
            celda -> new SimpleObjectProperty<>(celda.getValue().conseguirExistencias()));
        columnaMinimoExistencias.setCellValueFactory(
            celda -> new SimpleObjectProperty<>(celda.getValue().conseguirMinimoExistencias()));
    }

    private void inicializarColumnaEdicion () {
        columnaEditar.setCellFactory(columna -> new TableCell<>() {
        private final Button botonEditar = new Button("Editar");
        {
            botonEditar.setOnAction(event -> {
                Producto producto =
                    getTableView().getItems().get(getIndex());
                editarProducto(producto);
            });
        }
        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setGraphic(null);
            } else {
                setGraphic(botonEditar);
            }
        }
    });
    }

    private void cargarProductos() {
        productosObservables.setAll(productoRepositorio.consultarTodo());
    }

    @FXML
    private void abrirFormularioAgregar() {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/gestion/view/ProductoAgregar.fxml"));
            Parent root = loader.load();
            Stage ventana = new Stage();
            ventana.setTitle("Agregar producto");
            ventana.setScene(new Scene(root));
            ventana.initModality(Modality.APPLICATION_MODAL);
            ventana.showAndWait();
            cargarProductos();
        } catch (IOException e) {
            e.printStackTrace();
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("Error");
            alerta.setHeaderText("No se pudo abrir el formulario");
            alerta.setContentText(e.getMessage());
            alerta.showAndWait();
        }
    }

    private void editarProducto(Producto producto) {
        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/gestion/view/ProductoEditar.fxml")
            );
            Parent root = loader.load();
            LoteFormularioControlador controlador =
                loader.getController();
            controlador.cargarProductoCampoTexto(producto);
            Stage ventana = new Stage();
            ventana.setTitle("Editar producto");
            ventana.setScene(new Scene(root));
            ventana.initModality(Modality.APPLICATION_MODAL);
            ventana.showAndWait();
            cargarProductos();
        } catch (IOException e) {
            e.printStackTrace();
            new Alert(
                Alert.AlertType.ERROR,
                "No se pudo abrir el formulario de edición."
            ).showAndWait();
        }
    }
}