package com.gestion.view;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

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

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import com.gestion.model.Producto;
import com.gestion.model.Usuario;

public class ProductoVista {

    public void mostrarProductos(List<Producto> producto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mostrarProductos'");
    }

    public void mostrarMensaje(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mostrarMensaje'");
    }

    public Producto pedirDatosProducto() {
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        return new Producto(nombre, email);
    }

	public int leerOpcion() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'leerOpcion'");
	}

    public void mostrarMenu() {
        System.out.println("\n=== GESTIÓN DE USUARIOS ===");
        System.out.println("1. Crear usuario");
        System.out.println("2. Listar usuarios");
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
