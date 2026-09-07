package com.ejemplo.dashboard;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/ejemplo/dashboard/main-view.fxml"));
        Scene scene = new Scene(loader.load(), 1200, 800);
        
        // Estilos CSS (opcional)
        scene.getStylesheets().add(getClass().getResource("/com/ejemplo/dashboard/styles/style.css").toExternalForm());
        
        primaryStage.setTitle("🚀 Mi Dashboard - JavaFX Pro");
        primaryStage.setScene(scene);
        primaryStage.setMinWidth(900);
        primaryStage.setMinHeight(600);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}