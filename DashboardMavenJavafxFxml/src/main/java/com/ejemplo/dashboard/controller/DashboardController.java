package com.ejemplo.dashboard.controller;

import com.ejemplo.dashboard.model.Usuario;
import com.ejemplo.dashboard.model.Venta;
import com.ejemplo.dashboard.util.DataGenerator;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;

import java.util.List;

public class DashboardController {

    @FXML
    private Label lblTotalUsuarios;

    @FXML
    private Label lblUsuariosActivos;

    @FXML
    private Label lblTotalVentas;

    @FXML
    private Label lblIngresosTotales;

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;


    @FXML
    public void initialize() {

        actualizarEstadisticas();

        configurarGrafico();
    }


    private void actualizarEstadisticas() {

        List<Usuario> usuarios =
                DataGenerator.generarUsuarios();

        List<Venta> ventas =
                DataGenerator.generarVentas();


        int totalUsuarios =
                usuarios.size();


        long usuariosActivos =
                usuarios.stream()
                        .filter(Usuario::isActivo)
                        .count();


        int totalVentas =
                ventas.size();


        double ingresosTotales =
                ventas.stream()
                        .filter(v ->
                                v.getEstado().equals("Completada")
                        )
                        .mapToDouble(Venta::getMonto)
                        .sum();


        lblTotalUsuarios.setText(
                String.valueOf(totalUsuarios)
        );

        lblUsuariosActivos.setText(
                usuariosActivos + " activos"
        );

        lblTotalVentas.setText(
                String.valueOf(totalVentas)
        );

        lblIngresosTotales.setText(
                String.format("$%.2f", ingresosTotales)
        );
    }


    private void configurarGrafico() {

        xAxis.setLabel("Días");

        yAxis.setLabel("Ventas ($)");


        XYChart.Series<String, Number> serie =
                new XYChart.Series<>();

        serie.setName("Ventas");


        serie.getData().add(
                new XYChart.Data<>("Lun", 1200)
        );

        serie.getData().add(
                new XYChart.Data<>("Mar", 1800)
        );

        serie.getData().add(
                new XYChart.Data<>("Mié", 1500)
        );

        serie.getData().add(
                new XYChart.Data<>("Jue", 2200)
        );

        serie.getData().add(
                new XYChart.Data<>("Vie", 2800)
        );

        serie.getData().add(
                new XYChart.Data<>("Sáb", 3200)
        );

        serie.getData().add(
                new XYChart.Data<>("Dom", 1900)
        );


        barChart.setData(
                FXCollections.observableArrayList(serie)
        );
    }
}