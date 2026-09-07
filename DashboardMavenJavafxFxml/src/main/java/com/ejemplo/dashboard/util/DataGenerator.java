package com.ejemplo.dashboard.util;

import com.ejemplo.dashboard.model.Usuario;
import com.ejemplo.dashboard.model.Venta;
import java.util.ArrayList;
import java.util.List;

public class DataGenerator {
    
    public static List<Usuario> generarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario(1, "Ana García", "ana@email.com", "Administrador", true));
        usuarios.add(new Usuario(2, "Carlos López", "carlos@email.com", "Usuario", true));
        usuarios.add(new Usuario(3, "María Rodríguez", "maria@email.com", "Editor", false));
        usuarios.add(new Usuario(4, "Juan Pérez", "juan@email.com", "Usuario", true));
        usuarios.add(new Usuario(5, "Laura Martínez", "laura@email.com", "Administrador", true));
        usuarios.add(new Usuario(6, "Pedro Sánchez", "pedro@email.com", "Usuario", false));
        usuarios.add(new Usuario(7, "Sofía Torres", "sofia@email.com", "Editor", true));
        usuarios.add(new Usuario(8, "Miguel Ángel", "miguel@email.com", "Usuario", true));
        return usuarios;
    }
    
    public static List<Venta> generarVentas() {
        List<Venta> ventas = new ArrayList<>();
        ventas.add(new Venta(101, "Laptop Pro", 1200.00, "2024-01-15", "Completada"));
        ventas.add(new Venta(102, "Mouse Wireless", 45.50, "2024-01-16", "Pendiente"));
        ventas.add(new Venta(103, "Teclado Mecánico", 89.99, "2024-01-17", "Completada"));
        ventas.add(new Venta(104, "Monitor 27\"", 350.00, "2024-01-18", "Cancelada"));
        ventas.add(new Venta(105, "Disco SSD 1TB", 120.00, "2024-01-19", "Completada"));
        ventas.add(new Venta(106, "Memoria RAM 16GB", 85.00, "2024-01-20", "Completada"));
        ventas.add(new Venta(107, "Webcam HD", 55.00, "2024-01-21", "Pendiente"));
        ventas.add(new Venta(108, "Auriculares Gaming", 75.00, "2024-01-22", "Completada"));
        return ventas;
    }
}