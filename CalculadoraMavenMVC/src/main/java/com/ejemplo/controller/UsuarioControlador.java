package com.ejemplo.controller;

import com.ejemplo.model.Usuario;
import com.ejemplo.view.UsuarioVista;
import java.util.ArrayList;
import java.util.List;

public class UsuarioControlador {
    private List<Usuario> usuarios = new ArrayList<>();
    private UsuarioVista vista;

    public UsuarioControlador(UsuarioVista vista) {
        this.vista = vista;
    }

    public void ejecutar() {
        int opcion;
        do {
            vista.mostrarMenu();
            opcion = vista.leerOpcion();

            switch (opcion) {
                case 1 -> crearUsuario();
                case 2 -> listarUsuarios();
                case 3 -> vista.mostrarMensaje("¡Hasta luego!");
                default -> vista.mostrarMensaje("Opción inválida.");
            }
        } while (opcion != 3);
    }

    private void crearUsuario() {
        Usuario nuevo = vista.pedirDatosUsuario();
        usuarios.add(nuevo);
        vista.mostrarMensaje("✅ Usuario creado exitosamente.");
    }

    private void listarUsuarios() {
        vista.mostrarUsuarios(usuarios);
    }
}
