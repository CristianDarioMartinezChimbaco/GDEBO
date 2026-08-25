package com.gestion.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.gestion.modelo.Producto;



public class ProductoRepositorio {

    private String crearTabla = "CREATE TABLE IF NOT EXISTS producto ("
        + "id INTEGER PRIMARY KEY, "
        + "codigo_barras INTEGER UNIQUE NOT NULL, "
        + "nombre_producto TEXT NOT NULL, "
        + "marca TEXT NOT NULL, "
        + "cantidad_producto REAL NOT NULL, "
        + "unidad_medida TEXT, "
        + "unidad_agrupada INTEGER, "
        + "precio_venta REAL NOT NULL, "
        + "existencias INTEGER, "
        + "minimo_existencias INTEGER, "
        + "FOREIGN KEY (unidad_agrupada) REFERENCES producto(id)"
        + ");"
    ;
    private String actualizar = "UPDATE producto" 
        + "SET codigo_barras = ?, "
        + "nombre_producto = ?, "
        + "marca = ?,"
        + "cantidad_producto = ?, "
        + "unidad_medida = ?, "
        + "unidad_agrupada = ?, "
        + "precio_venta = ?, "
        + "existencias = ?, " 
        + "minimo_existencias = ? " 
        + "WHERE id = ?"
    ; 
    private String insertar = "INSERT INTO producto VALUES "
        + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?,)"
    ;
    private String consultar = "SELECT * FROM producto;";
    
    // Getters

    // Setters

    public void generarTabla(){
        try (Connection conexion = Conexion.conectar()){
            conexion.createStatement().execute(crearTabla);
        } 
        catch (Exception e){
            System.out.print("ERROR: " + e);
        }
    }

    public void agregarProducto(Producto producto) {
        try (
            Connection conexion = Conexion.conectar();
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(insertar)
        ) {
            sentenciaPreparada.setString(1, producto);
            sentenciaPreparada.setString(2, producto);
            sentenciaPreparada.setString(3, producto);
            sentenciaPreparada.setDouble(4, producto);
            sentenciaPreparada.setDouble(5, producto);
            sentenciaPreparada.setDouble(6, producto);
            sentenciaPreparada.setDouble(7, producto);
            sentenciaPreparada.setDouble(8, producto);
            sentenciaPreparada.setDouble(9, producto);
            sentenciaPreparada.setDouble(10, producto);
            sentenciaPreparada.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ejecutar() {
        try (Connection conexion = Conexion.conectar()) {

            // Crear tabla
            conexion.createStatement().execute(crearTabla);

            // Insertar producto
            try (PreparedStatement ps = conexion.prepareStatement(insertar)) {

                ps.setString(1, "Arroz Diana");
                ps.setDouble(2, 5000);

                ps.executeUpdate();
            }

            // Consultar
            try (ResultSet rs =
                            conexion.createStatement().executeQuery(consultar)) {

                while (rs.next()) {
                    System.out.println(
                            rs.getInt("id") + " - " +
                            rs.getString("nombre") + " - $" +
                            rs.getDouble("precio")
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


/* 
public class ProductoRepositorio {
    private Connection conexion;

    public ProductoRepositorio(Connection conexion) {
        this.conexion = conexion;
    }

    public void agregarProducto(String codigoBarras, String nombre, String marca, double precio) {
        String sql = "INSERT INTO productos (codigo_barras, nombre, marca, precio) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, codigoBarras);
            statement.setString(2, nombre);
            statement.setString(3, marca);
            statement.setDouble(4, precio);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet obtenerProductos() {
        String sql = "SELECT * FROM productos";
        try {
            PreparedStatement statement = conexion.prepareStatement(sql);
            return statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
*/