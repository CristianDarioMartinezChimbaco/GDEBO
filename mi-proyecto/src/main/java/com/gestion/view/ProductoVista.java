package com.gestion.view;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
/*
import javafx.fxml.FXML;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.beans.property.ReadOnlyObjectWrapper;
*/
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import com.gestion.model.Producto;
import com.gestion.model.Usuario;

public class ProductoVista {

    private Scanner esc = new Scanner(System.in);

    public void mostrarProductos(List<Producto> productos) {
       if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
        } else {
            System.out.println("\n--- LISTA DE PRODUCTOS ---");
            for (int i = 0; i < productos.size(); i++) {
                System.out.println((i+1) + ". " + productos.get(i));
            }
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public Producto pedirDatosProducto() {
        Producto producto = new Producto();
        System.out.print("Codigo: ");
        producto.colocarCodigoBarras(esc.nextLine());
        System.out.print("Nombre: ");
        producto.colocarNombre(esc.nextLine());
        System.out.print("Marca: ");
        producto.colocarMarca(esc.nextLine());
        System.out.print("Cantidad de producto: ");
        producto.colocarCantidadProducto(esc.nextDouble());
        System.out.print("Unidad de medida: ");
        esc.nextLine();// Consumir el salto de línea pendiente
        producto.colocarUnidadMedida(esc.nextLine());
        System.out.print("Unidad agrupada: ");
        producto.colocarUnidadAgrupada(esc.nextInt());
        System.out.print("Precio: ");
        producto.colocarPrecio(esc.nextDouble());
        System.out.print("Existencias: ");
        producto.colocarExistencias(esc.nextDouble());
        System.out.print("Minimo de existencias: ");
        producto.colocarMinimoExistencias(esc.nextDouble());
        return producto;
    }

	public int leerOpcion() {
		try {
            return Integer.parseInt(esc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void mostrarMenu() {
        System.out.println("\n=== GESTIÓN DE PRODUCTOS ===");
        System.out.println("1. Crear producto");
        System.out.println("2. Listar productos");
        System.out.println("3. Salir");
        System.out.print("Elige una opción: ");
    }

    /*
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
    */
}
