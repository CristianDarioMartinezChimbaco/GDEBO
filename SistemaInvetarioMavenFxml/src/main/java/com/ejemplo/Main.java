package main.java.com.ejemplo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
                Main.class.getResource(
                        "/com/ejemplo/view/dashboard.fxml"
                )
        );

        Scene scene = new Scene(loader.load());

        scene.getStylesheets().add(
                Main.class.getResource(
                        "/com/ejemplo/css/style.css"
                ).toExternalForm()
        );

        stage.setTitle("Sistema de Inventario - Don Edgar");

        stage.setScene(scene);

        stage.setMinWidth(1100);
        stage.setMinHeight(700);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}