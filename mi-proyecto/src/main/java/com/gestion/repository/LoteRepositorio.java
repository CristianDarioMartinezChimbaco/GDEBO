package com.gestion.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.gestion.model.Lote;

public class LoteRepositorio {

    private static final String[] CAMPOS = {
        "id",
        "id_producto",
        "lote",
        "fecha_registro",
        "fecha_vencimiento",
        "cantidad_entrada",
        "precio_compra",
        "activo"
    };

    // Create
    private static final String CREAR_TABLA = "CREATE TABLE IF NOT EXISTS lote ( "
        + "id INTEGER PRIMARY KEY, "
        + "id_producto INTEGER, "
        + "lote TEXT NOT NULL, "
        + "fecha_registro TEXT NOT NULL, "
        + "fecha_vencimiento TEXT NOT NULL, "
        + "cantidad_entrada REAL NOT NULL, "
        + "precio_compra REAL NOT NULL, "
        + "activo INTEGER NOT NULL DEFAULT 1 CHECK (activo IN (0, 1)), "
        + "FOREIGN KEY (id_producto) REFERENCES producto(id) );"
    ;
    private static final String CONSULTA_INSERTAR = "INSERT INTO lote "
        + "(id_producto, "
        + "lote, "
        + "fecha_registro, "
        + "fecha_vencimiento, "
        + "cantidad_entrada, "
        + "precio_compra) "
        + "VALUES (?, ?, ?, ?, ?, ?);"
    ;
    // Read
    private static final String CONSULTA_TODO = "SELECT "
        + "id, id_producto, lote, fecha_registro, "
        + "fecha_vencimiento, cantidad_entrada, precio_compra "
        + "FROM lote WHERE activo = 1;"
    ;
    // Update
    private static final String CONSULTA_ACTUALIZAR = "UPDATE lote "
        + "SET id_producto = ?, "
        + "lote = ?, "
        + "fecha_registro = ?, "
        + "fecha_vencimiento = ?, "
        + "cantidad_entrada = ?, "
        + "precio_compra = ? "
        + "WHERE id = ?;"
    ;
    // Delete
    private static final String CONSULTA_BORRAR = "UPDATE lote SET activo = 0 WHERE id = ?;";
    // Constructor
    public LoteRepositorio() {
        generarTabla();
    }

    // Metodos

    // Create
    private void generarTabla() {
        try (
            Connection conexion = ConexionBaseDatos.conectar();
            Statement sentencia = conexion.createStatement()
        ) {
            sentencia.execute(CREAR_TABLA);
        } catch (SQLException e) {
            throw new RuntimeException(
                "No se pudo crear la tabla lote ", e
            );
        }
    }

    public void agregarLote(Lote lote) {
        try (
            Connection conexion = ConexionBaseDatos.conectar();
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(CONSULTA_INSERTAR)
        ) {
            sentenciaPreparada.setInt(1, lote.conseguirIdProducto());
            sentenciaPreparada.setString(2, lote.conseguirLote());
            sentenciaPreparada.setString(3, lote.conseguirFechaRegistro());
            sentenciaPreparada.setString(4, lote.conseguirFechaVencimiento());
            sentenciaPreparada.setDouble(5, lote.conseguirCantidadEntrada());
            sentenciaPreparada.setDouble(6, lote.conseguirPrecioCompra());
            sentenciaPreparada.executeUpdate();
        } catch (SQLException e) {
            System.out.print("ERROR SQL: " + e);
            throw new RuntimeException(
                "No se pudo agregar lote ", e
            );
        }
    }

    // Read

    public ObservableList<Lote> consultarTodo() {
        return consultar(CONSULTA_TODO);
    }

    public ObservableList<String> consultarColumnaIdProducto() {
        return consultarColumna(CAMPOS[1]);
    }

    public ObservableList<String> consultarColumnaLote() {
        return consultarColumna(CAMPOS[2]);
    }

    public ObservableList<String> consultarColumnaFechaRegistro() {
        return consultarColumna(CAMPOS[3]);
    }

    public ObservableList<String> consultarColumnaFechaVencimiento() {
        return consultarColumna(CAMPOS[4]);
    }

    public ObservableList<String> consultarColumnaCantidadEntrada() {
        return consultarColumna(CAMPOS[5]);
    }

    public ObservableList<String> consultarColumnaPrecioCompra() {
        return consultarColumna(CAMPOS[6]);
    }

    private ObservableList<String> consultarColumna(String nombreColumna) {
        ObservableList<String> columna = FXCollections.observableArrayList();
        String consultaFinal = "SELECT "
            + nombreColumna
            + " FROM lote WHERE activo = 1;";
        try (
            Connection conexion = ConexionBaseDatos.conectar();
            Statement sentencia = conexion.createStatement();
            ResultSet conjuntoResultados = sentencia.executeQuery(consultaFinal)
        ) {
            while (conjuntoResultados.next()) {
                columna.add(conjuntoResultados.getString(nombreColumna));
            }
        } catch (SQLException e) {
            throw new RuntimeException(
                "Error al consultar la columna "
                + nombreColumna,
                e
            );
        }

        return columna;
    }

    private ObservableList<Lote> consultar(String consultaFinal) {
        ObservableList<Lote> lotes =
            FXCollections.observableArrayList();
        try (
            Connection conexion = ConexionBaseDatos.conectar();
            Statement sentencia = conexion.createStatement();
            ResultSet conjuntoResultados = sentencia.executeQuery(consultaFinal)
        ) {
            while (conjuntoResultados.next()) {
                Lote lote = new Lote();
                lote.colocarId(conjuntoResultados.getInt("id"));
                lote.colocarIdProducto(conjuntoResultados.getInt("id_producto"));
                lote.colocarLote(conjuntoResultados.getString("lote"));
                lote.colocarFechaRegistro(conjuntoResultados.getString("fecha_registro"));
                lote.colocarFechaVencimiento(conjuntoResultados.getString("fecha_vencimiento"));
                lote.colocarCantidadEntrada(conjuntoResultados.getDouble("cantidad_entrada"));
                lote.colocarPrecioCompra(conjuntoResultados.getDouble("precio_compra"));
                lotes.add(lote);
            }
        } catch (SQLException e) {
            throw new RuntimeException(
                "Error al consultar lotes ", e
            );
        }
        return lotes;
    }

    // Update
    public void editarLote(Lote lote) {
        try (
            Connection conexion = ConexionBaseDatos.conectar();
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(CONSULTA_ACTUALIZAR)
        ) {
            sentenciaPreparada.setInt(1, lote.conseguirIdProducto());
            sentenciaPreparada.setString(2, lote.conseguirLote());
            sentenciaPreparada.setString(3, lote.conseguirFechaRegistro());
            sentenciaPreparada.setString(4, lote.conseguirFechaVencimiento());
            sentenciaPreparada.setDouble(5, lote.conseguirCantidadEntrada());
            sentenciaPreparada.setDouble(6, lote.conseguirPrecioCompra());
            sentenciaPreparada.setInt(7, lote.conseguirId());
            sentenciaPreparada.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(
                "Error al editar lote ", e
            );
        }
    }

    // Delete
    public void borrarLote(Integer id) {
        try (
            Connection conexion = ConexionBaseDatos.conectar();
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(CONSULTA_BORRAR)
        ) {
            sentenciaPreparada.setInt(1, id);
            sentenciaPreparada.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(
                "Error al borrar lote ", e
            );
        }
    }
}