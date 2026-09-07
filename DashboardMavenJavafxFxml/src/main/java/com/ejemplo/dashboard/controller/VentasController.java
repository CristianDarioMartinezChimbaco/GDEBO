package com.ejemplo.dashboard.controller;

import com.ejemplo.dashboard.model.Venta;
import com.ejemplo.dashboard.util.DataGenerator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class VentasController {

    @FXML
    private TableView<Venta> tablaVentas;

    @FXML
    private TableColumn<Venta, Integer> colIdVenta;

    @FXML
    private TableColumn<Venta, String> colProducto;

    @FXML
    private TableColumn<Venta, Double> colMonto;

    @FXML
    private TableColumn<Venta, String> colFecha;

    @FXML
    private TableColumn<Venta, String> colEstado;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox<String> comboEstado;

    private ObservableList<Venta> ventasData;


    @FXML
    public void initialize() {

        ventasData = FXCollections.observableArrayList(
                DataGenerator.generarVentas()
        );

        configurarComboEstado();

        configurarTablaVentas();
    }


    private void configurarComboEstado() {

        comboEstado.setItems(
                FXCollections.observableArrayList(
                        "Todos",
                        "Completada",
                        "Pendiente",
                        "Cancelada"
                )
        );

        comboEstado.setValue("Todos");
    }


    private void configurarTablaVentas() {

        colIdVenta.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );

        colProducto.setCellValueFactory(
                new PropertyValueFactory<>("producto")
        );

        colMonto.setCellValueFactory(
                new PropertyValueFactory<>("monto")
        );

        colFecha.setCellValueFactory(
                new PropertyValueFactory<>("fecha")
        );

        colEstado.setCellValueFactory(
                new PropertyValueFactory<>("estado")
        );

        tablaVentas.setItems(ventasData);
    }
}