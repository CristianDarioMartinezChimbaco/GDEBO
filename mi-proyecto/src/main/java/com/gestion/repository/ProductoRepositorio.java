package com.gestion.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.gestion.model.Producto;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductoRepositorio {

    private static final String[] CAMPOS = {"id",
        "codigo_barras",
        "nombre_producto",
        "marca",
        "id_categoria",
        "cantidad_producto",
        "unidad_medida",
        "unidad_agrupada",
        "precio_venta",
        "existencias",
        "minimo_existencias",
        "activo" 
    };

    // Create
    private static final String CREAR_TABLA = "CREATE TABLE IF NOT EXISTS producto ( "
        + "id INTEGER PRIMARY KEY, "
        + "codigo_barras TEXT UNIQUE, "
        + "nombre_producto TEXT NOT NULL, "
        + "marca TEXT NOT NULL, "
        + "id_categoria INTEGER NOT NULL, "
        + "cantidad_producto REAL NOT NULL, "
        + "unidad_medida TEXT, "
        + "unidad_agrupada INTEGER, "
        + "precio_venta REAL NOT NULL, "
        + "existencias REAL NOT NULL, "
        + "minimo_existencias REAL NOT NULL, "
        + "activo INTEGER NOT NULL DEFAULT 1 CHECK (activo IN (0, 1) ), "
        + "FOREIGN KEY (id_categoria) REFERENCES categoria(id), "
        + "FOREIGN KEY (unidad_agrupada) REFERENCES producto(id) );"
    ;

    private static final String CONSULTA_INSERTAR = "INSERT INTO producto "
        + "(codigo_barras, "
        + "nombre_producto, "
        + "marca, "
        + "id_categoria, "
        + "cantidad_producto, "
        + "unidad_medida, "
        + "unidad_agrupada, "
        + "precio_venta, "
        + "existencias, "
        + "minimo_existencias) "
        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);"
    ;

    // Read
    private static final String CONSULTA_TODO = "SELECT " 
        + "id, codigo_barras, nombre_producto, marca, id_categoria, cantidad_producto, "
        + "unidad_medida, unidad_agrupada, precio_venta, existencias, "
        + "minimo_existencias FROM producto WHERE activo = 1;";


/*
SELECT
    p.*,
    agrupado.codigo_barras || ' - ' ||
    agrupado.nombre_producto || ' - ' ||
    agrupado.marca 
    AS codigo_nombre_marca_unidad_agrupada
FROM producto p
LEFT JOIN producto agrupado
    ON p.unidad_agrupada = agrupado.id
WHERE p.activo = 1;
*/
    
    private static final String CONSULTA_PRODUCTO = "SELECT " 
        + "id, codigo_barras, nombre_producto, marca, id_categoria, cantidad_producto, "
        + "unidad_medida, unidad_agrupada, precio_venta, existencias, "
        + "minimo_existencias FROM producto WHERE activo = 1 AND id = ?;";

    // Update
    private static final String CONSULTA_ACTUALIZAR = "UPDATE producto " 
        + "SET codigo_barras = ?, "
        + "nombre_producto = ?, "
        + "marca = ?,"
        + "id_categoria = ?, "
        + "cantidad_producto = ?, "
        + "unidad_medida = ?, "
        + "unidad_agrupada = ?, "
        + "precio_venta = ?, "
        + "existencias = ?, " 
        + "minimo_existencias = ? " 
        + "WHERE id = ?;"
    ; 

    // Delete    
    private static final String CONSULTA_BORRAR = "UPDATE producto SET activo = 0 WHERE id = ?;";

    // Constructor
    public ProductoRepositorio(){
        generarTabla();
    }

    // Metodos
    // Create
    private void generarTabla(){
        try (
            Connection conexion = ConexionBaseDatos.conectar();
            Statement sentencia = conexion.createStatement()) {
            sentencia.execute(CREAR_TABLA);
        } catch (SQLException e) {
            throw new RuntimeException("No se pudo crear la tabla producto ", e);
        }
    }

    public void agregarProducto(Producto producto) {
        try (
            Connection conexion = ConexionBaseDatos.conectar();
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(CONSULTA_INSERTAR)
        ) {      
            sentenciaPreparada.setString(1, producto.conseguirCodigoBarras());
            sentenciaPreparada.setString(2, producto.conseguirNombre());
            sentenciaPreparada.setString(3, producto.conseguirMarca());
            sentenciaPreparada.setInt(4, producto.conseguirCategoria());
            sentenciaPreparada.setObject(5, producto.conseguirCantidadProducto());
            sentenciaPreparada.setString(6, producto.conseguirUnidadMedida());
            sentenciaPreparada.setObject(7, producto.conseguirUnidadAgrupada());
            sentenciaPreparada.setDouble(8, producto.conseguirPrecio());
            sentenciaPreparada.setDouble(9, producto.conseguirExistencias());
            sentenciaPreparada.setDouble(10, producto.conseguirMinimoExistencias());
            sentenciaPreparada.executeUpdate();
        } catch (SQLException e) {
            System.out.print("ERROR SQL: " + e);
            throw new RuntimeException("No se pudo agregar producto ", e);
        }
    }

    // Read       
    public ObservableList<Producto> consultarTodo() {
        return consultar(CONSULTA_TODO);
    }

    public ObservableList<String> consultarColumnaId(){
        return consultarColumna(CAMPOS[0]);
    }

    public ObservableList<String> consultarColumnaCodigoBarras(){
        return consultarColumna(CAMPOS[1]);
    }

    public ObservableList<String> consultarColumnaNombreProducto(){
        return consultarColumna(CAMPOS[2]);
    }

    public ObservableList<String> consultarColumnaMarca(){
        return consultarColumna(CAMPOS[3]);
    }

    public ObservableList<String> consultarColumnaCategoria(){
        return consultarColumna(CAMPOS[4]);
    }

    public ObservableList<String> consultarColumnaCantidadProducto(){
        return consultarColumna(CAMPOS[5]);
    }

    public ObservableList<String> consultarColumnaUnidadMedida(){
        return consultarColumna(CAMPOS[6]);
    }

    public ObservableList<String> consultarColumnaUnidadAgrupada(){
        return consultarColumna(CAMPOS[7]);
    }

    public ObservableList<String> consultarColumnaPrecio(){
        return consultarColumna(CAMPOS[8]);
    }

    public ObservableList<String> consultarColumnaExistencias(){
        return consultarColumna(CAMPOS[9]);
    }

    public ObservableList<String> consultarColumnaMinimoExistencias(){
        return consultarColumna(CAMPOS[10]);
    }

    private ObservableList<String> consultarColumna(String nombreColumna){
        ObservableList<String> columna = FXCollections.observableArrayList();
        String consultaFinal = "SELECT " 
            + nombreColumna
            + " FROM producto WHERE activo = 1; "
        ;
        try (
            Connection conexion = ConexionBaseDatos.conectar();
            Statement sentencia = conexion.createStatement();
            ResultSet conjuntoResultados = sentencia.executeQuery(consultaFinal)
        ) {
            while (conjuntoResultados.next()) {
                columna.add(conjuntoResultados.getString(nombreColumna));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar la columna " + nombreColumna, e);
        }
        return columna;
    }

    public Producto consultarProducto(int id){
        Producto producto = new Producto();
        try (
            Connection conexion = ConexionBaseDatos.conectar();
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(CONSULTA_PRODUCTO)
        ) {
            sentenciaPreparada.setInt(1, id);
            try (ResultSet conjuntoResultados = sentenciaPreparada.executeQuery()) {
                if (conjuntoResultados.next()) {
                    producto = convertirProducto(conjuntoResultados);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar producto ", e);
        }
        return producto;
    }

    private ObservableList<Producto> consultar(String consultaFinal){
        ObservableList<Producto> productos = FXCollections.observableArrayList();
        try (
            Connection conexion = ConexionBaseDatos.conectar();
            Statement sentencia = conexion.createStatement();
            ResultSet conjuntoResultados = sentencia.executeQuery(consultaFinal)
        ) {
            while (conjuntoResultados.next()) {
                productos.add(convertirProducto(conjuntoResultados));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al consultar productos ", e);
        }
        return productos;
    }

    private Producto convertirProducto(ResultSet conjuntoResultados) throws SQLException {
        Producto producto = new Producto();
        producto.colocarId(conjuntoResultados.getInt("id"));
        producto.colocarCodigoBarras(conjuntoResultados.getString("codigo_barras"));
        producto.colocarNombre(conjuntoResultados.getString("nombre_producto"));
        producto.colocarMarca(conjuntoResultados.getString("marca"));
        producto.colocarCategoria(conjuntoResultados.getInt("id_categoria"));
        producto.colocarCantidadProducto(conjuntoResultados.getDouble("cantidad_producto"));
        producto.colocarUnidadMedida(conjuntoResultados.getString("unidad_medida"));
        producto.colocarUnidadAgrupada((Integer) conjuntoResultados.getObject("unidad_agrupada"));
        producto.colocarPrecio(conjuntoResultados.getDouble("precio_venta"));
        producto.colocarExistencias(conjuntoResultados.getDouble("existencias"));
        producto.colocarMinimoExistencias(conjuntoResultados.getDouble("minimo_existencias"));
        return producto;
    }
    // Update
    public void editarProducto(Producto producto) {
        try (
            Connection conexion = ConexionBaseDatos.conectar();
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(CONSULTA_ACTUALIZAR)
        ) {
            sentenciaPreparada.setString(1, producto.conseguirCodigoBarras());
            sentenciaPreparada.setString(2, producto.conseguirNombre());
            sentenciaPreparada.setString(3, producto.conseguirMarca());
            sentenciaPreparada.setInt(4, producto.conseguirCategoria());
            sentenciaPreparada.setDouble(5, producto.conseguirCantidadProducto());
            sentenciaPreparada.setString(6, producto.conseguirUnidadMedida());
            sentenciaPreparada.setObject(7, producto.conseguirUnidadAgrupada());
            sentenciaPreparada.setDouble(8, producto.conseguirPrecio());
            sentenciaPreparada.setDouble(9, producto.conseguirExistencias());
            sentenciaPreparada.setDouble(10, producto.conseguirMinimoExistencias());
            sentenciaPreparada.setInt(11, producto.conseguirId());  
            sentenciaPreparada.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al editar producto ", e);
        }
    }

    // Delete
    public void borrarProducto(Integer id) {
        try ( 
            Connection conexion = ConexionBaseDatos.conectar();
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(CONSULTA_BORRAR)
        ) { 
            sentenciaPreparada.setInt(1, id); 
            sentenciaPreparada.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al borrar producto ", e);
        }
    }

}