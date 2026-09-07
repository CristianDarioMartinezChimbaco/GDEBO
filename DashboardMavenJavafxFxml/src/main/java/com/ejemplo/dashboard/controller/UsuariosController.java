package com.ejemplo.dashboard.controller;

import com.ejemplo.dashboard.model.Usuario;
import com.ejemplo.dashboard.util.DataGenerator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class UsuariosController {

    @FXML
    private TableView<Usuario> tablaUsuarios;

    @FXML
    private TableColumn<Usuario, Integer> colIdUsuario;

    @FXML
    private TableColumn<Usuario, String> colNombreUsuario;

    @FXML
    private TableColumn<Usuario, String> colEmailUsuario;

    @FXML
    private TableColumn<Usuario, String> colRolUsuario;

    @FXML
    private TableColumn<Usuario, Boolean> colActivoUsuario;


    private ObservableList<Usuario> usuariosData;


    @FXML
    public void initialize() {

        usuariosData = FXCollections.observableArrayList(
                DataGenerator.generarUsuarios()
        );

        configurarTablaUsuarios();

    }


    private void configurarTablaUsuarios() {

        colIdUsuario.setCellValueFactory(
                new PropertyValueFactory<>("id")
        );

        colNombreUsuario.setCellValueFactory(
                new PropertyValueFactory<>("nombre")
        );

        colEmailUsuario.setCellValueFactory(
                new PropertyValueFactory<>("email")
        );

        colRolUsuario.setCellValueFactory(
                new PropertyValueFactory<>("rol")
        );

        colActivoUsuario.setCellValueFactory(
                new PropertyValueFactory<>("activo")
        );

        tablaUsuarios.setItems(usuariosData);

    }
}