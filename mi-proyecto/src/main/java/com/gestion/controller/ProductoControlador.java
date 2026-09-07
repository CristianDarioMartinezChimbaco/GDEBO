package com.gestion.controller;

import java.util.ArrayList;
import java.util.List;

import com.gestion.model.Producto;
import com.gestion.model.Usuario;
import com.gestion.repository.ProductoRepositorio;
import com.gestion.view.ProductoVista;

public class ProductoControlador {
    private List<Producto> productos = new ArrayList<>();
    private ProductoVista productoVista;
    private ProductoRepositorio productoRepositorio;
    // Constructor
    public ProductoControlador(ProductoVista productoVista) {
        this.productoVista = productoVista;
    }

    public void ejecutar() {


        int opcion;
        do {
            productoVista.mostrarMenu();
            opcion = productoVista.leerOpcion();

            switch (opcion) {
                case 1 -> crearProducto();
                case 2 -> listarProductos();
                case 3 -> productoVista.mostrarMensaje("¡Hasta luego!");
                default -> productoVista.mostrarMensaje("Opción inválida.");
            }
        } while (opcion != 3);
    }

    private void crearProducto() {
        Producto nuevoProducto = productoVista.pedirDatosProducto();
        productos.add(nuevoProducto);
        productoVista.mostrarMensaje("Producto creado exitosamente.");
    }

    private void listarProductos() {

        productoVista.mostrarProductos(productos);
    }
}
