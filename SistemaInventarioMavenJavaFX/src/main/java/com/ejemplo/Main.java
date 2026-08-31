package com.ejemplo;

import javafx.application.Application;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;

import javafx.geometry.Insets;

import javafx.scene.Scene;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Pagination;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Separator;
import javafx.scene.control.Spinner;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;

import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;

import java.time.LocalDate;

public class Main extends Application {

    private final ObservableList<Producto> productos =
            FXCollections.observableArrayList();

    private BorderPane root;

    @Override
    public void start(Stage stage) {

        cargarDatos();

        root = new BorderPane();

        root.setTop(crearMenuSuperior());
        root.setLeft(crearSidebar());
        root.setCenter(crearDashboard());

        Scene scene = new Scene(root, 1250, 750);

        scene.getStylesheets().add(
                getClass().getResource("style.css").toExternalForm()
        );

        stage.setTitle("Sistema de Inventario - Don Edgar");
        stage.setScene(scene);
        stage.setMinWidth(1000);
        stage.setMinHeight(650);
        stage.show();
    }

    // =========================================================
    // DATOS
    // =========================================================

    private void cargarDatos() {

        productos.addAll(
                new Producto(
                        1,
                        "Arroz Diana",
                        "Granos",
                        250,
                        4200,
                        1050000,
                        "Disponible"
                ),

                new Producto(
                        2,
                        "Aceite Premier",
                        "Aceites",
                        35,
                        12500,
                        437500,
                        "Bajo stock"
                ),

                new Producto(
                        3,
                        "Leche Alquería",
                        "Lácteos",
                        120,
                        4500,
                        540000,
                        "Disponible"
                ),

                new Producto(
                        4,
                        "Huevos AA",
                        "Huevos",
                        15,
                        18000,
                        270000,
                        "Crítico"
                ),

                new Producto(
                        5,
                        "Frijol Rojo",
                        "Granos",
                        80,
                        7200,
                        576000,
                        "Disponible"
                ),

                new Producto(
                        6,
                        "Carne de Res",
                        "Cárnicos",
                        45,
                        28000,
                        1260000,
                        "Disponible"
                )
        );
    }

    // =========================================================
    // MENU
    // =========================================================

    private MenuBar crearMenuSuperior() {

        MenuBar menuBar = new MenuBar();

        Menu archivo = new Menu("Archivo");

        MenuItem nuevo = new MenuItem("Nuevo");
        MenuItem cerrar = new MenuItem("Cerrar");

        cerrar.setOnAction(e ->
                ((Stage) menuBar.getScene().getWindow()).close()
        );

        archivo.getItems().addAll(nuevo, cerrar);

        Menu herramientas = new Menu("Herramientas");

        MenuItem configuracion =
                new MenuItem("Configuración");

        MenuItem reportes =
                new MenuItem("Reportes");

        herramientas.getItems().addAll(
                configuracion,
                reportes
        );

        Menu ayuda = new Menu("Ayuda");

        MenuItem acerca =
                new MenuItem("Acerca del sistema");

        acerca.setOnAction(e -> mostrarAcerca());

        ayuda.getItems().add(acerca);

        menuBar.getMenus().addAll(
                archivo,
                herramientas,
                ayuda
        );

        return menuBar;
    }

    // =========================================================
    // SIDEBAR
    // =========================================================

    private VBox crearSidebar() {

        VBox sidebar = new VBox(10);

        sidebar.setPadding(new Insets(20));
        sidebar.setPrefWidth(220);

        Label titulo = new Label("DON EDGAR");
        titulo.getStyleClass().add("logo");

        Label subtitulo =
                new Label("Sistema de gestión");

        subtitulo.getStyleClass().add("subtitle");

        Separator separator = new Separator();

        Button dashboard =
                crearBotonSidebar("Dashboard");

        Button inventario =
                crearBotonSidebar("Inventario");

        Button ventas =
                crearBotonSidebar("Ventas");

        Button reportes =
                crearBotonSidebar("Reportes");

        Button configuracion =
                crearBotonSidebar("Configuración");

        dashboard.setOnAction(e ->
                root.setCenter(crearDashboard())
        );

        inventario.setOnAction(e ->
                root.setCenter(crearInventario())
        );

        ventas.setOnAction(e ->
                root.setCenter(crearVentas())
        );

        reportes.setOnAction(e ->
                root.setCenter(crearReportes())
        );

        configuracion.setOnAction(e ->
                mostrarConfiguracion()
        );

        Region espacio = new Region();

        VBox.setVgrow(espacio, Priority.ALWAYS);

        Label usuario =
                new Label("Usuario: Administrador");

        usuario.getStyleClass().add("user-label");

        sidebar.getChildren().addAll(
                titulo,
                subtitulo,
                separator,
                dashboard,
                inventario,
                ventas,
                reportes,
                configuracion,
                espacio,
                usuario
        );

        return sidebar;
    }

    private Button crearBotonSidebar(String texto) {

        Button boton = new Button(texto);

        boton.setMaxWidth(Double.MAX_VALUE);

        boton.getStyleClass().add("sidebar-button");

        Tooltip tooltip =
                new Tooltip("Abrir " + texto);

        boton.setTooltip(tooltip);

        return boton;
    }

    // =========================================================
    // DASHBOARD
    // =========================================================

    private VBox crearDashboard() {

        VBox contenido = new VBox(20);

        contenido.setPadding(new Insets(25));

        Label titulo =
                new Label("Dashboard");

        titulo.getStyleClass().add("page-title");

        Label fecha =
                new Label("Domingo, 30 de agosto de 2026");

        fecha.getStyleClass().add("date-label");

        HBox tarjetas = new HBox(15);

        tarjetas.getChildren().addAll(
                crearTarjeta(
                        "Productos",
                        "126",
                        "Productos registrados"
                ),

                crearTarjeta(
                        "Ventas hoy",
                        "$850.000",
                        "34 ventas"
                ),

                crearTarjeta(
                        "Inventario",
                        "$18.450.000",
                        "Valor total"
                ),

                crearTarjeta(
                        "Alertas",
                        "8",
                        "Requieren atención"
                )
        );

        HBox graficas = new HBox(20);

        graficas.getChildren().addAll(
                crearGraficaVentas(),
                crearGraficaCategorias()
        );

        HBox.setHgrow(
                graficas.getChildren().get(0),
                Priority.ALWAYS
        );

        HBox.setHgrow(
                graficas.getChildren().get(1),
                Priority.ALWAYS
        );

        contenido.getChildren().addAll(
                titulo,
                fecha,
                tarjetas,
                graficas
        );

        return contenido;
    }

    private VBox crearTarjeta(
            String titulo,
            String valor,
            String descripcion
    ) {

        VBox tarjeta = new VBox(8);

        tarjeta.setPadding(new Insets(18));

        tarjeta.setPrefWidth(230);
        tarjeta.setPrefHeight(120);

        tarjeta.getStyleClass().add("card");

        Label lblTitulo =
                new Label(titulo);

        lblTitulo.getStyleClass().add("card-title");

        Label lblValor =
                new Label(valor);

        lblValor.getStyleClass().add("card-value");

        Label lblDescripcion =
                new Label(descripcion);

        lblDescripcion.getStyleClass().add(
                "card-description"
        );

        tarjeta.getChildren().addAll(
                lblTitulo,
                lblValor,
                lblDescripcion
        );

        return tarjeta;
    }

    // =========================================================
    // GRAFICA DE VENTAS
    // =========================================================

    private VBox crearGraficaVentas() {

        CategoryAxis xAxis =
                new CategoryAxis();

        NumberAxis yAxis =
                new NumberAxis();

        xAxis.setLabel("Día");
        yAxis.setLabel("Ventas");

        BarChart<String, Number> chart =
                new BarChart<>(xAxis, yAxis);

        chart.setTitle("Ventas de la semana");

        XYChart.Series<String, Number> serie =
                new XYChart.Series<>();

        serie.setName("Ventas");

        serie.getData().add(
                new XYChart.Data<>("Lun", 450000)
        );

        serie.getData().add(
                new XYChart.Data<>("Mar", 520000)
        );

        serie.getData().add(
                new XYChart.Data<>("Mié", 390000)
        );

        serie.getData().add(
                new XYChart.Data<>("Jue", 710000)
        );

        serie.getData().add(
                new XYChart.Data<>("Vie", 850000)
        );

        serie.getData().add(
                new XYChart.Data<>("Sáb", 930000)
        );

        chart.getData().add(serie);

        chart.setPrefHeight(350);

        VBox contenedor =
                new VBox(chart);

        contenedor.getStyleClass().add("chart-card");

        VBox.setVgrow(chart, Priority.ALWAYS);

        return contenedor;
    }

    // =========================================================
    // GRAFICA DE CATEGORIAS
    // =========================================================

    private VBox crearGraficaCategorias() {

        PieChart pie =
                new PieChart();

        pie.setTitle("Productos por categoría");

        pie.getData().add(
                new PieChart.Data("Granos", 35)
        );

        pie.getData().add(
                new PieChart.Data("Cárnicos", 20)
        );

        pie.getData().add(
                new PieChart.Data("Lácteos", 15)
        );

        pie.getData().add(
                new PieChart.Data("Bebidas", 20)
        );

        pie.getData().add(
                new PieChart.Data("Otros", 10)
        );

        pie.setPrefHeight(350);

        VBox contenedor =
                new VBox(pie);

        contenedor.getStyleClass().add("chart-card");

        return contenedor;
    }

    // =========================================================
    // INVENTARIO
    // =========================================================

    private VBox crearInventario() {

        VBox contenido = new VBox(15);

        contenido.setPadding(new Insets(25));

        Label titulo =
                new Label("Inventario");

        titulo.getStyleClass().add("page-title");

        // -------------------------
        // FILTROS
        // -------------------------

        TextField buscador =
                new TextField();

        buscador.setPromptText(
                "Buscar producto..."
        );

        ComboBox<String> categoria =
                new ComboBox<>();

        categoria.getItems().addAll(
                "Todas",
                "Granos",
                "Aceites",
                "Lácteos",
                "Huevos",
                "Cárnicos"
        );

        categoria.setValue("Todas");

        DatePicker fecha =
                new DatePicker(
                        LocalDate.now()
                );

        Button nuevo =
                new Button("+ Nuevo producto");

        nuevo.getStyleClass().add(
                "primary-button"
        );

        nuevo.setOnAction(e ->
                mostrarNuevoProducto()
        );

        HBox filtros =
                new HBox(
                        10,
                        buscador,
                        categoria,
                        fecha,
                        nuevo
                );

        HBox.setHgrow(
                buscador,
                Priority.ALWAYS
        );

        // -------------------------
        // TABLA
        // -------------------------

        TableView<Producto> tabla =
                new TableView<>();

        TableColumn<Producto, Number> id =
                new TableColumn<>("ID");

        id.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );

        TableColumn<Producto, String> nombre =
                new TableColumn<>("Producto");

        nombre.setCellValueFactory(
                new PropertyValueFactory<>("nombre")
        );

        TableColumn<Producto, String> cat =
                new TableColumn<>("Categoría");

        cat.setCellValueFactory(
                new PropertyValueFactory<>("categoria")
        );

        TableColumn<Producto, Number> stock =
                new TableColumn<>("Stock");

        stock.setCellValueFactory(
                new PropertyValueFactory<>("stock")
        );

        TableColumn<Producto, Number> precio =
                new TableColumn<>("Precio");

        precio.setCellValueFactory(
                new PropertyValueFactory<>("precio")
        );

        TableColumn<Producto, String> estado =
                new TableColumn<>("Estado");

        estado.setCellValueFactory(
                new PropertyValueFactory<>("estado")
        );

        tabla.getColumns().addAll(
                id,
                nombre,
                cat,
                stock,
                precio,
                estado
        );

        // -------------------------
        // FILTRADO
        // -------------------------

        FilteredList<Producto> filtrados =
                new FilteredList<>(
                        productos,
                        p -> true
                );

        buscador.textProperty()
                .addListener((obs, viejo, nuevoTexto) -> {

                    filtrados.setPredicate(
                            producto -> {

                                if (nuevoTexto == null ||
                                        nuevoTexto.isBlank()) {

                                    return true;
                                }

                                return producto
                                        .getNombre()
                                        .toLowerCase()
                                        .contains(
                                                nuevoTexto
                                                        .toLowerCase()
                                        );
                            }
                    );
                });

        SortedList<Producto> ordenados =
                new SortedList<>(filtrados);

        ordenados.comparatorProperty()
                .bind(
                        tabla.comparatorProperty()
                );

        tabla.setItems(ordenados);

        // -------------------------
        // PAGINACION
        // -------------------------

        Pagination paginacion =
                new Pagination(3, 0);

        contenido.getChildren().addAll(
                titulo,
                filtros,
                tabla,
                paginacion
        );

        VBox.setVgrow(
                tabla,
                Priority.ALWAYS
        );

        return contenido;
    }

    // =========================================================
    // VENTAS
    // =========================================================

    private VBox crearVentas() {

        VBox contenido = new VBox(20);

        contenido.setPadding(
                new Insets(25)
        );

        Label titulo =
                new Label("Ventas");

        titulo.getStyleClass().add(
                "page-title"
        );

        // PRODUCTO

        ComboBox<String> producto =
                new ComboBox<>();

        producto.getItems().addAll(
                "Arroz Diana",
                "Aceite Premier",
                "Leche Alquería",
                "Huevos AA",
                "Frijol Rojo",
                "Carne de Res"
        );

        producto.setPromptText(
                "Seleccionar producto"
        );

        // CANTIDAD

        Spinner<Integer> cantidad =
                new Spinner<>(1, 100, 1);

        // PRECIO

        Label precio =
                new Label("$4.200");

        precio.getStyleClass().add(
                "card-value"
        );

        // PROGRESO

        ProgressBar progreso =
                new ProgressBar(0.65);

        progreso.setPrefWidth(400);

        Label progresoTexto =
                new Label(
                        "Meta de ventas: 65%"
                );

        // BOTON

        Button registrar =
                new Button("Registrar venta");

        registrar.getStyleClass().add(
                "primary-button"
        );

        registrar.setOnAction(e -> {

            if (producto.getValue() == null) {

                mostrarError(
                        "Debe seleccionar un producto."
                );

                return;
            }

            mostrarInformacion(
                    "Venta registrada",
                    "Se registró la venta correctamente."
            );
        });

        HBox formulario =
                new HBox(
                        15,
                        producto,
                        cantidad,
                        precio,
                        registrar
                );

        VBox meta =
                new VBox(
                        10,
                        progresoTexto,
                        progreso
                );

        TitledPane panel =
                new TitledPane(
                        "Registrar venta",
                        formulario
                );

        panel.setExpanded(true);

        contenido.getChildren().addAll(
                titulo,
                panel,
                meta
        );

        return contenido;
    }

    // =========================================================
    // REPORTES
    // =========================================================

    private VBox crearReportes() {

        VBox contenido = new VBox(15);

        contenido.setPadding(
                new Insets(25)
        );

        Label titulo =
                new Label("Reportes");

        titulo.getStyleClass().add(
                "page-title"
        );

        TabPane tabs =
                new TabPane();

        Tab ventas =
                new Tab("Ventas");

        Tab inventario =
                new Tab("Inventario");

        Tab financiero =
                new Tab("Financiero");

        ventas.setContent(
                crearReporteVentas()
        );

        inventario.setContent(
                crearReporteInventario()
        );

        financiero.setContent(
                crearReporteFinanciero()
        );

        tabs.getTabs().addAll(
                ventas,
                inventario,
                financiero
        );

        tabs.setTabClosingPolicy(
                TabPane.TabClosingPolicy.UNAVAILABLE
        );

        contenido.getChildren().addAll(
                titulo,
                tabs
        );

        VBox.setVgrow(
                tabs,
                Priority.ALWAYS
        );

        return contenido;
    }

    private VBox crearReporteVentas() {

        VBox box =
                new VBox(15);

        box.setPadding(
                new Insets(20)
        );

        box.getChildren().addAll(
                new Label("Ventas del mes"),
                new Label("$18.450.000"),
                new Label("342 transacciones")
        );

        return box;
    }

    private VBox crearReporteInventario() {

        VBox box =
                new VBox(15);

        box.setPadding(
                new Insets(20)
        );

        box.getChildren().addAll(
                new Label("Valor del inventario"),
                new Label("$18.450.000"),
                new Label("126 productos")
        );

        return box;
    }

    private VBox crearReporteFinanciero() {

        VBox box =
                new VBox(15);

        box.setPadding(
                new Insets(20)
        );

        box.getChildren().addAll(
                new Label("Ingresos"),
                new Label("$25.000.000"),
                new Label("Utilidad estimada: $5.800.000")
        );

        return box;
    }

    // =========================================================
    // DIALOGOS
    // =========================================================

    private void mostrarNuevoProducto() {

        Dialog<ButtonType> dialog =
                new Dialog<>();

        dialog.setTitle(
                "Nuevo producto"
        );

        ButtonType guardar =
                new ButtonType(
                        "Guardar",
                        ButtonBar.ButtonData.OK_DONE
                );

        dialog.getDialogPane()
                .getButtonTypes()
                .addAll(
                        guardar,
                        ButtonType.CANCEL
                );

        GridPane grid =
                new GridPane();

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(
                new Insets(20)
        );

        TextField nombre =
                new TextField();

        TextField categoria =
                new TextField();

        TextField precio =
                new TextField();

        TextField stock =
                new TextField();

        grid.add(
                new Label("Nombre:"),
                0, 0
        );

        grid.add(
                nombre,
                1, 0
        );

        grid.add(
                new Label("Categoría:"),
                0, 1
        );

        grid.add(
                categoria,
                1, 1
        );

        grid.add(
                new Label("Precio:"),
                0, 2
        );

        grid.add(
                precio,
                1, 2
        );

        grid.add(
                new Label("Stock:"),
                0, 3
        );

        grid.add(
                stock,
                1, 3
        );

        dialog.getDialogPane()
                .setContent(grid);

        dialog.showAndWait();
    }

    private void mostrarConfiguracion() {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle("Configuración");
        alert.setHeaderText(
                "Configuración del sistema"
        );

        alert.setContentText(
                "Aquí podrían configurarse:\n\n" +
                "• Usuarios\n" +
                "• Impuestos\n" +
                "• Unidades de medida\n" +
                "• Datos de la empresa\n" +
                "• Parámetros de inventario"
        );

        alert.showAndWait();
    }

    private void mostrarAcerca() {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle("Acerca de");
        alert.setHeaderText(
                "Sistema de Inventario Don Edgar"
        );

        alert.setContentText(
                "Sistema desarrollado en JavaFX\n" +
                "Versión 1.0"
        );

        alert.showAndWait();
    }

    private void mostrarInformacion(
            String titulo,
            String mensaje
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.INFORMATION
                );

        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        alert.showAndWait();
    }

    private void mostrarError(
            String mensaje
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.ERROR
                );

        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);

        alert.showAndWait();
    }

    // =========================================================
    // MODELO
    // =========================================================

    public static class Producto {

        private final IntegerProperty id;
        private final StringProperty nombre;
        private final StringProperty categoria;
        private final IntegerProperty stock;
        private final DoubleProperty precio;
        private final DoubleProperty valorInventario;
        private final StringProperty estado;

        public Producto(
                int id,
                String nombre,
                String categoria,
                int stock,
                double precio,
                double valorInventario,
                String estado
        ) {

            this.id =
                    new SimpleIntegerProperty(id);

            this.nombre =
                    new SimpleStringProperty(nombre);

            this.categoria =
                    new SimpleStringProperty(categoria);

            this.stock =
                    new SimpleIntegerProperty(stock);

            this.precio =
                    new SimpleDoubleProperty(precio);

            this.valorInventario =
                    new SimpleDoubleProperty(
                            valorInventario
                    );

            this.estado =
                    new SimpleStringProperty(estado);
        }

        public int getId() {
            return id.get();
        }

        public String getNombre() {
            return nombre.get();
        }

        public String getCategoria() {
            return categoria.get();
        }

        public int getStock() {
            return stock.get();
        }

        public double getPrecio() {
            return precio.get();
        }

        public double getValorInventario() {
            return valorInventario.get();
        }

        public String getEstado() {
            return estado.get();
        }

        public IntegerProperty idProperty() {
            return id;
        }

        public StringProperty nombreProperty() {
            return nombre;
        }

        public StringProperty categoriaProperty() {
            return categoria;
        }

        public IntegerProperty stockProperty() {
            return stock;
        }

        public DoubleProperty precioProperty() {
            return precio;
        }

        public StringProperty estadoProperty() {
            return estado;
        }
    }

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(String[] args) {
        launch(args);
    }
}
