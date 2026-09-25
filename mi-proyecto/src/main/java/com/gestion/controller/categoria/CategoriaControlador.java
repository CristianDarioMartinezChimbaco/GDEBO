package com.gestion.controller.categoria;

import java.io.IOException;
import java.util.Optional;

import com.gestion.controller.producto.ProductoFormularioControlador;
import com.gestion.model.Categoria;
import com.gestion.model.Producto;
import com.gestion.repository.CategoriaRepositorio;

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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign.MaterialDesign;

public class CategoriaControlador {
    private CategoriaRepositorio categoriaRepositorio = new CategoriaRepositorio();
    private final ObservableList<Categoria> ListaCategorias = FXCollections.observableArrayList();
    @FXML private Button botonSalir;
    @FXML private TableView<Categoria> tablaCategorias;
    @FXML private TableColumn<Categoria, Integer> columnaId;   
    @FXML private TableColumn<Categoria, String> columnaCategoria;
    @FXML private TableColumn<Categoria, Void> columnaEditar;
    
    private void inicializarCategoriasTablaVista() {
        columnaCategoria.setCellValueFactory(
            celda -> new SimpleStringProperty(celda.getValue().conseguirNombre()));
        columnaId.setCellValueFactory(
            celda -> new SimpleObjectProperty<>(celda.getValue().conseguirId()));
    }

    private void cargarCategorias(){
        ListaCategorias.setAll(categoriaRepositorio.consultarTodo());
    }

    private void inicializarColumnaEdicion(){
        columnaEditar.setCellFactory(columna -> new TableCell<>(){
            private final Button botonEditar = new Button();
            {
                FontIcon iconoEditar = new FontIcon(MaterialDesign.MDI_PENCIL);
                botonEditar.setGraphic(iconoEditar);
                botonEditar.setOnAction(event -> {
                    Categoria categoria = getTableRow().getItem();
                    if (categoria != null){
                        editarCategoria(categoria);
                    }
                });
            }
            @Override
            protected void updateItem(Void item, boolean empty){
                super.updateItem(item, empty);
                if (empty){
                    setGraphic(null);
                } else {
                    setGraphic(botonEditar);
                }
            }
        });
    }

    private void editarCategoria(Categoria categoria) {
        Alert alerta = new Alert(AlertType.CONFIRMATION);
        alerta.setTitle("Editar Categoria");
        alerta.setHeaderText("Categoria nueva");
        alerta.setContentText("Nombre de la nueva categoria:");
        TextField txtNombreCategoria = new TextField();
        txtNombreCategoria.setText(categoria.conseguirNombre());
        alerta.getDialogPane().setContent(txtNombreCategoria);
        ButtonType botonContinuar = new ButtonType("Guardar", ButtonBar.ButtonData.OK_DONE);
        ButtonType botonCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        alerta.getButtonTypes().setAll(botonContinuar, botonCancelar);
        Optional<ButtonType> resultado = alerta.showAndWait();
        if (resultado.isPresent() && resultado.get() == botonContinuar){
            if (txtNombreCategoria.getText().trim().isEmpty()){
                new Alert(AlertType.ERROR,"Debe ingresar un nombre para la categoria.").showAndWait();
                return;
            }
            categoria.colocarNombre(txtNombreCategoria.getText().trim());
            CategoriaRepositorio catRepo = new CategoriaRepositorio();
            catRepo.editarCategoria(categoria);
        }
        cargarCategorias();
    }

    @FXML 
    public void initialize(){
        inicializarCategoriasTablaVista();
        inicializarColumnaEdicion();
        tablaCategorias.setItems(ListaCategorias);
        cargarCategorias();
    }

    @FXML 
    public void salir(){
        Stage ventana = (Stage) botonSalir.getScene().getWindow();
        ventana.close();
    }

    @FXML
    public void agregarCategoria(){
        Alert alerta = new Alert(AlertType.CONFIRMATION);
        alerta.setTitle("Agregar Categoria");
        alerta.setHeaderText("Categoria nueva");
        alerta.setContentText("Nombre de la nueva categoria:");
        TextField txtNombreCategoria = new TextField();
        txtNombreCategoria.setPromptText("Nombre");
        alerta.getDialogPane().setContent(txtNombreCategoria);
        ButtonType botonContinuar = new ButtonType("Guardar", ButtonBar.ButtonData.OK_DONE);
        ButtonType botonCancelar = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        alerta.getButtonTypes().setAll(botonContinuar, botonCancelar);
        Optional<ButtonType> resultado = alerta.showAndWait();
        if (resultado.isPresent() && resultado.get() == botonContinuar){
            if (txtNombreCategoria.getText().trim().isEmpty()){
                new Alert(AlertType.ERROR,"Debe ingresar un nombre para la categoria.").showAndWait();
                return;
            }
            Categoria nuevaCategoria = new Categoria();
            nuevaCategoria.colocarNombre(txtNombreCategoria.getText().trim());
            CategoriaRepositorio catRepo = new CategoriaRepositorio();
            catRepo.agregarCategoria(nuevaCategoria);
        }
        cargarCategorias();
    }
}
