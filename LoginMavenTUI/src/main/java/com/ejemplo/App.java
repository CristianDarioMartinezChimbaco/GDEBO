package com.ejemplo;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.util.ArrayList;
import java.util.List;

public class App {
    
    private static Screen screen;
    private static WindowBasedTextGUI gui;
    private static String currentUser = "";
    private static boolean loggedIn = false;
    private static Window mainWindow;
    
    // Datos de ejemplo
    private static List<Producto> productos = new ArrayList<>();
    private static List<Cliente> clientes = new ArrayList<>();
    
    static {
        // Productos
        productos.add(new Producto("Laptop", 1200.99, 15));
        productos.add(new Producto("Mouse", 25.50, 50));
        productos.add(new Producto("Teclado", 45.00, 30));
        productos.add(new Producto("Monitor", 350.00, 10));
        productos.add(new Producto("Auriculares", 80.00, 25));
        productos.add(new Producto("Webcam", 60.00, 18));
        productos.add(new Producto("Tablet", 300.00, 12));
        productos.add(new Producto("Impresora", 150.00, 8));
        
        // Clientes
        clientes.add(new Cliente("Juan Perez", "juan@mail.com", "123456789"));
        clientes.add(new Cliente("Maria Garcia", "maria@mail.com", "987654321"));
        clientes.add(new Cliente("Carlos Lopez", "carlos@mail.com", "456123789"));
        clientes.add(new Cliente("Ana Martinez", "ana@mail.com", "789456123"));
        clientes.add(new Cliente("Pedro Sanchez", "pedro@mail.com", "321654987"));
    }
    
    public static void main(String[] args) throws Exception {
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        terminalFactory.setInitialTerminalSize(new TerminalSize(90, 30));
        screen = terminalFactory.createScreen();
        screen.startScreen();
        
        gui = new MultiWindowTextGUI(screen);
        
        mostrarLogin();
    }
    
    // ============================================================
    //  LOGIN
    // ============================================================
    private static void mostrarLogin() {
        Window window = new BasicWindow("=== SISTEMA DE LOGIN - LANTERNA ===");
        Panel panel = new Panel(new GridLayout(2));
        
        panel.addComponent(new Label("Usuario:"));
        TextBox userBox = new TextBox();
        panel.addComponent(userBox);
        
        panel.addComponent(new Label("Contraseña:"));
        TextBox passBox = new TextBox().setMask('*');
        panel.addComponent(passBox);
        
        Button loginBtn = new Button("Iniciar Sesion", () -> {
            String user = userBox.getText();
            String pass = passBox.getText();
            
            if ("admin".equals(user) && "1234".equals(pass)) {
                currentUser = user;
                loggedIn = true;
                window.close();
                mostrarMenuPrincipal();
            } else {
                MessageDialog.showMessageDialog(gui, "Error", "Credenciales incorrectas");
            }
        });
        
        panel.addComponent(loginBtn, GridLayout.createHorizontallyFilledLayoutData(2));
        window.setComponent(panel);
        gui.addWindowAndWait(window);
    }
    
    // ============================================================
    //  MENU PRINCIPAL CON NAVBAR
    // ============================================================
    private static void mostrarMenuPrincipal() {
        mainWindow = new BasicWindow("MENU PRINCIPAL - " + currentUser.toUpperCase());
        Panel panel = new Panel(new LinearLayout(Direction.VERTICAL));
        
        // ===== NAVBAR =====
        Panel navbar = new Panel(new LinearLayout(Direction.HORIZONTAL));
        navbar.addComponent(new Label(" [NAVEGACION] "));
        navbar.addComponent(new Button("INICIO", () -> mostrarInicio()));
        navbar.addComponent(new Button("PRODUCTOS", () -> mostrarProductos()));
        navbar.addComponent(new Button("CLIENTES", () -> mostrarClientes()));
        navbar.addComponent(new Button("+ NUEVO", () -> mostrarNuevoCliente()));
        navbar.addComponent(new Button("ESTADISTICAS", () -> mostrarEstadisticas()));
        navbar.addComponent(new Button("SALIR", () -> {
            loggedIn = false;
            currentUser = "";
            mainWindow.close();
            mostrarLogin();
        }));
        panel.addComponent(navbar);
        
        // ===== CONTENIDO PRINCIPAL =====
        panel.addComponent(new EmptySpace(new TerminalSize(1, 1)));
        panel.addComponent(new Label("BIENVENIDO " + currentUser.toUpperCase() + "!"));
        panel.addComponent(new EmptySpace(new TerminalSize(1, 1)));
        panel.addComponent(new Label("Resumen del Sistema:"));
        panel.addComponent(new Label("  • Total Productos: " + productos.size()));
        panel.addComponent(new Label("  • Total Clientes: " + clientes.size()));
        panel.addComponent(new Label("  • Usuario: " + currentUser));
        panel.addComponent(new EmptySpace(new TerminalSize(1, 1)));
        panel.addComponent(new Label("Usa la barra superior para navegar entre secciones"));
        
        mainWindow.setComponent(panel);
        gui.addWindowAndWait(mainWindow);
    }
    
    // ============================================================
    //  SECCION: INICIO
    // ============================================================
    private static void mostrarInicio() {
        Window window = new BasicWindow("PANEL DE CONTROL");
        Panel panel = new Panel(new LinearLayout(Direction.VERTICAL));
        
        panel.addComponent(new Label("=== PANEL DE CONTROL ==="));
        panel.addComponent(new EmptySpace(new TerminalSize(1, 1)));
        panel.addComponent(new Label("Bienvenido al Sistema de Gestion"));
        panel.addComponent(new EmptySpace(new TerminalSize(1, 1)));
        panel.addComponent(new Label("Resumen General:"));
        panel.addComponent(new Label("  • Productos: " + productos.size()));
        panel.addComponent(new Label("  • Clientes: " + clientes.size()));
        panel.addComponent(new Label("  • Usuario: " + currentUser));
        
        double total = productos.stream().mapToDouble(p -> p.precio * p.stock).sum();
        panel.addComponent(new Label("  • Valor Inventario: $" + String.format("%.2f", total)));
        panel.addComponent(new EmptySpace(new TerminalSize(1, 1)));
        panel.addComponent(new Button("Volver", window::close));
        
        window.setComponent(panel);
        gui.addWindowAndWait(window);
    }
    
    // ============================================================
    //  SECCION: PRODUCTOS (TABLA)
    // ============================================================
    private static void mostrarProductos() {
        Window window = new BasicWindow("LISTA DE PRODUCTOS");
        Panel panel = new Panel(new LinearLayout(Direction.VERTICAL));
        
        // Encabezado
        panel.addComponent(new Label("ID  PRODUCTO            PRECIO   STOCK  VALOR TOTAL"));
        panel.addComponent(new Label("--  ------------------  -------  -----  -----------"));
        
        // Datos
        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            String row = String.format("%-3d %-19s $%-7.2f %-5d  $%-10.2f", 
                i + 1, 
                p.nombre.length() > 18 ? p.nombre.substring(0, 18) : p.nombre, 
                p.precio, 
                p.stock, 
                p.precio * p.stock);
            panel.addComponent(new Label(row));
        }
        
        panel.addComponent(new EmptySpace(new TerminalSize(1, 1)));
        panel.addComponent(new Button("Volver", window::close));
        
        window.setComponent(panel);
        gui.addWindowAndWait(window);
    }
    
    // ============================================================
    //  SECCION: CLIENTES (TABLA)
    // ============================================================
    private static void mostrarClientes() {
        Window window = new BasicWindow("LISTA DE CLIENTES");
        Panel panel = new Panel(new LinearLayout(Direction.VERTICAL));
        
        panel.addComponent(new Label("#   NOMBRE               EMAIL                    TELEFONO"));
        panel.addComponent(new Label("--  ------------------  -----------------------  -----------"));
        
        for (int i = 0; i < clientes.size(); i++) {
            Cliente c = clientes.get(i);
            String row = String.format("%-3d %-20s %-23s %-11s", 
                i + 1,
                c.nombre.length() > 18 ? c.nombre.substring(0, 18) : c.nombre,
                c.email.length() > 23 ? c.email.substring(0, 23) : c.email,
                c.telefono);
            panel.addComponent(new Label(row));
        }
        
        panel.addComponent(new EmptySpace(new TerminalSize(1, 1)));
        panel.addComponent(new Button("Volver", window::close));
        
        window.setComponent(panel);
        gui.addWindowAndWait(window);
    }
    
    // ============================================================
    //  SECCION: NUEVO CLIENTE (FORMULARIO)
    // ============================================================
    private static void mostrarNuevoCliente() {
        Window window = new BasicWindow("NUEVO CLIENTE");
        Panel panel = new Panel(new GridLayout(2));
        
        panel.addComponent(new Label("Nombre:"));
        TextBox nombreBox = new TextBox();
        panel.addComponent(nombreBox);
        
        panel.addComponent(new Label("Email:"));
        TextBox emailBox = new TextBox();
        panel.addComponent(emailBox);
        
        panel.addComponent(new Label("Telefono:"));
        TextBox telefonoBox = new TextBox();
        panel.addComponent(telefonoBox);
        
        Button guardarBtn = new Button("Guardar", () -> {
            String nombre = nombreBox.getText();
            String email = emailBox.getText();
            String telefono = telefonoBox.getText();
            
            if (nombre.isEmpty() || email.isEmpty()) {
                MessageDialog.showMessageDialog(gui, "Error", "Nombre y Email son obligatorios");
            } else {
                clientes.add(new Cliente(nombre, email, telefono));
                MessageDialog.showMessageDialog(gui, "Exito", 
                    "Cliente agregado correctamente\nTotal clientes: " + clientes.size());
                window.close();
            }
        });
        
        panel.addComponent(guardarBtn, GridLayout.createHorizontallyFilledLayoutData(2));
        panel.addComponent(new Button("Cancelar", window::close), 
                          GridLayout.createHorizontallyFilledLayoutData(2));
        
        window.setComponent(panel);
        gui.addWindowAndWait(window);
    }
    
    // ============================================================
    //  SECCION: ESTADISTICAS (GRAFICOS SIMULADOS)
    // ============================================================
    private static void mostrarEstadisticas() {
        Window window = new BasicWindow("ESTADISTICAS");
        Panel panel = new Panel(new LinearLayout(Direction.VERTICAL));
        
        double totalInventario = productos.stream()
            .mapToDouble(p -> p.precio * p.stock).sum();
        
        int totalStock = productos.stream().mapToInt(p -> p.stock).sum();
        
        panel.addComponent(new Label("=== ESTADISTICAS DEL SISTEMA ==="));
        panel.addComponent(new EmptySpace(new TerminalSize(1, 1)));
        panel.addComponent(new Label("📊 Resumen General:"));
        panel.addComponent(new Label("  • Total Productos: " + productos.size()));
        panel.addComponent(new Label("  • Total Clientes: " + clientes.size()));
        panel.addComponent(new Label("  • Total Stock: " + totalStock + " unidades"));
        panel.addComponent(new Label("  • Valor Inventario: $" + String.format("%.2f", totalInventario)));
        panel.addComponent(new EmptySpace(new TerminalSize(1, 1)));
        
        panel.addComponent(new Label("📈 Top 3 Productos por Stock:"));
        productos.stream()
            .sorted((a, b) -> Integer.compare(b.stock, a.stock))
            .limit(3)
            .forEach(p -> {
                String barra = "█".repeat(Math.min(p.stock / 2, 30));
                panel.addComponent(new Label("  " + p.nombre + ": " + barra + " (" + p.stock + ")"));
            });
        
        panel.addComponent(new EmptySpace(new TerminalSize(1, 1)));
        panel.addComponent(new Label("👥 Tipos de Clientes:"));
        panel.addComponent(new Label("  • Premium: " + clientes.stream().filter(c -> c.nombre.startsWith("A")).count()));
        panel.addComponent(new Label("  • Regular: " + (clientes.size() - clientes.stream().filter(c -> c.nombre.startsWith("A")).count())));
        panel.addComponent(new EmptySpace(new TerminalSize(1, 1)));
        
        panel.addComponent(new Button("Volver", window::close));
        
        window.setComponent(panel);
        gui.addWindowAndWait(window);
    }
    
    // ============================================================
    //  CLASES DE DATOS
    // ============================================================
    public static class Producto {
        String nombre;
        double precio;
        int stock;
        
        Producto(String nombre, double precio, int stock) {
            this.nombre = nombre;
            this.precio = precio;
            this.stock = stock;
        }
    }
    
    public static class Cliente {
        String nombre;
        String email;
        String telefono;
        
        Cliente(String nombre, String email, String telefono) {
            this.nombre = nombre;
            this.email = email;
            this.telefono = telefono;
        }
    }
}
