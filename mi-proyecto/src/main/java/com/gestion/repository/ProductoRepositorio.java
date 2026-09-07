package com.gestion.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.gestion.model.Producto;

public class ProductoRepositorio {
    private static final String[] campos = {"id",
            "codigo_barras",
            "nombre_producto",
            "marca",
            "cantidad_producto",
            "unidad_medida",
            "unidad_agrupada",
            "precio_venta",
            "existencias",
            "minimo_existencias",
            "activo" 
    };
        // Create
    private static String crearTabla = "CREATE TABLE IF NOT EXISTS producto ( "
        + "id INTEGER PRIMARY KEY, "
        + "codigo_barras TEXT UNIQUE, "
        + "nombre_producto TEXT NOT NULL, "
        + "marca TEXT NOT NULL, "
        + "cantidad_producto REAL NOT NULL, "
        + "unidad_medida TEXT, "
        + "unidad_agrupada INTEGER, "
        + "precio_venta REAL NOT NULL, "
        + "existencias REAL, "
        + "minimo_existencias REAL, "
        + "activo INTEGER NOT NULL DEFAULT 1 CHECK (activo IN (0, 1)), "
        + "FOREIGN KEY (unidad_agrupada) REFERENCES producto(id)); "
    ;
    private String consultaInsertar = "INSERT INTO producto "
        + "(codigo_barras, "
        + "nombre_producto, "
        + "marca, "
        + "cantidad_producto, "
        + "unidad_medida, "
        + "unidad_agrupada, "
        + "precio_venta, "
        + "existencias, "
        + "minimo_existencias) "
        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?); "
    ;
        // Read
    private static String consultaTodo = "SELECT * FROM producto WHERE activo = 1; ";
    private static String consultaCodigoBarras = "SELECT nombre_producto FROM producto WHERE activo = 1; ";
    private static String consultaNombre = "SELECT * FROM producto WHERE activo = 1; ";
    private static String consultaMarca = "SELECT * FROM producto WHERE activo = 1; ";
    private static String consultaCantidad = "SELECT * FROM producto WHERE activo = 1; ";
    private static String consultaUnidadMedida = "SELECT * FROM producto WHERE activo = 1; ";
    private static String consultaUnidadAgrupada = "SELECT * FROM producto WHERE activo = 1; ";
    private static String consultaPrecio = "SELECT * FROM producto WHERE activo = 1; ";
        // Update
    private String consultaActualizar = "UPDATE producto " 
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
    private String consultaBorrar = "UPDATE producto SET activo = 0 WHERE id = ?; ";
    // Getters

    // Setters

    // Metodos

        // Create
     public void generarTabla(){
        try (Connection conexion = ConexionBaseDatos.conectar()){
            conexion.createStatement().execute(crearTabla);
        } catch (Exception e){
            System.out.println("Error al crear la tabla producto: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void agregarProducto(Producto producto) {
        try (
            Connection conexion = ConexionBaseDatos.conectar();
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(consultaInsertar)
        ) {      
            sentenciaPreparada.setString(1, producto.conseguirCodigoBarras()); // Empieza en 1
            sentenciaPreparada.setString(2, producto.conseguirNombre());
            sentenciaPreparada.setString(3, producto.conseguirMarca());
            sentenciaPreparada.setDouble(4, producto.conseguirCantidadProducto());
            sentenciaPreparada.setString(5, producto.conseguirUnidadMedida());
            sentenciaPreparada.setInt(6, producto.conseguirUnidadAgrupada());
            sentenciaPreparada.setDouble(7, producto.conseguirPrecio());
            sentenciaPreparada.setDouble(8, producto.conseguirExistencias());
            sentenciaPreparada.setDouble(9, producto.conseguirMinimoExistencias());
            sentenciaPreparada.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        // Read
    public String crearConsultaFiltro(String[] filtros) {
        ///////////////////////////////////////////////////////////////
        // OJO ESCAPAR FILTROS /////////////////////////////////////
        /////////////////////////////////////////////////////////////
        String resultado = "SELECT * FROM producto WHERE activo = 1 ";
        if (filtros != null && filtros.length == 10 ){
            for(int i = 0; i < 10; i++) {
                if (filtros[i] != null && !filtros[i].isBlank()) {
                    if (filtros[i].equals("NULL")){
                        resultado += "AND " 
                        + campos[i] 
                        + " IS NULL ";
                    } else {
                        resultado += "AND " 
                        + campos[i] 
                        + " = " 
                        + filtros[i]
                        + " ";
                    }
                }
            }
        } else {
            throw new IllegalArgumentException(
                "El arreglo de filtros debe contener exactamente 10 elementos");
        }
        return resultado + "; ";
    }

    public ArrayList<Producto> consultarTodo() {
        return consultar(consultaTodo);
    }

    public ArrayList<Producto> consultarFiltro(String[] consulta) {
        return consultar(crearConsultaFiltro(consulta));
    }

    public ArrayList<Producto> consultar(String consultaFinal){
        ArrayList<Producto> productos = new ArrayList<>();    
        try (
            Connection conexion = ConexionBaseDatos.conectar();
            ResultSet conjuntoResultados = conexion.createStatement().executeQuery(consultaFinal)
        ) {
            while (conjuntoResultados.next()) {
                Producto producto = new Producto();
                producto.colocarId(conjuntoResultados.getInt("id"));
                producto.colocarCodigoBarras(conjuntoResultados.getString("codigo_barras"));
                producto.colocarNombre(conjuntoResultados.getString("nombre_producto"));
                producto.colocarMarca(conjuntoResultados.getString("marca"));
                producto.colocarCantidadProducto(conjuntoResultados.getDouble("cantidad_producto"));
                producto.colocarUnidadMedida(conjuntoResultados.getString("unidad_medida"));
                producto.colocarUnidadAgrupada(conjuntoResultados.getInt("unidad_agrupada"));
                producto.colocarPrecio(conjuntoResultados.getDouble("precio_venta"));
                producto.colocarExistencias(conjuntoResultados.getDouble("existencias"));
                producto.colocarMinimoExistencias(conjuntoResultados.getDouble("minimo_existencias"));
                productos.add(producto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productos;
    }
        // Update
        // consultaFiltro(id) -> 
    public void EditarProducto(Producto producto) {
        try (
            Connection conexion = ConexionBaseDatos.conectar();
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(consultaActualizar)
        ) {
            sentenciaPreparada.setString(1, producto.conseguirCodigoBarras());
            sentenciaPreparada.setString(2, producto.conseguirNombre());
            sentenciaPreparada.setString(3, producto.conseguirMarca());
            sentenciaPreparada.setDouble(4, producto.conseguirCantidadProducto());
            sentenciaPreparada.setString(5, producto.conseguirUnidadMedida());
            sentenciaPreparada.setInt(6, producto.conseguirUnidadAgrupada());
            sentenciaPreparada.setDouble(7, producto.conseguirPrecio());
            sentenciaPreparada.setDouble(8, producto.conseguirExistencias());
            sentenciaPreparada.setDouble(9, producto.conseguirMinimoExistencias());
            sentenciaPreparada.setInt(10, producto.conseguirId());  
            sentenciaPreparada.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        // Delete
    public void BorrarProducto(Integer id) {
        try (
            Connection conexion = ConexionBaseDatos.conectar();
            PreparedStatement sentenciaPreparada = conexion.prepareStatement(consultaBorrar)
        ) { 
            sentenciaPreparada.setInt(1, id); 
            sentenciaPreparada.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}