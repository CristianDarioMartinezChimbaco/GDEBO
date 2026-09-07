package com.gestion;

import com.gestion.controller.*;
import com.gestion.model.*;
import com.gestion.repository.*;
import com.gestion.view.*;

public class Aplicacion {
    public static void main(String[] args) {
        ProductoVista productoVista = new ProductoVista();
        ProductoRepositorio productoRepositorio = new ProductoRepositorio();
        ProductoControlador controlador = new ProductoControlador(productoVista, productoRepositorio);
        controlador.ejecutar();
    }
}
