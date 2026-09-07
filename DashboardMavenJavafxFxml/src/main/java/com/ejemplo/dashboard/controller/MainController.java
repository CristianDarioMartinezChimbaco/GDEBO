package com.ejemplo.dashboard.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainController {

    // ========================================
    // NAVBAR
    // ========================================

    @FXML
    private Label lblTitulo;

    @FXML
    private Label lblUsuario;

    @FXML
    private MenuButton menuOpciones;


    // ========================================
    // SIDEBAR
    // ========================================

    @FXML
    private VBox sidebar;

    @FXML
    private Button btnDashboard;

    @FXML
    private Button btnUsuarios;

    @FXML
    private Button btnVentas;

    @FXML
    private Button btnReportes;

    @FXML
    private Button btnConfiguracion;

    @FXML
    private TreeView<String> treeView;


    // ========================================
    // CONTENIDO PRINCIPAL
    // ========================================

    @FXML
    private StackPane contenidoPrincipal;


    // ========================================
    // INITIALIZE
    // ========================================

    @FXML
    public void initialize() {

        configurarNavbar();

        configurarSidebar();

        configurarTreeView();

        mostrarDashboard();
    }


    // ========================================
    // NAVBAR
    // ========================================

    private void configurarNavbar() {

        lblTitulo.setText("🚀 Mi Dashboard");

        lblUsuario.setText("👤 Administrador");
    }


    // ========================================
    // SIDEBAR
    // ========================================

    private void configurarSidebar() {

        btnDashboard.setOnAction(e ->
                mostrarDashboard()
        );

        btnUsuarios.setOnAction(e ->
                mostrarUsuarios()
        );

        btnVentas.setOnAction(e ->
                mostrarVentas()
        );

        btnReportes.setOnAction(e ->
                mostrarReportes()
        );

        btnConfiguracion.setOnAction(e ->
                mostrarConfiguracion()
        );
    }


    // ========================================
    // TREE VIEW
    // ========================================

    private void configurarTreeView() {

        TreeItem<String> root =
                new TreeItem<>("📂 Navegación");

        TreeItem<String> usuarios =
                new TreeItem<>("👥 Usuarios");

        TreeItem<String> ventas =
                new TreeItem<>("💰 Ventas");

        TreeItem<String> configuracion =
                new TreeItem<>("⚙ Configuración");


        root.getChildren().addAll(
                usuarios,
                ventas,
                configuracion
        );

        root.setExpanded(true);

        treeView.setRoot(root);

        treeView.setShowRoot(true);
    }


    // ========================================
    // DASHBOARD
    // ========================================

    private void mostrarDashboard() {

        cargarVista(
                "/com/ejemplo/dashboard/dashboard-view.fxml"
        );
    }


    // ========================================
    // USUARIOS
    // ========================================

    private void mostrarUsuarios() {

        cargarVista(
                "/com/ejemplo/dashboard/usuarios-view.fxml"
        );
    }


    // ========================================
    // VENTAS
    // ========================================

    private void mostrarVentas() {

        cargarVista(
                "/com/ejemplo/dashboard/ventas-view.fxml"
        );
    }


    // ========================================
    // REPORTES
    // ========================================

    private void mostrarReportes() {

        VBox contenido = new VBox(20);

        contenido.setStyle(
                "-fx-padding: 30;"
        );


        Label titulo =
                new Label("📊 Reportes");

        titulo.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;"
        );


        Label mensaje =
                new Label(
                        "Aquí aparecerán los reportes del sistema."
                );


        contenido.getChildren().addAll(
                titulo,
                mensaje
        );


        contenidoPrincipal
                .getChildren()
                .setAll(contenido);
    }


    // ========================================
    // CONFIGURACIÓN
    // ========================================

    private void mostrarConfiguracion() {

        VBox contenido = new VBox(20);

        contenido.setStyle(
                "-fx-padding: 30;"
        );


        Label titulo =
                new Label("⚙ Configuración");

        titulo.setStyle(
                "-fx-font-size: 28px;" +
                "-fx-font-weight: bold;"
        );


        Label mensaje =
                new Label(
                        "Configuración del sistema."
                );


        contenido.getChildren().addAll(
                titulo,
                mensaje
        );


        contenidoPrincipal
                .getChildren()
                .setAll(contenido);
    }


    // ========================================
    // CARGAR VISTAS
    // ========================================

    private void cargarVista(String ruta) {

        try {

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(ruta)
                    );


            Node vista = loader.load();


            contenidoPrincipal
                    .getChildren()
                    .setAll(vista);


        } catch (IOException e) {

            e.printStackTrace();

            mostrarAlerta(
                    "Error",
                    "No se pudo cargar la vista:\n" + ruta
            );
        }
    }


    // ========================================
    // ALERTA
    // ========================================

    private void mostrarAlerta(
            String titulo,
            String mensaje
    ) {

        Alert alert =
                new Alert(
                        Alert.AlertType.ERROR
                );


        alert.setTitle(titulo);

        alert.setHeaderText(null);

        alert.setContentText(mensaje);

        alert.showAndWait();
    }
}