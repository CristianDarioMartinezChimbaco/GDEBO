package main.java.com.ejemplo.controller;

import com.ejemplo.model.Producto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

import javafx.fxml.FXML;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.beans.property.ReadOnlyObjectWrapper;

import java.text.NumberFormat;
import java.util.Locale;

public class DashboardController {

    @FXML
    private Label lblProductos;

    @FXML
    private Label lblStock;

    @FXML
    private Label lblVentas;

    @FXML
    private Label lblStockBajo;

    @FXML
    private Label lblPorcentaje;

    @FXML
    private TextField txtBuscar;

    @FXML
    private ComboBox<String> cmbCategoria;

    @FXML
    private TableView<Producto> tablaProductos;

    @FXML
    private TableColumn<Producto, String> colCodigo;

    @FXML
    private TableColumn<Producto, String> colNombre;

    @FXML
    private TableColumn<Producto, String> colCategoria;

    @FXML
    private TableColumn<Producto, Integer> colStock;

    @FXML
    private TableColumn<Producto, Double> colPrecio;

    @FXML
    private BarChart<String, Number> barChart;

    @FXML
    private PieChart pieChart;

    private final ObservableList<Producto> productos =
            FXCollections.observableArrayList();

    private FilteredList<Producto> productosFiltrados;


    @FXML
    public void initialize() {

        cargarDatos();

        configurarTabla();

        configurarCategorias();

        configurarBusqueda();

        cargarEstadisticas();

        cargarGraficaBarras();

        cargarGraficaCircular();
    }


    private void cargarDatos() {

        productos.addAll(

                new Producto(
                        "001",
                        "Arroz Diana 1kg",
                        "Granos",
                        35,
                        4800
                ),

                new Producto(
                        "002",
                        "Frijol Bola Roja 500g",
                        "Granos",
                        12,
                        5200
                ),

                new Producto(
                        "003",
                        "Aceite Premier 1L",
                        "Aceites",
                        8,
                        9200
                ),

                new Producto(
                        "004",
                        "Leche Entera 1L",
                        "Lácteos",
                        25,
                        4200
                ),

                new Producto(
                        "005",
                        "Huevos AA x30",
                        "Huevos",
                        6,
                        18000
                ),

                new Producto(
                        "006",
                        "Café Sello Rojo 500g",
                        "Bebidas",
                        18,
                        14500
                ),

                new Producto(
                        "007",
                        "Azúcar 1kg",
                        "Granos",
                        30,
                        4500
                )

        );
    }


    private void configurarTabla() {

        colCodigo.setCellValueFactory(
                new PropertyValueFactory<>("codigo")
        );

        colNombre.setCellValueFactory(
                new PropertyValueFactory<>("nombre")
        );

        colCategoria.setCellValueFactory(
                new PropertyValueFactory<>("categoria")
        );

        colStock.setCellValueFactory(
                new PropertyValueFactory<>("stock")
        );

        colPrecio.setCellValueFactory(
                new PropertyValueFactory<>("precio")
        );

        productosFiltrados =
                new FilteredList<>(productos, p -> true);

        tablaProductos.setItems(productosFiltrados);
    }


    private void configurarCategorias() {

        cmbCategoria.getItems().addAll(
                "Todas",
                "Granos",
                "Aceites",
                "Lácteos",
                "Huevos",
                "Bebidas"
        );

        cmbCategoria.setValue("Todas");

        cmbCategoria.setOnAction(
                event -> filtrar()
        );
    }


    private void configurarBusqueda() {

        txtBuscar.textProperty().addListener(
                (observable, anterior, nuevo) -> filtrar()
        );
    }


    private void filtrar() {

        String texto = txtBuscar
                .getText()
                .toLowerCase()
                .trim();

        String categoria = cmbCategoria.getValue();

        productosFiltrados.setPredicate(producto -> {

            boolean coincideTexto =
                    producto.getNombre()
                            .toLowerCase()
                            .contains(texto)
                    ||
                    producto.getCodigo()
                            .toLowerCase()
                            .contains(texto);

            boolean coincideCategoria =
                    categoria == null
                    || categoria.equals("Todas")
                    || producto.getCategoria()
                            .equals(categoria);

            return coincideTexto && coincideCategoria;
        });
    }


    private void cargarEstadisticas() {

        lblProductos.setText(
                String.valueOf(productos.size())
        );

        int stockTotal = productos.stream()
                .mapToInt(Producto::getStock)
                .sum();

        lblStock.setText(
                String.valueOf(stockTotal)
        );

        lblVentas.setText(
                "$2.845.000"
        );

        long stockBajo = productos.stream()
                .filter(p -> p.getStock() < 10)
                .count();

        lblStockBajo.setText(
                String.valueOf(stockBajo)
        );
    }


    private void cargarGraficaBarras() {

        XYChart.Series<String, Number> serie =
                new XYChart.Series<>();

        serie.setName("Ventas");

        serie.getData().add(
                new XYChart.Data<>("Granos", 45)
        );

        serie.getData().add(
                new XYChart.Data<>("Aceites", 30)
        );

        serie.getData().add(
                new XYChart.Data<>("Lácteos", 25)
        );

        serie.getData().add(
                new XYChart.Data<>("Bebidas", 40)
        );

        serie.getData().add(
                new XYChart.Data<>("Huevos", 20)
        );

        barChart.getData().add(serie);
    }


    private void cargarGraficaCircular() {

        pieChart.getData().addAll(

                new PieChart.Data(
                        "Granos",
                        45
                ),

                new PieChart.Data(
                        "Aceites",
                        20
                ),

                new PieChart.Data(
                        "Lácteos",
                        15
                ),

                new PieChart.Data(
                        "Bebidas",
                        12
                ),

                new PieChart.Data(
                        "Huevos",
                        8
                )
        );
    }


    @FXML
    private void nuevoProducto() {

        Alert alerta = new Alert(
                Alert.AlertType.INFORMATION
        );

        alerta.setTitle("Nuevo producto");

        alerta.setHeaderText(
                "Registrar producto"
        );

        alerta.setContentText(
                "Aquí posteriormente tendremos "
                + "el formulario de registro."
        );

        alerta.showAndWait();
    }


    @FXML
    private void mostrarDashboard() {

        System.out.println(
                "Dashboard seleccionado"
        );
    }


    @FXML
    private void mostrarInventario() {

        System.out.println(
                "Inventario seleccionado"
        );
    }


    @FXML
    private void mostrarVentas() {

        System.out.println(
                "Ventas seleccionado"
        );
    }


    @FXML
    private void mostrarReportes() {

        System.out.println(
                "Reportes seleccionado"
        );
    }
}