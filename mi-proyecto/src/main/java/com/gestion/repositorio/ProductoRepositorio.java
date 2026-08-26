package com.gestion.repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.gestion.modelo.Producto;



public class ProductoRepositorio {

    // Create
    private String crearTabla = "CREATE TABLE IF NOT EXISTS producto ( "
        + "id INTEGER PRIMARY KEY, "
        + "codigo_barras TEXT UNIQUE, "
        + "nombre_producto TEXT NOT NULL, "
        + "marca TEXT NOT NULL, "
        + "cantidad_producto REAL NOT NULL, "
        + "unidad_medida TEXT, "
        + "unidad_agrupada INTEGER, "
        + "precio_venta REAL NOT NULL, "
        + "existencias INTEGER, "
        + "minimo_existencias INTEGER, "
        + "FOREIGN KEY (unidad_agrupada) REFERENCES producto(id) "
        + "); "
    ;
    private String insertar = "INSERT INTO producto VALUES "
        + "(NULL, ?, ?, ?, ?, ?, ?, ?, ?, ?,); "
    ;
    // Read
    private String consulta = "SELECT * FROM producto; ";
    // Update
    private String actualizar = "UPDATE producto " 
        + "SET codigo_barras = ?, "
        + "nombre_producto = ?, "
        + "marca = ?,"
        + "cantidad_producto = ?, "
        + "unidad_medida = ?, "
        + "unidad_agrupada = ?, "
        + "precio_venta = ?, "
        + "existencias = ?, " 
        + "minimo_existencias = ? " 
        + "WHERE id = ?; "
    ; 
    // Delete    
    private String borrar = "DELETE FROM producto WHERE id = ?; ";
    // Getters

    // Setters

    // Metodos
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
            sentenciaPreparada.setString(1, producto.conseguirCodigoBarras());
            sentenciaPreparada.setString(2, producto.conseguirNombre());
            sentenciaPreparada.setString(3, producto.conseguirMarca());
            sentenciaPreparada.setDouble(4, producto.conseguirCantidadProducto());
            sentenciaPreparada.setString(5, producto.conseguirUnidadMedida());
            sentenciaPreparada.setInt(6, producto.conseguirUnidadAgrupada());
            sentenciaPreparada.setDouble(7, producto.conseguirPrecio());
            sentenciaPreparada.setInt(8, producto.conseguirExistencias());
            sentenciaPreparada.setInt(9, producto.conseguirMinimoExistencias());
            sentenciaPreparada.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void consultaEstrella(){
        try (
            Connection conexion = Conexion.conectar();
            ResultSet rs = conexion.createStatement().executeQuery(consulta)
        ) {
            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " - " +
                    rs.getString("nombre") + " - $" +
                    rs.getDouble("precio")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String crearFiltro(Producto producto) {
        String resultado = "SELECT * FROM producto WHERE 1=1 ";
        if (producto.conseguirId() >= 0) {
            resultado += "AND id = ? ";
        }
        if (producto.conseguirCodigoBarras() != null
                && !producto.conseguirCodigoBarras().isBlank()) {
            resultado += "AND codigo_barras = ? ";
        }
        if (producto.conseguirNombre() != null
                && !producto.conseguirNombre().isBlank()) {
            resultado += "AND nombre_producto = ? ";
        }
        if (producto.conseguirMarca() != null
                && !producto.conseguirMarca().isBlank()) {
            resultado += "AND marca = ? ";
        }
        if (producto.conseguirCantidadProducto() >= 0) {
            resultado += "AND cantidad_producto = ? ";
        }
        if (producto.conseguirUnidadMedida() != null
                && !producto.conseguirUnidadMedida().isBlank()) {
            resultado += "AND unidad_medida = ? ";
        }
        if (producto.conseguirUnidadAgrupada() >= 0) {
            resultado += "AND unidad_agrupada = ? ";
        }
        if (producto.conseguirPrecio() >= 0) {
            resultado += "AND precio_venta = ? ";
        }
        if (producto.conseguirExistencias() >= 0) {
            resultado += "AND existencias = ? ";
        }
        if (producto.conseguirMinimoExistencias() >= 0) {
            resultado += "AND minimo_existencias = ? ";
        }
        return resultado + "; ";
    }

    public void consultaFiltro(Producto producto){
        String consultar = selecionar 
            + estrella 
            + tabla
            + cerrarConsulta;
        try (
            Connection conexion = Conexion.conectar();
            ResultSet rs = conexion.createStatement().executeQuery(consultar)
        ) {
            while (rs.next()) {
                System.out.println(
                    rs.getInt("id") + " - " +
                    rs.getString("nombre") + " - $" +
                    rs.getDouble("precio")
                );
            }
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