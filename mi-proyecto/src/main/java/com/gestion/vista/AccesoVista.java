package com.gestion.vista;

//import java.io.IOException;

import com.gestion.modelo.Usuario;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

public class AccesoVista {   
	private Usuario usuario;
    // Atributos de la interfaz gráfica
    private DefaultTerminalFactory terminalFactory;
    private Screen screen;
    private WindowBasedTextGUI interfazGraficaUsuario;
    private Window ventana;
    private Panel panel;
    private TextBox cajaUsuario;
    private TextBox cajaContrasena;
    private Button accesoBoton;

    // Constructor
    public AccesoVista(Usuario usuario) {
        this.usuario = usuario;
    }

	// Getters
    public Usuario conseguirUsuario() { 
      return usuario; 
    }

    public WindowBasedTextGUI conseguirInterfazGraficaUsuario() {
        return interfazGraficaUsuario;
    }

    public Window conseguirVentana() {
        return ventana;
    }

    public Panel conseguirPanel() {
        return panel;
    }

    public TextBox conseguirCajaUsuario() {
        return cajaUsuario;
    }

    public TextBox conseguirCajaContrasena() {
        return cajaContrasena;
    }

    public Button conseguirAccesoBoton() {
        return accesoBoton;
    }

    // Setters
    public void colocarUsuario(Usuario usuario) { 
      this.usuario = usuario; 
    }

    public void colocarInterfazGraficaUsuario(WindowBasedTextGUI interfazGraficaUsuario) {
        this.interfazGraficaUsuario = interfazGraficaUsuario;
    }

    public void colocarVentana(Window ventana) {
        this.ventana = ventana;
    }

    public void colocarPanel(Panel panel) {
        this.panel = panel;
    }

    public void colocarCajaUsuario(TextBox cajaUsuario) {
        this.cajaUsuario = cajaUsuario;
    }

    public void colocarCajaContrasena(TextBox cajaContrasena) {
        this.cajaContrasena = cajaContrasena;
    }

    public void colocarAccesoBoton(Button accesoBoton) {
        this.accesoBoton = accesoBoton;
    }

  
	// Metodos
    public void abrirVistaAcceso() {
        try {
            terminalFactory = new DefaultTerminalFactory();
            terminalFactory.setInitialTerminalSize(new TerminalSize(90, 30));
            screen = terminalFactory.createScreen();
            screen.startScreen();        
            interfazGraficaUsuario = new MultiWindowTextGUI(screen);

            ventana = new BasicWindow("=== ACCESO ===");
            panel = new Panel(new GridLayout(2));

            panel.addComponent(new Label("Usuario:"));
            cajaUsuario = new TextBox();
            panel.addComponent(cajaUsuario);
            
            panel.addComponent(new Label("Contrasena:"));
            cajaContrasena = new TextBox().setMask('*');
            panel.addComponent(cajaContrasena);
            
            accesoBoton = new Button("Iniciar Sesion", () -> accionBoton(
                cajaUsuario.getText(), cajaContrasena.getText()
            ));
            
            panel.addComponent(accesoBoton, GridLayout.createHorizontallyFilledLayoutData(2));
            ventana.setComponent(panel);
            interfazGraficaUsuario.addWindowAndWait(ventana);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void accionBoton(String nombre, String contrasena) {        
        usuario.colocarNombreUsuario(nombre);
        usuario.colocarContrasena(contrasena);
    }

    public void cerrarVistaAcceso() {
        ventana.close();
    }
    
}
