package com.gestion;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;

public class Aplicacion extends Application{

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
            getClass().getResource("/com/gestion/view/Producto.fxml")
        );

        Scene scene = new Scene(loader.load());

        stage.setTitle("Gestión de Productos");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        
        launch(args);
        //ProductoRepositorio productoRepositorio = new ProductoRepositorio();
        //ProductoControlador controlador = new ProductoControlador(productoRepositorio);
        //controlador.listarProductosRepositorio();
        /*
        ProductoVista productoVista = new ProductoVista();
        ProductoRepositorio productoRepositorio = new ProductoRepositorio();
        ProductoControlador controlador = new ProductoControlador(productoVista, productoRepositorio);
        controlador.ejecutar();
        */
    }
}
