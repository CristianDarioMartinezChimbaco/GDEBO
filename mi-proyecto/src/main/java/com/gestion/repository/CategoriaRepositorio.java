package com.gestion.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.gestion.model.Categoria;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CategoriaRepositorio {
    private static final String[] CAMPOS = {"id",
        "nombre_categoria",
        "activo"
    };
    private static final String CREAR_TABLA = "CREATE TABLE IF NOT EXISTS categoria ( "
            + "id INTEGER PRIMARY KEY, "
            + "nombre_categoria TEXT NOT NULL UNIQUE, "
            + "activo INTEGER NOT NULL DEFAULT 1 CHECK (activo IN (0, 1) ) );"
    ;
    private static final String CONSULTA_INSERTAR = "INSERT INTO categoria ("
            + "nombre_categoria"
            + ") VALUES (?);"
    ;
    private static final String CONSULTA_TODO = "SELECT "
            + String.join(", ", CAMPOS)
            + " FROM categoria "
            + "WHERE activo = 1;"
    ;
    private static final String CONSULTA_ACTUALIZAR = "UPDATE categoria SET "
            + "nombre_categoria = ? "
            + "WHERE id = ?;"
    ;
    private static final String CONSULTA_ELIMINAR = "UPDATE categoria SET "
            + "activo = 0 "
            + "WHERE id = ?;"
    ;

    public void generarTabla() {
        try (
            Connection conexion = ConexionBaseDatos.conectar();
            Statement sentencia = conexion.createStatement()
        ) {
            sentencia.execute(CREAR_TABLA);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean agregarCategoria(Categoria categoria) {
        try (
            Connection conexion = ConexionBaseDatos.conectar();
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(CONSULTA_INSERTAR)
        ) {
            sentenciaPreparada.setString(1, categoria.conseguirNombre());
            sentenciaPreparada.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ObservableList<Categoria> consultarTodo() {
        ObservableList<Categoria> categorias = FXCollections.observableArrayList();
        try (
                Connection conexion = ConexionBaseDatos.conectar();
                PreparedStatement sentenciaPreparada = conexion.prepareStatement(CONSULTA_TODO);
                ResultSet conjuntoResultados = sentenciaPreparada.executeQuery()
            ) {
            while (conjuntoResultados.next()) {
                Categoria categoria = new Categoria();
                categoria.colocarId(conjuntoResultados.getInt("id"));
                categoria.colocarNombre(conjuntoResultados.getString("nombre_categoria"));
                categoria.colocarActivo(conjuntoResultados.getInt("activo"));
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }

    public boolean editarCategoria(Categoria categoria) {
        try (
            Connection conexion = ConexionBaseDatos.conectar();
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(CONSULTA_ACTUALIZAR)
        ) {
            sentenciaPreparada.setString(1, categoria.conseguirNombre());
            sentenciaPreparada.setInt(2, categoria.conseguirId());
            sentenciaPreparada.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarCategoria(Integer id) {
        try (
            Connection conexion = ConexionBaseDatos.conectar();
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(CONSULTA_ELIMINAR)
        ) {
            sentenciaPreparada.setInt(1, id);
            sentenciaPreparada.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
