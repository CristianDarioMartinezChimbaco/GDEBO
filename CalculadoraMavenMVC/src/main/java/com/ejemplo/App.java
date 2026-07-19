package com.ejemplo;

import com.ejemplo.controller.UsuarioControlador;
import com.ejemplo.view.UsuarioVista;

public class App {
    public static void main(String[] args) {
        UsuarioVista vista = new UsuarioVista();
        UsuarioControlador controlador = new UsuarioControlador(vista);
        controlador.ejecutar();
    }
}
