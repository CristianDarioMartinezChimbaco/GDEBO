package com.ejemplo.view;

import com.ejemplo.model.Usuario;
import java.util.List;
import java.util.Scanner;

public class UsuarioVista {
    private Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        System.out.println("\n=== GESTIÓN DE USUARIOS ===");
        System.out.println("1. Crear usuario");
        System.out.println("2. Listar usuarios");
        System.out.println("3. Salir");
        System.out.print("Elige una opción: ");
    }

    public Usuario pedirDatosUsuario() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        return new Usuario(nombre, email);
    }

    public void mostrarUsuarios(List<Usuario> usuarios) {
        if (usuarios.isEmpty()) {
            System.out.println("No hay usuarios registrados.");
        } else {
            System.out.println("\n--- LISTA DE USUARIOS ---");
            for (int i = 0; i < usuarios.size(); i++) {
                System.out.println((i+1) + ". " + usuarios.get(i));
            }
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
