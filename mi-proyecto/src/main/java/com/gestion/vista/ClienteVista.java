package com.gestion.vista;

import com.gestion.modelo.Usuario;
import java.util.Scanner;


public class ClienteVista {
	private Scanner sc = new Scanner(System.in);
	private Usuario usuario = new Usuario();


	// Getters
    public Usuario conseguirUsuario() { 
      return usuario; 
    }

    // Setters
    public void colocarNombre(Usuario usuario) { 
      this.usuario = usuario; 
    }
  
	// Metodos


}
