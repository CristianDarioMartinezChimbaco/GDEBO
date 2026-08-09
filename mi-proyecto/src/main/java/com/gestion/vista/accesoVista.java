package com.gestion.vista;

import com.gestion.modelo.Usuario;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.gui2.dialogs.MessageDialog;

public class AccesoVista {
    // private Scanner sc = new Scanner(System.in);
    private static WindowBasedTextGUI interfazGraficaUsuario;

	private Usuario usuario = new Usuario();

    // Constructor
    public AccesoVista(Usuario usuario) {
        this.usuario = usuario;
    }

	// Getters
    public Usuario conseguirUsuario() { 
      return usuario; 
    }

    // Setters
    public void colocarNombre(Usuario usuario) { 
      this.usuario = usuario; 
    }
  
	// Metodos
    void imprimirLogin() {
        Window ventana = new BasicWindow("=== ACCESO ===");
        Panel panel = new Panel(new GridLayout(2));
        
        panel.addComponent(new Label("Usuario:"));
        TextBox cajaUsuario = new TextBox();
        panel.addComponent(cajaUsuario);
        
        panel.addComponent(new Label("Contrasena:"));
        TextBox cajaContrasena = new TextBox().setMask('*');
        panel.addComponent(cajaContrasena);
        
        Button accesoBoton = new Button("Iniciar Sesion", AccionBoton(
            cajaUsuario.getText(), cajaContrasena.getText(), ventana
        ));
        
        panel.addComponent(accesoBoton, GridLayout.createHorizontallyFilledLayoutData(2));
        ventana.setComponent(panel);
        interfazGraficaUsuario.addWindowAndWait(ventana);
    }

    public Runnable AccionBoton(String nombre, String contrasena, Window ventana) {        
        usuario.colocarNombreUsuario(nombre);
        usuario.colocarContrasena(contrasena);
        
        if ("admin".equals(usuario.conseguirNombreUsuario()) && "1234".equals(usuario.conseguirContrasena())) {
            
            MessageDialog.showMessageDialog(interfazGraficaUsuario, "Correcto", "Credenciales correctas");
        
            // currentUser = usuario.conseguirNombreUsuario();
            // loggedIn = true;
            ventana.close();
            // mostrarMenuPrincipal();
        } else {
            MessageDialog.showMessageDialog(interfazGraficaUsuario, "Error", "Credenciales incorrectas");
        }
    }




    
}
