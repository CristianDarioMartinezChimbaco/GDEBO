package com.gestion;

import com.gestion.controller.ProductoControlador;
import com.gestion.model.*;
import com.gestion.view.*;

public class Aplicacion {
    public static void main(String[] args) {
        ProductoVista productoVista = new ProductoVista();
        ProductoControlador controlador = new ProductoControlador(productoVista);
        controlador.ejecutar();
    }
}
