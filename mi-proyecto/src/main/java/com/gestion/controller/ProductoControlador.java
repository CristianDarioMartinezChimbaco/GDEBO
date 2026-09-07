package com.gestion.controller;

import java.util.ArrayList;
import java.util.List;

import com.gestion.model.Producto;
import com.gestion.repository.ProductoRepositorio;
import com.gestion.view.ProductoVista;

public class ProductoControlador {
    private List<Producto> productos = new ArrayList<>();
    private ProductoVista productoVista;
    private ProductoRepositorio productoRepositorio;
    // Constructor
    public ProductoControlador(ProductoVista productoVista, ProductoRepositorio productoRepositorio) {
        this.productoVista = productoVista;
        this.productoRepositorio = productoRepositorio;
    }

    public void ejecutar() {
        // 
        int opcion;
        do {
            // Crear tabla productos si no existe
            crearTablaRepositorio();
            // cargar datos de la bd
            listarProductosRepositorio();
            productoVista.mostrarMenu();
            opcion = productoVista.leerOpcion();

            switch (opcion) {
                case 1 -> crearProducto();
                case 2 -> listarProductosVista();
                case 3 -> productoVista.mostrarMensaje("¡Hasta luego!");
                default -> productoVista.mostrarMensaje("Opción inválida.");
            }
        } while (opcion != 3);
    }

    private void crearProducto() {
        Producto nuevoProducto = productoVista.pedirDatosProducto();
        productoRepositorio.agregarProducto(nuevoProducto);
        productoVista.mostrarMensaje("Producto creado exitosamente.");
    }

    private void listarProductosRepositorio() {
        productos = productoRepositorio.consultarTodo();
    }

    private void listarProductosVista() {
        productoVista.mostrarProductos(productos);
    }

    private void crearTablaRepositorio() {
        productoRepositorio.generarTabla();
    }
}
